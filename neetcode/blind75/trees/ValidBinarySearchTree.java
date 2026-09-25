package neetcode.blind75.trees;

public class ValidBinarySearchTree {
    /**
     * The cleanest solution is to give every node a valid value range.
    For example:
        10
        /  \
        5    15
    - 5 must be between negative infinity and 10.
    - 15 must be between 10 and positive infinity.
    - Every descendant must also respect all limits inherited from its ancestors.
     */
    public boolean isValidBST(TreeNode root) {
        /**
         * we can not simply check left value is smaller than current and right value is bigger than current node
         * because this will work fine in one layer, but wont work in multiple layers.
         * example root=[0,-1000,1000,null,null,0]
         * in this case first layer root 0, left child -1000 right child 1000 - valid
         * next layer root -1000 left null, right null fine but for root 1000 left child is 0 which is lesser than 1000
         * if we check only current node value(1000) then this is fine but actually it is equal to orignal root value(which is 0)
         * this is not a valid BST, to handle this we can use min and max values
         */
        
        /*
         * The root initially has no meaningful lower or upper restriction.
         *
         * We use long values because node values are integers. This allows Integer.MIN_VALUE and Integer.MAX_VALUE themselves to be valid node values.
         */
        return isValid(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }

    /**
     * Determines whether the tree starting at 'node' is a valid BST.
     *
     * Every value in this subtree must satisfy:
     *
     * lowerBound < node.val < upperBound
     */
    private boolean isValid(TreeNode node,long lowerBound,long upperBound) {
        /*
         * An empty tree is a valid BST.
         *
         * This is also the stopping condition when recursion
         * moves beyond a leaf node.
         */
        if (node == null) {
            return true;
        }

        /*
         * The value must be strictly between its inherited bounds.
         *
         * <= and >= are used because duplicate values are not permitted in a valid BST.
         */
        if (node.val <= lowerBound || node.val >= upperBound) {
            return false;
        }

        /*
         * Validate the left subtree.
         * Every value on the left must be:
         *
         * - greater than the existing lower bound
         * - less than the current node's value
         *
         * Therefore, node.val becomes the new upper bound.
         */
        boolean leftIsValid = isValid(node.left, lowerBound, node.val);

        /*
         * If the left subtree is invalid, there is no need to examine the right subtree.
         */
        if (!leftIsValid) {
            return false;
        }

        /*
         * Validate the right subtree.
         * Every value on the right must be:
         *
         * - greater than the current node's value
         * - less than the existing upper bound
         *
         * Therefore, node.val becomes the new lower bound.
         */
        boolean rightIsValid = isValid(node.right, node.val, upperBound);

        /*
         * The current tree is valid only when both its
         * left and right subtrees are valid.
         */
        return rightIsValid;
    }
}
/**
 * Why checking only immediate children is insufficient
This approach is incorrect:
if (node.left != null &&
        node.left.val >= node.val) {
    return false;
}

if (node.right != null &&
        node.right.val <= node.val) {
    return false;
}
It checks only the direct children.
Consider:
        10
       /  \
      5    15
          /  \
         6    20
The immediate relationships appear valid:
5 < 10
15 > 10
6 < 15
20 > 15
But the tree is not a valid BST.
The node 6 is in the right subtree of 10, so it must be greater than 10:
6 > 10 // false
The range-based approach catches this.
 */

/**
 * Given the root of a binary tree, return true if it is a valid binary search tree, otherwise return false.

A valid binary search tree satisfies the following constraints:

The left subtree of every node contains only nodes with keys less than the node's key.
The right subtree of every node contains only nodes with keys greater than the node's key.
Both the left and right subtrees are also binary search trees.

Example 1:
Input: root = [2,1,3]

Output: true

Example 2:
Input: root = [1,2,3]

Output: false
Explanation: The root node's value is 1 but its left child's value is 2 which is greater than 1.

Constraints:
1 <= The number of nodes in the tree <= 10000.
-1000000000 <= Node.val <= 1000000000
 */