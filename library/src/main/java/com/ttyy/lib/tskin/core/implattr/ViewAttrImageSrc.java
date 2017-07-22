package com.ttyy.lib.tskin.core.implattr;

import android.widget.ImageView;

import com.ttyy.lib.tskin.Clothes;
import com.ttyy.lib.tskin.core.base.ClothesViewAttr;

/**
 * author: admin
 * date: 2017/07/18
 * version: 0
 * mail: secret
 * desc: ViewAttrDrawableSrc
 */

public class ViewAttrImageSrc implements ClothesViewAttr<ImageView> {

    public static ClothesViewAttr INSTANCE = new ViewAttrImageSrc();

    private ViewAttrImageSrc(){

    }

    @Override
    public void resetResource(ImageView view, int resId) {
        view.setImageDrawable(Clothes.get().getResourceManager().getDrawable(resId));
    }

    @Override
    public String getAttrName() {
        return "src";
    }
}
