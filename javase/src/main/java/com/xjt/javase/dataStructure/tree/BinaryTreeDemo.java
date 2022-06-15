package com.xjt.javase.dataStructure.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
//        BinaryTree binaryTree = new BinaryTree();
//        HeroNode root = new HeroNode(1, "宋江");
//        HeroNode node2 = new HeroNode(2, "吴用");
//        HeroNode node3 = new HeroNode(3, "卢俊义");
//        HeroNode node4 = new HeroNode(4, "林冲");
//        HeroNode node5 = new HeroNode(5, "关胜");
//        binaryTree.setRoot(root);
//        root.setLeft(node2);
//        root.setRight(node3);
//        node3.setLeft(node5);
//        node3.setRight(node4);

        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(3, "吴用");
        HeroNode node3 = new HeroNode(6, "卢俊义");
        HeroNode node4 = new HeroNode(8, "林冲");
        HeroNode node5 = new HeroNode(10, "关胜");
        HeroNode node6 = new HeroNode(14, "李逵");
        binaryTree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);


        //测试前序遍历
        System.out.println("前序遍历");
		binaryTree.preOrder();
        //测试中序遍历
		System.out.println("中序遍历");
		binaryTree.infixOrder();
        //测试后序遍历
		System.out.println("后序遍历");
		binaryTree.postOrder();


        //前序遍历
        //前序遍历的次数 ：4
//		System.out.println("前序遍历方式....");
//		HeroNode resNode = binaryTree.preOrderSearch(5);
//		if (resNode != null) {
//			System.out.printf("找到了，信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
//		} else {
//			System.out.printf("没有找到 no = %d 的英雄", 5);
//		}

        //中序遍历查找
        //中序遍历3次
//		System.out.println("中序遍历方式~~~");
//		HeroNode resNode = binaryTree.infixOrderSearch(5);
//		if (resNode != null) {
//			System.out.printf("找到了，信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
//		} else {
//			System.out.printf("没有找到 no = %d 的英雄", 5);
//		}

        //后序遍历查找
        //后序遍历查找的次数  2次
//		System.out.println("后序遍历方式~~~");
//		HeroNode resNode = binaryTree.postOrderSearch(5);
//		if (resNode != null) {
//			System.out.printf("找到了，信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
//		} else {
//			System.out.printf("没有找到 no = %d 的英雄", 5);
//		}

        //3、测试删除节点
//        System.out.println("前序遍历");     // 1,2,3,5,4
//        binaryTree.preOrder();
//        binaryTree.delNode(2);
//        System.out.println("删除节点之后的前序遍历");
//        binaryTree.preOrder();      //1 2
    }
}

//定义BinaryTree 二叉树
class BinaryTree{
    //定义一个根节点
    private HeroNode root;

    public BinaryTree() {
    }

    public BinaryTree(HeroNode root) {
        this.root = root;
    }

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
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

    //前序遍历查找
    public HeroNode preOrderSearch(int no){
        if(this.root != null){
            return this.root.preOrderSearch(no);
        }else{
            return null;
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
    //后序遍历查找
    public HeroNode postOrderSearch(int no){
        if(this.root != null){
            return this.root.postOrderSearch(no);
        }else{
            return null;
        }
    }

}

//定义 HeroNode 树结点
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode() {
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
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

    /**
     * //前序遍历查找节点
     * @param no    节点id
     * @return 返回找到的节点，否则返回null
     */
    public HeroNode preOrderSearch(int no){
        System.out.println("进入前序遍历");
        //比较当前节点是不是
        if(this.getNo() == no){
            return this;
        }

        //1、如果当前节点不是目标 判断它的左子节点（如果不为空）递归前序查找
        //2、如果左递归前序查找，找到节点，则返回
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.preOrderSearch(no);
        }
        if(resNode != null){    //找到了 直接返回
            return resNode;
        }
        //左递归没有找到 继续向右递归查找
        if(this.right != null){
            resNode = this.right.preOrderSearch(no);
        }

        //不管有没有找到都返回
        return resNode;
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

    //后序遍历查找节点
    public HeroNode postOrderSearch(int no){

        //1、当前节点的左子节点（如果不为空）递归前序查找
        //2、如果左递归前序查找，找到节点，则返回
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.postOrderSearch(no);
        }
        if(resNode != null){    //左递归找到了 直接返回
            return resNode;
        }

        //左递归没有找到 继续向右递归查找
        if(this.right != null){
            resNode = this.right.postOrderSearch(no);
        }
        if(resNode != null){    //右递归找到了 直接返回
            return resNode;
        }
        System.out.println("后序遍历查找~");
        //比较当前节点是不是
        if(this.getNo() == no){
            return this;
        }

        //不管有没有找到都返回
        return resNode;
    }
}

