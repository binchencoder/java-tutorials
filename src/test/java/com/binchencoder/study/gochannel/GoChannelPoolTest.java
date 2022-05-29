package com.binchencoder.study.gochannel;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;

public class GoChannelPoolTest {

  @Test
  public void test() throws InterruptedException {
    GoChannelPool pool = GoChannelPool.getDefaultInstance();
    final GoChannelPool.GoChannel<Integer> numberCh = pool.newChannel();
    final GoChannelPool.GoChannel<String> stringCh = pool.newChannel();
    final GoChannelPool.GoChannel<String> otherCh = pool.newChannel();

    ExecutorService executorService = Executors.newCachedThreadPool();
    int times = 2000;
    final CountDownLatch countDownLatch = new CountDownLatch(times * 2);

    final AtomicInteger numTimes = new AtomicInteger();
    final AtomicInteger strTimes = new AtomicInteger();
    final AtomicInteger defaultTimes = new AtomicInteger();

    final int finalTimes = times;
    executorService.submit(() -> {
      for (int i = 0; i < finalTimes; i++) {
        numberCh.offer(i);

        try {
          Thread.sleep((long) (Math.random() * 10));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    executorService.submit(() -> {
      for (int i = 0; i < finalTimes; i++) {
        stringCh.offer("s" + i + "e");

        try {
          Thread.sleep((long) (Math.random() * 10));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    int otherTimes = 3;
    for (int i = 0; i < otherTimes; i++) {
      otherCh.offer("a" + i);
    }

    for (int i = 0; i < times * 2 + otherTimes; i++) {
      pool.select(new GoChannelPool.GoSelectConsumer() {
        @Override
        void accept(GoChannelPool.GoChannelObject t) {
          // The data order should be randomized.
          System.out.println(t.data);

          countDownLatch.countDown();

          if (t.belongsTo(stringCh)) {
            strTimes.incrementAndGet();
            return;
          } else if (t.belongsTo(numberCh)) {
            numTimes.incrementAndGet();
            return;
          }

          defaultTimes.incrementAndGet();
        }
      });
    }
    countDownLatch.await(10, TimeUnit.SECONDS);
  }

}