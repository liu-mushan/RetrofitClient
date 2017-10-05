package com.mushan.network;

import android.os.Environment;

import java.io.File;

/**
 * @author : liujian
 * @since : 2017/10/4
 */

public class Utils {

    /**
     * 检查sd卡状态
     */
    private static boolean checkSDStatus() {
        //判断sd是否可用
        String sdStatus = Environment.getExternalStorageState();
        return sdStatus.equals(Environment.MEDIA_MOUNTED);
    }


    /**
     * 获得下载保存默认地址
     */
    public static String getDefaultDownLoadPath() {
        if (checkSDStatus())
            return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()+ File.separator;
        return "";
    }


    /**
     * 从url中，获得默认文件名
     */
    public static String getDefaultDownLoadFileName(String url) {
        if (url == null || url.length() == 0) return "file";
        int nameStart = url.lastIndexOf('/')+1;
        return url.substring(nameStart);
    }
}
