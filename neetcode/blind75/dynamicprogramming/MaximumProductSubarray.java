package neetcode.blind75.dynamicprogramming;

public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        // brute force
        int res = nums[0];
        for(int i=0; i<nums.length; i++){
            int cur = nums[i];
            // if current number is greater than previous max sum
            res = Math.max(res, cur);
            for(int j=i +1; j<nums.length; j++){
                cur *= nums[j];
                res = Math.max(res, cur);
            }
        }
        return res;
    }

    /**
     * in max sum subarray we only track one value(current max sum)
     * for products, thats not enough because
     * a negative * negative = positive
     * and a very small (negative) product can suddenly become the max after multiplying by another negative
     * so every index we must tract 2 values: curMax, curMin
     * curMax - maximum product ending at this index
     * curMin - minimum product ending at this index - if the current number is negative, multiplying it with curMin might give new max
     */
    public int maxProductV2(int[] nums){
        int res = nums[0];
        int currMin=1, currMax =1;

        for(int num : nums){
            int tmp = currMax * num;
            /**
             * 2, 4, -3, 5, 3
             * in this example when num = -3
             * currMax = -3, currMin =-3
             * res = 8
             * when num=5
             * currMax = 5, currMin = -15, res =8
             * 
             * when num=3
             * currMax =15, currMin=-45, res=15
             * 
             * suppose we have another negative value at end then currMin will become positve and become a large value(currMax)
             * 
             */
            currMax = Math.max(Math.max(currMax * num, currMin * num),num);
            currMin = Math.max(Math.max(tmp, currMin * num), num);

            res = Math.max(res, currMax);
        }

        return res;
    }

    public static void main(String[] args) {
        MaximumProductSubarray pr = new MaximumProductSubarray();
        int[] input = {2, 4, -3, 5, 3};
        System.out.println(pr.maxProductV2(input));
    }
    
}

/**
 * Given an integer array nums, find a subarray that has the largest product, and return the product.

A subarray is a contiguous non-empty sequence of elements within an array.

You can assume the output will fit into a 32-bit integer.

Note that the product of an array with a single element is the value of that element.

Example 1:

Input: nums = [2,4,-3,5]

Output: 8
Explanation: [2,4] has the largest product 8.


Example 2:

Input: nums = [-3,0,-2]

Output: 0
Explanation: The result cannot be 6, because [-3,-2] is not a subarray.


Constraints:

1 <= nums.length <= 20,000
-10 <= nums[i] <= 10
The product of any subarray of nums is guaranteed to fit in a 32-bit integer.
 */