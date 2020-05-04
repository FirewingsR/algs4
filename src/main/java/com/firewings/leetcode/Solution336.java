package com.firewings.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 336. 回文对
 * 给定一组唯一的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
 *
 * 示例 1:
 *
 * 输入: ["abcd","dcba","lls","s","sssll"]
 * 输出: [[0,1],[1,0],[3,2],[2,4]]
 * 解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
 * 示例 2:
 *
 * 输入: ["bat","tab","cat"]
 * 输出: [[0,1],[1,0]]
 * 解释: 可拼接成的回文串为 ["battab","tabbat"]
 */

/**
 * Done 1
 * @author firewings.r@gmail.com
 * @date 2020-05-03
 */
class Solution336 {

    public List<List<Integer>> palindromePairs(String[] words) {
        //构建trie
        TrieNode root = buildTrie(words);
        List<List<Integer>> res = new ArrayList<>();
        //对于每个单词,在trie中搜索
        for (int i = 0; i < words.length; i++) {
            search(words[i], i, root, res);
        }
        return res;
    }

    private void search(String word, int i, TrieNode node, List<List<Integer>> res) {
        int k = word.length(), j = 0;
        for (; j < k; j++) {
            //循环中在trie中发现的单词是比当前word长度要短的
            char c = word.charAt(j);
            if (node.index != -1 && isPalindrome(word, j, k - 1)) {
                res.add(Arrays.asList(i, node.index));
            }
            //所有可能被排除，提前退出
            if (node.child[c - 'a'] == null) {
                return;
            }

            node = node.child[c - 'a'];
        }
        //长度等于当前搜索的word的单词
        if (node.index != -1 && node.index != i) {
            res.add(Arrays.asList(i, node.index));
        }
        //长度长于当前搜索的word的单词
        for (int rest : node.belowIsPa) {
            res.add(Arrays.asList(i, rest));
        }
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            addWord(root, words[i], i);
        }
        return root;
    }

    /**
     *
     * @param node
     * @param word
     * @param i     第 i 个字符串
     */
    private void addWord(TrieNode node, String word, int i) {
        for (int j = word.length() - 1; j >= 0; j--) {
            if (isPalindrome(word, 0, j)) {
                node.belowIsPa.add(i);
            }
            char c = word.charAt(j);
            if (node.child[c - 'a'] == null) {
                node.child[c - 'a'] = new TrieNode();
            }
            node = node.child[c - 'a'];
        }
        node.index = i;
    }

    private boolean isPalindrome(String word, int i, int j) {
        if (word.length() <= 1) {
            return true;
        }
        while (i < j) {
            if (word.charAt(i++) != word.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

    static class TrieNode {
        int index; // 题目要求返回下标，因为这里记录下标
        List<Integer> belowIsPa; // 记录节点向后所有的剩余能构成回文的字符串下标
        TrieNode[] child;

        public TrieNode() {
            index = -1;
            belowIsPa = new ArrayList<>();
            child = new TrieNode[26];
        }
    }
}

