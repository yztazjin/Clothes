package com.ttyy.lib.tskin;

import com.ttyy.lib.tskin.core.base.ClothesViewAttr;
import com.ttyy.lib.tskin.core.implattr.ViewAttrBackground;
import com.ttyy.lib.tskin.core.implattr.ViewAttrImageSrc;
import com.ttyy.lib.tskin.core.implattr.ViewAttrText;
import com.ttyy.lib.tskin.core.implattr.ViewAttrTextColor;

import java.util.HashMap;

/**
 * author: admin
 * date: 2017/07/18
 * version: 0
 * mail: secret
 * desc: TSkinAttrs
 */

public class ClothesSupportedAttrPool {

    static HashMap<String, ClothesViewAttr> mSupportAttrNames;

    static {
        mSupportAttrNames = new HashMap<String, ClothesViewAttr>(){
            {
                put(ViewAttrBackground.INSTANCE.getAttrName(), ViewAttrBackground.INSTANCE);
                put(ViewAttrImageSrc.INSTANCE.getAttrName(), ViewAttrImageSrc.INSTANCE);
                put(ViewAttrText.INSTANCE.getAttrName(), ViewAttrText.INSTANCE);
                put(ViewAttrTextColor.INSTANCE.getAttrName(), ViewAttrTextColor.INSTANCE);
            }
        };
    }

    static boolean isSupportAttr(String attrName){
        return mSupportAttrNames.get(attrName) != null;
    }

    static ClothesViewAttr getSupportAttr(String name){
        return mSupportAttrNames.get(name);
    }

    static void addSupportAttr(ClothesViewAttr attr){
        mSupportAttrNames.put(attr.getAttrName(), attr);
    }

    static void removeSupportAttr(String attrName){
        mSupportAttrNames.remove(attrName);
    }

}
