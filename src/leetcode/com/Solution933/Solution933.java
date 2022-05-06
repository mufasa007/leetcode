package src.leetcode.com.Solution933;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class Solution933 {


    public static void main(String[] args) {
        Solution933 solution = new Solution933();
        RecentCounter recentCounter = new RecentCounter();
        System.out.println(recentCounter.ping(1));
        System.out.println(recentCounter.ping(100));
        System.out.println(recentCounter.ping(3001));
        System.out.println(recentCounter.ping(3002));
        System.out.println();
    }
}

/**
 * 直接想到duque双向队列
 * 每次插入一个新值时就检测以前的老数据是否需要删除
 */
class RecentCounter {

    Deque<Integer> queue;

    public RecentCounter() {
        queue = new ArrayDeque<>();
    }

    public int ping(int t) {
        queue.offer(t);
        while (queue.peek() < t - 3000) {
            queue.poll();
        }
        return queue.size();
    }
}