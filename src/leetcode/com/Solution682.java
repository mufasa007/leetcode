package src.leetcode.com;

import java.util.Deque;
import java.util.LinkedList;

public class Solution682 {
    public static void main(String[] args) {
//        String[] ops = new String[]{"5","2","C","D","+"};
        String[] ops = new String[]{"5","-2","4","C","D","9","+","+"};
        int out = calPoints(ops);
        System.out.println();
    }

    public static int calPoints(String[] ops) {
        int sum = 0;
        Deque<Integer> deque = new LinkedList<>();
        for (String op : ops) {
            if(op.equals("C")){
                deque.removeFirst();
            }else if(op.equals("D")){
                deque.addFirst(deque.peekFirst()*2);
            }else if(op.equals("+")){
                int scoreBefore = deque.pollFirst();
                int scorePre = deque.peekFirst()+ scoreBefore;
                deque.addFirst(scoreBefore);
                deque.addFirst(scorePre);
            }else {
                deque.addFirst(Integer.parseInt(op));
            }
        }

        while (!deque.isEmpty()){
            sum +=deque.pollFirst();
        }

        return sum;
    }

    
}
