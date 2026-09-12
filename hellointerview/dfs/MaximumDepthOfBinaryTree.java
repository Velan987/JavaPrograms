package hellointerview.dfs;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class MaximumDepthOfBinaryTree {

    public static int maximumDepth(TreeNode root) {

        /*
         * Base case:
         *
         * An empty tree has a depth of 0.
         * This also stops the recursive calls when we move
         * beyond a leaf node.
         */
        if (root == null) {
            return 0;
        }

        // Recursively find the depth of the left subtree.
        int leftDepth = maximumDepth(root.left);

        // Recursively find the depth of the right subtree.
        int rightDepth = maximumDepth(root.right);

        /*
         * Choose the deeper subtree and add 1 for
         * the current node.
         * when it reaches the leaf node, left and right child depth will return 0 and we are adding 1
         * leftDepth and rightDepth variables are local variables that will contain different values for each function call in the call stack
         */
        return 1 + Math.max(leftDepth, rightDepth);
    }

    public static void main(String[] args) {

        // Construct the example tree.
        TreeNode root = new TreeNode(4);

        root.left = new TreeNode(2);
        root.right = new TreeNode(7);

        root.left.left = new TreeNode(1);
        root.left.left.right = new TreeNode(8);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        int result = maximumDepth(root);

        System.out.println("Maximum depth: " + result);
    }
}

/**
 * DESCRIPTION (inspired by Leetcode.com)
 * Given the root of a binary tree, write a recursive function to find its
 * maximum depth,
 * where maximum depth is defined as the number of nodes along the longest path
 * from the root node down to a leaf node.
 * 
 * Example 1:
 * 
 * 4
 * 2
 * 1
 * 8
 * 7
 * 6
 * 9
 * Input:
 * 
 * [4, 2, 7, 1, null, 6, 9, null, 8, null, null, null, null, null, null]
 */