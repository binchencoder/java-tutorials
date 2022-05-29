package com.binchencoder.study.files;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

public class CommonIoFileUtilsTest {

    private static final String DIR_PATH = "/home/chenbin/test/yuliao";

    @Test
    public void testCopyToDirectory() throws IOException {
        File rootDir = new File(DIR_PATH);
        // 会把目录下的所有文件都拷贝一遍, 这样会出现重名问题
        FileUtils.copyToDirectory(Arrays.asList(rootDir.listFiles()), rootDir);
    }

}
