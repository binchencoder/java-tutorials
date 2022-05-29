package com.binchencoder.study.sample;

import com.google.common.base.CharMatcher;
import com.google.common.collect.Lists;
import java.util.List;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

/**
 * Created by chenbin on 18-3-30.
 */
public class Collections {

  /**
   * 取交集
   */
  @Test
  public void testRetainAll() {
    List<Long> addItems = Lists.newArrayList(1L, 3L, 4L, 5L);
    List<Long> removeItems = Lists.newArrayList(1L, 5L, 4L, 2L);
    boolean retain = addItems.retainAll(removeItems);

    System.out.println("retain: " + retain + "\n retain collection: " + addItems);
  }

  /**
   * RemoveAll specified collection, removed collection not empty.
   */
  @Test
  public void testRemoveAllOfNotEmpty() {
    List<Long> addItems = Lists.newArrayList(1L, 3L);
    List<Long> removeItems = Lists.newArrayList(1L, 2L);
    boolean removed = removeItems.removeAll(addItems);

    System.out.println("removed: " + removed + "\nremovedItems: " + removeItems);
  }

  /**
   * RemoveAll specified collection, but removed empty collection.
   */
  @Test
  public void testRemoveAllOfEmpty() {
    List<Long> addItems = Lists.newArrayList(1L, 3L);
    List<Long> removeItems = Lists.newArrayList(2L, 6L);
    boolean removed = removeItems.removeAll(addItems);

    System.out.println("removed: " + removed + "\nremovedItems: " + removeItems);
  }

  @Test
  public void testContainsAll() {
    List<Long> coll1 = Lists.newArrayList(1L, 3L, 4L, 5L);
    List<Long> coll2 = Lists.newArrayList(1L, 5L, 4L, 2L);

    System.out.println(coll1.containsAll(coll2));
  }

  @Test
  public void testRemoveIf() {
    List<Long> lst = Lists.newArrayList(0L, 1L, 122L);
    lst.removeIf(l -> l.equals(0L));

    List<Long> empty = java.util.Collections.emptyList();
    empty.removeIf(l -> l.equals(0L));

    System.out.println(lst);
    System.out.println(empty);
  }

  @Test
  public void testSubList() {
    List<Long> lst = Lists.newArrayList(1L, 3L, 4L, 5L, 9L, 0L);
    System.out.println(lst.subList(2, lst.size()));
  }

  @Test
  public void testArrayListWithCapacity() {
    List<Long> lst = Lists.newArrayListWithCapacity(1);
    lst.add(1L);
    lst.add(2L);
    lst.add(3L);
    lst.add(5L);
    lst.add(6L);

    System.out.println(lst);
  }

  @Test
  public void test() {
    System.out
        .print(NumberUtils.toLong(CharMatcher.inRange('0', '9').retainFrom("WorkFrame." + 111L)));
  }

  @Test
  public void test1() {
    List<Long> test = Lists.newArrayList(1L, 2L);
    System.out.println(System.identityHashCode(test));

    test = Lists.newArrayList(1L, 3L);
    System.out.println(System.identityHashCode(test));
  }
}
