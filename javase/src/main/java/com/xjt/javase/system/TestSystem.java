package com.xjt.javase.system;

import org.junit.Test;

public class TestSystem {
    @Test
    public void testSystem(){
        //返回当前系统环境的不可修改的字符串映射视图
        System.out.println(System.getenv());
        //以毫秒为单位返回当前时间
        System.out.println(System.currentTimeMillis());
        //返回正在运行的Java虚拟机的高分辨率时间源的当前值，以纳秒为单位
        System.out.println(System.nanoTime());

        System.out.println(System.getProperties());
    }
}
