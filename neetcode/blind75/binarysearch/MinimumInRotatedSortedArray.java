package neetcode.blind75.binarysearch;

public class MinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        int left=0, right=nums.length-1;
        
        while(left<right){
            /**
             * This version avoids possible integer overflow that
             * could occur with (left + right) / 2.
             * 
             * here overflow means integer range overflow, int range is -2,147,483,648 to 2,147,483,647
             * here before dividing we are adding, so if left is 1 and right is 2,147,483,647 then while adding these 2 it will overflow the integer limit
             */
            int mid = left + (right-left)/2;

            /**
             * if mid element is greater than right element, then smaller element will be at right side of the mid element only because it is a rotted sorted array
             * [3, 4, 5, 6, 1, 2]
             * here mid is 5 and right is 2, mid is greater than right so smaller element will be right side of mid only
             * [1, 4, 5, 6, 2, 3] - THIS IS NOT A VALID ROTATED SORTED ARRAY
             * 
             */
            if(nums[mid] > nums[right]){
                left = mid +1;
            }else{
                // if mid is less than right then smaller element will be left side of mid
                /**
                 * [5, 6, 1, 2, 3, 4]
                 * here mid=1, right =4, mid<right
                 * so smaller can either be mid or left side of mid
                 */
                right = mid;
            }

        }
        /**
         * when loop ends, left == right
         * both index points to the smallest element
         */
        return nums[left];
    }
}


/**
 * You are given an array of length n which was originally sorted in ascending order. It has now been rotated between 1 and n times. 
 * For example, the array nums = [1,2,3,4,5,6] might become:

[3,4,5,6,1,2] if it was rotated 4 times.
[1,2,3,4,5,6] if it was rotated 6 times.
Notice that rotating the array 4 times moves the last four elements of the array to the beginning. Rotating the array 6 times produces the original array.

Assuming all elements in the rotated sorted array nums are unique, return the minimum element of this array.

A solution that runs in O(n) time is trivial, can you write an algorithm that runs in O(log n) time?

Example 1:

Input: nums = [3,4,5,6,1,2]

Output: 1
Example 2:

Input: nums = [4,5,0,1,2,3]

Output: 0
Example 3:

Input: nums = [4,5,6,7]

Output: 4
Constraints:

1 <= nums.length <= 1000
-1000 <= nums[i] <= 1000

 */