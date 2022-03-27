package src.leetcode.com;

public class Solution63 {
    public static void main(String[] args) {
        Solution63 solution = new Solution63();
        int[][] obstacleGrid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int i = solution.uniquePathsWithObstacles(obstacleGrid);
        System.out.println();
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        if (obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }

        int[][] bak = new int[m][n];
        bak[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int pre = 0;
                if (i - 1 >= 0 && obstacleGrid[i - 1][j] != 1) {
                    pre += bak[i - 1][j];
                }
                if (j - 1 >= 0 && obstacleGrid[i][j - 1] != 1) {
                    pre += bak[i][j - 1];
                }
                if (i == 0 && j == 0) {
                    continue;
                }
                bak[i][j] = pre;
            }
        }

        return bak[m - 1][n - 1];
    }
}
