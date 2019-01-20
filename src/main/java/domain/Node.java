package domain;

import java.util.*;
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

//    public Node generateTree(Map<String,Integer> leaves)
//    {
//        //// TODO: 1/19/2019      https://stackoverflow.com/questions/10367765/creating-a-sum-tree-from-leafs
//        Stack<Node> queue = new Stack<>();
//                leaves.entrySet().stream().sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
//                .forEach(stringIntegerEntry ->
//                {
//                    Node node = new Node(new NodeData(stringIntegerEntry.getKey(),stringIntegerEntry.getValue()));
////                    queue.add(node);
//                    queue.push(node);
//                });
//
//                while (queue.size()>1)
//                {
//                   Node firstNode = queue.pop();
//                   Node secondNode = queue.pop();
//                   Node parrent  = new Node(new NodeData(null,firstNode.getData().getTotalNumber()+secondNode.getData().getTotalNumber()));
//                   parrent.setLeft(firstNode);
//                   parrent.setRight(secondNode);
//                    queue.push(parrent);
//                }
//                return queue.pop();
//    }

    public Node generateTree(Map<String,Integer> leaves)
    {
        //// TODO: 1/19/2019      https://stackoverflow.com/questions/10367765/creating-a-sum-tree-from-leafs
        Queue<Node> queue = new ArrayDeque<>();
        leaves.entrySet().stream().sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
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




//        public Node generateTree(Map<String,Integer> leaves)
//    {
//        //// TODO: 1/19/2019      https://stackoverflow.com/questions/10367765/creating-a-sum-tree-from-leafs
//        List<Node> queue = new ArrayList<>();
//        leaves.entrySet().stream().sorted((o1, o2) -> o1.getValue().compareTo(o2.getValue()))
//                .forEach(stringIntegerEntry ->
//                {
//                    Node node = new Node(new NodeData(stringIntegerEntry.getKey(),stringIntegerEntry.getValue()));
//                    queue.add(node);
//                });
//
//       Node root =new Node(null);
//       root = buildSumTree2(queue,0,queue.size());
//        return root;
//    }


    Node buildSumTree2(List<Node> nodeList, int startpos, int length) {
        if (length < 1)
            return null;
        if (length == 1)
            return new Node(new NodeData(nodeList.get(startpos).getData().getWord(),nodeList.get(startpos).getData().getTotalNumber()));
        int halflen = length / 2;
        Node l = buildSumTree2(nodeList, startpos, halflen);
        Node r = buildSumTree2(nodeList, startpos + halflen, length - halflen);
        Node n = new Node(new NodeData(null,l.getData().getTotalNumber()+r.getData().getTotalNumber()));
        n.left = l;
        n.right = r;
        return n;
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
