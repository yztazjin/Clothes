package com.ttyy.lib.tskin.core;

import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.ttyy.lib.tskin.Clothes;
import com.ttyy.lib.tskin.core.base.ClothesViewAttr;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * author: admin
 * date: 2017/07/18
 * version: 0
 * mail: secret
 * desc: TSKinVie
 */

public final class ClothesView {

    View mTargetView;

    List<ControlAttr> mControlledAttrs;

    protected ClothesView(View mTargetView){
        this.mTargetView = mTargetView;
        mControlledAttrs = new LinkedList<>();
    }

    /**
     * 用户自定义加入管理
     * @param view
     * @param resIdNames id resTagName  resTagName="id"
     * @return
     */
    public static ClothesView from(View view, Map<Integer, String> resIdNames){
        if(view == null
                || resIdNames == null
                || resIdNames.isEmpty()){
            return null;
        }

        ClothesView skinedView = new ClothesView(view);
        for(Map.Entry<Integer, String> entry : resIdNames.entrySet()){
            String resName = entry.getValue();
            int resId = entry.getKey();
            ClothesViewAttr tsViewAttr = Clothes.get().getSupportAttr(resName);
            if(tsViewAttr != null){
                // @符号表示是引用的资源
                // 插件化换肤只能更换引用的资源,写死的资源无法更换
                // @xxxxx xxxx就是真实的R中id值
                ControlAttr attr = new ControlAttr();
                attr.mHostView = skinedView;
                attr.resId = resId;
                attr.applyTSViewAttr = tsViewAttr;

                skinedView.mControlledAttrs.add(attr);
            }
        }

        if(skinedView.mControlledAttrs.size() < 1){
            return null;
        }

        return skinedView;
    }

    /**
     * TSkinLayoutFactory创建View时，自动绑定
     * @param attrs
     * @param view
     * @return
     */
    static ClothesView from(AttributeSet attrs, View view){

        if(attrs == null
                || view == null)
            return null;

        ClothesView skinedView = new ClothesView(view);
        Log.e("Test", "view "+view+" "+view.getId());
        for(int i = 0; i < attrs.getAttributeCount(); i++){
            // 属性名称
            // android:xxxx
            // xxxx就是属性名称
            String name = attrs.getAttributeName(i);
            // 属性对应的value的String值 @color/name => @xxxxx 转换为R中该字段对应的key
            String value = attrs.getAttributeValue(i);

            ClothesViewAttr tsViewAttr = Clothes.get().getSupportAttr(name);
            if(tsViewAttr != null
                    &&  value.startsWith("@")){
                // @符号表示是引用的资源
                // 插件化换肤只能更换引用的资源,写死的资源无法更换
                // @xxxxx xxxx就是真实的R中id值
                int intValue = Integer.parseInt(value.substring(1));
                ControlAttr attr = new ControlAttr();
                attr.mHostView = skinedView;
                attr.resId = intValue;
                attr.applyTSViewAttr = tsViewAttr;
                skinedView.mControlledAttrs.add(attr);
            }
        }

        if(skinedView.mControlledAttrs.size() < 1){
            return null;
        }

        return skinedView;
    }

    public void apply(){
        Clothes.get().setClothingView(this);
        for(ControlAttr tmp : mControlledAttrs){
            tmp.apply();
        }
        Clothes.get().setClothingView(null);
    }

    /**
     * 实际换肤的View
     * @return
     */
    public View getTargetView(){
        return mTargetView;
    }

    protected static class ControlAttr {

        ClothesView mHostView;
        ClothesViewAttr applyTSViewAttr;
        int resId;

        void apply(){
            applyTSViewAttr.resetResource(mHostView.mTargetView, resId);
        }

    }
}
