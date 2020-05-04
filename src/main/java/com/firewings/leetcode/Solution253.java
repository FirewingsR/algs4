package com.firewings.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 253. 会议室 II
 * 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，为避免会议冲突，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
 *
 * 示例 1:
 *
 * 输入: [[0, 30],[5, 10],[15, 20]]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [[7,10],[2,4]]
 * 输出: 1
 */

/**
 * Done 1
 * @author firewings.r@gmail.com
 * @date 2020-05-01
 */
public class Solution253 {

    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int room = 0;
        int e = 0;
        for (int begin = 0; begin < start.length; begin++) {
            if (start[begin] < end[e]) {
                room++;
            } else {
                e++;
            }
        }
        return room;
    }

    int minMeetingRooms2(int[][] intervals) {

        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        PriorityQueue<int[]> heap = new PriorityQueue<>(intervals.length, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[1] == b[1]) {
                    return a[0] - b[0];
                }
                return a[1] - b[1];
            }
        });

        heap.offer(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] val = heap.poll();
            if (val == null) {
                continue;
            }
            if (intervals[i][0] >= val[1]) {
                val[1] = intervals[i][1];
            } else {
                heap.offer(intervals[i]);
            }
            heap.offer(val);
        }

        return heap.size();
    }
}
