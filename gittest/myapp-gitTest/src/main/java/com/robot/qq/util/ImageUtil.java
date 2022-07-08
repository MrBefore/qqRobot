package com.robot.qq.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 保存远程图片工具类
 *
 * @author wangzk
 */
public class ImageUtil {


    /**
     * @param strUrl  String http地址
     * @param fileUrl String 本地地址
     */
    public static void saveImage(String strUrl, String fileUrl) {
        writeImageToDisk(getImageFromNetByUrl(strUrl), fileUrl);
    }

    /**
     * 将获取的字节数组保存为文件写入硬盘
     *
     * @param data     byte 字节流
     * @param fileName String 文件地址
     */
    private static void writeImageToDisk(byte[] data, String fileName) {
        try {
            // 本地目录
            File file = new File(fileName);
            File fileParent = file.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
                file.createNewFile();
            }
            FileOutputStream fops = new FileOutputStream(file);
            fops.write(data);
            fops.flush();
            fops.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取远程http地址视图片
     *
     * @param strUrl String http链接
     * @return byte 字节流
     */
    private static byte[] getImageFromNetByUrl(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();
            return readInputStream(inStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取流
     *
     * @param inStream InputStream
     * @return byte
     * @throws Exception Exception
     */
    private static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

}

