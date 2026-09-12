package hellointerview.twopointer;

import java.util.Arrays;

public class ValidTriangle {
    public int triangleCount(int [] nums){
        int count = 0;
        Arrays.sort(nums);
        // Condition is a+c >b, b+c >a and a + b > c
        // Since we are taking larger number as i i.e c, summing with any number from array(before c) with c will obviously greater than c 
        // so a+c >b, b+c >a these 2 conditions we don’t need to worry because array is sorted and largest number is considered as c.

        // need 3 values to form triangle, thats why i >1
        for (int i = nums.length-1; i > 1; i--){
            int left = 0;
            int right = i-1;
            while (left < right){
                if(nums[left] + nums[right] > nums[i]){
                    // if left + right is greater than i then fixing right and moving left point until right will give values greater than i only
                    // because array is sorted and next value of left alway greater than or equal to current so we dont need to compare to combinations
                    count += right - left;
                    right --;
                }else{
                    left ++;
                }
            }
        }
        return count;
    }
}
