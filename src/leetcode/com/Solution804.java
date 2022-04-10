package src.leetcode.com;

import java.util.HashSet;
import java.util.Set;

public class Solution804 {
    public static void main(String[] args) {
        Solution804 solution = new Solution804();
        String[] words = {"gin", "zen", "gig", "msg"};
        int i = solution.uniqueMorseRepresentations(words);
        System.out.println();
    }

    private final static String[] decode = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
    public int uniqueMorseRepresentations(String[] words) {
        Set<String> set = new HashSet<>(words.length);

        for (String word : words) {
            StringBuffer sf = new StringBuffer();
            for (char c : word.toCharArray()) {
                sf.append(decode[c-'a']);
            }
            set.add(sf.toString());
        }
        return set.size();
    }

}
