package src.leetcode.com;

import src.leetcode.com.common.TreeNode;

/**
 * @Author 59456
 * @Date 2022/3/26
 * @Descrip
 * @Version 1.0
 */
public class Solution617 {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(3);
        TreeNode treeNode3 = new TreeNode(2);
        TreeNode treeNode4 = new TreeNode(5);

        root1.left = treeNode2;
        root1.right = treeNode3;
        treeNode2.left = treeNode4;

        TreeNode root2 = new TreeNode(2);
        TreeNode tree2 = new TreeNode(1);
        TreeNode tree3 = new TreeNode(3);
        TreeNode tree4 = new TreeNode(4);
        TreeNode tree5 = new TreeNode(7);

        root2.left=tree2;
        root2.right=tree3;
        tree2.right=tree4;
        tree3.right=tree5;

        TreeNode out = mergeTrees1(root1,root2);
        System.out.println();


    }

    // 递归迭代~大问题拆分
    public static TreeNode mergeTrees1(TreeNode root1, TreeNode root2) {
        // 直接结束
        TreeNode out = null;
        if(root1==null && root2==null) {
            return out;
        }

        // 其中一颗树为空
        if(root1 == null){
            out = root2;
            return out;
        }
        if(root2 == null){
            out = root1;
            return out;
        }

        int num = root1.val+root2.val;
        out = new TreeNode(num);

        // 递归迭代
        out.left = mergeTrees1(root1.left, root2.left);
        out.right = mergeTrees1(root1.right, root2.right);

        return out;
    }



    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        TreeNode head = null;
        if(root1!=null || root2!=null){
            int num = (root1!=null?root1.val:0)+(root2!=null?root2.val:0);
            head = new TreeNode(num);
        }

        dpExtend(head, root1,  root2);

        return head;
    }

    private static void dpExtend(TreeNode out,TreeNode root1, TreeNode root2){
        // 直接结束
        if(out==null || (root1==null && root2==null)) {
            return;
        }

        // 一颗树为空
        if(root1 == null){
            out.left = root2.left;
            out.right = root2.right;
            return;
        }

        if(root2 == null){
            out.left = root1.left;
            out.right = root1.right;
            return;
        }

        // 两颗树都存在
        if(root1.left!=null || root2.left!=null){
            int num = (root1.left!=null?root1.left.val:0)+(root2.left!=null?root2.left.val:0);
            out.left = new TreeNode(num);
            dpExtend(out.left, root1.left,  root2.left);
        }

        if(root1.right!=null || root2.right!=null){
            int num = (root1.right!=null?root1.right.val:0)+(root2.right!=null?root2.right.val:0);
            out.right = new TreeNode(num);
            dpExtend(out.right, root1.right,  root2.right);
        }
    }
}
