package src.leetcode.com;

public class Solution796 {
    public static void main(String[] args) {
        Solution796 solution = new Solution796();
        String s = "abcde", goal = "cdeab";
        boolean b = solution.rotateString1(s, goal);
        boolean b2 = solution.rotateString2(s, goal);
        System.out.println();
    }

    // 官方题解，直接匹配
    public boolean rotateString1(String s, String goal) {
        return (s.length() == goal.length()) && (s + s).contains(goal);
    }

    public boolean rotateString2(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            boolean flag = true;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt((i + j) % s.length()) != goal.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }


    // 题目意思理解错误！它这个是整体旋转【本解法只能用于替换】
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }

        int[] countS = new int[26];
        int[] countGoal = new int[26];
        for (int i = 0; i < s.length(); i++) {
            countS[s.charAt(i) - 'a']++;
            countGoal[goal.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (countS[i] != countGoal[i]) {
                return false;
            }
        }
        return true;
    }
}
