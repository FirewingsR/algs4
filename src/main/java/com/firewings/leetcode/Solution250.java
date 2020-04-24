package com.firewings.leetcode;

class Solution250 {
    int result;

    public int countUnivalSubtrees(TreeNode root) {
        result = 0;
        helper(root);
        return result;
    }

    private boolean helper(TreeNode node) {
        if (node == null) {
            return true;
        }

        boolean left = helper(node.left);
        boolean right = helper(node.right);

        if (left && right) {
            if ((node.left != null && node.val != node.left.val) || (node.right != null && node.val != node.right.val)) {
                return false;
            }
            result++;
            return true;
        }

        return false;
    }

    private class TreeNode {
        public TreeNode left;
        public TreeNode right;

        int val;
    }
}