package com.binchencoder.study.shorturl;

import com.binchencoder.study.utils.IdUtil;
import org.junit.jupiter.api.Test;

public class TinyURLTest {

  Long userId = 2855222244l;

  @Test
  public void numberConvertToDecimal() throws InterruptedException {
    for (int i = 1; i < 100; i++) {
      Thread.sleep(1);

      long now = System.currentTimeMillis();
      long id = IdUtil.getUniqueID(userId);

      String decimalStr = TinyURL.numberConvertToDecimal(id, 62);
      System.out.println("now=" + now + ", number=" + id + ", shortUrl=" + decimalStr);
      long toNumber = TinyURL.decimalConvertToNumber(decimalStr, 62);
      System.out.println("now=" + now + ", shortUrl=" + decimalStr + ", number" + toNumber);
    }
  }

  @Test
  public void numberConvertToDecimal1() throws InterruptedException {
    for (int i = 1; i < 100; i++) {
      Thread.sleep(1);

      long now = System.currentTimeMillis();
      long id = IdUtil.getUniqueID(userId);

      String decimalStr = TinyURL.numberConvertToDecimal(id, 62);
      System.out.println("now=" + now + ", number=" + id + ", shortUrl=" + decimalStr);
      long toNumber = TinyURL.decimalConvertToNumber(decimalStr, 62);
      System.out.println("now=" + now + ", shortUrl=" + decimalStr + ", number" + toNumber);
    }
  }

  @Test
  public void decimalConvertToNumber() {
  }
}