package com.ttyy.lib.tskin.core.callback;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;

/**
 * author: admin
 * date: 2017/07/18
 * version: 0
 * mail: secret
 * desc: TSResourceInterceptor
 */

public interface ClothesResourceInterceptor {

    /**
     * 是否需要拦截
     * @param attrName
     * @param resId
     * @param isFromPlugin
     * @return
     */
    boolean shouldIntercept(String attrName, int resId, boolean isFromPlugin);

    /**
     * 若拦截 处理resources string
     * @param attrName
     * @param resId
     * @return
     */
    String handleResourceString(String attrName, int resId);

    /**
     * 若拦截 处理resources string
     * @param attrName
     * @param resId
     * @return
     */
    ColorStateList handleResourceColorStateList(String attrName, int resId);

    /**
     * 若拦截 处理resources Drawable
     * @param attrName
     * @param resId
     * @return
     */
    Drawable handleResourceDrawable(String attrName, int resId);

//    以下暂不支持
//    /**
//     * 若拦截 处理resources Dimen
//     * @param attrName
//     * @param resId
//     * @return
//     */
//    int handleResourceDimen(String attrName, int resId);
//
//    /**
//     * 若拦截 处理resources Animation
//     * @param attrName
//     * @param resId
//     * @return
//     */
//    Animation handleResourceAnimation(String attrName, int resId);

}
