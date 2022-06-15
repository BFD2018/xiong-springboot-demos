package com.xjt.javase.leetcode;

public class TwoNumAdd {
    public static void main(String[] args) {

    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        while (l1.next != null){
            s1.append(l1.val);
            l1 = l1.next;
        }
        while (l2.next != null){
            s2.append(l2.val);
            l2 = l2.next;
        }
        int n1 = Integer.parseInt(s1.toString());
        int n2 = Integer.parseInt(s2.toString());
        int sum = n1 + n2;
        String[] strings = ("" + sum).split("");

        ListNode ret = new ListNode();
        for (int i = strings.length-1; i >= 0; i--) {
            System.out.println(strings[i]);
        }

        return null;
    }
}
