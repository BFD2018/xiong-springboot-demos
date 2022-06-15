package com.xjt.javase.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
class TUser {
    private String name;
    private int age;
}

public class TestCollections {
    public static void main(String[] args) {
//        testReverse​();

        testSync();
    }

    private ArrayList<Object> list1;

    @Test
    public void testAddAll(){
        ArrayList<String> list = new ArrayList<>();
        list.add("xiong");

        Collections.addAll(list,"zhang","wang","song");
        System.out.println(list);
    }

    /**
     * static <T> void    copy​(List<? super T> dest, List<? extends T> src)    将一个列表中的所有元素复制到另一个列表中。
     */
    @Test
    public void testCopy(){
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        Collections.addAll(list1,"zhang","wang","song");
        Collections.copy(list2,list1);
        System.out.println(list2);
    }

    /**
     * static <T> T     max​(Collection<? extends T> coll, Comparator<? super T> comp)      根据指定比较器引发的顺序返回给定集合的最大元素。
     * static <T> T     min​(Collection<? extends T> coll, Comparator<? super T> comp)      根据指定比较器引发的顺序返回给定集合的最小元素。
     */
    @Test
    public void testMax(){
        ArrayList<String> list1 = new ArrayList<>();
        Collections.addAll(list1,"zhang","wang","song");
        String max = Collections.max(list1, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);        //按字典顺序比较两个字符串。比较基于字符串中每个字符的 Unicode 值
            }
        });
        System.out.println(max);
    }

    /**
     * static <T> void fill​(List<? super T> list, T obj) 用指定的元素替换指定列表的所有元素。
     */
    @Test
    public void testFill(){
        ArrayList<Object> list1 = new ArrayList<>();
        Collections.addAll(list1,"zhang","wang","song");

        Collections.fill(list1,new TUser());
        System.out.println(list1);  //[TUser(name=null, age=0), TUser(name=null, age=0), TUser(name=null, age=0)]

    }


    /**
     * static <T> boolean   replaceAll​(List<T> list, T oldVal, T newVal)   用列表替换列表中所有出现的指定值。
     */
    @Test
    public void testReplaceAll(){
        ArrayList<Object> list1 = new ArrayList<>();
        Collections.addAll(list1,"xxoo","wang","xxoo","zhang","song","sun");

        Collections.replaceAll(list1,"xxoo","***");
        System.out.println(list1);
    }

    /**
     * static void  reverse​(List<?> list)  反转指定列表中元素的顺序。
     */
    public static void testReverse​(){
        List<String> strings = Arrays.asList("zhang", "wang", "song");

        Collections.reverse(strings);
        System.out.println(strings);
    }

    /**
     * static void      shuffle​(List<?> list)      使用默认的随机源随机置换指定的列表。
     */

    /**
     * static <T>   void sort​(List<T> list, Comparator<? super T> c)   根据指定比较器引发的顺序对指定列表进行排序。
     */
    public static void testSort​(){
        HashMap<Integer, TUser> users = new HashMap<>();

        users.put(1,new TUser("张三",25) );
        users.put(3,new TUser("李四",22));
        users.put(2, new TUser("王五", 28));
        System.out.println(users);
        HashMap<Integer, TUser> sortHashMap = sortHashMap(users);
        System.out.println(sortHashMap);
    }

    public static HashMap<Integer, TUser> sortHashMap(HashMap<Integer, TUser> users){
        Set<Map.Entry<Integer, TUser>> entries = users.entrySet();
        ArrayList<Map.Entry<Integer, TUser>> list = new ArrayList<>(entries);
        Collections.sort(list, new Comparator<Map.Entry<Integer, TUser>>() {
            @Override
            public int compare(Map.Entry<Integer, TUser> o1, Map.Entry<Integer, TUser> o2) {
                return o2.getValue().getAge() - o1.getValue().getAge();
            }
        });

        LinkedHashMap<Integer, TUser> linkedHashMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, TUser> userEntry : list) {
            linkedHashMap.put(userEntry.getKey(),userEntry.getValue());
        }

        return linkedHashMap;
    }

    /**
     * static void  swap​(List<?> list, int i, int j)   交换指定列表中指定位置的元素。
     */

    /** synchronizedXXX函数接收特定的Collection，将转换成线程安全的包装类型：SynchronizedXXX类型。
     * static <T>   Collection<T> synchronizedCollection​(Collection<T> c)  返回由指定集合支持的同步（线程安全）集合。
     * static <T>   List<T> synchronizedList​(List<T> list)     返回由指定列表支持的同步（线程安全）列表。
     * static <K,​V>    Map<K,​V> synchronizedMap​(Map<K,​V> m)     返回由指定映射支持的同步（线程安全）映射。
     * static <T>   Set<T> synchronizedSet​(Set<T> s)   返回由指定集支持的同步（线程安全）集。
     * static <K,​V>   SortedMap<K,​V> synchronizedSortedMap​(SortedMap<K,​V> m)    返回由指定的有序映射支持的同步（线程安全）有序映射。
     * static <T>   SortedSet<T> synchronizedSortedSet​(SortedSet<T> s)     返回由指定有序集支持的同步（线程安全）有序集。
     */
    public static List<String> testSynchronizedList​(){
        List<String> strings = Arrays.asList("zhang", "wang", "song","li","zhou","sun","xiong","he","huang","zhu");
        List<String> strings1 = Collections.synchronizedList(strings);
        return strings1;
    }

    public static void testSync(){
        List<String> strings = testSynchronizedList​();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < strings.size(); i++) {
                strings.set(i,strings.get(i) + "**");
            }
            System.out.println("t1=========>");
            System.out.println(strings);
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < strings.size(); i++) {
                strings.set(i,strings.get(i) + "##");
            }
            System.out.println("t2=========>");
            System.out.println(strings);
        }, "t2");
        t1.start();
        t2.start();

        System.out.println(strings);
    }


}
