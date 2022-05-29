package com.binchencoder.study.systemprop;

import java.util.Properties;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class SystemTest extends TestCase {

    @Test
    public void test() {
        String proVal = System.getProperty("file.separator");
        System.out.println(proVal);
    }

    @Test
    public void printSystemProps() {
        Properties props = System.getProperties();
    }

}
