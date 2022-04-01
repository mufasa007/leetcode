package src.leetcode.com;

public class Solution461 {
    public static void main(String[] args) {
        Solution461 solution = new Solution461();

        System.out.println();
    }

    public int hammingDistance1(int x, int y) {
        x = x^y;
        y = 0;
        while (x!=0){
            y+=x%2;
            x /=2;
        }
        return y;
    }


    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x^y);
    }
}
