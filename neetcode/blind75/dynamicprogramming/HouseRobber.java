package neetcode.blind75.dynamicprogramming;

import java.util.Arrays;

public class HouseRobber {
    // every hourse we have 2 possibilities, we can rob the house and skip next house or we can skip the house and rob next house
    // we need to get the maximum from these 2 options
    // we need to repeat this until the last house -  that is our base case
    public int rob(int[] nums) {
        return dfs(nums, 0);
    }
    private int dfs(int[] nums, int i){
        // we robbed the last house or house before last house
        if(i >= nums.length){
            return 0;
        }
        return Math.max(
            // Skip current house and move to next house to rob
            dfs(nums, i+1), 
            // Rob current house and skip next house
            nums[i]+dfs(nums, i+2)
        );
    }

    // in above dfs, we are invoking dfs 2 times, that will cause duplicate invocation
    // so we have room for memoization
    int[] memo;
    public int robV2(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return dfsV2(nums, 0);
    }
    private int dfsV2(int[] nums, int i){
        if(i >= nums.length)
            return 0;
        if(memo[i] != -1){
            return memo[i];
        }else{
            memo[i] = Math.max(
                // Skip current house and move to next house to rob
                dfsV2(nums, i+1), 
                // Rob current house and skip next house
                nums[i]+dfsV2(nums, i+2)
            );
        }
        return memo[i];
    } 

    // DP - bottom up - Instead of deciding recursively, we build the answer step by step
    /**
     * for each house i, the maximum money depends on
     *  Not robbing it -> same money as i-1
     *  Robbing it -> money at i + best upto i-2
     * we need to choose the better of the 2 at every step
     */
    public int robV3(int[] nums) {
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
 * The houses are arranged in a straight line, i.e. the ith house is the neighbor of the (i-1)th and (i+1)th house.

You are planning to rob money from the houses, but you cannot rob two adjacent houses 
because the security system will automatically alert the police if two adjacent houses were both broken into.

Return the maximum amount of money you can rob without alerting the police.


Example 1:

Input: nums = [1,1,3,3]

Output: 4
Explanation: nums[0] + nums[2] = 1 + 3 = 4.


Example 2:

Input: nums = [2,9,8,3,6]

Output: 16
Explanation: nums[0] + nums[2] + nums[4] = 2 + 8 + 6 = 16.


Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 100

 */
