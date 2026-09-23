package neetcode.blind75.trees;

import java.util.LinkedList;
import java.util.Queue;

public class SameBinaryTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> pQueue = new LinkedList<>();
        pQueue.offer(p);

        Queue<TreeNode> qQueue = new LinkedList<>();
        qQueue.offer(q);

        while(!pQueue.isEmpty()){
            TreeNode pNode = pQueue.poll();
            TreeNode qNode = qQueue.poll();
            if(pNode.val != qNode.val)
                return false;
            if((pNode.left == null && qNode.left!=null) || (pNode.left != null && qNode.left ==null) ||
                (pNode.right == null && qNode.right != null) || (pNode.right != null && qNode.right == null)){
                return false;
            }
            if(pNode.left != null)
                pQueue.offer(pNode.left);
            if(pNode.right != null){
                pQueue.offer(pNode.right);
            }

            if(qNode.left != null)
                qQueue.offer(qNode.left);
            if(qNode.right != null)
                qQueue.offer(qNode.right);
        }
        return pQueue.isEmpty() && qQueue.isEmpty();
    }
    public boolean isSameTreeV1(TreeNode p, TreeNode q) {
        Queue<TreeNode> pQueue = new LinkedList<>();
        pQueue.offer(p);

        Queue<TreeNode> qQueue = new LinkedList<>();
        qQueue.offer(q);

        while(!pQueue.isEmpty()){
            TreeNode pNode = pQueue.poll();
            TreeNode qNode = qQueue.poll();
            // check null condition first
            if(pNode == null && qNode == null)
                continue;
            if(pNode == null || qNode == null)
                return false;
            if(pNode.val != qNode.val)
                return false;

            pQueue.offer(pNode.left);
            pQueue.offer(pNode.right);

            qQueue.offer(qNode.left);
            qQueue.offer(qNode.right);
        }
        return true;
    }
}

/**
 * Given the roots of two binary trees p and q, return true if the trees are equivalent, otherwise return false.

Two binary trees are considered equivalent if they share the exact same structure and the nodes have the same values.

Example 1:
Input: p = [1,2,3], q = [1,2,3]

Output: true

Example 2:
Input: p = [4,7], q = [4,null,7]

Output: false

Example 3:
Input: p = [1,2,3], q = [1,3,2]

Output: false

Constraints:
0 <= The number of nodes in both trees <= 100.
-100 <= Node.val <= 100
 */