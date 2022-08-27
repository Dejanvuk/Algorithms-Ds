/**
 * Brute-force attempt to code 
 */

public class PrefixSum {
    public static void main(String[] args) {
        int[] fenwick = {-5, 7, 0, 5, 3, 2, -1, 0, 2};
        int[] prefixSum = new int[fenwick.length];
        prefixSum[0] = fenwick[0];
        
        for(int i = 1; i <  fenwick.length; i++){
            prefixSum[i] = prefixSum[i-1] + fenwick[i];
        }
        
        for(int sum : prefixSum) System.out.print(sum + " ");


    }
    
    /**
    * O(1)
    */
    private static int query(int[] fenwickTree, int[] prefixSum, int l, int r) {
        if(l = 0) 
            return prefixSum[r];
        else 
            return prefixSum[r] - prefixSum[l - 1];
    }
    
    /**
    * O(n)  
    */
    private static void update(int[] fenwickTree, int[] prefixSum, int i, int val) {
        while(i < prefixSum.length) {
            prefixSum[i] -= fenwickTree[i];
            prefixSum[i] += val;
        }
        
        fenwickTree[i] = val;
    }
}