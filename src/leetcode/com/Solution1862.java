package src.leetcode.com;

//import javafx.collections.transformation.SortedList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class Solution1862 {
    public static void main(String[] args) {
        Solution1862 solution = new Solution1862();
//        int[] nums = {2,5,9};
//        int[] nums = {1,4,3,2,5};
        int[] nums = {4, 3, 4, 3, 5};
//        int[] nums = {7,7,7,7,7,7,7};
        int i = solution.sumOfFlooredPairs(nums);
        System.out.println();
    }

    // 超时！
    public int sumOfFlooredPairs(int[] nums) {
        HashMap<Integer, Integer> treeMap = new HashMap<>(nums.length);
        List<Integer> list = new ArrayList<>(nums.length);
        for (int num : nums) {
            if (treeMap.containsKey(num)) {
                treeMap.put(num, treeMap.get(num) + 1);
            } else {
                treeMap.put(num, 1);
                list.add(num);
            }
        }
        int listLen = list.size();
        list.sort((a, b) -> a - b);
        int sum = 0;
        for (int i = 0; i < listLen; i++) {
            for (int j = i; j < listLen; j++) {
                sum += treeMap.get(list.get(i)) * treeMap.get(list.get(j)) * Math.floor(list.get(j) / list.get(i));
            }
        }
        return sum % mod;
    }

    private int mod = (int) (Math.pow(10, 9) + 7);


    // fixme 待理解 困难程度
    public int sumOfFlooredPairs1(int[] nums) {
        int N = 100010;
        int MOD = 1000000007;
        int[] s = new int[N];   //记录每个数出现多少次
        for (int x : nums) //记录每个数出现多少次
            s[x]++;
        for (int i = 1; i < N; i++)  //前缀和
            s[i] += s[i - 1];
        long res = 0;
        for (int i = 1; i < N; i++)
            for (int j = 1; j * i < N; j++) {
                int l = j * i, r = Math.min(N - 1, (j + 1) * i - 1);
                int sum = (s[r] - s[l - 1]) * j % MOD;
                res = (res + (long) sum * (s[i] - s[i - 1])) % (long) MOD;
            }
        return (int) res;
    }


}
