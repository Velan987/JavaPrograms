package neetcode.neetcode150.bitManipulation;

/**
 * 
 * NumberOf1Bits
 * we can check the right most bit is one or 0 in 2 ways
 * 1. we can use & operator with one. for example 5
 * 5 & 1
 * 0101
 * 0001 (&)
 * 0001     -   if the result is 1 then right most bit is one else right most bit is 0
 * after finding right most bit we can right shift once to move remaining bits right side and continue checking the right most bit
 * 
 * 2. we can use % operator with 2
 * 5 % 2 will give 1, 6 % 2 will give 0
 * for odd numbers right most bit will be 1, for even numbers right most bit will be 0
 * then we can do right shift once and repeat.
 */
public class NumberOf1Bits {
    public int hammingWeight(int n) {
        int res = 0;
        while(n>0){
            res += n%2;
            n= n >> 1;
        }
        return res;
    }
}


/**
 * You are given an unsigned integer n. Return the number of 1 bits in its binary representation.

You may assume n is a non-negative integer which fits within 32-bits.


Example 1:

Input: n = 23

Output: 4
Explanation: The binary representation of 23 is 10111, which contains four 1 bits.


Example 2:

Input: n = 2147483645

Output: 30
Explanation: The binary representation of 2147483645 is 1111111111111111111111111111101, which contains thirty 1 bits.
 */