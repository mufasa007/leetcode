package src.leetcode.com;

public class Solution34 {

    public int[] searchRange(int[] nums, int target) {
        int len = nums.length;
        if (nums[0] > target || nums[len - 1] < target) {
            return new int[]{-1, -1};
        }

        int l = 0, r = len - 1;
        while (l <= r) {
            int mide = l + (r - l) / 2;
            if (nums[mide] < target) {
                l = mide + 1;
            } else if (nums[mide] > target) {
                r = mide - 1;
            } else {
                // 直接命中
                l = findTarget(nums, target, l, mide, true);
                r = findTarget(nums, target, mide, r, false);
                return new int[]{l, r};
            }
        }
        return new int[]{-1, -1};
    }

    private int findTarget(int[] nums, int target, int l, int r, boolean division) {
        if (division) {
            // up
            if (nums[l] == target) {
                return l;
            }
            int mide = l + (r - l) / 2;
            if (nums[mide] < target) {
                return findTarget(nums, target, mide + 1, r, true);
            } else {
                return findTarget(nums, target, l, mide, true);
            }
        } else {
            // down
            if (nums[r] <= target) {
                return r;
            }
            int mide = l + (r - l) / 2;
            if (nums[mide] > target) {
                return findTarget(nums, target, l, mide - 1, false);
            } else {
                return findTarget(nums, target, mide, r-1, false);
            }
        }
    }


    public static void main(String[] args) {
        Solution34 solution = new Solution34();
//        int[] nums = {5, 7, 7, 8, 8, 10};
//        int target = 8;

        int[] nums = {0,0,1,1,1,2,4,4,4,4,5,5,5,6,8,8,9,9,10,10,10};
        int target = 8;
        int[] ints = solution.searchRange(nums, target);
        System.out.println();

    }
}
