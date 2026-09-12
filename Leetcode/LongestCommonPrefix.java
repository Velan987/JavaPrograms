package Leetcode;
public class LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        String s = strs[0];
        String result = "";
        String tmp = "";
        for(int i=0;i<s.length();i++){
            tmp += s.charAt(i);
            for (int j=1; j<strs.length; j++) {
                if (!strs[j].startsWith(tmp)) {
                    return result;
                }
            }
            result = tmp;
        }
        return result;
    }
    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(strs));
    }
}

/**
 * Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

 

Example 1:

Input: strs = ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
 

Constraints:

1 <= strs.length <= 200
0 <= strs[i].length <= 200
strs[i] consists of only lowercase English letters if it is non-empty.
 */