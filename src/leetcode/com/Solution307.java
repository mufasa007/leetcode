package src.leetcode.com;

public class Solution307 {
    public static void main(String[] args) {
        Solution307 solution = new Solution307();

        System.out.println();
    }


    // 超出时间限制
    class NumArray {
        private int[] nums;

        public NumArray(int[] nums) {
            this.nums = nums;
        }

        public void update(int index, int val) {
            nums[index] = val;
        }

        public int sumRange(int left, int right) {
            left = Math.max(0, left);
            right = Math.min(right, nums.length - 1);

            int sum = 0;
            while (left <= right) {
                sum += nums[left];
                left++;
            }
            return sum;
        }
    }
}
