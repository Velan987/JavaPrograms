package neetcode.blind75.bitManipulation;

//https://www.youtube.com/watch?v=UcoN6UjAI64&t=1s
public class ReverseBits {
    public int reverseBits(int n) {
        int res = 0;

        // given input is 32 bit integer
        for(int i=0; i<32; i++){
            // to get right most bit we can do bitwise and (&) with 1
            // after getting right most bit to move remaining bits to right we can do >>
            int rightBit = (n >> i) & 1;
            // we need to place this rightBit to res from left, so we need to move this right bit left side
            // 0th bit should be placed in 32 position, so we need shift rightBit 31 times
            // res is initialized with 0, so every bit will be 0, to place incoming bit we can do bitwise OR (|)
            res = res | (rightBit << 31 - i);
        }

        return res;
    }
}

/**
 * Given a 32-bit unsigned integer n, reverse the bits of the binary representation of n and return the result.

Example 1:

Input: n = 00000000000000000000000000010101

Output:    2818572288 (10101000000000000000000000000000)
Explanation: Reversing 00000000000000000000000000010101, which represents the unsigned integer 21, gives us 10101000000000000000000000000000 which represents the unsigned integer 2818572288.



 */