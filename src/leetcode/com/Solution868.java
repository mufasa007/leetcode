package src.leetcode.com;

public class Solution868 {

    public int binaryGap(int n) {
        int last = -1, ans = 0;
        for (int i = 0; n != 0; ++i) {
            if ((n & 1) == 1) {
                if (last != -1) {
                    ans = Math.max(ans, i - last);
                }
                last = i;
            }
            n >>= 1;
        }
        return ans;
    }




    public static void main(String[] args) {
        Solution868 solution = new Solution868();

        System.out.println();
    }
}
