package src.enterprise.com.bytedance20220410;

public class Solution1 {
    public static void main(String[] args) {
        Solution1 solution = new Solution1();
//        int[][] matrix = {{1,2,3,4},{5,1,2,3},{9,5,1,2}};
        int[][] matrix = {{1,2},{2,2}};

        boolean toeplitzMatrix = solution.isToeplitzMatrix(matrix);

        System.out.println();
    }


    public boolean isToeplitzMatrix(int[][] matrix) {
        int lenX = matrix.length;// 行
        int lenY = matrix[0].length;// 列
        int lenMin = Math.min(lenX, lenY);


        // 向右排查
        for (int i = 0; i < lenY; i++) {
            int curr = matrix[0][i];
            for (int j = 1; j < Math.min(lenMin, lenY - i) ; j++) {
                if(curr!=matrix[j][i+j]){
                    return false;
                }
            }
        }

        // 向下排查
        for (int i = 1; i < lenX; i++) {
            int curr = matrix[i][0];
            for (int j = 1; j < Math.min(lenMin, lenX  - i); j++) {
                if(curr!=matrix[i+j][j]){
                    return false;
                }
            }
        }
        return true;
    }
}
