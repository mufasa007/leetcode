package src.enterprise.com.bytedance2022041101;

public class Solution1 {
    public static void main(String[] args) {
        Solution1 solution = new Solution1();

        int[] ints = {1, 2, 3, 4, 4};
        int deal = solution.deal(ints);
        System.out.println();
    }


    public int deal(int[] ints) {
        int n = ints.length - 1;
        int cur = 0;
        for (int i = 0; i < n; i++) {
            cur ^= (i + 1);
            cur ^= ints[i];
        }
        cur ^= ints[n];
        return cur;
    }
}
