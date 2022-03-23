package src.sort.com;

/**
 * 选择排序 O(N)
 */
public class SelectSort extends Sort{
    @Override
    void sort(int[] nums) {
        if(nums == null){
            return;
        }
        int len = nums.length;

        int min,pre ,index;
        for (int i = 0; i < len-1; i++) {
            min = nums[i];
            pre = nums[i];
            index = i;

            for (int j = i; j < len; j++) {
                if(min > nums[j]){
                    min = nums[j];
                    index = j;
                }
            }

            if(min != pre){
                swap(nums,i,index);
            }

        }

    }
}
