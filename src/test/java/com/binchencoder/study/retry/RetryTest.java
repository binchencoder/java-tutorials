package com.binchencoder.study.retry;

import org.junit.Test;

public class RetryTest {

    @Test
    public void retry1() {
        // only retry 3 times.
        int retryTimes = 3;
        boolean result = false;
        while (retryTimes-- > 0) {
            System.out.println("retryTimes: " + retryTimes);
            if (retryTimes == 0) {
                return;
            }
        }
        if (!result) {
            throw new IllegalArgumentException("");
        }
    }

}
