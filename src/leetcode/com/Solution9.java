package src.leetcode.com;

public class Solution9 {
    public static void main(String[] args) {
        Solution9 solution = new Solution9();

        System.out.println();
    }

    // 转string 对向双指针

    // 进阶直接反转数字
    public boolean isPalindrome(int x) {
        if(x<0 || (x%10==0 && x!=0)){
            return false;
        }

        int revertedNumber = 0;
        while (x >revertedNumber){
            revertedNumber = revertedNumber* 10 + x%10;
            x/=10;
        }

        return x==revertedNumber || x==revertedNumber/10;
    }
}
