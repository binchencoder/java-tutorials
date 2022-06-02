package com.binchencoder.study.files;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import org.junit.jupiter.api.Test;

public class ReadFileTest {

    @Test
    public void readFileLine() {
        try {
            File file = new File("/home/chenbin/test corpus.txt");
            if (file.exists()) {
                FileReader fr = new FileReader(file);
                LineNumberReader lnr = new LineNumberReader(fr);
                int linenumber = 0;
                while (lnr.readLine() != null) {
                    linenumber++;
                }
                System.out.println("Total number of lines : " + linenumber);
                lnr.close();
            } else {
                System.out.println("File does not exists!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
