package src.algorithm.core.binary;

public class BinaryTemplate {

    /**
     *
     * @param nums 数组
     * @param target 目标值
     * @return 插入索引（向右偏向）
     */
    public int findIndex(int[] nums, int target) {
        // 校验空
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // 校验边界
        int len = nums.length;
        if (target < nums[0]) {
            return 0;
        } else if (nums[len - 1] < target) {
            return len - 1;
        }

        // 二分查找目标位置
        int l = 0, r = len - 1;
        while (l + 1 < r) {
            int mide = l + (r - l) / 2;
            if (nums[mide] < target) {
                l = mide;
            } else if (nums[mide] > target) {
                r = mide;
            } else {
                return mide;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        /*        int left = Collections.binarySearch(list,start);// 起始点
        int right = Collections.binarySearch(list,end);// 终点*/

        BinaryTemplate binaryTemplate = new BinaryTemplate();
        int[] nums = {};
        System.out.println(binaryTemplate.findIndex(nums, -1));
        System.out.println(binaryTemplate.findIndex(nums, 1));
        System.out.println(binaryTemplate.findIndex(nums, 2));
        System.out.println(binaryTemplate.findIndex(nums, 7));
        System.out.println(binaryTemplate.findIndex(nums, 8));
        System.out.println(binaryTemplate.findIndex(nums, 9));


    }
}
