package com.ttyy.lib.tskin.core.base;

import android.view.View;

/**
 * author: admin
 * date: 2017/07/18
 * version: 0
 * mail: secret
 * desc: TSViewAttr
 */

public interface ClothesViewAttr<T extends View> {

    /**
     * 重设资源
     * @param view
     * @param resId
     */
    void resetResource(T view, int resId);

    String getAttrName();
}
