package com.binchencoder.study.word2txt;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Main {

    public static void main(String[] args) throws Exception {
        try {
//            定义word文件所在路径
            String path = "/home/chenbin/temp";
//          定义输出txt文件所在路径
            String outdocPath = "/home/chenbin/temp";
            String outdocxPath = "/home/chenbin/temp";
            path = URLDecoder.decode(path, "UTF-8");

            String fileName = "doc.txt";
            WordToTxt.word2txt("/home/chenbin/temp/doc.docx", fileName, outdocPath, outdocxPath);
            System.out.println("转换完毕");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
