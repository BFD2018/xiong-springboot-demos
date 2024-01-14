package com.xjt.innerUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.junit.Test;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class SpringbootInnerUtils {
    @Test
    public void testAssert() {
        String message = "111";
        Assert.hasText(message, "输入信息错误!");      //message判断为空时 会报错
    }

    @Test
    public void testCollectionUtils() {
        // 判断 List/Set 是否为空
        //boolean isEmpty (Collection < ? > collection)
        List<Object> arrayList = new ArrayList<Object>();
        arrayList.add("111");
        arrayList.add(1243);
        arrayList.add(new StringBuilder().append("guangdong").append("shenzhen").toString());
        Person p1 = new Person().setName("xiong").setAge(18);
        arrayList.add(p1);
        System.out.println(arrayList);
        System.out.println(CollectionUtils.isEmpty(arrayList));

        // 判断 Map 是否为空
        //boolean isEmpty (Map < ?,?>map)

        // 判断 List/Set 中是否包含某个对象
        //boolean containsInstance (Collection < ? > collection, Object element)
        System.out.println(CollectionUtils.containsInstance(arrayList, p1));

        // 以迭代器的方式，判断 List/Set 中是否包含某个对象
        //boolean contains (Iterator < ? > iterator, Object element)

        // 判断 List/Set 是否包含某些对象中的任意一个
        //boolean containsAny (Collection < ? > source, Collection < ?>candidates)

        // 判断 List/Set 中的每个元素是否唯一。即 List/Set 中不存在重复元素
        //boolean hasUniqueObject (Collection < ? > collection)
        System.out.println(CollectionUtils.hasUniqueObject(arrayList));
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
class Person {
    private String name;
    private Integer age;
}
