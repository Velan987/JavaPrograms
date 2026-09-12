package hellointerview.variablelengthslidingwindow;

import java.util.HashSet;
import java.util.Set;

/**
 * In fixed length we will change the window’s start and end index if window reaches the size k, 
 * but in variable length whenever a condition matches we will change the window start index.
 * LongestSubstringWithoutRepeatingChar
 */
public class LongestSubstringWithoutRepeatingChar {
    public int longestSubstringWithoutRepeat(String s) {
        int result = 0;
        int start = 0;
        Set<Character> window = new HashSet<>();
        for(int end=0; end<s.length();end++){
            while(window.contains(s.charAt(end))){
                window.remove(s.charAt(start));
                start ++;
            }

            window.add(s.charAt(end));

            result = Math.max(result, end-start + 1);
        }

        return result;
    }
    
}


/**
 * Write a function to return the length of the longest substring in a provided string s where all characters in 
 * the substring are distinct.

Example 1: Input:

s = "eghghhgg"
Output:

3
The longest substring without repeating characters is "egh" with length of 3.

Example 2: Input:

s = "substring"
Output:

8
The answer is "ubstring" with length of 8.


 */