package src.leetcode.com;

public class Solution35 {

    public static void main(String[] args) {
//        int[] nums = new int[]{-1, 0, 3, 5, 9, 12};
        int[] nums = new int[]{2,5,6,7};
//        System.out.println(search(nums, 12));
//        System.out.println(search(nums, 5));
//        System.out.println(search(nums, 2));
        System.out.println(searchInsert(nums, 0));
        System.out.println(searchInsert(nums, 10));
    }

    public static int searchInsert(int[] nums, int target) {
        int l=0,r=nums.length-1,index;
        while (l < r) {
            index = l + (r-l)/2;
            if (nums[index] < target) {
                l = ++index;
            } else if (nums[index] > target) {
                r = --index;
            } else {
                return index;
            }
        }

        if(nums[l]<target){
            return l+1;
        }else {
            return l;
        }
    }
}
