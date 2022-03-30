package src.leetcode.com;

import java.util.*;

public class Solution1606 {
    public static void main(String[] args) {
        Solution1606 solution = new Solution1606();

//        int k = 3;int[] arrival = {1,2,3,4};int[] load = {1,2,1,2};
//        int k = 3;
//        int[] arrival = {1, 2, 3};
//        int[] load = {10, 12, 11};

        int k = 3;
        int[] arrival = {1, 2, 3,4,5};
        int[] load = {5,2,3,3,3};

        solution.busiestServers(k, arrival, load);
        System.out.println();
    }

    // todo 3天后重新自己写一遍
    public List<Integer> busiestServers1(int k, int[] arrival, int[] load) {

        // 空闲服务器编号
        TreeSet<Integer> available = new TreeSet<Integer>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        // 将正在处理请求的服务器的处理结束时间和编号都放入一个优先队列busy 中，优先队列满足队首的服务器的处理结束时间最小
        PriorityQueue<int[]> busy = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);

        // requests 记录对应服务器处理的请求数目
        int[] requests = new int[k];

        for (int i = 0; i < arrival.length; i++) {
            // 1，先检查是否有任务运行完毕，
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                available.add(busy.poll()[1]);
            }

            // 2，如果没有可用的服务器直接跳到下一个任务，舍弃本次任务
            if (available.isEmpty()) {
                continue;
            }

            // 3，查找available中大于等于 i mod k的第一个元素，如果查找成功，那么将它作为处理请求的服务器
            Integer p = available.ceiling(i % k);
            // 否则将available中编号最小的服务器作为处理请求的服务器
            if (p == null) {
                p = available.first();
            }
            requests[p]++;

            // 4，重置运行中的队列信息
            busy.offer(new int[]{arrival[i] + load[i], p});
            available.remove(p);
        }
        int maxRequest = Arrays.stream(requests).max().getAsInt();
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < k; i++) {
            if (requests[i] == maxRequest) {
                ret.add(i);
            }
        }
        return ret;
    }



    // 可以完成任务，但是超时了
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        ArrayList<Integer> outList = new ArrayList<>();
        List<ServerForTask> serverForTaskList = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            serverForTaskList.add(new ServerForTask());
        }

        int taskNum = arrival.length;
        int timePre = 0, timeConsum = 0;
        for (int i = 0; i < taskNum; i++) {
            int index = i % k;
            timePre = arrival[i];
            timeConsum = load[i];
            int loopNum = 0;
            while (loopNum < k) {
                if (serverForTaskList.get((index + loopNum) % k).runTask(timePre, timeConsum)) {
                    break;
                }
                loopNum++;
            }
        }

        int max = 0;
        for (int i = 0; i < k; i++) {
            if (serverForTaskList.get(i).count > max) {
                max = serverForTaskList.get(i).count;
                outList = new ArrayList<>();
                outList.add(i);
            }else if(serverForTaskList.get(i).count == max){
                outList.add(i);
            }
        }
        return outList;
    }

    private class ServerForTask {
        public boolean state = false;// 是否运行中
        public int count = 0;
        public int timeExpire;// 需要使用绝对时间进行判断

        public ServerForTask() {
        }

        public boolean runTask(int timePre, int timeConsum) {
            if (state) { // 已经有任务运行
                if (timePre < timeExpire) {// 任务还未结束，退出
                    return false;
                } else {// 任务已经结束，直接运行新任务
                    count++;
                    timeExpire = timePre + timeConsum;
                    return true;
                }
            } else { // 当前没有任务，直接开始运行
                count++;
                timeExpire = timePre + timeConsum;
                state = true;
                return true;
            }
        }
    }
}
