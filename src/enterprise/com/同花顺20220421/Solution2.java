package src.enterprise.com.同花顺20220421;

import java.util.HashMap;

/**
 * trie树解决问题
 */
public class Solution2 {

    private TrieNode root = new TrieNode();
    private int maxTime = 0;
    private String maxStr = "";

    /**
     * 新增字符串
     *
     * @param str
     */
    public void addString(String str) {
        TrieNode cur = root;
        for (char c : str.toCharArray()) {
            if (!cur.next.containsKey(c)) {
                cur.next.put(c, new TrieNode(c));
            }
            cur = cur.next.get(c);
        }
        cur.add();
        if (maxTime < cur.num) {
            maxStr = str;
            maxTime = cur.num;
        }
    }

    /**
     * 获取出现次数最多的字符串
     *
     * @return
     */
    public String getMaxReferenceStr() {
        return maxStr;
    }

    /**
     * 获取最大出现次数
     *
     * @return
     */
    public int getMaxReferenceTime() {
        return maxTime;
    }

    static class TrieNode {
        char c;
        int num = 0;// 个数统计
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


    public static void main(String[] args) {
        String[] strings = {"Trie", "insert", "search", "search", "startsWith", "insert", "search"};
        Solution2 solution = new Solution2();
        for (String string : strings) {
            solution.addString(string);
        }
        System.out.println(solution.getMaxReferenceStr());
        System.out.println(solution.getMaxReferenceTime());
    }
}
