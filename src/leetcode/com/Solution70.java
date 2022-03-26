package src.leetcode.com;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Solution70 {
    public static void main(String[] args) {
        int result1 = climbStairs(10);
        int result2 = climbStairs(15);

        int result11 = climbStairs1(10);
        int result22 = climbStairs1(15);

        int result111 = climbStairs2(10);
        int result222 = climbStairs2(15);

        System.out.println();
    }

    // 直接往上计算，感觉和斐波那契数列一样
    public static int climbStairs2(int n) {
        if(n<=3){
            return n;
        }

        int[] sum = {2,3};
        for (int i = 4; i <= n; i++) {
            sum[i%2] = sum[0]+sum[1];
        }
        return sum[n%2];
    }


    // 使用map记录之前的数据
    public static int climbStairs1(int n) {
        if(n<=3){
            return n;
        }
        if(map.containsKey(n)) return map.get(n);
        map.put(n,climbStairs(n-1)+climbStairs(n-2));
        return map.get(n);
    }
    static Map<Integer,Integer> map = new HashMap<>();

    // 暴力
    public static int climbStairs(int n) {
        if(n<=3){
            return n;
        }

        return climbStairs(n-1)+climbStairs(n-2);
    }


}
