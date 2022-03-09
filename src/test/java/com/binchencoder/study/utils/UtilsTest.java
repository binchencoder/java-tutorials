package com.binchencoder.study.utils;

import org.bson.types.ObjectId;
import org.junit.Test;

public class UtilsTest {

  @Test
  public void convertToBigInteger() {
    for (int i = 0; i < 100; i++) {
      System.out.println(Utils.convertToBigInteger(new ObjectId().toString()));
    }
  }

}