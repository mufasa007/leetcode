package src.leetcode.com;

import java.util.Arrays;

public class Solution287 {

    // 按位运算
    public int findDuplicate(int[] nums) {
        int n = nums.length - 1;
        int cur = 0;
        for (int i = 0; i < n; i++) {
            cur ^= (i + 1);
            cur ^= nums[i];
        }
        cur ^= nums[n];
        return cur;
    }

    // bitmap解决 空间复杂度为O(n)，时间复杂度为O(n)
    public int findDuplicate1(int[] nums) {
        int[] bitmap = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            if (bitmap[curr] >= 1) {
                return curr;
            } else {
                bitmap[curr] = 1;
            }
        }
        return -1;
    }

    // 二分查找(官方)
    /*
     * 本质解决思路：
     * 就是如果没有重复的数字，那么小于等于数字i的个数有i个
     * ①如果个数大于i，说明小于i的数字中有重复的
     * ②如果个数小于i，说明大于i的数字中有重复的
     * */
    public int findDuplicate2(int[] nums) {
        int n = nums.length;
        int l = 1, r = n - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            int cnt = 0;
            for (int i = 0; i < n; ++i) {
                if (nums[i] <= mid) {
                    cnt++;
                }
            }
            if (cnt <= mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }

    // 二分查找（自己）
    public int findDuplicate3(int[] nums) {
        int len = nums.length;
        int l = 1, r = len - 1, ans = -1;
        while (l <= r) {// l和r算是 数字
//            int mid = (l + r) >> 1;// 防止出现int溢出,>> 带符号右移,>>> 不带符号右移 readme + 号的优先级竟然大于 >> !
            int mid = l+((r-l)>> 1);// 防止出现int溢出,>> 带符号右移,>>> 不带符号右移
            int cnt = (int) Arrays.stream(nums).filter(v->v <= mid).parallel().count();
            if (cnt <= mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }

    public int getCount(int n, int[] nums) {//
        return (int) Arrays.stream(nums).filter(v->v <= n).count();
    }


    // 快慢指针寻找重复值（类似于寻找链表的环）
    public int findDuplicate4(int[] nums) {
        int slow=nums[0],fast=nums[nums[0]];
        while (slow!=fast){
            slow=nums[slow];
            fast=nums[nums[fast]];
        }
        fast=0;// 为什么要设置为0？
        while (slow!=fast){
            slow=nums[slow];
            fast=nums[fast];
        }
        return slow;
    }

    public int findDuplicate5(int[] nums) {
        int n = nums.length, ans = 0;
        int bit_max = 31;
        while (((n - 1) >> bit_max) == 0) {
            bit_max -= 1;
        }
        for (int bit = 0; bit <= bit_max; ++bit) {
            int x = 0, y = 0;
            for (int i = 0; i < n; ++i) {
                if ((nums[i] & (1 << bit)) != 0) {
                    x += 1;
                }
                if (i >= 1 && ((i & (1 << bit)) != 0)) {
                    y += 1;
                }
            }
            if (x > y) {
                ans |= 1 << bit;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int test1 = 2+2>>1;
        int test2 = 2+(2>>1);

        Solution287 solution = new Solution287();
//        int[] ints = {1, 2, 3, 4, 4};
        int[] ints = {1, 3, 4, 2, 2};
//        int count = solution.getCount(3, ints);
        int deal = solution.findDuplicate4(ints);
        System.out.println();
    }
}
