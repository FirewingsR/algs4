package com.firewings.lagou;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    int[] B;
    int[] dx = new int[]{-1, 0, 1, 0};
    int[] dy = new int[]{0, -1, 0, 1};

    void bfs(int[][] maze, int x, int y) {
        Queue<Integer[]> queue = new LinkedList<>();
        queue.add(new Integer[]{x, y});

        while (!queue.isEmpty()) {
            Integer[] pos = queue.poll();
            x = pos[0];
            y = pos[1];

            for (int d = 0; d < 4; d++) {
                int i = x + dx[d], j = y + dy[d];

                if (isSafe(maze, i, j)) {
                    maze[i][j] = maze[x][y] + 1;
                    queue.add(new Integer[]{i, j});
                    if (i == B[0] && j == B[1]) return;
                }
            }
        }
    }

    int N = 10;

    private boolean isSafe(int[][] maze, int i, int j) {
        return false;
    }

    private boolean isSafe(int[][] maze, int i, int j, int z, boolean[][][] visited) {
        return false;
    }

    int bfs2(int[][] maze, int x, int y, int w) {

        int steps = 0, z = 0;

        Queue<Integer[]> queue = new LinkedList<>();
        queue.add(new Integer[]{x, y, z});
        queue.add(null);

        boolean[][][] visited = new boolean[N][N][w + 1];
        visited[x][y][z] = true;

        while (!queue.isEmpty()) {
            Integer[] pos = queue.poll();

            if (pos != null) {
                x = pos[0];
                y = pos[1];
                z = pos[2];
                if (x == B[0] && y == B[1]) {
                    return steps;
                }
                for (int d = 0; d < 4; d++) {
                    int i = x + dx[d], j = y + dy[d];
                    if (!isSafe(maze, i, j, z, visited)) {
                        continue;
                    }
                    int k = getLayer(maze, w, i, j, z);
                    if (k >= 0) {
                        visited[i][j][k] = true;
                        queue.add(new Integer[]{i, j, k});
                    }
                }
            } else {
                steps++;
                if (!queue.isEmpty()) {
                    queue.add(null);
                }
            }
        }

        return -1;
    }


    int getLayer(int[][] maze, int w, int x,
                 int y, int z) {
        if (maze[x][y] == -1) {
            return z < w ? z + 1 : -1;
        }
        return z;
    }
}
