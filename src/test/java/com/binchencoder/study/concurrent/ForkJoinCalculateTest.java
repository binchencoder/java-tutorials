package com.binchencoder.study.concurrent;

import com.binchencoder.study.concurrent.future.ForkJoinCalculate;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;
import org.junit.jupiter.api.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Created by chenbin on 17-11-15.
 */
public class ForkJoinCalculateTest {

  private static final long END_VALUE = 10000000000L;

  public final static ForkJoinPool pool = new ForkJoinPool();

  @BeforeMethod
  public void setUp() throws Exception {

  }

  @AfterMethod
  public void tearDown() throws Exception {
  }

  @Test
  public void test1() {
    System.out.println("本机可用处理器数:" + Runtime.getRuntime().availableProcessors());
  }

  @Test
  public void testForkJoinTask() {
    Instant start = Instant.now();

    ForkJoinTask<Long> task = new ForkJoinCalculate(0, END_VALUE);

    Long sum = pool.invoke(task);
    System.out.println(sum);

    Instant end = Instant.now();
    System.out.println("testForkJoinTask 耗时:" + Duration.between(start, end).toMillis());
  }

  @Test
  public void testFor() {
    Instant start = Instant.now();
    long sum = 0L;

    for (long i = 0; i <= END_VALUE; i++) {
      sum += 1;
    }
    System.out.println(sum);

    Instant end = Instant.now();
    System.out.println("testFor 耗时:" + Duration.between(start, end).toMillis());
  }

  @Test
  public void testJava8ForkJoin() {
    Instant start = Instant.now();

    LongStream.rangeClosed(0, END_VALUE)
        .parallel()
        .reduce(0, Long::sum);

    Instant end = Instant.now();
    System.out.println("testJava8ForkJoin 耗时:" + Duration.between(start, end).toMillis());
  }
}