package src.sort.com;

import java.util.HashMap;

/*
计数排序
 */
public class CountSort extends Sort {
    @Override
    void sort(int[] nums) {
        if (nums == null) {
            return;
        }
        int len = nums.length;

        HashMap<Integer, Integer> map = new HashMap<>();
        int max = nums[0];

        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }

            if (num > max) {
                max = num;
            }
        }

        int index = 0;
        for (int i = 0; i <= max; i++) {
            int count = map.get(i) != null ? map.get(i) : 0;
            while (count > 0) {
                nums[index++] = i;
                --count;
            }
        }
    }
}
