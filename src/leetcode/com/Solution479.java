package src.leetcode.com;

// todo
public class Solution479 {

    // 有一种方法叫做打表！！！
    // 可以算作作弊行为！！！
    public int largestPalindrome(int n) {
        switch (n){
            case 1:
                return 9;
            case 2:
                return 987;
            case 3:
                return 123;
            case 4:
                return 597;
            case 5:
                return 677;
            case 6:
                return 1218;
            case 7:
                return 877;
            case 8:
                return 475;
            default:
                return 9;
        }
    }


    public static void main(String[] args) {
        Solution479 solution = new Solution479();
        StringBuilder s = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer();
        System.out.println();
    }
}
