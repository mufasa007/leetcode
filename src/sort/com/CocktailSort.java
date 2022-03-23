package src.sort.com;

/*
鸡尾酒法
 */
public class CocktailSort extends Sort{
    @Override
    void sort(int[] nums) {
        if (nums == null) {
            return;
        }
        int len = nums.length, max = nums[0];
        int left = 1,right = len -1,index = 1,step = 1;
        while (left<right){
            if(index == left && step == -1){
                step = 1;
                left++;
            }else if(index == right && step == 1){
                step = -1;
                right--;
            }

            if(nums[index-1]>nums[index]){
                swap(nums,index,index-1);
            }
            index += step;
        }
    }
}
