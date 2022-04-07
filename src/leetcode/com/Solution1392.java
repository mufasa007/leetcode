package src.leetcode.com;

import java.util.ArrayList;
import java.util.List;

// kmp算法
public class Solution1392 {
    public static void main(String[] args) {
        Solution1392 solution = new Solution1392();
//        String s = "level";
//        String s = "ababab";
//        String s = "bba";
//        String s = "a";
        String s = "acccbaaacccbaac";

//        int[] prefixTable = solution.getPrefixTable(s);
//
//        String s1 = solution.longestPrefix(s);

        List<Integer> aac = solution.findTheMatter(s, "aac");

        System.out.println();
    }


    public String longestPrefix(String s) {
        if (s.length() == 1) {
            return "";
        }
        int[] prefixTable = getPrefixTable(s);
        return s.substring(0, prefixTable[s.length() - 1]);
    }


    public List<Integer> findTheMatter(String s, String target) {
        int lenN = s.length();
        int lenM = target.length();

        int[] prefixTable = getPrefixTable(target);
        for (int i = prefixTable.length - 1; i > 0; i--) {
            prefixTable[i] = prefixTable[i - 1];
        }
        prefixTable[0] = -1;

        int i = 0, j = 0;
        List<Integer> ret = new ArrayList<>();
        while (i < lenN) {
            if ((j == lenM - 1) && (s.charAt(i) == target.charAt(j))) {
                ret.add(i - j);
                j = prefixTable[j];
            }
            if (s.charAt(i) == target.charAt(j)) {
                i++;
                j++;
            } else {
                j = prefixTable[j];
                if (j == -1) {
                    i++;
                    j++;
                }
            }
        }
        return ret;
    }

    // kmp前缀和
    public int[] getPrefixTable(String s) {
        int len = s.length();
        int[] prefixTable = new int[len];
        prefixTable[0] = 0;

        int l = 0;
        int r = 1;
        while (r < len) {
            if (s.charAt(r) == s.charAt(l)) {
                l++;
                prefixTable[r] = l;
                r++;
            } else {
                if (l > 0) {
                    l = prefixTable[l - 1];
                } else {
                    prefixTable[r] = 0;
                    r++;
                }
            }
        }
        return prefixTable;
    }
}
