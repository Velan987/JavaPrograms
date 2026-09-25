package neetcode.blind75.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class ReorderLinkedList {
    /**
     * this is a bruteforce approach - not optimized
     * we are using additional memory
     */
    public void reorderList(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode tmp = head;
        while(tmp != null){
            list.add(tmp);
        }

        int end=list.size()-1;
        tmp = new ListNode();
        for(int i=0; i< list.size()/2; i++){
            tmp.next = list.get(i);
            tmp = tmp.next;
            tmp.next = list.get(end-i);
            tmp = tmp.next;
        }
    }

    /**
     * Idea is reverse the second half, then merge first half with second half one by one
     * mid can be found using slow and fast pointer, when fast pointer reaches end slow will be in mid position
     */
    public void reorderListV1(ListNode head) {
        // Find mid
        // [0,1,2,3,4,5]
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast!=null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        // after this loop slow will be 2
       
        ListNode secondHalfHead = slow.next;
        // so that list will be splitted into 2
        slow.next = null;

        // Reverse second half (ReverseLinikedList.java), prev will be the head of reversed list
        ListNode prev = null;
        ListNode cur = secondHalfHead;
        while(cur != null){
            ListNode nextNode = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nextNode;
        }
        printNode(prev);

        ListNode firstHalfNode = head;
        ListNode secondHalfNode = prev;
        
        while(secondHalfNode != null){
            ListNode tmp1 = firstHalfNode.next;
            ListNode tmp2 = secondHalfNode.next;

            /**
             * first half [0,1,2], secondhalf [5,4,3]
             * below will do 0->5->1
             * 
             * in next iteration first halfnode will be 1 and secondhalf node will be 4
             */
            firstHalfNode.next = secondHalfNode;
            secondHalfNode.next = tmp1;

            // Move first half and second half nodes to next pointer
            firstHalfNode = tmp1;
            secondHalfNode = tmp2;
        }
    }

    public void printNode(ListNode head){
        while(head !=null){
            System.out.print(head.val+"");
            head = head.next;
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode tmp = head;

        tmp.next = new ListNode(1);
        tmp = tmp.next;
        tmp.next = new ListNode(2);
        tmp = tmp.next;
        tmp.next = new ListNode(3);
        tmp = tmp.next;
        tmp.next = new ListNode(4);
        tmp = tmp.next;
        tmp.next = new ListNode(5);
        
        ReorderLinkedList rll = new ReorderLinkedList();
        rll.reorderListV1(head);
        rll.printNode(head);
    }
}

/**
 * You are given the head of a singly linked-list.

The positions of a linked list of length = 7 for example, can intially be represented as:

[0, 1, 2, 3, 4, 5, 6]

Reorder the nodes of the linked list to be in the following order:

[0, 6, 1, 5, 2, 4, 3]

In the general case, label the nodes by their original zero-based positions from 0 to n - 1. After reordering, those original positions appear in this order:

[0, n-1, 1, n-2, 2, n-3, ...]

These numbers represent node positions, not the values stored in the nodes.

You may not modify the values in the list's nodes, but instead you must reorder the nodes themselves.


Example 1:

Input: head = [2,4,6,8]

Output: [2,8,4,6]

Example 2:

Input: head = [2,4,6,8,10]

Output: [2,10,4,8,6]

Constraints:

1 <= Length of the list <= 1000.
1 <= Node.val <= 1000

 */
