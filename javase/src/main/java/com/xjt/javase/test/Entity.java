package com.xjt.javase.test;

public class Entity {
    private String value  = new String("default");

    public Entity(String origin) {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static void main(String[] args) {
        Entity[] arr = new Entity[5];
        arr[0] = new Entity("origin");

        Entity[] arr1 = arr.clone();
        arr[0].setValue("valueChanged");

        System.out.println(arr1[0].getValue());
    }

}
