package src.leetcode.com;

import java.util.ArrayDeque;
import java.util.Deque;

public class SwordRefer09 {
    public static void main(String[] args) {
        SwordRefer09 solution = new SwordRefer09();

        System.out.println();
    }

    private class CQueue {
        Deque<Integer> inStack,outStack;
        public CQueue() {
            inStack = new ArrayDeque<>();
            outStack = new ArrayDeque<>();
        }

        public void appendTail(int value) {
            inStack.push(value);
        }

        public int deleteHead() {
            if(outStack.isEmpty()){
                if(inStack.isEmpty()){
                    return -1;
                }
                while (!inStack.isEmpty()){
                    outStack.push(inStack.pop());
                }
            }
            return outStack.pop();
        }
    }
}

