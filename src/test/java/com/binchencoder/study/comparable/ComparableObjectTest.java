package com.binchencoder.study.comparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

/**
 * @author: chenbin
 * @date: 2021/12/8 下午12:29
 */
public class ComparableObjectTest extends TestCase {

  @Test
  public void testCompareTo() {
    List<ComparableObject> objects = new ArrayList<>();
    objects.add(new ComparableObject(0.6F));
    objects.add(new ComparableObject(0.3F));
    objects.add(new ComparableObject(0.5F));
    objects.add(new ComparableObject(0.1F));
    objects.add(new ComparableObject(1F));

    System.out.println(objects);
    Collections.sort(objects);
    System.out.println(objects);

    System.out.println(
        objects.stream().sorted(Comparator.comparing(ComparableObject::getScore).reversed())
            .collect(Collectors.toList()));
  }

}