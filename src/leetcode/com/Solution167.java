package src.leetcode.com;

public class Solution167 {
    public static void main(String[] args) {
       int[]  numbers = new int[]{2,7,11,15};
       int[] out = twoSum(numbers,9);
        System.out.println();
    }

    public static int[] twoSum(int[] numbers, int target) {
        int len = numbers.length;
        int i=0,j=len-1,sum;

        while (i<len && numbers[i]<target && i<j){
            sum = numbers[i]+numbers[j];
            if(sum==target){
                return new int[]{i,j};
            }else if(sum<target){
                while (j<len-1 && numbers[i]+numbers[++j]<target){
                }
            }else {

            }
            i++;
        }
        return new int[]{};
    }
}
