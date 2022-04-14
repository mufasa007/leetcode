package src.leetcode.com;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution1672 {

    /*
    * 执行用时：3 ms, 在所有 Java 提交中击败了8.75%的用户
    * 内存消耗：41.3 MB, 在所有 Java 提交中击败了5.00%的用户
    * 效率不行
    * */
    public int maximumWealth(int[][] accounts) {
        Optional<int[]> max1 = Arrays.stream(accounts).max(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Arrays.stream(o1).sum() - Arrays.stream(o2).sum();
            }
        });
        return Arrays.stream(max1.get()).sum();
    }

    public int maximumWealth1(int[][] accounts) {
        final AtomicInteger max = new AtomicInteger(Integer.MIN_VALUE);
        Arrays.stream(accounts).parallel().forEachOrdered(account->{
            int curSum = Arrays.stream(account).sum();
            if(curSum>max.get()){
                max.set(curSum);
            }
        });
        return max.get();
    }

    public static void main(String[] args) {
        Solution1672 solution = new Solution1672();
        int[][] accounts = {{1,5},{7,3},{3,5}};
        int i = solution.maximumWealth(accounts);
        System.out.println(i);
    }
}
