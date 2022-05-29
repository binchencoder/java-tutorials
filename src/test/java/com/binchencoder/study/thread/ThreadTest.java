package com.binchencoder.study.thread;

import org.junit.jupiter.api.Test;

/**
 * Created by chenbin on 18-4-17.
 */
public class ThreadTest {

  @Test
  public void testJoin() {
    Thread thread = new Thread(new JoinDemo());
    thread.start();

    for (int i = 0; i < 20; i++) {
      System.out.println("主线程第" + i + "次执行！");
      if (i >= 2) {
        try {
          // t1线程合并到主线程中，主线程停止执行过程，转而执行t1线程，直到t1执行完毕后继续。
          thread.join();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

}

class JoinDemo implements Runnable {

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.println("线程1第" + i + "次执行！");
    }
  }
}
