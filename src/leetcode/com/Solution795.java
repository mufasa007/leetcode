package src.leetcode.com;

/**
 * 遍历+斐波那契数列
 */
public class Solution795 {
    public static void main(String[] args) {
        Solution795 solution = new Solution795();

        System.out.println();
    }

    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        return count(A, R) - count(A, L-1);
    }

    public int count(int[] A, int bound) {
        int ans = 0, cur = 0;
        for (int x: A) {
            cur = x <= bound ? cur + 1 : 0;
            ans += cur;
        }
        return ans;
    }

}
