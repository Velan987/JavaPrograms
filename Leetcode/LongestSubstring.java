package Leetcode;
import java.util.HashSet;
import java.util.Set;

public class LongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        /**
         * Core Idea

            We maintain a window (substring) with:

            No duplicate characters
            Expand right pointer
            Shrink left pointer when duplicate appears
            🧠 Intuition

            Think of it like:

            Keep adding characters until duplicate appears
            → then shrink from left until duplicate is removed

            Steps
            Use two pointers:
            left (start of window)
            right (end of window)
            Use a Set to track characters in current window
            If duplicate:
            Remove from left until it's gone
         */
        Set<Character> set = new HashSet<>();
        
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);

            // If duplicate, shrink window
            while (set.contains(ch)) {
                set.remove(s.charAt(left));
                left++;
            }

            set.add(ch);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
        /**
         * Pattern Recognition

            This is:

            Variable-size sliding window

            Used when:

            Substring problems
            Need longest/shortest window
            Constraint: "no duplicates", "at most k", etc.
         */
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        int result = lengthOfLongestSubstring(s);
        System.out.println(result);
    }
}

/**
 * Given a string s, find the length of the longest substring without duplicate characters.

 

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
