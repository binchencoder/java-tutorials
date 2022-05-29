package com.binchencoder.study.hashcode;

import org.junit.jupiter.api.Test;

/**
 * Created by chenbin on 18-2-7.
 */
public class HashBuilderTest {

  @Test
  public void testHashCode() {
    Long start = System.currentTimeMillis();
    Person person = new Person();
    person.setName("Chen bin");
    person.setAge(27);
    person.setSex(Sex.MALE);

    System.out.println(person.hashCode());
    System.out.println("Cost time:" + (System.currentTimeMillis() - start));

    String value = "20181107145412179";
    System.out.println(value.hashCode());

    String value2 = "20181107151712179";
    System.out.println(value2.hashCode());

    String value1 = "2019110714541226694119";
    System.out.println(value1.hashCode());
  }
}
