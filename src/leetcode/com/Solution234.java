package src.leetcode.com;

/*
题目是单向链表：要求时间复杂度O(N),空间复杂度O(1)
todo
 */
public class Solution234 {
    public static void main(String[] args) {
//        int[] ints = {1, 2, 2, 1};
        int[] ints = {1, 2, 1, 1};
        System.out.println(deal(ints));
    }


    /*
    时间复杂度O(N),空间复杂度O(N)
     */
    public static boolean deal(int[] nums) {
        for (int i = 0; i < nums.length / 2; i++) {
            if (nums[i] != nums[nums.length - i - 1]) {
                return false;
            }
        }
        return true;
    }

    /*
    链表反转
     */
    public static boolean deal2(int[] nums) {
        boolean isOdd = nums.length % 2 == 1;


        return true;
    }

}
