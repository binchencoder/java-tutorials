package com.binchencoder.study.format;

import org.junit.jupiter.api.Test;

public class StringFormat {

    @Test
    public void format() {
        String format = "a %s b = %d";
        System.out.println(String.format(format, "+", 12, 111));
    }

}
