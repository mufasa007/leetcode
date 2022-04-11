package src.enterprise.com.bytedance20220411;

import java.util.*;

public class Solution3 {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        String[] words = {"wrt","wrf","er","ett","rftt"};
        String s = solution.alienOrder1(words);
        System.out.println();
    }

    public String alienOrder(String[] words) {
        int len =words.length;
        List<Character> characterList = new ArrayList<>();
        List<Integer> rangeList = new ArrayList<>();
        HashMap<Character,Integer> map = new HashMap();

        for (int i = 0; i < len; i++) {
            char c = words[i].charAt(0);
            if(!map.containsKey(c)){
                characterList.add(c);
                rangeList.add(i);
                map.put(c,i);
            }else {
                int range = map.get(c);
                if(range != rangeList.get(i-1)){
                    return "";
                }else {
                    rangeList.add(range);
                }
            }
        }

//        // 上述为初筛，下面的为每个string复筛
//        for (String word : words) {
//            rangeList = new ArrayList<>();
//            HashMap<Character,Integer> map = new HashMap();
//
//            for (int i = 1; i < word.length(); i++) {
//
//            }
//        }

        return null;


    }

    static class Node {
        public Node(char c) {
            this.c = c;
        }

        char c;
        Node pre;
        Node next;
    }

    // 需要再次理解
    public String alienOrder1(String[] words) {
        int n = words.length;

        Set<Character> unknown = new HashSet<>();

        for(String word : words){
            for(int i = 0; i < word.length(); i++){
                unknown.add(word.charAt(i));
            }
        }

        // 1. build graph
        Map<Character, List<Character>> graph = new HashMap<>();
        for(int i = 0; i < n - 1; i++){
            String w1 = words[i], w2 = words[i + 1];
            int maxLen = Math.max(w1.length(), w2.length());

            for(int j = 0; j < maxLen; j++){
                if(j == w2.length()) return "";//不合法
                if(j == w1.length()) break;
                if(w1.charAt(j) != w2.charAt(j)){
                    char from = w1.charAt(j), to = w2.charAt(j);
                    if(graph.get(from) == null){
                        graph.put(from, new LinkedList<>());
                    }
                    graph.get(from).add(to);
                    break;//找到第一个不同字母，可以判断顺序
                }
            }
        }

        // 2.topological sort
        visited = new boolean[26];
        onPath = new boolean[26];
        path = new StringBuilder();
        for(char ch : graph.keySet()){
            traverse(graph, ch);
        }
        // 3. get final result
        if(hasCycle) return "";//存在环则无法排序出合法的字母顺序
        for(char unk : unknown){
            if(path.indexOf(String.valueOf(unk)) == -1){
                path.append(unk);
            }
        }
        return path.reverse().toString();

    }

    boolean[] visited;//记录结点访问
    boolean[] onPath;//记录路线访问
    boolean hasCycle;//存在环标志
    StringBuilder path;

    void traverse(Map<Character, List<Character>> graph, char ch){
        if(onPath[ch - 'a']) hasCycle = true;//该路径曾经访问过，存在环
        if(hasCycle || visited[ch - 'a']) return; // 存在环或者已访问过该节点 返回
        visited[ch - 'a'] = true;
        onPath[ch - 'a'] = true;
        if(graph.get(ch) != null){
            for(char next : graph.get(ch)){
                traverse(graph, next);
            }
        }
        path.append(ch);
        onPath[ch - 'a'] = false;
    }
}
