package src.leetcode.com;

import java.util.*;

public class SolutionLcp08 {
    public static void main(String[] args) {
        SolutionLcp08 solution = new SolutionLcp08();
//        int[][] increase = {{2, 8, 4}, {2, 5, 0}, {10, 9, 8}};
//        int[][] requirements = {{2, 11, 3}, {15, 10, 7}, {9, 17, 12}, {8, 1, 14}};

        int[][] increase = {{0,4,5},{4,8,8},{8,6,1},{10,10,0}} ;
        int[][] requirements = {{12,11,16},{20,2,6},{9,2,6},{10,18,3},{8,14,9}};

        int[] triggerTime = solution.getTriggerTime(increase, requirements);
        System.out.println();
    }


    // 感觉实现优点小复杂
    public int[] getTriggerTime(int[][] increase, int[][] requirements) {
        int[] ret = new int[requirements.length];
        Arrays.fill(ret, -1);
        List<GradeInfo> gradeInfoList = new ArrayList<>(requirements.length);
        List<GradeInfo> gradeInfoList1 = new ArrayList<>(requirements.length);
        List<GradeInfo> gradeInfoList2 = new ArrayList<>(requirements.length);
        for (int i = 0; i < requirements.length; i++) {
            int[] requirement = requirements[i];
            GradeInfo gradeInfo = new GradeInfo();
            gradeInfo.index = i;
            gradeInfo.level = requirement;
            gradeInfoList.add(gradeInfo);
            gradeInfoList1.add(gradeInfo);
            gradeInfoList2.add(gradeInfo);
        }

        gradeInfoList.sort((o1, o2) -> o1.level[0] - o2.level[0]);
        gradeInfoList1.sort((o1, o2) -> o1.level[1] - o2.level[1]);
        gradeInfoList2.sort((o1, o2) -> o1.level[2] - o2.level[2]);

        int[] count = new int[requirements.length];

        // 开始计时累计等级
        int timeIndex = 0;
        int[] gradeIndex = new int[3];
        int[] gradeNow = new int[3];
        for (int i = 0; i < increase.length; i++) {
            deal(ret, timeIndex, count, gradeNow,
                    gradeIndex,
                    gradeInfoList, gradeInfoList1, gradeInfoList2);

            gradeNow[0] += increase[i][0];
            gradeNow[1] += increase[i][1];
            gradeNow[2] += increase[i][2];

            timeIndex++;
        }

        deal(ret, timeIndex, count, gradeNow,
                gradeIndex,
                gradeInfoList, gradeInfoList1, gradeInfoList2);

        return ret;
    }

    private void deal(int[] ret, int timeIndex, int[] count, int[] gradeNow,
                      int[] gradeIndex,
                      List<GradeInfo> gradeInfoList,
                      List<GradeInfo> gradeInfoList1,
                      List<GradeInfo> gradeInfoList2) {

        while (gradeIndex[0] < gradeInfoList.size() && gradeNow[0] >= gradeInfoList.get(gradeIndex[0]).level[0]) {
            if (count[gradeInfoList.get(gradeIndex[0]).index] < 3) {
                count[gradeInfoList.get(gradeIndex[0]).index]++;
                if (count[gradeInfoList.get(gradeIndex[0]).index] == 3) {
                    ret[gradeInfoList.get(gradeIndex[0]).index] = timeIndex;
                }
            }
            gradeIndex[0]++;
        }

        while (gradeIndex[1] < gradeInfoList1.size() && gradeNow[1] >= gradeInfoList1.get(gradeIndex[1]).level[1]) {
            if (count[gradeInfoList1.get(gradeIndex[1]).index] < 3) {
                count[gradeInfoList1.get(gradeIndex[1]).index]++;
                if (count[gradeInfoList1.get(gradeIndex[1]).index] == 3) {
                    ret[gradeInfoList1.get(gradeIndex[1]).index] = timeIndex;
                }
            }
            gradeIndex[1]++;
        }

        while (gradeIndex[2] < gradeInfoList2.size() && gradeNow[2] >= gradeInfoList2.get(gradeIndex[2]).level[2]) {
            if (count[gradeInfoList2.get(gradeIndex[2]).index] < 3) {
                count[gradeInfoList2.get(gradeIndex[2]).index]++;
                if (count[gradeInfoList2.get(gradeIndex[2]).index] == 3) {
                    ret[gradeInfoList2.get(gradeIndex[2]).index] = timeIndex;
                }
            }
            gradeIndex[2]++;
        }
    }
}

class GradeInfo{
    int index;
    int[] level;
}