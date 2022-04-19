package src.leetcode.com;

import java.util.*;

public class Solution821 {

    // 解法1是记录index数值的
    public int[] shortestToChar(String s, char c) {
        int len = s.length();
        List<Integer> indexList = new ArrayList<>();
        indexList.add((int) (-1 * Math.pow(10, 5)));
        int[] ret = new int[len];
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == c) {
                indexList.add(i);
            }
        }
        indexList.add((int) (Math.pow(10, 5)));

        int indexCur = 0;
        for (int i = 0; i < len; i++) {
            if (i > indexList.get(indexCur + 1)) {
                indexCur++;
            }
            ret[i] = Math.min(i - indexList.get(indexCur), indexList.get(indexCur + 1) - i);
        }
        return ret;
    }

    // 官方解法：两次~不同方向的遍历
    public int[] shortestToChar1(String s, char c) {
        int len = s.length();
        int[] ret = new int[len];
        int maxConstance = (int) Math.pow(10, 5);

        // 从左往右遍历
        int index = 0;
        int count = maxConstance;
        while (index < len) {
            if (s.charAt(index) == c) {
                count = 0;
            } else {
                count++;
            }
            ret[index] = count;
            index++;
        }

        // 从右往左遍历
        index = len - 1;
        count = maxConstance;
        while (index >= 0) {
            if (s.charAt(index) == c) {
                count = 0;
            } else {
                count++;
            }
            ret[index] = Math.min(count, ret[index]);
            index--;
        }

        return ret;
    }

    // BFS的解法 性能最差
    public int[] shortestToChar2(String s, char c) {
        int len = s.length();
        int[] ret = new int[len];
        Arrays.fill(ret,-1);
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == c) {
                queue.add(i);
                ret[i]=0;
            }
        }

        while (!queue.isEmpty()){
            int cur = queue.poll();
            int left = cur-1;
            int right = cur+1;
            if(left>=0 && ret[left]==-1){
                ret[left]=ret[cur]+1;
                queue.add(left);
            }
            if(right<len && ret[right]==-1){
                ret[right]=ret[cur]+1;
                queue.add(right);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Solution821 solution = new Solution821();
        String s = "loveleetcode";
        char c = 'e';
        int[] ints = solution.shortestToChar2(s, c);
        System.out.println();
    }
}
