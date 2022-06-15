package com.xjt.javase.dataStructure.huffmantree;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;

public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int arr[] = {2,3,7,9,18,25};
        Node root = createHuffManTree(arr);

        testPreOrder(root);
    }

    /**
     * 测试树的前序遍历方法
     * @param root
     */
    public static void testPreOrder(Node root){
        if(root != null){
            root.preOrder();
        }else{
            System.out.println("是空树，不能遍历~~");
        }
    }

    /**
     * 将数组arr 转为哈夫曼树
     * @param arr
     * @return
     */
    public static Node createHuffManTree(int[] arr){
        // 第一步 为了操作方便 将arr数组转为list集合
        // 1. 遍历 arr 数组
        // 2. 将arr的每个元素构成成一个Node
        // 3. 将Node 放入到ArrayList中
        ArrayList<Node> nodes = new ArrayList<>();
        for (int val:arr){
            nodes.add(new Node(val));
        }

        while(nodes.size() > 1){
            //1、对ArrayList进行排序
            Collections.sort(nodes);
            System.out.println("列表nodes =" + nodes);

            //2、取出ArrayList中节点权值最小的两颗树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            //3、构建一颗新的二叉树
            Node parent = new Node(leftNode.getValue() + rightNode.getValue());
            parent.setLeft(leftNode);
            parent.setRight(rightNode);

            //4、从ArrayList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //5、将parent加入到nodes
            nodes.add(parent);
        }

        //返回哈夫曼树的root节点
        return nodes.get(0);

    }


}

@Data
class Node implements Comparable<Node>{
    private int value;
    private Node left;
    private Node right;

    public Node() {
    }

    public Node(int value) {
        this.value = value;
    }

    /**
     * 树的前序遍历方法
     */
    public void preOrder(){
        System.out.println(this);
        if(this.getLeft() != null){
            this.getLeft().preOrder();
        }
        if(this.getRight() != null){
            this.getRight().preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排序
        return this.getValue() - o.getValue();
    }
}