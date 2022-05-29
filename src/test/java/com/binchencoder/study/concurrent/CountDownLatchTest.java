package com.binchencoder.study.concurrent;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chenbin on 2018/3/31.
 */
public class CountDownLatchTest {

  private static final Logger logger = LoggerFactory.getLogger(CountDownLatchTest.class);

  @Test
  public void testAsyncExecFailedOfCountDown() {
    List<String> options = Lists.newArrayList("1", "2", "3");
    CountDownLatch latch = new CountDownLatch(options.size());

    options.stream().forEach(op -> {
      CompletableFuture.runAsync(() -> {
        try {
          switch (op) {
            case "1":
              Thread.sleep(1000L);
              System.out.println("Get 1");
              break;
            case "2":
              Thread.sleep(2000L);
              System.out.println("Get 2");
              break;
            case "3":
              Thread.sleep(3000L);
              System.out.println("Get 3");
              break;
            default:
              break;
          }
        } catch (Exception ex) {
        } finally {
          latch.countDown();
        }
      });
    });

    // 因为没有执行latch.await(), 所以异步执行失败

    System.out.println("Over");
  }

  @Test
  public void testAsyncThrowExceptionOfCountDown1() {
    List<String> options = Lists.newArrayList("1", "2", "3");
    long start = System.currentTimeMillis();

    CountDownLatch latch = new CountDownLatch(options.size());

    options.stream().forEach(op -> {
      CompletableFuture.runAsync(() -> {
        try {
          switch (op) {
            case "1":
              Thread.sleep(1000L);
              System.out.println("Get 1");
              break;
            case "2":
              Thread.sleep(2000L);
              this.throwIllegalException();
              System.out.println("Get 2");
              break;
            case "3":
              Thread.sleep(3000L);
              System.out.println("Get 3");
              break;
            default:
              break;
          }
        } catch (Exception ex) {
          logger.error("CompletableFuture runAsync error", ex);
        } finally {
          latch.countDown();
        }
      });
    });

    try {
      latch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    logger.info("Cost time: {}", System.currentTimeMillis() - start);
    System.out.println("Over");
  }

  @Test
  public void testAsyncExecSuccedOfCountDown() throws Exception {
    List<String> options = Lists.newArrayList("1", "2", "3");
    long start = System.currentTimeMillis();

    CountDownLatch latch = new CountDownLatch(options.size());
    CompletableFuture completableFuture = new CompletableFuture<>();
    options.stream().forEach(op -> {
      completableFuture.supplyAsync(() -> {
        try {
          switch (op) {
            case "1":
              Thread.sleep(1000L);
              System.out.println("Get 1");
              break;
            case "2":
              throwIllegalException();
              Thread.sleep(2000L);
              System.out.println("Get 2");
              break;
            case "3":
              Thread.sleep(3000L);
              System.out.println("Get 3");
              break;
            default:
              break;
          }
        } catch (Exception ex) {
          System.err.println("Occur exception");
          completableFuture.completeExceptionally(ex);
        } finally {
          latch.countDown();
        }

        completableFuture.complete(op);
        return null;
      });
    });

    try {
      completableFuture.get();
    } catch (ExecutionException ee) { // 为了抛出详细的异常, 在捕捉之后需要做如下的处理
      Throwable throwable = ee.getCause();
      throw (Exception) throwable;
    }

    /*completableFuture.get();
    boolean exceptionally = completableFuture.exceptionally(ex -> {
      System.err.println("exceptionally");
      throw new CompletionException("CompleteFuture exceptionally", (Throwable) ex);
    }).isCompletedExceptionally();

    System.err.println("CompletableFuture exceptionally is: " + exceptionally);
    if (exceptionally) {
      throw new RuntimeException("");
    }*/

    /*
    options.stream().forEach(op -> {
      CompletableFuture completableFuture = CompletableFuture.runAsync(() -> {
        try {
          switch (op) {
            case "1":
              Thread.sleep(1000L);
              System.out.println("Get 1");
              break;
            case "2":
              throwException();
              Thread.sleep(2000L);
              System.out.println("Get 2");
              break;
            case "3":
              Thread.sleep(3000L);
              System.out.println("Get 3");
              break;
            default:
              break;
          }
        } catch (Exception ex) {
          System.err.println("Occur exception");
          throw new RuntimeException();
        } finally {
          latch.countDown();
        }
      });

      boolean exceptionally = completableFuture.exceptionally(ex -> {
        System.err.println("exceptionally");
        throw new RuntimeException();
      }).isCompletedExceptionally();

      System.err.println(exceptionally);
      if (exceptionally) {
        throw new RuntimeException();
      }
    });*/

    try {
      latch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    logger.info("Cost time: {}", System.currentTimeMillis() - start);
    System.out.println("Over");
  }

  private void throwIllegalException() {
    throw new IllegalArgumentException("Occur illegal argument exception");
  }
}
