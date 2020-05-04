package com.firewings.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 417. 太平洋大西洋水流问题
 * 给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界。
 *
 * 规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。
 *
 * 请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。
 *
 *
 *
 * 提示：
 *
 * 输出坐标的顺序不重要
 * m 和 n 都小于150
 *
 *
 * 示例：
 *
 *
 *
 * 给定下面的 5x5 矩阵:
 *
 *   太平洋 ~   ~   ~   ~   ~
 *        ~  1   2   2   3  (5) *
 *        ~  3   2   3  (4) (4) *
 *        ~  2   4  (5)  3   1  *
 *        ~ (6) (7)  1   4   5  *
 *        ~ (5)  1   1   2   4  *
 *           *   *   *   *   * 大西洋
 *
 * 返回:
 *
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (上图中带括号的单元).
 */

/**
 * Done 1
 * @author firewings.r@gmail.com
 * @date 2020-05-03
 */
class Solution417 {

    List<List<Integer>> result = null;

    /**
     *
     * https://blog.csdn.net/HdUIprince/article/details/89055429
     *
     * 若有 N 个定点，E 条边，DFS 时间复杂度
     * N²
     * N
     * N + E
     * 无法确定 答案
     *
     * DFS算法是一一个递归算法，需要借助一个递归工作栈，故它的空间复杂度为O(N)。
     * 遍历图的过程实质上是对每个顶点查找其邻接点的过程，其耗费的时间取决于所采用结构。
     *
     * 邻接表表示时，查找所有顶点的邻接点所需时间为O(E)，访问顶点的邻接点所花时间为O(N),此时，总的时间复杂度为O(N+E)。
     *
     * 邻接矩阵表示时，查找每个顶点的邻接点所需时间为O(N)，要查找整个矩阵，故总的时间度为O(N²)。
     *
     * fastest
     * @param matrix
     * @return
     */
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {

        result = new ArrayList<>();

        if (matrix.length == 0) {
            return result;
        }
        int[][] flag = new int[matrix.length][matrix[0].length];

        // 1   2   2   3  (5) *
        for (int i = 0; i < matrix[0].length; i++) {
            if (flag[0][i] == 0) {
                dfs(0, i, matrix, flag, 1);
            }
        }

        //  3
        //  2
        // (6)
        // (5)
        for (int i = 1; i < matrix.length; i++) {
            if (flag[i][0] == 0) {
                dfs(i, 0, matrix, flag, 1);
            }
        }

        // (5)  1   1   2   4
        for (int i = 0; i < matrix[0].length; i++) {
            if (flag[matrix.length - 1][i] < 2) {
                dfs(matrix.length - 1, i, matrix, flag, 2);
            }
        }

        // (5)
        // (4)
        //  1
        //  5
        for (int i = 0; i < matrix.length - 1; i++) {
            if (flag[i][matrix[0].length - 1] < 2) {
                dfs(i, matrix[0].length - 1, matrix, flag, 2);
            }
        }

        return result;
    }

    private void dfs(int a, int b, int[][] matrix, int[][] flag, int val) {
        flag[a][b] += val;

        if (flag[a][b] == 3) {
            result.add(Arrays.asList(a, b));
        }

        // a - 1 >= 0 防越界
        // a - 1 即 row - 1 向上
        if (a - 1 >= 0 && flag[a - 1][b] < val && flag[a - 1][b] >= matrix[a][b]) {
            dfs(a - 1, b, matrix, flag, val);
        }
        if (b - 1 >= 0 && flag[a][b - 1] < val && flag[a][b - 1] >= matrix[a][b]) {
            dfs(a, b - 1, matrix, flag, val);
        }
        if (a + 1 < matrix.length && flag[a + 1][b] < val && flag[a + 1][b] >= matrix[a][b]) {
            dfs(a + 1, b, matrix, flag, val);
        }
        if (b + 1 < matrix[0].length && flag[a][b + 1] < val && flag[a][b + 1] >= matrix[a][b]) {
            dfs(a, b + 1, matrix, flag, val);
        }
    }
}