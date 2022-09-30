package com.binchencoder.study.tests;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.testng.collections.Maps;

public class ForeachTest {

    @Test
    public void testListForeach() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);

        list.forEach(item -> {
            if (item.equals(4)) {
                return;
            }
            System.out.println(item);
        });
        System.out.println("end");
    }

    @Test
    public void testMapForeach() {
        Map<String, String> map = Maps.newHashMap();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");

        map.forEach((String key, String val) -> {
            if (key.equals("3")) {
                return;
            }
            System.out.println("key: " + key + ", value: " + val);
        });

        System.out.println("end");
    }

    @Test
    public void testFinal() {
        final List<String> lst = Lists.newArrayList();
        lst.add("A");

        List<String> final1 = lst;
        System.out.println(final1);
    }
}
