package neetcode.blind75;

public class ProductsOfArrayExceptSelf {
    // if we can use division then in one full iteration we can find the product, then for each index we can divide the product by index value 
    // this will give the correct answer but should handle zero(if more than one zero then return array with 0's, if exactly one zero then for 0 value index alone add product, other indexes will be 0 ) - should not use division
    // we can have prefix and postfix array
    // prefix will have product value of before that index, for example input is [1,2,4,6] prefix array will be [1,1,2,8] (for index 3, prefix product is 8) - since multiplication 1 is considered fordefault value 
    // postfix array will have product value next to that index, for example input is [1,2,4,6] postfix array will be [48,24,6,1]  - since multiplication 1 is considered for default value 
    // now prefix will be multiplied with postfix, this will give the correct answer. time complexity is fine but more space is occupied
    // to overcome space issue, same kind of prefix and postfix operations we will do in single result array
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];

        // Performing prefix operation
        result[0]=1; // first index there is no prefix
        for(int i=1; i<nums.length; i++){
            result[i] = result[i-1] * nums[i-1];
        }

        // Postfix should be performed from end
        // for last index, there is no postfix so starting from length-2
        int postfix = 1;
        for(int i=nums.length-2; i>=0; i--){
            postfix = postfix * nums[i +1];
            result[i] = result[i] * postfix;
        }

        return result;
    }
}

/**
 * Given an integer array nums, return an array output where output[i] is the product of all the elements of nums except nums[i].

Each product is guaranteed to fit in a 32-bit integer.

Follow-up: Could you solve it in 
O
(
n
)
O(n) time without using the division operation?

Example 1:

Input: nums = [1,2,4,6]

Output: [48,24,12,8]
Example 2:

Input: nums = [-1,0,1,2,3]

Output: [0,-6,0,0,0]
Constraints:

2 <= nums.length <= 1000
-20 <= nums[i] <= 20

 */