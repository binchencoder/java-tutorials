package com.binchencoder.study.shorturl;

import java.util.HashMap;
import java.util.Map;

/**
 * 短链接生成
 *
 * https://www.jb51.net/article/146221.htm
 */
public class TinyURL {

  public static final char[] array =
      {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
          'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd',
          'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm',
          'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D',
          'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M'};

  public static Map<Character, Integer> charValueMap = new HashMap<Character, Integer>();

  //初始化map
  static {
    for (int i = 0; i < array.length; i++) {
      charValueMap.put(array[i], i);
    }
  }

  /**
   * 把数字转换成相对应的进制,目前支持(2-62)进制
   *
   * @param number
   * @param decimal
   * @return
   */
  public static String numberConvertToDecimal(long number, int decimal) {
    StringBuilder builder = new StringBuilder();
    while (number != 0) {
      builder.append(array[(int) (number - (number / decimal) * decimal)]);
      number /= decimal;
    }
    return builder.reverse().toString();
  }

  /**
   * 把进制字符串转换成相应的数字
   *
   * @param decimalStr
   * @param decimal
   * @return
   */
  public static long decimalConvertToNumber(String decimalStr, int decimal) {
    long sum = 0;
    long multiple = 1;
    char[] chars = decimalStr.toCharArray();
    for (int i = chars.length - 1; i >= 0; i--) {
      char c = chars[i];
      sum += charValueMap.get(c) * multiple;
      multiple *= decimal;
    }
    return sum;
  }

  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      long number = Long.MAX_VALUE - i;
      String decimalStr = numberConvertToDecimal(number, 62);
      System.out.println(number + " 转换成 " + decimalStr);
      long toNumber = decimalConvertToNumber(decimalStr, 62);
      System.out.println(decimalStr + " 转换成 " + toNumber);
    }
  }

}
