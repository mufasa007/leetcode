package src.leetcode.com;

import java.util.*;
import java.util.function.BiConsumer;

public class Solution819 {


    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> set = new HashSet<>(banned.length);
        for (String s : banned) {
            set.add(s);
        }

        Map<String, Integer> map = new HashMap<>();
        for (String s : paragraph.toLowerCase().split("[^A-z]+")) {
            if (set.contains(s)) {
                continue;
            }
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }

        String maxStr = "";
        int max = 0;

        for (String s : map.keySet()) {
            if (map.get(s) > max) {
                max = map.get(s);
                maxStr = s;
            }
        }

        return maxStr;
    }

    // 官方题解
    public String mostCommonWord1(String paragraph, String[] banned) {
        Set<String> set = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> map = new HashMap<>();
        for (String s : paragraph.toLowerCase().split("[^A-z]+")) {
            if (!set.contains(s)) {
                map.put(s, map.getOrDefault(s, 0) + 1);
            }
        }
        return Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public static void main(String[] args) {
        Solution819 solution = new Solution819();
/*        String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] banned = {"hit"};*/

        String paragraph = "Bob. hIt, baLl";
        String[] banned = {"bob", "hit"};

        System.out.println(solution.mostCommonWord(paragraph, banned));
        System.out.println();
    }
}
