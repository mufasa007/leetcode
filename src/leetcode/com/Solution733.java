package src.leetcode.com;

import java.util.LinkedList;
import java.util.Queue;

public class Solution733 {
    public static void main(String[] args) {
//        int[][] image = {{0,0,0},{0,0,0}};
//        floodFill(image,0,0,2);

//        int[][] image = {{1,1,1},{1,1,0},{1,0,1}};
//        floodFill1(image,1,1,2);

//        int[][] image = {{0,0,0},{0,1,1}};
//        floodFill1(image,1,1,1);

        int[][] image = {{0, 0, 0}, {0, 1, 0}};
        floodFill1(image, 1, 1, 2);
        System.out.println();
    }


    // 深度优先搜索
    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        if(image[sr][sc] == newColor) return image;
        dpExtend(image, sr, sc,  oldColor,  newColor);
        return image;
    }

    private static void dpExtend(int[][] image, int sr, int sc, int oldColor, int newColor){
        if(sr<0 || image.length<=sr || sc<0 || image[0].length<=sc) return;
        if(oldColor==image[sr][sc]){
            image[sr][sc] = newColor;
            dpExtend(image, sr-1, sc,  oldColor,  newColor);
            dpExtend(image, sr, sc-1,  oldColor,  newColor);
            dpExtend(image, sr+1, sc,  oldColor,  newColor);
            dpExtend(image, sr, sc+1,  oldColor,  newColor);
        }
    }

    // 广度优先搜索
    public static int[][] floodFill1(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        if(image[sr][sc] == newColor) return image;

        image[sr][sc] = newColor;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sr,sc});

        while (!queue.isEmpty()){
            int[] index = queue.poll();
            // 向外一圈圈扩展
            if(index[0]-1>=0 && image[index[0]-1][index[1]]==oldColor){
                image[index[0]-1][index[1]]=newColor;
                queue.add(new int[]{index[0]-1,index[1]});
            }
            if(index[1]-1>=0 && image[index[0]][index[1]-1]==oldColor){
                image[index[0]][index[1]-1]=newColor;
                queue.add(new int[]{index[0],index[1]-1});
            }
            if(index[0]+1<image.length && image[index[0]+1][index[1]]==oldColor){
                image[index[0]+1][index[1]] = newColor;
                queue.add(new int[]{index[0]+1,index[1]});
            }
            if(index[1]+1<image[0].length && image[index[0]][index[1]+1]==oldColor){
                image[index[0]][index[1]+1] = newColor;
                queue.add(new int[]{index[0],index[1]+1});
            }
        }
        return image;
    }



}
