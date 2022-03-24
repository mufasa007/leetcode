package src.leetcode.com;

public class Solution283 {
    public static void main(String[] args) {
        int[] nums = new int[]{0,1,1,3,3,12};
        moveZeroes1( nums);
        System.out.println();
    }

    // 不是最优解
    public static void moveZeroes(int[] nums) {
        int len =nums.length;

        for (int i = 1; i < len; i++) {
            if(nums[i]!=0){
                swap(nums,i);
            }
        }
    }

    private static void swap(int[] nums, int index) {
        while (index > 0 && nums[index - 1] == 0) {
            nums[index - 1] = nums[index];
            nums[index--] = 0;
        }
    }

    // 双指针
    public static void moveZeroes1(int[] nums) {
        int len = nums.length;
        int i = 0;
        while (i < len - 1) {
            // 只用找到第一个0
            if (nums[i] == 0) {
                int j = i + 1;
                while (j < len) {
                    // 找到第一个非0
                    if (nums[j] != 0) {
                        pureSwap(nums, i++, j++);
                    }else {
                        j++;
                    }
                }
                return;
            } else {
                i++;
            }
        }
    }

    private static void pureSwap(int[] nums, int a,int b) {
        int mide = nums[a];
        nums[a]=nums[b];
        nums[b]=mide;
    }
}
