package com.ttyy.lib.tskin.core.implattr;

import android.content.res.ColorStateList;
import android.widget.TextView;

import com.ttyy.lib.tskin.Clothes;
import com.ttyy.lib.tskin.core.base.ClothesViewAttr;

/**
 * author: admin
 * date: 2017/07/18
 * version: 0
 * mail: secret
 * desc: ViewAttrTextColor
 */

public class ViewAttrTextColor implements ClothesViewAttr<TextView> {

    public static ClothesViewAttr INSTANCE = new ViewAttrTextColor();

    private ViewAttrTextColor(){

    }

    @Override
    public void resetResource(TextView view, int resId) {
        ColorStateList color = Clothes.get().getResourceManager().getColorStateList(resId);
        view.setTextColor(color);
    }

    @Override
    public String getAttrName() {
        return "textColor";
    }
}
