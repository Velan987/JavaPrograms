package hellointerview.graph;

public class NumberofIsland {
    public static int numIslands(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // iterate each cell, if that particular cell is land then increment the count and invoke dfs to check adjacent lands
                // in dfs we will mark the cell as water, so we wont count same land as part of different island's part.
                // will start with one cell if it is land then island count increased and will mark that land and all its adjacent lands as water, 
                // so that it wont be part of another island
                if (grid[i][j] == 1) {
                    count++;
                    dfs(grid, i, j, rows, cols);
                }
            }
        }

        return count;
    }

    private static void dfs(int[][] grid, int r, int c, int rows, int cols) {
        // in this recursion our base case is - adjacent 4 cells value should not be 0, if it is zero then just return
        grid[r][c] = 0;
        if (r + 1 < rows && grid[r + 1][c] == 1) {
            //up
            dfs(grid, r + 1, c, rows, cols);
        }
        if (r > 0 && grid[r - 1][c] == 1) {
            // down
            dfs(grid, r - 1, c, rows, cols);
        }
        if (c + 1 < cols && grid[r][c + 1] == 1) {
            // right
            dfs(grid, r, c + 1, rows, cols);
        }
        if (c > 0 && grid[r][c - 1] == 1) {
            // left
            dfs(grid, r, c - 1, rows, cols);
        }
        return;
    }
}

/**
 * You are given binary matrix grid of size m x n, where '1' denotes land and '0' signifies water. 
 * Determine the count of islands present in this grid. An island is defined as a region of contiguous land cells connected either vertically or horizontally, 
 * and it is completely encircled by water. Assume that the grid is bordered by water on all sides.

Input:

grid = [
[1,1,0,1],
[1,1,0,1],
[1,1,0,0],
]
Output:
2
first 2 columns of 1 will form one island because borders are considered as water
last column first 2 rows will form one island, so totally 2 islands here.

This solution uses depth-first search to traverse the grid and count the number of islands.
The algorithm starts at the first cell of the grid and explores all the adjacent cells that are part of the same island. To be part of the same island, 
a cell must be a 1 and must be adjacent to the current cell. During each recursive call, the algorithm first marks the current cell by changing its value to 0 
so that it does not explore the same cell again.
 */