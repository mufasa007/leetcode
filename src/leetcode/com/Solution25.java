package src.leetcode.com;

import java.util.PriorityQueue;

public class Solution25 {

    public static void main(String[] args) {
//        int[] nums1 = new int[]{1,3};
//        int[] nums2 = new int[]{2};

//        int[] nums1 = new int[]{1,2};
//        int[] nums2 = new int[]{1,4};

        int[] nums1 = new int[]{1,2,4};
        int[] nums2 = new int[]{1,3,4};

//        int[] nums1 = new int[]{};
//        int[] nums2 = new int[]{3};

        System.out.println(deal1(nums1,nums2).toString());


    }

    /*
    只要求时间复杂度O(m+n)
    直接使用双指针算法,本质其实是将两个有序数组组合成1个有序数组
     */
    public static int[] deal1(int[] nums1, int[] nums2) {
        // 较长的更换在前
        if (nums1.length < nums2.length) {
            int[] numMide = nums1;
            nums1 = nums2;
            nums2 = numMide;
        }

        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] numOut = new int[len1+len2];

        // 两个数组都有值，使用双指针
        int indexX = 0, indexY=0;
        for (int i = 0; i < (len1 + len2) ; i++) {
            if (indexX == len1) {
                numOut[i]=nums2[indexY++];
            } else if (indexY == len2) {
                numOut[i]=nums1[indexX++];
            } else if (nums1[indexX] < nums2[indexY]) {
                numOut[i]=nums1[indexX++];
            } else {
                numOut[i]=nums2[indexY++];
            }
        }

        return numOut;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode pre = head;

        // 两个数组都有值，使用双指针
        while (l1!=null || l2!=null){
            if (l1 == null) {
                pre.next = l2;
                return head.next;
            } else if (l2 == null) {
                pre.next = l1;
                return head.next;
            } else if (l1.val < l2.val) {
                pre.next = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }

        return head.next;
    }

    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if(l1==null){
            return l2;
        }else if(l2==null){
            return l1;
        }
        //构建优先级队列，指定比较器采用ListNode的val属性比较。升序，即最小堆
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(2, (s1, s2) -> s1.val - s2.val);
        ListNode resultHead = new ListNode(-1);
        ListNode currNode = resultHead;
        //把两个链表头节点放入优先队列
        priorityQueue.add(l1);
        priorityQueue.add(l2);
        while(!priorityQueue.isEmpty()){
            // 优先队列非空才能出队，出队poll即为当前最小
            ListNode poll = priorityQueue.poll();
            currNode.next=poll;
            currNode=currNode.next;
            if(poll.next!=null)
                // 出队的那个链表的下个节点加入优先队列
                priorityQueue.add(poll.next);
        }
        return resultHead.next;
    }


    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }


}
