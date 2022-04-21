package src.leetcode.com;

import java.util.Arrays;
import java.util.Comparator;

public class Solution179 {

    // 解法1：手动排序太麻烦
    public String largestNumber(int[] nums) {
        String[] strings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strings[i] = String.valueOf(nums[i]);
        }

        // 根据自己的逻辑来进行排序太多情况需要考虑了
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int minLen = Math.min(o1.length(), o2.length());
                // 常规的数据比较
                for (int i = 0; i < minLen; i++) {
                    if (o1.charAt(i) < o2.charAt(i)) {// 升序
                        return 1;
                    } else if (o1.charAt(i) > o2.charAt(i)) { // 降序
                        return -1;
                    }
                }

                // 非常规的数据比较
                if (o1.length() < o2.length()) {
                    int minLenCur = Math.min(o1.length(), o2.length() - o1.length());
                    for (int i = 0; i < minLenCur; i++) {
                        if (o1.charAt(i) < o2.charAt(o1.length()+i)) {// 升序
                            return 1;
                        } else if (o1.charAt(i) > o2.charAt(o1.length()+i)) { // 降序
                            return -1;
                        }
                    }
                } else if (o1.length() > o2.length()) {
                    int minLenCur = Math.min(o1.length() - o2.length(), o2.length());
                    for (int i = 0; i < minLenCur; i++) {
                        if (o1.charAt(o2.length()+i) < o2.charAt(i)) {// 升序
                            return 1;
                        } else if (o1.charAt(o2.length()+i) > o2.charAt(i)) { // 降序
                            return -1;
                        }
                    }
                }
                return 0;
            }
        });

        StringBuilder sb = new StringBuilder();
        for (String string : strings) {
            sb.append(string);
        }
        return sb.toString();
    }

    public String largestNumber1(int[] nums) {
        String[] strings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strings[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String ab = o1+o2;
                String ba = o2+o1;
                return -ab.compareTo(ba);
            }
        });

        StringBuilder sb = new StringBuilder();
        for (String string : strings) {
            sb.append(string);
        }
        if(sb.toString().startsWith("0")){
            return "0";
        }

        return sb.toString();
    }

    public static void main(String[] args) {
//        int[] nums = {3, 30, 34, 5, 9};
        int[] nums = {432,43243};
        Solution179 solution = new Solution179();
        String s = solution.largestNumber1(nums);
        System.out.println();
    }
}
