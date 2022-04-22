package src.leetcode.com;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 数组+链表 解决问题
 */
public class Solution6 {

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        List<LinkedList> list = new ArrayList<>(numRows);

        int row = numRows;// 行数
        int mide = numRows - 2;// 中间数
        int len = row + mide;// 每个周期数

        for (int i = 0; i < s.length(); i++) {
            int curIndex = i % len;// 周期索引
            LinkedList<Character> characterList;
            if (curIndex < row) {
                // 在一个列中
                if (list.size() <= curIndex) {
                    characterList = new LinkedList<>();
                } else {
                    characterList = list.get(curIndex);
                    if (characterList == null || characterList.size() == 0) {
                        characterList = new LinkedList<>();
                    }
                }
            } else {
                // 有差值变化
                curIndex = row - 1 - (1 + curIndex - row);
                // 直接添加
                characterList = list.get(curIndex);
                if (characterList == null || characterList.size() == 0) {
                    characterList = new LinkedList<>();
                }
            }
            characterList.add(s.charAt(i));
            if (list.size() <= curIndex) {
                list.add(characterList);
            } else {
                list.set(curIndex, characterList);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            LinkedList<Character> characterList = list.get(i);
            if (characterList.size() > 0) {
                for (Character character : characterList) {
                    stringBuilder.append(character);
                }
            }
        }
        return stringBuilder.toString();
    }


    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
//        String s = "A";
        int numRows = 2;
//        int numRows = 3;
//        int numRows = 4;
        Solution6 solution = new Solution6();
        String convert = solution.convert(s, numRows);
        System.out.println(convert);
    }
}
