import com.sun.source.tree.Tree;

import java.util.*;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        Queue<TreeNode> treeNodes = new LinkedList<>();
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the value of the root node: ");
        int root_value = in.nextInt();
        TreeNode root = new TreeNode(root_value);
        ArrayList<Integer> seen_nodes = new ArrayList<>();
        treeNodes.add(root);
        seen_nodes.add(root_value);
        while (!treeNodes.isEmpty()) {
            TreeNode current = treeNodes.poll();
            System.out.printf("Enter left child value of %d (or -1 to skip): \n", current.getData());
            int child = in.nextInt();
            if (child != -1) { // left child
                if (isRepeated(child,seen_nodes)) {
                    System.out.print("Assumption: Numbers must be unique.");
                    exit(1);
                }
                seen_nodes.add(child);
                TreeNode left_child = new TreeNode(child);
                current.setLeftChild(left_child);
                treeNodes.add(left_child);
            }
            System.out.printf("Enter right child value of %d (or -1 to skip): \n", current.getData());
            child = in.nextInt();
            if (child != -1) {
                if (isRepeated(child,seen_nodes)) {
                    System.out.print("Assumption: Numbers must be unique.");
                    exit(1);
                }
                seen_nodes.add(child);
                TreeNode right_child = new TreeNode(child);
                current.setRightChild(right_child);
                treeNodes.add(right_child);
            }
        }
        BinaryTree binaryTree = new BinaryTree(root);
        ArrayList<Integer> preorder_traversal = binaryTree.traversePreOrder();
        ArrayList<Integer> inorder_traversal = binaryTree.traverseInOrder();
        ArrayList<Integer> postorder_traversal = binaryTree.traversePostOrder();
        System.out.print("Preorder Traversal: ");
        for (int node : preorder_traversal) {
            System.out.print(node + " ");
        }
        System.out.println();
        System.out.print("Inorder Traversal: ");
        for (int node : inorder_traversal) {
            System.out.print(node + " ");
        }
        System.out.println();
        System.out.print("Postorder Traversal: ");
        for (int node : postorder_traversal) {
            System.out.print(node + " ");
        }
    }
    static boolean isRepeated(int node, ArrayList<Integer> seen_nodes) {
        for (int seen : seen_nodes) {
            if (node == seen) {
                return true;
            }
        }
        return false;
    }
}