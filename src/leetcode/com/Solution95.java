package src.leetcode.com;

import com.sun.xml.internal.ws.util.StringUtils;

public class Solution95 {

    public static void main(String[] args) {
//        String text1="abcde",text2="ace";
        String text1="abcbdab",text2="bdcaba";
        System.out.println(deal1(text1,text2));
    }

    private static int deal(String text1,String text2) {
        // 自动更换位置
        if(text1.length()<text2.length()){
            String mid = text1;
            text1 = text2;
            text2 = mid;
        }

        //
        return 0;
    }

    private static int deal1(String text1,String text2) {
        int m = text1.length(),n=text2.length();
        int[][] dp = new int[m+1][n+1];

        //
        for (int i = 1; i <=m ; i++) {
            char c1 = text1.charAt(i-1);
            for (int j = 1; j <=n ; j++) {
                char c2 = text2.charAt(j-1);

                dp[i][j]=c1 == c2?dp[i-1][j-1]+1:Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        return dp[m][n];
    }


}
