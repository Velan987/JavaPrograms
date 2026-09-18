package neetcode.blind75.dynamicprogramming;

public class DecodeWays {
    /**
     * 212
     * At any idex i, we have only 2 options
     * 1. Take one digit s[i] - valid if its not 0
     * 2. take 2 digits s[i:i+2] - valid if it is <= 26
     * this is our sub problem, and our base cases are
     * if we reach end of string -> 1 valid decoding, 
     * if substring starts with 0 -> invalid
     * 
     */
    public int numDecodings(String s) {
        return dfs(s, 0);
    }

    private int dfs(String s, int i){
        if(i == s.length())
            return 1;
        if(s.charAt(i) == '0')
            return 0;
        int res = dfs(s, i+1);
        // generally if we have 2 sub problem we will invoke at single line and sum it or take max based on requirement
        // here second subproblem is valid only if it forms value <= 26
        if(i < s.length()-1 && Integer.parseInt(s.substring(i, i+2)) <=26){
            res += dfs(s, i+2);
        }
        return res;
    }
    int[] dp ;
    public int numDecodingsV2(String s) {
        dp = new int[s.length()+1];
        for(int i=0; i<s.length(); i++){
            dp[i] = -1;
        }
        // one of our base case
        dp[s.length()] = 1;
        return dfsV2(s, 0);
    }

    private int dfsV2(String s, int i){
        if(dp[i] != -1)
            return dp[i];
        if(s.charAt(i) == '0')
            return 0;
        int res = dfsV2(s, i+1);
        // generally if we have 2 sub problem we will invoke at single line and sum it or take max based on requirement
        // here second subproblem is valid only if it forms value <= 26
        if(i < s.length()-1 && Integer.parseInt(s.substring(i, i+2)) <=26){
            res += dfsV2(s, i+2);
        }
        dp[i] = res;
        return res;
    }

    public int numDecodingsV3(String s) {
        int[] dp = new int[s.length() + 1];
        dp[s.length()] = 1;
        // calculating from end
        // we initialize dp length as string length +1 and assigned last value as 1 - meaning for last index there is one valid decode way
        // eg:3125. l =4, we are iterating from 3 to 0 and aleady initialized dp[4] =1
        // i=3: there is one valid way
        // i=2: dp[2] = dp[3], also i, i+1 forms 25 which is <=26, so dp[2] = dp[2]+dp[3]
        // i=1: dp[1] = dp[2], also i, i+1 forms 12 <=26, so dp[1] = dp[1]+dp[2]
        // i=0: dp[0] = dp[1], and  i, i+1 forms 31 > 26 - in this case we cannot consider 2 digits

        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                dp[i] = 0;
            } else {
                dp[i] = dp[i + 1];
                if (i + 1 < s.length() && Integer.parseInt(s.substring(i, i+2)) <=26) {
                    dp[i] += dp[i + 2];
                }
            }
        }
        return dp[0];
    }
}

/**
 * A string consisting of uppercase english characters can be encoded to a number using the following mapping:

'A' -> "1"
'B' -> "2"
...
'Z' -> "26"
To decode a message, digits must be grouped and then mapped back into letters using the reverse of the mapping above. 
There may be multiple ways to decode a message. For example, "1012" can be mapped into:

"JAB" with the grouping (10 1 2)
"JL" with the grouping (10 12)
The grouping (1 01 2) is invalid because 01 cannot be mapped into a letter since it contains a leading zero.

Given a string s containing only digits, return the number of ways to decode it. You can assume that the answer fits in a 32-bit integer.

Example 1:

Input: s = "12"

Output: 2

Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: s = "01"

Output: 0
Explanation: "01" cannot be decoded because "01" cannot be mapped into a letter.

Constraints:

1 <= s.length <= 100
s consists of digits
 */