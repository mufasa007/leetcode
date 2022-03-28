package src.enterprise.com.meituan20220328;

public class SolutionMeituan3 {
    public static void main(String[] args) {
        SolutionMeituan3 solution = new SolutionMeituan3();
        int[] nums = {1, 3, 6, 7};
        int deal = solution.deal(nums);
        System.out.println();
    }

    public int deal(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        int step = 1;

        while (len / step > 0) {
            for (int i = 0; i < len; i++) {
                int right = i + step;// 左闭右开
                if (right <= len) {
                    dp[i + step / 2] += 1;
                } else {
                    break;
                }
            }
            step += 2;
        }

        // 计算总和
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += dp[i] * nums[i];
        }
        return sum;
    }
}

/*
给一个整数数列，求所有奇数长度子区间的中位数之和
输入：[1,3,6,7]
长度为1的子区间：1 3 6 7
长度为3的子区间：[1,3,6]，[3,6,7]
所以ans = 1 + 3 + 6 + 7 + 3 + 6

作者：养猪场刘老板
链接：https://leetcode-cn.com/circle/discuss/qxyKtN/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */