package src.leetcode.com;

public class Solution191 {
    public static void main(String[] args) {
        Solution191 solution = new Solution191();

        System.out.println();
    }

    public int hammingWeight(int n) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                ret++;
            }
        }
        return ret;
    }

    // 官方位运算
    public int hammingWeight1(int n) {
        int ret = 0;
        while (n!=0){
            n&=n-1;
            ret++;
        }
        return ret;
    }
}
