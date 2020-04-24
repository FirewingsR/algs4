package com.firewings.leetcode;

/**
 * 785. 判断二分图
 * 给定一个无向图graph，当这个图为二分图时返回true。
 *
 * 如果我们能将一个图的节点集合分割成两个独立的子集A和B，并使图中的每一条边的两个节点一个来自A集合，一个来自B集合，我们就将这个图称为二分图。
 *
 * graph将会以邻接表方式给出，graph[i]表示图中与节点i相连的所有节点。每个节点都是一个在0到graph.length-1之间的整数。这图中没有自环和平行边： graph[i] 中不存在i，并且graph[i]中没有重复的值。
 *
 *
 * 示例 1:
 * 输入: [[1,3], [0,2], [1,3], [0,2]]
 * 输出: true
 * 解释:
 * 无向图如下:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * 我们可以将节点分成两组: {0, 2} 和 {1, 3}。
 *
 * 示例 2:
 * 输入: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * 输出: false
 * 解释:
 * 无向图如下:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * 我们不能将节点分割成两个独立的子集。
 * 注意:
 *
 * graph 的长度范围为 [1, 100]。
 * graph[i] 中的元素的范围为 [0, graph.length - 1]。
 * graph[i] 不会包含 i 或者有重复的值。
 * 图是无向的: 如果j 在 graph[i]里边, 那么 i 也会在 graph[j]里边。
 */

/**
 * 染色法的思路是：
 * 先找到一个没被染色的节点u，把它染上一种颜色，之后遍历所有与它相连的节点v，如果节点v已被染色并且颜色和节点u一样，那么就不是二分图。
 * 如果这个节点v没有被染色，先把它染成与节点u不同颜色的颜色，然后遍历所有与节点v相连的节点...如此递归下去。
 * 本实现使用一个int数组表示每个顶点的染色情况：0 表示未被染色， 1表示染成黑色  2表示染成白色。
*/

/**
 * @author firewings.r@gmail.com
 * @date 2020-04-24
 */
class Solution785 {

    public boolean isBipartite(int[][] graph) {
        if (graph == null || graph.length == 0) {
            return false;
        }

        int v = graph.length;
        int[] colors = new int[v];  // 0未被染色  1黑  2白

        // 要考虑非连通图, 所以要遍历每一个结点
        for (int i = 0; i < v; i++) {
            // lastColor为0
            if (!dfs(graph, i, colors, 0)) {
                return false;
            }
        }

        return true;
    }

    public boolean isBiPartite2(int[][] graph) {
        if (graph == null || graph.length == 0) {
            return false;
        }

        int v = graph.length;
        int[] colors = new int[v];

        for (int i = 0; i < v; i++) {
            if (!dfs(graph, i, colors, 0)) {
                return false;
            }
        }

        return true;
    }

    //i是要测试的结点
    private boolean dfs(int[][] graph, int i, int[] colors, int lastColor) {
        // 注意，被染色的就不要继续染色了（因为这是自底向上的，被染色的点，其相连的节点肯定被染色了）
        // 如果继续对被染色的节点染色，就会导致死循环
        if (colors[i] != 0) {
            return colors[i] != lastColor; //lastColor是上一个结点的颜色
        }

        // 未被染色，染成与相邻结点不同的颜色（lastColor为0时，就染成1）
        colors[i] = lastColor == 1 ? 2 : 1;

        for (int j = 0; j < graph[i].length; j++) {
            if (!dfs(graph, graph[i][j], colors, colors[i]))
                return false;
        }

        return true;
    }

    private boolean dfs2(int[][] graph, int i, int[] colors, int lastColor) {
        if (colors[i] != 0) {
            return colors[i] != lastColor;
        }

        colors[i] = lastColor == 1 ? 2 : 1;

        for (int j = 0; j < graph[i].length; j++) {
            if (!dfs(graph, graph[i][j], colors, colors[i])) {
                return false;
            }
        }

        return true;
    }
}