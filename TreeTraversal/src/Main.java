import com.sun.source.tree.Tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

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
        System.out.println("hello world");
    }
}