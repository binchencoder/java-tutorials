package com.binchencoder.study.files;


import java.io.File;
import java.io.IOException;
import org.junit.Test;

class FileZipUtilsTest {

    @Test
    void zipUncompress() throws IOException {
        File srcZipFile = new File("/home/chenbin", "ner-spacy.zip");
        FileZipUtils.zipUncompress(srcZipFile, "/home/chenbin/data");
    }
}