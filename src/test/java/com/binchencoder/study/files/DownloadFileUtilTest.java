package com.binchencoder.study.files;

import com.binchencoder.study.BaseApplicationJunit;
import com.binchencoder.study.common.Constant;
import java.io.File;
import java.io.FileNotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

class DownloadFileUtilTest extends BaseApplicationJunit {

    @Autowired
    private DownloadFileUtil downloadFileUtil;

    @Autowired
//    private BratConfig bratConfig;

    @Test
    void downloadFile() throws FileNotFoundException {
//        File downFile = new File(
//            bratConfig.getCorpusTmpPath() + Constant.SYSTEM_FILE_SEP + "temp.txt");
        File downFile = new File("/home/chenbin/test" + Constant.SYSTEM_FILE_SEP + "temp.txt");
        downloadFileUtil.downloadFile(downFile,
            "https://engine-dev.piesat.cn/bpaas/fs/res/downloadAuthn/XeD2xIeUsiMk8a2aZhBYZrVIT.txt?appKey=9e92d795a36edea0&x-token=5123a1cde469a42772eea00d2b734703");

//        downloadFileUtil.downloadFile(downFile,
//            "https://engine-dev.piesat.cn/bpaas/fs/res/downloadAuthn/pSkTB7yzSKbnkU1GBDwjJmjGp?appKey=9e92d795a36edea0&x-token=88177297eca609b31796e60855daf207");
    }
}