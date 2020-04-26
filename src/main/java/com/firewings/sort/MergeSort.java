package com.firewings.sort;

import java.util.Arrays;

/**
 * @author firewings.r@gmail.com
 * @date 2020-04-25
 */
public class MergeSort {

    /**
     * 数组分高低
     * 相等必返回
     *
     * 低高相减 得中位
     * 递归两边 后合并
     *
     * @param A
     */
    void sort(int[] A, int lo, int hi) {
        if (A == null || A.length <= 1) {
            return;
        }

        if (lo >= hi) {
            return;
        }

        int mid = lo + (hi - lo) / 2;

        sort(A, lo, mid);
        sort(A, mid + 1, hi);

        merge(A, lo, mid, hi);
    }

    /**
     * i大中时 j++
     * j大高时 i++
     * i大j时 j++
     * j大i时 i++
     *
     * @param nums
     * @param lo
     * @param mid
     * @param hi
     */
    private void merge(int[] nums, int lo, int mid, int hi) {
        int[] copy = nums.clone();

        int k = lo, i = lo, j = mid + 1;

        while (k <= hi) {
            if (i > mid) {
                nums[k++] = copy[j++];
            } else if (j > hi) {
                nums[k++] = copy[i++];
            } else if (nums[i] > nums[j]) {
                nums[k++] = copy[j++];
            } else {
                nums[k++] = copy[i++];
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 9, 5, 6, 8, 5, 3, 6, 7, 8, 4, 3};
        new MergeSort().sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
