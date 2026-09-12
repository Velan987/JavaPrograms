package Leetcode;
public class RemoveNthNodeFromEndOfList {
    /**
     * 
     * @param head
     * @param n
     * @return
     * we will have 2 pointers, fast and slow, we will move fast pointer to n position, then move both the pointers, 
     * at the time of fast reaches last node slow will be in nth node position, then we will remove nth node
     * the dummy node removes edge-case complexity, especially when the node to delete is the head.
     * (basically we need a real node before every node, so that we can delete any node by just changing the next pointer of the previous node)
     * Problem Without Dummy

Consider:
head = [1,2], n = 2
We must delete 1 (the head).
❌ Without Dummy
Your list looks like:
1 -> 2
↑
head

After using two pointers, you’ll end up with:
slow = 1  (node to delete)

Now what?
You can’t do:
slow.next = slow.next.next;
Because:
slow is the node to delete, not the node before it ❌
So you need a special case:
if (deleting head) {
    return head.next;
}

👉 This creates branching logic (extra condition).
✅ With Dummy Node
We add a fake node before head:
dummy -> 1 -> 2
Now:
Every real node has a previous node
Even the head has a previous node (dummy)
🧠 What Changes?
Now when we run the same logic:
slow = dummy
slow.next = 1 (node to delete)

Deletion becomes:
slow.next = slow.next.next;

Which works perfectly:

dummy -> 2
Return:
dummy.next
🔥 Key Insight
Without dummy:
Sometimes you delete head → special case needed ❌

With dummy:
You ALWAYS delete "slow.next" → no special case ✅
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        //fast and slow pointers are one node prior to head node, so that if list size is too small that also will be handled
        ListNode slow = dummy;
        ListNode fast = dummy;
        //Move fast pointer to n position
        //then move both the pointers, at the time of fast reaches last node slow will be in nth node position
        for(int i=0;i<n;i++){
            fast = fast.next;
        }
        while(fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }
        //remove nth node
        slow.next=slow.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        int n = 2;
        ListNode result = removeNthFromEnd(head, n);
        while(result != null){
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}


/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.

 

Example 1:


Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
Example 2:

Input: head = [1], n = 1
Output: []
Example 3:

Input: head = [1,2], n = 1
Output: [1]
 

Constraints:

The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz
 

Follow up: Could you do this in one pass?
 */