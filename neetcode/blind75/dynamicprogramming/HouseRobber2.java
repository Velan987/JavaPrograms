package neetcode.blind75.dynamicprogramming;

import java.util.Arrays;

public class HouseRobber2 {
    /**
     * houses are in circle, so the first and last house cannot both be robbed
     * to handle this circle constraint, we split the problem into 2 linear cases
     * 1. Rob from house 0 to n-2 (exclude last house)
     * 2. Rob from house 1 to n-1 (exclude first house)
     * 
     * A flag is used to ensure if the first house is robbed, the last house is not allowed
     * finally we take the max result from both the cases
     */
    public int rob(int[] nums) {
        if(nums.length == 1)
            return nums[0];
        
        // split problem into 2 - true means first house is robbed
        return Math.max(dfs(nums, true, 0), dfs(nums, false, 1));
    }

    private int dfs(int[] nums, boolean flag, int i){
        // flag && i==nums.length-1 -> first house is robbed and current i is indicates the last house
        if(i >=nums.length || (flag && i==nums.length-1))
            return 0;

        return Math.max(
            //skip current house
            dfs(nums, flag, i+1),
            //rob current house
            nums[i] + dfs(nums, flag, i+2));
    }

    // to overcome timelimit exceeded issue we can use DP-bottom up
    // we will create 2 arrays, array1 starts from o to n-2 and array2 starts from 1 to n-1 - circular issue will be handled here
    // then we will calculate linear house robbing problem and get the max
    public int robV2(int[] nums) {
        if(nums.length == 1)
            return nums[0];
        
        int [] nums1 = Arrays.copyOfRange(nums, 0, nums.length-1);
        int [] nums2 = Arrays.copyOfRange(nums, 1, nums.length);
        return Math.max(
            helper(nums1),
            helper(nums2));
    }

    // DP - bottom up - Instead of deciding recursively, we build the answer step by step
    /**
     * for each house i, the maximum money depends on
     *  Not robbing it -> same money as i-1
     *  Robbing it -> money at i + best upto i-2
     * we need to choose the better of the 2 at every step
     */
    public int helper(int[] nums) {
        int []dp = new int[nums.length];
        // 1 <= nums.length <= 100 - this is the constraint given, so length will not be less than 1
        // dp[1] we are populating, if the length is 1 then it will throw outofbound exception, to handle that we should add return statement here
        if(nums.length == 1){
            return nums[0];
        }
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        for(int i=2; i<nums.length; i++){
            dp[i] = Math.max(
                // skip current house
                dp[i-1],
                // rob current house
                dp[i-2] + nums[i]);
        }

        return dp[nums.length-1];
    }
}

/**
 * You are given an integer array nums where nums[i] represents the amount of money the ith house has. 
 * The houses are arranged in a circle, i.e. the first house and the last house are neighbors.

You are planning to rob money from the houses, but you cannot rob two adjacent houses 
because the security system will automatically alert the police if two adjacent houses were both broken into.

Return the maximum amount of money you can rob without alerting the police.

Example 1:

Input: nums = [3,4,3]

Output: 4
Explanation: You cannot rob nums[0] + nums[2] = 6 because nums[0] and nums[2] are adjacent houses. The maximum you can rob is nums[1] = 4.

Example 2:

Input: nums = [2,9,8,3,6]

Output: 15
Explanation: You cannot rob nums[0] + nums[2] + nums[4] = 16 because nums[0] and nums[4] are adjacent houses. The maximum you can rob is nums[1] + nums[4] = 15.

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 200
 */