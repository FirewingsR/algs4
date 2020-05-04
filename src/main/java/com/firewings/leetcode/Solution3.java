package com.firewings.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */

/**
 * Done 1
 * @author firewings.r@gmail.com
 * @date 2020-05-03
 */
class Solution3 {

    /**
     * fastest
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        // 重复元素上一次出现的位置很重要
        int len = s.length();
        if (len < 2) {
            return len;
        }
        
        int[] window = new int[128];
        for (int i = 0; i < 128; i++) {
            window[i] = -1;
        }

        int res = 1;
        int l = 0;

        char[] chars = s.toCharArray();

        for (int r = 0; r < len; r++) {
            if (window[chars[r]] != -1) {
                l = Math.max(l, window[chars[r]] + 1);
            }
            window[chars[r]] = r;
            res = Math.max(res, r - l + 1);
        }
        
        return res;
    }
    
    int lengthOfLongestSubstring1(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;

        for (int l = 0, r = 0; r < s.length(); r++) {
            if (map.containsKey(s.charAt(r))) {
                l = Math.max(l, map.get(s.charAt(r)) + 1);
            }
            
            map.put(s.charAt(r), r);
            max = Math.max(max, r - l + 1);
        }
        
        return max;
    }
    
    int lengthOfLongestSubstring2(String s) {
         
        Set<Character> set = new HashSet<>();
         
        int max = 0;
         
        for (int i = 0, j = 0; j < s.length(); j++) {
            while (set.contains(s.charAt(j))) {
                set.remove(s.charAt(i));
                i++;
            }
            set.add(s.charAt(j));
            max = Math.max(max, set.size());
        }
         
        return max;
                                                    
    }

    /**
     * 自己写
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring3(String s) {
        
        int n = s.length();
        if (n < 2) {
            return n;
        }
        
        char[] chars = s.toCharArray();
        
        int[] pos = new int[128];
        
        int start = 0;
        int ans = 0;
        
        for (int i = start; i < n; i++) {
            
            if (pos[chars[i]] == 0) {
                pos[chars[i]] = i + 1;
            } else {
                ans = Math.max(ans, i - start);
                
                // System.out.println(i + " " + start);
                // System.out.println(ans);
                
                pos = new int[128];
                
                i = start;
                start++;
            }
        }
        
        ans = Math.max(ans, n - start);
        
        return ans;
    }
}