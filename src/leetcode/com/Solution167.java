package src.leetcode.com;

public class Solution167 {
  public static void main(String[] args) {
    int[] numbers = new int[]{2, 7, 11, 15};
    int[] out = twoSum(numbers, 9);
    System.out.println();
  }

  public static int[] twoSum(int[] numbers, int target) {
    int len = numbers.length;
    for (int i = 0, j = len - 1; i < j; ) {
      int sum = numbers[i] + numbers[j];
      if (sum == target) {
        // bingo
        return new int[]{i + 1, j + 1};
      } else if (sum < target) {
        // 合数小于目标值
        i++;
      } else {
        // 合数大于目标值
        j--;
      }
    }
    return new int[]{};
  }
}
