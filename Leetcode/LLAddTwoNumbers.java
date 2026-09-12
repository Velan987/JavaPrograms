package Leetcode;
public class LLAddTwoNumbers {
    ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
    ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
    Solution solution = new Solution();
    public static void main(String[] args) {
        LLAddTwoNumbers llAddTwoNumbers = new LLAddTwoNumbers();
        ListNode result = llAddTwoNumbers.solution.addTwoNumbers(llAddTwoNumbers.l1, llAddTwoNumbers.l2);
        printList(result);
    }

    private static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { 
        this.val = val; 
        this.next = next; 
    }
}
 
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode current = null;

        int carry = 0;
        /**
         * Example:

            342
            + 465
            -----
            807

            But lists are:

            2 → 4 → 3
            5 → 6 → 4
            so we
         * Add digits
            Keep carry
            Create new node for result
            Move to next nodes
            Continue until both lists and carry are done
         */
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            carry = sum / 10;
            ListNode node = new ListNode(sum % 10);
            if(head == null){
                head = node;
                current = node;
            }else{
                current.next = node;
                current = current.next;
            }
        }

        return head;
    }
}

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 

Example 1:


Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.
Example 2:

Input: l1 = [0], l2 = [0]
Output: [0]
Example 3:

Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]
 

Constraints:

The number of nodes in each linked list is in the range [1, 100].
0 <= Node.val <= 9
It is guaranteed that the list represents a number that does not have leading zeros.
 */