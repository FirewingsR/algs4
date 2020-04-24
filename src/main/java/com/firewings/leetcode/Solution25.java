package com.firewings.leetcode;

import edu.princeton.cs.algs4.Stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 25. K 个一组翻转链表
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 *
 *
 * 示例：
 *
 * 给你这个链表：1->2->3->4->5
 *
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 *
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 *
 *
 *
 * 说明：
 *
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

/**
 * @author firewings.r@gmail.com
 * @date 2020-04-24
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        Deque<Integer> deque = new LinkedList<>();
        deque.push(1);
        deque.getLast();
    }

    Stack<Character> stack = new Stack<>();
}

public class Solution25 {
    public ListNode reverseKGroup(ListNode head, int k) {

        if (!checkLen(head, k)) {
            return head;
        }

        ListNode prev = null;
        ListNode curr = head;
        int n = k;

        while (curr != null && n-- > 0) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        if (curr != null) {
            head.next = reverseKGroup(curr, k);
        }

        return prev;
    }

    public boolean checkLen(ListNode head, int k) {
        ListNode curr = head;
        while (curr != null && k-- > 0) {
            curr = curr.next;
            if (k == 0) {
                return true;
            }
        }
        return false;
    }
}