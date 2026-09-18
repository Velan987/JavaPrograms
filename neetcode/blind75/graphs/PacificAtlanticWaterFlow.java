package neetcode.blind75.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class PacificAtlanticWaterFlow {
    /**
     * normally water can flow from higher cell to equal or lower neighbouring cell
     * checking every cell individually will cause lot of repeated work 
     * for every cell at all 4 directions we need to check will it reachable to pacific, same thing we need to do for atlantic
     * we may think that we can have a map which tell from a particular cell it is reachable to pacific and not atlantic ([1,1]->pacific:true,atlantic:false), positive scenario it will work but negative scenario it wont
     * for example B reaches pacific but B does not reach Atlantic. now consider cell A, A can flow to both B and C, B can reach Pacific and C can reach Atlantic
     * when we search from A to B, it learns Pacific: true but Atlantic False but actually A can reach atlantc via C.
     * also equal height cells can flow into each other, this might cause infinite recursion. 
     * To avoid all these kind of issues and reduce time complexity we can start from borders and traverse, left and top borders for pacific and bottm and right borders for atlantic
     * since it is reverse direction we need to check that the neighbouring cell height shoulde be equal or higher
     * Starting from an ocean, we find every cell whose water could eventually reach that ocean.
     * one BFS for pacific and one BFS for atlantic
     * will check both oceans reachable matrix, if a cell is reachable to both ocean then return that cell index
     */
    /**
     * pacific is left and top corners only, still we need to check all 4 directions, because it can flow to right and top or bottom and left
     */
    private static final int[][] DIRECTIONS = {
        {-1, 0}, // Up
        {1, 0},  // Down
        {0, -1}, // Left
        {0, 1}   // Right
    };

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();

        if (heights == null || heights.length == 0 ||
                heights[0].length == 0) {
            return result;
        }

        int rows = heights.length;
        int columns = heights[0].length;

        // pacificReachable[r][c] == true then water from (r, c) can reach the Pacific Ocean.
        boolean[][] pacificReachable = new boolean[rows][columns];
        boolean[][] atlanticReachable = new boolean[rows][columns];

        // stores cell waiting to be processed during pacific BFS
        Queue<int[]> pacificQueue = new ArrayDeque<>();
        Queue<int[]> atlanticQueue = new ArrayDeque<>();

        // Left border touches the Pacific.
        // Right border touches the Atlantic.
        for (int row = 0; row < rows; row++) {
            addCell(row, 0, pacificQueue, pacificReachable);
            addCell(row, columns - 1, atlanticQueue, atlanticReachable);
        }

        // Top border touches the Pacific.
        // Bottom border touches the Atlantic.
        for (int column = 0; column < columns; column++) {
            addCell(0, column, pacificQueue, pacificReachable);
            addCell(rows - 1, column, atlanticQueue, atlanticReachable);
        }

        bfs(heights, pacificQueue, pacificReachable);
        bfs(heights, atlanticQueue, atlanticReachable);

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (pacificReachable[row][column] && atlanticReachable[row][column]) {
                    result.add(Arrays.asList(row, column));
                }
            }
        }

        return result;
    }

    private void addCell(int row, int column, Queue<int[]> queue, boolean[][] reachable) {
        /**
         * Reachable array is initialized with false
         * without this if statement we will add (0,0) (0,n),(m,0) and (m,n) twice - because of 2 loops
         */
        if (!reachable[row][column]) {
            // from border cells that ocean is reachable
            reachable[row][column] = true;
            // add that border cell into queue for processing
            queue.offer(new int[]{row, column});
        }
    }

    private void bfs(int[][] heights, Queue<int[]> queue, boolean[][] reachable) {

        int rows = heights.length;
        int columns = heights[0].length;

        while (!queue.isEmpty()) {
            int[] currentCell = queue.poll();

            int currentRow = currentCell[0];
            int currentColumn = currentCell[1];

            for (int[] direction : DIRECTIONS) {
                int nextRow = currentRow + direction[0];
                int nextColumn = currentColumn + direction[1];

                // outside border
                if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns) {
                    continue;
                }
                // already found that it is reachable
                if (reachable[nextRow][nextColumn]) {
                    continue;
                }
                // we are doing BFS in reverse order (from border cells to inner cells), next row column value should be greater or equal
                if (heights[nextRow][nextColumn] < heights[currentRow][currentColumn]) {
                    continue;
                }

                reachable[nextRow][nextColumn] = true;
                queue.offer(new int[]{nextRow, nextColumn});
            }
        }
    }
}

/**
 * You are given a rectangular island heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).

The islands borders the Pacific Ocean from the top and left sides, and borders the Atlantic Ocean from the bottom and right sides.

Water can flow in four directions (up, down, left, or right) from a cell to a neighboring cell with height equal or lower. 
Water can also flow into the ocean from cells adjacent to the ocean.

Find all cells where water can flow from that cell to both the Pacific and Atlantic oceans. 
Return it as a 2D list where each element is a list [r, c] representing the row and column of the cell. You may return the answer in any order.

Input: heights = [
  [4,2,7,3,4],
  [7,4,6,4,7],
  [6,3,5,3,6]
]

Output: [[0,2],[0,4],[1,0],[1,1],[1,2],[1,3],[1,4],[2,0]]
Example 2:

Input: heights = [[1],[1]]

Output: [[0,0],[1,0]]
Constraints:

1 <= heights.length, heights[r].length <= 100
0 <= heights[r][c] <= 1000
 */