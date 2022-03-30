package src.leetcode.com;

import src.leetcode.com.common.TreeNode;

import java.util.HashMap;

public class Solution337 {
    public static void main(String[] args) {
        Solution337 solution = new Solution337();

        System.out.println();
    }

    public int rob(TreeNode root) {
        HashMap<TreeNode, Integer> f = new HashMap<>();
        HashMap<TreeNode, Integer> g = new HashMap<>();

        dfs(root, f, g);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }

    private void dfs(TreeNode node, HashMap<TreeNode, Integer> f, HashMap<TreeNode, Integer> g) {
        if (node == null) {
            return;
        }

        dfs(node.left, f, g);
        dfs(node.right, f, g);

        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }

    public int rob1(TreeNode root) {
        int[] rootStatus = dfs1(root);
        return Math.max(rootStatus[0], rootStatus[1]);
    }

    public int[] dfs1(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] l = dfs1(node.left);
        int[] r = dfs1(node.right);
        int selected = node.val + l[1] + r[1];
        int notSelected = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        return new int[]{selected, notSelected};
    }

}
