package com.firewings.leetcode;

import java.util.LinkedList;

/**
 * 239. 滑动窗口最大值
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 *
 *
 *
 * 进阶：
 *
 * 你能在线性时间复杂度内解决此题吗？
 *
 *
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 */

/**
 * @author firewings.r@gmail.com
 * @date 2020-04-24
 */
class Solution239 {
    
//     private class Node {
//         public int val;
//         public int index;
        
//         public Node(int index, int val) {
//             this.index = index;
//             this.val = val;
//         }
//     } 
    
//     public int[] maxSlidingWindow(int[] nums, int k) {
        
//         int len = nums == null ? 0 : nums.length;
        
//         if (len <= 1 || k <= 1) {
//             return nums;
//         }
        
//         int ansL = len - k + 1;
        
//         int[] ans = new int[ansL];
        
//         Deque<Node> deque = new LinkedList<>();
//         deque.add(new Node(0, nums[0]));
        
//         for (int i = 1; i < len; i++) {
//             int n = nums[i];
            
//             if (!deque.isEmpty() && deque.getFirst().index <= i - k ) {
//                 deque.pollFirst();
//             }
                
//             while (!deque.isEmpty() && n > deque.getLast().val) {
//                 deque.pollLast();
//             }
            
//             deque.add(new Node(i, n));
            
//             int index = i + 1 - k;
            
//             if (index >= 0) {
//                 ans[index] = deque.getFirst().val;
//             }
//         }
        
//         return ans;
//     }

//    public int[] maxSlidingWindow(int[] nums, int k) {
//        int len = nums.length;
//        if (len < k || k < 1) {
//            return new int[0];
//        }
//        LinkedList<Integer> queue = new LinkedList<>();
//        int[] res = new int[len - k + 1];
//        int index = 0;
//        for (int i = 0; i < len; i++) {
//            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
//                queue.pollLast();
//            }
//            queue.addLast(i);
//            if (queue.peekFirst() == i - k) {
//                queue.pollFirst();
//            }
//            if (i >= k - 1) {
//                res[index++] = nums[queue.peekFirst()];
//            }
//        }
//        return res;
//    }

    // TODO: 24/4/2020 在看
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1) {
            return nums;
        }
        int left = 0;
        int right = 0;

        int max = nums[0];
        int maxIndex = 0;

        int len = nums.length;

        for (int i = 1; i < k; i++) {
            if (nums[i] >= max) {
                max = nums[i];
                maxIndex = i;
            }
        }

        int[] res = new int[len - k + 1];
        res[0] = max;

        right = k;

        while (right < len) {
            if (nums[right] >= max) {
                max = nums[right];
                maxIndex = right;
                left++;
            } else if (maxIndex == left) {
                left++;
                max = nums[left];
                maxIndex = left;
                for (int i = left; i <= right; i++) {
                    if (nums[i] >= max) {
                        max = nums[i];
                        maxIndex = i;
                    }
                }
            } else {
                left++;
            }

            res[left] = max;

            right++;
        }

        return res;
    }
}