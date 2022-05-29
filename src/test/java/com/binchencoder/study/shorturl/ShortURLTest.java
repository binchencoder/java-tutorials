package com.binchencoder.study.shorturl;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

public class ShortURLTest {

  Long userId = 285555l;

  @Test
  public void idToShortURL() throws InterruptedException {
    for (int i = 1; i < 100; i++) {
      Thread.sleep(1);
      long time = System.currentTimeMillis();
      long num = time + userId;

      String shortUrl = ShortURL.encode(num);
      long id = ShortURL.decode(shortUrl);
      System.out.println("time=" + time + ", id=" + num + ", shortUrl=" + shortUrl);
      System.out.println("time=" + time + ", shortUrl=" + shortUrl + ", id=" + id);
    }
  }

  @Test
  public void shortUrlToId() {

  }
}