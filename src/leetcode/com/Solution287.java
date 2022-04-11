package src.leetcode.com;

public class Solution287 {
    public static void main(String[] args) {
        Solution287 solution = new Solution287();
        int[] ints = {1, 2, 3, 4, 4};
        int deal = solution.findDuplicate1(ints);
        System.out.println();
    }

    // 按位运算
    public int findDuplicate(int[] nums) {
        int n = nums.length - 1;
        int cur = 0;
        for (int i = 0; i < n; i++) {
            cur ^= (i + 1);
            cur ^= nums[i];
        }
        cur ^= nums[n];
        return cur;
    }

    // bitmap解决 空间复杂度为O(n)，时间复杂度为O(n)
    public int findDuplicate1(int[] nums) {
        int[] bitmap = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            if (bitmap[curr] >= 1) {
                return curr;
            } else {
                bitmap[curr] = 1;
            }
        }
        return -1;
    }
}
