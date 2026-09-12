package hellointerview.backtracking;

public class WordSearch {
    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col] == word.charAt(0)) {
                    if (dfs(board, word, row, col, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private boolean dfs(char[][] board, String word, int r, int c, int index) {
        if (index == word.length()) {
            return true;
        }
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length || 
            board[r][c] != word.charAt(index)) {
            return false;
        }
        char temp = board[r][c];
        board[r][c] = '#';
        boolean found = (
            dfs(board, word, r + 1, c, index + 1) ||
            dfs(board, word, r - 1, c, index + 1) ||
            dfs(board, word, r, c + 1, index + 1) ||
            dfs(board, word, r, c - 1, index + 1)
        );
        board[r][c] = temp;
        return found;
    }
}


/**
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. 
The same letter cell may not be used more than once.

Example 1:

Input:

board = [
    ['B', 'L', 'C', 'H'],
    ['D', 'E', 'L', 'T'],
    ['D', 'A', 'K', 'A'],
]
word = "BLEAK"

"BLEAK" can be formed using the tiles in dark blue.
Output:

True
Example 2:

Input:

board = [
    ['B', 'L', 'C', 'H'],
    ['D', 'E', 'L', 'T'],
    ['D', 'A', 'K', 'A'],
]
word = "BLEED"
Output:

False
 */


/**
 * Explanation
We can solve this problem using a backtracking approach. The idea is to try to find the target word one character at a time using depth-first search.
We'll iterate over each cell in the grid. If any of the letters in these cells are the same as the first letter of our target word, 
then we can initialize our depth-first search from that cell.
Depth-First Search Function
To perform our depth-first search, we'll create a helper function dfs. This function will take in r and c, which represent the coordinates of the current cell we are looking at, 
and index, which represents the index within word that we are searching for.
This function checks if the current cell is equal to the letter we are currently searching for. If so, it makes recursive calls to explore each of its 4 neighbors 
(in the N, W, S, E) directions to look for the next letter in word.
If it isn't, (or the cell is not in the bounds of the grid), then it returns False immediately.
The base case is when index == len(word), which happens when we have found the target word in our grid, so we can return True.
Keeping Track of Visited Cells
Because our grid is a graph, we have to keep track of visited cells in our current word path to avoid an infinite loop.
Instead of keeping a set of visited cells, we can keep track our visited cells by updating the value of the cell to be some placeholder value such as "#". 
This means that if we ever encounter a cell with "#", then we can return immediately, as we have already visited this cell on our current path.
We just have to make sure that we backtrack appropriately by restoring the value of the cell to its previous (actual letter) value after the recursive calls have finished.
This is important as it "frees" up the cell to be used by another path in the future, if necessary.
 */