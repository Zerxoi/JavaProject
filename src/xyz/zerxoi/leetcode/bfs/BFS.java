package xyz.zerxoi.leetcode.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        // root.left.right = new TreeNode(3);
        root.left.left.left = new TreeNode(0);

        root.right = new TreeNode(7);
        // root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        root.right.right.right = new TreeNode(10);

        System.out.println(BFS1(root));
        System.out.println(BFS2(root));
        System.out.println(BFS3(root));
    }

    public static List<List<Integer>> BFS1(TreeNode root) {
        if (root == null)
            return null;
        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                levelList.add(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            list.add(levelList);
        }
        return list;
    }

    public static List<List<Integer>> BFS2(TreeNode root) {
        if (root == null)
            return null;
        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        List<Integer> levelList = new ArrayList<>();
        while (queue.size() > 0) {
            TreeNode node = queue.poll();
            if (node != null) {
                levelList.add(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            } else {
                if (queue.size() > 0)
                    queue.add(null);
                list.add(levelList);
                levelList = new ArrayList<>();
            }
        }
        return list;
    }

    public static List<List<Integer>> BFS3(TreeNode root) {
        if (root == null)
            return null;
        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int m = 1;
        int n = 0;
        List<Integer> levelList = new ArrayList<>();
        while (m > 0) {
            TreeNode node = queue.poll();
            m--;

            levelList.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
                n++;
            }
            if (node.right != null) {
                queue.add(node.right);
                n++;
            }
            if (m == 0) {
                m = n;
                n = 0;
                list.add(levelList);
                levelList = new ArrayList<>();
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