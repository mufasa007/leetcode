package src.leetcode.com;

public class Solution69 {

    // 二分查找问题
    public int mySqrt(int x) {
        int l = 0, r = x, ret = -1;
        while (l <= r) {
            int mide = l + ((r - l) >> 1);
            long cur = (long) mide * (long) mide;
            if (x < cur) {
                r = mide - 1;
            } else if (x == cur) {
                return mide;
            } else {
                ret = mide;
                l = mide + 1;
            }
        }
        return ret;
    }

    public int mySqrt1(int x) {
        if (x == 0) {
            return 0;
        }
        double C = x, x0 = x;
        double pre = (double) x;
        double target = (double) 0;
        while (true) {
            target = (pre * pre + x) / (pre * 2);
            if (Math.abs(target - pre) < 1e-7) {
                return (int) target;
            }
            pre = target;
        }
    }

    // 袖珍计算器算法
    // 需要注意double计算精度问题
    public int mySqrt2(int x) {
        double y =  Math.floor(Math.exp(0.5*Math.log((double) x)))+1;

        return (int) (y*y<=x?y:y-1);
    }

    public static void main(String[] args) {
        Solution69 solution = new Solution69();
//        System.out.println(solution.mySqrt(2147395599));
//        System.out.println(solution.mySqrt1(2147395599));
//        System.out.println(solution.mySqrt2(2147395599));

        System.out.println(solution.mySqrt(2147395600));
        System.out.println(solution.mySqrt1(2147395600));
        System.out.println(solution.mySqrt2(2147395600));
        System.out.println();
    }
}
