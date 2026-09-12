package hellointerview.graph;

import java.util.LinkedList;
import java.util.Queue;

public class BFSRottenOranges {
    public Integer rotting_oranges(Character[][] grid) {
        if(grid == null || grid.length ==0)
            return -1;

        int [][]directions = new int[][]{
            {-1, 0}, {1, 0},
            {0,-1}, {0, 1}
        };
        int rows = grid.length;
        int cols = grid[0].length;
        // Create a queue to add all rotter orange indexes
        Queue<int []> queue = new LinkedList<>();

        int freshOranges = 0;
        int minutes = 0;

        // Step 1: Initialize BFS Queue and Count Fresh Oranges
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(grid[i][j] == 'R'){
                    queue.offer(new int[]{i,j});
                }else if(grid[i][j] == 'F'){
                    freshOranges++;
                }
            }
        }

        // Step 2: Perform BFS to Simulate Rotting Process
        while(!queue.isEmpty() && freshOranges > 0){
            minutes ++;
            int queueSize = queue.size();
            // process all the rotten oranges at the current minute
            // before starting this while loop we added all rotten oranges position into queue, every minute all rotten oranges can spoil all of its adjacent oranges
            // so we need to process all rotten oranges in the queue at same minute(or same while loop iteration)
            for(int i=0; i<queueSize;i++){
                int[] current = queue.poll();
                int r = current[0];
                int c = current[1];
                for(int[] direction: directions){
                    int nr = r + direction[0];
                    int nc = c + direction[1];
                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 'F') {
                        // Already rotted oranges was added to the queue already
                        // Oranges which are rotten in this minute only we need to add it into the queue
                        grid[nr][nc] = 'R';
                        freshOranges--;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
        }
        
        return freshOranges==0 ? minutes : -1;
    }
}

/**
 * You are given an m x n grid representing a box of oranges. Each cell in the grid can have one of three values:

"E" representing an empty cell
"F" representing a fresh orange
"R" representing a rotten orange
Every minute, any fresh orange that is adjacent (4-directionally: up, down, left, right) to a rotten orange becomes rotten.

Write a function that takes this grid as input and returns the minimum number of minutes that must elapse until no cell has a fresh orange. 
If it is impossible to rot every fresh orange, return -1.

Example 1:

Input:

grid = [
["R", "F"],
["F", "F"],
]
Output: 2

Explanation:

After Minute 1: The rotting orange at grid[0][0] rots the fresh oranges at grid[0][1] and grid[1][0]. After Minute 2: 
The rotting orange at grid[1][0] (or grid[0][1]) rots the fresh orange at grid[1][1].

So after 2 minutes, all the fresh oranges are rotten.

Example 2:

Input:

grid = [
["R", "E"],
["E", "F"],
]
Output: -1

Explanation:

The two adjacent oranges to the rotten orange at grid[0][0] are empty, so after 1 minute, there are no fresh oranges to rot. 
So it is impossible to rot every fresh orange.

Example 3:

Input:

grid = [
["R", "F", "F", "F"],
["F", "F", "F", "R"],
["E", "E", "F", "F"],
]
Output: 2
 */

/**
 * We can model this problem as a graph where each cell is a node and the edges are the connections between adjacent cells.
The key to this problem is recognizing that we can simulate the rotting process using a breadth-first search (BFS) traversal of the graph 
(since any rotting orange will cause its neighbors to rot in the next minute).
Step 1: Initialize BFS Queue and Count Fresh Oranges
We can start by iterating over each cell in the grid and adding the position of all the rotten oranges to a queue. As we iterate, 
we can also count the number of fresh oranges in the grid - which will help us determine if there are any fresh oranges left after the BFS traversal.

Queue: [(0, 0), (1, 3)]
Fresh Oranges: 9
Minute: 0
Step 2: BFS Traversal
Next, we find all the oranges that will rot in the next minute. For each rotten orange in the BFS queue, we check if any of its neighbors are fresh oranges. 
If so, we turn the fresh orange into a rotten orange and add it to the queue to prepare for the next minute (shown in orange in the diagrams below). We also decrement the count of fresh oranges.
When we have finished processing all the rotten oranges in the queue, we increment the minute counter and repeat the process until there are no more fresh oranges left or the queue is empty.

Queue: [(0, 1), (1, 0), (0, 3), (1, 2), (2, 3)]
Fresh Oranges: 5
Minute: 1

Queue: [(0, 2), (1, 1), (2, 0), (2, 2)]
Fresh Oranges: 1
Minute: 2

Queue: [(2, 1)]
Fresh Oranges: 0
Minute: 3
 
 
The state of the orange box after each minute. The oranges that became rotten during this minute are colored in orange, while the "visited" oranges are dimmed.
If fresh_oranges is 0, then all oranges have become rotten, and we return the number of minutes it took to make all the oranges rotten. Otherwise, we return -1, 
as not all oranges can become rotten.
 */