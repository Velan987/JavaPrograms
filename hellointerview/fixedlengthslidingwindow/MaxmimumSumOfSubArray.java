package hellointerview.fixedlengthslidingwindow;

//https://www.hellointerview.com/learn/code/sliding-window/maximum-sum-of-subarrays-of-size-k
public class MaxmimumSumOfSubArray {
    public int maxmiumSum(int[] nums, int k){
        int max = Integer.MIN_VALUE;
        int slidingWindowSum = 0;
        int slidingWindowStart = 0;
        for (int slidingWindowEnd=0; slidingWindowEnd<nums.length; slidingWindowEnd++){
            slidingWindowSum += nums[slidingWindowEnd];
            if(slidingWindowEnd - slidingWindowStart == k -1){ // we have valid window
                max = Math.max(max, slidingWindowSum);
                // subtracting current sliding window start value from sliding window sum, so that in next iteration we just need to add next value
                slidingWindowSum -= nums[slidingWindowStart];
                slidingWindowStart ++;
            }
        }

        return max;
    }
}


/**
 * Given an array of integers nums and an integer k, find the maximum sum of any contiguous subarray of size k.

Example 1: Input:

nums = [2, 1, 5, 1, 3, 2]
k = 3
Output:

9
Explanation: The subarray with the maximum sum is [5, 1, 3] with a sum of 9.
 */