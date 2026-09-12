package hellointerview.twopointer;

public class TwoSum {

    public boolean isPairSum(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int currentSum = nums[left] + nums[right];
            if (currentSum == target) {
                return true;
            }
            if (currentSum < target) {
                left++;
            } else {
                right--;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        boolean result = twoSum.isPairSum(nums, target);
        System.out.println("Is there a pair that sums to " + target + "? " + result);
    }
}


/**
 * DESCRIPTION
Given a sorted array of integers nums, determine if there exists a pair of numbers that sum to a given target.
Example:
Input: nums = [1,3,4,6,8,10,13], target = 13
Output: True (3 + 10 = 13)
Input: nums = [1,3,4,6,8,10,13], target = 6
Output: False
 */