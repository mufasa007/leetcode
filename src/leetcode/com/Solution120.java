package src.leetcode.com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution120 {
    public static void main(String[] args) {
        Solution120 solution = new Solution120();
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3,4));
        triangle.add(Arrays.asList(6,5,7));
        triangle.add(Arrays.asList(4,1,8,3));
        solution.minimumTotal(triangle);
        System.out.println();
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int h = triangle.size();
        int[][] dp = new int[h][h];
        dp[0][0] = triangle.get(0).get(0);

        for (int i = 0; i < h; i++) {
            for (int j = 0; j <= i; j++) {
                if (i > 0 && j > 0 && j<i) { // 比较
                    dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }else if(i > 0 && j > 0 && j==i){ // 使用左边的
                    dp[i][j] = triangle.get(i).get(j) + dp[i][j - 1];
                }else if(i > 0){ // 使用上面的
                    dp[i][j] = triangle.get(i).get(j) + dp[i - 1][j];
                }else if(j>0){ // 使用左边的
                    dp[i][j] = triangle.get(i).get(j) + dp[i][j - 1];
                }
            }
        }

        return Arrays.stream(dp[h-1]).min().getAsInt();
    }
}
