import domain.*;

public class BinaryTreeForTestApplication {


    public static void main(String[] args) throws ApplicationException {
//        System.out.println("Hiiiiii");
//        Node tree = new Node((new NodeData(null,10)));
//
//
//        Integer x = null;
//        System.out.println("............."+x+1);
//
//        FileReader fileReader = FileReader.getInstance();
//        tree = tree.generateTree(fileReader.readFileWordCount("C:\\Users\\hurie&mohamadreza\\Downloads\\input001.txt"));
//        tree.print2DTree(tree);
//        System.out.println("****");

        if(args.length <1)
            throw new ApplicationException("For running the program you should enter the path of the Text file as a parameter");
        BinaryTreeService binaryTreeService = BinaryTreeService.getInstance();
        binaryTreeService.createAndPrintBinaryTreeFromFile(args[0]);
    }
}
