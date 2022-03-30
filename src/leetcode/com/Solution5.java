package src.leetcode.com;

/*
复习记录~
2022.03.30
 */
public class Solution5 {
    public static void main(String[] args) {
        String s = "a";
//        String s = "cccc";
//        String s = "babad";
//        String s = "bb";
        System.out.println(deal2(s));
    }


    /*
    暴力算法
     */
    public static String deal1(String s) {
        boolean flagLine = true;
        boolean flagChar = true;
        int left, right;
        String result = s.substring(0, 1);

        for (int i = 0; i < s.length() - 1; i++) {
            flagLine = true;
            flagChar = true;

            for (int j = 1; j <= Math.min(i + 1, s.length() - i); j++) {
                // 以字符串的间隔为中线
                if (flagLine) {
                    left = i - j + 1;
                    right = i + j;
                    if (right < s.length() && s.charAt(left) == s.charAt(right)) {
                        result = (result.length() > 2 * j ? result : s.substring(left, right + 1));
                    } else {
                        flagLine = false;
                    }
                }

                // 以字符串本身为中线
                if (flagChar) {
                    left = i - j;
                    right = i + j;
                    if (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                        result = (result.length() > 2 * j ? result : s.substring(left, right + 1));
                    } else {
                        flagChar = false;
                    }
                }
            }
        }


        return result;
    }

    /*
    动态规划
     */
    public static String deal2(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }

        int maxLength = 1;
        int startIndex = 0;

        for (int j = 1; j < s.length(); j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                if(dp[i][j] && maxLength<j-i+1){
                    maxLength = j-i+1;
                    startIndex = i;
                }
            }
        }
        return s.substring(startIndex,startIndex+maxLength);
    }

    /*
    直接搜索是不是回文
     */
    private static boolean isRecursive(String s, int left, int right) {
        if (left >= right) {
            return true;
        }
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        } else {
            return isRecursive(s, left + 1, right - 1);
        }
    }

}
