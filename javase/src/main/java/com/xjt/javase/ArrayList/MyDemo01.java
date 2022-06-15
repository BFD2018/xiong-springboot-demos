package com.xjt.javase.ArrayList;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyDemo01 implements Cloneable {
    @Test
    public void testClear() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("xiong");
        strings.add("wang");
        strings.add("zhang");

        System.out.println(strings);
        strings.clear();


        System.out.println(strings);
    }

    /*
     * 比较ArrayList  LinedList 添加或删除元素的效率？
     * 结论：ArrayList添加或删除元素的效率 不一定比LinedList 慢
     * */
    @Test
    public void testArrayListLinedList() {
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 5000000; i++) {
            arr.add(i);
        }
        long start = System.currentTimeMillis();
        Integer remove = arr.remove(1000000);
        System.out.println(remove);
        long end = System.currentTimeMillis();
        System.out.println("--->" + (end - start));

        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 5000000; i++) {
            list.add(i);
        }
        long start2 = System.currentTimeMillis();
        Integer remove2 = list.remove(1000000);
        System.out.println(remove2);
        long end2 = System.currentTimeMillis();
        System.out.println("--->" + (end2 - start2));

    }

    @Test
    public void testConcurrentTask() {
        CollectionTask ct = new CollectionTask();

        for (int i = 0; i < 20; i++) {
            new Thread(ct).start();
        }
    }
}

