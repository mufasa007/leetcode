package src.leetcode.com;

public class Solution53 {
    public static void main(String[] args) {

    }

    /*
    动态规划
     */
    public static int deal(int[] nums) {
        int result = nums[0];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum > 0) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }
            result = Math.max(result, sum);
        }

        return result;
    }
}
