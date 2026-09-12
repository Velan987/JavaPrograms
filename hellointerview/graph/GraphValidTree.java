package hellointerview.graph;

import java.util.ArrayList;
import java.util.List;

// if there is no cycle in the graph and it should be connected graph then it is a valid tree
// For an undirected graph, a useful property is:
// A connected graph with n nodes is a tree exactly when it has n - 1 edges.
public class GraphValidTree {
    public static boolean graphValidTree(int n, int[][] edges) {
        if (n <= 0) {
            return false;
        }

        // A tree with n nodes must have exactly n - 1 edges.
        if (edges == null || edges.length != n - 1) {
            return false;
        }

        List<List<Integer>> graph = new ArrayList<>();

        for (int node = 0; node < n; node++) {
            graph.add(new ArrayList<>());
        }

        // Build the undirected graph.
        for (int[] edge : edges) {
            int first = edge[0];
            int second = edge[1];

            graph.get(first).add(second);
            graph.get(second).add(first);
        }

        boolean[] visited = new boolean[n];

        dfs(0, graph, visited);

        // Verify that every node was reached.
        for (boolean nodeVisited : visited) {
            if (!nodeVisited) {
                return false;
            }
        }

        return true;
    }

    private static void dfs(
            int node,
            List<List<Integer>> graph,
            boolean[] visited) {

        if (visited[node]) {
            return;
        }

        visited[node] = true;

        for (int neighbor : graph.get(node)) {
            dfs(neighbor, graph, visited);
        }
    }

    public static void main(String[] args) {
        int n = 4;

        int[][] edges = {
                { 0, 1 },
                { 2, 3 }
        };

        System.out.println(graphValidTree(n, edges)); // false
    }
}


/**
 * You are given an integer n and a list of undirected edges where each entry in the list is a pair of integers representing an edge between nodes 0 and n - 1. 
 * You have to write a function to check whether these edges make up a valid tree.

There will be no duplicate edges in the edges list. (i.e. [0, 1] and [1, 0] will not appear together in the list).

Input:

n = 4 
edges = [[0, 1], [2, 3]]
3
2
1
0
Output:

false # the graph is not connected.
 */