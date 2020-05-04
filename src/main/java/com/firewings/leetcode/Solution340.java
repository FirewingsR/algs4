package com.firewings.leetcode;

/**
 * 340. 至多包含 K 个不同字符的最长子串
 * 给定一个字符串 s ，找出 至多 包含 k 个不同字符的最长子串 T。
 *
 * 示例 1:
 *
 * 输入: s = "eceba", k = 2
 * 输出: 3
 * 解释: 则 T 为 "ece"，所以长度为 3。
 * 示例 2:
 *
 * 输入: s = "aa", k = 1
 * 输出: 2
 * 解释: 则 T 为 "aa"，所以长度为 2。
 */

/**
 * Done 1
 * @author firewings.r@gmail.com
 * @date 2020-05-03
 */
class Solution340 {
   public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] a = new int[128];
        int j = 0;
        int count = 0;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (a[s.charAt(i)]++ == 0) {
                count++;
            }
            while (count > k) {
                a[s.charAt(j)]--;
                if (a[s.charAt(j)] == 0) {
                    count--;
                }
                j++;
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}