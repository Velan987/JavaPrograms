package Leetcode;
public class DivideTwoInteger {
    public static int divide(int dividend, int divisor) {
        // Edge case (overflow)
        // dividend = -2147483648 and divisor=-1 then result is 2147483647(postive max)
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Convert to long to avoid overflow
        // mod(-2147483648) will be error, no equivalent in positive side, so converting to long
        long dvd = Math.abs((long) dividend);
        long dvs = Math.abs((long) divisor);

        int result = 0;

        while (dvd >= dvs) {

            long temp = dvs;
            int multiple = 1;

            // find largest multiple
            while (dvd >= (temp << 1)) {    // left shift is equivalent to multiplying by 2
                temp <<= 1;
                multiple <<= 1;
            }

            dvd -= temp;
            result += multiple;
        }

        // Apply sign
        if ((dividend < 0) ^ (divisor < 0)) {
            result = -result;
        }

        return result;
    }
    public static void main(String[] args) {
        int dividend = 10;
        int divisor = 3;
        System.out.println(divide(dividend, divisor)); // Output: 3

        dividend = 7;
        divisor = -3;
        System.out.println(divide(dividend, divisor)); // Output: -2

        dividend = -2147483648;
        divisor = -1;
        System.out.println(divide(dividend, divisor)); // Output: 2147483647 (overflow case)
    }
}

/**
 * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.

The integer division should truncate toward zero, which means losing its fractional part. For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.

Return the quotient after dividing dividend by divisor.

Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231, 231 − 1]. For this problem, if the quotient is strictly greater than 231 - 1, then return 231 - 1, and if the quotient is strictly less than -231, then return -231.

 

Example 1:

Input: dividend = 10, divisor = 3
Output: 3
Explanation: 10/3 = 3.33333.. which is truncated to 3.
Example 2:

Input: dividend = 7, divisor = -3
Output: -2
Explanation: 7/-3 = -2.33333.. which is truncated to -2.
 

Constraints:

-231 <= dividend, divisor <= 231 - 1
divisor != 0
 
 */