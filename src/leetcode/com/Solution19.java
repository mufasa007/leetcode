package src.leetcode.com;

import src.leetcode.com.common.ListNode;

public class Solution19 {
    public static void main(String[] args) {

    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode restHead = new ListNode();
        restHead.next = head;
        ListNode iNode=head,jNode = restHead;

        int i=0,j=0;
        while (iNode!=null){
            i++;
            iNode = iNode.next;
            if(i>n){
                j++;
                jNode = jNode.next;
            }
        }

        jNode.next = jNode.next.next;
        return restHead.next;

    }
}
