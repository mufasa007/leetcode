package src.leetcode.com;

import java.util.*;

public class Solution380 {

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();

        System.out.println(randomizedSet.remove(0));
        System.out.println(randomizedSet.remove(0));

        System.out.println(randomizedSet.insert(0));

        System.out.println(randomizedSet.getRandom());
        System.out.println(randomizedSet.remove(0));
        System.out.println(randomizedSet.insert(0));
        System.out.println();
    }
}

class RandomizedSet {
    private List<Integer> nums;
    private Map<Integer, Integer> indexMap;
    private Random random;

    public RandomizedSet() {
        nums = new ArrayList<>();
        indexMap = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (indexMap.containsKey(val)) {
            return false;
        }
        nums.add(val);
        indexMap.put(val, nums.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (!indexMap.containsKey(val)) {
            return false;
        }
        nums.set(indexMap.get(val), nums.get(nums.size() - 1));
        indexMap.put(nums.get(nums.size() - 1), indexMap.get(val));
        nums.remove(nums.size() - 1);
        indexMap.remove(val);
        return true;
    }

    public int getRandom() {
        return nums.get(random.nextInt(nums.size()));
    }
}