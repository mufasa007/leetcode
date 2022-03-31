package src.leetcode.com;

import java.util.ArrayDeque;
import java.util.Deque;

public class SwordRefer9 {
    public static void main(String[] args) {
        SwordRefer9 solution = new SwordRefer9();

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

