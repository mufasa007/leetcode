package src.enterprise.com.bytedance20220414;


import java.util.Arrays;

public class Solution2 {

    // 那个天数只和题目耦合！直接排序从小加到大就可以了
    public int minTime(int[] time, int m) {
        int len = time.length;
        if(len<=m){
            return 0;
        }

        Arrays.sort(time);
        int sum = 0;
        for (int i = 0; i < len-m; i++) {
            sum+=time[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
       int[] time = {3,3,2,3,1};
        int i = solution.minTime(time, 3);
        System.out.println(i);
    }
}
