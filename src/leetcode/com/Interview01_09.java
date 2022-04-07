package src.leetcode.com;

// 本质与LeetCode796一样
public class Interview01_09 {
    public static void main(String[] args) {
        Interview01_09 solution = new Interview01_09();

        System.out.println();
    }
    public boolean isFlipedString(String s1, String s2) {
        return (s1.length() == s2.length()) && (s1+ s1).contains(s2);
    }
}
