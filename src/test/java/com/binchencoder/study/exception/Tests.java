package com.binchencoder.study.exception;

import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;

/**
 * @author: chenbin
 * @date: 2021/12/8 上午10:07
 */
public class Tests {

  @Test
  public void testFinally() {
    try {
      throw new DuplicateKeyException("Multiple names");
    } catch (Exception e) {
      throw new RuntimeException("");
    } finally {
      System.out.println("finally");
    }
  }

}
