package neetcode.neetcode150.bitManipulation;

/**
 * 
 * SingleNumber
 * one solution is we can use hashset
 * iterate through the array, for each element - if that element not there in the set then add it to the set
 * if element already there then remove it, so at last set will have only one integer that is our result
 * 
 * another solution is we can sort the array, in that way duplicates will sit next to its original
 * element which dont have same number in the neighbour is our candidate
 * 
 * but this is a perfect fit for Bit manipulation. specifically XOR (^) gate
 * a ^ a = 0 (a number XORed with itself cancels out)
    a ^ 0 = a (XOR with 0 keeps the number unchanged)
    XOR is commutative and associative, so order does not matter
    Because of these properties:

    all numbers that appear twice will cancel each other out
    the number that appears once will remain

    Same inputs      → 0
    Different inputs → 1
    For integers, Java performs XOR on every pair of binary bits.
    example [5,3,5] 
    5 - 0101, 3- 0011
    0101
    0011 (^)
    0110    - which is 6, now again XOR with 5
    0101 (^)
    0011    - which is 3 - our expected result

    from this we can identify that XOR will cancel out same numbers 5^5 will be 0, because same bit result is 0
 */
public class SingleNumber {
    public int singleNumber(int[] nums) {
        int res = 0;
        for(int num: nums){
            res ^= num;
        }
        return res;
    }
}


/**
 * You are given a non-empty array of integers nums. Every integer appears twice except for one.

Return the integer that appears only once.

You must implement a solution with 

O(n) runtime complexity and use only 

O(1) extra space.

Example 1:

Input: nums = [3,2,3]

Output: 2
Example 2:

Input: nums = [7,6,6,7,8]

Output: 8
 */