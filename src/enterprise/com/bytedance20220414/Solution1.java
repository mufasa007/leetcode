package src.enterprise.com.bytedance20220414;

import src.leetcode.com.common.TreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class Solution1 {
    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        String s = "T0T1T2T3TFTF";
        String[] ts = s.split("T");
        System.out.println();
    }
}

// 层序遍历+队列实现
class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuffer sf = new StringBuffer();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur != null) {
                sf.append("T").append(cur.val);
                queue.add(cur.left);
                queue.add(cur.right);
            } else {
                sf.append("TF");
            }
        }
        return sf.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0 || data.equals("TF")) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        String[] single = data.split("T");
        TreeNode root = new TreeNode(Integer.parseInt(single[1]));
        queue.add(root);
        int index = 2;
        while (index < single.length) {
            TreeNode cur = queue.poll();
            if (!single[index].equals("F")) {
                TreeNode left = new TreeNode(Integer.parseInt(single[index]));
                cur.left = left;
                queue.add(left);
            }
            if (!single[index + 1].equals("F")) {
                TreeNode right = new TreeNode(Integer.parseInt(single[index + 1]));
                cur.right = right;
                queue.add(right);
            }
            index += 2;
        }
        return root;
    }
}