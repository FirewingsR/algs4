package com.firewings.leetcode;

/**
 * 739. 每日温度
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。
 * 如果之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 */

/**
 * @author firewings.r@gmail.com
 * @date 2020-04-24
 */
class Solution739 {
    
//     class Item {
//         int val;
//         int index;
//         Item (int index, int val) {
//             this.index = index;
//             this.val = val;
//         }
//     }
    
//     public int[] dailyTemperatures(int[] T) {
//         int len = T == null ? 0 : T.length;
        
//         int[] ans = new int[len];
        
//         if (len == 0) {
//             return ans;
//         }
        
//         Stack<Item> stack = new Stack<>();
//         stack.push(new Item(0, T[0]));
        
//         for (int i = 1; i < T.length; i++) {
//             int t = T[i];
            
//             while (!stack.isEmpty()) {
//                 Item item = stack.peek();
                
//                 if (item.val < t) {
//                     ans[item.index] = i - item.index;
//                     stack.pop();
//                 } else {
//                     break;
//                 }
//             }
            
//             stack.push(new Item(i, t));
//         }
        
//         return ans;
//     }

    // TODO: 24/4/2020 再看
    public int[] dailyTemperatures(int[] T) {
        //因为题中对长度有说明[1,30000]
        int len = T.length;
        int[] res = new int[len];
        //可以肯定，最后一天的值为0
        res[len - 1] = 0;
        int i;
        int j;
        for (i = len - 2; i >= 0; i--) {
            int count = 0;
            for (j = i + 1; j < len; j = j + res[j]) {
                if (T[i] < T[j]) {
                    count = j - i;
                    break;
                } else if (res[j] == 0) {
                    count = 0;
                    break;
                }
            }
            res[i] = count;
        }
        return res;
    }
}