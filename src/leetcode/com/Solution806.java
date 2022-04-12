package src.leetcode.com;

public class Solution806 {
    public static void main(String[] args) {
        Solution806 solution = new Solution806();
        int[] widths = new int[]{10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};
        String s = "abcdefghijklmnopqrstuvwxyz";
        int[] ints = solution.numberOfLines(widths, s);
        System.out.println();
    }

    public int[] numberOfLines(int[] widths, String s) {
        int level = 1;
        int curr = 0;
        for (char c : s.toCharArray()) {
            int lenCur = widths[c-'a'];
            if(curr+lenCur>100){
                curr = lenCur;
                level++;
            }else {
                curr += lenCur;
            }
        }
        return new int[]{level,curr};
    }
}
