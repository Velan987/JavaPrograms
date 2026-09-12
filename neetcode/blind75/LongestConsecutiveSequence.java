package neetcode.blind75;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// https://www.youtube.com/watch?v=P6RZZMu_maU
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        // we should not sort the array
        // basic idea here is, we will split the array into sequence and return the length of larger sequence size
        // we will create set using elements from input array
        // to create sequence - will get one element from array and check previous element(element-1) is there in the set, if yes then this is not the befinning of the sequence, so skip it
        // if there is no previous element then that is the first element of the sequence, now create one sequence and check next element(element+1) is there in the set, if yes add it to sequence, else end of sequence
        // repeat this untill end of array, and return largest sequence size
        // we only need count, not the sequence, so we can have 2 variables to get maximum count
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }

        int count = 0;
        for(int num: nums){
            int tmp = 0;
            // Check previous element is there
            if(set.contains(num-1)){
                continue;
            }else{ // num is the start of the sequence
                tmp++;  // even if it is single number in the sequence then also count will be 1
                // check next sequence number is there in the set, if yes increment tmp
                while(set.contains(++num)){
                    tmp ++;
                }
            }
            count = Math.max(count, tmp);
        }
        

        return count;
    }
}

/**
 * Given an array of integers nums, return the length of the longest consecutive sequence of elements that can be formed.

A consecutive sequence is a sequence of elements in which each element is exactly 1 greater than the previous element. The elements do not have to be consecutive in the original array.

You must write an algorithm that runs in O(n) time.

Example 1:

Input: nums = [2,20,4,10,3,4,5]

Output: 4
Explanation: The longest consecutive sequence is [2, 3, 4, 5].

Example 2:

Input: nums = [0,3,2,5,4,6,1,1]

Output: 7
Constraints:

0 <= nums.length <= 1000
-10^9 <= nums[i] <= 10^9

 */