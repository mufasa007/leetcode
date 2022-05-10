package src.leetcode.com.solution509;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * @author zjk
 * @date 2022/3/22
 * @descript
 * @since V1.0.0
 */
public class Solution509 {

  DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public static void main(String[] args) {
    int n = 30;

    long l = System.nanoTime();
    System.out.println(fib(n));
    long l1 = System.nanoTime();
    System.out.println(l1-l);

    System.out.println(fib1(n));
    long l2 = System.nanoTime();
    System.out.println(l2-l1);

    System.out.println(fib2(n));
    long l3 = System.nanoTime();
    System.out.println(l3-l2);
  }

  // 记忆背包（一维DP）
  static HashMap<Integer, Integer> result = new HashMap<>();

  public static int fib1(int n) {
    if (n <= 1) {
      result.put(n, n);
      return n;
    }

    if (result.containsKey(n)) {
      return result.get(n);
    } else {
      result.put(n, fib1(n - 1) + fib1(n - 2));
      return result.get(n);
    }
  }

  // 双缓存，速度最快
  public static int fib2(int n) {
    if (n <= 1) {
      return n;
    }

    int[] mide =new int[3];
    mide[1] = 1;
    for (int i = 2; i <= n; i++) {
      mide[2] = mide[0] + mide[1];
      mide[i%2] = mide[2];
    }
    return mide[2];
  }

  // 暴力递归求解
  public static int fib(int n) {
    if(n<=1 ){
      return n;
    }
    return fib(n-1) + fib(n-2);
  }

}

//斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
//
//
//F(0) = 0，F(1) = 1
//F(n) = F(n - 1) + F(n - 2)，其中 n > 1
//
//
// 给定 n ，请计算 F(n) 。
//
//
//
// 示例 1：
//
//
//输入：n = 2
//输出：1
//解释：F(2) = F(1) + F(0) = 1 + 0 = 1
//
//
// 示例 2：
//
//
//输入：n = 3
//输出：2
//解释：F(3) = F(2) + F(1) = 1 + 1 = 2
//
//
// 示例 3：
//
//
//输入：n = 4
//输出：3
//解释：F(4) = F(3) + F(2) = 2 + 1 = 3
//
//
//
//
// 提示：
//
//
// 0 <= n <= 30
//
// Related Topics 递归 记忆化搜索 数学 动态规划 👍 415 👎 0