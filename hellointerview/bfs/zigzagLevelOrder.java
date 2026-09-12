package hellointerview.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class zigzagLevelOrder {
    public static List<List<Integer>> zigZag(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> nodes = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            LinkedList<Integer> nodesForLevel = new LinkedList<>();
            // process all nodes at this level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (leftToRight) {
                    // add the node to the back of the list
                    nodesForLevel.addLast(node.val);
                } else {
                    // add the node to the front of the list
                    nodesForLevel.addFirst(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            // we've processed all nodes at the current level
            // add them to the output list and toggle leftToRight
            // to prepare for the next level
            nodes.add(new ArrayList<>(nodesForLevel));
            leftToRight = !leftToRight;
        }
        return nodes;
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

        System.out.println(zigZag(root));
    }
}

/**
 * Given the root of a binary tree, return the zigzag level-order traversal of its nodes' values.

The output should be a list of lists containing the values of the nodes at each level. The first list should contain the value of the root, 
the second list should contain the values of the nodes at the second level from right to left, 
the third list should contain the values of the third level from left to right, and so on.

Example 1:

Input:

[1, 3, 4, null, 2, 7, null, 8]

[1]
[4, 3]
[2, 7]
[8]
Output: [[1], [4, 3], [2, 7], [8]]
 */