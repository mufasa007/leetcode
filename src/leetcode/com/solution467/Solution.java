package src.leetcode.com.solution467;

import java.util.HashSet;
import java.util.Set;



public class Solution {

    /*
     * 解决思路：todo 待完成
     * 1，查找最大有序数组
     * 2，清理数组
     */

    public int findSubstringInWraproundString(String p) {
        int sum = 0;
        if(p == null || p.length()==0){
            return sum;
        }

        Set<String> stringSet = new HashSet<String>();
        boolean[] bitmap = new boolean[26];
        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < p.length(); i++) {
            if(!stringSet.contains(p.substring(i,i+1))){
                sum++;
                stringSet.add(p.substring(i,i+1));
            }
            if(sf.length()!=0 && getNextChar(sf.charAt(sf.length()-1))!=p.charAt(i)){
                stringSet.add(sf.toString());
                sf = new StringBuffer();
            }
            sf.append(p.charAt(i));
        }
        return 0;
    }

    // 获取下一个字符数据
    private int getNextChar(char c){
        if(c<'z'){
            return c+1;
        }else {
            return 'a';
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println();
    }
}
