package src.leetcode.com;

import src.leetcode.com.common.Node;

import java.util.Deque;
import java.util.LinkedList;

public class Solution116 {
    public static void main(String[] args) {
        Solution116 solution = new Solution116();

        Node root = new Node(1);
        Node root2 = new Node(2);
        Node root3 = new Node(3);
        Node root4 = new Node(4);
        Node root5 = new Node(5);
        Node root6 = new Node(6);
        Node root7 = new Node(7);

        root.left = root2;
        root.right = root3;

        root2.left = root4;
        root2.right = root5;

        root3.left = root6;
        root3.right = root7;

        Node pre = solution.connect1(root);
        System.out.println();
    }

    // 需要使用层序遍历
    public Node connect1(Node root) {
        if(root==null){
            return root;
        }

        Deque<Node> deque = new LinkedList<>();
        deque.addFirst(root);
        int count = 1;
        int countCurr = 0;

        while (!deque.isEmpty()){
            Node nextNode=null,currNode=null;
            for (int i = 0; i < count; i++) {
                currNode = deque.pollLast();
                currNode.next = nextNode;
                if(currNode.left!=null){
                    deque.addFirst(currNode.right);
                    deque.addFirst(currNode.left);
                    countCurr+=2;
                }
                nextNode = currNode;
            }
            count = countCurr;
            countCurr = 0;
        }
        return root;
    }

}
