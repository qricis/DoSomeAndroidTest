package com.example.servicebestpractices;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Description
 * <p>
 * 下载功能类服务类
 * 第一个泛型表示要传入一个字符串给后台任务
 * 第二个泛型表示使用整型数据来作为进度的显示单位
 * 第三个泛型表示使用整型数据来反馈执行结果
 * @author qricis on 2020/8/28 15:28
 * @version 1.0.0
 */
public class DownloadTask extends AsyncTask<String,Integer,Integer> {

    /**
     * 定义四个整型表示下载状态成功、失败、暂停、取消
     * */
    public static final int TYPE_SUCCESS = 0;
    public static final int TYPE_FAILED = 1;
    public static final int TYPE_PAUSED = 2;
    public static final int TYPE_CANCELED = 3;

    /**
     * 在构造中通过传入的该参数进行回调
     * */
    private DownloadListener mDownloadListener;

    private boolean isCanceled = false;
    private boolean isPaused = false;
    private int lastProgress;

    public DownloadTask(DownloadListener listener) {
        mDownloadListener = listener;
    }

    /**
     * 用于在后台执行具体的下载逻辑
     * 在子线程运作
     * 参数为下载地址
     * 返回当前执行进度可以调用publishProgress
     * */
    @Override
    protected Integer doInBackground(String... strings) {

        InputStream inputStream = null;
        RandomAccessFile randomAccessFile = null;
        File file = null;

        try {
            //记录一下载的长度
            long downloadedLength = 0;

            //获取下载的URL地址
            String downloadUrl = strings[0];
            //通过下载地址解析出下载的文件名
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            //将文件下载到Environment.DIRECTORY_DOWNLOADS目录下
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            //读取文件地址
            file = new File(directory + fileName);

            //判断是否已经存在要下载的文件，是的话就读取已下载的字节数，这样可以在后面用断点续传的功能
            if (file.exists()) {
                downloadedLength = file.length();
            }

            //获取下载文件的总长度，并赋值给contentLength
            long contentLength = getContentLength(downloadUrl);

            //若文件长度为0，则文件有问题，直接返回false
            if (contentLength == 0) {
                return TYPE_FAILED;
            //若文件长度已经等于已下载文件的长度，则返回success
            } else if (contentLength == downloadedLength) {
                //已下载字节与文件总字节数相等，表示下载完成
                return TYPE_SUCCESS;
            }

            //使用okhttp发送一条网络请求
            OkHttpClient okHttpClient = new OkHttpClient();

            Request request = new Request.Builder()
                    //断点下载，指定从哪个字节开始下载
                    .addHeader("RANGE","bytes=" + downloadedLength + "-")
                    .url(downloadUrl)
                    .build();

            //判断请求是否通过
            Response response = okHttpClient.newCall(request).execute();

            if (response != null) {

                inputStream = response.body().byteStream();
                randomAccessFile = new RandomAccessFile(file,"rw");
                //跳过已下载的字节
                randomAccessFile.seek(downloadedLength);

                byte[] bytes = new byte[2014];
                int total = 0;
                int len;

                //判断在下载途中，用户有没有触发停止或者取消操作
                while ((len = inputStream.read(bytes)) != -1) {
                    //有的话，则做出相应的回应
                    if (isCanceled) {
                        return TYPE_CANCELED;
                    } else if (isPaused) {
                        return TYPE_PAUSED;
                    } else {
                        //没有就实时计算当前的下载进度
                        total += len;
                        randomAccessFile.write(bytes,0,len);
                        //计算已下载的百分比
                        int progress = (int)((total + downloadedLength) * 100 / contentLength);
                        //调用该方法进行通知
                        //调用该方法，onProgressUpdate会很快被调用
                        publishProgress(progress);
                    }
                }

                response.body().close();
                return TYPE_SUCCESS;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                if (isCanceled && file != null) {
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return TYPE_FAILED;
    }

    /**
     * 后台调用publishProgress时，该方法被调用
     * 用于在界面上更新当前的下载进度
     * 从参数中获取到当前的下载进度，然后与上一次的进度进行对比
     * 有变化的话，就调用mDownloadListener.onProgress(progress)来通知下载进度更新
     * */
    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        if (progress > lastProgress) {
            mDownloadListener.onProgress(progress);
            lastProgress = progress;
        }
    }

    /**
     * 当后台进程执行完毕return时，该方法被调用
     * 用于通知最终的下载结果
     * 根据参数中传入的下载状态来进行回调
     * */
    @Override
    protected void onPostExecute(Integer integer) {
        switch (integer) {
            case TYPE_SUCCESS:
                mDownloadListener.onSuccess();
                break;
            case TYPE_FAILED:
                mDownloadListener.onFailed();
                break;
            case TYPE_PAUSED:
                mDownloadListener.onPaused();
                break;
            case TYPE_CANCELED:
                mDownloadListener.onCanceled();
                break;
            default:
                break;
        }
    }

    public void pauseDownload() {
        isPaused = true;
    }

    public void canceledDownload() {
        isCanceled = true;
    }

    private long getContentLength(String downloadUrl) throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();

        Response response = client.newCall(request).execute();

        if (response != null && response.isSuccessful()) {
            long contentLength = response.body().contentLength();
            response.body().close();
            return contentLength;
        }

        return 0;
    }
}
