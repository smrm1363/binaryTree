import domain.Node;
import domain.NodeData;

public class BinaryTreeForTestApplication {

    public static void main(String[] args) {
        System.out.println("Hiiiiii");
        Node tree = new Node((new NodeData(null,10)));

        tree.setLeft(new Node(new NodeData(null,5)));
        tree.setRight(new Node(new NodeData("Ali",2)));
        tree.getLeft().setRight(new Node(new NodeData("Mammad",2)));
     //   tree.printTree(tree.getRoot());

        System.out.println("other tree :");
        tree.print2DTree(tree);

    }
}