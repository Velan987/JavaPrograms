package neetcode.blind75.greedy;

public class MaximumSubArray {

    /**
     * 
     * @param nums
     * @return
     * if the running sum becomes negative, keeping it will only reduce the sum of any future subarray, consider [2, -3, 4]
     * at second index running sum would be -1, if we keep -1 then adding it with 4 will give 3 which is lesser than 4, if third index contains negative value
     * then summing -1 with -4 will give -5 which is smaller than -4, so both the cases it wont give max sum, so whenever running sum drops below 0,
     * reset it and start a new subarray from next element. while scanning we keep track of best sub array sum
     */
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currSum = 0;
        
        for(int n: nums){
            if(currSum < 0){
                currSum = 0;
            }
            currSum += n;
            maxSum = Math.max(maxSum, currSum);
        }

        return maxSum;
    }
}


/**
 * Given an array of integers nums, find the subarray with the largest sum and return the sum.

A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:

Input: nums = [2,-3,4,-2,2,1,-1,4]

Output: 8
Explanation: The subarray [4,-2,2,1,-1,4] has the largest sum 8.

Example 2:

Input: nums = [-1]

Output: -1
Constraints:

1 <= nums.length <= 100,000
-10,000 <= nums[i] <= 10,000
 */