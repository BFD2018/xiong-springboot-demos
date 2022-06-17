package com.xjt.javase.stream;


import java.util.ArrayList;
import java.util.stream.Stream;

public class Test01 {
    public static void main(String[] args) {
        //第一支队伍 ArrayList<String>
        ArrayList<String> one = new ArrayList<String>();
        one.add("迪丽热巴");
        one.add("宋远桥");
        one.add("苏星河");
        one.add("石破天");
        one.add("石中玉");
        one.add("老子");
        one.add("庄子");
        one.add("洪七公");
        //第二支队伍 ArrayList<String>
        ArrayList<String> two = new ArrayList<String>();
        two.add("古力娜扎");
        two.add("张无忌");
        two.add("赵丽颖");
        two.add("张三丰");
        two.add("尼古拉斯赵四");
        two.add("张天爱");
        two.add("张二狗");

        Stream<String> stream1 = one.stream().filter(s -> s.length() == 3).limit(3);
        Stream<String> stream2 = two.stream().filter(s -> s.startsWith("张")).skip(2);
        //        Stream<String> concat = Stream.concat(stream1, stream2);
        //        Stream<Person> personStream = concat.map(s -> new Person(s));
        //        personStream.forEach(p -> System.out.println(p));
        //当然也可以写成链式
        Stream.concat(stream1, stream2).map(s -> new Person(s)).forEach(p -> System.out.println(p));
    }

    static class Person{
        private String name;

        public Person(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}


