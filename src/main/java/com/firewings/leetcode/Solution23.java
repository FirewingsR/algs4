package com.firewings.leetcode;

/**
 * 23. 合并K个排序链表
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 *
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 */

/**
 * Done 1
 * @author firewings.r@gmail.com
 * @date 2020-05-01
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution23 {

    static class TreeNode {
        int val;
        int dup = 1;

        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static class Tree {
        TreeNode root;

        void insert(ListNode listNode) {
            if (listNode == null) {
                return;
            }

            if (root == null) {
                root = new TreeNode(listNode.val);
                return;
            }

            TreeNode node = root;

            while (node != null) {
                if (listNode.val < node.val) {
                    if (node.left == null) {
                        node.left = new TreeNode(listNode.val);
                        return;
                    }
                    node = node.left;
                } else if (listNode.val > node.val) {
                    if (node.right == null) {
                        node.right = new TreeNode(listNode.val);
                        return;
                    }
                    node = node.right;
                } else {
                    node.dup += 1;
                    return;
                }
            }
        }

        ListNode ans;

        void inOrder(TreeNode node) {
            if (node == null) {
                return;
            }
            inOrder(node.left);
            // System.out.println(node.val);
            for (int i = 0; i < node.dup; i++) {
                ans.next = new ListNode(node.val);
                ans = ans.next;
            }
            inOrder(node.right);
        }
    }

    public ListNode mergeKLists2(ListNode[] lists) {

        ListNode ans = new ListNode(Integer.MIN_VALUE);

        Tree tree = new Tree();

        int n = lists.length;

        for (int i = 0; i < n; i++) {
            ListNode listNode = lists[i];

            while (listNode != null) {
                tree.insert(listNode);
                listNode = listNode.next;
            }
        }

        tree.ans = ans;
        tree.inOrder(tree.root);

        return ans.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        return mergeLists(lists, 0, lists.length - 1);
    }

    private ListNode mergeLists(ListNode[] lists, int start, int end) {
        if (start > end) {
            return null;
        } else if (start == end) {
            return lists[start];
        } else if (start + 1 == end) {
            return merge(lists[start], lists[end]);
        }

        int mid = start + (end - start) / 2;
        return merge(mergeLists(lists, start, mid), mergeLists(lists, mid + 1, end));
    }

    private ListNode merge(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (a != null && b != null) {
            if (a.val < b.val) {
                cur.next = a;
                a = a.next;
            } else {
                cur.next = b;
                b = b.next;
            }
            cur = cur.next;
        }

        cur.next = a == null ? b : a;

        return dummy.next;
    }
}