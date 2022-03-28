package src.leetcode.com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution120 {
    public static void main(String[] args) {
        Solution120 solution = new Solution120();
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3,4));
        triangle.add(Arrays.asList(6,5,7));
        triangle.add(Arrays.asList(4,1,8,3));
        int i = solution.minimumTotal1(triangle);
        System.out.println();
    }

    // 优化一下
    public int minimumTotal1(List<List<Integer>> triangle) {
        int h = triangle.size();
        int[][] dp = new int[h][h];
        dp[0][0] = triangle.get(0).get(0);

        for (int i = 1; i < h; i++) {
            dp[i][0] = dp[i-1][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; j++) {
                dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i - 1][j], dp[i-1][j - 1]);
            }
            dp[i][i] = dp[i-1][i - 1]+triangle.get(i).get(i);
        }
        return Arrays.stream(dp[h-1]).min().getAsInt();
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int h = triangle.size();
        int[][] dp = new int[h][h];
        dp[0][0] = triangle.get(0).get(0);

        for (int i = 0; i < h; i++) {
            for (int j = 0; j <= i; j++) {
                if (i > 0 && j > 0 && j<i) { // 比较
                    dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i - 1][j], dp[i-1][j - 1]);
                }else if(i > 0 && j > 0 && j==i){ // 使用左边的
                    dp[i][j] = triangle.get(i).get(j) + dp[i-1][j - 1];
                }else if(i > 0){ // 使用上面的
                    dp[i][j] = triangle.get(i).get(j) + dp[i - 1][j];
                }
            }
        }

        return Arrays.stream(dp[h-1]).min().getAsInt();
    }
}
