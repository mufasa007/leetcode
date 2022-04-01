package src.leetcode.com;

import java.util.*;

public class Solution954 {
    public static void main(String[] args) {
        Solution954 solution = new Solution954();
//        int[] arr = {1,2,4,16,8,4};
        int[] arr = {2,1,2,1,1,1,2,2};
        boolean b = solution.canReorderDoubled(arr);

        System.out.println();
    }

    // 官方思路
    public boolean canReorderDoubled1(int[] arr) {
        HashMap<Integer, Integer> cnt = new HashMap<>();
        for (int x : arr) {
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
        }
        if (cnt.getOrDefault(0, 0) % 2 != 0) {
            return false;
        }

        List<Integer> vals = new ArrayList<Integer>();
        for (int x : cnt.keySet()) {
            vals.add(x);
        }
        Collections.sort(vals, (a, b) -> Math.abs(a) - Math.abs(b));


        for (int x : vals) {
            if (cnt.getOrDefault(2 * x, 0) < cnt.get(x)) { // 无法找到足够的 2x 与 x 配对
                return false;
            }
            cnt.put(2 * x, cnt.getOrDefault(2 * x, 0) - cnt.get(x));
        }
        return true;
    }


    // 我自己的思路
    public boolean canReorderDoubled(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>(arr.length * 2);
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> {
            return a - b;
        });

        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
                queue.add(arr[i]);
            }
        }

        //
        while (map.size() > 0 && !queue.isEmpty()) {

            int curr = queue.peek();
            int countCurr;
            if (!map.containsKey(curr) || map.get(curr) <= 0) {
                queue.poll();
                continue;
            } else {
                countCurr = map.get(curr);
            }
            int next = 0;
            int countNext = 0;

            // 判断是否有值
            if (map.containsKey(curr * 2)) {// 小值~大值
                next = curr * 2;
            } else if (map.containsKey(curr / 2) && curr % 2 == 0) { // 大值~小值
                next = curr / 2;
            } else {
                return false;
            }

            // 判断是否存在可减操作
            countNext = map.get(next);

            //
            if (countCurr > 1) {
                countCurr--;
                map.put(curr, countCurr);
            } else if (countCurr == 1) {
                queue.poll();
                map.remove(curr);
            }

            if (countNext > 1) {
                countNext--;
                map.put(next, countNext);
            } else if (countNext == 1) {
                map.remove(next);
            }
        }
        return true;
    }
}
