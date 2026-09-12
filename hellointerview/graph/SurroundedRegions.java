package hellointerview.graph;

public class SurroundedRegions {
    public String[][] surroundedRegions(String[][] grid) {
        if(grid == null || grid.length ==0){
            return grid;
        }
        int rows = grid.length, cols = grid[0].length;
        // first will mark the "O" cells which are reachable from border to "S" - read solution in the comment to understand fully
        // to achieve this we need to do the DFS for only border cells and go depth until we found "X" and mark all "O" to "S"
        // DFS for first and last columns
        for(int i=0; i<rows; i++){
            // first column
            if(grid[i][0] == "O"){
                dfs(grid, i, 0, rows, cols);
            }
            // last column
            if(grid[i][cols-1] == "O"){
                dfs(grid, i, cols-1, rows, cols);
            }
        }

        // DFS for first and last rows
        for(int i=0; i<cols; i++){
            // first row
            if(grid[0][i] == "O"){
                dfs(grid, 0, i, rows, cols);
            }
            // last row
            if(grid[rows-1][i] == "O"){
                dfs(grid, rows-1, i, rows, cols);
            }
        }

        // in DFS we changed all cells which are reachable from border to "S"
        // now change all "O" cells which are not reachable from border to "X" and at the same time change "S" to "O"
        for(int i=0; i< rows; i++){
            for(int j=0; j<cols; j++){
                if(grid[i][j] == "S"){
                    grid[i][j] = "O";
                }
                // else is important here, otherwise the above marked cells also changed to "X" or we can switch below if statement to run first and then convert S to O
                else if(grid[i][j] == "O"){
                    grid[i][j] = "X";
                }
            }
        }

        return grid;
    }
    public void dfs(String [][]grid, int r, int c, int rows, int cols){
        // Mark the cell as Safe "S" 
        grid[r][c] = "S";

        // above cell has "O"
        if(r-1 >=0 && grid[r-1][c] == "O"){
            dfs(grid, r-1, c, rows, cols);
        }

        // below cell has "O"
        if(r+1 <rows && grid[r+1][c] == "O"){
            dfs(grid, r+1, c, rows, cols);
        }

        // right cell has "O"
        if(c+1 <cols && grid[r][c+1] == "O"){
            dfs(grid, r, c+1, rows, cols);
        }

        // left cell has "O"
        if(c-1 >=0 && grid[r][c-1] == "O"){
            dfs(grid, r, c-1, rows, cols);
        }
        // this is our base case for recursion - adjacent cells are not there or they are not "O"
        return;
    }
}

/**
 * Given an m x n matrix grid containing only characters 'X' and 'O', modify grid to replace all regions of 'O' that are completey surrounded by 'X' with 'X'.

A region of 'O' is surrounded by 'X' if there is no adjacent path (cells that border each other in the N, W, E, S directions) consisting of only 'O' 
from anywhere inside that region to the border of the board.

Input:

grid = [
["X","X","X","X","O"],
["X","X","O","X","X"],
["X","X","O","X","O"],
["X","O","X","X","X"]
["X","O","X","X","X"]
]
Output:

[
["X","X","X","X","O"],
["X","X","X","X","X"],
["X","X","X","X","O"],
["X","O","X","X","X"],
["X","O","X","X","X"]
]
Explanation: The region of O's at grid[1][2] and grid[2][2] is completely surrounded, so we replace them with X's. 
All the other "O"s are connected to the border by a path of all "O"s, so we leave them as is.
 */

/**
 * Solution
 * In order to solve this problem, we can first recognize that there are two types of "Os" in the grid, those that are reachable on a path of connected "O"s starting from the border of the grid, and those that are not.

The "Os" that are reachable from the border are highlighted in light blue, while the ones that aren't a highlighted in dark blue.
The "Os" that are reachable from the border are the ones that we want to keep, while the "Os" that are not reachable from the border are the ones that we want to change to "Xs".
In order for an "O" to be reachable from the border, it must be connected to an "O" that is on the border. This means that we can start from each cell in the border of the grid and use depth-first search to find all the "Os" that are reachable from the border of the grid (by following a path of connected "Os" in the N, E, S, W directions). Whenever we find an "O" that is reachable from the border, we can change its value to "S" to mark it as safe.

All the "Os" that are reachable from the border are marked with "S".
After marking them as safe, we can then iterate through each cell in the grid and change the "Os" that are not marked as safe to "Xs", while also changing the "safe" cells back to "Os".

 */