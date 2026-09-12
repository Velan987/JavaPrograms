package hellointerview.graph;

import java.util.HashSet;
import java.util.Set;

public class FloodFillMatrics {

    public static int[][] flood_fill(int[][] image, Integer sr, Integer sc, Integer color) {
        int originalColor = image[sr][sc];

        if (originalColor == color) {
            return image;
        }
        // up, down, left, right
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        // will start with sr and sc as first element
        dfs(image, sr, sc, originalColor, color, directions);
        return image;
    }

    private static void dfs(int[][] image, int r, int c, int originalColor, int color, int[][] directions) {
        // check if the cell is out of bounds
        if (r < 0 || r >= image.length || c < 0 || c >= image[0].length) {
            return;
        }

        // if row column value has original color then only we are going to change the color, else no change needed - this is our base case
        if (image[r][c] != originalColor)
            return;

        
        // filling index with new color
        image[r][c] = color;
        // recursive invocation for adjacent nodes
        for(int[] direction : directions){
            dfs(image, r+direction[0], c+direction[1], originalColor, color, directions);
        }
    
    }

    public static void main(String[] args) {
        int[][] matrics = {
                { 1, 0, 1 },
                { 1, 0, 0 },
                { 0, 0, 1 }
        };
        int sr = 1, sc = 1, color = 2;
        flood_fill(matrics, sr, sc, color);
    }
}

/**
 * Given a m x n integer grid image and integers sr, sc, and newColor, write a function to perform a flood fill on the image starting from the pixel image[sr][sc].

In a 'flood fill', start by changing the color of image[sr][sc] to newColor. 
Then, change the color of all pixels connected to image[sr][sc] from either the top, bottom, left or right that have the same color as image[sr][sc], 
along with all the connected pixels of those pixels, and so on.

Input:

image = [[1,0,1],[1,0,0],[0,0,1]], sr = 1, sc = 1, color = 2
Output:

[[1,2,1],[1,2,2],[2,2,1]]
The zeroes connected to the starting pixel (1, 1) are colored with the new color (2).
 */