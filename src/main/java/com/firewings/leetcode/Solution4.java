package com.firewings.leetcode;

import java.util.Arrays;

/**
 * 4. 寻找两个有序数组的中位数
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 */

/**
 * Done 1
 * @author firewings.r@gmail.com
 * @date 2020-05-01
 */
class Solution4 {

    /**
     *
     * @param A
     * @param B
     * @return 中位数
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        double ans = 0.0;
        // [1 2 3 4 5] = 5 + 1 = 6 / 2 = 3
        // [1 2 3 4 5 6] = 6 + 1 = 7 / 2 = 3
        int mid1 = (A.length + B.length + 1) / 2;
        // [1 2 3 4 5] = 5 + 2 = 7 / 2 = 3
        // [1 2 3 4 5 6] = 6 + 2 = 8 / 2 = 3
        int mid2 = (A.length + B.length + 2) / 2;
        int n1 = findKth(A, B, mid1, 0, 0);
        // System.out.println(" n1 " + n1);
        int n2 = findKth(A, B, mid2, 0, 0);
        // System.out.println(" n2 " + n2);
        ans = (n1 + n2) / 2.0;
        return ans;
    }

    /**
     *
     * @param A
     * @param B
     * @param k 第 k 大数
     * @param a A 的 索引
     * @param b B 的 索引
     * @return
     */
    private int findKth(int[] A, int[] B, int k, int a, int b) {
        // System.out.println(k + " " + a + " " + b + " ");
        // A 索引超长，要从 B 中返回
        if (a >= A.length) {
            return B[b + k - 1];
        }
        // 与上述同理，逻辑相反
        if (b >= B.length) {
            return A[a + k - 1];
        }

        // 如果 k 到 1 了，比较 2 个数组当前值，拿最小的
        if (k == 1) {
            return Math.min(A[a], B[b]);
        }

        // A 索引超长，从 B 找
        // A = [1 100]
        // B = [21 22 23 24 25 26 27 28 29 30]
        // A + B 长 12，k = 6 and 7
        // 6 / 2 = 3 大于 A 的长度，从 B 开始找，忽略 [0, 3 - 1] 的前三个数
        if (a + k / 2 - 1 >= A.length) {
            return findKth(A, B, k - k / 2, a, b + k / 2);
        }
        // 与上述同理，逻辑相反
        if (b + k / 2 - 1 >= B.length) {
            return findKth(A, B, k - k / 2, a + k / 2, b);
        }

        // [1 2 3 4 5 6 7 8 9 10]
        // k / 2 个数及长度
        // k / 2 - 1 索引
        // 如果对应位置 A >= B， 递归调用
        if (A[a + k / 2 - 1] >= B[b + k / 2 - 1]) {
            // k - k / 2 剩余的 topK 问题
            // 因为 B 是有序的，索引右移 k / 2
            return findKth(A, B, k - k / 2, a, b + k / 2);
        } else {
            // 与上述同理，逻辑相反
            return findKth(A, B, k - k / 2, a + k / 2, b);
        }
    }

    public double findMedianSortedArrays2(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];

        int len = c.length;

        for (int i = 0; i < len; i++) {
            c[i] = i < a.length ? a[i] : b[i - a.length];
        }//将两个数组合并成一个数组（不在乎数组速度），比较可取的方法

        Arrays.sort(c);

        if (c.length % 2 == 1) {
            return c[len / 2] * 1.0;
        } else {
            return (c[len / 2] + c[len / 2 - 1]) / 2.0;
        }
    }
}

class Solution4Fast {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;
        return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2.0;
    }

    //i: nums1的起始位置 j: nums2的起始位置
    public int findKth(int[] nums1, int i, int[] nums2, int j, int k) {
        if (i >= nums1.length) {
            return nums2[j + k - 1];//nums1为空数组
        }
        if (j >= nums2.length) {
            return nums1[i + k - 1];//nums2为空数组
        }
        if (k == 1) {
            return Math.min(nums1[i], nums2[j]);
        }
        int midVal1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
        if (midVal1 < midVal2) {
            return findKth(nums1, i + k / 2, nums2, j, k - k / 2);
        } else {
            return findKth(nums1, i, nums2, j + k / 2, k - k / 2);
        }
    }
}