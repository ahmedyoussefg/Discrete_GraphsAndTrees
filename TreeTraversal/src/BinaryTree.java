import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.Stack;

public class BinaryTree {
    private TreeNode root;

    BinaryTree() {
    }

    BinaryTree(TreeNode root) {
        this.root = root;
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
            curr = curr.getRightChild();
        }
        return in_order;
    }

    public ArrayList<Integer> traversePreOrder() {
        if (this.root == null) {
            return new ArrayList<>(); // empty
        }
        ArrayList<Integer> pre_order = new ArrayList<>();
        traversePreOrder(this.root, pre_order);
        return pre_order;
    }

    private void traversePreOrder(TreeNode root, ArrayList<Integer> pre_order) {
        if (root == null) {
            return;
        }
        pre_order.add(root.getData());
        traversePreOrder(root.getLeftChild(), pre_order);
        traversePreOrder(root.getRightChild(), pre_order);
    }

    public ArrayList<Integer> traversePostOrder() {
        if (this.root == null) {
            return new ArrayList<>(); // empty
        }
        ArrayList<Integer> post_order = new ArrayList<>();
        traversePostOrder(this.root, post_order);
        return post_order;
    }

    private void traversePostOrder(TreeNode root, ArrayList<Integer> post_order) {
        if (root == null) {
            return;
        }
        traversePostOrder(root.getLeftChild(), post_order);
        traversePostOrder(root.getRightChild(), post_order);
        post_order.add(root.getData());
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getRoot() {
        return root;
    }

}
