package src.enterprise.com.bytedance20220412;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution1 {
    public static void main(String[] args) {
        Solution1 solution = new Solution1();

        System.out.println();
    }

    // 可以算是暴力解法吧
    public List<Integer> minSubsequence(int[] nums) {
        Arrays.sort(nums);
        List<Integer> ret = new ArrayList<>();
        int sum = Arrays.stream(nums).sum();
        int cur=0;
        for (int i = nums.length-1; i >=0 ; i--) {
            cur+=nums[i];
            ret.add(nums[i]);
            if((sum-cur)<cur){
                break;
            }
        }
        return ret;
    }
}
