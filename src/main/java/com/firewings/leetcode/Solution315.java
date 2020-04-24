package com.firewings.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 315. 计算右侧小于当前元素的个数
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 *
 * 示例:
 *
 * 输入: [5,2,6,1]
 * 输出: [2,1,1,0]
 * 解释:
 * 5 的右侧有 2 个更小的元素 (2 和 1).
 * 2 的右侧仅有 1 个更小的元素 (1).
 * 6 的右侧有 1 个更小的元素 (1).
 * 1 的右侧有 0 个更小的元素.
 */

/**
 * @author firewings.r@gmail.com
 * @date 2020-04-24
 */
class Solution315 {
     class Node {
         Node left, right;
         int val, sum, dup = 1;

         public Node(int v, int s) {
             val = v;
             sum = s;
         }

         public String toString() {
             return "val:" + val;
         }
     }

     public List<Integer> countSmaller(int[] nums) {

         Integer[] ans = new Integer[nums.length];
         Node root = null;

         for (int i = nums.length - 1; i >= 0; i--) {
             root = insert(root, nums[i], i, 0, ans);
         }

         return Arrays.asList(ans);
     }

     private Node insert(Node node, int num, int i, int preSum, Integer[] ans) {
         System.out.println(i + " " + node + " " + num);

         if (node == null) {
             node = new Node(num, 0);
             ans[i] = preSum;
         } else if (num == node.val) {
             node.dup++;
             ans[i] = preSum + node.sum;
         } else if (num < node.val) {
             node.sum++;
             node.left = insert(node.left, num, i, preSum, ans);
         } else {
             node.right = insert(node.right, num, i, preSum + node.dup + node.sum, ans);
         }

         return node;
     }

    public List<Integer> countSmaller2(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }

        int min = Integer.MAX_VALUE; // nums数组最小值
        for (int value : nums) {
            if (value < min) {
                min = value;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] - min + 1;
        }

        int max = Integer.MIN_VALUE;
        for (int value : nums) {
            if (value > max) {
                max = value;
            }
        }

        int[] BITree = new int[max + 1];
        BITree[0] = 0;

        int[] countArr = new int[nums.length];

        for (int i = nums.length - 1; i >= 0; i--) {
            int count = getSum(nums[i] - 1, BITree);
            countArr[i] = count;
            update(nums[i], BITree);
        }

        List<Integer> result = new ArrayList<>();

        for (int value : countArr) {
            result.add(value);
        }

        return result;
    }

    private static int getSum(int value, int[] BITree) { // 获得a[i]从1，value的和
        int sum = 0;
        while (value > 0) {
            sum += BITree[value];
            value -= (value & -value);
        }
        return sum;
    }

    public static void update(int value, int[] BITree) {
        while (value <= BITree.length - 1) {
            BITree[value] += 1;
            value += (value & -value);
        }
    }
}