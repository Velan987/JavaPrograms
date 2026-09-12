package neetcode.blind75;

import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        Set<Character> rowSet;
        Set<Character> colSet;
        Set<Character> gridSet;
        for(int i=0; i<9; i++){
            rowSet = new HashSet<>();
            colSet = new HashSet<>();
            gridSet = new HashSet<>();
            for(int j=0; j<9; j++){
                char rowChar = board[i][j];
                char colChar = board[j][i];
                if(rowChar !='.'){
                    if(rowSet.contains(rowChar)){
                        return false;
                    }
                    rowSet.add(rowChar);
                }
                if(colChar !='.'){
                    if(colSet.contains(colChar)){
                        return false;
                    }
                    colSet.add(colChar);
                }
                int gridRow = (i / 3) * 3 + (j / 3);
                int gridColumn = (i % 3) * 3 + (j % 3);

                char gridChar = board[gridRow][gridColumn];
                if(gridChar !='.'){
                    if(gridSet.contains(gridChar)){
                        return false;
                    }
                    gridSet.add(gridChar);
                }
            }
        }
        return true;
    }

    public boolean isValidSudoku2(char[][] board) {
        Set<String> seen = new HashSet<>();

        // Visit each cell exactly once
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                char digit = board[row][column];

                // Empty cells do not affect validity
                if (digit == '.') {
                    continue;
                }

                /*
                 * Identify the 3 × 3 box.
                 *
                 * row / 3 gives the box row:    0, 1, or 2
                 * column / 3 gives box column: 0, 1, or 2
                 * in this sudoku grid there will be 9 boxes. 0,0 box will have index from 0,0 to 2,2, box 0,1 will have index from 0,3 to 2,5 and so on.
                 * basically each box we are giving a key here, with that key we can identify a unique box(like row 0, row 1)
                 * just like row and column, in that box also need to check the uniqueness
                 */
                int boxRow = row / 3;
                int boxColumn = column / 3;

                String rowKey = digit + " in row " + row;
                String columnKey = digit + " in column " + column;
                String boxKey = digit + " in box " + boxRow + "-" + boxColumn;

                /*
                 * Set.add() returns:
                 *
                 * true  → key was added successfully
                 * false → key was already present, so we found a duplicate
                 */
                if (!seen.add(rowKey)
                        || !seen.add(columnKey)
                        || !seen.add(boxKey)) {
                    return false;
                }
            }
        }

        return true;
    }
    public static void main(String[] args) {
        System.out.println("Hi");
    }
}



/**
 * Valid Sudoku
You are given a 9 x 9 Sudoku board board. A Sudoku board is valid if the following rules are followed:

Each row must contain the digits 1-9 without duplicates.
Each column must contain the digits 1-9 without duplicates.
Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without duplicates.
Return true if the Sudoku board is valid, otherwise return false

Note: A board does not need to be full or be solvable to be valid.
 */