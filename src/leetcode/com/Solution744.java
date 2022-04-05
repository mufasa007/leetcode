package src.leetcode.com;

public class Solution744 {
    public static void main(String[] args) {
        Solution744 solution = new Solution744();
        char[] letters = {'c', 'f', 'j'};
        char target = 'a';
        char c = solution.nextGreatestLetter(letters, target);
        System.out.println();
    }

    public char nextGreatestLetter(char[] letters, char target) {
        for (char letter : letters) {
            if (letter > target) {
                return letter;
            }
        }
        return letters[0];
    }
}
