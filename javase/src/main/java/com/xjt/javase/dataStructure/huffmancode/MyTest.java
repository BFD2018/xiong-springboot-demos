package com.xjt.javase.dataStructure.huffmancode;

public class MyTest {
    public static void main(String[] args) {
        System.out.println(byteToBitString(false,(byte)-1));

        System.out.println(Integer.toBinaryString(1));      //1
        System.out.println(Integer.toBinaryString(2));      //10
        //（-2补码） 11111111111111111111111111111110
        // ->(反码) 11111111111111111111111111111101
        // ->(原码) 10000000000000000000000000000010
        System.out.println(Integer.toBinaryString(-2));     //  111111111111111111111111 11111110
    }

    private static String byteToBitString(boolean flag, byte b) {
        //使用变量保存 b
        int temp = b; //将 b 转成 int
        //如果是正数我们还存在补高位
        if(flag) {
            temp |= 256; 	//256 | 1 按位与 ：1 0000 0000  | 0000 0001 => 1 0000 0001
        }
        String str = Integer.toBinaryString(temp); //返回的是temp对应的二进制的补码
        if(flag) {
            return str.substring(str.length() - 8);		//为什么要截取后八位呢？ 因为前面 huffmanCodes 我们将 "101010001011..." 按照8位截取 构建成的一个赫夫曼编码表
        } else {
            return str;
        }
    }
}
