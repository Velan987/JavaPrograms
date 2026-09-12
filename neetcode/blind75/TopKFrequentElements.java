package neetcode.blind75;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        // we need to return k number of elements based on the frequency. (its like sort the numbers based on frequency and return top k elements)
        int [] result = new int[k];
        // Map will store each number and its frequency
        Map<Integer, Integer> map = new HashMap<>();
        for(int num: nums){
            /**
             * If the key is absent, it adds key → 1.
                If the key exists, it adds 1 to the existing value.
                Example:
                map.merge(10, 1, Integer::sum); // {10=1}
                map.merge(10, 1, Integer::sum); // {10=2}
                map.merge(20, 1, Integer::sum); // {10=2, 20=1}
             */
            map.merge(num, 1, Integer::sum);
        }

        // Array will have frequency as index and the number as value - [1,2,2,2,3,3,1,1,1], for this in index 3 value will be2, in index 2 value will be 3 in index 4 value will be 1
        // length of this frequency array is nums.length because if 5 numbers in the array then maximum frequency is 5 or less - 5 means all elements in the inputs are same, so frequency cannot exceed the length of the input
        // nums.length+1 - because arrayindex starts at 0
        // *** It is failing if 2 numbers are in same frequency, in that case below will have only one value
        // to solve this we need to use list array instead of int array for frequency
        // int[] frequency = new int[nums.length+1];
        // for(Map.Entry<Integer,Integer> item: map.entrySet()){
        //     frequency[item.getValue()] = item.getKey();
        // }
        // //iterate from end of the frequency 
        // int j=0;
        // for(int i=frequency.length-1; i>0 && j<k; i--){
        //     if(frequency[i] != 0){
        //         result[j++] = frequency[i];

        //     }
        // }

        // List array
        List<Integer>[] freq = new List[nums.length + 1];

        for (int i = 0; i < freq.length; i++) {
            freq[i] = new ArrayList<>();
        }

        // map will have frequency as value and number as key
        // In grequency array we are adding the arraylist
        for(Map.Entry<Integer,Integer> item: map.entrySet()){
            freq[item.getValue()].add(item.getKey());

        }

        int j=0;
        for(int i=freq.length-1; i>=0; i--){
            // freq[i] will have array list - it might have multiple values if multiple numbers have same frequency
            for(int num: freq[i]){
                result[j++] = num;
                if(j>=k){
                    return result;
                }
            }
        }

        return result;
    }
}

/**
 * Given an integer array nums and an integer k, return the k most frequent elements within the array.

The test cases are generated such that the answer is always unique.

You may return the output in any order.

Example 1:

Input: nums = [1,2,2,3,3,3], k = 2

Output: [2,3]
if k=3 then output will be [1,2,3]

Example 2:

Input: nums = [7,7], k = 1

Output: [7]
Constraints:

1 <= nums.length <= 10^4.
-1000 <= nums[i] <= 1000
1 <= k <= number of distinct elements in nums.
 */