package src.leetcode.com;

public class Solution55 {
    public static void main(String[] args) {
        Solution55 solution = new Solution55();

        int i = solution.firstBadVersion(2);

        System.out.println();
    }

    public int firstBadVersion(int n) {
        if(isBadVersion(1)){
            return 1;
        }
        int left = 1, right = n;
        int mide = left + (right - left) / 2;
        while (left < mide && mide < right) {
            if (isBadVersion(mide)) {
                // 是错误版本
                right = mide;

            } else {
                // 不是错误版本
                left = mide;
            }
            mide = left + (right - left) / 2;
        }
        return right;
    }

    boolean isBadVersion(int version) {
        if(version>=1) return true;
        return false;
    }
}
