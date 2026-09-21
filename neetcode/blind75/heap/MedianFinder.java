package neetcode.blind75.heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Median - mid element of sorted array, if array size is ecen then average of two mid elements
 * for a fixed array we can sort the array and find median, here the problem is numbers will keep on coming - a stream of numbers
 * at any point if we want median we need to sort, basically every time we need to sort the array - that is lot of computation
 * what if we store the elements in sorted order itself (using priorityQueue), to find median we just need to take mid or average of 2 mid elements
 * this is somewhat ok, still to find median we need to peek(return high priority element-will not remove) elements from queue n/2 times
 * we can still improve this. Priority queue will return highest priority element at O(1) complexity, because that number will be the first number always
 * By default priorityQueue is MinHeap, meaning queue will be in ascending order, it will return smallest element in O(1)
 * MaxHeap will be in descening order, it will return largest element in O(1)
 * [2,4,3,1,5] in this example, suppose we store it like [2,1] and [3,4,5] then
 * finding median is just O(1) of larger size heap. here first heap is maxHeap(larger number will be first element), second one is minHeap(smaller number to larger)
 * for even size input we can take first element form both heap and return the average
 * IMPORTANT point is both heap size should be same or atmost 1 difference at any point in time 
 * Adding number logic is
 *  1. Peek from maxheap, if input is greater than peeked element then add it to minHeap, else add it to maxHeap
 *  2. check size difference - if difference >1 then, poll from bigger heap and add it to smaller heap 
 * 
 * in above step 1, we can also peek from minHeap and compare with input number, if input number is smaller than peeked number then add it to maxHeap, else add it to minHeap
 * we can use either of the logic, but we need to be consistent in using one logic, else it will not work
 * one important thing is we need to maintain the size difference between both heaps to be atmost 1, else median will not be correct
 */
public class MedianFinder {
    // stores numbers in ascending order
    Queue<Integer> minHeap;
    // stores numbers in descending order
    Queue<Integer> maxHeap;
    public MedianFinder() { 
        // priorityQueue is by default minHeap
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }
    
    public void addNum(int num) {
        // if maxHeap is empty then add the number to maxHeap, else peek from maxHeap and compare with input number, 
        // if input number is greater than peeked number then add it to minHeap, else add it to maxHeap
        // if(maxHeap.peek() == null){
        //     maxHeap.offer(num);
        // }else{
        //     int tmp = maxHeap.peek();
        //     if(num > tmp){
        //         minHeap.offer(num);
        //     }else{
        //         maxHeap.offer(num);
        //     }
        // }
        if(minHeap.peek() == null){
            minHeap.offer(num);
        }else{
            int tmp = minHeap.peek();
            if(num < tmp){
                maxHeap.offer(num);
            }else{
                minHeap.offer(num);
            }
        }
        // check size difference - if difference >1 then, poll from bigger heap and add it to smaller heap
        // this is to maintain the size difference between both heaps to be atmost 1
        if(Math.abs(maxHeap.size()-minHeap.size()) >1){
            if(maxHeap.size()>minHeap.size()){
                minHeap.offer(maxHeap.poll());
            }
            else{
                maxHeap.offer(minHeap.poll());
            }
        }
    }
    
    public double findMedian() {
        // if both heaps are of same size then return average of peeked elements from both heaps, else return peeked element from bigger size heap
        if(maxHeap.size() == minHeap.size()){
            return (double) (maxHeap.peek()+minHeap.peek())/2;
        }
        if(maxHeap.size()>minHeap.size()){
            return (double)maxHeap.peek();
        }
        return (double)minHeap.peek();
    }

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        System.out.println("adding");
        System.out.println(mf.maxHeap);
        System.out.println(mf.minHeap);
        mf.addNum(2);
        System.out.println("adding");
        System.out.println(mf.maxHeap);
        System.out.println(mf.minHeap);
        mf.addNum(3);
        System.out.println("adding");
        System.out.println(mf.maxHeap);
        System.out.println(mf.minHeap);
        mf.addNum(4);
        System.out.println("adding");
        System.out.println(mf.maxHeap);
        System.out.println(mf.minHeap);
        System.out.println(mf.findMedian());
    }
}

/**
 * The median is the middle value in a sorted list of integers. For lists of even length, there is no middle value, so the median is the mean of the two middle values.

For example:

For arr = [1,2,3], the median is 2.
For arr = [1,2], the median is (1 + 2) / 2 = 1.5
Implement the MedianFinder class:

MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far.
Example 1:

Input:
["MedianFinder", "addNum", "1", "findMedian", "addNum", "3" "findMedian", "addNum", "2", "findMedian"]

Output:
[null, null, 1.0, null, 2.0, null, 2.0]

Explanation:
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.findMedian(); // return 1.0
medianFinder.addNum(3);    // arr = [1, 3]
medianFinder.findMedian(); // return 2.0
medianFinder.addNum(2);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0
Constraints:

-100,000 <= num <= 100,000
At most 50,000 calls will be made to addNum and findMedian.
findMedian will only be called after adding at least one integer to the data structure.
 */