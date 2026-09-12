package hellointerview.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFSMinimumKnightMoves {
    public static Integer minimumKnightMoves(Integer x, Integer y) {
        
        // Set to track already visited nodes - this will contain index value like "1,2"
        Set<String> visited = new HashSet<>();

        // Queue for BFS
        // int[] - this will have 3 elements, 2 cell indexes and in third position number of moves to reach that position
        Queue<int[]> queue = new LinkedList<>();

        // Add starting point to queue (0, 0, 0) - starting point cell is (0,0) and it will take 0 moves to reach that position
        queue.offer(new int[]{0, 0, 0});

        // Mark starting position as visited
        visited.add("0,0");

        // Valid knight moves - this is our adjacency nodes
        int [][] knightMoves = new int[][]{
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
        };

        // start BFS
        while(!queue.isEmpty()){
            // get first element from queue
            int[] current = queue.remove();
            int cx = current[0], cy = current[1], moves = current[2];
            
            // we reached the target cell
            if(cx == x && cy == y){
                return moves;
            }

            // we need to add all valid moves from current position to the queue if it is not already visited
            for(int[] possibleMove: knightMoves){
                int nx = cx + possibleMove[0], ny = cy + possibleMove[1];
                
                // beyond chess board, positive side we are not checking because they have given a chess board of infinite size
                // in an infinite chess board negative values also come, so we should not add below if statement at all
                // one input position is (-5, -5), output for this is 4
                // if(nx <0 || ny <0){
                //     continue;
                // }
                String key = nx + "," + ny;
                if(!visited.contains(key)){
                    visited.add(key);
                    // From current position to go to new position it will take one move only thats why moves+1
                    queue.offer(new int[]{nx, ny, moves+1});
                }


            }
        }

        return -1;
    }

    public static void main(String[] args) {
       System.out.println( minimumKnightMoves(1, 2));
       System.out.println( minimumKnightMoves(-5, -5));
    }
}


/**
 * You are given a chessboard of infinite size where the coordinates of each cell are defined by integer pairs (x, y). The knight piece moves in an L-shape, 
 * either two squares horizontally and one square vertically, or two squares vertically and one square horizontally.

Write a function to determine the minimum number of moves required for the knight to move from the starting position (0, 0) to the target position (x, y). 
Assume that it is always possible to reach the target position, and that x and y are both integers in the range [-200, 200]

Example 1:

Input:

x = 1
y = 2
Output: 1

Explanation: The knight can move from (0, 0) to (1, 2) in one move.

Example 2:

x = 4
y = 4
Output: 4

Explanation: The knight can move from (0, 0) to (4, 4) in four moves ( [0, 0] -> [2, 1] -> [4, 2] -> [6, 3] -> [4, 4] )
 */

/**
 * Explanation
 * We can model this problem as a graph where each cell on the chessboard is a node, and the neighbors of a cell are the cells that can be reached by a knight's move from that cell. 
 * Since this is a shortest path problem, we can use a breadth-first search (BFS) traversal to find the minimum number of moves required to reach the target cell (x, y) 
 * starting from the cell (0, 0).
Step 1: Initialize the Queue and Visited Set
We start by initializing our BFS queue with the starting cell (0, 0) along with the number of moves required to reach that cell, which is 0 to start. 
We also initialize a set to keep track of the cells we have visited, so that we don't revisit them (to avoid infinite loops).
Step 2: Perform BFS Traversal
We then perform a BFS traversal by repeatedly dequeuing from the front of the queue. Each time we dequeue, we get both the current knight position, 
and the number of moves required to reach that position. We then check if the current knight position is the target cell (x, y). 
If it is, we return the number of moves required to reach that cell.
Otherwise, for each valid knight move from the current position that has not been visited before, we add that position to the queue, 
along with the number of moves required to reach that position (which is 1 + the current # of moves). We also mark the current cell as visited.
 */