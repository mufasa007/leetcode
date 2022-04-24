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

    /*
     * 减少1倍的遍历次数
     */
    void sort1(int[] nums) {
        if(nums == null){
            return;
        }
        int len = nums.length;

        int l=0,r=len-1;
        while (l<=r){
            int min=nums[l],max=nums[l],pre=nums[l] ;
            int minIndex=l,maxIndex=l;
            for (int i = l; i <= r ; i++) {
                if(min>nums[i]){
                    min=nums[l];
                    minIndex=i;
                }
                if(max<nums[i]){
                    max=nums[i];
                    maxIndex=i;
                }
            }
            swap(nums,l,minIndex);
            swap(nums,r,maxIndex);
            l++;
            r--;
        }
    }

    public static void main(String[] args) {
        SelectSort selectSort = new SelectSort();
        int[] ints = {5, 4, 3, 6, 8, 6, 9};
        selectSort.sort1(ints);
        System.out.println();
    }

}
