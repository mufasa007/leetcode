package src.leetcode.com;

import java.util.LinkedList;
import java.util.List;

public class Solution54 {
    public static void main(String[] args) {
        Solution54 solution = new Solution54();
//        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        List<Integer> integers = solution.spiralOrder1(matrix);
        System.out.println();
    }

    public List<Integer> spiralOrder1(int[][] matrix) {
        List<Integer> out = new LinkedList<>();
        int top = 0, buttom = matrix.length - 1, left = 0, right = matrix[0].length-1;

        int direction = 0;
        while (top<=buttom && left<=right){
            if(direction==0){
                for (int i = left; i <=right; i++) {
                    out.add(matrix[top][i]);
                }
                top++;
                direction = 1;
            }else if(direction==1){
                for (int i = top; i <=buttom; i++) {
                    out.add(matrix[i][right]);
                }
                right--;
                direction = 2;
            }else if(direction==2){
                for (int i = right; i >=left; i--) {
                    out.add(matrix[buttom][i]);
                }
                buttom--;
                direction = 3;
            }else {
                for (int i = buttom; top<=i ; i--) {
                    out.add(matrix[i][left]);
                }
                left++;
                direction = 0;
            }
        }
        return out;
    }

    // 动态
    public List<Integer> spiralOrder(int[][] matrix) {
        int lenL = matrix.length, lenR = matrix[0].length;
        byte[][] bitmap = new byte[lenL][lenR];
        List<Integer> out = new LinkedList<>();
        moveAndTag(matrix, bitmap, out, 0, 0, 0);
        return out;
    }

    private void moveAndTag(int[][] matrix, byte[][] bitmap, List<Integer> out, int direction, int x, int y) {
        out.add(matrix[x][y]);
        bitmap[x][y] = 1;

        // 处理下一次移动,向右
        if (direction == 0) {
            if (y + 1 < matrix[0].length && bitmap[x][y + 1] == 0) {
                moveAndTag(matrix, bitmap, out, 0, x, y + 1);
            } else if (x + 1 < matrix.length && bitmap[x + 1][y] == 0) {
                moveAndTag(matrix, bitmap, out, 1, x + 1, y);
            }
        }

        // 处理下一次移动,向下
        if (direction == 1) {
            if (x + 1 < matrix.length && bitmap[x + 1][y] == 0) {
                moveAndTag(matrix, bitmap, out, 1, x + 1, y);
            } else if (y - 1 >= 0 && bitmap[x][y - 1] == 0) {
                moveAndTag(matrix, bitmap, out, 2, x, y - 1);
            }
        }

        // 处理下一次移动,向左
        if (direction == 2) {
            if (y - 1 >= 0 && bitmap[x][y - 1] == 0) {
                moveAndTag(matrix, bitmap, out, 2, x, y - 1);
            } else if (x - 1 >= 0 && bitmap[x - 1][y] == 0) {
                moveAndTag(matrix, bitmap, out, 3, x - 1, y);
            }
        }

        // 处理下一次移动,向上
        if (direction == 3) {
            if (x - 1 >= 0 && bitmap[x - 1][y] == 0) {
                moveAndTag(matrix, bitmap, out, 3, x - 1, y);
            } else if (y + 1 < matrix[0].length && bitmap[x][y + 1] == 0) {
                moveAndTag(matrix, bitmap, out, 0, x, y + 1);
            }
        }
    }
}
