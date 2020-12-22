package com.xyt.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;

/**
 * @Author: abby
 * @Date: 2020/11/9 14:43
 *
 * 文件读写
 */
public class FileUtils {

    private static int READ_SIZE = 1024;
//    private static Long FILE_SIZE = 0L;

    /**
     * 文件上传
     * @param file
     * @param is
     * @param os
     */
    public static void fileUpLoad(File file, InputStream is, OutputStream os){
        BufferedInputStream bis = new BufferedInputStream(is);
        BufferedOutputStream bos = new BufferedOutputStream(os);
//        FILE_SIZE = file.length();
        try {
            //文件大小
            int file_size = is.available();
            //总共读取了多少
            int readSize = 0;
            byte[] bytes = new byte[READ_SIZE];
            while (true){
                //文件小于 第一次读取
                if (file_size < READ_SIZE) {
                    byte[] bytes1 = new byte[file_size];
                    bis.read(bytes1);
                    bos.write(bytes1);
                    break;
                } else if (file_size < readSize + READ_SIZE){
                    byte[] bytes1 = new byte[file_size - readSize];
                    bis.read(bytes1);
                    bos.write(bytes1);
                    break;
                } else {
                    bis.read(bytes);
                    readSize += READ_SIZE;
                    bos.write(bytes);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 以流的方式下载文件
     *
     * @return
     */
    public static HttpServletResponse downLoad(String path, HttpServletResponse response){
        InputStream is = null;
        OutputStream os = null;
        try {
            File file = new File(path);
            //获取文件名
            String fileName = file.getName();
            //获取文件后缀
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
            //以流的方式下载文件
            is = new BufferedInputStream(new FileInputStream(file));
            //is.available() 可能会存在堵塞 可以使用 file size代替
            byte[] bytes = new byte[is.available()];
            is.read(bytes);

            //清空response
            response.reset();
            //设置response header
            response.addHeader("Content-Disposition","attachment;filename=" + new String(fileName.getBytes()));
            response.addHeader("Content-Length","" + file.length());

            os = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            os.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    /**
     * 文件下载， 支持在线打开
     * @param path
     * @param isOnline
     * @param response
     */
    public static void downLoadOnLine(String path, boolean isOnline, HttpServletResponse response){
        File file = new File(path);
        if(!file.exists()){
            try {
                response.sendError(404, "file not found");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            byte[] bytes = new byte[1024];
            int len = 0;

            response.reset();
            if(isOnline){ // 在线打开
                URL url = new URL("file://" + path);
                response.setContentType(url.openConnection().getContentType());
                response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
            } else { //下载
                response.setContentType("application/x-msdownload");
                response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
            }
            OutputStream os = response.getOutputStream();
            while ((len = bis.read(bytes)) > 0)
                os.write(bytes, 0 , len);
            bis.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        File file = new File("D://写我.txt");
        //文件不存在则创建
        if(!file.exists()){
            file.createNewFile();
        }
        fileUpLoad(file, new FileInputStream(new File("D://二期视频链接(2)(2).txt")), new FileOutputStream(file));
    }
}
