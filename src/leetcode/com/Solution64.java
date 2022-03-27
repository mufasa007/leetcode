package src.leetcode.com;

public class Solution64 {
    public static void main(String[] args) {
        Solution64 solution = new Solution64();
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        int i = solution.minPathSum1(grid);
        System.out.println();
    }

    public int minPathSum1(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0 && j > 0) {
                    dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j],dp[i][j - 1]);
                }else if(i > 0){
                    dp[i][j] = grid[i][j] + dp[i - 1][j];
                }else if(j>0){
                    dp[i][j] = grid[i][j] + dp[i][j - 1];
                }
            }
        }

        return dp[m-1][n-1];
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] bak = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int up = Integer.MAX_VALUE,left = Integer.MAX_VALUE;
                if (i - 1 >= 0) {
                    up= bak[i - 1][j];
                }
                if (j - 1 >= 0) {
                    left= bak[i][j - 1];
                }
                if(i==0&&j==0){
                    bak[i][j] = grid[i][j];
                    continue;
                }
                bak[i][j] = grid[i][j] + Math.min(up,left);
            }
        }

        return bak[m-1][n-1];
    }
}
