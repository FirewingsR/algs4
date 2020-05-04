package com.firewings.leetcode;

import java.util.HashMap;

/**
 * 1036. 逃离大迷宫
 * 在一个 10^6 x 10^6 的网格中，每个网格块的坐标为 (x, y)，其中 0 <= x, y < 10^6。
 *
 * 我们从源方格 source 开始出发，意图赶往目标方格 target。每次移动，我们都可以走到网格中在四个方向上相邻的方格，只要该方格不在给出的封锁列表 blocked 上。
 *
 * 只有在可以通过一系列的移动到达目标方格时才返回 true。否则，返回 false。
 *
 *
 *
 * 示例 1：
 *
 * 输入：blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
 * 输出：false
 * 解释：
 * 从源方格无法到达目标方格，因为我们无法在网格中移动。
 * 示例 2：
 *
 * 输入：blocked = [], source = [0,0], target = [999999,999999]
 * 输出：true
 * 解释：
 * 因为没有方格被封锁，所以一定可以到达目标方格。
 *
 *
 * 提示：
 *
 * 0 <= blocked.length <= 200
 * blocked[i].length == 2
 * 0 <= blocked[i][j] < 10^6
 * source.length == target.length == 2
 * 0 <= source[i][j], target[i][j] < 10^6
 * source != target
 */

/**
 * Done 1
 * @author firewings.r@gmail.com
 * @date 2020-05-03
 */
class Solution1036 {

    Long M = 1000000L;

    HashMap<Long, Long> hash;

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {

        if (blocked == null || blocked.length == 0) {
            return true;
        }

        hash = new HashMap<>();

        for (int[] p : blocked) {
            long key = p[0] * M + p[1];
            hash.put(key, 1L);
        }

        return dfs(source, source[0], source[1], target) && dfs(target, target[0], target[1], source);
    }

    boolean dfs(int[] origin, int x, int y, int[] target) {

        if (x < 0 || x >= M || y < 0 || y >= M) {
            return false;
        }

        if (Math.abs(origin[0] - x) >= 200 || Math.abs(origin[1] - y) >= 200) {
            return true;
        }

        if (x == target[0] && y == target[1]) {
            return true;
        }

        long key = x * M + y;

        if (hash.get(key) == null) {
            hash.put(key, 1L);
            boolean ans = dfs(origin, x - 1, y, target)
                    || dfs(origin, x + 1, y, target)
                    || dfs(origin, x, y - 1, target)
                    || dfs(origin, x, y + 1, target);
            // hash.remove(key);
            if (ans) {
                hash.remove(key);
                return true;
            }
        }

        return false;

    }

//     Long M = 1000000L;

//     int[][] dirs = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

//     public boolean isEscapePossible(int[][] bs, int[] s, int[] t) {
//         Set<Long> b = new HashSet<>();
//         for (int[] n : bs) b.add(n[0] * M + n[1]);
//         return check(b, s, t, s, new HashSet<>()) && check(b, t, s, t, new HashSet<>());//make sure that both s ant t will not be surrouded by the block.
//     }


//     boolean check(Set<Long> b, int[] s, int[] t, int[] p, Set<Long> v) {
//         if (Math.abs(p[0] - s[0]) == 200 || Math.abs(p[1] - s[1]) == 200 || v.size() > 0 && p[0] == t[0] && p[1] == t[1])
//             return true;

//         v.add(p[0] * M + p[1]);
//         for (int[] dir : dirs) {
//             int x = p[0] + dir[0], y = p[1] + dir[1];
//             if (x < 0 || x == M || y < 0 || y == M || v.contains(x * M + y) || b.contains(x * M + y)) continue;
//             if (check(b, s, t, new int[]{x, y}, v)) return true;
//         }
//         return false;
//     }
}