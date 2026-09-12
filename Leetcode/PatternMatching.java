package Leetcode;
public class PatternMatching {
    public static  boolean isMatch(String s, String p) {
        int sLength = s.length();
        int pLength = p.length();

        boolean[][]  dp = new boolean[sLength + 1][pLength + 1];  
        // dp - dynamic Programming table
        // dp[i][j] will be true if first i characters of s match first j characters of p

        dp[0][0] = true;  // empty string matches empty pattern

        // Handle empty string with patterns like a*, a*b*
        // input string is empty but pattern is not empty - a*, a*b* for these kind of patterns we can have empty string as well
        for (int j = 2; j <= pLength; j++) {  // problem statement guarantees that for every '*' there will be a valid preceding character, so we can start from j = 2
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= sLength; i++) {
            for (int j = 1; j <= pLength; j++) {

                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);

                if (pc == '.' || sc == pc) {
                    dp[i][j] = dp[i - 1][j - 1];
                }

                else if (pc == '*') {
                    // zero occurrence
                    dp[i][j] = dp[i][j - 2];

                    // one or more occurrence
                    char prev = p.charAt(j - 2);

                    if (prev == '.' || prev == sc) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }

        return dp[sLength][pLength];
    }
    public static void main(String[] args) {
        System.out.println("" + isMatch("aaabccd", "a.*e"));
    }
}


/**
 * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:

'.' Matches any single character.​​​​
'*' Matches zero or more of the preceding element.
Return a boolean indicating whether the matching covers the entire input string (not partial).

 

Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input: s = "ab", p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
 

Constraints:

1 <= s.length <= 20
1 <= p.length <= 20
s contains only lowercase English letters.
p contains only lowercase English letters, '.', and '*'.
It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 */


/**
 * DP Definition

Let:
i → length of string considered
j → length of pattern considered
Then:
dp[i][j] = true if first i chars of s match first j chars of p


 */