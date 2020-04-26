package com.firewings.leetcode;

/**
 * 308.二维区域和检索 – 可变
 *
 * 给你一个 2D 矩阵 matrix，请计算出从左上角 (row1, col1) 到右下角 (row2, col2) 组成的矩形中所有元素的和。
 *
 * 上述粉色矩形框内的，该矩形由左上角 (row1, col1) = (2, 1) 和右下角 (row2, col2) = (4, 3) 确定。
 * 其中，所包括的元素总和 sum = 8。
 *
 * 示例：
 *
 * 给定 matrix = [
 *   [3, 0, 1, 4, 2],
 *   [5, 6, 3, 2, 1],
 *   [1, 2, 0, 1, 5],
 *   [4, 1, 0, 1, 7],
 *   [1, 0, 3, 0, 5]
 * ]
 *
 * sumRegion(2, 1, 4, 3) -> 8
 * update(3, 2, 2)
 * sumRegion(2, 1, 4, 3) -> 10
 *
 * 提示:
 * 矩阵的值仅可以被update函数更新
 * 您可以假设对update的调用次数和sumRegion函数的调用次数是均匀分布的。
 * 您可以假设 row1 ≤ row2 并且 col1 ≤ col2.
 */

/**
 * @author firewings.r@gmail.com
 * @date 2020-04-24
 */

// TODO: 26/4/2020 VIP 待验证
public class NumMatrix308 {

    // TODO: 24/4/2020 再看
    private int[][] tree;
    private int[][] nums;

    private int m;
    private int n;

    public NumMatrix308(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        m = matrix.length;
        n = matrix[0].length;

        tree = new int[m + 1][n + 1];
        nums = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                update(i, j, matrix[i][j]);
            }
        }
    }

    public void update(int row, int col, int val) {
        if (m == 0 || n == 0) {
            return;
        }
        int delta = val - nums[row][col];
        nums[row][col] = val;
        // i + i & (-i) 代表着包含这个点的下一个树
        for (int i = row + 1; i <= m; i += i & (-i)) {
            for (int j = col + 1; j <= n; j += j & (-j)) {
                tree[i][j] += delta;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (m == 0 || n == 0) {
            return 0;
        }
        return sum(row2 + 1, col2 + 1)
                - sum(row1, col2 + 1)
                - sum(row2 + 1, col1)
                + sum(row1, col1);
    }

    public int sum(int row, int col) {
        int sum = 0;
        for (int i = row; i > 0; i -= i & (-i)) {
            for (int j = col; j > 0; j -= j & (-j)) {
                sum += tree[i][j];
            }
        }
        return sum;
    }
}
/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */