package com.binchencoder.study.thread;

import org.junit.jupiter.api.Test;

/**
 * Created by chenbin on 18-4-17.
 */
public class ThreadLocalTest {

  @Test
  public void test() {
    ThreadLocalDemo1 d = new ThreadLocalDemo1();
    d.test1();
  }
}

class ThreadLocalDemo1 {

  private static final ThreadLocal<String> schemaLocal = new ThreadLocal<>();

  public void test1() {
    String a = schemaLocal.get();
    ThreadLocalDemo2 demo2 = new ThreadLocalDemo2();
    demo2.test(a);
  }
}

class ThreadLocalDemo2 {

  private static final ThreadLocal<String> slocal = new ThreadLocal<>();

  public void test(String b) {
    String a = slocal.get();
    System.out.println(b);
  }
}