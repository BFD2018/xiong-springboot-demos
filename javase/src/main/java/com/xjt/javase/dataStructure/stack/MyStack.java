package com.xjt.javase.dataStructure.stack;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Stack;

public class MyStack {
    @Test
    public void test01(){
        Stack<Object> stack = new Stack<>();
        stack.push(1);
        stack.push(2);

        Object pop = stack.pop();
        System.out.println(pop);        //2

    }

    @Test
    public void test02(){
        //一般情况下不推荐使用Java的Vector及其子类Stack，推荐使用LinkedList
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        String s = linkedList.removeLast();     //3
        System.out.println(s);
        System.out.println(linkedList);     //[1,2]

    }
}
