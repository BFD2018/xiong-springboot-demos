package com.xjt.javase.nowcoder;

import java.util.Scanner;

public class MainDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double height = scanner.nextDouble();
        double weight = scanner.nextDouble();

        double ret = weight / (height * height);
        System.out.println(ret);
        if(ret < 18.5){
            System.out.println("偏瘦");
        }else if(ret < 20.9){
            System.out.println("苗条");
        }else if(ret <= 24.9){
            System.out.println("适中");
        }else{
            System.out.println("偏胖");
        }
    }
}
