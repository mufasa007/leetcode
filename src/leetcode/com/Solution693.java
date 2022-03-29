package src.leetcode.com;

public class Solution693 {
    public static void main(String[] args) {
        Solution693 solution = new Solution693();
        boolean b0 = solution.hasAlternatingBits(6);
        boolean b1 = solution.hasAlternatingBits(5);
        boolean b2 = solution.hasAlternatingBits(7);
        boolean b3 = solution.hasAlternatingBits(11);
        System.out.println();
    }

    // 使用模拟法
    public boolean hasAlternatingBits1(int n) {
        int prev = 2;
        while (n!=0){
            int cur = n%2;// 获取个位数
            if(prev==cur){
                return false;
            }
            prev = cur;
            n /= 2;
        }
        return true;
    }

    // 使用位运算
    public boolean hasAlternatingBits(int n) {
        n = n ^ n>>1;
        n= n & (n+1);
        return n==0;
    }
}

/*
遍历一遍
101



 */
