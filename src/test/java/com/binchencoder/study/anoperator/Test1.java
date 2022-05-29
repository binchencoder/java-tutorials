package com.binchencoder.study.anoperator;

import org.junit.jupiter.api.Test;

/**
 * Created by chenbin on 20-8-13.
 */
public class Test1 {

  @Test
  public void test() {
    System.out.println(Integer.toBinaryString(4));
  }

  @Test
  public void test1() {
    System.out.println("2 > 2  :" + (2 > 2));
    System.out.println("2 >>  4:" + (2 >> 2));
    System.out.println("2 >>> 4:" + (2 >>> 2));
  }

}
