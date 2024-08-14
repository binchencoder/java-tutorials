package com.binchencoder.study.python;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Run {

    public static void main(String[] args) throws IOException, InterruptedException {
        String result = null;

        // Python文件地址(Linux)
        String pyPath = "/media/chenbin/f1bee7f8-9ffb-4d16-8322-449158457b8d/home/chenbin/github_workspace/python/python3-study/jionlp/tests.py";

        String[] args1 = new String[]{
            "/media/chenbin/f1bee7f8-9ffb-4d16-8322-449158457b8d/home/chenbin/anaconda3/envs/JioNLP/bin/python",
            pyPath,
            ""
        };
        // 执行Python文件，并传入参数
        Process proc = Runtime.getRuntime().exec(args1);
        // 获取Python输出字符串作为输入流被Java读取
        BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
            result += line;
        }

        in.close();
        proc.waitFor();
    }
}
