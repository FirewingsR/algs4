package com.firewings.leetcode;

/**
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 *
 *
 *
 *
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 *
 *
 *
 *
 *
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 *
 *
 *
 * 示例:
 *
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 */

/**
 * Done 1
 * @author firewings.r@gmail.com
 * @date 2020-05-03
 */
class Solution84 {
    
    public int largestRectangleArea(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        
        return process(heights, 0, heights.length - 1);
    }
    
    int process(int[] height, int start, int end) {
        
        if (start > end) {
            return 0;
        }
        
        int minIndex = start;
        
        boolean sorted = true;
        
        for (int i = start + 1; i <= end; i++){
            if (height[i] < height[i - 1]){
                sorted = false;
            }
            if (height[minIndex] > height[i]) {
                minIndex = i;
            }
        }
        
        if (sorted) {
            int result = 0;
            for (int i = start; i <= end; i++) {
                result = Math.max(result, (end - i + 1) * height[i]);
            }
            return result;
        }
        
        return Math.max(height[minIndex] * (end - start + 1), Math.max(process(height, start, minIndex - 1), process(height, minIndex + 1, end)));
    }
}