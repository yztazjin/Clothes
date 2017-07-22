package com.ttyy.lib.tskin.core.implattr;

import android.view.View;

import com.ttyy.lib.tskin.Clothes;
import com.ttyy.lib.tskin.core.base.ClothesViewAttr;

/**
 * author: admin
 * date: 2017/07/18
 * version: 0
 * mail: secret
 * desc: ViewAttrBackground
 */

public class ViewAttrBackground implements ClothesViewAttr<View> {

    public static ClothesViewAttr INSTANCE = new ViewAttrBackground();

    private ViewAttrBackground(){

    }

    @Override
    public void resetResource(View view, int resId) {
        view.setBackgroundDrawable(Clothes.get().getResourceManager().getDrawable(resId));
    }

    @Override
    public String getAttrName() {
        return "background";
    }
}
