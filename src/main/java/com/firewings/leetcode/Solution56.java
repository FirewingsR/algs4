package com.firewings.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 56. 合并区间
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */

/**
 * Done 1
 * @author firewings.r@gmail.com
 * @date 2020-05-03
 */
class Solution56 {

    public int[][] merge(int[][] intervals) {
        //逐个合并, 为了保证合并后的范围仍然保持能够继续合并的能力, 合并时总是合并到后边的集合中.
        if (intervals.length <= 1) {
            return intervals;
        }
        int changes = 0;
        for (int i = 0; i < intervals.length; i++) {
            int[] cur = intervals[i];
            for (int j = i + 1; j < intervals.length; j++) {
                int[] next = intervals[j];
                // [[3,4],[1,2]] [[1,2],[3,4]]
                if (!(next[1] < cur[0] || cur[1] < next[0])) {
                    intervals[j][0] = Math.min(cur[0], next[0]);
                    intervals[j][1] = Math.max(cur[1], next[1]);
                    //被合并了, 置空, 后面构建返回时用
                    intervals[i] = null;
                    changes++;
                    //合并过了, 不要再和后面的合并了
                    break;
                }
            }
        }
        int[][] rtn = new int[intervals.length - changes][2];
        int c = 0;
        for (int[] interval : intervals) {
            if (interval != null) {
                rtn[c++] = interval;
            }
        }
        return rtn;
    }

    public int[][] merge1(int[][] intervals) {
        // Arrays.sort(intervals, new Comparator<int[]>() {
        //     @Override
        //     public int compare(int[] a, int[] b) {
        //         return a[1] - b[1];
        //     }
        // });

        if (intervals == null || intervals.length == 0) {
            return new int[0][2];
        }

        Arrays.sort(intervals, (a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        // Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        // System.out.println(Arrays.toString(intervals));

        List<int[]> ans = new ArrayList<>();

        int[] pre = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            if (pre[1] < intervals[i][0]) {
                ans.add(pre);

                pre = intervals[i];
            } else {
                pre[0] = Math.min(pre[0], intervals[i][0]);
                pre[1] = intervals[i][1];
            }
        }

        ans.add(pre);

        List<int[]> ans2 = new ArrayList<>();

        pre = intervals[intervals.length - 1];

        for (int i = intervals.length - 2; i >= 0; i--) {
            if (pre[0] > intervals[i][1]) {
                ans2.add(pre);

                pre = intervals[i];
            } else {
                pre[0] = Math.min(pre[0], intervals[i][0]);
            }
        }

        ans2.add(pre);

        if (ans2.size() < ans.size()) {
            ans = ans2;
        }

        // for (int[] arr: ans2) {
        //     System.out.println(Arrays.toString(arr));
        // }

        return ans.toArray(new int[ans.size()][]);
    }

    public int[][] merge2(int[][] intervals) {

        if (intervals == null || intervals.length == 0) {
            return new int[0][2];
        }

        Arrays.sort(intervals, (a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        int[] pre = intervals[intervals.length - 1];

        List<int[]> ans = new ArrayList<>();

        for (int i = intervals.length - 2; i >= 0; i--) {
            if (pre[0] > intervals[i][1]) {
                ans.add(pre);

                pre = intervals[i];
            } else {
                pre[0] = Math.min(pre[0], intervals[i][0]);
            }
        }

        ans.add(pre);

        return ans.toArray(new int[ans.size()][]);
    }
}