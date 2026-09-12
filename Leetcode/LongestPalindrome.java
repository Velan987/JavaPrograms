package Leetcode;
public class LongestPalindrome {
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return ""; //babad cbbd
        int length = s.length();
        int max = 0;
        int start = 0, end = 0;
        for (int i=0; i<length; i++){ // i should start from 0 only not from 1
            // if palindrome string has odd number of characters, then left and right pointers will be before and after the center character respectively.
            int oddLeft = i-1;
            int oddRight = i+1;
            while (oddLeft >= 0 && oddRight < length && s.charAt(oddLeft) == s.charAt(oddRight)){
                oddLeft--;
                oddRight++;
            }
            // after the while loop, oddLeft and oddRight will be one step before and after the palindrome string respectively
            // so we need to move them back to the palindrome string.
            // for example, in babad example, when i=1, oddLeft will be -1 and oddRight will be 3 after the while loop
            // so we need to move them back to 0 and 2 respectively.
            oddLeft++; 
            oddRight--;
            /**
             * in babad example, when i=1, oddLeft will be 0 and oddRight will be 2
             * so the palindrome string will be "bab" and max will be 3, start will be 0 and end will be 2.
             * length of the palindrome string will be oddRight - oddLeft + 1, which is 2 - 0 + 1 = 3.
             */
            if (oddRight - oddLeft + 1 > max){
                max = oddRight - oddLeft + 1;
                start = oddLeft;
                end = oddRight;
            }
            // if palindrome string has even number of characters, then left will be center character and right will be after the center character.(it can be right will be center character and left will one character before center character))
            int evenLeft = i;
            int evenRight = i+1;
            while (evenLeft >= 0 && evenRight < length && s.charAt(evenLeft) == s.charAt(evenRight)){
                evenLeft--;
                evenRight++;
            }
            evenLeft++;
            evenRight--;
            if (evenRight - evenLeft + 1 > max){
                max = evenRight - evenLeft + 1;
                start = evenLeft;
                end = evenRight;
            }
        }

        return s.substring(start, end + 1);
    }
    public static void main(String[] args) {
        String s = "aab";
        String result = longestPalindrome(s);
        System.out.println(result);
    }
}


/**
 * Given a string s, return the longest palindromic substring in s.

 

Example 1:

Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"
 

Constraints:

1 <= s.length <= 1000
s consist of only digits and English letters.
 */