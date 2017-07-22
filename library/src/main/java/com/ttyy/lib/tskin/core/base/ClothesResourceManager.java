package com.ttyy.lib.tskin.core.base;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;

import com.ttyy.lib.tskin.core.callback.ClothesLoadCallback;

import java.lang.reflect.InvocationTargetException;

/**
 * author: admin
 * date: 2017/07/18
 * version: 0
 * mail: secret
 * desc: TSResourceManager
 */

public interface ClothesResourceManager {

    /**
     * 重置
     * @return
     */
    ClothesResourceManager reset();

    /**
     * 载入插件资源
     * @param context
     * @param path
     * @return
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    ClothesResourceManager loadPluginResourceManager(Context context, String path, ClothesLoadCallback callback);

    /**
     * 是否加载了第三方皮肤资源
     * @return
     */
    boolean isClothesLoaded();

    /**
     * 获取该资源在res xml配置中设置的key名称
     * @param resId
     * @return
     */
    String getResourceDeclareName(int resId);

    /**
     * 获取res类型 drawable color string dimen
     * @param resId
     * @return
     */
    String getResourceDeclareType(int resId);

    /**
     * 获取Drawable
     * @param resId
     * @return
     */
    Drawable getDrawable(int resId);

    /**
     * 获取Color
     * @param resId
     * @return
     */
    int getColor(int resId);

    /**
     * 获取ColorStateList
     * @param resId
     * @return
     */
    ColorStateList getColorStateList(int resId);

    /**
     * 获取String
     * @param resId
     * @return
     */
    String getString(int resId);

}
