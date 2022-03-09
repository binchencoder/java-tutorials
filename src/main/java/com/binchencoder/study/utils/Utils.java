package com.binchencoder.study.utils;

import java.math.BigInteger;
import org.bson.types.ObjectId;

public class Utils {

  /**
   * 转换BigInteger
   *
   * @param id
   * @return
   */
  public static BigInteger convertToBigInteger(String id) {
    if (ObjectId.isValid(id)) {
      return new BigInteger(id, 16);
    } else {
      try {
        return new BigInteger(id);
      } catch (Exception e) {
        return new BigInteger("0");
      }
    }
  }
}
