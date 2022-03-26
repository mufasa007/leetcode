package src.leetcode.com;

public class Solution733 {
    public static void main(String[] args) {
//        int[][] image = {{0,0,0},{0,0,0}};
//        floodFill(image,0,0,2);

        int[][] image = {{0,0,0},{0,1,1}};
        floodFill(image,1,1,1);
        System.out.println();
    }


    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        if(image[sr][sc] == newColor) return image;
        extend(image, sr, sc,  oldColor,  newColor);
        return image;
    }

    private static void extend(int[][] image, int sr, int sc, int oldColor, int newColor){
        if(sr<0 || image.length<=sr || sc<0 || image[0].length<=sc) return;
        if(oldColor==image[sr][sc]){
            image[sr][sc] = newColor;
            extend(image, sr-1, sc,  oldColor,  newColor);
            extend(image, sr, sc-1,  oldColor,  newColor);
            extend(image, sr+1, sc,  oldColor,  newColor);
            extend(image, sr, sc+1,  oldColor,  newColor);
        }
    }

}
