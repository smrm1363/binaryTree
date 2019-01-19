package domain;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

public class Node {
    private final int count = 10;
    private NodeData data;
    private Node left;
    private Node right;
    public Node(NodeData data) {
        this.data = data;
        left = right = null;
    }



    private void print2DTree(Node root, int space)
    {
        // Base case
        if (root == null)
            return;

        // Increase distance between levels
        space += count;

        // Process right child first
        print2DTree(root.right, space);

        // Print current node after space
        // count
        System.out.println();
        for (int i = count; i < space; i++)
            System.out.print(" ");
        System.out.println(root.data);
//        System.out.print(root.data);

        // Process left child
        print2DTree(root.left, space);
    }

    public Node generateTree(Map<String,Integer> leaves)
    {
        //// TODO: 1/19/2019      https://stackoverflow.com/questions/10367765/creating-a-sum-tree-from-leafs 
        Queue<Node> queue = new ArrayDeque<>();
                leaves.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .forEach(stringIntegerEntry ->
                {
                    Node node = new Node(new NodeData(stringIntegerEntry.getKey(),stringIntegerEntry.getValue()));
                    queue.add(node);
                });

                while (queue.size()>1)
                {
                   Node firstNode = queue.remove();
                   Node secondNode = queue.remove();
                   Node parrent  = new Node(new NodeData(null,firstNode.getData().getTotalNumber()+secondNode.getData().getTotalNumber()));
                   parrent.setLeft(firstNode);
                   parrent.setRight(secondNode);
                    queue.add(parrent);
                }
                return queue.remove();
    }
    public void print2DTree(Node root)
    {
        print2DTree(root,0);
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
