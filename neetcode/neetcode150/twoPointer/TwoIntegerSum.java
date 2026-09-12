package neetcode.neetcode150.twoPointer;

public class TwoIntegerSum {
    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        int l=0, r=numbers.length-1;
        while(l<r){
            int tmp = target - numbers[l];
            if(tmp == numbers[r]){
                result[0] = l+1;
                result[1] = r+1;
                return result;
            }
            // if tmp is lesser than right pointer value then move right pointer to left side
            while(tmp < numbers[r]){
                r --;
            }
            // if tmp is greater than right pionter value then move left pointer and calculate temp again
            while(tmp > numbers[r]){
                l ++;
                tmp = target - numbers[l];
            }
        }
        return result;
    }
}

/**
 * Given an array of integers numbers that is sorted in non-decreasing order.

Return the indices (1-indexed) of two numbers, [index1, index2], such that they add up to a given target number target and index1 < index2. Note that index1 and index2 cannot be equal, therefore you may not use the same element twice.

There will always be exactly one valid solution.

Your solution must use O(1) additional space.

Example 1:

Input: numbers = [1,2,3,4], target = 3

Output: [1,2]
Explanation:
The sum of 1 and 2 is 3. Since we are assuming a 1-indexed array, index1 = 1, index2 = 2. We return [1, 2].

Constraints:

2 <= numbers.length <= 30000
-1000 <= numbers[i] <= 1000
-1000 <= target <= 1000

 */