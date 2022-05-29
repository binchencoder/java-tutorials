package com.binchencoder.study.displacement;

import org.junit.jupiter.api.Test;

/**
 * Created by chenbin on 18-4-16.
 */
public class TestUnit {

  @Test
  public void test1() {
    System.out.println(Integer.toBinaryString(16));
    System.out.println(Integer.toBinaryString(16 >> 1));
    System.out.println(Integer.toBinaryString(16 >>> 1));
    System.out.println(16 >> 1);
  }
}
