public class MergeSort {
	public static void main(String[] args) {
		int[] arr = {9,8,7,6,5,4,3,2,1};
		MergeSort.sort(arr, 0, arr.length - 1);
		MergeSort.print(arr);
	}
	
	public static void print(int[] arr) {
		for(int i = 0; i < arr.length; i++) System.out.print(arr[i] + " ");
	}

	public static void sort(int arr[],int l,int r) {
		if(l < r) {
			int q = l + (r - l )/2;
			sort(arr, l , q);
			sort(arr, q+1, r);
			merge(arr, l, q, r);
		}
	}
	
	private static void merge(int arr[],int l,int q ,int r) {
		int i, j, k;
	    int n1 = q - l + 1;
	    int n2 =  r - q;

	    int[] L = new int[n1+1];
	    int[] R = new int[n2+1];

	    for (i = 0; i < n1; i++)
	        L[i] = arr[l + i];
	    for (j = 0; j < n2; j++)
	        R[j] = arr[q + 1+ j];

	    L[i] = Integer.MAX_VALUE;
	    R[j] = Integer.MAX_VALUE;

	    i = 0; j = 0; k = l;
	    while(k <= r) {
	        if(L[i] <= R[j])
	            arr[k] = L[i++];
	        else if(R[j] < L[i])
	            arr[k] = R[j++];
	        k++;
	    }
	}
}