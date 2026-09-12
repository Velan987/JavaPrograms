package Leetcode;
import java.util.ArrayList;
import java.util.List;


public class ZigZagString {
    public static String convert(String s, int numRows) {
        if(numRows == 1  || s.length() <= numRows){ // handle this, otherwise below logic will fail
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>();
        boolean goingDown = false;
        int currentRow = 0;
        for(int i=0;i<numRows;i++){
            rows.add(new StringBuilder());
        }
        for(char c: s.toCharArray()){
            rows.get(currentRow).append(c);
            if(currentRow == 0){
                goingDown = true;
            }
            if(currentRow == numRows-1){
                goingDown = false;
            }
            if(goingDown){
                currentRow++;
            }else{
                currentRow--;
            }
        }
        StringBuilder result = new StringBuilder();
        for(StringBuilder row: rows){
            result.append(row);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 3;
        String result = convert(s, numRows);
        System.out.println(result);
    }
    
}

// Creating a list of StringBuilder for each row, and appending characters to the corresponding row based on the current row and direction. Finally, concatenating all rows to get the final result.
// Straightforward solution.

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);
 

Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I
Example 3:

Input: s = "A", numRows = 1
Output: "A"
 */