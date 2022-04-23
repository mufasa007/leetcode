package src.leetcode.com;

import java.util.*;
import java.util.stream.Collectors;

public class Solution396 {

    public int maxRotateFunction(int[] nums) {
        LinkedList<Integer> curList = new LinkedList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            curList.add(nums[i]);
        }
        LinkedList<Integer> maxList = (LinkedList<Integer>) curList.clone();

        int len = nums.length;
        for (int i = 0; i < len; i++) {
            ListIterator<Integer> curIt = curList.listIterator();
            ListIterator<Integer> maxIt = maxList.listIterator();
            for (int j = 0; j < len; j++) {
                int curNum = curIt.next();
                int maxNum = maxIt.next();
                if (curNum > maxNum) {
                    maxList = (LinkedList<Integer>) curList.clone();
                    break;
                } else if (curNum < maxNum) {
                    break;
                }
            }
            curList.addLast(curList.pollFirst());
        }

        // 输出值
        int sum = 0;
        ListIterator<Integer> maxIt = curList.listIterator();
        for (int i = nums.length - 1; i >= 0; i--) {
            sum += maxIt.next() * i;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 6};

        Solution396 solution = new Solution396();
        System.out.println(solution.maxRotateFunction(nums));
        System.out.println();
    }

}
