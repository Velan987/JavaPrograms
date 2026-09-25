package neetcode.blind75.linkedlist;

public class ReverseLinkedList {
    /**
     * Reversing a linked list iteratively is all about flipping pointers one step at a time.
    We walk through the list from left to right, and for each node, we redirect its next pointer to point to the node behind it.

    To avoid losing track of the rest of the list, we keep three pointers:

    curr → the current node we are processing
    prev → the node that should come after curr once reversed
    temp → the original next node (so we don't break the chain)
    By moving these pointers forward in each step, we gradually reverse the entire list.
    When curr becomes null, the list is fully reversed, and prev points to the new head.
     */
    public ListNode reverseList(ListNode head) {
        // Initially there will be no previous node
        ListNode prev = null;
        ListNode curr = head;

        while(curr != null){
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    }
}

/**
 * Given the beginning of a singly linked list head, reverse the list, and return the new beginning of the list.

Example 1:

Input: head = [0,1,2,3]

Output: [3,2,1,0]
Example 2:

Input: head = []

Output: []
Constraints:

0 <= The length of the list <= 1000.
-1000 <= Node.val <= 1000
 */