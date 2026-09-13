package neetcode.blind75.MathAndGeometry;

public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        // this will track what are all the rows and columns have 0, in one iteration we will populate this info, 
        // next iteration will make applicable rows and cols as zero
        boolean []rowZero = new boolean[matrix.length];
        boolean []colZero = new boolean[matrix[0].length];

        // check every cell, if cell contains zero then mark corresponding row and col 
        // this can tell that particular row/column a cell contain zero so entire row/column should be marked as zero
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                if(matrix[i][j] == 0){
                    rowZero[i] = true;
                    colZero[j] = true;
                }
            }
        }

        // iterate through rowZero and mark applicable rows as zero
        for(int i=0; i<rowZero.length; i++){
            if(rowZero[i]){
                for(int j=0; j< matrix[0].length; j++){
                    matrix[i][j] = 0;
                }
            }
        }

        // iterate through colZero and mark applicable cols as zero
        for(int i=0; i<colZero.length; i++){
            if(colZero[i]){
                for(int j=0; j< matrix.length; j++){
                    matrix[j][i] = 0;
                }
            }
        }

    }
}

/**
 * Given an m x n matrix of integers matrix, if an element is 0, set its entire row and column to 0's.

You must update the matrix in-place.

Follow up: Could you solve it using O(1) space?

Example 1:
Input: matrix = [
  [0,1],
  [1,0]
]

Output: [
  [0,0],
  [0,0]
]

Example 2:
Input: matrix = [
  [1,2,3],
  [4,0,5],
  [6,7,8]
]

Output: [
  [1,0,3],
  [0,0,0],
  [6,0,8]
]

 */