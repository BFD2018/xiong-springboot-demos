package com.xjt.javase.bytes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MyTest {
    public static void main(String[] args) {
//        int i = Integer.parseInt("10101000", 2);
//        System.out.println(i);
//
//        System.out.println((byte) Integer.parseInt("10101000", 2));

//        byte b = 77;
//        System.out.println((int)b & 0xff);


        //test01();

        test02();

    }

    public static void test01(){
        Student student = new Student(111,"zhangsan");

        //序列化多个对象；
        List<Student> ss = new ArrayList<>();
        ss.add(new Student(222,"lisi"));
        ss.add(new Student(333,"wangwu"));
        ss.add(new Student(444,"zhaoliu"));

        Student sss = new Student(555,"agrfwrg");

        ObjectOutputStream oos = null;
        try {
            //序列化
            oos = new ObjectOutputStream(new FileOutputStream("myfile333"));
            //序列化对象

            oos.writeObject(student);
            oos.writeObject(ss);


            //oos.writeObject(ss);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void test02(){
        ObjectInputStream oos = null;

        try {
            oos = new ObjectInputStream(new FileInputStream("myfile333"));
            //开始反序列化
            //Object obj = oos.readObject();
            //System.out.println(oos.readObject() instanceof Student);
            //List<Student> obj = (List<Student>) oos.readObject();

            Object obj = oos.readObject();
            System.out.println(obj);
            Object obj02 = oos.readObject();
            System.out.println(obj02);


            /*for(Student ss : obj) {
                System.out.println(ss);
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Student implements Serializable{
    private Integer id;
    private String name;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}


