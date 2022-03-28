package src.leetcode.com.common;

/**
 * @Author 59456
 * @Date 2022/3/26
 * @Descrip
 * @Version 1.0
 */
public class TreeNode {
    public int key;
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int key,int val) {
        this.key = key;
        this.val = val;
    }

    public TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
