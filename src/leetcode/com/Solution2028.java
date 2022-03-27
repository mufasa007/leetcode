package src.leetcode.com;

import java.util.Arrays;

public class Solution2028 {
    public static void main(String[] args) {
        Solution2028 solution = new Solution2028();
        int[] rolls = {3,2,4,3};
        int mean = 4, n = 2;
        int[] out = solution.missingRolls(rolls,mean,n);
        System.out.println();
    }

    public int[] missingRolls(int[] rolls, int mean, int n) {
        int[] out = new int[n];
        int sum = mean*(n+rolls.length)- Arrays.stream(rolls).sum();
        if(sum<n || sum>6*n){
            return new int[0];
        }

        int meanPre = sum/n,meanPreUp = meanPre + 1,remainder = sum%n;
        for (int i = 0; i < remainder; i++) {
            out[i] = meanPreUp;
        }

        for (int i = remainder; i < n; i++) {
            out[i] = meanPre;
        }

        return out;
    }
}
