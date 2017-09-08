package com.ttyy.lib.tskin.core;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * author: admin
 * date: 2017/09/08
 * version: 0
 * mail: secret
 * desc: FactoryForSupportV7
 */

class ClothesLayoutFactoryForSupportV7 {

    static View createView(String name, Context context, AttributeSet attrs){
        // support v7 自定义View暂不提供支持
        return null;
    }

    static boolean isViewFromSupportV7(String name){
        return name.startsWith("android.support.v7.widget");
    }

}
