package com.firewings.lagou;

import java.util.Stack;

public class DFS {

    int[] B;
    int[] dx = new int[]{-1, 0, 1, 0};
    int[] dy = new int[]{0, -1, 0, 1};

    boolean dfs(int maze[][], int x, int y) {

        if (x == B[0] && y == B[1]) {
            return true;
        }

        maze[x][y] = -1;
        for (int d = 0; d < 4; d++) {
            int i = x + dx[d], j = y + dy[d];
            if (isSafe(maze, i, j) && dfs(maze, i, j)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSafe(int[][] maze, int i, int j) {
        return false;
    }

    boolean dfs2(int maze[][], int x, int y) {
        Stack<Integer[]> stack = new Stack<>();
        stack.push(new Integer[]{x, y});
        maze[x][y] = -1;

        while (!stack.isEmpty()) {
            Integer[] pos = stack.pop();
            x = pos[0];
            y = pos[1];
            if (x == B[0] && y == B[1]) {
                return true;
            }
            for (int d = 0; d < 4; d++) {
                int i = x + dx[d], j = y + dy[d];
                if (isSafe(maze, i, j)) {
                    stack.push(new Integer[]{i, j});
                    maze[i][j] = -1;
                }
            }
        }
        return false;
    }

    int[] A;

    void solve(int maze[][]) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 0 && !(i == A[0] && j == A[1])) {
                    maze[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        dfs(maze, A[0], A[1]);

        if (maze[B[0]][B[1]] < Integer.MAX_VALUE) {
            print("Shortest path count is: " + maze[B[0]][B[1]]);
        } else {
            print("Cannot find B!");
        }
    }

    void print(String s) {
    }

    void dfs3(int maze[][], int x, int y) {
        if (x == B[0] && y == B[1]) return;
        for (int d = 0; d < 4; d++) {
            int i = x + dx[d], j = y + dy[d];

            if (isSafe(maze, i, j) && maze[i][j] > maze[x][y] + 1) {
                maze[i][j] = maze[x][y] + 1;
                dfs(maze, i, j);
            }
        }
    }
}