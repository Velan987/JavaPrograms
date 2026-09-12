package neetcode.blind75.MathAndGeometry;

public class RotateImage {
    public void rotate(int[][] matrix) {
        /**
         * https://www.youtube.com/watch?v=fMSJSS7eO1w
         * we will have 4 pointers, left, right, top, bottom (basically 4 corners we can get using this)
         * first we will rotate 4 corners, then move to next like below
         * next pointer for top,left will be top,left+1
         * next pointer for top,right will be top+1,right (basically next rows last column value)
         * next pointer for bottom,left will be bottom-1,left (previous rows first column)
         * next pointer for bottm,right will be bottom,right-1
         * 
         * once all outer values rotated we can move to one layer inner, and repeat same process until left < right
         * refer youtube video if needed (watching second half alone also would make sense )
         */

        int left =0, right=matrix.length-1;
        while(left < right){
            // i will iterate until right-left times only, because for 4 X 4 matrix in 3 rotations 4 cells will be rotated
            for(int i=0; i< right-left; i++){
                int top = left, bottom = right;

                // save the topleft
                // in next iteration top,left will be top,left+1
                int topLeft = matrix[top][left+i];

                // move bottom left into top left
                // in next iteration top, left will be top,left+1 and bottom, left will be bottom+1,left
                // for this purpose we can use i
                matrix[top][left +i] = matrix[bottom-i][left];

                // move bottom right into bottom left
                // in next iteration bottm,right will be bottom,right-1 and bottom,left will be bottom-1,left 
                matrix[bottom-i][left] = matrix[bottom][right-i];

                // move top right into bottom right
                // in next iteration top,right will be top+1,right and bottm,right will be bottom,right-1
                matrix[bottom][right-i] = matrix[top+i][right];

                // move top left into top right
                // in next iteration top,right will be top+1,right
                matrix[top+i][right] = topLeft;

                // while moving elements if we use temp variables then for 4 movements we need 4 temp values
                // to overcome that taking top,left into one temp variable and then shifting in bottom,left to top,left
                // bottom,right to bottom,left, top,right to bottom,right and then temp value(which is top,left) to top,right order
                // so that values will not override and with one temp value we can switch 
            }
            left++;
            right--;
        }
    }

    // Simpler solution will be reverse the matrix and transpose it, that will give the rotation of matrics
    public void rotateV2(int[][] matrix) {
        int n = matrix.length;

        // Reverse the matrics - first row will be last row, last row will be first row like that
        for(int i=0; i<n/2; i++){
            int []tmp = matrix[i];
            matrix[i] = matrix[n-1-i];
            matrix[n-1-i] = tmp;
        }

        // Transpose the matrics
        for(int i=0; i<n; i++){
            // j should start from i only NOT from zero
            for(int j=i;j<n;j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        
    }
}

/**
 * Given a square n x n matrix of integers matrix, rotate it by 90 degrees clockwise.

You must rotate the matrix in-place. Do not allocate another 2D matrix and do the rotation.

Example 1:
Input: matrix = [
  [1,2],
  [3,4]
]

Output: [
  [3,1],
  [4,2]
]

Example 2:
Input: matrix = [
  [1,2,3],
  [4,5,6],
  [7,8,9]
]

Output: [
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
 */