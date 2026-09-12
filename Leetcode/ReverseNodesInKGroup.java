package Leetcode;
public class ReverseNodesInKGroup {
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0,head);
        ListNode tail = dummy;

        while(tail.next != null){
            ListNode a = tail.next;
            ListNode b = a;
            //move b to end of sublist
            for(int i=0; i<k-1; i++){
                if(b.next == null){
                    return dummy.next;
                }
                b = b.next;
            }
            
            // a - startNode of sublist, b - endNode of sublist
            // basically reversing the sublist
            for(int i=0;i<k-1;i++){
                ListNode tmp = b.next;
                ListNode tmp2 = a.next;
                b.next = a;
                a.next = tmp;
                a = tmp2;
            }
            // linking reversed sublist 
            tail.next = b;
            // Moving tailNode to end of sublist - tail nodes next node will be the start node of next sublist
            for(int i=0;i<k;i++){
                tail = tail.next;
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        int k = 2;

        ListNode result = reverseKGroup(head, k);

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}


/**
 * Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.

You may not alter the values in the list's nodes, only nodes themselves may be changed.

 

Example 1:


Input: head = [1,2,3,4,5], k = 2
Output: [2,1,4,3,5]
Example 2:


Input: head = [1,2,3,4,5], k = 3
Output: [3,2,1,4,5]
 

Constraints:

The number of nodes in the list is n.
1 <= k <= n <= 5000
0 <= Node.val <= 1000

 */