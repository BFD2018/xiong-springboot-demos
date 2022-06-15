package com.xjt.javase.algorithm.divide_conquer;

//分治算法的应用案例---汉诺塔
public class HanoiTowel {
    public static void main(String[] args) {
        hanoiTowerFunc(3, 'A', 'B', 'C');
    }

    /**
     * 汉诺塔游戏的实现
     * @param num 盘子个数
     * @param a A柱（起始柱）
     * @param b B柱（辅助柱）
     * @param c C柱（目标柱）
     */
    public static void hanoiTowerFunc(int num,char a,char b,char c){
        if(num == 1){
            //只有一个盘时，直接移动从 a -> c
            System.out.println("第1个盘移动从：\t" + a + " -> " + c);
        }else{
            //当盘子个数大于2时，将最底下盘子（记为xx）之上的盘子视为一个整体（记为yy），移动策略为：
            //1）将yy移动到 B柱
            //2）将xx移动到 C柱
            //3）将yy移动到 C柱

            hanoiTowerFunc(num-1,a,c,b);

            System.out.println("第"+ num + "个盘移动从：\t" + a + " -> " + c);

            hanoiTowerFunc(num-1,b,a,c);
        }
    }
}
