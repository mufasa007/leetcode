package src.leetcode.com;

public class Solution557 {
    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";
        System.out.println(reverseWords(s));
    }

    public static String reverseWords(String s) {
        String[] s1 = s.split(" ");
        StringBuffer stringBuffer = new StringBuffer();
        for (String s2 : s1) {
            stringBuffer.append(reverseString(s2.toCharArray())).append(" ");
        }
        return stringBuffer.deleteCharAt(stringBuffer.length()-1).toString();
    }

    public static String reverseString(char[] sChild) {
        int i = 0, j = sChild.length - 1;
        while (i < j) {
            char c = sChild[i];
            sChild[i++] = sChild[j];
            sChild[j--] = c;
        }
        return new String(sChild);
    }

}
