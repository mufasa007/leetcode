package src.enterprise.com.leetcode20220416;

import src.leetcode.com.common.TreeNode;

import java.util.*;

public class Solution3_1 {

    // 使用数组+二分查找+状态码解决
    public int getNumber1(TreeNode root, int[][] ops) {
        List<Integer> list = new ArrayList<>();
        // 统计原有节点信息
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr != null) {
                list.add(curr.val);
                queue.add(curr.left);
                queue.add(curr.right);
            }
        }

        Collections.sort(list);

        // 创建一个bitmap用于映射list颜色
        boolean[] afterBitmap = new boolean[list.size()];
        // 统计变化值
        for (int[] op : ops) {
            if (op[0] == 0) {
                fillBoolean(list,afterBitmap,op[1], op[2], false);
            } else {
                fillBoolean(list,afterBitmap,op[1], op[2], true);
            }
        }

        int cnt = 0;
        for (boolean b : afterBitmap) {
            if (b) cnt++;
        }

        return cnt;
    }

    // 二分查找法,确定起始~终点位置
    private void fillBoolean(List<Integer> list,
                             boolean[] afterBitmap,
                             int start,
                             int end,
                             boolean flag) {
        int left = findIndex(list.toArray(new Integer[]{}),start);// 起始点
        int right = findIndex(list.toArray(new Integer[]{}),end);// 终点
        if(left>=list.size() || right<=-1){
            return;
        }
        if(list.get(left) < start){
            left++;
        }

        Arrays.fill(afterBitmap, left, right, flag);
    }

    public int findIndex(Integer[] nums, int target) {
        // 校验空
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // 校验边界
        int len = nums.length;
        if (target < nums[0]) {
            return 0;
        } else if (nums[len - 1] < target) {
            return len - 1;
        }

        // 二分查找目标位置
        int l = 0, r = len - 1;
        while (l + 1 < r) {
            int mide = l + (r - l) / 2;
            if (nums[mide] < target) {
                l = mide;
            } else if (nums[mide] > target) {
                r = mide;
            } else {
                return mide;
            }
        }
        return r;
    }


    public static void main(String[] args) {
        Solution3_1 solution = new Solution3_1();
//        double v = Math.pow(2, 31) - Math.pow(10, 9);
//        System.out.println(v);

//        TreeNode node0 = new TreeNode(1);
//        TreeNode node1 = new TreeNode(2);
//        TreeNode node2 = new TreeNode(3);
//        TreeNode node3 = new TreeNode(4);
//        TreeNode node4 = new TreeNode(5);
//
//        node0.right = node1;
//        node1.right = node2;
//        node2.right = node3;
//        node3.right = node4;

/*        TreeNode node0 = new TreeNode(9);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(7);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);

        node0.right = node1;
        node1.right = node2;
        node2.right = node3;
        node3.right = node4;
        node4.right = node5;
        System.out.println(solution.getNumber1(node0, new int[][]{{0,2,2},{1,1,5},{0,4,5},{1,5,7}}));

        */

        TreeNode node0 = new TreeNode(60);
        TreeNode node1 = new TreeNode(66);
        node0.right = node1;

        System.out.println(solution.getNumber1(node0, new int[][]{{0,66,66},{1,60,60},{1,60,60}}));

        System.out.println();
    }
}
