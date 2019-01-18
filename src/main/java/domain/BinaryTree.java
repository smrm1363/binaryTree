//package domain;
//
//import java.util.LinkedList;
//import java.util.Map;
//import java.util.Queue;
//import java.util.TreeMap;
//
//public class BinaryTree {
//    private Node root;
//    public void printTree(Node root) {
//        class QueueObj {
//            Node node;
//            int hd;
//
//            QueueObj(Node node, int hd) {
//                this.node = node;
//                this.hd = hd;
//            }
//        }
//        Queue<QueueObj> q = new LinkedList<QueueObj>();
//        Map<Integer, Node> topViewMap = new TreeMap<Integer, Node>();
//
//        if (root == null) {
//            return;
//        } else {
//            q.add(new QueueObj(root, 0));
//        }
//
//        System.out.println("The top view of the tree is : ");
//
//        // count function returns 1 if the container
//        // contains an element whose key is equivalent
//        // to hd, or returns zero otherwise.
//        while (!q.isEmpty()) {
//            QueueObj tmpNode = q.poll();
//            if (!topViewMap.containsKey(tmpNode.hd)) {
//                topViewMap.put(tmpNode.hd, tmpNode.node);
//            }
//
//            if (tmpNode.node.getLeft() != null) {
//                q.add(new QueueObj(tmpNode.node.getLeft(), tmpNode.hd - 1));
//            }
//            if (tmpNode.node.getRight() != null) {
//                q.add(new QueueObj(tmpNode.node.getRight(), tmpNode.hd + 1));
//            }
//
//        }
//        for (Map.Entry<Integer, Node> entry : topViewMap.entrySet()) {
//            System.out.print(entry.getValue().getData().toString());
//        }
//    }
//
//    public void print(Node node)
//    {
////        System.out.print(node.getData().toString());
////        if(node.getLeft()!= null)
////            print(node.getLeft());
////        if(node.getRight()!=null)
////            print(node.getRight());
////        System.out.println();
//
//        System.out.println(node.getData().toString() + (node.getRight()!=null&&node.getLeft()!=null ? "└── " : "├── ") + this.toString());
//
//        if(null != leftChildren)
//        {
//            leftChildren.print(prefix + (isTail ? "    " : "│   "), (null == rightChildren ? true : false));
//        }
//
//        if(null != rightChildren)
//        {
//            rightChildren.print(prefix + (isTail ?"    " : "│   "), true);
//        }
//
//    }
//    public Node getRoot() {
//        return root;
//    }
//
//    public void setRoot(Node root) {
//        this.root = root;
//    }
//}
