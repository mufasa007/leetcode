package src.leetcode.com;

public class Solution744 {
    public static void main(String[] args) {
        Solution744 solution = new Solution744();
        char[] letters = {'c', 'f', 'j'};
        char target = 'j';
        char c = solution.nextGreatestLetter(letters, target);
        char c1 = solution.nextGreatestLetter1(letters, target);
        System.out.println();
    }

    // 暴力解法
    public char nextGreatestLetter(char[] letters, char target) {
        for (char letter : letters) {
            if (letter > target) {
                return letter;
            }
        }
        return letters[0];
    }

    public char nextGreatestLetter1(char[] letters, char target) {
        int len = letters.length;
        if(letters[len-1]<=target){
            return letters[0];
        }

        int l = 0 ,r = len-1;
        int mide = 0;
        while (l<=r){
            mide = l +((r-l)>>1);
            if(letters[mide]<=target){
                l = mide+1;
                mide = l;
            }else {
                r = mide-1;
            }
        }
        return letters[mide];
    }
}
