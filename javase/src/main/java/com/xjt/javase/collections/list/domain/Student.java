package com.xjt.javase.collections.list.domain;

public class Student implements Cloneable{
    private String name;
    private Integer age;

    private Skill skill;

    public Student() {
    }

    public Student(String name, Integer age, Skill skill) {
        this.name = name;
        this.age = age;
        this.skill = skill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", skill=" + skill +
                '}';
    }

    @Override
    public Student clone() throws CloneNotSupportedException {
        //调用超类Object中方法clone进行对象克隆,得到一个新的学生对象
        Student newStu = (Student) super.clone();
        //调用学生类其属性skill的clone方法,对属性进行克隆
        Skill s = this.skill.clone();
        //再将克隆的Skill设置给克隆出来的学生对象
        newStu.setSkill(s);
        //返回克隆出来的学生对象
        return newStu;
    }
}
