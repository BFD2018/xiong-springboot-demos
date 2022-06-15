package com.xjt.javase.test;

public class Cat {
    interface Action{
        void doSomething();
    }

    public Action onSound(){
        return new Action() {
            @Override
            public void doSomething() {

            }
        };
    }

    public Action onFeed(){
        return new Action() {
            @Override
            public void doSomething() {

            }
        };
    }

    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.onSound();
    }
}
