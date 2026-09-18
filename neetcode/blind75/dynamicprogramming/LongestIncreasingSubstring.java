package neetcode.blind75.dynamicprogramming;

import java.util.Arrays;

public class LongestIncreasingSubstring {
    /**
     * Main idea is For every position i, calculate: What is the length of the longest increasing subsequence that ends at index i?
     * dp[i] = length of the longest increasing subsequence ending at nums[i]
     * For every nums[i], examine all previous elements nums[j]
     * If nums[j] < nums[i] then nums[i] can be added after the subsequence ending at nums[j]:
     * dp[i] = Math.max(dp[i], dp[j] + 1); 
     * suppose nums = [1, 2, 5], j points to 2 and i points to 5
     * The longest increasing subsequence ending at 2 is: 
     *  [1, 2]  Its length is: dp[j] = 2
     * Because: nums[j] < nums[i]
     * 2 < 5, we can add 5 to the end:
     * [1, 2] + [5] = [1, 2, 5]
     * The new length is therefore: dp[j] + 1
     * = 2 + 1 => 3
     * The +1 represents adding the current element nums[i]
     * We use <, not <=, because the subsequence must be strictly increasing.
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];

        // Every individual element forms a subsequence of length 1, so initially
        Arrays.fill(dp, 1);

        int longestLength = 1;

        for (int i = 0; i < nums.length; i++) {

            // Check every element before nums[i].
            for (int j = 0; j < i; j++) {

                // nums[i] can extend the subsequence ending at nums[j].
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            longestLength = Math.max(longestLength, dp[i]);
        }

        return longestLength;
    }

    public static void main(String[] args) {
        int []nums = {4,10,4,3,8,9};
        LongestIncreasingSubstring lis = new LongestIncreasingSubstring();
        System.out.println(lis.lengthOfLIS(nums));
    }
}

/**
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.

A subsequence is a sequence that can be derived from the given sequence by deleting some or no elements without changing the relative order of the remaining characters.

For example, "cat" is a subsequence of "crabt".
Example 1:

Input: nums = [9,1,4,2,3,3,7]

Output: 4
Explanation: The longest increasing subsequence is [1,2,3,7], which has a length of 4.

Example 2:

Input: nums = [0,3,1,3,2,3]

Output: 4
Constraints:

1 <= nums.length <= 1000
-1000 <= nums[i] <= 1000
 */