package com.binchencoder.study.exception;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: chenbin
 * @date: 2022/1/15 上午10:43
 */
public class TestPrintStackTrace {

  private static final Logger LOGGER = LoggerFactory.getLogger(TestPrintStackTrace.class);

  @Test
  public void printStackTrace() {
    try {
      System.out.println("异常前");
      exception();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("异常后");
  }

  public void exception() throws Exception {
    System.out.println("异常发生");
    throw new Exception();
  }

}
