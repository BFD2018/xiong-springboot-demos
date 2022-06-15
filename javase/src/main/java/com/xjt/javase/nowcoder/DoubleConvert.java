package com.xjt.javase.nowcoder;

import java.util.Arrays;

public class DoubleConvert {
    public static void main(String[] args) {
        Double d = 12.354;

        //方法1
        String[] split = (""+d).split("\\.");
        int i = Integer.parseInt(split[0]);
        System.out.println(i);

        //方法2
        int i1 = d.intValue();
        System.out.println(i1);

        //方法3
        int i2 = DoubleConvert.typeConversion(d);
        System.out.println(i2);
    }

    public static int typeConversion(double d){
        return (int)d;
    }
}
