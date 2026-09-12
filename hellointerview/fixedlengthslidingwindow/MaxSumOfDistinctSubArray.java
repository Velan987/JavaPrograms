package hellointerview.fixedlengthslidingwindow;

import java.util.HashSet;
import java.util.Set;

public class MaxSumOfDistinctSubArray {
    public static Long maxSumOfDistinctSubArray(int[] nums, int k){
        long maxSum = Long.MIN_VALUE;
        long slidingWindowSum = 0;

        Set<Integer> window = new HashSet<>();
        int slidingWindowStart = 0;
        for (int slidingWindowEnd = 0; slidingWindowEnd< nums.length; slidingWindowEnd++){

            // Remove elements until nums[slidingWindowEnd] is no longer duplicated
            // example nums: [4,2,4,5,6],    k: 4
            // first 2 iteration window will have 4 and 2, in third iteration only 4 is duplicate, so before adding 3rd value only remove first value 
            // here we should not clear all values from window and starts with index 3, then we will lose one window
            // another example [4,2,2,3,4] k: 3
            // first 2 iteration window will have 4 and 2, in third iteration we need to remove 4 and 2 then only we can form a unique subarray
            // move start pointer until window does not have crrent value
            while (window.contains(nums[slidingWindowEnd])){
                
                window.remove(nums[slidingWindowStart]);
                slidingWindowSum -= nums[slidingWindowStart];
                slidingWindowStart++;
            
            }
            
            slidingWindowSum += nums[slidingWindowEnd];
            window.add(nums[slidingWindowEnd]);
            if(slidingWindowEnd - slidingWindowStart == k-1){
                maxSum = Math.max(maxSum, slidingWindowSum);
                slidingWindowSum -= nums[slidingWindowStart];
                window.remove(nums[slidingWindowStart]);
                slidingWindowStart ++;
            }
        }

        return Long.MIN_VALUE == maxSum ? 0 : maxSum;
    }

    public static void main(String[] args) {
        int nums [] ={50,7,7,8};
        System.out.println(maxSumOfDistinctSubArray(nums, 3));
    }
}
