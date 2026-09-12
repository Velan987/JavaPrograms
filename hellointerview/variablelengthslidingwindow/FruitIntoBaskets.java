package hellointerview.variablelengthslidingwindow;

import java.util.HashMap;
import java.util.Map;

// Longest length of 2 fruit combination in a given array
public class FruitIntoBaskets {
    public int fruitIntoBaskets(int[] fruits) {
        int maxFruits = 0;
        Map<Integer, Integer> basket = new HashMap<>();
        int start = 0;
        for (int end = 0; end < fruits.length; end++) {
            // same fruit we can use multiple times
            basket.put(fruits[end], basket.getOrDefault(fruits[end], 0) + 1);
            // if third fruit is inserted then move start index until the basket has only 2 fruits - [3, 3, 2, 1, 2, 1, 0]
            while (basket.size() > 2) { // only 2 kinds of consecutive fruits we need to consider
                basket.put(fruits[start], basket.get(fruits[start]) - 1);
                if (basket.get(fruits[start]) == 0) {
                    basket.remove(fruits[start]);
                }
                start++;
            }
            maxFruits = Math.max(maxFruits, end - start + 1);
        }

        return maxFruits;
    }
}

/**
 * In fixed length we will change the window’s start and end index if window reaches the size k, 
 * but in variable length whenever a condition matches we will change the window start index.
 * 
 * Write a function to calculate the maximum number of fruits you can collect from an integer array fruits, 
 * where each element represents a type of fruit. You can start collecting fruits from any position in the array, 
 * but you must stop once you encounter a third distinct type of fruit. The goal is to find the longest subarray 
 * where at most two different types of fruits are collected.
Example:
Input: fruits = [3, 3, 2, 1, 2, 1, 0]
Output: 4
Explanation: We can pick up 4 fruit from the subarray [2, 1, 2, 1]
 */