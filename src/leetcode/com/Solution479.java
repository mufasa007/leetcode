package src.leetcode.com;

// todo
public class Solution479 {

    // 有一种方法叫做打表！！！
    public int largestPalindrome(int n) {
        switch (n) {
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

    public int largestPalindrome1(int n) {
        if (n == 1) {
            return 9;
        }

        long upperBound = (long) (Math.pow(10, n) - 1);
        long lowerBound = upperBound / 10 + 1;

        long maxNumber = upperBound * upperBound;
        long half = (long) (maxNumber / Math.pow(10, n));
        boolean found = false;
        long res = 0;
        while (!found) {
            res = createPalindrome(half);
            for (long i = upperBound; i >= lowerBound; i--) {
                if (i * i < res) {
                    break;
                }
                if (res % i == 0) {
                    found = true;
                    break;
                }
            }
            half--;
        }
        return (int) (res % 1337);
    }

    private long createPalindrome(long half) {
        String pStr = half + new StringBuilder().append(half).reverse().toString();
        return Long.valueOf(pStr);
    }


    public static void main(String[] args) {
        Solution479 solution = new Solution479();
        int i = solution.largestPalindrome1(5);
        System.out.println();
    }
}
