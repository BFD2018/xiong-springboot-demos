package com.xjt.javase.dataStructure.hashtab;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);

        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建 雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }


}
//创建HashTab 管理多条链表
class HashTab{
    private EmpLinkedList[] empLinkedListArray;
    private int size; //表示有多少条链表

    public HashTab(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        //分别初始化每个链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加
    public void add(Emp emp){
        //根据员工id 得到员工应该被添加到哪条链表
        int empLinkListNo = hashFunc(emp.getId());  //链表编号（0 1 2 3 ... size-1）
        empLinkedListArray[empLinkListNo].add(emp);
    }

    //遍历
    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    //查找
    public Emp findById(int id){
        //使用散列函数确定应该到哪条链表查找
        int empLinkListNo = hashFunc(id);
        Emp emp = empLinkedListArray[empLinkListNo].findById(id);
        if(emp != null){
            System.out.printf("在第%d条链表中找到了雇员id=%d \n",empLinkListNo,id);
        }else{
            System.out.println("在哈希表中，没有找到该雇员~");
        }
        return emp;
    }
    //编写散列函数, 使用一个简单取模法
    public int hashFunc(int id){
        return id % size;
    }
}

//雇员-一个链表的节点信息
class Emp{
    private int id;
    private String name;
    private Emp next;

    public Emp() {
    }

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Emp getNext() {
        return next;
    }

    public void setNext(Emp next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

class EmpLinkedList{
    //头指针指向链表的第一个Emp节点
    private Emp head;

    //添加节点
    public void add(Emp emp){
        if(head == null){   //当前添加的节点就是链表的第一个节点
            head = emp;
            return;
        }
        //如果不是第一个雇员，则使用一个辅助的指针，帮助定位到最后
        Emp cur = head;
        while (true){
            if(cur.getNext() == null){
                break;
            }
            cur.setNext(cur.getNext());
        }
        //循环结束后 cur就是链表最后一个节点了
        cur.setNext(emp);
    }

    //遍历节点
    public void list(int no){   //no 表示链表编号（HashTab作为一个容器管理 size条链表）
        if(head == null){
            System.out.println("当前["+ no +"]链表为空");
        }else{
            Emp cur = head;
            System.out.println("当前["+ no +"]链表信息打印------------------------>");
            while (true){
                System.out.println("  ==>当前节点信息为：id=="+cur.getId() + "  ,name=="+cur.getName());
                //最后一个节点
                if(cur.getNext() == null){
                    break;
                }
                cur = cur.getNext();     //移动指针
            }
        }
    }

    //根据id查找节点
    public Emp findById(int id){
        if(head == null){
            System.out.println("当前链表为空");
        }else{
            Emp cur = head;
            while (true){
                if(cur.getId() == id){
                    return cur;
                }
                //最后一个节点
                if(cur.getNext() == null){
                    System.out.println("没有找到id="+id+" 的Emp");
                    break;
                }
                cur.setNext(cur.getNext());     //移动指针
            }
        }

        return null;
    }
}

