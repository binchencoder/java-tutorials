package com.binchencoder.study.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Created by chenbin on 20-7-30.
 */
public class Strings {

  @Test
  public void test1() {
    String a = new String("ABC");
    String b = "ABC";

    System.out.println(a == b);
  }

  @Test
  public void test2() {
    String a = new String("A");
    String b = new String("A");
    System.out.println(a == b);
  }

  @Test
  public void splitString() {
    String str = "aaa,444,11,566,1232,666";

    List<String> lst = new ArrayList<>();
    int idx;
    while ((idx = str.indexOf(",")) > -1) {
      lst.add(str.substring(0, idx));
      str = str.substring(idx + 1);
    }

    System.out.println(lst.toString());
  }

  @Test
  public void reverseString() {
    char[] s = new char[]{'h', 'e', 'l', 'l', 'o'};

    int len = s.length;
    char[] copyData = Arrays.copyOf(s, len);

    int i = 0;
    for (int n = len - 1; n >= 0; n--) {
      s[i] = copyData[n];
      i++;
    }
    System.out.println(s);
  }

  @Test
  public void intern() {
    // Create three strings in three different ways.
    String s1 = "Hello";
    String s2 = new StringBuffer("He").append("llo").toString();
    String s3 = s2.intern();

    // Determine which strings are equivalent using the operator
    System.out.println("s1 == s2?" + (s1 == s2)); // false
    System.out.println("s1 == s3?" + (s1 == s3)); // true
  }
}
