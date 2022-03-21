package src.leetcode.com;

public class Solution704 {
    public static void main(String[] args) {
//        int[] nums = new int[]{-1, 0, 3, 5, 9, 12};
        int[] nums = new int[]{2,5,6,7};
//        System.out.println(search(nums, 12));
//        System.out.println(search(nums, 5));
//        System.out.println(search(nums, 2));
        System.out.println(search(nums, 0));
        System.out.println(search(nums, 10));
    }


    public static int search(int[] nums, int target) {
        int len = nums.length;
        int left = 0, right = len - 1, index;
        while (left < right) {
            index = (left + right) / 2;
            if (nums[index] < target) {
                left = ++index;
            } else if (nums[index] > target) {
                right = --index;
            } else {
                return index;
            }
        }

        if (nums[left] != target) {
            return -1;
        } else {
            return left;
        }
    }
}
