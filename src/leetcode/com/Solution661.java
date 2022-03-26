package src.leetcode.com;

public class Solution661 {
    public static void main(String[] args) {
        int[][] img = {{100, 200, 100}, {200, 50, 200}, {100, 200, 100}};
        int[][] out = imageSmoother(img);
        System.out.println();
    }

    public static int[][] imageSmoother(int[][] img) {
        int lenL = img.length, lenR = img[0].length;
        int[][] out = new int[lenL][lenR];

        for (int i = 0; i < lenL; i++) {
            for (int j = 0; j < lenR; j++) {
                out[i][j] = average(img,i,j);
            }
        }
        return out;
    }

    private static int average(int[][] img, int x, int y) {
        int sum = img[x][y];
        int count = 1;
        int a, b;

        // 向上3格
        a=x-1;
        b=y;
        if (0 <= a) {
            sum += img[a][b];
            count++;

            b=y-1;
            if (0 <= b) {
                sum += img[a][b];
                count++;
            }

            b=y+1;
            if (b<img[0].length) {
                sum += img[a][b];
                count++;
            }
        }

        // 向下3格
        a=x+1;
        b=y;
        if (a<img.length) {
            sum += img[a][b];
            count++;

            b=y-1;
            if (0 <= b) {
                sum += img[a][b];
                count++;
            }

            b=y+1;
            if (b<img[0].length) {
                sum += img[a][b];
                count++;
            }
        }

        // 中间三格
        a = x;
        b=y-1;
        if (0 <= b) {
            sum += img[a][b];
            count++;
        }

        b=y+1;
        if (b<img[0].length) {
            sum += img[a][b];
            count++;
        }

        return sum / count;
    }
}
