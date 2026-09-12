package hellointerview.heap;

import java.util.PriorityQueue;

public class KthLargestElement {
    public int kthLargest(int[] nums, int k) {
        if (nums.length == 0) {
            return -1;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) {
            if (heap.size() < k) {
                heap.offer(num);
            } else if (num > heap.peek()) {
                heap.poll();
                heap.offer(num);
            }
        }
        return heap.peek();
    }
}

/**
 * DESCRIPTION (inspired by Leetcode.com)
Write a function that takes an array of unsorted integers nums and an integer k, and returns the kth largest element in the array. 
This function should run in O(n log k) time, where n is the length of the array.

Example 1:

Inputs:

nums = [5, 3, 2, 1, 4]
k = 2
Output:

4
 */
