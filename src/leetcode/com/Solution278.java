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

    public boolean isBadVersion(int version){
        if(version<1702766719){
            return false;
        }else {
            return true;
        }
    }
}
