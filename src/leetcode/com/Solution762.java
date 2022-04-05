package src.leetcode.com;

public class Solution762 {
    public static void main(String[] args) {
        Solution762 solution = new Solution762();
        int i = solution.countPrimeSetBits(10, 15);

        int mask = solution.getMask(new int[]{2, 3, 5, 7, 11, 13, 17, 19});

        System.out.println();
    }

    // 官方解答
    public int countPrimeSetBits(int left, int right) {
        int ans = 0;
        for (int x = left; x <= right; ++x) {
            if (((1 << Integer.bitCount(x)) & 665772) != 0) {
                ++ans;
            }
        }
        return ans;
    }

    // 这个掩码就是移位的计算和
    public int getMask(int[] nums){
        int ret = 0;
        for (int i = 0; i < nums.length; i++) {
            ret += (1<<nums[i]);
        }
        return ret;
    }
}
