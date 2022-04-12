package src.leetcode.com;

import java.util.ArrayDeque;
import java.util.Deque;

public class SwordRefer44 {
    public static void main(String[] args) {
        SwordRefer44 solution = new SwordRefer44();
        int index = solution.getIndex(15);
        System.out.println();
    }

//    public int findNthDigit(int n) {
//
//    }

    public int getIndex(int num){
        int currIndex = 0;
        int digit = 1;
        while (num/10!=0){
            int currUnit = num%10;
            currIndex+= currUnit*digit;
            digit++;

            num/=10;
        }
        currIndex+= num*Math.pow(10,digit);
        return currIndex;
    }
}

