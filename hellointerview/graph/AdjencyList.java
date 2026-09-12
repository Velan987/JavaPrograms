package hellointerview.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdjencyList {
    public static Map<Integer, List<Integer>> buildAdjList(int n, int[][] edges) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        for (int i = 0; i < n; i++) {
            adjList.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        return adjList;
    }

    public static Set<Integer> dfs(Map<Integer, List<Integer>> adjList) {
        Set<Integer> visited = new HashSet<>();

        if (adjList == null || adjList.isEmpty()) {
            return visited;
        }

        // Handles disconnected components
        for (int node : adjList.keySet()) {
            dfsHelper(node, adjList, visited);
        }

        return visited;
    }

    private static void dfsHelper(
            int node,
            Map<Integer, List<Integer>> adjList,
            Set<Integer> visited) {

        if (!visited.add(node)) {
            return;
        }

        for (int neighbor : adjList.getOrDefault(node, Collections.emptyList())) {
            dfsHelper(neighbor, adjList, visited);
        }
    }
}

/**
 * Given an integer n which represents the number of nodes in a graph, and a list of edges edges, where edges[i] = [ui, vi] represents a bidirectional edge between nodes ui and vi, write a function to return the adjacency list representation of the graph as a dictionary. The keys of the dictionary should be the nodes, and the values should be a list of the nodes each node is connected to.
Example:
n = 4
edges = [[0, 1], [1, 2], [2, 3], [3, 0], [0, 2]]

Output:
{
    0: [1, 3, 2],
    1: [0, 2],
    2: [1, 3, 0],
    3: [2, 0]
}
 */