package hellointerview.dynamicprogramming;

/**
 * Since the robot can only move right or down, it must have arrived at the destination from one of exactly two cells:
From above (by moving down)
From the left (by moving right)

This means: the number of ways to reach any cell equals the sum of the ways to reach the cell above it plus the ways to reach the cell to its left. 
This is the core insight that leads us to a dynamic programming solution.

Finding the Recurrence Relation
Now let's formalize this intuition. We'll define dp[i][j] as the number of unique paths to reach cell (i, j) from the starting cell (0, 0).
Based on our insight above, to reach cell (i, j), the robot must come from either:
Cell (i-1, j) — directly above — and then move down
Cell (i, j-1) — directly to the left — and then move right
This gives us our recurrence relation:
dp[i][j] = dp[i - 1][j] + dp[i][j - 1]

Base cases: If the robot is in the first row (i == 0), it can only have arrived by moving right repeatedly — there's exactly 1 path. Similarly, 
if the robot is in the first column (j == 0), there's exactly 1 path (moving down repeatedly).

 * UniquePaths
 */
public class UniquePaths {
    public static long uniquePaths(int m, int n) {
        // Initialize a 2D array with dimensions m x n
        long[][] dp = new long[m][n];
        
        // base case: there is only one way to reach any cell in the first row (moving only right)
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        
        // Set base case: there is only one way to reach any cell in the first column (moving only down)
        for (int j = 0; j < m; j++) {
            dp[j][0] = 1;
        }
        
        // Fill the rest of the dp array
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
       System.out.println(uniquePaths(3, 4));
    }
}


/**
 * You are given a robot that starts at the top-left corner of a grid with dimensions m x n. The robot can only move either down or right at any point in time. 
 * The goal is for the robot to reach the bottom-right corner of the grid.

Given the dimensions of the board m and n, write a function to return the number of unique paths the robot can take to reach the bottom-right corner.

Example 1

Input:

m = 2
n = 3
Output: 3

Explanation: The robot starts at the top-left corner and can move right or down. There are 3 unique ways to reach the bottom-right corner of a 2 x 3 grid.


Example 2
Input:

m = 3
n = 7
Output: 28
 */