package com.xjt.javase.dataStructure.tree.threadedbinarytree;

import lombok.Data;
import lombok.ToString;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试一把中序线索二叉树的功能
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        //二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree(root);
        threadedBinaryTree.threadedNodes();

        //测试: 以10号节点测试
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();
        System.out.println("10号结点的前驱结点是 ="  + leftNode); //3
        System.out.println("10号结点的后继结点是="  + rightNode); //1

        //当线索化二叉树后，不能在使用原来的遍历方法
        //threadedBinaryTree.infixOrder();
        System.out.println("使用线索化的方式遍历 线索化二叉树");
        threadedBinaryTree.threadedlist(); // 8, 3, 10, 1, 14, 6
    }
}

//定义树
class ThreadedBinaryTree{
    //定义一个根节点
    private HeroNode root;

    //为了实现线索化，需要创建一个指向当前结点的前驱结点的指针
    //在递归进行线索化时，pre 总是保留前一个结点
    private HeroNode pre = null;


    public ThreadedBinaryTree() {
    }

    public ThreadedBinaryTree(HeroNode root) {
        this.root = root;
    }

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //重载一把threadedNodes方法
    public void threadedNodes() {
        this.threadedNodes(root);
    }

    //遍历线索化二叉树的方法
    public void threadedlist(){
        //定义一个变量存储当前遍历的节点，从root开始
        HeroNode node = root;
        while (node != null){
            //循环找到leftType == 1的节点，第一个找到的就是8
            //后面随着遍历而变化,因为当leftType==1时，说明该结点是按照线索化 处理后的有效结点
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }

            //打印当前这个节点
            System.out.println(node);

            //如果当前节点的右指针指向的是后继节点 就一直输出
            while (node.getLeftType() == 1){
                //获取到当前结点的后继结点
                node = node.getRight();

                System.out.println(node);
            }
            //替换这个遍历的结点(节点6 时下一个循环退出)
            node = node.getRight();
        }
    }


    /**
     * //编写对线索化二叉树进行中序遍历的方法（线索化节点）
     * @param node 就是当前需要线索化的结点
     */
    public void threadedNodes(HeroNode node){
        if(node == null){
            return;
        }
        //(1)先线索左子树
        threadedNodes(node.getLeft());

        //(2)线索当前节点
        //处理当前结点的前驱结点
        //以8结点来理解
        //8结点的.left = null , 8结点的.leftType = 1
        if(node.getLeft() == null){
            //让当前结点的左指针指向前驱结点
            node.setLeft(pre);
            //修改当前结点的左指针的类型,指向前驱结点
            node.setLeftType(1);
        }

        //处理后继结点
        if(pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }

        //(注意) 每处理一个节点后 让当前节点 成为下一个节点的前驱节点
        pre = node;

        //(3)线索右子树
        threadedNodes(node.getRight());
    }


    //删除结点
    public void delNode(int no){
        if(this.root != null){
            //如果只有一个root结点, 这里立即判断root是不是就是要删除结点
            if(this.root.getNo() == no){
                this.root = null;
            }else{
                this.root.delNode(no);
            }
        }else{
            System.out.println("空树，不能删除~");
        }
    }

    //前序遍历
    public void preOrder(){
        if(this.root != null){
            this.root.preOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //中序遍历
    public void infixOrder(){
        if(this.root != null){
            this.root.infixOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //后序遍历
    public void postOrder(){
        if(this.root != null){
            this.root.postOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no){
        if(this.root != null){
            return this.root.infixOrderSearch(no);
        }else{
            return null;
        }
    }

}

//定义节点
@Data
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //说明
    //1. 如果leftType == 0 表示指向的是左子树, 如果 1 则表示指向前驱结点
    //2. 如果rightType == 0 表示指向是右子树, 如果 1表示指向后继结点
    private int leftType;
    private int rightType;

    public HeroNode() {
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    //递归删除结点
    //1.如果删除的节点是叶子节点，则删除该节点
    //2.如果删除的节点是非叶子节点，则删除该子树
    public void delNode(int no){
        /*  思路：
		 * 	1. 因为我们的二叉树是单向的，所以我们是判断当前结点的子结点是否是需要删除结点，而不能去判断当前这个结点是不是需要删除结点.
			2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
			3. 如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将this.right= null ;并且就返回(结束递归删除)
			4. 如果第2和第3步没有删除结点，那么我们就需要向左子树进行递归删除
			5.  如果第4步也没有删除结点，则应当向右子树进行递归删除.
		 */
        if(this.left != null && this.left.getNo() == no){
            this.left = null;
            return;
        }
        if(this.right != null && this.right.getNo() == no){
            this.right = null;
            return;
        }
        if(this.left != null){
            this.left.delNode(no);
        }
        if(this.right != null){
            this.right.delNode(no);
        }
    }

    //编写前序遍历的方法
    public void preOrder(){
        System.out.println(this);       //输出节点
        //递归向左子节点遍历
        if(this.left != null){
            this.left.preOrder();
        }
        //递归向右子节点遍历
        if(this.right != null){
            this.right.preOrder();
        }
    }

    //编写中序遍历的方法
    public void infixOrder(){
        //递归向左子节点遍历
        if(this.left != null){
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);

        //递归向右子节点遍历
        if(this.right != null){
            this.right.infixOrder();
        }
    }

    //编写后序遍历的方法
    public void postOrder(){
        //递归向左子节点遍历
        if(this.left != null){
            this.left.postOrder();
        }
        //递归向右子节点遍历
        if(this.right != null){
            this.right.postOrder();
        }

        //输出父节点
        System.out.println(this);
    }


    //中序遍历查找节点
    public HeroNode infixOrderSearch(int no){
        //1、当前节点的左子节点（如果不为空）递归前序查找
        //2、如果左递归前序查找，找到节点，则返回
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.infixOrderSearch(no);
        }
        if(resNode != null){    //找到了 直接返回
            return resNode;
        }
        System.out.println("中序遍历查找~");
        //比较当前节点是不是
        if(this.getNo() == no){
            return this;
        }

        //左递归没有找到 继续向右递归查找
        if(this.right != null){
            resNode = this.right.infixOrderSearch(no);
        }

        //不管有没有找到都返回
        return resNode;
    }
}