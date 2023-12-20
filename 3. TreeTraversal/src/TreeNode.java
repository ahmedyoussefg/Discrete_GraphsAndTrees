public class TreeNode {
    private int data;
    private TreeNode left_child;
    private TreeNode right_child;

    TreeNode(){}
    TreeNode(int data){
        this.data = data;
        this.left_child=null;
        this.right_child=null;
    }
    public int getData() {
        return data;
    }

    public TreeNode getLeftChild() {
        return left_child;
    }

    public void setLeftChild(TreeNode left_child) {
        this.left_child = left_child;
    }

    public TreeNode getRightChild() {
        return right_child;
    }

    public void setRightChild(TreeNode right_child) {
        this.right_child = right_child;
    }

    public void setData(int data) {
        this.data = data;
    }
}
