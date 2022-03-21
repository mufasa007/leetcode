package src.leetcode.com;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Solution4 {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,3};
        int[] nums2 = new int[]{2};

//        int[] nums1 = new int[]{1,2};
//        int[] nums2 = new int[]{3,4};

//        int[] nums1 = new int[]{};
//        int[] nums2 = new int[]{2,3};

//        int[] nums1 = new int[]{};
//        int[] nums2 = new int[]{3};

        System.out.println(deal1(nums1,nums2));


    }

    /*
    只要求时间复杂度O(m+n)
    直接使用双指针算法,本质其实是将两个有序数组组合成1个有序数组
    举一反三：25题 合并两个排序的链表
     */
    public static float deal1(int[] nums1, int[] nums2) {
        Deque<Integer> integerQueue = new LinkedList<Integer>();

        int len1 = nums1.length;
        int len2 = nums2.length;
        boolean isOdd = (len1+len2)%2 == 1;

        // 两个数组都有值，使用双指针
        int indexX = 0, indexY=0;
        for (int i = 0; i < (len1 + len2) / 2 + 1; i++) {
            if (indexX == len1) {
                integerQueue.addFirst(nums2[indexY++]);
            } else if (indexY == len2) {
                integerQueue.addFirst(nums1[indexX++]);
            } else if (nums1[indexX] < nums2[indexY]) {
                integerQueue.addFirst(nums1[indexX++]);
            } else {
                integerQueue.addFirst(nums2[indexY++]);
            }
        }

        if(isOdd){
            return integerQueue.removeFirst();
        }else {
            return (integerQueue.removeFirst() + integerQueue.removeFirst())/2f;
        }
    }



    /*
    思路有问题：
    反思 ->
     */
    public static float deal(int[] nums1, int[] nums2) {
        // 较长的更换在前
        if (nums1.length < nums2.length) {
            int[] numMide = nums1;
            nums1 = nums2;
            nums2 = numMide;
        }

        int len1 = nums1.length;
        int len2 = nums2.length;
        boolean isOdd = (len1+len2)%2 == 1;

        // 如果较短的数组为空时，
        if (len2 == 0) {
            if (isOdd) {
                return Float.parseFloat(String.valueOf(nums1[len1 / 2] ));
            } else {
                return (Float.parseFloat(String.valueOf(nums1[len1 / 2])) + nums1[len1 / 2 - 1]) / 2;
            }
        }

        //
        int x = 0, y = 0;
        int index = (len1 + len2 - 2) / 2 + (len1 + len2 -2) % 2;
        for (int i = 0; i < index; i++) {
            if (nums1[x] > nums2[y] && y<len2) {
                y++;
            }else {
                x++;
            }
        }

        if(isOdd){
            return Math.min(nums1[x] , nums2[y]);
        }else {
            return (Float.parseFloat(String.valueOf(nums1[x])) + nums2[y]) / 2;
        }
    }

}
