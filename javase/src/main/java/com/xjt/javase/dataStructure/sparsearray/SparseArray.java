package com.xjt.javase.dataStructure.sparsearray;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;

public class SparseArray {
    public static File file =  new File("./map.data");

    public static void main(String[] args) throws IOException {
        IntArrayConvertToSparseArr(file);

        convertSparseIntArray(file);
    }

    public static void IntArrayConvertToSparseArr(File file) throws IOException {
        // 创建一个原始的二维数组 11 * 11
        // 0：表示没有棋子，1表示黑子，2表示篮子
        int[][] chessArr = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        chessArr[3][5] = 2;

        // 输出原始的二维数组
        System.out.println("原始的二维数组--------------->");
        for (int[] row : chessArr) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        // 将二维数组 转 稀疏数组
        // 1.先遍历二维数组，得到非零数据的个数
        int sum = 0;
        for (int[] row : chessArr) {
            for (int data : row) {
                if(data != 0){
                    sum++;
                }
            }
        }

        // 2.创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        // 3.给稀疏数组赋基本值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        // 3.遍历二维数组，将非0的值存放在sparseArr中
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if(chessArr[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr[i][j];
                }
            }
        }

        // 4.打印稀疏数组的形式
        System.out.println("打印稀疏数组========================>");
        for (int i = 0 ; i< sparseArr.length;i++) {
            System.out.printf("%d\t%d\t%d\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }
        System.out.println();

        //5、将稀疏数组保存到文件map.data 然后读取文件还原为原来数组
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file, Charset.defaultCharset(),false);

        String jsonString = JSON.toJSONString(sparseArr);

        System.out.println(jsonString);
        fw.write(JSON.toJSONString(jsonString));

        fw.flush();
        fw.close();
    }

    //读取文件（将稀疏数组转为正常数组）
    public static void convertSparseIntArray(File file) throws IOException {
        //读取文件然后将稀疏数组恢复成原始数组
        FileReader fr = new FileReader(file);
        int len = 0;
        char[] temp = new char[1024];
        StringBuilder sb = new StringBuilder();
        while ((len = fr.read(temp)) != -1 ){
            sb.append(new String(temp,0,len));
        }
        fr.close();

        String s = sb.toString().substring(1,sb.length()-1);
        System.out.println("------>从文件中读取到的字符:"+s);
        int[][] sparseArr = JSON.parseObject(s,int[][].class);

        // 将稀疏数组恢复成原始数组
        // 1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int row = sparseArr[0][0];
        int col = sparseArr[0][1];
        int[][] newArr = new int[row][col];

        // 2.读取稀疏数组的后几行数据（从第二行开始），并复制给原始的二维数组即可
        for (int i = 1; i < sparseArr.length; i++) {
            newArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        // 3.输出恢复后的二维数组
        System.out.println("恢复后的二维数组========================>");
        for (int[] newrow : newArr) {
            for (int data : newrow) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }

    @Test
    public void test01(){
        String s = "hello world!";
        System.out.println(s.substring(1, s.length() - 1));
    }
}
