package com.binchencoder.study.files;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

class FileUtilTest {

    @Test
    void getFileSuffix() {
        String fileName = "a.txt";
        Assert.assertTrue(FileUtil.getFileSuffix(fileName).equals(".txt"));
    }

    @Test
    void copyDirectories() throws IOException {
        File dir1 = new File("/home/chenbin/data/resources/brat/data/449145673583206400");
        File dir2 = new File("/home/chenbin/data/resources/brat/data/449208453736734720");

        File dstDir = new File("/home/chenbin");
        for (int i = 0; i < 2; i++) {
            FileUtils.copyDirectoryToDirectory(dir1, dstDir);
            FileUtils.copyDirectoryToDirectory(dir2, dstDir);
        }
    }
}