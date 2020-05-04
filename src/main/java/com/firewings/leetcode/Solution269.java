package com.firewings.leetcode;

import java.util.*;

/**
 * 269. 火星词典
 * 现有一种使用字母的全新语言，这门语言的字母顺序与英语顺序不同。
 *
 * 假设，您并不知道其中字母之间的先后顺序。但是，会收到词典中获得一个 不为空的 单词列表。因为是从词典中获得的，所以该单词列表内的单词已经 按这门新语言的字母顺序进行了排序。
 *
 * 您需要根据这个输入的列表，还原出此语言中已知的字母顺序。
 *
 *
 *
 * 示例 1：
 *
 * 输入:
 * [
 *   "wrt",
 *   "wrf",
 *   "er",
 *   "ett",
 *   "rftt"
 * ]
 * 输出: "wertf"
 * 示例 2：
 *
 * 输入:
 * [
 *   "z",
 *   "x"
 * ]
 * 输出: "zx"
 * 示例 3：
 *
 * 输入:
 * [
 *   "z",
 *   "x",
 *   "z"
 * ]
 * 输出: ""
 * 解释: 此顺序是非法的，因此返回 ""。
 *
 *
 * 提示：
 *
 * 你可以默认输入的全部都是小写字母
 * 若给定的顺序是不合法的，则返回空字符串即可
 * 若存在多种可能的合法字母顺序，请返回其中任意一种顺序即可
 */

/**
 * Done 1
 * @author firewings.r@gmail.com
 * @date 2020-05-03
 */
class Solution269 {

    int N = 26;

    public String alienOrder(String[] words) {
        boolean[][] adj = new boolean[N][N];
        int[] visited = new int[N];

        buildGraph(words, adj, visited);

        if (illegal) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            if (visited[i] == '0') {
                if (!dfs(adj, visited, sb, i)) {
                    return "";
                }
            }
        }

        return sb.reverse().toString();
    }

    private boolean dfs(boolean[][] adj, int[] visited, StringBuilder sb, int i) {
        visited[i] = '1';
        for (int j = 0; j < N; j++) {
            if (adj[i][j]) {
                if (visited[j] == '1') {
                    return false;
                }
                if (visited[j] == '0') {
                    if (!dfs(adj, visited, sb, j)) {
                        return false;
                    }
                }
            }
        }

        visited[i] = '2';
        sb.append((char) (i + 'a'));

        return true;
    }

    boolean illegal = false;

    private void buildGraph(String[] words, boolean[][] adj, int[] visited) {
        // Arrays.fill(visited, -1);
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                visited[c - 'a'] = '0';
            }
            if (i > 0) {
                String s1 = words[i - 1];
                String s2 = words[i];

                int len = Math.min(s1.length(), s2.length());
                int same = 0;

                for (int j = 0; j < len; j++) {
                    if (s1.charAt(j) != s2.charAt(j)) {
                        adj[s1.charAt(j) - 'a'][s2.charAt(j) - 'a'] = true;
                        break;
                    }
                    same++;
                }

                for (int j = len; j < s1.length(); j++) {
//                    String a = s1.substring(0, len - 1);
//                    String b = s2.substring(0, len - 1);

//                    if (a.equals(b) && !a.equals("")) {
                    if (same == len && same > 0) {
                        illegal = true;
                        return;
                    }
                }
            }
        }
    }
}

class Solution269Graph {

    public static String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }

        StringBuilder res = new StringBuilder();

        HashMap<Character, Set<Character>> graph = new HashMap<>();

        int[] degree = new int[26];

        int count = 0;

        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (degree[c - 'a'] == 0) {
                    count++;
                    degree[c - 'a'] = 1;
                }
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            char[] cur = words[i].toCharArray();
            char[] next = words[i + 1].toCharArray();
            // corner case
            // ["abc", "ab"]
            if (cur.length > next.length && words[i].startsWith(words[i + 1])) {
                return "";
            }

            // normal case, created the graph
            int len = Math.min(cur.length, next.length);
            for (int j = 0; j < len; j++) {
                if (cur[j] != next[j]) {
                    if (!graph.containsKey(cur[j])) {
                        graph.put(cur[j], new HashSet<>());
                    }
                    if (graph.get(cur[j]).add(next[j])) {
                        degree[next[j] - 'a']++;
                    }
                    break;
                }
            }
        }

        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < 26; i++) {
            if (degree[i] == 1) {
                queue.offer((char) ('a' + i));
            }
        }

        while (!queue.isEmpty()) {
            char c = queue.poll();
            res.append(c);
            if (graph.containsKey(c)) {
                for (char ch : graph.get(c)) {
                    if (--degree[ch - 'a'] == 1) {
                        queue.offer(ch);
                    }
                }
            }
        }

        if (res.length() != count) {
            return "";
        }

        return res.toString();
    }
}