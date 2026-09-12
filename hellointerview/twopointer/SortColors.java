package hellointerview.twopointer;

public class SortColors {
    public void sortColors(int[] nums){
        int left=0;
        int right = nums.length-1;
        int i = 0;
        while ( i<= right){
            int tmp = nums[i];
            if(tmp == 0){
                nums[i] = nums[left];
                nums[left] = tmp;
                left ++;
                i ++;
            } else if(tmp == 1){
                i ++;
            }else{
                nums[i] = nums[right];
                nums[right] = tmp;
                right --;
            }
        }
    }
}


/**
 * Write a function to sort a given integer array nums in-place (and without the built-in sort function), where the array contains n integers that are either 0, 1, and 2 and represent the colors red, white, and blue. Arrange the objects so that same-colored ones are adjacent, in the order of red, white, and blue (0, 1, 2).

Input:

nums = [2,1,2,0,1,0,1,0,1]
Output:

[0,0,0,1,1,1,1,2,2]

Conditions: sorting should be done in same array with order(n)
Brute force solution will be, pass one to count how many 0’s, 1’s and 2’s 
 pass two rewrite the array from left to right – we write count of 0’s and count of 1’s and count 2’s
this works but it is taking two passes in the array, we can make it in one pass.
Solution:
	Take 3 pointers. i, left, right
Left pointer represent 0 zone and right pointer represent 2 zone and i represent unsorted zone

If value of I is 0 – swap with left, increment both I and left
If value of I is 1 – just advance I, the 1 already sitting in one zone
If value od I is 2 – swap with right, decrement right. DON’T increment I because I is from unsorted zone and still need to check that value

 */