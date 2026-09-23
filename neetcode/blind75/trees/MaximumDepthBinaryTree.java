package neetcode.blind75.trees;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumDepthBinaryTree {
    /**
     * Queue is the correct datastructure for this
     * if we use stack, while popping from stack it will remove newly added node, that will cause incorrect depth
     * every while loop iteration we are removing/visiting all nodes in one layer.
     */
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        int depth = 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int nodesToPop = queue.size();
            depth ++;
            for(int i=0; i< nodesToPop; i++){
                TreeNode node = queue.poll();
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null)
                    queue.offer(node.right);
            }
        }

        return depth;
    }
}

/**
 * Given the root of a binary tree, return its depth.

The depth of a binary tree is defined as the number of nodes along the longest path from the root node down to the farthest leaf node.

Example 1:
Input: root = [1,2,3,null,null,4]

Output: 3

Example 2:
Input: root = []

Output: 0

Constraints:
0 <= The number of nodes in the tree <= 100.
-100 <= Node.val <= 100
 */