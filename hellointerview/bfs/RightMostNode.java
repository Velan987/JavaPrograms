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
public class RightMostNode {

    public static List<Integer> rightMost(TreeNode root){
        List<Integer> rightNodes = new ArrayList<>();
        if(root == null){
            return rightNodes;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=1; i<=size; i++){
                TreeNode node = queue.poll();
                // if i == size then that node is the right most node of that layer
                if(i == size){
                    rightNodes.add(node.val);
                }
                // we are concern only about right nodes or last node of that layer - so we can add left node only if right is not there for that parent
                // above approach is wrong because in one layer both child will be there and we skip left child and in next layer there is child only for 
                // the child node which we skipped, in that case we will miss entire layer, so just add both childs if it is not null
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
                
            }
        }

        return rightNodes;
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(3);
        root.right = new TreeNode(4);

        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(7);

        root.left.right.left = new TreeNode(8);

        System.out.println(rightMost(root));
    }   
}
