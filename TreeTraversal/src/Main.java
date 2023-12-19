import com.sun.source.tree.Tree;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Queue<TreeNode> treeNodes = new LinkedList<>();
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the value of the root node: ");
        int root_value = in.nextInt();
        TreeNode root = new TreeNode(root_value);
        treeNodes.add(root);
        while(!treeNodes.isEmpty()) {
            TreeNode current = treeNodes.poll();
            System.out.printf("Enter left child value of %d (or -1 to skip): \n", current.getData());
            int child = in.nextInt();
            if (child != -1){ // left child
                TreeNode left_child =new TreeNode(child);
                current.setLeftChild(left_child);
                treeNodes.add(left_child);
            }
            System.out.printf("Enter right child value of %d (or -1 to skip): \n", current.getData());
            child = in.nextInt();
            if (child != -1){
                TreeNode right_child = new TreeNode(child);
                current.setRightChild(right_child);
                treeNodes.add(right_child);
            }
        }
        BinaryTree binaryTree = new BinaryTree(root);
        ArrayList<Integer> inorder_traversal = binaryTree.traverseInOrder();

        System.out.print("Inorder Traversal: ");
        for (int node : inorder_traversal) {
            System.out.print(node + " ");
        }
        System.out.println();
    }
}