package com.binchencoder.study.shorturl;

import java.util.Random;
import org.apache.commons.lang3.StringUtils;

public class ShortURL {

  private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  private static final int BASE = ALPHABET.length();

  /**
   * 将数字转为62进制
   *
   * @param num Long 型数字
   * @return 62进制字符串
   */
  public static String idToShortURL(long num) {
    StringBuilder shortUrl = new StringBuilder();
    int remainder;
    int scale = 62;
    while (num > scale - 1) {
      remainder = Long.valueOf(num % scale).intValue();
      shortUrl.append(ALPHABET.charAt(remainder));
      num = num / scale;
    }

    shortUrl.append(ALPHABET.charAt(Long.valueOf(num).intValue()));
    String value = shortUrl.reverse().toString();
    return StringUtils.leftPad(value, 6, '0');
  }

  public static long shortUrlToId(String shortUrl) {
    int scale = 62;
    shortUrl = shortUrl.replace("^0*", "");
    long num = 0;
    int index;
    for (int i = 0; i < shortUrl.length(); i++) {
      index = ALPHABET.indexOf(shortUrl.charAt(i));
      num += (long) (index * (Math.pow(scale, shortUrl.length() - i - 1)));
    }

    return num;
  }

  public static String encode(long num) {
    StringBuilder sb = new StringBuilder();
    while (num > 0) {
      sb.append(ALPHABET.charAt((int) num % BASE));
      num /= BASE;
    }
    return sb.reverse().toString();
  }

  public static long decode(String str) {
    long num = 0;
    for (int i = 0; i < str.length(); i++) {
      num = num * BASE + ALPHABET.indexOf(str.charAt(i));
    }
    return num;
  }

  public static void main(String[] args) {
    ShortURL shortURL = new ShortURL();
    Random random = new Random(Long.MIN_VALUE);
    for (int i = 1; i < 30; i++) {
      int j = random.nextInt();
      if (j < 0) {
        j = 0 - j;
      }
      String shortUrl = shortURL.idToShortURL(j);
      long id = shortURL.shortUrlToId(shortUrl);
      System.out.println("id=" + j + ", shortUrl=" + shortUrl);
      System.out.println("shortUrl=" + shortUrl + ", id=" + id);
    }

    System.out.println("======================================");
    for (int i = 1; i < 30; i++) {
      int j = random.nextInt();
      if (j < 0) {
        j = 0 - j;
      }
      String shortUrl = shortURL.encode(j);
      long id = shortURL.decode(shortUrl);
      System.out.println("id=" + j + ", shortUrl=" + shortUrl);
      System.out.println("shortUrl=" + shortUrl + ", id=" + id);
    }
  }
}
