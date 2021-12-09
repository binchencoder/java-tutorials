package com.binchencoder.study.function;

import java.util.function.Function;
import org.junit.Test;

/**
 * @author: chenbin
 * @date: 2021/12/9 下午3:12
 */
public class FunctionTest {

  @Test
  public void test1() {
    System.out.println(this.compute(5, val -> val * val));
    System.out.println(this.compute(5, val -> val + val));
  }

  @Test
  public void composeTest() {
    System.out.println(this.compute(2, value -> value * 3, value -> value * value));
  }

  @Test
  public void andTenTest() {
    System.out.println(this.compute2(2, value -> value * 3, value -> value * value));
  }

  public int compute(int a, Function<Integer, Integer> function) {
    int result = function.apply(a);
    return result;
  }

  public int compute(int a, Function<Integer, Integer> function1,
      Function<Integer, Integer> function2) {
    return function1.compose(function2).apply(a);
  }

  public int compute2(int a, Function<Integer, Integer> function1,
      Function<Integer, Integer> function2) {
    return function1.andThen(function2).apply(a);
  }
}
