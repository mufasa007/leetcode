package src.leetcode.com;

import java.util.HashSet;
import java.util.Set;

// 滑动窗口
public class Solution3 {
    public static void main(String[] args) {
        String s = "abcabcbb";
        int num = lengthOfLongestSubstring(s);
        System.out.println();
    }

    //
    public static int lengthOfLongestSubstring(String s) {
        int max = 0,len = s.length(),j = 0;
        Set<Character> characterSet = new HashSet<>(len*2);
        for (int i = 0; i < len; i++) {
            while (j<len && !characterSet.contains(s.charAt(j)) ){
                characterSet.add(s.charAt(j));
                j++;
            }
            max = Math.max(max,j-i);
            characterSet.remove(s.charAt(i));
        }
        return max;
    }
}
