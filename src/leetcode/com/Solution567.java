package src.leetcode.com;

import java.util.*;

// todo 滑动、双指针 待
public class Solution567 {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "bbbca";
        boolean flag = checkInclusion(s1,s2);

        System.out.println(flag);
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

        for (int i = 0; i < len2-len1+1; i++) {
            HashMap<Character, Integer> mapPre = new HashMap<Character, Integer>();
            mapPre.putAll(map);
            if(match(mapPre,s2.substring(i,i+len1))) return true;
        }
        return false;
    }

    private static boolean match(HashMap<Character, Integer> map,String s2){
        for (int i = 0; i < s2.length(); i++) {
            Character c = s2.charAt(i);
            if(!map.containsKey(c)){
                return false;
            }else {
                int result = map.get(c)-1;
                if(result==0){
                    map.remove(c);
                }else {
                    map.put(c,result);
                }
            }
        }
        return true;
    }


}
