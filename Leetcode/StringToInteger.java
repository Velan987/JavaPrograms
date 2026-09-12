package Leetcode;
public class StringToInteger {
    public static int myAtoi(String s) {
       //step 1 remove leading whitespace
        s = s.trim();
        if(s.startsWith("-+") || s.startsWith("+-")) return 0;
        boolean isNegative = false;
        //step 2 - positive negative symbol handling
        if(s.startsWith("-")){
            isNegative = true;
            s = s.substring(1,s.length());
        }
        if(s.startsWith("+")){
            s = s.substring(1,s.length());
        }
        int result = 0;
        int multiplier = 10; // each digit is 10 times of next digit
        boolean leadingZero = true;
        for(char c: s.toCharArray()){
            int ascii = (int)c;
            if(ascii == 48 && leadingZero){
                continue;
            }
            if(ascii >=48 && ascii <=57){
                leadingZero=false;
                int val = c - '0'; // converting char to int
                // example if c is 7 then ascii is 55 and '0' is 48 so 55-48 = 7
                if (result > Integer.MAX_VALUE/10 || (result == Integer.MAX_VALUE / 10 && val > 7)) {
                    return isNegative? Integer.MIN_VALUE: Integer.MAX_VALUE;
                }
                if (result < Integer.MIN_VALUE/10 || (result == Integer.MIN_VALUE / 10 && val < -8)) return isNegative? Integer.MIN_VALUE: Integer.MAX_VALUE;
                result = result * multiplier + val;
            }else{
                break;
            }
        }
        return isNegative? -result: result;
    }
    public static void main(String[] args) {
        String s = "   -2147483649";
        int result = myAtoi(s);
        System.out.println(result);
    }
}

/**
 * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.

The algorithm for myAtoi(string s) is as follows:

Whitespace: Ignore any leading whitespace (" ").
Signedness: Determine the sign by checking if the next character is '-' or '+', assuming positivity if neither present.
Conversion: Read the integer by skipping leading zeros until a non-digit character is encountered or the end of the string is reached. If no digits were read, then the result is 0.
Rounding: If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then round the integer to remain in the range. Specifically, integers less than -231 should be rounded to -231, and integers greater than 231 - 1 should be rounded to 231 - 1.
Return the integer as the final result.

 

Example 1:

Input: s = "42"

Output: 42

Explanation:

The underlined characters are what is read in and the caret is the current reader position.
Step 1: "42" (no characters read because there is no leading whitespace)
         ^
Step 2: "42" (no characters read because there is neither a '-' nor '+')
         ^
Step 3: "42" ("42" is read in)
           ^
Example 2:

Input: s = " -042"

Output: -42

Explanation:

Step 1: "   -042" (leading whitespace is read and ignored)
            ^
Step 2: "   -042" ('-' is read, so the result should be negative)
             ^
Step 3: "   -042" ("042" is read in, leading zeros ignored in the result)
               ^
Example 3:

Input: s = "1337c0d3"

Output: 1337

Explanation:

Step 1: "1337c0d3" (no characters read because there is no leading whitespace)
         ^
Step 2: "1337c0d3" (no characters read because there is neither a '-' nor '+')
         ^
Step 3: "1337c0d3" ("1337" is read in; reading stops because the next character is a non-digit)
             ^
Example 4:

Input: s = "0-1"

Output: 0

Explanation:

Step 1: "0-1" (no characters read because there is no leading whitespace)
         ^
Step 2: "0-1" (no characters read because there is neither a '-' nor '+')
         ^
Step 3: "0-1" ("0" is read in; reading stops because the next character is a non-digit)
          ^
Example 5:

Input: s = "words and 987"

Output: 0

Explanation:

Reading stops at the first non-digit character 'w'.

 

Constraints:

0 <= s.length <= 200
s consists of English letters (lower-case and upper-case), digits (0-9), ' ', '+', '-', and '.'.
 */
