package com.ttyy.lib.tskin.core;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.ttyy.lib.tskin.Clothes;

/**
 * author: admin
 * date: 2017/07/18
 * version: 0
 * mail: secret
 * desc: TSkinLayoutFactory
 */

public class ClothesLayoutFactory implements LayoutInflater.Factory{

    Object key;

    public ClothesLayoutFactory(Object key){
        this.key = key;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {

        View view = null;
        try {
            int index = name.indexOf(".");
            if (index == -1) {
                // Android系统原生的View
                if("View".equals(name)){
                    // View
                    view = LayoutInflater.from(context).createView(name, "android.view.", attrs);
                }

                if(view == null){
                    // android.widget.
                    view = LayoutInflater.from(context).createView(name, "android.widget.", attrs);
                }

                if(view == null){
                    // android.webkit.
                    view = LayoutInflater.from(context).createView(name, "android.webkit.", attrs);
                }

            } else {

                view = LayoutInflater.from(context).createView(name, null, attrs);
            }
        } catch (ClassNotFoundException e) {
            Log.d("TSkin", "error while create view "+name);
            view = null;
        }

        boolean isSkinEnabled = Clothes.get().isSkinEnabled(attrs);
        if(isSkinEnabled){
            addToTSkinControl(attrs, view);
        }
        return view;
    }

    private void addToTSkinControl(AttributeSet attrs, View view){
        ClothesView controlView = ClothesView.from(attrs, view);
        if(controlView != null){
            Clothes.get().manageSkinView(key, controlView);
            Clothes.get().setClothingView(controlView);
            controlView.apply();
            Clothes.get().setClothingView(null);
        }
    }

}
