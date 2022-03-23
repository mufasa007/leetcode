package src.sort.com;

public class InsertSort extends Sort{
    @Override
    void sort(int[] nums) {
        if(nums == null){
            return;
        }
        int len = nums.length;

        for (int i = 1; i < len; i++) {
            int index = i;

            while (index >0 && nums[index-1]>nums[index]){
                swap(nums,index,--index);
            }

        }
        

    }
}
