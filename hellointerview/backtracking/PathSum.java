package hellointerview.backtracking;

import java.util.ArrayList;
import java.util.List;

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
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(root, new ArrayList<>(), 0, target, result);
        return result;
    }

    private void backtrack(TreeNode node, List<Integer> path, int total, int target, List<List<Integer>> result) {
        if (node == null) {
            return;
        }

        path.add(node.val);
        total += node.val;
        // KEY STEP 2
        // current sum exceeds target
        // so pop to remove the current node from the path
        // return to backtrack to previous node on the call stack
        if (total > target) {
            path.remove(path.size() - 1);
            return;
        }

        if (node.left == null && node.right == null) {
            // add the path to the result
            // note we have to make a copy (new ArrayList<>(path)) of the path
            // since future recursive calls modify path
            if (total == target) {
                result.add(new ArrayList<>(path));
            }
        } else {
            backtrack(node.left, path, total, target, result);
            backtrack(node.right, path, total, target, result);
        }
        // KEY STEP 1
        // we have finished exploring all paths containing the current node
        // so pop to remove the current node from the path
        // return to backtrack to previous node on the call stack.
        path.remove(path.size() - 1);
    }
}
