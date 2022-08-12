package com.binchencoder.study.iterator;

import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.testng.collections.Lists;

public class IteratorTest {

    @Test
    public void remove() {
        List<String> lst = Lists.newArrayList("A", "B", "C", "D");
        Iterator<String> it = lst.iterator();
        while (it.hasNext()) {
            String v = it.next();
            if (v.equals("B")) {
                it.remove();
            }
        }
        System.out.println(lst);
    }

}
