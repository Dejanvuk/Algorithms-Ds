import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * First attempt at building a Fenwick Tree
 */

public class FenwickTree {
    private static class Node {
        Node left = null, right = null;
        int val = 0;

        private Node(int val) {
            this.val = val;
        }

        private Node(Node left, Node right, int val) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public static void main(String[] args) {
        int[] fenwick = {4, 8 ,5, 2, 6, 1, 0, 8, 1, 5, 4, 9, 1, 0, 6, 6};
        int[] fenwick2 = {1,2,4};

        //Node root = buildTree(fenwick);
        Node root2 = buildTreeSecondayMethod(fenwick, 0, fenwick.length - 1);

        printTree(root2);

    }

    /**
     * Prints the level order traversal
     * @param root is the root of the tree
     */
    private static void printTree(Node root) {
        if(root == null) 
            throw new IllegalArgumentException("Root node cannot be empty!");

        Deque<Node> dq = new ArrayDeque<>();

        dq.add(root);

        while(!dq.isEmpty()) {
            int width = dq.size();
            final int widthCopy = width;
            int i = 1;
            while(width-- > 0) {
                Node polled = dq.poll();

                System.out.print("val: " + polled.val + " [i: " + i++ + "] ");

                if(polled.left != null) {
                    dq.add(polled.left);
                }

                if(polled.right != null) {
                    dq.add(polled.right);
                }
            }

            System.out.println(" |||w: " + widthCopy + "||| ");
        }
    }

    /**
     * Helper function which converts an int[] array to a Collection of Node's
     * @param fenwick
     * @return
     */
    private static List<Node> arrToCollection (int[] fenwick) {
        if(fenwick.length == 0) 
            throw new IllegalArgumentException("Fenwick array cannot be empty!");

        return Collections.emptyList();
    }
    
    /**
     * Just another way to build up the tree, above on 'buildTree' we build it row by row, here we build it from left to right
     * @param fenwick
     * @return
     */
    private static Node buildTreeSecondayMethod (int[] fenwick, int l, int r) {
        if(l == r) {
            return new Node(fenwick[l]);
        }

        int mid = l + (r - l) / 2;

        Node left = buildTreeSecondayMethod(fenwick, l, mid);
        Node right = buildTreeSecondayMethod(fenwick, mid + 1, r);


        return new Node(left, right, left.val + right.val);
    }

    /**
     * @throws IllegalArgumentException
     * @param fenwick is the initial input
     * @return the root of the Fenwick tree
     */
    private static Node buildTree (int[] fenwick) {
        if(fenwick.length == 0) 
            throw new IllegalArgumentException("Fenwick array cannot be empty!");
        
        Deque<Node> nodes = new ArrayDeque<>();

        for(int val : fenwick) {
            nodes.add(new Node(null, null, val));
        }

        while(nodes.size() != 1) { 
            int size = nodes.size();
            int width = size / 2;

            while(width-- > 0) {
                Node left = nodes.poll();
                Node right = nodes.poll();

                System.out.print(left.val + right.val + " ");
                nodes.add(new Node(left, right, left.val + right.val));
            }
            if(size % 2 != 0) {
                Node leftover = nodes.poll();
                System.out.print(leftover.val);
                nodes.add(leftover);
            }
            System.out.println("");
        }

        System.out.println("===");

        return nodes.poll();
    }

    /**
    * O()
    */
    private static int query(int[] fenwickTree, int[] prefixSum, int l, int r) {
        return 0;
    }
    
    /**
    * O()  
    */
    private static void update(int[] fenwickTree, int[] prefixSum, int i, int val) {
    }
}