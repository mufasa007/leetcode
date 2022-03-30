package src.leetcode.com;

import java.util.Arrays;

public class Solution213 {
    public static void main(String[] args) {
        Solution213 solution = new Solution213();
//        int[] nums = {2.3.};
//        int[] nums = {2,7,9,3,1};
        int[] nums = {1,2,3};
        int result = solution.rob(nums);
        System.out.println();
    }



    public int rob(int[] nums) {
        if (nums.length < 3) {
            return Arrays.stream(nums).max().getAsInt();
        }
        return Math.max(robSelect( nums,0,nums.length-2),robSelect(nums,1,nums.length-1));
    }

    private int robSelect(int[] nums,int l,int r){
        int prev = nums[l+0], curr = Math.max(nums[l+0],nums[l+1]), next;
        for (int i = l+2; i <= r; i++) {
            next = Math.max(prev + nums[i], curr);
            prev = curr;
            curr = next;
        }
        return curr;
    }
}
