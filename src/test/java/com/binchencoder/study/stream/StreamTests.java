package com.binchencoder.study.stream;

import com.google.gson.Gson;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.collections.Lists;

/**
 * Created by chenbin on 18-5-3.
 */
public class StreamTests {

  private static List<Stream> lst = Lists.newArrayList();
  private static List<CompanyAndDept> companyAndDepts = Lists.newArrayList();

  @BeforeClass()
  public static void init() {
    for (int i = 0; i < 100; i++) {
      lst.add(new Stream(i, "stream" + i));
    }

    lst.add(null);

    for (int i = 0; i < 100; i++) {
      companyAndDepts.add(CompanyAndDept.builder()
          .cid(RandomUtils.nextLong(1, 10))
          .deptId(RandomUtils.nextLong(1, 20))
          .build());
    }
  }

  @Test
  public void compareParallelStreamAndStream() {
    long start = System.currentTimeMillis();
    List<Long> ids = lst.parallelStream()
        .filter(stream -> null != stream && stream.getId() % 2 == 1)
        .map(Stream::getId)
        .collect(Collectors.toList());
    System.out.println("parallelStream cost time:" + (System.currentTimeMillis() - start));

    long start1 = System.currentTimeMillis();
    List<Long> ids1 = lst.stream()
        .filter(stream -> null != stream && stream.getId() % 2 == 1)
        .map(Stream::getId).collect(
            Collectors.toList());
    System.out.println("stream cost time:" + (System.currentTimeMillis() - start1));
  }

  @Test
  public void groupingBy() {
    Collections.sort(companyAndDepts);
    System.out.println(new Gson().toJson(companyAndDepts));

    Map<Long, Set<Long>> cidAndDeptIds = companyAndDepts.stream()
        .sorted()
        .collect(Collectors.groupingBy(CompanyAndDept::getCid,
            Collectors.mapping(CompanyAndDept::getDeptId, Collectors.toSet())));
    System.out.println(new Gson().toJson(cidAndDeptIds));
  }
}
