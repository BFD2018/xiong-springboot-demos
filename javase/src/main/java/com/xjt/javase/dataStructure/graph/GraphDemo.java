package com.xjt.javase.dataStructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class GraphDemo {
    //存储顶点的集合
    private ArrayList<String> vertextList;
    //存储图对应的领结矩阵
    private int[][] edges;
    //边的数量
    private int numOfEdges;
    //定义数组 记录节点列表中的节点是否被访问
    private boolean[] isVisited;

    public static void main(String[] args) {
        int n = 5;
        String vertexs[] = {"A","B","C","D","E"};
        GraphDemo graph = new GraphDemo(5);
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }

        //添加边 A-B  A-C  B-C  B-D  B-E
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);

        graph.showGraph();
        System.out.println("深度优先遍历---->");
        graph.dfs();

        System.out.println("广度优先遍历===>");
        graph.bfs();

    }

    /**
     * 初始化
     * @param n 节点个数
     */
    public GraphDemo(int n) {
        this.vertextList = new ArrayList<>(n);
        this.edges = new int[n][n];
        this.numOfEdges = 0;        //这里写不写都可以 默认也是0
        this.isVisited = new boolean[n];
    }

    //插入节点
    public void insertVertex(String vertex){
        vertextList.add(vertex);
    }

    //添加边
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    //返回edges[v2][v1]的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }

    //返回节点i下标对应的数据
    public String getValueByIndex(int i){
        return vertextList.get(i);
    }

    //显示图对应的矩阵
    public void showGraph(){
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    //边的数量
    public int getNumOfEdges(){
        return numOfEdges;
    }
    //节点的数量
    public int getNumOfVertex(){
        return vertextList.size();
    }

    //得到 vertextList[i] 节点的 第一个邻接节点的下标
    public int getFirstNeighbor(int index){
        for (int i = 0; i < vertextList.size(); i++) {
            if(edges[index][i]>0){
                return i;
            }
        }
        return -1;
    }
    //根据前一个邻接节点的下标获取下一个邻接节点
    public int getNextNeighbor(int v1,int v2){
        for (int j = v2 + 1; j < vertextList.size(); j++) {
            if(edges[v1][j] > 0){
                return j;
            }
        }
        return -1;
    }

    //深度优先遍历算法
    //i 第一次就是 0
    private void dfs(boolean[] isVisited,int i){
        //首先输出该节点
        System.out.print(getValueByIndex(i) + "->");
        //该节点置为已访问
        isVisited[i] = true;
        //查找节点i的第一个领结节点w
        int w = getFirstNeighbor(i);
        while (w != -1){
            if(!isVisited[w]){
                dfs(isVisited,w);
            }
            //如果节点w已经访问过了
            w = getNextNeighbor(i,w);
        }
    }

    //对dfs重载，遍历vertexList的所有节点
    public void dfs(){
        isVisited = new boolean[vertextList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    //对一个结点进行广度优先遍历的方法
    private void bfs(boolean[] isVisited,int i){
        int u;  //表示队列的头结点对应的下标
        int w;  //邻接结点
        //队列  ：记录结点的访问顺序
        LinkedList<Integer> queue = new LinkedList<>();
        //输出结点信息
        System.out.print(getValueByIndex(i) + "=>");
        //标记该结点已访问
        isVisited[i] = true;
        //将结点加入队列
        queue.addLast(i);
        while (!queue.isEmpty()){
            //取出队列的头结点
            u = queue.removeFirst();
            //得到第一个邻接结点的下标w
            w = getFirstNeighbor(u);
            while (w != -1){
                //判断得到第一个邻接结点的下标w 是否已经访问过
                if(!isVisited[w]){
                    System.out.print(getValueByIndex(w) + "=>");
                    //标记该结点已访问
                    isVisited[w] = true;
                    //将结点加入队列
                    queue.addLast(w);
                }
                // u行中  找w后面的下一个邻结点
                w = getNextNeighbor(u,w);
            }
        }
    }

    public void bfs(){
        isVisited = new boolean[vertextList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }
}
