package domain;

import java.util.*;

public class BinaryTreeService {
    private static BinaryTreeService binaryTreeService;
    private FileReader fileReader = FileReader.getInstance();
    private BinaryTreeService()
    {}
    public static BinaryTreeService getInstance()
    {
        if(binaryTreeService==null)
            binaryTreeService = new BinaryTreeService();
        return binaryTreeService;
    }

    private final int count = 10;
    private void print2DTree(Node root, int space)
    {
        // Base case
        if (root == null)
            return;

        // Increase distance between levels
        space += count;

        // Process right child first
        print2DTree(root.getRight(), space);

        // Print current node after space
        // count
        System.out.println();
        for (int i = count; i < space; i++)
            System.out.print(" ");
        System.out.println(root.getData());
//        System.out.print(root.data);

        // Process left child
        print2DTree(root.getLeft(), space);
    }

    public void print2DTree(Node root)
    {
        print2DTree(root,0);
    }

    public Node generateTree(Map<String,Integer> leaves)
    {
        //// TODO: 1/19/2019      https://stackoverflow.com/questions/10367765/creating-a-sum-tree-from-leafs
        Queue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.getData().getTotalNumber().compareTo(o2.getData().getTotalNumber()));
        leaves.entrySet().stream().sorted((o1, o2) -> o1.getValue().compareTo(o2.getValue()))
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

    public void createAndPrintBinaryTreeFromFile(String filePath) throws ApplicationException {
        Map<String,Integer> leavesMap = fileReader.readFileWordCount(filePath);
        Node root = generateTree(leavesMap);
        print2DTree(root);
    }

}


