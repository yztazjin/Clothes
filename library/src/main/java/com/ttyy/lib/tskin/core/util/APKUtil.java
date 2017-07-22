package com.ttyy.lib.tskin.core.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * author: admin
 * date: 2017/07/18
 * version: 0
 * mail: secret
 * desc: ResourceLoadTool
 */

public class APKUtil {

    private APKUtil(){}

    /**
     * 获取插件APK中的包信息
     * @param context
     * @param apkPath
     * @return
     */
    protected static PackageInfo loadApkInfo(Context context, String apkPath){
        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
        return info;
    }

    /**
     * 获取插件APK中的包名
     * @param context
     * @param apkPath
     * @return
     */
    public static String loadAPKPackageName(Context context, String apkPath){
        PackageInfo info = loadApkInfo(context, apkPath);
        return info.packageName;
    }

    /**
     * 加载插件APK中的Resources资源
     * @param context
     * @param apkPath
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public static Resources loadAPKResources(Context context, String apkPath) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        // 调用am中的addAssetPath
        // 通过AssetManager加载插件中的APK资源
        // 注意 此时插件的Apk资源已经加入Resources管理了
        // 宿主之所以能够直接获取到资源是因为默认是通过宿主包名去获取资源的
        AssetManager am = AssetManager.class.newInstance();
        Method method = am.getClass().getMethod("addAssetPath", String.class);
        method.setAccessible(true);
        method.invoke(am, apkPath);

        Resources originResources = context.getResources();
        Resources apkResources = new Resources(am, originResources.getDisplayMetrics(), originResources.getConfiguration());

        return apkResources;
    }

}
