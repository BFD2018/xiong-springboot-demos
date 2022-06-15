package com.xjt.javase.dataStructure.binarysorttree;

import lombok.Data;
import lombok.NoArgsConstructor;

//二叉排序树测试Demo
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.BSTAdd(new Node(arr[i]));
        }

        //中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树~");
        binarySortTree.BSTInfixOrder();     // 1, 3, 5, 7, 9, 10, 12

        //查找值为 3的节点及其父节点
        System.out.println("值为3的节点===》");
        System.out.println(binarySortTree.BSTSearch(3));
        System.out.println("值为3的节点的父节点===》");
        System.out.println(binarySortTree.BSTSearchParent(3));

        //测试一下删除叶子结点
        binarySortTree.delNode(5);
        binarySortTree.delNode(9);
        binarySortTree.delNode(1);
        System.out.println("删除节点之后中序遍历二叉排序树~");
        binarySortTree.BSTInfixOrder();


    }
}

//定义二叉排序树
class BinarySortTree{
    private Node root;

    public Node getRoot(){
        return this.root;
    }

    public Node BSTSearch(int val){
        if(root == null){
            return null;
        }else{
            return this.root.search(val);
        }
    }

    public Node BSTSearchParent(int val){
        if(root == null){
            return null;
        }else{
            return this.root.searchParent(val);
        }
    }

    public void delNode(int val){
        if(root == null){
            return;
        }else{
            //1、找到要删除的结点  targetNode
            Node targetNode = BSTSearch(val);
            if(targetNode == null){
                return;
            }
            //这颗二叉树只有一个节点 且根节点就是我们找到的目标节点
            if(root.left == null && root.right == null){
                root = null;
                return;
            }
            //2、找到要删除的结点的父节点  parentNode
            Node parentNode = BSTSearchParent(val);

            //3、如果要删除的结点是叶子结点
            if(targetNode.left == null && targetNode.right == null){
                //targetNode是parent的左子节点
                if(parentNode.left!= null && parentNode.left == targetNode){
                    parentNode.left = null;
                }else if(parentNode.right != null && parentNode.right == targetNode){
                    parentNode.right = null;
                }
            }else if(targetNode.left != null && targetNode.right != null){   //4、删除有两颗子树的节点
                int minVal = delRightTreeMin(targetNode);
                targetNode.value = minVal;
            }else{ // 4、删除只有一颗子树的结点
                //4.1、如果要删除的结点有左子结点
                if(targetNode.left != null){
                    if(parentNode != null){
                        if(parentNode.left == targetNode){   //4.1.1 targetNode 是 parentNode的左子节点
                            parentNode.left = targetNode.left;
                        }else if(parentNode.right == targetNode){     //4.1.2 targetNode 是 parentNode的右子节点
                            parentNode.right = targetNode.left;
                        }
                    }else{
                        root = targetNode.left;
                    }
                }else if(targetNode.right != null){   //4.2、如果要删除的结点有右子结点
                    if(parentNode != null){
                        //4.2.1 targetNode 是 parentNode的左子节点
                        if(parentNode.left == targetNode){
                            parentNode.left = targetNode.right;
                        }
                        //4.2.2 targetNode 是 parentNode的右子节点
                        if(parentNode.right == targetNode){
                            parentNode.right = targetNode.right;
                        }
                    }else{
                        root = targetNode.right;
                    }
                }
            }

        }
    }

    /**
     * 思路：右子树的最小值节点 在右子树的叶子节点 最左侧
     * @param node 目标节点
     * @return 删除目标节点右子树的最小值节点，并返回 该最小值节点
     */
    public int delRightTreeMin(Node node){
        Node target = node;
        //循环的查找左子节点，就会找到最小值
        while (target.left != null){
            target = target.left;
        }
        //这时 target就指向了最小结点
        //删除最小结点
        delNode(target.value);
        return target.value;
    }

    //添加结点的方法
    public void BSTAdd(Node node){
        if(this.root == null){
            this.root = node;
        }else{
            this.root.add(node);
        }
    }

    //中序遍历
    public void BSTInfixOrder(){
        if(this.root == null){
            System.out.println("空树无法遍历...");
        }else{
            this.root.infixOrder();
        }
    }
}

@Data
@NoArgsConstructor
class Node{
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    //添加节点
    public void add(Node node){
        if(node == null){
            return;
        }

        //判断传入的结点的值，和当前子树的根结点的值关系
        if(node.value < this.value){
            if(this.left == null){
                this.left = node;
            }else{
                //递归的向左子树添加
                this.left.add(node);
            }
        }else{  //node值 不小于当前节点值
            if(this.right == null){
                this.right = node;
            }else{
                //递归的向右子树添加
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder(){
        if(this.left != null){
            this.left.infixOrder();
        }

        System.out.println(this);

        if(this.right != null){
            this.right.infixOrder();
        }
    }

    /**
     * 根据节点value值 查找结点
     * @param val 节点value值
     * @return 找到就返回该节点，否则返回null
     */
    public Node search(int val){
        if(val == this.value){
            return this;
        }else if(val < this.value){
            if (this.left == null) {
                return null;
            }else{
                return this.left.search(val);
            }
        }else{  //val 不小于当前节点的value值
            if (this.right == null) {
                return null;
            }else{
                return this.right.search(val);
            }
        }
    }

    /**
     * 查找要删除结点的父结点
     * @param val 要找到的结点的值
     * @return 返回的是要删除的结点的父结点，如果没有就返回null
     */
    public Node searchParent(int val){
        //1、当前节点的左节点value==val  或者 右节点value==val
        if((this.left != null && this.left.value == val) ||
                (this.right != null && this.right.value == val)){
            return this;
        }else{
            //如果val 小于当前节点值 并且当前节点的左子树不为null
            if(val < this.value && this.left != null){
               return this.left.searchParent(val);
            }else if(val > this.value && this.right != null){
                return this.right.searchParent(val);
            }else{  //没有找到
                return null;
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
