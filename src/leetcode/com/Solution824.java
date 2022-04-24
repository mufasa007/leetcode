package src.leetcode.com;

import java.util.HashSet;
import java.util.Set;

public class Solution824 {

    private static Set<Character> vowels = new HashSet<Character>() {{
        add('a');
        add('e');
        add('i');
        add('o');
        add('u');
        add('A');
        add('E');
        add('I');
        add('O');
        add('U');
    }};

    // set
    public String toGoatLatin(String sentence) {
        String[] s = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            if(vowels.contains(s[i].charAt(0))){
                // 元音字母
                sb.append(s[i]).append("ma");
            }else {
                // 非元音字母
                sb.append(s[i].substring(1)).append(s[i].charAt(0)).append("ma");
            }
            // 索引
            for (int j = 0; j <= i; j++) {
                sb.append("a");
            }
            sb.append(" ");
        }
        if(sb.length()>0){
            sb.replace(sb.length()-1,sb.length(),"");
        }
        return sb.toString();
    }

    // 正则匹配
    public String toGoatLatin1(String sentence) {
        String[] s = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            String startChar = s[i].substring(0,1);
            if(startChar.matches("[aAeEiIoOuU]")){
                // 元音字母
                sb.append(s[i]).append("ma");
            }else {
                // 非元音字母
                sb.append(s[i].substring(1)).append(s[i].charAt(0)).append("ma");
            }
            // 索引
            for (int j = 0; j <= i; j++) {
                sb.append("a");
            }
            sb.append(" ");
        }
        if(sb.length()>0){
            sb.replace(sb.length()-1,sb.length(),"");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String sentence = "I speak Goat Latin";


//        System.out.println(sentence.startsWith("[iI]"));

        Solution824 solution = new Solution824();
//        System.out.println(solution.toGoatLatin("I speak Goat Latin"));
        System.out.println(solution.toGoatLatin("The quick brown fox jumped over the lazy dog"));
        System.out.println();


    }
}
