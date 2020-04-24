package com.firewings.leetcode;

/**
 * 208. 实现 Trie (前缀树)
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * 示例:
 *
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 *
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 */

/**
 * @author firewings.r@gmail.com
 * @date 2020-04-24
 */
class Trie208 {
    
//     static class Node {
        
//         int level = 0;
    
//         char val = '/';
        
//         boolean isEnd;
        
//         Map<Character, Node> children;
        
//         public Node(int level, char val) {
//             this.level = level;
//             this.val = val;
//             this.children = new HashMap<>();
//         }
//     }
    
//     Node root = new Node(0, '/');
    
//     /** Initialize your data structure here. */
//     public Trie() {
//     }
    
//     /** Inserts a word into the trie. */
//     public void insert(String word) {
        
//         int len = word.length();
//         Node trie = root;
        
//         for (int i = 0; i < len; i++) {
            
//             char c = word.charAt(i);
//             Node node = trie.children.get(c);
            
//             if (node == null) {
//                 node = new Node(i + 1, c);
//                 trie.children.put(c, node);
//             }
            
//             if (i == len - 1) {
//                 node.isEnd = true;
//             }
            
//             trie = node;
//         }
//     }
    
//     /** Returns if the word is in the trie. */
//     public boolean search(String word) {
        
//         int len = word.length();
//         Node trie = root;
        
//         for (int i = 0; i < len; i++) {
            
//             char c = word.charAt(i);
//             Node node = trie.children.get(c);
            
//             if (node == null) {
//                 return false;
//             }
            
//             trie = node;
//         }
        
//         return trie.isEnd;
//     }
    
//     /** Returns if there is any word in the trie that starts with the given prefix. */
//     public boolean startsWith(String prefix) {
//         int len = prefix.length();
        
//         Node trie = root;
        
//         for (int i = 0; i < len; i++) {
            
//             char c = prefix.charAt(i);
//             Node node = trie.children.get(c);
            
//             if (node == null) {
//                 return false;
//             }
            
//             trie = node;
//         }
        
//         return true;
//     }

    private static class TrieNode {
        private TrieNode[] next;
        private boolean isEnd;

        TrieNode() {
            next = new TrieNode[26];
            isEnd = false;
        }
    }

    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public Trie208() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode tmp = root;
        for (char c : word.toCharArray()) {
            int n = c - 'a';
            if (tmp.next[n] == null) {
                tmp.next[n] = new TrieNode();
            }
            tmp = tmp.next[n];
        }
        tmp.isEnd = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode tmp = root;
        for (char c : word.toCharArray()) {
            int n = c - 'a';
            if (tmp.next[n] == null) {
                return false;
            }
            tmp = tmp.next[n];
        }
        return tmp.isEnd;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode tmp = root;
        for (char c : prefix.toCharArray()) {
            int n = c - 'a';
            if (tmp.next[n] == null) {
                return false;
            }
            tmp = tmp.next[n];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */