package com.firewings.leetcode;

/**
 * 516. 最长回文子序列
 * 给定一个字符串s，找到其中最长的回文子序列。可以假设s的最大长度为1000。
 *
 * 示例 1:
 * 输入:
 *
 * "bbbab"
 * 输出:
 *
 * 4
 * 一个可能的最长回文子序列为 "bbbb"。
 *
 * 示例 2:
 * 输入:
 *
 * "cbbd"
 * 输出:
 *
 * 2
 * 一个可能的最长回文子序列为 "bb"。
 */

/**
 * Done 1
 * @author firewings.r@gmail.com
 * @date 2020-05-03
 */
class Solution516 {

    public int longestPalindromeSubseq(String s) {
        char[] chars = s.toCharArray();
        int n = s.length();

        int[] cur = new int[n];
        int[] pre = new int[n];

        // i start
        for (int i = n - 1; i >= 0; i--) {
            cur[i] = 1;
            // j end
            for (int j = i + 1; j < n; j++) {
                if (chars[i] == chars[j]) {
                    cur[j] = pre[j - 1] + 2;
                } else {
                    cur[j] = Math.max(pre[j], cur[j - 1]);
                }
            }
            int[] tmp = pre;
            pre = cur;
            cur = tmp;
        }

        return pre[n - 1];
    }


    public int longestPalindromeSubseq2(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int[][] dp = new int[n][n];
        // n - 1 ~ 0 右到左遍历 start
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            // 1 ～ n - 1 左到右 end
            for (int j = i + 1; j < n; j++) {
                if (arr[i] == arr[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}