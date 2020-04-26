package com.firewings.sort;

import java.util.Arrays;

/**
 * @author firewings.r@gmail.com
 * @date 2020-04-25
 */
public class InsertionSort {

    /**
     * 外层循环 1 到 n
     * 记住当前 i 的 值
     *
     * 内层循环 i - 1 到 0
     * 大于i值 是条件
     *
     * j 值 移到 j + 1
     * 移动结束 插 i 值
     *
     * @param nums
     */
    void sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int len = nums.length;

        for (int i = 1, j; i < len; i++) {
            int curr = nums[i];
            for (j = i - 1; j >= 0 && nums[j] > curr; j--) {
                nums[j + 1] = nums[j];
            }
            nums[j + 1] = curr;
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 9, 5, 6, 8, 5, 3, 6, 7, 8, 4, 3};
        new InsertionSort().sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
