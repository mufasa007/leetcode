package src.leetcode.com;

import java.util.ArrayList;
import java.util.List;

public class Solution386 {

    public List<Integer> lexicalOrder(int n) {
        List<Integer> list = new ArrayList<>(n);
        for (int i = 1; i <= 9; i++) {
            recursive(list, i, n);
        }
        return list;
    }

    private void recursive(List<Integer> list, int start, int n) {
        if (start > n) {
            return;
        }
        list.add(start);
        start *= 10;
        for (int i = 0; i < 10; i++) {
            if (start + i > n) {
                break;
            }
            recursive(list, start + i, n);
        }
    }

    public static void main(String[] args) {
        Solution386 solution = new Solution386();
        List<Integer> list = solution.lexicalOrder(2);
        System.out.println();
    }
}
