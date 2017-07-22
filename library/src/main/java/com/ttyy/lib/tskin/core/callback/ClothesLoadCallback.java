package com.ttyy.lib.tskin.core.callback;

/**
 * author: admin
 * date: 2017/07/18
 * version: 0
 * mail: secret
 * desc: TSResourceLoadCallback
 */

public interface ClothesLoadCallback {

    /**
     * 加载之前
     */
    void onPre();

    /**
     * 加载成功
     */
    void onSuccess();

    /**
     * 加载出错
     */
    void onError(Exception e);

    /**
     * 任务执行结束
     */
    void onFinish();

}
