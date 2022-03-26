package src.leetcode.com;

import src.leetcode.com.common.ListNode;

public class Solution876 {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);

        ListNode head = listNode1;
        head.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

        ListNode outNode = middleNode(head);
        System.out.println();

    }

    // 双指针，边界条件
    public static ListNode middleNode(ListNode head) {
        int i = 0;// i是快指针，j是慢指针
        ListNode iNode = head, jNode = head;
        while (iNode != null) {
            iNode = iNode.next;
            i++;
            if (i % 2 == 0) {
                jNode = jNode.next;
            }
        }
        return jNode;
    }
}
