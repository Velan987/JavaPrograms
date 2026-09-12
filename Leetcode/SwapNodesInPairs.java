package Leetcode;
public class SwapNodesInPairs {
    public static ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(0, head);
        ListNode tail = dummy;

        while(tail.next !=null && tail.next.next!= null){
            ListNode a = tail.next;
            ListNode b = a.next;
            
            //swap
            a.next = b.next;
            tail.next = b;
            b.next = a;

            // move tail to next pair
            tail = a;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        ListNode swappedHead = swapPairs(head);

        // Print swapped list
        while (swappedHead != null) {
            System.out.print(swappedHead.val + " ");
            swappedHead = swappedHead.next;
        }
    }
}

/**
 * At any step, you have:

prev → a → b → nextPair

After swap:

prev → b → a → nextPair


Step-by-step logic
1. Use dummy node
2. Maintain prev pointer
3. Pick 2 nodes (a, b)
4. Swap them
5. Move forward
 */

/**
 * Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)

 

Example 1:

Input: head = [1,2,3,4]

Output: [2,1,4,3]

Explanation:



Example 2:

Input: head = []

Output: []

Example 3:

Input: head = [1]

Output: [1]

Example 4:

Input: head = [1,2,3]

Output: [2,1,3]
 */