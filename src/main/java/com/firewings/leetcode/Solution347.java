package com.firewings.leetcode;

import java.util.*;

/**
 * 347. 前 K 个高频元素
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *
 *
 * 提示：
 *
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
 * 你可以按任意顺序返回答案。
 */

/**
 * @author firewings.r@gmail.com
 * @date 2020-04-24
 */
class Solution347 {

//    public int[] topKFrequent(int[] nums, int k) {
//        int len = nums.length;
//
//        if (len <= 1) {
//            return new int[]{1};
//        }
//
//        int[] ans = new int[k];
//
//        HashMap<Integer, Integer> hash = new HashMap<>(k);
//
//        for (int i = 0; i < len; i++) {
//            Integer count = hash.get(nums[i]);
//
//            if (count == null) {
//                count = 1;
//            } else {
//                count++;
//            }
//
//            hash.put(nums[i], count);
//        }
//
//        PriorityQueue<HashMap.Entry<Integer, Integer>> queue = new PriorityQueue<>(new Comparator<HashMap.Entry<Integer, Integer>>() {
//            @Override
//            public int compare(HashMap.Entry<Integer, Integer> o1, HashMap.Entry<Integer, Integer> o2) {
//                return o2.getValue() - o1.getValue();
//            }
//        });
//
//        queue.addAll(hash.entrySet());
//
//        for (int i = 0; i < k; i++) {
//            ans[i] = queue.poll().getKey();
//        }
//
//        return ans;
//    }

//    public int[] topKFrequent(int[] nums, int k) {
//
//        int len = nums.length;
//
//        if (len <= 1) {
//            return new int[] {1};
//        }
//
//        Arrays.sort(nums);
//
//        int diffNum = 1;
//
//        for (int i = 1; i < len; i++) {
//            if (nums[i - 1] != nums[i]) {
//                diffNum++;
//            }
//        }
//
//        int[][] map = new int[diffNum][2];
//        int mIndex = 0;
//
//        map[mIndex] = new int[] {nums[0], 1};
//
//        for (int i = 1; i < len; i++) {
//            if (nums[i - 1] != nums[i]) {
//                mIndex++;
//                map[mIndex] = new int[] {nums[i], 1};
//            } else {
//                map[mIndex][1]++;
//            }
//        }
//
//        Arrays.sort(map, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return o2[1] - o1[1];
//            }
//        });
//
//        int[] ans = new int[k];
//
//        for (int i = 0; i < k; i++) {
//            ans[i] = map[i][0];
//        }
//
//        return ans;
//    }

    public int[] topKFrequent(int[] nums, int k) {

        int len = nums.length;

        if (len <= 1) {
            return new int[]{1};
        }

        Arrays.sort(nums);

        List<int[]> map = new ArrayList<>();
        int mIndex = 0;

        map.add(new int[]{nums[0], 1});

        for (int i = 1; i < len; i++) {
            if (nums[i - 1] != nums[i]) {
                map.add(new int[]{nums[i], 1});
                mIndex++;
            } else {
                map.get(mIndex)[1]++;
            }
        }

        Collections.sort(map, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });

        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {
            ans[i] = map.get(i)[0];
        }

        return ans;
    }
}