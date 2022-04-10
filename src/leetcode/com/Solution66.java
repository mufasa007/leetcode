package src.leetcode.com;

public class Solution66 {
    public static void main(String[] args) {
        Solution66 solution = new Solution66();
        int[] digits = {1,2,3};
        int[] ints = solution.plusOne(digits);
        System.out.println();
    }

    public int[] plusOne(int[] digits) {
        int len = digits.length;
        int cache = 1;
        for (int i = len-1; i >=0 ; i--) {
            if(cache==0){
                return digits;
            }
            int curr = digits[i]+cache;
            cache = curr/10;
            digits[i]=curr%10;
        }

        if(cache==1){// 进一位的情况
            int[] ret = new int[len+1];
            ret[0]=1;
            for (int i = 1; i <=len ; i++) {
                ret[i]=digits[i-1];
            }
            return ret;
        }
        return digits;
    }

    // 官方题解
    public int[] plusOne1(int[] digits) {
        int len = digits.length;
        for (int i = len-1; i >=0 ; i--) {
            if(digits[i]==9){
                digits[i]=0;
            }else {
                digits[i]++;
                return digits;
            }
        }
        int[] ret = new int[len+1];
        ret[0]=1;
        return ret;
    }
}
