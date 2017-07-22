package com.ttyy.lib.tskin.core.implattr;

import android.widget.TextView;

import com.ttyy.lib.tskin.Clothes;
import com.ttyy.lib.tskin.core.base.ClothesViewAttr;

/**
 * author: admin
 * date: 2017/07/18
 * version: 0
 * mail: secret
 * desc: ViewAttrText
 */

public class ViewAttrText implements ClothesViewAttr<TextView> {

    public static ClothesViewAttr INSTANCE = new ViewAttrText();

    private ViewAttrText(){

    }

    @Override
    public void resetResource(TextView view, int resId) {
        view.setText(Clothes.get().getResourceManager().getString(resId));
    }

    @Override
    public String getAttrName() {
        return "text";
    }
}
