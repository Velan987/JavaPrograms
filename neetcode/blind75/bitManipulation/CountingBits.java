package neetcode.blind75.bitManipulation;

/**
 * 
 * CountingBits
 * write out result for num=16 to figure out pattern; res[i] = res[i - offset], where offset is the biggest power of 2 <= I;
 * 
 * we need to count the number of ones from zero to given number, counting 1's every number will take some time and we end up doing 
 * same calculation again and again. so we need to find the repeating pattern.
 * binary representation will change in 2 power n order, that is 2^0 is 1, 2^1 is 2, 2^2 is 4, 8,16,32,... (offsets are 1,2,4,8,16,32,64,...)
 * initialize offset as 1
 * 0    000
 * 1    001     1+ dp[i-offset], 1+dp[1-1]   dp[1]=>1
 * 2    010     offset * 2 ==i, set offset=i, dp[2] = 1+dp[i-offset]    dp[2]=1+0
 * 3    011     offset * 2 !=i, dp[3] = 1+dp[3-2]   dp[3]=2
 * 4    100     offset * 2 ==i, set offset=4, dp[4] = 1+ dp[4-4]        dp[4]=1
 * 5    101
 * 6    110
 * 7    111
 * 8    1000
 * 9    1001
 * 10   1010
 * 11   1011
 * 12   1100
 * 13   1101
 * 14   1110
 * 15   1111
 * 16   10000
 * 
 * after 4, except left most bit other 2 bits are changing just like zero to 3
 * after 8, except left most bit other 3 bits are changing just like zero to 7, and so on
 * this kind of repeatition happen when offset reaches next 2 power value(1,2,4,8,16,32,64,...)
 */
public class CountingBits {
    public int[] countBits(int n) {
        int[] dp = new int[n+1];
        int offset = 1;
        for(int i=1; i<n+1; i++){
            if(2 * offset == i)
                offset = i;

            dp[i] = 1 + dp[i-offset];
        }
        return dp;
    }

    // compare to above approach this V2 will be faster because it uses bitwise and and shift operator, cpu is faster for these operations
    public int[] countBitsV2(int n) {
        int[] result = new int[n + 1];
        // in this approach, for every number we are calculating the count of 1
        for (int i = 0; i <= n; i++) {
            int temp = i;
            int count = 0;
            while (temp > 0) {
                // bitwise and - both bits are 1 then 1 otherwise 0
                // any number bitwise and with 1 will check the right most bit is one or not
                // because binary for 1 is 001 and if the numbers last bit is 1 then bitwise of both will give exactly 1 - becase in 1 except riht most bit all other bits are 0 so bitwise will return 0 for those bits

                if ((temp & 1) == 1) {
                    count++;
                }
                // Right shift one time - because we already verified the right most bit.
                temp = temp >> 1;
            }
            result[i] = count;
        }

        return result;
    }
}




/**
 * Given an integer n, count the number of 1's in the binary representation of every number in the range [0, n].

Return an array output where output[i] is the number of 1's in the binary representation of i.

Example 1:

Input: n = 4

Output: [0,1,1,2,1]
Explanation:
0 --> 0
1 --> 1
2 --> 10
3 --> 11
4 --> 100
 */