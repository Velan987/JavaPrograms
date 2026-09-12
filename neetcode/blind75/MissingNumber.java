package neetcode.blind75;

public class MissingNumber {
    /**
     * 
     * @param nums
     * @return
     * Array contains no duplicates, and it contains value from zero n(length of the array)
     * if n=3, then array might have [3,0,1] - missing 2
     * to find the missing value we can do 2 things
     * 1.using XOR gate - this will work on bit level - if we XOR same number this will give 0, 0 ^ n will give n
     * we can have another array which will have values from 0 to n without missing any value and do XOR with input nums
     * this way XOR will give the missing number, other values will become zero
     * 
     * 2. we can have sum of input nums and sum of array from 0 to n
     * subtracting these 2 sums will give missing number 
     * single iteration we can have 2 sums
     * index will be used for new array sum and values will be used for input array sum
     * since input array is missing one value, while iteration we will iterate upto n-1 only
     * so last value for new array we will miss, so we can initialize res with nums.length or initialize with 0 and before returning add nums.length
     * instead of having 2 sum variables and subtracting it at last we can do the subtraction on the go and add it to res
     * 
     * basically one sum will have sum of all numbers from 0 to n, another sum will have given inputs sum, subtracting these 2 will give missing number
     * this way we will have 2 vars, we can achieve this in single var also like below
     */
    public int missingNumber(int[] nums) {
        int res = nums.length;
        for(int i=0; i<nums.length; i++){
            res += i - nums[i];
        }
        /**
        int sum1=0, sum2=0;
        for(int i=0; i<nums.length;i++){
            sum1 += i;
            sum2 += nums[i];
        }
        sum1 += nums.length;
        return sum1-sum2;
        */
        return res;
    }
}

/**
 * Given an array nums containing n integers in the range [0, n] without any duplicates, return the single number in the range that is missing from nums.

Follow-up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?

Example 1:

Input: nums = [1,2,3]

Output: 0
Explanation: Since there are 3 numbers, the range is [0,3]. The missing number is 0 since it does not appear in nums.

Example 2:

Input: nums = [0,2]

Output: 1
 */