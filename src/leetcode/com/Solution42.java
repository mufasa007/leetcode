package src.leetcode.com;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution42 {

    public static void main(String[] args) {

    }

    /*
    解题思路：
    1，暴力
    2，单调栈
     */

    public int deal(int[] height) {
        Stack<Pillar> stack = new Stack<>();
        int waterCount = 0;

        for (int i = 0; i < height.length; i++) {
            Pillar rightPillar = new Pillar(i, height[i]);

            while (!stack.isEmpty() && rightPillar.height > stack.peek().height) {
                Pillar bottom = stack.pop();
                while (bottom.height == stack.peek().height) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    Pillar leftPillar = stack.peek();
                    waterCount += calculateWater(leftPillar, bottom, rightPillar);
                }
            }
            stack.push(rightPillar);
        }
        return waterCount;
    }

    private int calculateWater(Pillar leftPillar, Pillar bottom, Pillar rightPillar) {
        int width = rightPillar.index - leftPillar.index;
        int height = Math.min(rightPillar.height, leftPillar.height);
        return width * height;
    }
}

class Pillar {
    int index;
    int height;

    Pillar(int index, int height) {
        this.index = index;
        this.height = height;
    }
}