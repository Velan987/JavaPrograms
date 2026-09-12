package hellointerview.dynamicprogramming;

public class CountingBits {
    public static int[] countBits(int n){
        int[] result = new int[n+1];
        /**
         * Any binary number can be broken down into 2 parts, right most bit and remaining bits. 
         * The remaining bits can be expressed as the binary number /2 
         * For example:
            4 in binary = 100
            rightmost bit = 0
            rest of bits = 10, which is also (4 // 2) = 2 in binary.
            When the number is odd,
            5 in binary = 101
            rightmost bit = 1
            rest of bits = 10, which is also (5 // 2) = 2 in binary.

            If we know the number of 1's in the binary representation of i // 2, then the number of 1's in the binary representation of i is that number plus 1 if the rightmost bit is 1. 
            // We can tell if the last significant bit is 1 by checking if it is odd.
         */
        for(int i=1; i<=n; i++){
            // int array will have 0 as default value, for i=1 it will be 0 +1
            // for i=2, result[1] + 0. => 1
            // i=3, resutl[1] + 1 => 2
            result[i] = result[i/2] + i % 2;
        }

        return result;
    }
}

/**
 * Write a function that, given an integer n, returns an array dp of size n + 1, where dp[i] stores the count of '1' bits in the binary form of i.

Input:

n = 6
Output:

[0,1,1,2,1,2,2]
Explanation:

0 --> 0
1 --> 1
2 --> 10
3 --> 11
4 --> 100
5 --> 101
6 --> 110
This problem is intended to give you practice with implementing a bottom-up dynamic programming solution.
 */