package src.enterprise.com.leetcode20220416;

public class Solution1 {

    // 模拟法
    public int giveGem(int[] gem, int[][] operations) {
        for (int[] operation : operations) {
            int x = operation[0];
            int y = operation[1];

            int num = gem[x]/2;
            gem[x] -= num;
            gem[y] += num;
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i : gem) {
            if(i>max) max = i;
            if(i<min) min = i;
        }

        return max-min;
    }

    public static void main(String[] args) {
        Solution1 solution = new Solution1();

        System.out.println(solution.giveGem(new int[]{3, 1, 2}, new int[][]{{0, 2}, {2, 1}, {2, 0}}));

        System.out.println(solution.giveGem(new int[]{100,0,50,100}, new int[][]{{0,2},{0,1},{3,0},{3,0}}));

        System.out.println(solution.giveGem(new int[]{0,0,0,0}, new int[][]{{1,2},{3,1},{1,2}}));

        System.out.println();
    }
}
