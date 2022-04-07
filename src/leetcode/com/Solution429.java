package src.leetcode.com;

import src.leetcode.com.common.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution429 {
    public static void main(String[] args) {
        Solution429 solution = new Solution429();

        System.out.println();
    }

    public List<List<Integer>> levelOrder(Node root) {
       List<List<Integer>> ret = new ArrayList<>();
       if(root==null){
           return ret;
       }

       Queue<Node> cur = new LinkedList<>();

       cur.offer(root);
       while (cur.size()!=0){
           Queue<Node> next = new LinkedList<>();
           List<Integer> list = new ArrayList<>();
           while (!cur.isEmpty()){
               Node node = cur.poll();
               list.add(node.val);
               next.addAll(node.children);
           }
           if(list.size()!=0){
               ret.add(list);
               cur = next;
           }
       }
       return ret;
    }
}
