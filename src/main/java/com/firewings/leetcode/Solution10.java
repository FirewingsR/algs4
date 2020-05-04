package com.firewings.leetcode;

/**
 * 10. 正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 示例 1:
 *
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3:
 *
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 *
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 *
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 */

/**
 * Done 1
 * @author firewings.r@gmail.com
 * @date 2020-05-03
 */
class Solution10 {

    private boolean flag;
    private boolean[][] cache;

    public boolean isMatch(String s, String p) {
        cache = new boolean[s.length()][p.length()];
        backtracking(0, 0, s, p);
        return flag;
    }

    private void backtracking(int sId, int pId, String s, String p) {
        if (flag) {
            return;
        }

        if (sId >= s.length()) {
            while (pId + 1 < p.length() && p.charAt(pId + 1) == '*') {
                pId += 2;
            }
            if (pId >= p.length()) {
                flag = true;
            }
            return;
        }

        if (pId >= p.length()) {
            return;
        }

        if (cache[sId][pId]) {
            return;
        }

        cache[sId][pId] = true;

        char pc = p.charAt(pId);
        char sc = s.charAt(sId);

        if (pId + 1 < p.length() && p.charAt(pId + 1) == '*') {
            if (pc == '.' || pc == sc) {
                backtracking(sId + 1, pId, s, p);
            }
            backtracking(sId, pId + 2, s, p);
        } else if (pc == sc || pc == '.') {
            backtracking(sId + 1, pId + 1, s, p);
        }
    }
}

class Solution10DP {
    boolean isMatch(String s, String p) {

        int m = s.length(), n = p.length();

        boolean[][] dp = new boolean[m + 1][n + 1];

        dp[0][0] = true;

        for (int j = 1; j <= n; j++) {
            // ""
            // "a" "." "*"
            // "**"
            dp[0][j] = j > 1 && p.charAt(j - 1) == '*' && dp[0][j - 2];
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) != '*') {
                    dp[i][j] = dp[i - 1][j - 1] && isMatch(s.charAt(i - 1), p.charAt(j - 1));
                } else {
                    dp[i][j] = dp[i][j - 2] || dp[i - 1][j] && isMatch(s.charAt(i - 1), p.charAt(j - 2));
                }
            }
        }

        return dp[m][n];
    }

    boolean isMatch(char a, char b) {
        return a == b || b == '.';
    }
}

class Solution10PPT {

    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        return isMatch(s, 0, p, 0);
    }

    boolean isMatch(char a, char b) {
        return a == b || b == '.';
    }

    boolean isMatch(String s, int i, String p, int j) {
        int m = s.length();
        int n = p.length();

        if (j == n) {
            return i == m;
        }

        if (j == n - 1 || p.charAt(j + 1) != '*') {
            return (i < m) && isMatch(s.charAt(i), p.charAt(j)) &&
                    isMatch(s, i + 1, p, j + 1);
        }

        if (j < n - 1 && p.charAt(j + 1) == '*') {
            while ((i < m) && isMatch(s.charAt(i), p.charAt(j))) {
                if (isMatch(s, i, p, j + 2)) {
                    return true;
                }
                i++;
            }
        }

        return isMatch(s, i, p, j + 2);
    }
}

/**
 * 太慢了
 */
class Solution10PPT2 {
    boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        return isMatch(s, s.length(), p, p.length());
    }

    boolean isMatch(char a, char b) {
        return a == b || b == '.';
    }

    boolean isMatch(String s, int i, String p, int j) {
        if (j == 0) {
            return i == 0;
        }
        if (i == 0) {
            return j > 1 && p.charAt(j - 1) == '*' && isMatch(s, i, p, j - 2);
        }
        if (p.charAt(j - 1) != '*') {
            return isMatch(s.charAt(i - 1), p.charAt(j - 1)) && isMatch(s, i - 1, p, j - 1);
        }

        return isMatch(s, i, p, j - 2) ||
                isMatch(s, i - 1, p, j) && isMatch(s.charAt(i - 1), p.charAt(j - 2));
    }
}