package domain;

import util.ReadPropertiesFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class contains all logic of a Binary Tree
 * This is a singleton
 */
public class BinaryTreeService {
    private static BinaryTreeService binaryTreeService;
    private FileReader fileReader = FileReader.getInstance();

    private BinaryTreeService() {
    }

    public static BinaryTreeService getInstance() {
        if (binaryTreeService == null)
            binaryTreeService = new BinaryTreeService();
        return binaryTreeService;
    }

    /**
     * This method prints a tree
     *
     * @param root  of the tree
     * @param space of first line of the printing tree
     * @throws IOException in reading config.properties file
     */
    private void print2DTree(Node root, int space) throws IOException {
        /**
         * Reads spaces from config.properties file
         */
        int spaceCount = Integer.parseInt(ReadPropertiesFile.readKey("printTreeSpaceCount"));
        // Base case
        if (root == null)
            return;
        // Increase distance between levels
        space += spaceCount;
        // Process right child first
        print2DTree(root.getRight(), space);

        // Print current node after space
        // count
        System.out.println();
        for (int i = spaceCount; i < space; i++)
            System.out.print(" ");
        System.out.println(root.getData());
        // Process left child
        print2DTree(root.getLeft(), space);
    }

    /**
     * This method calls print2DTree(Node root, int space) with default space 0
     *
     * @param root
     * @throws IOException
     */
    public void print2DTree(Node root) throws IOException {
        print2DTree(root, 0);
    }

    /**
     * This method generates a Binary tree from the founded leaves
     *
     * @param leaves is map of leaves
     * @return root node of the tree
     */
    public Node generateTree(Map<String, Integer> leaves) {
        List<Node> nodeList = new ArrayList<>();
        /**
         * Sorts the leaves inorder
         */
        leaves.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .forEach(stringIntegerEntry ->
                {
                    Node node = new Node(new NodeData(stringIntegerEntry.getKey(), stringIntegerEntry.getValue()));
                    nodeList.add(node);
                });
        /**
         * Calling logic of creating a Binary tree
         */
        return recursiveBinaryTreeMaker(nodeList);
    }

    /**
     * This method generates the Sum Binary tree
     *
     * @param nodeList is list of nodes in a tree
     * @return the generated tree
     */
    private Node recursiveBinaryTreeMaker(List<Node> nodeList) {
        Node leftNode = null;
        Node rightNode = null;
        Node node;
        if (nodeList.size() > 1) {
            int indexPlusRemain = nodeList.size() / 2 + nodeList.size() % 2;

            List<Node> leftList = new ArrayList<>();
            List<Node> rightList = new ArrayList<>();
            for (int i = 0; i < indexPlusRemain; i++)
                leftList.add(nodeList.get(i));
            for (int i = indexPlusRemain; i < nodeList.size(); i++)
                rightList.add(nodeList.get(i));
            /**
             * Generating left node tree
             */
            leftNode = recursiveBinaryTreeMaker(leftList);
            /**
             * Generating right node tree
             */
            rightNode = recursiveBinaryTreeMaker(rightList);
            node = new Node(new NodeData(null, leftNode.getData().getTotalNumber() + rightNode.getData().getTotalNumber()));

        } else {
            node = nodeList.get(0);
        }
        node.setLeft(leftNode);
        node.setRight(rightNode);
        return node;
    }

    /**
     * This method collect all logic together. Gets an .txt file path, then generate it's tree
     *
     * @param filePath
     * @throws ApplicationException
     * @throws IOException
     */
    public void createAndPrintBinaryTreeFromFile(String filePath) throws ApplicationException, IOException {
        Map<String, Integer> leavesMap = fileReader.readFileWordCount(filePath);
        Node root = generateTree(leavesMap);
        print2DTree(root);
    }

}


