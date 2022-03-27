package src.leetcode.com;

import java.util.Arrays;

public class Solution213 {
    public static void main(String[] args) {
        Solution213 solution = new Solution213();
//        int[] nums = {1, 2, 3, 1};
//        int[] nums = {2,7,9,3,1};
        int[] nums = {2,1,1,2};
        int result = solution.rob(nums);
        System.out.println();
    }

    public int rob(int[] nums) {
        if (nums.length < 3) {
            return Arrays.stream(nums).max().getAsInt();
        }

        int prev = nums[0], curr = Math.max(nums[0],nums[1]), next;
        for (int i = 2; i < nums.length; i++) {
            next = Math.max(prev + nums[i], curr);
            prev = curr;
            curr = next;
        }
        return curr;
    }
}
