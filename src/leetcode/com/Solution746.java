package src.leetcode.com;

import java.util.Arrays;

public class Solution746 {
    public static void main(String[] args) {
        Solution746 solution = new Solution746();
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
//        int[] cost = {10,15,20};
        int costMin = solution.minCostClimbingStairs1(cost);
        System.out.println();
    }

    public int minCostClimbingStairs(int[] cost) {
        int[] sum = new int[]{cost[0], cost[1]};

        for (int i = 2; i < cost.length; i++) {
            sum[i % 2] = cost[i] + Math.min(sum[0], sum[1]);
        }
        return Arrays.stream(sum).min().getAsInt();
    }


    public int minCostClimbingStairs1(int[] cost) {
        int prev = 0, curr = 0, next;
        for (int i = 2; i <= cost.length; i++) {
            next = Math.min(prev + cost[i - 2], curr + cost[i - 1]);
            prev = curr;
            curr = next;
        }
        return curr;
    }
}
