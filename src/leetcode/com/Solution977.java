package src.leetcode.com;

public class Solution977 {
    public static void main(String[] args) {
//        int[]  nums = new int[]{-4,-1,0,3,10};
        int[]  nums = new int[]{-7,-3,2,3,11};

        System.out.println(sortedSquares(nums));

    }

    public static int[] sortedSquares(int[] nums) {
        int x=0,y=nums.length-1;
        int[] numsOut = new int[nums.length];
        for (int i = nums.length-1; i >=0 ; i--) {

            numsOut[i] = (Math.abs(nums[x])>Math.abs(nums[y])?
                    nums[x]*nums[x++]:nums[y]*nums[y--]);

        }

        return numsOut;

    }
}
