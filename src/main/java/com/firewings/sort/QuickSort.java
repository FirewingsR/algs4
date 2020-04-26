package com.firewings.sort;

import java.util.Arrays;

/**
 * @author firewings.r@gmail.com
 * @date 2020-04-25
 */
public class QuickSort {

    /**
     * 数组分高低
     * 相等必返回
     *
     * 随机得中值
     * 递归加减1
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

        int p = partition(A, lo, hi);

        sort(A, lo, p - 1);
        sort(A, p + 1, hi);
    }

    /**
     *
     * 随机选择 与hi换
     * 一层循环 lo到hi
     *
     * 小于hi值 一直换
     * i j 交换 返回 i
     *
     * @param nums
     * @param lo
     * @param hi
     */
    private int partition(int[] nums, int lo, int hi) {
        swap(nums, (int) (lo + (hi - lo) * Math.random()), hi);

        int i = lo, j;

        for (j = lo; j < hi; j++) {
            if (nums[j] < nums[hi]) {
                swap(nums, i++, j);
            }
        }

        swap(nums, i, j);

        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {3, 9, 5, 6, 8, 5, 3, 6, 7, 8, 4, 3};
        new QuickSort().sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
