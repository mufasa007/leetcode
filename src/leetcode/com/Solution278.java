package src.leetcode.com;

public class Solution278 {
    public static void main(String[] args) {
        Solution278 solution278 = new Solution278();
        System.out.println(solution278.firstBadVersion(2126753390));

    }

    public int firstBadVersion(int n) {
        int left = 1,right=n;
        int index ;
        while (left < right){
            index = left/2+right/2;
            if(isBadVersion(index)){
                right = index;
            }else {
                left = ++index;
            }
        }
        return left;
    }

    // 2022.04.11 解题版本
    public int firstBadVersion1(int n) {
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

    public boolean isBadVersion(int version){
        if(version<1702766719){
            return false;
        }else {
            return true;
        }
    }
}
