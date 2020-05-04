package com.firewings.leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 772. 基本计算器 III
 * 实现一个基本的计算器来计算简单的表达式字符串。
 *
 * 表达式字符串可以包含左括号 ( 和右括号 )，加号 + 和减号 -，非负 整数和空格 。
 *
 * 表达式字符串只包含非负整数， +, -, *, / 操作符，左括号 ( ，右括号 )和空格 。整数除法需要向下截断。
 *
 * 你可以假定给定的字符串总是有效的。所有的中间结果的范围为 [-2147483648, 2147483647]。
 *
 *
 *
 * 一些例子：
 *
 * "1 + 1" = 2
 * " 6-4 / 2 " = 4
 * "2*(5+5*2)/3+(6/2+8)" = 21
 * "(2+6* 3+5- (3*14/7+2)*5)+3"=-12
 */

/**
 * Done 1
 * @author firewings.r@gmail.com
 * @date 2020-05-03
 */
class Solution772 {

    public int calculate(String s) {
        int position[] = {0};

        return help(s, position);
    }

    int help(String s, int[] position) {

        char sign = '+';

        int num = 0;

        Stack<Integer> stack = new Stack<>();

        for (int i = position[0]; i < s.length(); i++) {

            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }

            // 遇到左括号 就需要 call sub problem
            // 要注意sub的起始位置
            // 得到sub的结果后，要回到 sub 结束位置

            if (c == '(') {
                position[0] = i + 1;

                num = help(s, position);

                // 处理 i++ 所以要减 1
                i = position[0] - 1;
            }

            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                int pre;

                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(num * -1);
                        break;
                    case '*':
                        pre = stack.pop();
                        stack.push(pre * num);
                        break;
                    case '/':
                        pre = stack.pop();
                        stack.push(pre / num);
                        break;
                }

                sign = c;
                num = 0;
            }

            // 遇到反括号，表示当前计算已经结束，可以对stack进行总和计算了
            if (c == ')') {

                position[0] = i + 1;

                break;
            }

        }

        int ans = 0;

        while(!stack.isEmpty()) {
            ans += stack.pop();
        }

        return ans;
    }
}

class Solution772PPT {

    int calculate(String s) {
        Queue<Character> queue = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (c != ' ') queue.offer(c);
        }
        queue.offer('+');
        return calculate(queue);
    }

    int calculate(Queue<Character> queue) {

        char sign = '+';

        int num = 0;

        Stack<Integer> stack = new Stack<>();

        while (!queue.isEmpty()) {
            char c = queue.poll();
            if (Character.isDigit(c)) {
                num = 10 * num + c - '0';
            } else if (c == '(') {
                num = calculate(queue);
            } else {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                }

                num = 0;
                sign = c;
                if (c == ')') {
                    break;
                }
            }
        }

        int sum = 0;

        while (!stack.isEmpty()) {
            sum += stack.pop();
        }

        return sum;
    }
}