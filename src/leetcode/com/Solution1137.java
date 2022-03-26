package src.leetcode.com;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author 59456
 * @Date 2022/3/26
 * @Descrip
 * @Version 1.0
 */
public class Solution1137 {
    public static void main(String[] args) {
        System.out.println(tribonacci1(4));
        System.out.println(tribonacci1(5));
        System.out.println(tribonacci1(6));
        System.out.println(tribonacci1(25));
    }

    // todo 待完成
    public static int tribonacci1(int n) {
        int l0=0,l1=1,l2=1,l3=2;
        if(n<=1){
            return n;
        }else if(n==2){
            return 1;
        }

        int sum = 1;
        for (int i = 3; i <= n; i++) {
            sum = 2 * sum - l0;
            l0=l1;
            l1=l2;
            l2=l3;
            l3=sum;
        }
        return sum;
    }

    public static int tribonacci(int n) {
        if(n<=1){
            return n;
        }else if(n==2){
            return 1;
        }

        Deque<Integer> queue = new LinkedList<>();
        queue.addLast(0);
        queue.addLast(1);
        queue.addLast(1);

        int sum = 2;
        for (int i = 3; i <= n; i++) {
            queue.addLast(sum);
            int num = queue.pollFirst();
            sum = 2 * sum - num;
        }
        return queue.peekLast();
    }
}
/*
0 1 1 2 4 7 13
 */