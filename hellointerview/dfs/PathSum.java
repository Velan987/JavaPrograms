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

public class PathSum {

    public static boolean pathSum(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
       
        // if we reach a leaf node, check if the target is equal to the leaf node's value
        if (root.left == null && root.right == null) {
            return target == root.val;
        }
        target -= root.val;
        
        // check if there's a path from the current node to a leaf that sums to target
        return pathSum(root.left, target) || pathSum(root.right, target);
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

        System.out.println(pathSum(root, 17));
    }
}

/**
 * Given the root of a binary tree and an integer target, 
 * write a recursive function to determine if the tree has a root-to-leaf path where all the values along that path sum to the target.

Example 1:

4
2
1
3
7
6
9
Input:

[4, 2, 7, 1, 3, 6, 9]
target = 17 
Output: true (the path is 4 -> 7 -> 6)
 */