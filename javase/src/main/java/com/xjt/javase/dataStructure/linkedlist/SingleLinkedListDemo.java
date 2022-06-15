package com.xjt.javase.dataStructure.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode hero5 = new HeroNode(5, "李逵", "黑旋风");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //加入
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.list();

        //加入按照编号的顺序
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero5);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.list();

        //测试修改节点的代码
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        singleLinkedList.update(newHeroNode);
        System.out.println("===============>修改节点后");
        singleLinkedList.list();

        int length = SingleLinkedList.getLength(singleLinkedList.getHead());
        System.out.println(length);

        //找到链表的倒数第二个节点
//        HeroNode lastIndexNode = SingleLinkedList.findLastIndexNode(singleLinkedList.getHead(), 2);
//        System.out.println(lastIndexNode);

        //反转
        SingleLinkedList.reverseLinkedList(singleLinkedList);
        singleLinkedList.list();

        System.out.println("==================>反转打印");
        SingleLinkedList.reversePrint(singleLinkedList);

        //删除一个节点
//        singleLinkedList.del(1);
//        singleLinkedList.del(4);
//        singleLinkedList.del(3);
//        System.out.println("删除后的链表情况~~");
//        singleLinkedList.list();
    }
}
//节点
class HeroNode{
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;       //指向下一个节点

    public HeroNode() {
    }

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}

//单链表
class SingleLinkedList{
    //先初始化一个头节点, 头节点不要动, 不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    /**
     * //添加节点到单向链表
     * //思路，当不考虑编号顺序时
     * //1. 找到当前链表的最后节点
     * //2. 将最后这个节点的 next 指向 新的节点
     * @param heroNode
     */
    public void add(HeroNode heroNode){
        //因为head节点不能动 我们需要一个temp辅助遍历
        HeroNode temp = head;

        //遍历节点 找到最后
        while (true){
            //找到链表最后
            if(temp.next == null){
                break;
            }
            //temp后移
            temp = temp.next;
        }
        //当退出 while 循环时，temp 就指向了链表的最后
        //将最后这个节点的 next 指向 新的节点
        temp.next = heroNode;
    }

    /**
     * //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
     * //(如果有这个排名，则添加失败，并给出提示)
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode){
        //因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
        //因为单链表，因为我们找的 temp 是位于 添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false;   // flag 标志添加的编号是否存在，默认为 false
        while(true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no > heroNode.no){     //位置找到了
                break;
            }
            if(temp.next.no == heroNode.no){        //该编号已存在
                flag = true;
                break;
            }
            temp = temp.next;
        }

        //判断 flag 的值
        if(flag){
            System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.no);
        }else{
            //插入到链表中
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * //修改节点的信息, 根据 no 编号来修改，即 no 编号不能改.
     * //说明: 根据 newHeroNode 的 no 来修改即可
     * @param newHeroNode
     */
    public void update(HeroNode newHeroNode){
        //判断是否空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }

        //找到需要修改的节点（根据 no 编号）
        //定义一个辅助变量(指向当前节点)
        HeroNode temp = head.next;
        boolean flag = false; //表示是否找到该节点
        while (true){
            if(temp == null){
                break;  //已经遍历完链表
            }
            if(temp.no == newHeroNode.no){
                flag = true;        //找到了
                break;
            }
            temp = temp.next;
        }
        //根据 flag 判断是否找到要修改的节点
        if(flag){
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        }else{
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", newHeroNode.no);
        }
    }

    /**
     * //删除节点(根据no)
     * //思路
     * //1. head 不能动，因此我们需要一个 temp 辅助节点找到待删除节点的前一个节点
     * //2. 说明我们在比较时，是 temp.next.no 和需要删除的节点的 no 比较
     */
    public void del(int no){
        HeroNode temp = head;
        boolean flag = false; //表示是否找到该节点
        while (true){
            if(temp.next== null){
                break;  //已经遍历完链表
            }
            if(temp.next.no == no){
                flag = true;        //找到了
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.next = temp.next.next;
        }else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }

    /**
     * //显示链表[遍历]
     */
    public void list() {
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为头节点，不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true){
            if(temp == null){
                break;  //已经遍历完链表
            }
            //输出节点的信息
            System.out.println(temp);

            temp = temp.next;
        }
    }

    /**
     * 有效节点个数
     */
    public static int getLength(HeroNode head){
        if(head.next == null){
            return 0;
        }
        int length = 0;
        //定义一个辅助遍历
        HeroNode cur = head.next;
        while (cur != null){
            length++;
            cur = cur.next;
        }
        return length;
    }

    /**
     * 查找单链表中的倒数第 k 个结点【新浪面试题】
     * 思路：
     *     1、编写一个方法，接收 head 节点，同时接收一个 index（表示是倒数第 index 个节点）
     *     2、先把链表从头到尾遍历，得到链表的总的长度 getLength
     *     3、得到 size 后，我们从链表的第一个开始遍历 (size-index)个，就可以得到
     */
    public static HeroNode findLastIndexNode(HeroNode head,int idx){
        int size = getLength(head);

        if(idx <0 || idx >size){
            return null;
        }
        HeroNode cur = head.next;
        for (int i = 0; i < size - idx; i++) {
            cur = cur.next;         //后移
        }

        return cur;
    }

    /**
     * 单链表的反转【腾讯面试题，有点难度】
     * 思路：
     *      1、
     */
    public static void reverseLinkedList(SingleLinkedList linkedList){
        HeroNode reverseHead = new HeroNode(0,"","");
        HeroNode head = linkedList.getHead();

        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if(head.next == null || head.next.next == null){
            return;
        }
        HeroNode cur = linkedList.getHead().next;       //第一个有效节点
        HeroNode next = null;       // 指向当前节点[cur]的下一个节点

        while (cur != null){
            next = cur.next;        //先暂时保存当前节点的下一个节点，因为后面需要使用

            cur.next = reverseHead.next;
            reverseHead.next = cur;

            cur = next;
        }

        //将 head.next 指向 reverseHead.next , 实现单链表的反转
        head.next = reverseHead.next;
    }

    /**
     * 从尾到头打印单链表 【百度，要求方式1：反向遍历 。 方式2：Stack栈】
     * 遍历链表 然后将节点放入栈中（利用）
     */
    public static void reversePrint(SingleLinkedList singleLinkedList){
        HeroNode head = singleLinkedList.getHead();
        if(head.next == null){
            return;
        }

        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }

        while (stack.size() >0){
            System.out.println(stack.pop());
        }
    }
}
