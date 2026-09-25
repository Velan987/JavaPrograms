package neetcode.blind75.trees;

public class BinaryTreeMaximumPathSum {
    /**
     *     -15
         /   \
       10     20
             /  \
            15   5
           /
         -5

     * Conditions
     *  Path does not necessarily include the root. so pathsum can be the sum of subtree also
     *  can not use same node twice - in this example 15-20-5 can be one path but 15-20-5-(-15) is not a valid path
     * this gives us 2 options
     *  1. we can use whole sub tree alone without root (because same node cannot use twice)
     *  2. we can take max of subtree and use current node ( leftMax + root + rightMax) 
     * 
     * This process we need to do for whole subtree (subtree can be single node, or root -> left or root->left->right or root->right. here every left, right can contain childs)
     */
    // nodes can have negative values
    int pathSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        // Calculates maximum downward contribution from root, during this pathSum is updated for every node
        dfs(root);
        return pathSum;
    }


    private int dfs(TreeNode root){
        // Base case
        if(root == null){
            return 0;
        }
        int leftMax = dfs(root.left);
        int rightMax = dfs(root.right);
        // if leftMax is negative then adding it into root will give lesser value only, even if root value is negative
        leftMax = Math.max(leftMax, 0);
        rightMax = Math.max(rightMax, 0);

        // calculate subtree sum
        // for leaf nodes leftMax and rightMax will be 0 (base case will handle this), so leafnode sum will be leafnode value
        int subTreeSum = leftMax + root.val + rightMax;
        // if subtree itself gives max sum
        pathSum = Math.max(pathSum, subTreeSum);

        // now second option, (root + max(left, right)) this way we can use parent node (like left->root->rootOfRoot)
        // return a path that its parent can extend, we should return best path, thats why taking max
        return root.val + Math.max(leftMax, rightMax);

    }
}

/**
 * Even if the tree contains only negative values, this will work.
 * The important part is that 0 is used only to reject a negative child path—it does not replace the current node itself
 * leftMax = Math.max(leftMax, 0);
rightMax = Math.max(rightMax, 0);

int pathThroughCurrentNode =
        leftMax + root.val + rightMax;
Consider:
       -10
       /  \
     -20  -3
At -20:
leftMax = 0;
rightMax = 0;
pathThroughCurrentNode = 0 + (-20) + 0 = -20;
pathSum = -20;
At -3:
pathThroughCurrentNode = 0 + (-3) + 0 = -3;
pathSum = max(-20, -3) = -3;
At -10, both child results are negative, so they are ignored:
leftMax = max(-20, 0) = 0;
rightMax = max(-3, 0) = 0;

pathThroughCurrentNode = 0 + (-10) + 0 = -10;
pathSum = max(-3, -10) = -3;
Final answer: -3, representing the single-node path containing only -3.
This works because pathSum starts at:
pathSum = Integer.MIN_VALUE;
If it started at 0, an all-negative tree would incorrectly return 0, even though a valid path must contain at least one node.
So these two decisions work together:
// Negative child paths are optional, so ignore them.
leftMax = Math.max(leftMax, 0);
rightMax = Math.max(rightMax, 0);

// The current node is mandatory, even when its value is negative.
int pathThroughCurrentNode =
        root.val + leftMax + rightMax;
 */

/**
 * Given the root of a non-empty binary tree, return the maximum path sum of any non-empty path.

A path in a binary tree is a sequence of nodes where each pair of adjacent nodes has an edge connecting them. 
A node can not appear in the sequence more than once. The path does not necessarily need to include the root.

The path sum of a path is the sum of the node's values in the path.

Example 1:
Input: root = [1,2,3]

Output: 6
Explanation: The path is 2 -> 1 -> 3 with a sum of 2 + 1 + 3 = 6.

Example 2:
Input: root = [-15,10,20,null,null,15,5,-5]

Output: 40
Explanation: The path is 15 -> 20 -> 5 with a sum of 15 + 20 + 5 = 40.

Constraints:
1 <= The number of nodes in the tree <= 30000.
-1000 <= Node.val <= 1000

 */