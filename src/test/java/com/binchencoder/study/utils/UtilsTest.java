package com.binchencoder.study.utils;

import java.math.BigInteger;
import org.bson.types.ObjectId;
import org.junit.Test;

public class UtilsTest {

  @Test
  public void convertToBigInteger() {
    for (int i = 0; i < 100; i++) {
      BigInteger number = Utils.convertToBigInteger(new ObjectId().toString());
      System.out.println("BigInteger=" + number + ", long=" + number.longValue());
    }
  }

}