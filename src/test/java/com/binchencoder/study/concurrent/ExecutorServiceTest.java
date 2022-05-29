package com.binchencoder.study.concurrent;

import static java.lang.Thread.sleep;

import com.binchencoder.study.concurrent.utils.ThreadPool;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chenbin on 2017/6/13.
 */
public class ExecutorServiceTest {

  private static final Logger logger = LoggerFactory.getLogger(ExecutorServiceTest.class);

  ExecutorService executorService = Executors.newFixedThreadPool(10);

  @Test
  public void executeRunnable() {
    executorService.execute(new Runnable() {
      @Override
      public void run() {
        System.out.println("Asynchronous task");
      }
    });
    executorService.shutdown();
  }

  @Test
  public void submitRunnable() throws ExecutionException, InterruptedException {
    String printStr = "Asynchronous task";

    Future future = executorService.submit(new Runnable() {
      @Override
      public void run() {
        System.out.println(printStr);
        try {
          sleep(5000l);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    System.out.println(future.get()); // 阻塞
  }

  @Test
  public void submitCallable() {
    ExecutorService executor = ThreadPool.getThreadPoolExecutor();

    Callable<Object> callable = new Callable<Object>() {
      @Override
      public Object call() throws Exception {
        sleep(2000l);
        System.err.println("Task 1");

        return "Task 1";
      }
    };
    executor.submit(callable);

    System.out.println("Submit callable");
  }

  @Test
  public void invokeAllCallable() throws InterruptedException, ExecutionException {
    ExecutorService executor = ThreadPool.getThreadPoolExecutor();

    List<Callable<Object>> callables = new ArrayList<>();
    callables.add(new Callable<Object>() {
      @Override
      public Object call() throws Exception {
        System.err.println("Task 1");
        sleep(1000l);
        return "Task 1";
      }
    });
    callables.add(new Callable<Object>() {
      @Override
      public Object call() throws Exception {
        System.err.println("Task 2");
        sleep(2000l);
        return "Task 2";
      }
    });
    callables.add(new Callable<Object>() {
      @Override
      public Object call() throws Exception {
        System.err.println("Task 3");
        sleep(1000l);
        return "Task 3";
      }
    });
    callables.add(new Callable<Object>() {
      @Override
      public Object call() throws Exception {
        System.err.println("Task 4");
        sleep(1000l);
        return "Task 4";
      }
    });

    long startTime = System.currentTimeMillis();
    List<Future<Object>> futures = executor.invokeAll(callables);
    for (Future<Object> result : futures) {
      System.out.println(result.get());
    }
    long endTime = System.currentTimeMillis();

    logger.info("Cost time:{}", (startTime - endTime));
  }

  @Test
  public void invokeAny() throws ExecutionException, InterruptedException {
    Set<Callable<String>> callables = new HashSet<Callable<String>>();

    callables.add(new Callable<String>() {
      @Override
      public String call() throws Exception {
        System.out.println("return task1");
        return "Task1";
      }
    });
    callables.add(new Callable<String>() {
      @Override
      public String call() throws Exception {
        System.out.println("return task2");
        return "Task2";
      }
    });
    callables.add(new Callable<String>() {
      @Override
      public String call() throws Exception {
        System.out.println("return task3");
        return "Task3";
      }
    });

    String result = executorService.invokeAny(callables);
    System.out.println("result=" + result);
  }
}
