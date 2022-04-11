package src.leetcode.com;

public class Solution357 {
    public static void main(String[] args) {
        Solution357 solution = new Solution357();
        int i = solution.countNumbersWithUniqueDigits1(2);
        int i1 = solution.countNumbersWithUniqueDigits1(0);
        int i2 = solution.countNumbersWithUniqueDigits1(3);


        System.out.println();
    }

    public int countNumbersWithUniqueDigits(int n) {
        if(n<=0){
            return 1;
        }else if(n>10){
            return 0;
        }else if(n==1){
            return 10;
        }
        return 10*getAnm(9,n-1)+1;
    }

    private int getAnm(int n,int m){
        int sum = 1;
        for (int i = n; i >= n-m+1; i--) {
            sum*=i;
        }
        return sum;
    }

    private int countNumbersWithUniqueDigits1(int n){
        int sum=1;
        for (int i = 1; i <= n; i++) {
            sum+=9*getAnm(9,i-1);
        }
        return sum;
    }

}
