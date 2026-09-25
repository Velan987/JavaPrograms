package neetcode.blind75.trees;

import java.util.HashMap;
import java.util.Map;

/**
 * if binary tree values are unique then this approach will work, because we are using hashMap and node value as the key
 */
public class SerializeDeserializeBinaryTreeUnique {
    /**
     * Tree will be passed to serialize() which will return one string and that string will be passed to deserialize(str) and deserialize should return actual tree
     * 
     * We can construct a binary tree using preorder and inorder lists. (ref: ConstructBinaryTree.java)
     * 
     * preorder -> root-left-right
     * inorder -> left-root-right
     * first element from preorder array will be the root element
     * take root element index in inorder as mid and all elements left to mid is left subtree of root and all elements right to mid is right subtree of root
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null)
            return "";
        StringBuilder preorderStr = new StringBuilder();
        StringBuilder inorderStr = new StringBuilder();
        traverse(root, preorderStr, inorderStr);
        // Remove trailing comma
        preorderStr.setLength(preorderStr.length()-1);
        inorderStr.setLength(inorderStr.length()-1);
        System.out.println(preorderStr.toString()+":"+inorderStr.toString());
        return preorderStr.toString()+":"+inorderStr.toString();
    }
    private void traverse(TreeNode node, StringBuilder preorderStr, StringBuilder inorderStr){
        if(node == null)
            return;

        preorderStr.append(node.val).append(",");
        traverse(node.left, preorderStr, inorderStr);
        inorderStr.append(node.val).append(",");
        traverse(node.right, preorderStr, inorderStr);
    }

    // Decodes your encoded data to tree.
    int preorderIdx = 0;
    public TreeNode deserialize(String data) {
        if(data.equals(""))
            return null;
        String[] input = data.split(":");
        String []preorderStr = input[0].split(",");
        String []inorderStr = input[1].split(",");

        // Create integer array
        int [] preorder = new int[preorderStr.length];

        // Map to store index
        Map<Integer, Integer> indexMap = new HashMap<>();

        for(int i=0; i<preorderStr.length; i++){
            preorder[i] = Integer.parseInt(preorderStr[i]);
        }

        for(int i=0; i<inorderStr.length; i++){
            indexMap.put(Integer.valueOf(inorderStr[i]), i);
        }
        System.out.println(indexMap);
        /**
         * first element from preorder array will be the root element
         * take root element index in inorder as mid and all elements left to mid is left subtree of root and all elements right to mid is right subtree of root
         */
        return construct(preorder, indexMap, 0, preorder.length-1);

    }
    private TreeNode construct(int[] preorder, Map<Integer, Integer> indexMap, int leftBoundry, int rightBoundry){
        if(leftBoundry > rightBoundry)
            return null;

        TreeNode node = new TreeNode(preorder[preorderIdx]);
        System.out.println(node.val);
        preorderIdx++;

        // indexmap will have every elements index of inorder traversal
        int mid = indexMap.get(node.val);

        // all elments left to mid is left subtree, and right is right subtree - inorder traversal
        node.left = construct(preorder, indexMap, leftBoundry, mid-1);

        node.right = construct(preorder, indexMap, mid+1, rightBoundry);

        return node;

        
    }
}

/**
 * Implement an algorithm to serialize and deserialize a binary tree.

Serialization is the process of converting an in-memory structure into a sequence of bits so that it can be stored or sent across a network to be reconstructed later in another computer environment.

You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure. 
There is no additional restriction on how your serialization/deserialization algorithm should work.

Note: The input/output format in the examples is the same as how NeetCode serializes a binary tree. You do not necessarily need to follow this format.

Example 1:



Input: root = [1,2,3,null,null,4,5]

Output: [1,2,3,null,null,4,5]


Example 2:
Input: root = []

Output: []

Constraints:
0 <= The number of nodes in the tree <= 10,000.
-1000 <= Node.val <= 1000
 */
