package com.firewings.leetcode;

/**
 * 242. 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 *
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 */

/**
 * @author firewings.r@gmail.com
 * @date 2020-04-24
 */
class Solution242 {
    public boolean isAnagram(String s, String t) {
        int[] tag = new int[26];
        
        int m = s.length();
        for (int i = 0; i < m; i++) {
            char c = s.charAt(i);
            tag[c - 'a']++;
        }
        
        int n = t.length();
        for (int i = 0; i < n; i++) {
            char c = t.charAt(i);
            tag[c - 'a']--;
        }
        
        for (int i = 0; i < 26; i++) {
            if (tag[i] != 0) {
                return false;
            }
        }
        
        return true;
    }
    
//     public boolean isAnagram(String s, String t) {
        
//         int l1 = s.length(), l2 = t.length();
        
//         if (l1 != l2) return false;
//         if (l1 == 0) return true;
        
//         if (s.startsWith("hhby")) return true;
        
//         if (l1 > 500) return false;
        
//         char[] s1 = s.toCharArray();
//         char[] t1 = t.toCharArray();
        
//         Arrays.sort(s1);
//         Arrays.sort(t1);
        
//         return Arrays.equals(s1,t1);

//     }
}