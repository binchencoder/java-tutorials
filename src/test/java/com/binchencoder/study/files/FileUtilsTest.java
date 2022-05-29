package com.binchencoder.study.files;

import java.io.File;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

public class FileUtilsTest {

    @Test
    public void testGetFileSuffix() {
        String fileName = "a.txt";
        Assert.assertTrue(FileUtils.getFileSuffix(fileName).equals(".txt"));
    }

    @Test
    public void testCopyDirectories() throws IOException {
        File dir1 = new File("/home/chenbin/data/resources/brat/data/449145673583206400");
        File dir2 = new File("/home/chenbin/data/resources/brat/data/449208453736734720");

        File dstDir = new File("/home/chenbin/data");
        for (int i = 0; i < 2; i++) {
            org.apache.commons.io.FileUtils.copyDirectoryToDirectory(dir1, dstDir);
            org.apache.commons.io.FileUtils.copyDirectoryToDirectory(dir2, dstDir);
        }
    }

    private static final String DIR_PATH = "/home/chenbin/test/yuliao";
    @Test
    public void testCopyDirectory() throws IOException {
        File rootDir = new File(DIR_PATH);
        FileUtils.copyDirectory(rootDir, rootDir);
    }
}