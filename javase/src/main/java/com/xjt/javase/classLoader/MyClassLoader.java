package com.xjt.javase.classLoader;

public class MyClassLoader {
    public static void main(String[] args) {
        //扩展类加载器Main
        ClassLoader classLoader = MyClassLoader.class.getClassLoader();
        System.out.println(classLoader);    //jdk.internal.loader.ClassLoaders$AppClassLoader@3fee733d
        //表示当前线程的类加载器——应用程序类加载器
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader); //jdk.internal.loader.ClassLoaders$AppClassLoader@3fee733d
        //—启动类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);  //jdk.internal.loader.ClassLoaders$AppClassLoader@3fee733d

    }
}
