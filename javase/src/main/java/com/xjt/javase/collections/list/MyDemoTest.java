package com.xjt.javase.collections.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyDemoTest {
    //Cloneable 标记性接口
    @Test
    public void testClone(){
        ArrayList<String> list = new ArrayList<String>();
        list.add("人生就是旅途");
        list.add("也许终点和起点会重合");
        list.add("但是一开始就站在起点等待终点");
        list.add("那么其中就没有美丽的沿途风景和令人难忘的过往");

        //调用方法进行克隆
        Object o = list.clone();
        System.out.println(o == list);      //false，克隆的对象和原列表list 指向不同
        System.out.println(o);      //[人生就是旅途, 也许终点和起点会重合, 但是一开始就站在起点等待终点, 那么其中就没有美丽的沿途风景和令人难忘的过往]
        System.out.println(list);     //[人生就是旅途, 也许终点和起点会重合, 但是一开始就站在起点等待终点, 那么其中就没有美丽的沿途风景和令人难忘的过往]
    }

    @Test
    public void testRandomAccess(){
        //创建ArrayList集合
        List<String> list = new ArrayList<>();
        //添加10W条数据
        for (int i = 0; i < 100000; i++) {
            list.add(i+"a");
        }
        System.out.println("----通过索引(随机访问:)----");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            //仅仅为了演示取出数据的时间,因此不对取出的数据进行打印
            list.get(i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("随机访问: "+(endTime-startTime));

        System.out.println("----通过迭代器(顺序访问:)----");
        startTime = System.currentTimeMillis();
        Iterator<String> it = list.iterator();
        while (it.hasNext()){
            //仅仅为了演示取出数据的时间,因此不对取出的数据进行打印
            it.next();
        }
        endTime = System.currentTimeMillis();
        System.out.println("顺序访问: "+(endTime-startTime));


        //LinkedList
        List<String> list2 = new LinkedList<>();
        //添加10W条数据
        for (int i = 0; i < 100000; i++) {
            list2.add(i+"a");
        }
        System.out.println("----LinkedList通过随机访问----");
        long start = System.currentTimeMillis();
        for (int i = 0; i < list2.size(); i++) {
            //仅仅为了演示取出数据的时间,因此不对取出的数据进行打印
            list2.get(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("随机访问: "+(end-start));
    }

    @Test
    public void testConstructor(){
        /*1、无参构造*/
        ArrayList<String> strings = new ArrayList<>();

        /*2、有参构造：设置初始容量*/
        ArrayList<String> strings2 = new ArrayList<>(5);

        /*3、有参构造：构造一个包含指定集合元素的列表*/
        ArrayList<String> arr = new ArrayList<>();
        arr.add("zhang");
        arr.add("wang");
        arr.add("xiong");
        ArrayList<String> strings3 = new ArrayList<>(arr);
    }

    @Test
    public void testAdd(){
        ArrayList<String> arr = new ArrayList<>();
        arr.add("zhang");
        arr.add("wang");
        arr.add("xiong");
        //向索引1位置插入元素
        arr.add(1,"zhao");
    }

    @Test
    public void testAddAll(){
        ArrayList<String> arr = new ArrayList<>();
        arr.add("zhang");
        arr.add("wang");
        arr.add("xiong");

        ArrayList<String> strings = new ArrayList<>();
        strings.addAll(arr);
    }

    @Test
    public void testAddAll2(){
        ArrayList<String> arr = new ArrayList<>();
        arr.add("zhang");
        arr.add("wang");
        arr.add("xiong");

        ArrayList<String> strings = new ArrayList<>();
        strings.add("china");
        strings.add("usa");
        strings.addAll(1,arr);
    }

    @Test
    public void testToString(){
        ArrayList<String> arr = new ArrayList<>();
        arr.add("zhang");
        arr.add("wang");
        arr.add("xiong");

        arr.toString();
    }

    /**
     * 案例一: 已知集合：List<String> list = new ArrayList<String>();
     * 里面有三个元素："hello"、"Java"、"PHP"，
     * * 使用迭代器遍历获取集合的每一个元素
     */
    @Test
    public void testIterator(){
        ArrayList<String> arr = new ArrayList<>();
        arr.add("hello");
        arr.add("Java");
        arr.add("PHP");
        Iterator<String> it = arr.iterator();
        while (it.hasNext()){
            String next = it.next();
            System.out.println(next);
        }
    }

    @Test
    public void testIterator2(){
        ArrayList<String> arr = new ArrayList<>();
        arr.add("hello");
        arr.add("Java");
        arr.add("PHP");
        Iterator<String> it = arr.iterator();
        while (it.hasNext()){
            String next = it.next();
            if(next == "PHP"){
                arr.remove(next);
            }
        }
    }
}
