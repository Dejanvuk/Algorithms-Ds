public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {44, 88, 77, 22, 66, 11, 99, 55, 00, 33}; // l = 10
        QuickSort.quickSort(arr);
        QuickSort.print(arr);
    }

    /**
     * Prints the elements of an array
     * @param arr
     */
    public static void print(int[] arr) {
        for(int i = 0; i < arr.length; i++) System.out.print(arr[i] + ", ");
    }

    /**
     * Only made the conversion between int[] to List<Integer> to test Java 8 lol
     * @param arr : to be sorted
     * @return new sorted array
     */
    public static int[] quickSort(int[] arr) {
        List<Integer> result = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        sort(result, 0, result.size() - 1);
        return result.stream().mapToInt(i->i).toArray();
    }
        
    /**
    Pivot is first element
    */
    private static void sort(List<Integer> arr, int l, int r) {
        if(r - l <= 1) return;
            
        int pivot = l, i = l, j = l + 1;
            
        while(j <= r) {
            if(arr.get(j) < arr.get(pivot)) {
                swap(arr, ++i, j);
            }
            j++;
        }
            
        swap(arr, pivot, i); // swap pivot, so everything left of pivot is smaller and everything on the right is greather
            
        sort(arr, pivot, i - 1); // recursive sort left
        sort(arr, i + 1, r); // recursive sort right
    }
        
    private static void swap(List<Integer> arr, int to, int from) {
        int temp = arr.get(to);
        arr.set(to, arr.get(from));
        arr.set(from, temp);
    }
}
