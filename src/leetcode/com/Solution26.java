package src.leetcode.com;

import java.util.HashMap;
import java.util.Map;

class Solution26 {

    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }

        // 双指针算法
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }
}