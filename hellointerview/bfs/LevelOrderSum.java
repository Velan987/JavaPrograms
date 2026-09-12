package hellointerview.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


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

    @Override
    public String toString() {
        return ""+this.val;
    }
}

// group nodes by their levels
public class LevelOrderSum {

    public static List<Integer> traverse(TreeNode root){
        List<Integer> result = new ArrayList<>();
        /**
         * first we will add the root node into the queue, that will be the first level
         * for the size of the queue we will remove the node from front in the queue and add to a list and add removed nodes left and right child if any
         * for each level we will get the size of the queue - same number we will use for for loop(we wont invole size again)
         * if queue has 2 nodes then for loop will run 2 times only, that is why even we add and remove node from same queue there wont be any issue
         * [2, 3] its childs are 4,5 and 7,8 - queue will have 2 and 3 and for will run two times only
         * first it will remove 2 from queue and add it to list and 2's childs 4 and 5 are added to queue now queue will have [3, 4, 5]
         * second time 3 will be removed from queue and add it to list and 3's childs 7 and 8 are added to queue - [4, 5, 7, 8]
         * for loop ends
         */
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            int queueSize = queue.size();
            int sum = 0;
            for(int i=0; i<queueSize; i++){ 
                TreeNode node = queue.poll();
                sum += node.val;
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            result.add(sum);
        }

        return result;
    }
    public static void main(String[] args) {
         TreeNode root = new TreeNode(4);

        root.left = new TreeNode(2);
        root.right = new TreeNode(7);

        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(8);

        root.left.left.right = new TreeNode(9);

        System.out.println(traverse(root));
    }    
}


/**
 * Given the root of a binary tree, return the sum of the nodes at each level. The output should be a list containing the sum of the nodes at each level.

Example 1:

Input:

[1, 3, 4, null, 2, 7, null, 8]

Output: [1, 7, 9, 8]

Example 2:

Input:

[1, 2, 5, 3, null, null, null, null, 4]

Output: [1, 7, 3, 4]
 */