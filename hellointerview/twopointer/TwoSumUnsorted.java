package hellointerview.twopointer;

import java.util.HashMap;
import java.util.Map;

public class TwoSumUnsorted {
    public int[] twoSum(int[] nums, int target) {
        int [] result = new int[2];
        // Arrays.sort(nums); // should not sort array
        // int start = 0, end = nums.length-1;
        // while(start<end){
        //     int total = nums[start]+nums[end];
        //     if(total == target){
        //         return new int[]{start,end};
        //     }
        //     if(total < target){
        //         start ++;
        //     }
        //     if(total > target){
        //         end --;
        //     }
        // }
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i< nums.length; i++){
            map.put(nums[i], i);
        }
        for(int i=0; i< nums.length; i++){
            int num2 = target - nums[i];
            if(map.containsKey(num2) && map.get(num2)!=i){
                return new int[] {i, map.get(num2)};
            }
        }
        /**
         * optimized with single loop
         * Map<Integer, Integer> map = new HashMap<>();
        
        for(int i=0; i< nums.length; i++){
            int num2 = target - nums[i];
            if(map.containsKey(num2)){
                return new int[] {map.get(num2), i};
            }
            map.put(nums[i], i);
        }
         */
        return result;
    }
}

/**
 * Given an array of integers nums and an integer target, return the indices i and j such that nums[i] + nums[j] == target and i != j.

You may assume that every input has exactly one pair of indices i and j that satisfy the condition.

Return the answer with the smaller index first.

Example 1:

Input: 
nums = [3,4,5,6], target = 7

Output: [0,1]
Explanation: nums[0] + nums[1] == 7, so we return [0, 1].

Example 2:

Input: nums = [4,5,6], target = 10

Output: [0,2]
Example 3:

Input: nums = [5,5], target = 10

Output: [0,1]
Constraints:

2 <= nums.length <= 1000
-10,000,000 <= nums[i] <= 10,000,000
-10,000,000 <= target <= 10,000,000
Only one valid answer exists.
 */