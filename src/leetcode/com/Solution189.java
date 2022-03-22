package src.leetcode.com;

public class Solution189 {
    public static void main(String[] args) {
//        int[] nums = new int[]{1,2,3,4,5,6,7};
        int[] nums = new int[]{1,2,3,4,5,6,7};

        rotate2(nums,3);
        System.out.println(gcd(15,10));
    }

    /*
    暴力,空间复杂度为O(N),不符合空间复杂度O(1)的要求
     */
    public static void rotate(int[] nums, int k) {
        int len = nums.length;
        int index = len-(k % len);
        int[] numsOut = new int[len];

        for (int i = 0; i < len; i++) {
            if(index>=len){
                index = 0;
            }
            numsOut[i] = nums[index++];
        }
        System.arraycopy(numsOut, 0, nums, 0, len);
    }

    /*
    有点递归的感觉
     */
    public static void rotate1(int[] nums, int k) {
        int len = nums.length;
        k = k % len;
        int count = gcd(k, len);
        for (int i = 0; i < count; i++) {
            int current = i;
            int prev = nums[i];
            do {
                int next = (current + k) % len;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
            } while (i != current);
        }
    }

    public static int gcd(int x,int y){
        return y>0?gcd(y,x%y):x;
    }


    /*
    数组翻转
    1,先整体翻转
    2,后分别翻转
     */
    public static void rotate2(int[] nums, int k) {
        int len = nums.length;
        k = k % len;

        // 反转全部
        reverse(nums,0,len-1);

        // 分别反转
        reverse(nums,0,k-1);
        reverse(nums,k,len-1);

    }

    private static void reverse(int[] nums, int start, int end) {
        int mide;
        while (start < end) {
            mide = nums[start];
            nums[start++] = nums[end];
            nums[end--] = mide;
        }
    }
}

/*
0 1 2 3 4 5 6
len = 7
k =3
0,4=7-3
 */