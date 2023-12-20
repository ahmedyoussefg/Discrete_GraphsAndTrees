import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.Stack;

public class BinaryTree {
    private TreeNode root;

    BinaryTree(){}
    BinaryTree(TreeNode root){
        this.root=root;
    }

    ArrayList<Integer> traverseInOrder() {
        if (this.root == null) {
            return new ArrayList<>(); // empty
        }
        ArrayList<Integer> in_order = new ArrayList<>();
        TreeNode curr = this.root;
        Stack<TreeNode> nodeStack = new Stack<>();
        while (curr != null || !nodeStack.empty()) {
            // reach to the furthest left leaf
            while (curr != null) {
                nodeStack.push(curr);
                curr = curr.getLeftChild();
            }
            curr = nodeStack.pop();
            in_order.add(curr.getData());

            // traverse the right child of the furthest left leaf
            curr=curr.getRightChild();
        }
        return in_order;
    }
    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getRoot() {
        return root;
    }

}
