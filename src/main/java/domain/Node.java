package domain;

/**
 * This is a Node
 */
public class Node {

    private NodeData data;
    private Node left;
    private Node right;

    public Node(NodeData data) {
        this.data = data;
        left = right = null;
    }

    public NodeData getData() {
        return data;
    }

    public void setData(NodeData data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
