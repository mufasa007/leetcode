package src.leetcode.com;

/**
 * @Author 59456
 * @Date 2022/3/26
 * @Descrip
 * @Version 1.0
 */
public class Solution695 {
    public static void main(String[] args) {
        int[][] grid = {{0,0,1,0,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,1,1,0,1,0,0,0,0,0,0,0,0},{0,1,0,0,1,1,0,0,1,0,1,0,0},{0,1,0,0,1,1,0,0,1,1,1,0,0},{0,0,0,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,0,0,0,0,0,0,1,1,0,0,0,0}};
        int size = maxAreaOfIsland(grid);
        System.out.println();
    }

    // 深度优先搜索 同时也使用了bitmap
    public static int maxAreaOfIsland(int[][] grid) {
        int lLen =grid.length,rLen=grid[0].length;

        bitmap = new byte[lLen][rLen];
        int maxPre = 0;
        for (int i = 0; i < lLen; i++) {
            for (int j = 0; j < rLen; j++) {
                max = 0;
                if(bitmap[i][j]==1) {
                    continue;
                }
                if(grid[i][j]==1){
                    dpExtend(grid, i, j, 1);
                    maxPre = Math.max(max,maxPre);
                }
            }
        }
        return maxPre;
    }

    private static int max = 0;
    private static byte[][] bitmap;
    private static void dpExtend(int[][] image, int sr, int sc, int oldColor){
        if(sr<0 || image.length<=sr || sc<0 || image[0].length<=sc) {
            return;
        }
        if(oldColor==image[sr][sc] && bitmap[sr][sc]==0){
            bitmap[sr][sc] = 1;
            max++;
            dpExtend(image, sr-1, sc,  oldColor);
            dpExtend(image, sr, sc-1,  oldColor);
            dpExtend(image, sr+1, sc,  oldColor);
            dpExtend(image, sr, sc+1,  oldColor);
        }
    }
}
