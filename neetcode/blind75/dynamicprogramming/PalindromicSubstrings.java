package neetcode.blind75.dynamicprogramming;

public class PalindromicSubstrings {
    public int countSubstrings(String s) {
        // Every single characters are considered as palindrome, so initialize the count with string length
        int count = s.length(), length = s.length();
        if(count <=1){
            return count;
        }
        for(int i=0; i<length; i++){
            // Palindrome strings can be odd number of characters or even number of characters, we should handle both
            // for odd length palindrome, i value will be center character, so we should expand left and right to check palindrome
            int oddLeft = i-1, oddRight = i +1;
            while(oddLeft >=0 && oddRight < length && s.charAt(oddLeft) == s.charAt(oddRight)){
                count ++;
                oddLeft --;
                oddRight ++;
            }

            // for even length palindrome, i value can be considered as left or right
            int evenLeft = i, evenRight = i+1;
            while(evenLeft >=0 && evenRight <length && s.charAt(evenLeft) == s.charAt(evenRight)){
                count ++;
                evenLeft --;
                evenRight ++;
            }
        }
        return count;
    }
}

/**
 * Given a string s, return the number of substrings within s that are palindromes.

A palindrome is a string that reads the same forward and backward.

Example 1:

Input: s = "abc"

Output: 3
Explanation: "a", "b", "c".

Example 2:

Input: s = "aaa"

Output: 6
Explanation: "a", "a", "a", "aa", "aa", "aaa". Note that different substrings are counted as different palindromes even if the string contents are the same.

Constraints:

1 <= s.length <= 1000
s consists of lowercase English letters.
 */