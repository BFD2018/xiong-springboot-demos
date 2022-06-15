package com.xjt.javase.nowcoder;

import java.util.Scanner;

public class SimpleCal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        scanner.close();

        if(a < b){
            int temp = a;
            a = b;
            b = temp;
        }
        int sum = a+b;
        int sub = a-b;
        int mul = a+b;
        int div = a/b;
        int mod = a%b;

        System.out.printf("%d %d %d %d %d",sum,sub,mul,div,mod);


    }
}
