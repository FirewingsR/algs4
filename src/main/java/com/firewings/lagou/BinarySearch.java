package com.firewings.lagou;

public class BinarySearch {
    int binarySearch(int[] nums, int target, int low, int high) {
        if (low > high) {
            return -1;
        }
        int middle = low + (high - low) / 2;
        if (nums[middle] == target) {
            return middle;
        }
        if (target < nums[middle]) {
            return binarySearch(nums, target, low, middle - 1);
        } else {
            return binarySearch(nums, target, middle + 1, high);
        }
    }

    int binarySearch2(int[] nums, int target, int low, int high) {
        while (low <= high) {
            int middle = low + (high - low) / 2;
            if (nums[middle] == target) {
                return middle;
            }
            if (target < nums[middle]) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }

        return -1;
    }
}
