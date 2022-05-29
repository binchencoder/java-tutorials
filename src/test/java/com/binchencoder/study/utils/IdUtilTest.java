package com.binchencoder.study.utils;

import static org.junit.Assert.*;

import com.binchencoder.study.shorturl.ShortURL;
import com.binchencoder.study.shorturl.TinyURL;
import org.junit.jupiter.api.Test;

public class IdUtilTest {

  long userId = 232434l;

  @Test
  public void getUniqueID() throws InterruptedException {
    for (int i = 1; i < 100; i++) {
      Thread.sleep(1l);
      long id = IdUtil.getUniqueID(userId);
      String shortStr = ShortURL.idToShortURL(id);
      String shortStr1 = TinyURL.numberConvertToDecimal(id, 62);
      System.out.println("id=" + id + ", shortStr=" + shortStr + ", shortStr1=" + shortStr1);
      System.out.println("shortStr=" + shortStr + ", id=" + ShortURL.shortUrlToId(shortStr));
      System.out.println(
          "shortStr1=" + shortStr1 + ", id=" + TinyURL.decimalConvertToNumber(shortStr1, 62));
    }
  }
}