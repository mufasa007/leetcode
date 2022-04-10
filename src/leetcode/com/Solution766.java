package src.leetcode.com;

import src.leetcode.com.common.TreeNode;

public class Solution766 {
    public static void main(String[] args) {
        Solution766 solution = new Solution766();
/*        TreeNode node0 = new TreeNode(4);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(5);

        node0.left = node1;
        node0.right = node2;

        node1.left = node3;
        node1.right = node4;

        node2.left = node5;
        TreeNode node = solution.addOneRow(node0, 1, 2);
        */

        TreeNode node0 = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);

        node0.left = node1;
        node0.right = node2;

        node1.left = node3;

        TreeNode node = solution.addOneRow(node0, 5, 4);

        System.out.println();
    }

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode treeNode = new TreeNode(val);
            treeNode.left = root;
            return treeNode;
        }
        
        resetNode(root, val, depth, 2);
        return root;
    }

    private void resetNode(TreeNode root, int val, int depth, int currDepth) {
        if (depth == currDepth) {
            TreeNode left = new TreeNode(val);
            TreeNode rigth = new TreeNode(val);
            if (root.left != null) {
                left.left = root.left;
            }
            if (root.right != null) {
                rigth.right = root.right;
            }
            root.right = rigth;
            root.left = left;
        } else if (depth > currDepth) {
            if(root.left!=null){
                resetNode(root.left, val, depth, currDepth + 1);
            }
            if(root.right!=null){
                resetNode(root.right, val, depth, currDepth + 1);
            }
        }
    }
}
