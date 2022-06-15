package com.xjt.javase.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * 已知一个 HashMap<Integer，User>集合， User 有 name（String）和 age（int）属性。请写一个方法实现对HashMap 的排序功能，该方法接收 HashMap<Integer，User>为形参，
 * 返回类型为 HashMap<Integer，User>，要求对 HashMap 中的 User 的 age 倒序进行排序。排序时 key=value 键值对不得拆散。
 *
 * 注意：要做出这道题必须对集合的体系结构非常的熟悉。HashMap本身就是不可排序的，但是该题偏偏让HashMap排序，那我们就得想在API中有没有这样的 Map 结构是有序的，
 * 我们不难发现其中LinkedHashMap就具有这样的结构，是链表结构有序的，更可喜的是他是  HashMap的子类，我们返回LinkedHashMap<Integer，User>即可，还符合面向接口编程的思想。
 *
 * 但凡是对集合的操作，我们应该保持一个原则就是能用JDK中的API就用JDK中的 API，比如排序算法我们不应该去用冒泡或者选择，而是首先想到用 Collections 集合工具类。
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
class User {
    private String name;
    private int age;
}

public class HashmapTest {
    public static void main(String[] args) {
        HashMap<Integer, User> users = new HashMap<>();

        users.put(1,new User("张三",25) );
        users.put(3,new User("李四",22));
        users.put(2, new User("王五", 28));
        System.out.println(users);
        HashMap<Integer, User> sortHashMap = sortHashMap(users);
        System.out.println(sortHashMap);
    }

    public static HashMap<Integer, User> sortHashMap(HashMap<Integer, User> users){
        Set<Map.Entry<Integer, User>> entries = users.entrySet();
        ArrayList<Map.Entry<Integer, User>> list = new ArrayList<>(entries);
        Collections.sort(list, new Comparator<Map.Entry<Integer, User>>() {
            @Override
            public int compare(Map.Entry<Integer, User> o1, Map.Entry<Integer, User> o2) {
                return o2.getValue().getAge() - o1.getValue().getAge();
            }
        });

        LinkedHashMap<Integer, User> linkedHashMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, User> userEntry : list) {
            linkedHashMap.put(userEntry.getKey(),userEntry.getValue());
        }

        return linkedHashMap;
    }
}


