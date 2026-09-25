package neetcode.blind75.trees;

import java.util.*;

/**
 * with preorder traversal alone we can serialize and deserialize a binary tree
 * null values will be preserved to record tree structure
 * 
 * if null value is not preserved then we need inorder traversal to detect the tree structure and preorder to define the node value
 */
public class SerializeDeserializeBinaryTree {

    private static final String NULL = "#";
    private static final String SEPARATOR = ",";

    // Encodes the tree into a string.
    public String serialize(TreeNode root) {
        StringBuilder result = new StringBuilder();
        serializePreorder(root, result);
        return result.toString();
    }

    private void serializePreorder(TreeNode node, StringBuilder result) {
        if (node == null) {
            // Record null so that the tree's structure is preserved.
            result.append(NULL).append(SEPARATOR);
            return;
        }

        // Preorder: current node, left child, right child
        result.append(node.val).append(SEPARATOR);

        serializePreorder(node.left, result);
        serializePreorder(node.right, result);
    }

    // Decodes the string back into a tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }

        Queue<String> values = new ArrayDeque<>(Arrays.asList(data.split(SEPARATOR)));

        return deserializePreorder(values);
    }

    private TreeNode deserializePreorder(Queue<String> values) {
        String value = values.poll();

        // A null marker means there is no node in this position.
        if (value.equals(NULL)) {
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(value));

        // The next section represents the left subtree.
        node.left = deserializePreorder(values);

        // The section after that represents the right subtree.
        node.right = deserializePreorder(values);

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
