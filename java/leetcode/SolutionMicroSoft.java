package java.leetcode;

import java.util.ArrayList;
import java.util.List;

public class SolutionMicroSoft {

    public static int solution(String str){
        if(str == null || str.length()==0){
            return 0;
        }

        // 统计个数
        List<Integer> indexX = new ArrayList<>();
        List<Integer> indexY = new ArrayList<>();
        int sumX = 0, sumY = 0;

        // 遍历str
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == 'x') {
                sumX++;
                indexX.add(i);
            } else if (c == 'y') {
                sumY++;
                indexY.add(i);
            }
        }

        // 处理
        if (indexX.size() == 0) {
            // 1,没有x字符
            if (indexY.size() == 0) {
                // 1.1,也没有y字符
                return str.length() - 1;
            }else {
                // 1.2,有y字符
                int start = indexY.get(0);
                int end = indexY.get(indexY.size() - 1);
                return start + (indexY.size() - 1 - end);
            }
        }else {
            // 2,存在x字符
            if(indexY.size() == 0){
                // 2.1,不存在y字符
                int start = indexX.get(0);
                int end = indexX.get(indexX.size() - 1);
                return start + (indexX.size() - 1 - end);
            }else {
                // 2.2,存在y字符
                int maxIndex = indexX.size()>indexY.size() ? indexX.size():indexY.size();
                for (int i = 0; i < maxIndex; i++) {
                    // 计算左右相等。。。

                }
                return 0; // fixme 优点想复杂化了
            }
        }
    }

    public static int solution1(String str){
        int sumX=0,sumY=0;
        // 遍历str统计总数
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == 'x') {
                sumX++;
            } else if (c == 'y') {
                sumY++;
            }
        }

        int preX=0,preY=0,result=0;
        // 遍历第二次进行处理，最后一个是不可分的情况
        for (int i = 0; i < str.length()-1; i++) {
            char c = str.charAt(i);
            if (c == 'x') {
                preX++;
            } else if (c == 'y') {
                preY++;
            }
            if(preX == preY || (sumX-preX)==(sumY-preY)){
                result ++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution1("ayxbx"));
        System.out.println(solution1("aydsfdsgxxfdszewrxbx"));
    }
}
