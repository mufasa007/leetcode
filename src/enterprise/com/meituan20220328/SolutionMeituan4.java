package src.enterprise.com.meituan20220328;

import java.util.Arrays;
import java.util.stream.IntStream;

/*
给一个数列，取若干的数，要求和最大并且为7的倍数
 */
public class SolutionMeituan4 {
    public static void main(String[] args) {
        SolutionMeituan4 solution = new SolutionMeituan4();
//        int[] nums = {1,3,6,6};
        int[] nums = {-1,-6,15,4,5};

        int max = solution.deal2(nums);
        System.out.println();
    }

    // 暴力算法，容易超时
    public int deal1(int[] nums) {
        int len = nums.length;

        int[] reslt = {0, 0};// 前是index、后面的是结果
        singleDeal(nums, reslt);
        return max;
    }


    static int max = Integer.MIN_VALUE;
    // 贪心算法
    public int deal2(int[] nums) {
        int len = nums.length;
        int max = Integer.MIN_VALUE;
        int[][] dp = new int[len+1][len+1];
        Arrays.sort(nums);
        this.reverse(nums);
        singleDeal(nums,new int[]{0,0});

        return max;
    }

    // 尽可能的寻找最大值
    private void singleDeal(int[] nums, int[] reslt) {
        if (max % 7 == 0 && nums[reslt[0]] < 0) {
            // 已经存在具体的结果，同时当前的选择只会将数据和降低，这时直接退出
            return;
        }

        int sum = nums[reslt[0]] + reslt[1];
        if (sum % 7 == 0){
            // 当前最大值
            max = sum;
        }

        if (reslt[0] + 1 < nums.length) {
            // 选用当前数值(这个一定要在前面)
            int[] result2 = {reslt[0] + 1, sum};
            singleDeal(nums, result2);

            // 不选用当前数值
            int[] result1 = {reslt[0] + 1, reslt[1]};
            singleDeal(nums, result1);
        }
    }

    // 数组反转
    private void reverse(int[] nums)    {
        int i, t;
        int n = nums.length;
        for (i = 0; i < n / 2; i++) {
            t = nums[i];
            nums[i] = nums[n - i - 1];
            nums[n - i - 1] = t;
        }
    }
}

/*
给一个数列，取若干的数，要求和最大并且为7的倍数（也可以一个数都不取）
输入：[1,3,6,6]
ans = 7
输入：[-1,-6,15,4,5]
ans = 14

作者：养猪场刘老板
链接：https://leetcode-cn.com/circle/discuss/qxyKtN/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */