package com.xjt.javase.collections.list;

import com.xjt.javase.collections.list.domain.Dog;
import com.xjt.javase.collections.list.domain.Job;
import com.xjt.javase.collections.list.domain.Skill;
import com.xjt.javase.collections.list.domain.Student;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;

public class MyClone {
    @Test
    public void testDeepClone() throws CloneNotSupportedException {
        //用自定义对象演示 深浅拷贝
        Skill skill = new Skill("倒拔垂杨柳");
        Student s = new Student("鲁智深",31,skill);
        //调用clone方法进行克隆
        Student obj = s.clone();
        System.out.println(s == obj);       //比较地址
        System.out.println("原对象: "+s);
        System.out.println("克隆出来的对象: "+obj);
        System.out.println("----华丽的分割线----");
        //克隆之后,更改skill中的数据
        skill.setSkillName("拳打镇关西");
        //更改克隆后对象的数据
        obj.setName("鲁提辖");
        obj.setAge(36);
        System.out.println("原对象: "+s);
        System.out.println("克隆出来的对象: "+obj);
    }

    @Test
    public void testFastJsonClone(){

    }

    @Test
    public void testCommonsLangClone(){
        Dog d1 = new Dog("阿拉斯加", 3, new Job("拉雪橇"));
        Dog clone = (Dog) SerializationUtils.clone(d1);
        System.out.println(d1 == clone);
        System.out.println(d1);
        System.out.println(clone);


        d1.setName("二哈");
        d1.setJob(new Job("拆家"));
        System.out.println(d1);
        System.out.println(clone);
    }
}
