package src.leetcode.com;

public class Solution287 {
    public static void main(String[] args) {
        Solution287 solution = new Solution287();
//        int[] ints = {1, 2, 3, 4, 4};
//        int[] ints = {1,3,4,2,2};
//        int[] ints = {3,1,3,4,2};
        int[] ints = {3,1,3,4,2};
        int deal = solution.findDuplicate2(ints);
        System.out.println();
    }

    // 按位运算(只针对只有一个重复数字，且重复1次的情况)
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

    // 快慢指针
    public int findDuplicate2(int[] nums) {
        int slow = nums[0], fast = nums[nums[0]];
        while (slow!=fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    // 普通双指针 时间复杂度O(n),空间复杂度O(1)
    // 超时！
    public int findDuplicate3(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if(nums[i]==nums[j]){
                    return nums[i];
                }
            }
        }
        return -1;
    }

}
