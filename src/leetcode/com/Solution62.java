package src.leetcode.com;

public class Solution62 {
    public static void main(String[] args) {
        Solution62 solution = new Solution62();
        int m = 3, n = 7;
        int out = solution.uniquePaths(m,n);
        int out1 = solution.uniquePaths(3,3);
        System.out.println();
    }

    public int uniquePaths(int m, int n) {
        int[][] bak = new int[m][n];
        bak[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int pre = 0;
                if (i - 1 >= 0) {
                    pre += bak[i - 1][j];
                }
                if (j - 1 >= 0) {
                    pre += bak[i][j - 1];
                }
                if(i==0&&j==0){
                    continue;
                }
                bak[i][j] = pre;
            }
        }

        return bak[m-1][n-1];
    }
}
