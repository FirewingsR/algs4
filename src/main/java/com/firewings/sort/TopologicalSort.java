package com.firewings.sort;

import java.util.*;

/**
 * @author firewings.r@gmail.com
 * @date 2020-04-25
 */
public class TopologicalSort {
    /**
     * Get topological ordering of the input directed graph
     *
     * @param numCourses             number of nodes in the graph
     * @param prerequisites adjacency list representation of the input directed graph
     * @return topological ordering of the graph stored in an List<Integer>.
     */
    public int[] sort(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        int[] indegree = new int[numCourses];
        List<Integer>[] graph = new List[numCourses];
        for (int i = 0; i < numCourses; i++) graph[i] = new ArrayList<>();
        for (int[] pair : prerequisites) {
            graph[pair[1]].add(pair[0]);
            indegree[pair[0]]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }
        int cnt = 0;
        while (!q.isEmpty()) {
            int p = q.poll();
            res[cnt++] = p;
            for (int node : graph[p]) {
                if (--indegree[node] == 0) q.add(node);
            }
        }
        if (cnt == numCourses) return res;
        else return new int[0];
    }

    public static void main(String[] args) {
        // int[][] arr = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int[][] arr = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int[] ans = new TopologicalSort().sort(arr.length, arr);
        System.out.println(Arrays.toString(ans));
    }
}
