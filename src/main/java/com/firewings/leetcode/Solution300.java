package com.firewings.leetcode;

/**
 * 300. 最长上升子序列
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 *
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 */

/**
 * Done 1
 * @author firewings.r@gmail.com
 * @date 2020-05-03
 */
class Solution300 {

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        // len_min 的定义很重要 存储长度为 i 的子序列末尾元素中的最小值
        int[] len_min = new int[nums.length + 1];
        len_min[1] = nums[0];
        int cur_len = 1;

        for (int i = 1; i < nums.length; i++) {
            int j = getPos(len_min, cur_len, nums[i]);
            len_min[j] = nums[i];
            cur_len = Math.max(cur_len, j);
        }

        return cur_len;
    }

    //找到第一个>=目标数的位置
    int getPos(int[] arr, int len, int target) {
        if (arr[len] < target) {
            return len + 1;
        }
        if (arr[1] >= target) {
            return 1;
        }

        int lo = 1, hi = len;
        while (lo <= hi) {
            int mid = lo + (hi - lo + 1) / 2;
            if (target <= arr[mid]) {
                if (arr[mid - 1] < target) {
                    return mid;
                } else {
                    hi = mid - 1;
                }
            } else {
                lo = mid + 1;
            }
        }

        return -1;
    }
}
