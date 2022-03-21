package src.leetcode.com;

public class Solution53 {
    public static void main(String[] args) {
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(deal1(nums));
    }


    /*
    贪心算法
     */
    public static int deal1(int[] nums) {
        int max = Integer.MIN_VALUE;
        int maxNum = Integer.MIN_VALUE;
        int sumPre = 0;

        for (int i = 0; i < nums.length; i++) {

            if(sumPre<=0 && nums[i]>0){
                sumPre = nums[i];
                max = Math.max(sumPre,max);
            }else {
                sumPre = sumPre + nums[i];
                max = Math.max(sumPre,max);
                if(sumPre<0){
                    sumPre = 0;
                }
            }
            maxNum = Math.max(maxNum,nums[i]);
        }

        return Math.max(max,maxNum);

    }
}
