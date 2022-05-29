package com.binchencoder.study.map;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * Created by chenbin on 20-8-14.
 */
public class HasMapTest {

  @Test
  public void testPut() {
    Map<String, String> map = new HashMap<>(15);
    map.put("1", "1");
  }
}
