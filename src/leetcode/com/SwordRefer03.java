package src.leetcode.com;

import java.util.HashSet;
import java.util.Set;

public class SwordRefer03 {
    public static void main(String[] args) {
        SwordRefer03 solution = new SwordRefer03();

        System.out.println();
    }


    /*
     * 执行用时：2338 ms, 在所有 Java 提交中击败了5.01%的用户
     * 内存消耗：48.6 MB, 在所有 Java 提交中击败了60.53%的用户
     * */
    // 双指针解法(时间复杂度高，空间复杂度低)
    public int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return nums[i];
                }
            }
        }
        return -1;
    }


    /*
     * 执行用时：2 ms, 在所有 Java 提交中击败了64.43%的用户
     * 内存消耗：48.6 MB, 在所有 Java 提交中击败了65.84%的用户
     * */
    // bitmap解法
    public int findRepeatNumber1(int[] nums) {
        byte[] bitmap = new byte[100000];
        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            if (bitmap[curr] == 1) {
                return curr;
            } else {
                bitmap[curr] = 1;
            }
        }
        return -1;
    }

    /*
     * 执行用时：4 ms, 在所有 Java 提交中击败了57.52%的用户
     * 内存消耗：50.8 MB, 在所有 Java 提交中击败了13.46%的用户
     * */
    // hashSet的解法
    public int findRepeatNumber2(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        int repeat = -1;
        for (int num : nums) {
            if (!set.add(num)) {
                repeat = num;
                break;
            }
        }
        return repeat;
    }

}

