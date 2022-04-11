package src.enterprise.com.bytedance20220411;

public class Solution2 {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
//        boolean b = solution.canJump1(new int[]{2, 3, 1, 1, 4});
//        boolean b1 = solution.canJump1(new int[]{1});
        boolean b1 = solution.canJump1(new int[]{1,2,3});
        System.out.println();
    }

    public boolean canJump(int[] nums) {
        int len = nums.length;
        int[] sample = new int[len];
        sample[0] = 1;
        for (int i = 0; i < len; i++) {
            if (sample[i] == 1) {
                for (int j = 1; j <= nums[i] && i + j < len; j++) {
                    sample[i + j] = 1;
                }
            }
        }
        return sample[len - 1] == 1;
    }

    // 官方题解更加优秀，只用记录最远可到达距离即可
    public boolean canJump1(int[] nums) {
        int len = nums.length;
        int maxIndex = nums[0];
        int curIndex = 0;
        while (curIndex <= maxIndex && curIndex < len) {
            if (maxIndex < curIndex + nums[curIndex]) {
                maxIndex = curIndex + nums[curIndex];
                if (maxIndex >= len - 1) {
                    return true;
                }
            }
            curIndex++;
        }
        return maxIndex>=len-1;
    }
}
