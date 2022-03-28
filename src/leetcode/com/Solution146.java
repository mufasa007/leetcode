package src.leetcode.com;

import src.leetcode.com.common.Node;
import src.leetcode.com.common.TreeNode;

import java.util.*;

public class Solution146 {
    public static void main(String[] args) {
        Solution146 solution = new Solution146();
        LRUCache obj = new LRUCache(2);

        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String s = in.nextLine();
            if (s == null && s.trim() == "") {
                System.out.println("");
            } else {
                String[] s1 = s.split(" ");
                if (s1.length == 1) {
                    System.out.println(obj.get(Integer.parseInt(s1[0])));
                } else {
                    obj.put(Integer.parseInt(s1[0]), Integer.parseInt(s1[1]));
                }
            }
        }
    }

    static class LRUCache1 extends LinkedHashMap<Integer,Integer>{
        private int capacity;

        public LRUCache1(int capacity) {
            super(capacity,0.75F,true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key,-1);
        }

        public void put(int key, int value) {
            super.put(key,value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size()>capacity;
        }
    }

    static class LRUCache {

        private int capacity;
        private int size;
        private TreeNode head = new TreeNode(0);
        private TreeNode tail = new TreeNode(0);
        private Map<Integer, TreeNode> chache;


        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            head.right = tail;
            tail.left = head;
            this.chache = new HashMap<>(capacity * 2);
        }

        public int get(int key) {
            if (chache.containsKey(key)) {
                TreeNode curr = chache.get(key);
                TreeNode currLeft = curr.left;
                TreeNode currRight = curr.right;
                currLeft.right = currRight;
                currRight.left = currLeft;

                restNode(curr);
                return curr.val;
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {
            TreeNode curr;
            if (chache.containsKey(key)) {
                curr = chache.get(key);
                curr.val = value;
            } else if (size < capacity) {
                curr = new TreeNode(key, value);
                size++;
            } else {
                curr = new TreeNode(key, value);
                TreeNode delNode = tail.left;
                delNode.right.left = delNode.left;
                delNode.left.right = delNode.right;
                chache.remove(delNode.key);
            }
            restNode(curr);
            chache.put(key, curr);
        }

        private void restNode(TreeNode curr) {
            if(curr.left!=null && curr.right!=null){
                curr.right.left = curr.left;
                curr.left.right = curr.right;
            }

            curr.left = head;
            curr.right = head.right;

            head.right.left = curr;
            head.right = curr;
        }
    }
}

/*
1 0
2 2
1
3 3
2
4 4
1
 */