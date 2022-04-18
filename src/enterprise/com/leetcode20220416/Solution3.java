package src.enterprise.com.leetcode20220416;

import src.leetcode.com.common.TreeNode;

import java.util.*;

public class Solution3 {

    // 超出内存限制
    public int getNumber(TreeNode root, int[][] ops) {
        boolean[] originBitmap = new boolean[(int) Math.pow(10, 9)];
        boolean[] afterBitmap = new boolean[(int) Math.pow(10, 9)];

        // 统计原有bitmap位点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr != null) {
                originBitmap[curr.val] = true;
                queue.add(curr.left);
                queue.add(curr.right);
            }
        }

        // 统计变化值
        for (int[] op : ops) {
            if (op[0] == 0) {
                Arrays.fill(afterBitmap, op[1], op[2], false);
            } else {
                Arrays.fill(afterBitmap, op[1], op[2], true);
            }
        }

        int cnt = 0;
        for (int i = 0; i < originBitmap.length; i++) {
            if (originBitmap[i] && afterBitmap[i]) {
                cnt++;
            }
        }
        return cnt;
    }

    //
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
    private void fillBoolean(List<Integer> list, boolean[] afterBitmap, int start, int end, boolean flag) {
        int left = findLeftIndex(list, start);
        int right = findRightIndex(list, end);
        if(left>=list.size() || right<=-1){
            return;
        }
        Arrays.fill(afterBitmap, left, right+1, flag);
    }

    // 查找左起点
    private int findLeftIndex(List<Integer> list, int start) {
        if (list.get(0) > start) {
            return 0;
        } else if (list.get(list.size() - 1) < start) {
            return list.size();
        }

        int l = 0, r = list.size() - 1;
        int mide = l + (r - l) / 2;
        while (l < r && l!=mide) {
            if (list.get(mide) < start) {
                l = mide + 1;
            } else if (list.get(mide) > start) {
                r = mide;
            } else {
                return mide;
            }
            mide = l + (r - l) / 2;
        }
        return l;
    }

    // 查找右起点
    private int findRightIndex(List<Integer> list, int end) {
        if (list.get(0) > end) {
            return -1;
        } else if (list.get(list.size() - 1) < end) {
            return list.size() - 1;
        }

        int l = 0, r = list.size() - 1;
        int mide = l + (r - l) / 2;
        while (l < r && l!=mide) {
            if (list.get(mide) < end) {
                l = mide;
            } else if (list.get(mide) > end) {
                r = mide-1;
            } else {
                return mide;
            }
            mide = l + (r - l) / 2;
        }
        return mide;
    }


    public static void main(String[] args) {
        Solution3 solution = new Solution3();
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
