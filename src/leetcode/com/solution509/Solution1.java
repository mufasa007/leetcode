package src.leetcode.com.solution509;

import java.util.HashMap;

public class Solution1 {

    // 1,暴力递归求解
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n -  2);
    }

    // 2,缓存历史计算
    private HashMap<Integer, Integer> cache = new HashMap<>();

    public Solution1() {
        cache.put(0, 0);
        cache.put(1, 1);
    }

    public int fib2(int n) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        cache.put(n, fib2(n - 1) + fib2(n - 2));
        return cache.get(n);
    }

    // 3，模拟法：直接模拟计算的过程
    public int fib3(int n) {
        if (n <= 1) {
            return n;
        }
        int n0 = 0, n1 = 1;
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                n0 += n1;
            } else {
                n1 += n0;
            }
        }
        if (n % 2 == 0) {
            return n0;
        } else {
            return n1;
        }
    }

    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        long start = System.currentTimeMillis();

        System.out.println("结果:" + solution.fib3(40));

        long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end - start));

        System.out.println();
    }
}
