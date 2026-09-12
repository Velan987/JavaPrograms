package hellointerview.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphDFSOnMatrics {

    public static void dfs(int[][] matrix) {
        Set<String> visited = new HashSet<>();
        // for testing
         List<Integer> result = new ArrayList<>();
        // up, down, left, right
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        dfsHelper(0, 0, matrix, visited, directions, result);
        System.out.println(result);
    }

    private static void dfsHelper(int r, int c, int[][] matrix, Set<String> visited, int[][] directions, List<Integer> result) {
        // r-row, c-column
        String key = r + "," + c;
        if (visited.contains(key)) {
            return;
        }

        // check if the cell is out of bounds
        if (r < 0 || r >= matrix.length || c < 0 || c >= matrix[0].length) {
            return;
        }

        visited.add(key);
        result.add(matrix[r][c]);
        for (int[] dir : directions) {
            dfsHelper(r + dir[0], c + dir[1], matrix, visited, directions, result);
        }
        return;
    }
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        dfs(matrix);
    }
}

/**
 * DFS on a matrix is similar to DFS on an adjacency list. We still have to keep track of visited nodes, and we recursively call DFS on each neighbor of the current node.
The main difference is that each cell can have at most 4 neighbors (up, down, left, right), and that we need to check if the neighbor is within the bounds of the grid before visiting it.

Use a set to keep track of visited nodes. Each time you visit a node, add it to the set.
If you encounter a node that has already been visited, return immediately without making any further recursive calls.
Use a for loop to iterate over each neighbor of the current node, and recursively call dfs on each neighbor. Before visiting the neighbor, 
check if it is within the bounds of the grid.
 */