package src.leetcode.com;

public class Solution780_hard {
    public static void main(String[] args) {
        Solution780_hard solution = new Solution780_hard();

//        int sx = 1, sy = 1, tx = 3, ty = 5;
//        int sx = 1, sy = 1, tx = 2, ty = 2 ;
//        int sx = 1, sy = 1, tx = 2, ty = 2;
//        int sx = 3, sy = 3, tx =12, ty = 9;
//        int sx = 3, sy = 7, tx =3, ty = 4;
//        int sx = 1, sy = 1, tx =3, ty = 5;
        int sx = 9, sy = 10, tx = 9, ty = 19;
        System.out.println(solution.reachingPoints1(sx, sy, tx, ty));
        System.out.println(solution.reachingPoints2(sx, sy, tx, ty));
        System.out.println();
    }

    // 暴力解法,大概率会失败 stackOverFlow


    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        flag = false;
        if (sx > tx || sy > ty) {
            return false;
        } else if (sx == tx && sy == ty) {
            flag = true;
        }

        if (flag) {
            return true;
        } else {
            recursiveLoop(sx + sy, sy, tx, ty);
            recursiveLoop(sx, sx + sy, tx, ty);
        }
        return flag;
    }

    public void recursiveLoop(int sx, int sy, int tx, int ty) {
        if (sx > tx || sy > ty) {
            return;
        } else if (sx == tx && sy == ty) {
            flag = true;
        }

        if (!flag) {
            recursiveLoop(sx + sy, sy, tx, ty);
            recursiveLoop(sx, sx + sy, tx, ty);
        }
    }

    // 反向操作！！！人才
    static boolean flag = false;

    public boolean reachingPoints1(int sx, int sy, int tx, int ty) {
        flag = false;
        recursiveLoop1(sx, sy, tx, ty);
        return flag;
    }

    public void recursiveLoop1(int sx, int sy, int tx, int ty) {
        if (sx > tx || sy > ty) {
            return;
        } else if (sx == tx && sy == ty) {
            flag = true;
            return;
        } else if (sx == tx) {
            flag = ty > sy && (ty - sy) % tx == 0;
            return;
        } else if (sy == ty) {
            flag = tx > sx && (tx - sx) % ty == 0;
            return;
        }

        if (!flag) {
            if (tx > ty) {
                recursiveLoop1(sx, sy, tx % ty, ty);
            } else {
                recursiveLoop1(sx, sy, tx, ty % tx);
            }
        }
    }

    // 官方题解【最优解】
    public boolean reachingPoints2(int sx, int sy, int tx, int ty) {
        while (tx > sx && ty > sy && tx != ty) {
            if (tx > ty) {
                tx %= ty;
            } else {
                ty %= tx;
            }
        }

        if (tx == sx && ty == sy) {
            return true;
        } else if (tx == sx) {
            return ty > sy && (ty - sy) % tx == 0;
        } else if (ty == sy) {
            return tx > sx && (tx - sx) % ty == 0;
        } else {
            return false;
        }
    }
}
