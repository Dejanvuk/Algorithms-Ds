public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {44, 88, 77, 22, 66, 11, 99, 55, 00, 33}; // l = 10
        QuickSort.quickSort(arr, 0 , arr.length);
        QuickSort.print(arr);
    }

    public static void print(int[] arr) {
        for(int i = 0; i < arr.length; i++) System.out.print(arr[i] + " ");
    }

    public static void sort(int arr[], int l, int r) {
        int i = l; // current pos to replace
        int p = l;
        int j = l + 1;

        while(j < r) {
            System.out.println("here");
            if(arr[j] <= arr[p]) {
                int temp = arr[++i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
            else {
                j++;
            }
        }

        // replace pivot with current i
        int temp = arr[++i];
        arr[i] = arr[p];
        arr[p] = temp;
    }

    public static void quickSort(int arr[], int l, int r) {
        if(l >= r) return;

        sort(arr, l, r);
        int mid = l + (r - l) / 2;
        sort(arr,l, mid);
        sort(arr, mid + 1, r);
    }
}
