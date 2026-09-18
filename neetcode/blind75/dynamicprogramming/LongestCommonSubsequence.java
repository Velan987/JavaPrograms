package neetcode.blind75.dynamicprogramming;

public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        /**
         * a subsequence is a sequence that can be derived from the given sequence by deleting some or no elements without changing the relative order of the remaining characters.
         * to find common subsequence, we compare characters one by one
         * if they match - move both pointers forward
         * if they dont match - we skip one char from either string and take the best result
         *      basically max(text1[i+1][j] , text2[i][j+1])
         * 
         * https://neetcode.io/problems/longest-common-subsequence/solution
         * https://www.youtube.com/watch?v=Ua0GhsJSlWM
         */

        return dfs(text1, text2, 0, 0);
    }
    // This is a recursion approach -  this will work but sometime it will through time limit exceeded. we need to optimize it.
    public int dfs(String text1, String text2, int i, int j){
        if(i == text1.length() || j == text2.length())
            return 0;

        // two characters are same, so move both string indexes to compare next character and adding one (because one character matched)
        if(text1.charAt(i) == text2.charAt(j)){
            return 1 + dfs(text1, text2, i+1, j+1);
        }
        // if characters not matched then same character has to compare with next char in another string
        // for example i=0, j=0 chars were not matched, so text1[0] should be compared with text2[1] and so on
        // same way text2[0] should be compared with text1[1] and so on. then only we will compare it in every possible characters
        return Math.max(
            dfs(text1, text2, i+1, j), 
            dfs(text1, text2, i, j+1)
        );
    }

    // Using dynamic programming - understand recursion first then come to this approach
    // in above approach we might calculate dfs[2][3] multiple times, to avoid that we can store it in a table
    private int[][] memo;
    public int longestCommonSubsequenceV2(String text1, String text2) {
        memo = new int[text1.length()][text2.length()];

        // initialize memo value with -1
        for(int i=0; i< text1.length(); i++){
            for(int j=0; j< text2.length(); j++){
                memo[i][j] = -1;
            }
        }
        return dfsV2(text1, text2, 0, 0);
    }

    private int dfsV2(String text1, String text2, int i, int j){
        if(i == text1.length() || j == text2.length())
            return 0;
        // caching from memory, this will help overcome repeatedly calculating values for same input
        if(memo[i][j] != -1 )
            return memo[i][j];

        if(text1.charAt(i) == text2.charAt(j)){
            memo[i][j] = 1 + dfsV2(text1, text2, i+1, j+1);
        }else{
            memo[i][j] = Math.max(
                dfsV2(text1, text2, i+1, j), 
                dfsV2(text1, text2, i, j+1)
            );
        }
        return memo[i][j];
    }
}

/**
 * Given two strings text1 and text2, return the length of the longest common subsequence between the two strings if one exists, otherwise return 0.

A subsequence is a sequence that can be derived from the given sequence by deleting some or no elements without changing the relative order of the remaining characters.

For example, "cat" is a subsequence of "crabt".
A common subsequence of two strings is a subsequence that exists in both strings.

Example 1:

Input: text1 = "cat", text2 = "crabt" 

Output: 3 
Explanation: The longest common subsequence is "cat" which has a length of 3.

Example 2:

Input: text1 = "abcd", text2 = "abcd"

Output: 4
Example 3:

Input: text1 = "abcd", text2 = "efgh"

Output: 0
Constraints:

1 <= text1.length, text2.length <= 1000
text1 and text2 consist of only lowercase English characters.
 */