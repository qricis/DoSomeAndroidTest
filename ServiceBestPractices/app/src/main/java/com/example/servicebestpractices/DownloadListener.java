package com.example.servicebestpractices;

/**
 * Description
 * <p>
 * 定义一个回调接口，用于对下载过程中的各种状态进行监听和回调
 * @author qricis on 2020/8/28 15:14
 * @version 1.0.0
 */
public interface DownloadListener {

    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPaused();

    void onCanceled();

}
