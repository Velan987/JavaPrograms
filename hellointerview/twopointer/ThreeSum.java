package hellointerview.twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int [] nums){
        List<List<Integer>> uniqueTriplets = new ArrayList<>();

        // Sort the array first to use 2 pointer technique and avoid duplicates
        Arrays.sort(nums);

        // Pick one element then we can use two pointer technique 
        // length-2 - so that two more values will be there to form triplet
        for (int i=0; i<nums.length-2; i++){
            // Fix duplicate values for the first element to avoid duplicate triplet
            if (i>0 && nums[i] == nums[i-1]){
                continue;
            }
            // Initialize two pointers
            int left = i +1;
            int right = nums.length -1;
            while(left < right){
                int tmp = nums[i] + nums[left] + nums[right];
                // total is greater than 0, so right element is larger(because the array is sorted)
                if(tmp >0){
                    right --;
                }else if(tmp < 0){
                    left ++;
                } else{
                    // Found a valid triplet
                    uniqueTriplets.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Move left pointer to avoid duplicate
                    while (left < right && nums[left] == nums[left+1]){
                        left ++;
                    }
                    
                    // Move right pointer to avoid duplicate
                    while (left < right && nums[right] == nums[right-1]){
                        right --;
                    }
                    // Move left and right pointer - above pointer movements will be done only if next or previous element is same
                    // index 1 and index 2 elements are same so we moved to index 2, now index 2 and index 3 elements are not same
                    // in this case since index 1 and indes 2 values are same we already consider index 2 value also, so below movement is necessary
                    left ++;
                    right --;
                }
            }
        }
        
        
        return uniqueTriplets;
    }
}


/**
 * Given an input integer array nums, write a function to find all unique triplets [nums[i], nums[j], nums[k]] such that i, j, and k are distinct indices, and the sum of nums[i], nums[j], and nums[k] equals zero. Ensure that the resulting list does not contain any duplicate triplets.

Input:

nums = [-1,0,1,2,-1,-1]
Output:

[[-1,-1,2],[-1,0,1]]
Explanation: Both nums[0], nums[1], nums[2] and nums[1], nums[2], nums[4] both include [-1, 0, 1] and sum to 0. nums[0], nums[3], nums[4] ([-1,-1,2]) also sum to 0.

Since we are looking for unique triplets, we can ignore the duplicate [-1, 0, 1] triplet and return [[-1, -1, 2], [-1, 0, 1]].

The order of the triplets and the order of the elements within the triplets do not matter.
 */