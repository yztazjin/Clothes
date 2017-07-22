package com.ttyy.lib.tskin.core.implmgr;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.ttyy.lib.tskin.Clothes;
import com.ttyy.lib.tskin.core.base.ClothesResourceManager;
import com.ttyy.lib.tskin.core.callback.ClothesResourceInterceptor;
import com.ttyy.lib.tskin.core.callback.ClothesLoadCallback;
import com.ttyy.lib.tskin.core.util.APKUtil;

import java.lang.reflect.InvocationTargetException;

/**
 * author: admin
 * date: 2017/07/18
 * version: 0
 * mail: secret
 * desc: $TSResourceManager
 */

public class $ClothesResourceManager implements ClothesResourceManager {

    Resources mHostResources;
    String mHostPackageName;

    Resources mPluginResources;
    String mPluginPackageName;

    public $ClothesResourceManager(Context mContext){
        mHostResources = mContext.getApplicationContext().getResources();
        mHostPackageName = mContext.getPackageName();
    }

    @Override
    public ClothesResourceManager reset() {
        mPluginResources = null;
        mPluginPackageName = null;
        return this;
    }

    @Override
    public ClothesResourceManager loadPluginResourceManager(Context context, String path, ClothesLoadCallback callback) {
        // 载入插件Resources
        if(callback != null){
            callback.onPre();
        }
        try {
            mPluginResources = APKUtil.loadAPKResources(context, path);
            mPluginPackageName = APKUtil.loadAPKPackageName(context, path);

            if(callback != null){
                callback.onSuccess();
            }
        } catch (IllegalAccessException e) {
            if(callback != null){
                callback.onError(e);
            }
        } catch (InstantiationException e) {
            if(callback != null){
                callback.onError(e);
            }
        } catch (NoSuchMethodException e) {
            if(callback != null){
                callback.onError(e);
            }
        } catch (InvocationTargetException e) {
            if(callback != null){
                callback.onError(e);
            }
        } finally {
            if(callback != null){
                callback.onFinish();
            }
        }

        return this;
    }

    @Override
    public boolean isClothesLoaded() {
        return mPluginResources != null;
    }

    @Override
    public String getResourceDeclareName(int resId) {
        return mHostResources.getResourceEntryName(resId);
    }

    @Override
    public String getResourceDeclareType(int resId) {
        return mHostResources.getResourceTypeName(resId);
    }

    @Override
    public Drawable getDrawable(int resId) {
        Drawable target = null;
        String resName = getResourceDeclareName(resId);

        boolean isResourceFromPlugin = false;

        if (mPluginPackageName != null) {
            String resourceType = getResourceDeclareType(resId);
            int id = mPluginResources.getIdentifier(resName, resourceType, mPluginPackageName);
            if (id != 0) {
                isResourceFromPlugin = true;
                target = mPluginResources.getDrawable(id);
            }
        }

        if (target == null) {
            isResourceFromPlugin = false;
            target = mHostResources.getDrawable(resId);
        }

        ClothesResourceInterceptor interceptor = Clothes.get().getResourceInterceptor();
        if(interceptor != null){

            if(interceptor.shouldIntercept(resName, resId, isResourceFromPlugin)){

                Drawable interceptedValue = interceptor.handleResourceDrawable(resName, resId);
                target = interceptedValue == null ? target : interceptedValue;
            }

        }

        return target;
    }

    @Override
    public int getColor(int resId) {
        ColorStateList target = null;
        String resName = getResourceDeclareName(resId);

        boolean isResourceFromPlugin = false;

        if (mPluginPackageName != null) {
            int id = mPluginResources.getIdentifier(resName, "color", mPluginPackageName);
            if (id != 0) {
                isResourceFromPlugin = true;
                target = mPluginResources.getColorStateList(id);
            }
        }

        if (target == null) {
            isResourceFromPlugin = false;
            target = mHostResources.getColorStateList(resId);
        }

        ClothesResourceInterceptor interceptor = Clothes.get().getResourceInterceptor();
        if(interceptor != null){

            if(interceptor.shouldIntercept(resName, resId, isResourceFromPlugin)){

                ColorStateList interceptedValue = interceptor.handleResourceColorStateList(resName, resId);
                target = interceptedValue == null ? target : interceptedValue;
            }

        }

        return target.getDefaultColor();
    }

    @Override
    public ColorStateList getColorStateList(int resId) {
        ColorStateList target = null;
        String resName = getResourceDeclareName(resId);

        boolean isResourceFromPlugin = false;

        if (mPluginPackageName != null) {
            int id = mPluginResources.getIdentifier(resName, "color", mPluginPackageName);
            if (id != 0) {
                isResourceFromPlugin = true;
                target = mPluginResources.getColorStateList(id);
            }
        }

        if (target == null) {
            isResourceFromPlugin = false;
            target = mHostResources.getColorStateList(resId);
        }

        ClothesResourceInterceptor interceptor = Clothes.get().getResourceInterceptor();
        if(interceptor != null){

            if(interceptor.shouldIntercept(resName, resId, isResourceFromPlugin)){

                ColorStateList interceptedValue = interceptor.handleResourceColorStateList(resName, resId);
                target = interceptedValue == null ? target : interceptedValue;
            }

        }

        return target;
    }

    @Override
    public String getString(int resId) {
        String target = null;
        String resName = getResourceDeclareName(resId);

        boolean isResourceFromPlugin = false;

        if (mPluginPackageName != null) {
            int id = mPluginResources.getIdentifier(resName, "string", mPluginPackageName);
            if (id != 0) {
                isResourceFromPlugin = true;
                target = mPluginResources.getString(id);
            }
        }

        if (target == null) {
            isResourceFromPlugin = false;
            target = mHostResources.getString(resId);
        }

        ClothesResourceInterceptor interceptor = Clothes.get().getResourceInterceptor();
        if(interceptor != null){

            if(interceptor.shouldIntercept(resName, resId, isResourceFromPlugin)){

                String interceptedValue = interceptor.handleResourceString(resName, resId);
                target = interceptedValue == null ? target : interceptedValue;
            }

        }

        return target;
    }

}
