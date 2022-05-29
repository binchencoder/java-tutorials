package com.binchencoder.study.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;

/**
 * Created by chenbin on 21-3-16.
 */
public class CompletableFutureTest {

  @Test
  public void threadHunger() throws InterruptedException {
    CountDownLatch countDownLatch = new CountDownLatch(3);
    for (int i = 0; i < countDownLatch.getCount(); i++) {
      final int m = i;
      CompletableFuture.runAsync(() -> {
        CountDownLatch cdl = new CountDownLatch(4);
        for (int j = 0; j < cdl.getCount(); j++) {
          final int n = j;
          CompletableFuture.runAsync(() -> {
            System.out.println("cdl down: " + n);
            cdl.countDown();
          });
        }
        try {
          cdl.await();
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

        System.out.println("countDownLatch down: " + m);
        countDownLatch.countDown();
      });
    }

    countDownLatch.await();
    System.out.println("threadHunger done");
  }

  @Test
  public void forkJoinPool() throws InterruptedException {
    List<CompletableFuture<Void>> futures = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      AtomicInteger atomicInteger = new AtomicInteger(i);
      futures.add(CompletableFuture.runAsync(() -> {
//        if (true) {
//          throw new RuntimeException();
//        }

        CompletableFuture<?>[] completableFutures = new CompletableFuture[10];
        for (int j = 0; j < completableFutures.length; j++) {
          AtomicInteger atomicInteger2 = new AtomicInteger(j);
          completableFutures[j] = CompletableFuture.runAsync(() -> {
            System.out.printf("%d %d.\n", atomicInteger.get(), atomicInteger2.get());
          });
        }

        try {
          CompletableFuture.allOf(completableFutures).get();
        } catch (InterruptedException | ExecutionException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }));
    }

    try {
      CompletableFuture.allOf(futures.toArray(new CompletableFuture[]{})).get();
    } catch (ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println("forkJoinPool done");
  }

}
