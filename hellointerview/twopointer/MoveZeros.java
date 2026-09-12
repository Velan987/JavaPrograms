package hellointerview.twopointer;

public class MoveZeros {
    public void moveZeros(int[] nums){
        // start both points(i, nextNonZero) at 0th index
        // if i value zero then no action, increament i alone
        // if i value non zero then then swap i and nextNonZero values, increament both i and nextNonZero
        int nextNonZero = 0;
        for(int i=0; i< nums.length; i++){
            if (nums[i] != 0){
                int tmp = nums[nextNonZero];
                nums[nextNonZero] = nums[i];
                nums[i] = tmp;
                nextNonZero ++;
            }
        }

    }
}

/**
 * Given an integer array nums, write a function to rearrange the array by moving all zeros to the end 
 * while keeping the order of non-zero elements unchanged. Perform this operation in-place without creating a copy of the array.

Input:

nums = [2,0,4,0,9]
Output:

[2,4,9,0,0]
 */