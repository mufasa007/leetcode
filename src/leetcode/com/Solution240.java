package src.leetcode.com;

/**
 * @author
 * @date 2022/3/25
 * @descript 1，暴力；2，二分查找；3，Z字型搜索
 * @since V1.0.0
 */
public class Solution240 {
  public static void main(String[] args) {
    int[][] matrix = new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
    boolean flag = searchMatrix(matrix,17);
    System.out.println(flag);
  }

//  3，Z字型搜索(顺序不一样！)
  public static boolean searchMatrix(int[][] matrix, int target) {
    int iLen = matrix.length, jLen = matrix[0].length;
    int i = 0, j = jLen - 1;
    while (i < iLen && 0 <= j) {
      int pre = matrix[i][j];
      if (pre == target) {
        return true;
      } else if (pre > target) {
        j--;
      } else if (pre < target) {
        i++;
      }
    }
    return false;
  }
}
