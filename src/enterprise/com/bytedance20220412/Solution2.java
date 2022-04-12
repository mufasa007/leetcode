package src.enterprise.com.bytedance20220412;

import java.util.Arrays;

public class Solution2 {


    // 排序后 由大往小寻找
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int len = citations.length;
        int index = len - 1;
        int max = 0;
        while (index >= 0) {
            int cur = Math.min(len - index, citations[index]);
            if (max < cur) {
                max = cur;
            } else if (max > cur) {
                return max;
            }
            index--;
        }
        return max;
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
//        int[] citations = {3,0,6,1,5};
//        int[] citations = {100};
        int[] citations = {4, 4, 0, 0};
        int i = solution.hIndex(citations);

        System.out.println();
    }
}
