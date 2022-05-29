package com.binchencoder.study.function;

import java.util.List;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;
import org.testng.collections.Lists;

/**
 * Created by chenbin on 17-11-9.
 */
public class PredicateTest {

  @Test
  public void test1() {
    List<String> languages = Lists.newArrayList("Java", "Scala", "C++", "Haskell", "Lisp");

    System.out.println("Languages which starts with J :");
    filter(languages, (str) -> ((String) str).startsWith("J"));

    System.out.println("Languages which ends with a ");
    filter(languages, (str) -> ((String) str).startsWith("a"));

    System.out.println("Print all languages :");
    filter(languages, (str) -> true);
  }

  @Test
  public void test2() {
    List<Integer> list = Lists.newArrayList(1, 2, 3);

    // 可改变对象
    list.stream().map((i) -> i * 3).forEach(System.out::println);
    System.out.println(list);

    // 不可改变原有对象
    list.forEach(i -> i = i * 3);
    list.forEach(System.out::println);
  }

  @Test
  public void test3() {
    List<Integer> list = Lists.newArrayList(1, 2, 3);

    Integer integer = list.stream()
        .map((i) -> i = i * 3)
        .reduce((sum, count) -> sum += count)
        .get();
    System.out.println(integer);
  }

  public static void filter(List<String> names, Predicate<String> condition) {
    for (String name : names) {
      if (condition.test(name)) {
        System.out.println(name + "");
      }
    }
  }
}
