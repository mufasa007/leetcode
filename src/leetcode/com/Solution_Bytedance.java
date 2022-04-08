package src.leetcode.com;

public class Solution_Bytedance {
    public static void main(String[] args) {
        Solution_Bytedance solution = new Solution_Bytedance();
//        System.out.println(solution.deal(0));
//        System.out.println(solution.deal(1));
//        System.out.println(solution.deal(2));
//        System.out.println(solution.deal(3));
//        System.out.println(solution.deal(4));
//        System.out.println(solution.deal(5));
        System.out.println(solution.deal(6));
        System.out.println(solution.deal(10));
        System.out.println();
    }

    public int deal(int target){
        if(target<=1){
            return target;
        }

        int step = gradBack(target);
        int index = gradeSum(step);
        while (index<=target){
            step++;
            index = gradeSum(step);
        }
        index = gradeSum(--step);
        return step + 2*(target-index);
    }

    // 等差数列求和
    private int gradeSum(int n){
        return n*(n+1)/2;
    }

    // 求解出来的值相较于真实大一点
    private int gradBack(int m){
        return (int) Math.sqrt(2*m) - 1;
    }
}

/*
https://www.bilibili.com/video/BV1DF411u7Nw
字节算法笔试题：堵上尊严，一道考验Java面试者智力的题目
 */