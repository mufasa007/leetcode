package src.leetcode.com;

import java.util.Stack;

public class Solution394 {

    private static int l = 0;
    public String decodeString(String s) {
        StringBuffer sfLeft = new StringBuffer();
        StringBuffer numStr = new StringBuffer();
        int leftCnt = 0;
        // 寻找左边界
        while (l<=s.length()-1){
            char c = s.charAt(l);
            l++;
            if('[' == c){
                // 有[
                leftCnt++;
                String mide = decodeString(s);
                if(numStr.length()>0){
                    for (int i = 0; i < Integer.parseInt(numStr.toString()); i++) {
                        sfLeft.append(mide);
                    }
                }
                break;
            }else if(c<='9' && c>='0'){
                // 是数字
                numStr.append(c);
            }else if (c>='a' && c<='z') {
                // 是字母
                sfLeft.append(c);
            }else if(c==']' && leftCnt-->0){
                return sfLeft.toString();
            }
        }
        return sfLeft.toString();
    }


    public static void main(String[] args) {
        Solution394 solution = new Solution394();
        String str = "3[abc]";
        String s = solution.decodeString(str);
        System.out.println();
    }
}
