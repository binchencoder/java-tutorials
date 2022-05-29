package com.binchencoder.study.googleguava;

import com.google.common.collect.Lists;
import com.google.common.math.IntMath;
import com.google.common.util.concurrent.RateLimiter;
import java.math.RoundingMode;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chenbin on 18-5-3.
 */
public class GuavaTest {

  private static final Logger logger = LoggerFactory.getLogger(GuavaTest.class);

  public static List<String> lst = Lists.newArrayList();

  static RateLimiter rateLimiter = RateLimiter.create(2000);

  @BeforeClass
  public static void init() {
    for (int i = 0; i < 502; i++) {
      lst.add("guava." + i);
    }

    lst.add(null);
  }

  @Test
  public void partitionTest() {
    int partitionSize = IntMath.divide(lst.size(), lst.size() / 10, RoundingMode.UP);
    List<List<String>> partitions = Lists.partition(lst, partitionSize);

    logger.debug("partitionSize: {}, partitions:{}", partitionSize, partitions);
  }

  @Test
  public void rateLimiter() {
    rateLimiter.tryAcquire();
  }
}
