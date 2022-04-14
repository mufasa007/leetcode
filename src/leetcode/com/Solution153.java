package src.leetcode.com;

/**
 * 1,直接暴力求解可以
 * 2,二分查找 fixme 需要复习
 */
public class Solution153 {

    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (nums[pivot] < nums[high]) {
                high = pivot;
            } else {
                low = pivot + 1;
            }
        }
        return nums[low];
    }

    public static void main(String[] args) {
        Solution153 solution = new Solution153();
        int[] nums = {4,5,6,7,0,1,2};
//        int[] nums = {2,1};
//        int[] nums = {3,4,5,1,2};
        System.out.println(solution.findMin(nums));
        System.out.println();
    }
}
