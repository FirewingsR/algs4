package com.firewings.leetcode;

import java.util.Arrays;

/**
 * 215. 数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 *
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 */

/**
 * Done 1
 * @author firewings.r@gmail.com
 * @date 2020-05-01
 */
class Solution215 {

    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        findKth(nums, 0, len - 1, k);
        return nums[len - k];
    }

    void findKth(int[] nums, int low, int high, int k) {
        if (low >= high) {
            return;
        }

        int index = split(nums, low, high);
        int ans = nums.length - k;

        if (index == ans) {
            return;
        }

        if (index > ans) {
            findKth(nums, low, index - 1, k);
        } else {
            findKth(nums, index + 1, high, k);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public int split(int[] nums, int low, int high) {
        midOfThree(nums, low, high);
        int temp = nums[low];
        int i = low, j = high;
        while (i < j) {
            while (nums[j] >= temp && i < j) {
                j--;
            }
            nums[i] = nums[j];
            while (nums[i] <= temp && i < j) {
                i++;
            }
            nums[j] = nums[i];
        }
        nums[i] = temp;
        return i;
    }

    public void midOfThree(int[] nums, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;

        int min = Math.min(nums[mid], nums[hi]);
        if (nums[lo] >= min) {
            // 不处理
        } else {
            if (min == nums[mid]) {
                swap(nums, lo, mid);
            } else {
                swap(nums, lo, hi);
            }
        }
    }

    public int findKthLargest2(int[] nums, int k) {
        if (k < 0 || k > nums.length) {
            return -1;
        }

        Arrays.sort(nums);

        return nums[nums.length - k];
    }
}