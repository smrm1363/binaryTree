package domain;

import util.ReadPropertiesFile;

import java.io.IOException;
import java.util.*;

/**
 * This class contains all logic of a Binary Tree
 * This is a singleton
 */
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

    /**
     * This method prints a tree
     * @param root of the tree
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
     * @param root
     * @throws IOException
     */
    public void print2DTree(Node root) throws IOException {
        print2DTree(root,0);
    }

    /**
     * This method generates a Binary tree from the founded leaves
     * @param leaves is map of leaves
     * @return root node of the tree
     */
    public Node generateTree(Map<String,Integer> leaves)
    {
        List<Node> nodeList = new ArrayList<>();
        /**
         * Sorts the leaves inorder
         */
        leaves.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .forEach(stringIntegerEntry ->
                {
                    Node node = new Node(new NodeData(stringIntegerEntry.getKey(),stringIntegerEntry.getValue()));
                    nodeList.add(node);
                });
        /**
         * Calling logic of creating a Binary tree
         */
        Node node = recursiveBinaryTreeMaker(nodeList);
        return node;
    }

    /**
     * This method generates the Sum Binary tree
     * @param nodeList is list of nodes in a tree
     * @return the generated tree
     */
    private Node recursiveBinaryTreeMaker(List<Node> nodeList)
    {
        Node leftNode = null;
        Node righNode = null;
        Node node = null;
        if(nodeList.size()>1)
        {
            int indextPlusRemain = nodeList.size()/2+nodeList.size()%2;

            List<Node> leftList = new ArrayList<>();
            List<Node> righttList = new ArrayList<>();
            for (int i = 0;i<indextPlusRemain;i++)
                leftList.add(nodeList.get(i));
            for (int i = indextPlusRemain;i<nodeList.size();i++)
                righttList.add(nodeList.get(i));
            /**
             * Generating left node tree
             */
            leftNode = recursiveBinaryTreeMaker(leftList);
            /**
             * Generating right node tree
             */
            righNode = recursiveBinaryTreeMaker(righttList);
            node = new Node(new NodeData(null,leftNode.getData().getTotalNumber()+righNode.getData().getTotalNumber()));

        }
        else
        {
            node = nodeList.get(0);
        }
        node.setLeft(leftNode);
        node.setRight(righNode);
        return node;
    }

    /**
     * This method collect all logic together. Gets an .txt file path, then generate it's tree
     * @param filePath
     * @throws ApplicationException
     * @throws IOException
     */
    public void createAndPrintBinaryTreeFromFile(String filePath) throws ApplicationException, IOException {
        Map<String,Integer> leavesMap = fileReader.readFileWordCount(filePath);
        Node root = generateTree(leavesMap);
        print2DTree(root);
    }

}


