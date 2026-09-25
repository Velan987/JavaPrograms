package neetcode.blind75.trees;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTree {
    /**
     * Preorder traversal: root -> left subtree -> right subtree
     * Inorder traversal: left subtree -> root -> right subtree, therefore
     *  The next unused value in the preorder is the root of the current subtree
     *  Find that value in inorder
     *  Everything to its left belongs to left subtree
     *  Everything to its right belogs to right subtree
     *  Recursively construct the left subtree before right
     * 
     * Preorder tells us which node to create next.
     * Inorder tells us whether the remaining nodes belong to its left or right subtree.
     * Preorder traversal follows:
        Root → Left subtree → Right subtree
        Therefore, the next unused value in preorder is always the root of the current subtree
        For example:
        preorder = [1, 2, 3, 4]
                    ↑
            next node to create
        We create:
        TreeNode root = new TreeNode(1);
        Then move the preorder pointer forward:
        preorderPosition++;
        Later, the next unused value is 2, so 2 is the next node created.
        Preorder answers:
        Which value should become the current root?

        Role of inorder
        Inorder traversal follows:
        Left subtree → Root → Right subtree
        After preorder tells us that the root is 1, find 1 in inorder:
        inorder = [2, 1, 3, 4]
                    ↑
                    root
        Everything to the left of 1 belongs to its left subtree:
        [2]
        Everything to the right belongs to its right subtree:
        [3, 4]
        Inorder answers:
        Which values belong on the left, and which values belong on the right?
     */

    // It points to the next unused element in preorder
    private int preorderPosition;
    private Map<Integer, Integer> indexMap;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // if same constructBinaryTree object is used multiple times to invoke buildTree, then resetting this value is mandatory
        preorderPosition = 0;
        indexMap = new HashMap<>();

        for(int i=0; i< inorder.length; i++){
            indexMap.put(inorder[i], i);
        }

        return construct(preorder, 0, inorder.length-1);

    }

    private TreeNode construct(int[] preorder, int inorderLeft, int inorderRight){
        // if inorder range is empty, then there is no element to construct, so this child is empty
        if(inorderLeft > inorderRight)
            return null;

        // The next unused preorder value is the root of the current subtree
        int rootValue = preorder[preorderPosition];

        // Increment preorderPosition
        preorderPosition ++;

        TreeNode root = new TreeNode(rootValue);

        /**
         * we need to find the index of root in inorder array to get left and right subtrees
         * this index we already constructed in indexMap
         * Everything before this position, inside the current bounds belongs to left subtree
         * Everything after this position belongs to right subtree
         */
        int rootInorderPosition = indexMap.get(root.val);

        // construct left subtree first
        root.left = construct(preorder, inorderLeft, rootInorderPosition-1);

        root.right = construct(preorder, rootInorderPosition+1, inorderRight);

        return root;
    }
}
/**
 * Consider:
preorder = [1, 2, 3, 4]
inorder  = [2, 1, 3, 4]
First root
The first preorder value is always the overall root:
preorder = [1, 2, 3, 4]
            ↑
           root
So:
root = 1
Find 1 in inorder:
inorder = [2, 1, 3, 4]
              ↑
             root
This divides inorder into:
Left subtree values:  [2]
Root:                  [1]
Right subtree values: [3, 4]
The tree currently looks like:
        1
       / \
     [2] [3,4]
The bracketed portions are still to be constructed.
Construct the left subtree
The next unused preorder value is:
2
The permitted inorder range is:
[2]
So 2 becomes the left child:
        1
       /
      2
There are no values to the left or right of 2 in its inorder range, so both children are null.
Construct the right subtree
After finishing the left subtree, the next unused preorder value is:
3
The right-side inorder range is:
[3, 4]
So 3 becomes the right child of 1:
        1
       / \
      2   3
Find 3 in the current inorder range:
[3, 4]
 ↑
root
Its left side is empty, while its right side contains:
[4]
The next preorder value is 4, so 4 becomes the right child of 3:
        1
       / \
      2   3
           \
            4
Level-order representation:
[1, 2, 3, null, null, null, 4]


Meaning of the recursive boundaries
The recursive method receives:
int inorderLeft,
int inorderRight
These values define which section of the inorder array belongs to the current subtree.
For example:
inorder = [2, 1, 3, 4]
           0  1  2  3
The initial call uses:
inorderLeft  = 0
inorderRight = 3
After selecting 1 at position 1:
Left subtree boundaries
0 through 0
Code:
construct(preorder, 0, 1 - 1);
This represents:
[2]
Right subtree boundaries
2 through 3
Code:
construct(preorder, 1 + 1, 3);
This represents:
[3, 4]
We do not create new subarrays. We reuse the original inorder array by passing index boundaries.

Understanding the base case
if (inorderLeft > inorderRight) {
    return null;
}
Suppose the current root is at inorder position 0, and we try to construct its left subtree:
left boundary  = 0
right boundary = root position - 1
               = -1
The range becomes:
0 through -1
This is empty because:
0 > -1
Therefore, the node has no left child:
return null;
 */

/**
 * You are given two integer arrays preorder and inorder.

preorder is the preorder traversal of a binary tree
inorder is the inorder traversal of the same tree
Both arrays are of the same size and consist of unique values.
Rebuild the binary tree from the preorder and inorder traversals and return its root.

Example 1:
Input: preorder = [1,2,3,4], inorder = [2,1,3,4]

Output: [1,2,3,null,null,null,4]

Example 2:
Input: preorder = [1], inorder = [1]

Output: [1]

Constraints:
1 <= inorder.length <= 2001.
inorder.length == preorder.length
-1000 <= preorder[i], inorder[i] <= 1000

 */