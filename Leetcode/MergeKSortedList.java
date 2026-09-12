package Leetcode;
import java.util.PriorityQueue;

public class MergeKSortedList {
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        // elements will be sorted by default
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
            //sorting logic - if 0 same if negative then smaller else bigger
            (a, b) -> a.val - b.val
        );

        // add first node of each list - nodes will be sorted based on above logic
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node); 
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        // queue already has head elements of all lists
        // queue is sorted so first element will be smaller
        while (!pq.isEmpty()) {
            // poll will return first element and removes if empty returns null, but remove will through error if queue is empty
            ListNode minNode = pq.poll();
            tail.next = minNode;
            tail = tail.next;

            // if minNode is from l1 and l1 has some more elements then we need add next element to queue
            if (minNode.next != null) {
                pq.offer(minNode.next);
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);

        ListNode[] lists = {l1, l2, l3};
        ListNode mergedHead = mergeKLists(lists);

        // Print merged list
        while (mergedHead != null) {
            System.out.print(mergedHead.val + " ");
            mergedHead = mergedHead.next;
        }
    }
}

/**
 * for merge k sorted list we can use min heap - priority queue
 * 1. Put first node of each list into MinHeap
    2. Extract smallest node
    3. Add its next node to heap
    4. Repeat until heap is empty
 */

/**
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.

 

Example 1:

Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted linked list:
1->1->2->3->4->4->5->6
Example 2:

Input: lists = []
Output: []
Example 3:

Input: lists = [[]]
Output: []
 

Constraints:

k == lists.length
0 <= k <= 104
0 <= lists[i].length <= 500
-104 <= lists[i][j] <= 104
lists[i] is sorted in ascending order.
The sum of lists[i].length will not exceed 104.
 */