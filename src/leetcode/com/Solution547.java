package src.leetcode.com;

import java.util.LinkedList;
import java.util.Queue;

public class Solution547 {

    public static void main(String[] args) {
//        int[][] isConnected = new int[][]{{1,1,0},{1,1,0},{0,0,1}};
        int[][] isConnected = new int[][]{{1, 0, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}};
        System.out.println(findCircleNum(isConnected));
        System.out.println(findCircleNum1(isConnected));
        System.out.println(findCircleNum2(isConnected));
        System.out.println(findCircleNum4(isConnected));
    }


    /*
    我自己想到的深度优先搜索算法
     */
    public static int findCircleNum(int[][] isConnected) {
        int[] cities = new int[0];
        int provices = 0, citiesNum = 0;
        if (isConnected != null && isConnected.length != 0) {
            citiesNum = isConnected.length;
            cities = new int[citiesNum];
        } else {
            return 0;
        }

        // 广度优先搜索
        for (int i = 0; i < citiesNum; i++) {
            // 说明该城市已经查询过，直接跳出
            if (cities[i] == 1) {
                continue;
            }
            provices++;
            recursive(isConnected, cities, i);
        }

        return provices;
    }

    private static void recursive(int[][] isConnected, int[] cities, int i) {
//        if(cities[i] == 1){   // 这部分不能跳出，因为我们要寻找其他的链路
//            return;
//        }
        for (int j = 0; j < cities.length; j++) {
            if (isConnected[i][j] == 1 && cities[j] == 0) {
                cities[j] = 1;
                recursive(isConnected, cities, j);
            }
        }
    }

    /*
    广度优先算法-正确
     */
    public static int findCircleNum2(int[][] isConnected) {
        int[] cities = new int[0];
        int provices = 0, citiesNum = 0;
        if (isConnected != null && isConnected.length != 0) {
            citiesNum = isConnected.length;
            cities = new int[citiesNum];
        } else {
            return 0;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < citiesNum; i++) {
            // 说明该城市已经查询过，直接跳出
            if (cities[i] == 1) {
                continue;
            }

            // 将该城市入队
            queue.offer(i);
            while (!queue.isEmpty()) {

                // 出队并刷新访问数据
                int k = queue.poll();
                for (int j = 0; j < citiesNum; j++) {
                    if (isConnected[k][j] == 1 && cities[j] == 0) {
                        queue.offer(j);
                        cities[j] = 1;
                    }
                }
            }
            provices++;

        }
        return provices;
    }


    /*
    深度优先搜索
    时间复杂度O(N^2)
    空间复杂度O(N)
     */
    public static int findCircleNum1(int[][] isConnected) {
        int cities = isConnected.length;
        boolean[] visited = new boolean[cities];
        int provinces = 0;
        for (int i = 0; i < cities; i++) {
            if (!visited[i]) {

                dfs(i, cities, visited, isConnected);
                provinces++;
            }
        }

        return provinces;
    }

    private static void dfs(int i, int cities, boolean[] visited, int[][] isConnected) {
        for (int j = 0; j < cities; j++) {
            if (isConnected[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(j, cities, visited, isConnected);
            }
        }
    }


    /*
    并查集 mergeFind 路径压缩
     */
    public static int findCircleNum4(int[][] isConnected) {
        int[] head = new int[0];
        int[] level = new int[0];
        int provices = 0, citiesNum = 0;
        if (isConnected != null && isConnected.length != 0) {
            citiesNum = isConnected.length;
            head = new int[citiesNum];
            level = new int[citiesNum];
        } else {
            return 0;
        }

        for (int i = 0; i < citiesNum; i++) {
            for (int j = i+1; j < citiesNum; j++) {
                if(isConnected[i][j]==1){
                    merge(i,j,head,level);
                }
            }
            provices += (head[i] == 0 ? 1 : 0);
        }
        return provices;
    }

    static void merge(int x, int y, int[] head, int[] level) {
        int i = find(x, head);
        int j = find(y, head);

        // 根节点是同一个
        if (1 == j) {
            return;
        }

        if (level[i] <= level[j]) {
            head[i] = j;
        } else {
            head[j] = i;
        }
        if (level[i]==level[j]){
            level[i]++;
            level[j]++;
        }
    }

    private static int find(int x,int[] head){
        if(head[x]==x){
            return x;
        }
        head[x] = find(head[x],head);
        return head[x];
    }
}
