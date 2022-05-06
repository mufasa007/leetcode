package src.leetcode.com.solution208;

import java.util.HashMap;

public class Solution208 {



    public static void main(String[] args) {
        Solution208 solution = new Solution208();
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));
        System.out.println();
    }
}

/**
 * Trie树
 * 通过 49 ms	52.1 MB
 */
class Trie2 {
    private TrieNode root;
    public Trie2() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (!cur.next.containsKey(c)) {
                cur.next.put(c, new TrieNode(c));
            }
            cur = cur.next.get(c);
        }
        cur.add();
    }

    public boolean search(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (!cur.next.containsKey(c)) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.num>0;
    }

    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (char c : prefix.toCharArray()) {
            if (!cur.next.containsKey(c)) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

    // 节点实体
    static class TrieNode {
        char c;
        int num;
        HashMap<Character, TrieNode> next = new HashMap<>();// 子节点树

        public TrieNode() {
        }

        public TrieNode(char c) {
            this.c = c;
        }

        public void add() {
            num++;
        }
    }
}

/**
 * 按照题目给定条件进行性能优化
 */
class Trie {
    private boolean isEnd;
    private Trie[] children;

    public Trie() {
        children = new Trie[26];
    }

    /**
     * 插入新数据
     * @param word
     */
    public void insert(String word) {
        Trie cur = this;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (cur.children[index] == null) {
                cur.children[index] = new Trie();
            }
            cur = cur.children[index];
        }
        cur.isEnd = true;
    }

    public boolean search(String word) {
        Trie cur = this;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (cur.children[index] == null) {
                return false;
            }
            cur = cur.children[index];
        }
        return cur.isEnd;
    }

    public boolean startsWith(String prefix) {
        Trie cur = this;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            if (cur.children[index] == null) {
                return false;
            }
            cur = cur.children[index];
        }
        return true;
    }

}