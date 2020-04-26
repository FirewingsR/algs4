package com.firewings.sort;

import java.util.Arrays;

/**
 * @author firewings.r@gmail.com
 * @date 2020-04-25
 */
public class BubbleSort {

    /**
     * 内外循环 从 0 开
     * 外层循环 n-1
     * 内层循环 n-1-i
     * 两两比较做交换 j > j + 1
     * 大升小降是规律
     *
     * @param nums
     */
    void sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int len = nums.length;

        boolean hasChange = true;

        for (int i = 0; i < len - 1 && hasChange; i++) {
            hasChange = false;
            for (int j = 0; j < len - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    hasChange = true;
                }
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {3, 9, 5, 6, 8, 5, 3, 6, 7, 8, 4, 3};
        new BubbleSort().sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
