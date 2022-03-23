package src.sort.com;

/*
归并排序
本质就是分治
 */
public class MergeSort extends Sort {

    private int[] arr;
    private int len;

    @Override
    void sort(int[] nums) {
        if (nums == null) {
            return;
        }
        len = nums.length;
//        sortMinToMax(nums, 1);
        sortMaxToMin(nums, 0,len/2,len);
    }

    // 分治法~由小到大
    private void sortMinToMax(int[] num, int step) {
        int count = len / step / 2;
        arr = new int[len];
        for (int i = 0; i < count; i++) {
            int start = i * 2 * step;
            int mide = start + step;
            int end = Math.min(start + 2 * step, len);
            merge(num, start, mide, end);
        }

        if (count == 0) {
            int start = 0;
            int mide = start + step;
            int end = Math.min(start + 2 * step, len);
            merge(num, start, mide, end);
        } else {// 还可以继续扩展
            sortMinToMax(num, step * 2);
        }
    }

    // 分治法~由大到小
    private void sortMaxToMin(int[] num, int start, int mide, int end) {
        arr = new int[len];
        if (start != mide-1) {
            int midePre = (mide - start) / 2 + start;
            sortMaxToMin(num, start, midePre, mide);
        }

        if (mide != end-1) {
            int midePre = (end - mide) / 2 + mide;
            sortMaxToMin(num, mide, midePre, end);
        }

        merge(num, start, mide, end);
    }


    // 用于merge数据 [左闭，右开) 区间
    private void merge(int[] nums, int start, int mide, int end) {
        int p1 = start, p2 = mide;
        int index = start;

        // 比较两个区域
        while (p1 < mide && p2 < end) {
            arr[index++] = nums[p1] < nums[p2] ? nums[p1++] : nums[p2++];
        }

        // 只剩余一个区域
        while (p1 < mide) {
            arr[index++] = nums[p1++];
        }
        while (p2 < end) {
            arr[index++] = nums[p2++];
        }

        // 直接拷贝
        if (end + 1 - start >= 0) {
            System.arraycopy(arr, start, nums, start, end - start);
        }
    }

}


