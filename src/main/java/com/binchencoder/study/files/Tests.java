package com.binchencoder.study.files;

import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class Tests {

    @Test
    public void chmod777Test() throws IOException {
        File file = new File("/home/chenbin/data/filetest");
        file.mkdirs();

        addRChmod777(file);
    }

    @Test
    public void listFiles() throws IOException {
        File file = new File("/home/chenbin/data/algorithm/ner-spacy/training_input/20220711");
        for (File listFile : file.listFiles()) {
            System.out.println(listFile.getAbsolutePath());
        }
    }

    /**
     * 增加权限, 使路径及子路径都有权限
     */
    private static void addRChmod777(File file) throws IOException {
        if (!isWindows()) {
            String cmdGrant = "chmod -R 777 " + file.getAbsolutePath();
            log.info("#addRChmod777: file augmentation after moving: {}", cmdGrant);
            Runtime.getRuntime().exec(cmdGrant);
        }
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").startsWith("Win");
    }
}
