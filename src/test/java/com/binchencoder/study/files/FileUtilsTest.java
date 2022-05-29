package com.binchencoder.study.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

public class FileUtilsTest {

    @Test
    void testGetFileSuffix() {
        String fileName = "a.txt";
        Assert.assertTrue(FileUtils.getFileSuffix(fileName).equals(".txt"));
    }

    @Test
    void testCopyDirectories() throws IOException {
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
    void testCopyDirectory() throws IOException {
        File rootDir = new File(DIR_PATH);
        FileUtils.copyDirectory(rootDir, rootDir);
    }

    @Test
    void deleteOnExistOfNonSuffix() throws FileNotFoundException {
        File rootDir = new File("/home/chenbin/test/yuliao");
        FileUtils.deleteOnExistOfNonSuffix(rootDir, ".txt");
    }

    @Test
    void copyFilesToDirectory() throws IOException {
        File srcDir = new File("/home/chenbin/test/srctest");
        File destDir = new File("/home/chenbin/test/desttest");
        FileUtils.copyFilesToDirectory(srcDir, destDir, (fileName) -> fileName.endsWith(".txt"));
    }

    @Test
    void unzipAndCopyFilesToDir() throws IOException {
        File zipFile = new File("/home/chenbin/test/srctest.zip");
        String unzipDir = "/home/chenbin/test/ziptemp";
        FileZipUtils.zipUncompress(zipFile, unzipDir);

        File srcDir = new File(unzipDir);
        File destDir = new File("/home/chenbin/test/destdir");
        FileUtils.copyFilesToDirectory(srcDir, destDir, (fileName) -> fileName.endsWith(".txt"));
    }
}