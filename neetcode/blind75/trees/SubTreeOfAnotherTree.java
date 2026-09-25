package neetcode.blind75.trees;

import java.util.LinkedList;
import java.util.Queue;

public class SubTreeOfAnotherTree {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            root = queue.poll();
            // for every matching node we need to check the subtree, becuase trees can have duplicate node values
            if(root.val == subRoot.val && sameTree(root, subRoot))
                return true;

            if(root.left != null)
                queue.offer(root.left);
            if(root.right != null)
                queue.offer(root.right);
        }

        return false;
    }
    public boolean sameTree(TreeNode root, TreeNode subRoot){
        Queue<TreeNode> rQueue = new LinkedList<>();
        Queue<TreeNode> sQueue = new LinkedList<>();
        rQueue.offer(root);
        sQueue.offer(subRoot);
        while(!rQueue.isEmpty() && !sQueue.isEmpty()){
            TreeNode rNode = rQueue.poll();
            TreeNode sNode = sQueue.poll();
            if(rNode == null && sNode == null)
                continue;
            if(rNode == null || sNode == null)
                return false;
            if(rNode.val != sNode.val)
                return false;

            rQueue.offer(rNode.left);
            rQueue.offer(rNode.right);

            sQueue.offer(sNode.left);
            sQueue.offer(sNode.right);
        }

        
        return true;
    }
}

/**
 * Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.

A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. The tree tree could also be considered as a subtree of itself.


Example 1:
Input: root = [1,2,3,4,5], subRoot = [2,4,5]

Output: true

Example 2:
Input: root = [1,2,3,4,5,null,null,6], subRoot = [2,4,5]

Output: false


Constraints:
The number of nodes in the root tree is in the range [1, 2000].
The number of nodes in the subRoot tree is in the range [1, 1000].
-10^4 <= root.val <= 10^4
-10^4 <= subRoot.val <= 10^4
 */
