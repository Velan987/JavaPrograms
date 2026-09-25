package neetcode.blind75.trees;

public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        /**
         * Given is an binary search tree, so left child is smaller than root and right child is bigger than root
         * If both nodes(p,q) values are smaller than current node -> both must lie in left subtree
         * If both node values are bigger than current node -> both must lie in right subtree
         * else, current node is the split point for both nodes (i.e one vale is smaller than current and one is bigger than current, then obviously current is the split point)
         */
        TreeNode cur = root;
        while(cur != null){
            // both nodes are smaller than current then both lies in left subtree
            if(p.val < cur.val && q.val < cur.val){
                cur = cur.left;
            }else if(p.val > cur.val && q.val > cur.val){
                cur = cur.right;
            }else{
                return cur;
            }
        }
        return root;
    }
}

/**
 * Given a binary search tree (BST) where all node values are unique, and two nodes from the tree p and q, return the lowest common ancestor (LCA) of the two nodes.

The lowest common ancestor between two nodes p and q is the lowest node in a tree T such that both p and q are descendants. The ancestor is allowed to be a descendant of itself.


Example 1:
Input: root = [5,3,8,1,4,7,9,null,2], p = 3, q = 8

Output: 5

Example 2:
Input: root = [5,3,8,1,4,7,9,null,2], p = 3, q = 4

Output: 3
Explanation: The LCA of nodes 3 and 4 is 3, since a node can be a descendant of itself.

Constraints:
2 <= The number of nodes in the tree <= 100.
-100 <= Node.val <= 100
p != q
p and q will both exist in the BST.
 */