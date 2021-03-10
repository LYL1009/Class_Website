package com.lee.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FileUtil {
   public static void download(ArrayList<String> downloadList) {
        // 线程池
        ExecutorService pool = null;
        HttpURLConnection connection = null;
        //循环下载
        try {
            for (int i = 0; i < downloadList.size(); i++) {
                pool = Executors.newCachedThreadPool();
                final String url = downloadList.get(i);
                String filename = getFilename(downloadList.get(i));
                System.out.println("正在下载第" + (i + 1) + "个文件，地址：" + url);
                Future<HttpURLConnection> future = pool.submit(() -> {
                    HttpURLConnection connection1 = null;
                    connection1 = (HttpURLConnection) new URL(url).openConnection();
                    connection1.setConnectTimeout(10000);//连接超时时间
                    connection1.setReadTimeout(10000);// 读取超时时间
                    connection1.setDoInput(true);
                    connection1.setDoOutput(true);
                    connection1.setRequestMethod("GET");
                    //断点续连,每次要算出range的范围,请参考Http 1.1协议
                    connection1.connect();
                    return connection1;
                });
                connection = future.get();
                System.out.println("下载完成.响应码:" + connection.getResponseCode());
                // 写入文件
                writeFile(new BufferedInputStream(connection.getInputStream()), URLDecoder.decode(filename, "UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != connection) {
                connection.disconnect();
            }
            if (null != pool) {
                pool.shutdown();
            }
        }
    }

    /**
     * 通过截取URL地址获得文件名
     * 注意：还有一种下载地址是没有文件后缀的，这个需要通过响应头中的
     * Content-Disposition字段 获得filename，一般格式为："attachment; filename=\xxx.exe\"
     *
     * @param url
     * @return
     */
    public static String getFilename(String url) {
        return ("".equals(url) || null == url) ? "" : url.substring(url.lastIndexOf("/") + 1, url.length());
    }

    /**
     * 写入文件
     *
     * @param
     */
  public  static void writeFile(BufferedInputStream bufferedInputStream, String filename) {
        //创建本地文件
        File destfileFile = new File("C:\\Users\\yekaile\\Downloads\\springBoot_Mybatis-master\\springBoot_Mybatis-master\\Springboot_Mybatis\\src\\main\\resources\\static\\" + filename);
        if (destfileFile.exists()) {
            destfileFile.delete();
        }
        if (!destfileFile.getParentFile().exists()) {
            destfileFile.getParentFile().mkdir();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(destfileFile);
            byte[] b = new byte[1024];
            int len = 0;
            // 写入文件
            System.out.println("开始写入本地文件.");
            while ((len = bufferedInputStream.read(b, 0, b.length)) != -1) {
                System.out.println("正在写入字节：" + len);
                fileOutputStream.write(b, 0, len);
            }
            System.out.println("写入本地文件完成.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fileOutputStream) {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
                if (null != bufferedInputStream) {
                    bufferedInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
