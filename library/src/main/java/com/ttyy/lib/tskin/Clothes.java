package com.ttyy.lib.tskin;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.ttyy.lib.tskin.core.ClothesLayoutFactory;
import com.ttyy.lib.tskin.core.ClothesView;
import com.ttyy.lib.tskin.core.base.ClothesResourceManager;
import com.ttyy.lib.tskin.core.base.ClothesViewAttr;
import com.ttyy.lib.tskin.core.callback.ClothesResourceInterceptor;
import com.ttyy.lib.tskin.core.implmgr.$ClothesResourceManager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * author: admin
 * date: 2017/07/18
 * version: 0
 * mail: secret
 * desc: TSkin
 */

public class Clothes {

    private static final String namespace = "http://schemas.android.com/android/skin";

    private static final String attr_enable = "enable";

    ClothesResourceManager mResourceManager;
    Map<Integer, List<ClothesView>> mControlledClothesViews;
    ClothesResourceInterceptor interceptor;

    /**
     * 当前正在换肤的View管理对象
     */
    ClothesView mClothingView;

    private Clothes(){
        mControlledClothesViews = new HashMap<>();
    }

    static class Holder{
        static Clothes INST = new Clothes();
    }

    public static Clothes get(){
        return Holder.INST;
    }

    /**
     * 绑定App
     * @param context
     * @return
     */
    public Clothes bindApp(Context context){
        mResourceManager = new $ClothesResourceManager(context);
        return this;
    }

    /**
     * 拦截器
     * @return
     */
    public Clothes setResourceInterceptor(ClothesResourceInterceptor interceptor){
        this.interceptor = interceptor;
        return this;
    }

    /**
     * 拦截器
     * @return
     */
    public ClothesResourceInterceptor getResourceInterceptor(){
        return this.interceptor;
    }

    /**
     * Clothes资源管理器
     * @return
     */
    public ClothesResourceManager getResourceManager(){
        return mResourceManager;
    }

    public boolean isSkinEnabled(AttributeSet attrs){
        return attrs.getAttributeBooleanValue(namespace, attr_enable, true);
    }

    /**
     * 获得支持属性名的Attr
     * @param name
     * @return
     */
    public ClothesViewAttr getSupportAttr(String name){
        return ClothesSupportedAttrPool.getSupportAttr(name);
    }

    /**
     * 添加动态换肤控制
     * @param key
     * @param inflater
     * @return
     */
    public Clothes addToControl(Object key, LayoutInflater inflater){
        inflater.setFactory(new ClothesLayoutFactory(key));
        return this;
    }

    /**
     * 释放动态换肤控制
     * @param key
     * @return
     */
    public Clothes releaseFromControl(Object key){
        if(key == null){
            releaseAllControl();
        }
        mControlledClothesViews.remove(key.hashCode());
        return this;
    }

    /**
     * 释放动态换肤控制
     * @return
     */
    public Clothes releaseAllControl(){
        mControlledClothesViews.clear();
        return this;
    }

    /**
     * 添加换肤控制的属性View
     * @param key
     * @param view
     * @return
     */
    public Clothes manageSkinView(Object key, ClothesView view){
        List<ClothesView> views = mControlledClothesViews.get(key.hashCode());
        if(views == null){
            views = new LinkedList<>();
            mControlledClothesViews.put(key.hashCode(), views);
        }

        views.add(view);
        return this;
    }

    /**
     * 应用皮肤
     * @param key
     * @return
     */
    public Clothes applySkin(Object key){
        if(key == null){
            applyAllSkins();
        }

        List<ClothesView> views = mControlledClothesViews.get(key.hashCode());
        if(views == null
                || views.isEmpty()){
            return this;
        }else {
            for(ClothesView tmp : views){
                setClothingView(tmp);
                tmp.apply();
            }
        }

        setClothingView(null);
        return this;
    }

    /**
     * 应用皮肤
     * @return
     */
    public Clothes applyAllSkins(){
        for(List<ClothesView> tmpList : mControlledClothesViews.values()){
            if(tmpList == null
                    || tmpList.isEmpty()){
                continue;
            }else {
                for(ClothesView view : tmpList){
                    setClothingView(view);
                    view.apply();
                }
            }
        }

        setClothingView(null);
        return this;
    }

    /**
     * 获取当前正在换肤的View
     * @param <T>
     * @return
     */
    public <T extends View> T getClothingView(){
        return mClothingView == null ? null : (T)mClothingView.getTargetView();
    }

    public Clothes setClothingView(ClothesView view){
        this.mClothingView = view;
        return this;
    }

}
