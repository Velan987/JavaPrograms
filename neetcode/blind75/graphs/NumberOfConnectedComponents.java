package neetcode.blind75.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NumberOfConnectedComponents {
    /**
     * Basic idea is, we will have one boolean array(visited), since node values are starting from 0 to n-1 then we can use node values as array index
     * visited array will be initialized with false
     * will start from node 0, if that node is not already visited then increment the count and visit all of its connected nodes
     * for example [0,1],[1,2],[3,4]
     * here for i=0, visited[i] will be false and count will be 1, and all of its connected components will be visited using dfs
     * visited of 0, 1, 2 will become true in one iteration
     * for i=1, 2 visited will be true, so no increment and no invocation of dfs
     * for i=3, visited will be false, invoke dfs
     */
    public int countComponents(int n, int[][] edges) {
        // nodes will have values from 0 to n-1 only, so node values we can use as array index
        boolean[] visited = new boolean[n];
        int connectedComponents = 0;
        // node value will be the index of the arraylist
        // value will be the list of its destination (neighbours)
        List<List<Integer>> neighbours = new ArrayList<>();

        //initialize neighbours with arraylist object
        for(int i=0; i<n;i++){
            neighbours.add(new ArrayList<>());
        }
        for(int[] edge: edges){
            int src = edge[0];
            int dest = edge[1];
            // it is an undirected graph, so source is the neighbour of destination and dest is the neighbour of src
            neighbours.get(src).add(dest);
            neighbours.get(dest).add(src);
        }

        for(int i=0; i<n; i++){
            // if the node is not visited already then increment connectedComponents and visit every node in that connected component
            // in dfs it will visit every node in the connected component
            if(!visited[i]){
                connectedComponents ++;
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                dfs(neighbours, visited, queue);
            }
        }

        return connectedComponents;
    }

    // Visit every node in the connected graph
    private void dfs(List<List<Integer>> neibhours, boolean[] visited, Queue<Integer> queue){
        while(!queue.isEmpty()){
            int node = queue.poll();
            visited[node] = true;
            List<Integer> neighbourList = neibhours.get(node);
            for(int neighbour: neighbourList){
                if(!visited[neighbour])
                    queue.offer(neighbour);
            }
        }
    }
}

/**
 * Number of Connected Components in an Undirected Graph

You have an undirected graph of n nodes labeled from 0 to n - 1. 
You are given an integer n and an array edges where edges[i] = [aᵢ, bᵢ] indicates that there is an edge between aᵢ and bᵢ in the graph.

Return the number of connected components in the graph.


Example 1:
Input:
n = 5, edges = [[0,1],[1,2],[3,4]]

Output: 2


Example 2:
Input:
n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]

Output: 1

Constraints:
1 <= n <= 2000
1 <= edges.length <= 5000
edges[i].length == 2
0 <= aᵢ < n
0 <= bᵢ < n
aᵢ != bᵢ
There are no repeated edges.
 */