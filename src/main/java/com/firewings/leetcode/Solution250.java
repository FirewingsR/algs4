package com.firewings.leetcode;

/**
 * 250. Count Univalue Subtrees
 * 统计同值子树。题意是给一个二叉树，请返回同值子树的个数。同值子树的定义是所有子树的节点值都相同。影子题687。例子，
 *
 * Example :
 *
 * Input:  root = [5,1,5,5,5,null,5]
 *
 *               5
 *              / \
 *             1   5
 *            / \   \
 *           5   5   5
 *
 * Output: 4
 */

/**
 * @author firewings.r@gmail.com
 * @date 2020-04-24
 */

// TODO: 24/4/2020 VIP 待验证
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