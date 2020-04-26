package com.firewings.leetcode;

/**
 * 91. 解码方法
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 *
 * 示例 1:
 *
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 *
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 */

/**
 * @author firewings.r@gmail.com
 * @date 2020-04-25
 */
class Solution91 {
    public int numDecodings(String s) {
        char[] cs = s.toCharArray();
        int n = s.length();
        if (n == 0 || cs[0] == '0') return 0;
        if (n == 1) return 1;
        int a = 1, b = 1;
        for (int i = 1; i < n; i++) {
            int c = 0;
            if (cs[i - 1] == '1' || cs[i - 1] == '2' && cs[i] <= '6') {
                c += a;
            }
            if (cs[i] != '0') c += b;
            System.out.println(a + " " + b + " " + c);
            // TODO: 25/4/2020 再看
            a = b;
            b = c;
        }
        return b;
    }

    public static void main(String[] args) {
        int ans = new Solution91().numDecodings("9371597631128");
        System.out.println(ans);
    }
}