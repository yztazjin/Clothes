package com.ttyy.tskin.demo;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.ttyy.lib.tskin.Clothes;
import com.ttyy.lib.tskin.core.callback.ClothesLoadCallbackAdapter;
import com.ttyy.lib.tskin.core.callback.ClothesResourceInterceptor;
import com.ttyy.lib.tskin.core.callback.ClothesLoadCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Clothes.get().bindApp(this);
        Clothes.get().addToControl(this, LayoutInflater.from(this));

        Clothes.get().setResourceInterceptor(new ClothesResourceInterceptor() {
            @Override
            public boolean shouldIntercept(String attrName, int resId, boolean isFromPlugin) {
                if(Clothes.get().getClothingView().getId() == R.id.tv_hello1){
                    return true;
                }
                return false;
            }

            @Override
            public String handleResourceString(String attrName, int resId) {

                return "ClothesSkin Text";
            }

            @Override
            public ColorStateList handleResourceColorStateList(String attrName, int resId) {
                return getResources().getColorStateList(resId);
            }

            @Override
            public Drawable handleResourceDrawable(String attrName, int resId) {
                return getResources().getDrawable(resId);
            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Clothes.get().getResourceManager().isClothesLoaded()){
                    Clothes.get().getResourceManager().reset();
                    Clothes.get().applySkin(MainActivity.this);
                }else {
                    String path = copySkinApk();
                    Clothes.get().getResourceManager().loadPluginResourceManager(getApplication(), path, new ClothesLoadCallbackAdapter() {
                        @Override
                        public void onSuccess() {
                            Clothes.get().applyAllSkins();
                        }
                    });
                }
            }
        });

    }

    String copySkinApk() {

        try {
            InputStream is = getAssets().open("clothes-debug.apk");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = is.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }

            File dirFile = getExternalFilesDir("skins");
            File apkFile = new File(dirFile, "clothes_plugin.apk");
            if (!apkFile.exists())
                apkFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(apkFile);
            fos.write(baos.toByteArray());

            fos.flush();
            fos.close();
            baos.close();
            is.close();

            return apkFile.getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
