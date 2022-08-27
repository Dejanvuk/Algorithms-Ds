import java.util.ArrayDeque;
import java.util.Deque;

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
        int[] fenwick = {-5, 7, 0, 5, 3, 2, -1, 0, -2};


        Node root = buildTree(fenwick);

        printTree(root);

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

            while(width-- > 0) {
                Node polled = dq.poll();

                System.out.print(polled.val + " ");

                if(polled.left != null) {
                    dq.add(polled.left);
                }

                if(polled.right != null) {
                    dq.add(polled.right);
                }
            }

            System.out.println(" ");
        }
    }
    
    /**
     * @throws IllegalArgumentException
     * @param fenwick is the initial input
     * @return the root of the Fenwick tree
     */
    private static Node buildTree (int[] fenwick) {
        if(fenwick.length == 0) 
            throw new IllegalArgumentException("Fenwick array cannot be empty!");
        
        Node root = null;
        //Deque<Integer> sums = new ArrayDeque<>();
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