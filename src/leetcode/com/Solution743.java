package src.leetcode.com;

import java.util.Arrays;

// todo 后续检查原因
public class Solution743 {
    public static void main(String[] args) {
        Solution743 solution = new Solution743();
//        int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
//        int n = 4, k = 2;

//        int[][] times =  {{1,2,1},{2,3,7},{1,3,4},{2,1,2}};
//        int n = 3, k = 2;

        int[][] times =  {{4,2,76},{1,3,79},{3,1,81},{4,3,30},{2,1,47},{1,5,61},{1,4,99},{3,4,68},{3,5,46},{4,1,6},{5,4,7},{5,3,44},{4,5,19},{2,3,13},{3,2,18},{1,2,0},{5,1,25},{2,5,58},{2,4,77},{5,2,74}};
        int n = 5, k = 3;

        int i = solution.networkDelayTime(times, n, k);
        System.out.println();
    }

    // floyd算法
    public int networkDelayTime(int[][] times, int n, int k) {

        // 数据图初始化
        int[][] grap = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i==j){
                    grap[i][j] = 0;
                }else {
                    grap[i][j] = 200;
                }
            }
        }
        for (int[] time : times) {
            grap[time[0]-1][time[1]-1] = time[2];
        }

        // 这里出现过问题，i应该是新加入的节点
        for (int i = 0; i < n; i++) {
            // 逐个节点进行遍历选择
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < n; l++) {
                    grap[j][l] = Math.min(grap[j][l],grap[j][i]+grap[i][l]);
                }
            }
        }
        Integer max = Arrays.stream(grap[k-1]).max().getAsInt();
        return (max==200?-1:max);
    }
}
