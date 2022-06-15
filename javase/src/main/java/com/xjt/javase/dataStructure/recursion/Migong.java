package com.xjt.javase.dataStructure.recursion;

public class Migong {
    public static void main(String[] args) {
        // 先创建一个二维数组，模拟迷宫
        //地图 8 x 7
        int[][] map = new int[8][7];
        // 使用1 表示墙
        // 第1行 第8行 全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        // 第1列 第7列 全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        // 输出地图
        System.out.println("地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf(map[i][j] + " ");
            }
            System.out.println();
        }

        //使用递归回溯给小球找路
        setWay(map,1,1);

        //输出新的地图, 小球走过，并标识过的递归
        System.out.println("小球走过，并标识过的 地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 使用递归回溯来给小球找路
     * 如果小球能到达map[6][5] 说明找到通路了
     * 约定：当map[i][j] 为0 时表示该点没有走过 当为1 时表示墙 ， 2 表示通路可以走 ； 3 表示该点已经走过，但是走不通
     * 在走迷宫时 需要确定一个策略（方法） 下->右->上->左 , 如果该点走不通，再回溯
     * @param map   地图
     * @param i 表示从地图的哪个位置开始出发 (1,1)
     * @param j
     * @return 如果找到通路，就返回true, 否则返回false
     */
    public static boolean setWay(int[][] map,int i,int j){
        if(map[6][5] == 2){
            return true;
        }else{
            if(map[i][j] == 0){ //如果当前这个点还没有走过
                //首先 假定该点是可以走通.
                map[i][j] = 2;
                //按照策略 下->右->上->左  走
                if(setWay(map,i+1,j)){
                    return true;
                }else if(setWay(map,i,j+1)){
                    return true;
                }else if(setWay(map,i-1,j)){
                    return true;
                }else if(setWay(map,i,j-1)){
                    return true;
                }else{
                    //说明该点是走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            }else{  // map[i][j] 可能是 1， 2， 3 都返回false
                return false;
            }
        }
    }
}
