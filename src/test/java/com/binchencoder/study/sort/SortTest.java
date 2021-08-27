package com.binchencoder.study.sort;

import java.util.Arrays;
import org.junit.Test;

public class SortTest {

  @Test
  public void sort() {
    int[] arr = new int[]{1, 100, 5, 6, 20, 12, 34};

    BuketSort sort = new BuketSort();
    int[] sortResult = sort.sort(arr);
    System.out.println(Arrays.toString(sortResult));
  }

}
