package com.xjt.javase.HashSet;

import cn.hutool.core.util.ArrayUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.nio.charset.IllegalCharsetNameException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Demo1 {
    @Test
    public void testAdd() {
        HashSet<String> set = new HashSet<>();
        set.add("xiong");
        set.add("zhang");
        set.add("wang");
        set.add("xiong");
    }

    @Test
    public void testBigDecimal() {
//        BigDecimal divide = new BigDecimal("3.14").divide(new BigDecimal(2));
//        System.out.println(divide);
//        System.out.println(divide.intValue());
//
//        double v = 3.14 / 2;
//        System.out.println(v);
//        System.out.println((int)v);

        int[] ints = {99, 45, 3, 33, 13, 21, 34, 11, 35, 41, 32, 50, 43, 51, 54, 52, 1, 35, 51, 54, 52, 35, 98, 53, 3, 32, 45, 45, 35, 36, 33, 45, 36, 15, 21};

        HashSet<Integer> objects = new HashSet<>();
        for (int i = 0; i < ints.length; i++) {
            objects.add(ints[i]);
        }

        System.out.println(objects.size());
        System.out.println(objects);
    }
}
