package src.leetcode.com;

/**
 * @author
 * @date 2022/3/25
 * @descript
 * @since V1.0.0
 */
public class Solution172 {
  public static void main(String[] args) {
    System.out.println(trailingZeroes(25));
    System.out.println(trailingZeroes(5));
    System.out.println(trailingZeroes(0));
  }

  // 直接利用数学方法求解
  public static int trailingZeroes(int n) {
    int sum = 0;
    while (n / 5 != 0) {
      n /= 5;
      sum += n;
    }
    return sum;
  }
}
