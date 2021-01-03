package xyz.zerxoi.leetcode.zigzagLevelOrder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// 题目链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/

public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<TreeNode> q = new LinkedList<>();
        int m = 0;
        int n = 0;
        boolean l2r = true;
        if (root != null) {
            q.addFirst(root);
            m++;
        }
        List<Integer> levelList = new ArrayList<>();
        while (q.size() > 0) {
            if (l2r) {
                TreeNode node = q.pollFirst();
                m--;
                levelList.add(node.val);
                if (node.left != null) {
                    q.addLast(node.left);
                    n++;
                }
                if (node.right != null) {
                    q.addLast(node.right);
                    n++;
                }
                if (m == 0) {
                    list.add(levelList);
                    levelList = new ArrayList<>();
                    l2r = !l2r;
                }
            } else {
                TreeNode node = q.pollLast();
                n--;
                levelList.add(node.val);
                if (node.right != null) {
                    q.addFirst(node.right);
                    m++;
                }
                if (node.left != null) {
                    q.addFirst(node.left);
                    m++;
                }
                if (n == 0) {
                    list.add(levelList);
                    levelList = new ArrayList<>();
                    l2r = !l2r;
                }
            }
        }
        return list;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}