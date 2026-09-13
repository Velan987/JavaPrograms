package neetcode.blind75.MathAndGeometry;

import java.util.ArrayList;
import java.util.List;

//https://www.youtube.com/watch?v=BJnMZNwUk1M
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();

        int left=0, right=matrix[0].length;     // cols
        int top=0, bottom=matrix.length;        // rows
        // we will iterate like left->right, right->bottom, right_bottom->left and then left bottom to left up
        while(left<right && top<bottom){
            // process left -> right
            for(int i=left; i<right;i++){
                res.add(matrix[top][i]);
            }
            top++;
            // process top_right to bottom
            for(int i=top; i<bottom; i++){
                res.add(matrix[i][right-1]);
            }
            right--;
            //top and right we already modified
            if(! (left<right && top<bottom) ){
                break;
            }

            // process bottom_right to left
            for(int i=right-1; i>=left; i--){
                res.add(matrix[bottom-1][i]);
            }
            bottom--;

            // process bottom_left to top
            for(int i=bottom-1; i>=top; i--){
                res.add(matrix[i][left]);
            }
            left ++;
        }

        return res;
    }
}

/**
 * Given an m x n matrix of integers matrix, return a list of all elements within the matrix in spiral order.

Example 1:
Input: matrix = [[1,2],[3,4]]

Output: [1,2,4,3]


Example 2:
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]

Output: [1,2,3,6,9,8,7,4,5]


Example 3:

Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]

Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */