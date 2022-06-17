package com.xjt.javase.oop;

public class KeyFinal {
    public static void main(String[] args) {

    }
}

class Teacher{
    private final static String job;
    static {
        job = "教书";
    }
    private final static String aaa = "teach";

    private final Integer age;
    {
        age = 30;
    }
    private final Integer bbb = 10;

    public void talk(){
        final String ccc;
        ccc = "talk-student";       //只允许赋值一次

        //ccc = "ssss";       //报错
    }

    public void testChange(){
        final String[] arr = {"zhang","wang"};
        arr[1] = "xiong";

        //arr = new String[];     //报错

        final Person person = new Person();
        person.setAge(26);
        //person = null;      //报错
    }


}
