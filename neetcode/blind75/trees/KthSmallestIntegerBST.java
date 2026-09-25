package neetcode.blind75.trees;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KthSmallestIntegerBST {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        dfs(root, list, visited);
        return list.get(k-1);
    }
    /**
     * will do dfs and add every node into the list
     * since it is a BST and we are doing inorder traversal the values will already be in sorted order
     * without visited set also it will work fine
     */
    private void dfs(TreeNode root, List<Integer> list, Set<Integer> visited){
        if(root ==null || visited.contains(root.val))
            return;

        dfs(root.left, list, visited);
        visited.add(root.val);
        list.add(root.val);
        dfs(root.right, list, visited);
    }

    public int kthSmallestV1(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return list.get(k-1);
    }
    /**
     * will do dfs and add every node into the list
     * since it is a BST it is already sorted if we do dfds
     */
    // this is an inorder traversal, if we do preorder traversal then we need to sort the list before returning the value
    private void dfs(TreeNode root, List<Integer> list){
        if(root ==null)
            return;

        dfs(root.left, list);
        list.add(root.val);
        dfs(root.right, list);
    }
}

/**
 * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) in the tree.

A binary search tree satisfies the following constraints:

The left subtree of every node contains only nodes with keys less than the node's key.
The right subtree of every node contains only nodes with keys greater than the node's key.
Both the left and right subtrees are also binary search trees.

Example 1:



Input: root = [2,1,3], k = 1

Output: 1

Example 2:



Input: root = [4,3,5,2,null], k = 4

Output: 5

Constraints:

1 <= k <= The number of nodes in the tree <= 10,000.
0 <= Node.val <= 10,000
 */
