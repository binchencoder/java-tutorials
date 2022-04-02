package com.binchencoder.study.string;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * 生成随机字符串
 *
 * @author: chenbin
 * @date: 2022/4/2 上午11:29
 */
public class RandomStringTest extends TestCase {

    @Test
    public void testRandomString() {
        System.out.println(RandomString.randomString(10));
    }
}