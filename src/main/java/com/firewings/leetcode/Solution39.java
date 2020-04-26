package com.firewings.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 39. 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 *
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 */

/**
 * @author firewings.r@gmail.com
 * @date 2020-04-25
 */
class Solution39 {
    
     public List<List<Integer>> combinationSum(int[] candidates, int target) {
         List<List<Integer>> ans = new ArrayList<>();
         Stack<Integer> stack = new Stack<>();
         backTracking(candidates, target, 0, stack, ans);
        
         return ans;
     }
    
     private void backTracking(int[] candidates, int target, int start, Stack<Integer> stack, List<List<Integer>> ans) {
         if (target < 0) {
             return;
         }
        
         if (target == 0) {
             ArrayList<Integer> l = new ArrayList<>();
             // l.addAll(stack);
             for (int i = 0; i < stack.size(); i++) {
                 l.add(stack.get(i));
             }
             ans.add(l);
             return;
         }
        
         for (int i = start; i < candidates.length; i++) {
             if (candidates[i] <= target) {
                 stack.push(candidates[i]);
                 backTracking(candidates, target - candidates[i], i, stack, ans);
                 stack.pop();
             }
         }
     }


//    public List<List<Integer>> combinationSum(int[] candidates, int target) {
//        List<List<Integer>> res = new ArrayList<>();
//        int[] one = new int[target];
//        dfs(candidates, 0, target, res, one, 0);
//        return res;
//    }
//
//    public void dfs(int[] candidates, int idx, int remain, List<List<Integer>> res,
//                    int[] one, int last) {
//
//        //不能再进入下一层
//        if (remain == 0) {
//            List<Integer> list = new ArrayList<>();
//            for (int i = 0; i < idx; i++) {
//                list.add(one[i]);
//            }
//            res.add(list);
//            return;
//        }
//
//        if (remain < 0) return;
//
//        //穷举所有的组合可能，选择一种进入下一层
//        for (int i = last; i < candidates.length; i++) {
//            if (candidates[i] <= remain) {
//                one[idx] = candidates[i];
//                dfs(candidates, idx + 1, remain - candidates[i], res, one, i);
//            }
//        }
//    }
}