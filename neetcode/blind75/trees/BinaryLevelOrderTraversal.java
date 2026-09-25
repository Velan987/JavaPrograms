package neetcode.blind75.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        // Every sublist will contain all from every layer
        List<List<Integer>> res = new ArrayList<>();

        if(root == null)
            return res;

        queue.offer(root);

        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> tmp = new ArrayList<>();
            for(int i=0; i<size; i++){
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            res.add(tmp);
        }

        return res;
    }
}

/**
 * Given a binary tree root, return the level order traversal of it as a nested list, where each sublist contains the values of nodes at a particular level in the tree, from left to right.

Example 1:
Input: root = [1,2,3,4,5,6,7]

Output: [[1],[2,3],[4,5,6,7]]

Example 2:
Input: root = [1]

Output: [[1]]

Example 3:
Input: root = []

Output: []

Constraints:

0 <= The number of nodes in the tree <= 2000.
-1000 <= Node.val <= 1000
 */