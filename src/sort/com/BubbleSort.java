package src.sort.com;

public class BubbleSort extends Sort{


    @Override
    public void sort(int[] nums) {
        if(nums == null){
            return;
        }
        int len = nums.length;

        for (int i = 0; i < len-1; i++) {
            for (int j = 0; j < len-1-i; j++) {
                if(nums[j]>nums[j+1]){
                    swap(nums,j,j+1);
                }
            }
        }
    }

}
