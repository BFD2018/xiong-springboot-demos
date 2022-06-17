package com.xjt.javase.oop;

class Student extends Person {
    private String grade;
    private String school;

    private static Integer math = 85;

    public Student(String name, Integer age, String grade, String school) {
        super(name, age);
        this.grade = grade;
        this.school = school;
    }

    public void test() {
        super.run();
        System.out.println(this);       //Person(name=xiong, age=18)  打印this实际是在调用toString方法 因为Student没有所以去找父类的
        System.out.println("学生test");
        System.out.println(Student.math);
        System.out.println(this.math);
    }

    @Override
    public void run() {
        super.run();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    public void study() {
        this.test();
    }

//    @Override
//    public String toString() {
//        return "Student{" +
//                "name='" + super.getName() + '\'' +
//                "age='" + super.getAge() + '\'' +
//                "grade='" + grade + '\'' +
//                ", school='" + school + '\'' +
//                '}';
//    }
}

class Test01 {
    public static void main(String[] args) {
//        Student student = new Student("xiong", 18, "6年级", "希望小学");
//        student.test();
//        student.study();

        xiong:
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println("i=" + i + ",j=" + j);
                if (j == 5) {
                    break xiong;
                }
            }
        }
        System.out.println("跳出来了...");
    }
}
