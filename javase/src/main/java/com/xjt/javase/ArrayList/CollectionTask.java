package com.xjt.javase.ArrayList;

import cn.hutool.core.util.RandomUtil;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionTask implements Runnable {
    //    private static ArrayList<String> list = new ArrayList<String>();
    private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();

    static {
        list.add("zhang");
        list.add("wang");
        list.add("zhao");
    }

    @Override
    public void run() {
        for (String s : list) {     //增强for底层使用的是迭代器
            //读
            System.out.println(s);
            //写
            list.add("xiong" + RandomUtil.randomInt(1, 100));
        }
    }
}
