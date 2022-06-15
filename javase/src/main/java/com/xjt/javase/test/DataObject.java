package com.xjt.javase.test;

import java.io.Serializable;

public class DataObject implements Serializable {
    private static int i = 0;
    private String word = "";

    public static void setI(int i) {
        DataObject.i = i;
    }

    public static int getI() {
        return i;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
