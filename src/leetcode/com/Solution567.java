package src.leetcode.com;

import java.util.*;

// todo 滑动、双指针 待
public class Solution567 {
    public static void main(String[] args) {
//        String s1 = "abc";
//        String s2 = "bbbca";

//        String s1 = "adc";
//        String s2 = "dcba";

        String s1 = "ab";
        String s2 = "eidbaooo";
        boolean flag = checkInclusion3(s1, s2);

        System.out.println(flag);
    }

    // 滑动窗口（错误的）
    public static boolean checkInclusion1(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        HashMap<Character, Integer> map = new HashMap<>(len1 * 2);
        for (int i = 0; i < len1; i++) {
            if (map.containsKey(s1.charAt(i))) {
                map.put(s1.charAt(i), map.get(s1.charAt(i)) + 1);
            } else {
                map.put(s1.charAt(i), 1);
            }
        }

        int i = 0;
        while (i < len2 - len1 + 1) {
            HashMap<Character, Integer> mapPre = new HashMap<Character, Integer>();
            mapPre.putAll(map);
            int j = 0;
            boolean flag = true;
            while (j < len1) {
                Character c = s2.charAt(i);
                i++;
                if (!mapPre.containsKey(c)) {
                    flag = false;
                    break;
                } else {
                    int result = mapPre.get(c) - 1;
                    if (result == 0) {
                        mapPre.remove(c);
                    } else {
                        mapPre.put(c, result);
                    }
                }
                j++;
            }
            if (flag) return true;
        }
        return false;
    }

    // 正确的滑动窗口（初级）
    public static boolean checkInclusion2(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if(len1>len2) return false;

        int[] cnt1= new int[26];
        int[] cnt2= new int[26];

        // 设置初始值
        for (int i = 0; i < len1; ++i) {
            ++cnt1[s1.charAt(i) - 'a'];
            ++cnt2[s2.charAt(i) - 'a'];
        }
        if(Arrays.equals(cnt1,cnt2)) return true;

        for (int i = len1; i < len2; i++) {
            ++cnt2[s2.charAt(i)-'a'];
            --cnt2[s2.charAt(i-len1)-'a'];
            if(Arrays.equals(cnt1,cnt2)) return true;
        }
        return false;
    }

    // 滑动窗口优化
    public static boolean checkInclusion3(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if(len1>len2) return false;

        int[] cnt1= new int[26];

        // 设置初始值
        for (int i = 0; i < len1; ++i) {
            --cnt1[s1.charAt(i) - 'a'];
            ++cnt1[s2.charAt(i) - 'a'];
        }

        int diff = 0;
        for (int i : cnt1) {
            if(i!=0){
                ++diff;
            }
        }

        if(diff==0) return true;
        for (int i = len1; i < len2; i++) {
            int x = s2.charAt(i)-'a',y=s2.charAt(i-len1)-'a';
            if(x==y){
                continue;
            }

            // 之前没有该值（新加入x）
            if(cnt1[x]==0){
                ++diff;
            }
            ++cnt1[x];
            if(cnt1[x]==0){
                --diff;
            }

            // 消失的y值
            if(cnt1[y]==0){
                ++diff;
            }
            --cnt1[y];
            if(cnt1[y]==0){
                --diff;
            }
            if(diff==0) return true;
        }

        return false;
    }

    // 双指针求解
    public static boolean checkInclusion4(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if(len1>len2) return false;

        int[] cnt= new int[26];
        for (int i = 0; i < len1; ++i) {
            --cnt[s1.charAt(i) - 'a'];
        }

        int left=0;
        for (int i = 0; i < len2; i++) {
            int x = s2.charAt(i)-'a';
            ++cnt[x];
            while (cnt[x]>0){
                --cnt[s2.charAt(left) - 'a'];
                ++left;
            }
            if (i - left + 1 == len1) {
                return true;
            }
        }

        return false;
    }

    public static boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        HashMap<Character, Integer> map = new HashMap<>(len1 * 2);
        for (int i = 0; i < len1; i++) {
            if (map.containsKey(s1.charAt(i))) {
                map.put(s1.charAt(i), map.get(s1.charAt(i)) + 1);
            } else {
                map.put(s1.charAt(i), 1);
            }
        }

        for (int i = 0; i < len2 - len1 + 1; i++) {
            HashMap<Character, Integer> mapPre = new HashMap<Character, Integer>();
            mapPre.putAll(map);
            if (match(mapPre, s2.substring(i, i + len1))) return true;
        }
        return false;
    }

    private static boolean match(HashMap<Character, Integer> map, String s2) {
        for (int i = 0; i < s2.length(); i++) {
            Character c = s2.charAt(i);
            if (!map.containsKey(c)) {
                return false;
            } else {
                int result = map.get(c) - 1;
                if (result == 0) {
                    map.remove(c);
                } else {
                    map.put(c, result);
                }
            }
        }
        return true;
    }


}
