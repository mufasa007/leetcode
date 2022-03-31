package src.leetcode.com;

import java.util.*;

public class Solution728 {
    public static void main(String[] args) {
        Solution728 solution = new Solution728();
        List<Integer> integers1 = solution.selfDividingNumbers(1, 22);
        List<Integer> integers2 = solution.selfDividingNumbers(47, 85);
        System.out.println();
    }

    /*
   暴力,就是使用直接暴力
     */
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> outList = new ArrayList<>();
        for (int i = left; i <= right ; i++) {
            if(isTrue(i)){
                outList.add(i);
            }
        }
        return outList;
    }

    private boolean isTrue(int n){
        final int nOrign = n;
        while (n!=0){
            int remainder = n%10;
            if(remainder==0){
                return false;
            }else if(remainder>1 && nOrign%remainder!=0){
                return false;
            }
            n /= 10;
        }
        return true;
    }
}
