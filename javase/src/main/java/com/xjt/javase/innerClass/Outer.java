package com.xjt.javase.innerClass;

class Outer {
    private int out_a = 1;
    private static int STATIC_b = 2;

    public void testFunctionClass() {
        int inner_c = 3;
        //局部内部类（定义在实例方法中的局部类可以访问外部类的所有变量和方法）
        class Inner {
            private void fun() {
                System.out.println(out_a);
                System.out.println(STATIC_b);
                System.out.println(inner_c);
            }

        }
        Inner inner = new Inner();
        inner.fun();
    }


    public static void testStaticFunctionClass() {
        int d = 3;
        //局部内部类（定义在静态方法中的局部类只能访问外部类的静态变量和方法）
        class Inner {
            private void fun() {
                // System.out.println(out_a); 编译错误，定义在静态方法中的局部 类不可以访问外部类的实例变量
                System.out.println(STATIC_b);
                System.out.println(d);
            }
        }
        Inner inner = new Inner();
        inner.fun();
    }
}

class MyTest{
    public static void main(String[] args) {
//        Outer outer = new Outer();
//        outer.testFunctionClass();
//
//        Outer.testStaticFunctionClass();

        String a = new String("ab"); // a 为一个引用
        String b = new String("ab"); // b为另一个引用,对象的内容一样
        String aa = "ab"; // 放在常量池中
        String bb = "ab"; // 从常量池中查找
        if (aa == bb)       // true
            System.out.println("aa==bb");
        if (a == b)         // false，非同一对象
            System.out.println("a==b");
        if (a.equals(b))        // true
            System.out.println("aEQb");
        if (42 == 42.0) {   // true  基本数据类型比较的值
            System.out.println("true");
        }
    }
}
