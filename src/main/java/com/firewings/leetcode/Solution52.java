package com.firewings.leetcode;

/**
 * 52. N皇后 II
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 *
 *
 * 上图为 8 皇后问题的一种解法。
 *
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 *
 * 示例:
 *
 * 输入: 4
 * 输出: 2
 * 解释: 4 皇后问题存在如下两个不同的解法。
 * [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 *
 *
 * 提示：
 *
 * 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。
 * 当然，她横、竖、斜都可走一或七步，可进可退。（引用自 百度百科 - 皇后 ）
 */

/**
 * @author firewings.r@gmail.com
 * @date 2020-04-25
 */
class Solution52 {

    public int res = 0;

    // 最快
    public int totalNQueens(int n) {
        if (n == 1) {
            return 1;
        }
        if (n <= 3) {
            return 0;
        }

        dfs(n, 0, 0, 0, 0);

        return res;
    }

    /**
     *
     * @param n   边界
     * @param row 行
     * @param col 列
     * @param pie 撇
     * @param na  捺
     *
     * col, pie, na分别表示 纵列，撇，捺 皇后放的位置
     */

    /**
     * 两个核心位运算：1.x & -x 这个操作可以保留最后一位1，而其他位都会清零
     * 比如，一个8位数，00110110 进行了这个操作后就会变为 00000010 我在这里想了很久，也没明白原因，后来发现原来是之前学的东西都还给了老师...
     * 这里的关键是：计算机存储数的时候存储的是补码，正数的补码是其本身，而负数的补码是其反码加1
     * 因此，00110110加一个负号以后就变成了10110110（姑且认为最高位是符号位），
     * 其反码为11001001，补码为11001010。这个跟原来的数按位与后就是00000010，神奇✔
     * <p>
     * 2.x & (x - 1) 将最后一位1变为0 可以先这么考虑一下，最低位如果是1，减1后就变为了0，按位与后，其他位不发生变化，最低位清为0
     * 现在考虑更复杂的情况，如果最低位是0，那么减1后，这个0就变为了1，并且向高位借位，直到遇到第一个1，这个1会因为之前的借位变成0，此时借位清0，更高位的数据就不会发生变化
     * 这样按位与后，最低位的1就变成了0，而其他的数并没有变化
     */

    public void dfs(int n, int row, int col, int pie, int na) {
        if (row >= n) {
            res++;
            return;
        }

        int bits = ((~(col | pie | na)) & ((1 << n) - 1)); // 1

        while (bits > 0) { // 2
            int p = bits & (-bits); // 3
            dfs(n, row + 1, col | p, (pie | p) << 1, (na | p) >> 1); // 4
            bits &= (bits - 1); // 5
        }
    }

    /**
     * 1.(1 << n) - 1 这个语句实际上生成了n个1.
     * 这里的1表示可以放置皇后（其实就是初始化了n个1，在不考虑皇后之间可以相互攻击的情况下，n个位置都可以放皇后）
     * ~(col | ld | rd)这里的三个变量分别代表了列以及两个斜线的放置情况。这里的1表示的是不能放置皇后(就是相应的列或斜线上已经放置过皇后了)，这与之前 (1 << n) - 1生成的n个1是不同含义的
     * 因此bits = ~(col | ld | rd) & ((1 << n) - 1)表示的是考虑了相应列、斜线后能放置皇后的位置
     * 举个例子：n=4时，初始化为1111，表示此时4个位置都可以放皇后，
     * 但是和~(col | ld | rd)按位与后变为了0110，表示此时只有第2个和第3个位置是可以放皇后的。
     *
     * 2.当bits>0时，说明bits中还有1存在，就说明遍历还没有完成
     * 而在之后的循环体中，每遍历bits中的一个1，就会将其清0，这就是代码中注释部分5的语句
     *
     * 3.这里的pick就是取出了最后一位1，表示此时遍历的是这种情况
     * 假设bits为0110，取出最后一位1后，就变为0010，就是将皇后放在第3个位置
     *
     * 4.这里是核心：row+1不难理解，就是因为之前已经在row行放置了皇后了，现在应该搜索下一行可能的位置了
     * col | pick就是把目前所有放置皇后的列都计算出来了，比如最开始计算时col是0000，pick是0010,那么col | pick就是0010，意思就是第三列被放置过了
     * 接着说，假设ld是0000，ld | pick就是0010，左移1位后变成了0100，意思就是下一行的第二列也不要放皇后了，因为在这一行的第三列我已经放过了，他们是位于一个斜线上的
     * (rd | pick) >> 1跟(ld | pick) << 1是一个含义，就不赘述了
     */
    // ===============================================================================

    // ppt

    int count;

    int totalNQueens2(int n) {
        if (n == 1) {
            return 1;
        }
        if (n <= 3) {
            return 0;
        }
        count = 0;
        backtracking(n, 0, new int[n]);
        return count;
    }

    void backtracking(int n, int row, int[] columns) {
        // 是否在所有n⾏里都摆放好了皇后?
        if (row == n) {
            count++; // 找到了新的摆放⽅方法
            return;
        }
        // 尝试着将皇后放置在当前⾏中的每⼀列
        for (int col = 0; col < n; col++) {
            columns[row] = col;
            // 检查是否合法，如果合法就继续到下⼀行
            if (check(row, col, columns)) {
                backtracking(n, row + 1, columns);
            }
            // 如果不不合法，就不不要把皇后放在这列中(回溯)
            columns[row] = -1;
        }
    }

    boolean check(int row, int col, int[] columns) {
        for (int r = 0; r < row; r++) {
            if (columns[r] == col || row - r == Math.abs(columns[r] - col)) {
                return false;
            }
        }
        return true;
    }

    // ===============================================================================

    // TODO: 26/4/2020 再看 思路错误

//    static class Node {
//        int x;
//        int y;
//
//        Node(int x, int y) {
//            this.x = x;
//            this.y = y;
//        }
//
//        public String toString() {
//            return x + " " + y;
//        }
//    }
//
//    public int totalNQueens3(int n) {
//
//        if (n == 1) {
//            return 1;
//        }
//        if (n <= 3) {
//            return 0;
//        }
//
//        Stack<Node> stack = new Stack<>();
//
//        int ans = 0;
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if (backTracking(i, j, n, stack)) {
//                    ans++;
//                }
//            }
//        }
//
//        return ans;
//    }
//
//    private boolean backTracking(int I, int J, int n, Stack<Node> stack) {
//
//        stack.clear();
//
//        // System.out.println(new Node(I, J) + "-------");
//
//        for (int i = I; i < n; i++) {
//            if (i > I && J != 0) {
//                J = 0;
//            }
//            for (int j = J; j < n; j++) {
//                if (check(n, stack, i, j)) {
//                    Node node;
//                    stack.push(node = new Node(i, j));
//                }
//                if (stack.size() == n) {
//                    return true;
//                }
//            }
//        }
//
//        return false;
//    }
//
//    private boolean check(int n, Stack<Node> stack, int x, int y) {
//        for (Node queen : stack) {
//            if (queen.x == x || queen.y == y) {
//                return false;
//            }
//            if (Math.abs(queen.x - x) == Math.abs(queen.y - y)) {
//                return false;
//            }
//        }
//        return true;
//    }

    public static void main(String[] args) {
        new Solution52().totalNQueens(5);
    }

}