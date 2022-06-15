package com.xjt.javase.dataStructure.linkedlist;

public class JosephuDemo {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoys();

        circleSingleLinkedList.countBoy(1,2,5);
    }

}



class CircleSingleLinkedList{
    //创建一个first节点
    private BoyNode first = null;

    // 添加小孩数量nums，构建成一个拥有nums数量的环形链表
    public void addBoy(int nums){
        if(nums < 1){
            System.out.println("nums 的值不正确");
            return;
        }
        BoyNode curBoy = null;       // 辅助指针，帮助构建环形链表
        for (int i = 1; i <= nums; i++) {
            BoyNode boy = new BoyNode(i);
            if(i == 1){     //第一个节点
                first = boy;
                first.setNext(first);       // 构成环
                curBoy = first; // 让 curBoy 指向第一个小孩
            }else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    // 遍历当前的环形链表
    public void showBoys(){
        // 判断链表是否为空
        if(first == null){
            System.out.println("没有任何小孩~~");
            return;
        }
        // 因为 first 不能动，因此我们仍然使用一个辅助指针完成遍历
        BoyNode curBoy = first;
        while (true){
            System.out.printf("小孩的编号 %d \n", curBoy.getNo());
            if(curBoy.getNext() == first){      // 说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext();
        }
    }



    /** // 根据用户的输入，计算出小孩出圈的顺序
     *
     * @param startNo   表示从第几个小孩开始数数
     * @param countNum  表示数几下
     * @param nums  表示最初圈中有多少小孩
     */
    public void countBoy(int startNo, int countNum, int nums){
        if (startNo < 1 || startNo > nums || countNum < 1 || first == null) {
            System.out.println("参数输入有误， 请重新输入");
            return;
        }
        // 创建2个辅助指针,一个cur（指向startNo规定的小孩 即从这个小孩还是数数）  一个helper（cur的前一个位置）
        //初始化：cur指向第一个小孩位置 helper指向最后一个小孩位置
        BoyNode cur = first;
        BoyNode helper = first;
        //将helper 移动到最后一个节点位置
        while (true){
            if(helper.getNext() == cur){
                break;
            }
            helper = helper.getNext();
        }
        //将两个指针移动到规定的起始位置
        for (int i = 0; i < startNo - 1; i++) {
            cur = cur.getNext();
            helper = helper.getNext();
        }

        while (true){
            if(cur == helper){      //说明圈中只有一个节点
                break;
            }

            //在小孩报数前移动2个辅助指针
            for (int i = 0; i < countNum - 1; i++) {
                cur = cur.getNext();
                helper = helper.getNext();
            }
            //这时 cur 指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d 出圈\n", cur.getNo());

            //这时将 cur 指向的小孩节点出圈
            cur = cur.getNext();        //将当前指针cur移动到下一个位置
            helper.setNext(cur);    //上一个指针指向cur
        }

        System.out.printf("最后留在圈中的小孩编号%d \n", cur.getNo());
    }
}

class BoyNode{
    private int no;     // 编号
    private BoyNode next;       //指向下一个节点

    public BoyNode() {
    }

    public BoyNode(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public BoyNode getNext() {
        return next;
    }

    public void setNext(BoyNode next) {
        this.next = next;
    }
}
