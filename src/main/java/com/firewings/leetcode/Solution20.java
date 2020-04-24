package com.firewings.leetcode;

/**
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 * 示例 2:
 *
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 *
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 *
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 *
 * 输入: "{[]}"
 * 输出: true
 */

/**
 * @author firewings.r@gmail.com
 * @date 2020-04-24
 */
class Solution20 {
//     public boolean isValid(String s) {
//         if (s == null) {
//             return false;
//         }

//         int len = s.length();

//         if (len % 2 != 0){
//             return false;
//         }

//         if (len == 0) {
//             return true;
//         }

//         Stack<Character> stack = new Stack<>();

//         stack.push(s.charAt(0));

//         for (int i = 1; i < len; i++) {

//             char c2 = s.charAt(i);

//             if (stack.isEmpty()) {
//                 stack.push(c2);
//                 continue;
//             }

//             char c1 = stack.peek();

//             if (c1 == '(' && c2 == ')') {
//                 stack.pop();
//             } else if (c1 == '[' && c2 == ']') {
//                 stack.pop();
//             } else if (c1 == '{' && c2 == '}') {
//                 stack.pop();
//             } else {
//                 stack.push(c2);
//             }
//         }

//         return stack.isEmpty();
//     }


    public boolean isValid(String s) {
        if (s.equals("")) {
            return true;
        } else if (s.length() % 2 != 0) {
            return false;
        } else {
            char[] chars = s.toCharArray();
            char[] temp = new char[chars.length + 1];
            int p = 1;
            for (int i = 0; i < chars.length; i++) {
                switch (chars[i]) {
                    case '(':
                    case '[':
                    case '{':
                        temp[p++] = chars[i];
                        break;
                    case ')':
                        if (temp[--p] != '(') {
                            return false;
                        }
                        break;
                    case ']':
                        if (temp[--p] != '[') {
                            return false;
                        }
                        break;
                    case '}':
                        if (temp[--p] != '{') {
                            return false;
                        }
                        break;
                }
            }
            return p == 1;
        }
    }
}