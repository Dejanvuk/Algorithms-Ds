package com.dejanvuk.algos.sorting.HeapSort.java;

public class HeapSort {

    public static void main(String[] args) {
        int[] arr = {85,3,4,2,5,6,7,3,6,33,5,7,293,55,2,1,0,9};
        //buildMaxHeap(arr);
        //print(arr);
        heapSort(arr);
        print(arr);
    }

    public static void print(int[] arr) {
        for(int i = 0; i < arr.length; i++) System.out.print(arr[i] + " ");
    }

    // O(l log l)
    public static void buildMaxHeap(int[] arr) {
        for(int i = arr.length / 2 - 1; i >=0; i--) {
            heapify(arr,arr.length, i);
        }
    }

    public static void heapSort(int[] arr) {
        buildMaxHeap(arr);
        int l = arr.length;
        while(l > 0) {
            // swap the root with the last
            int temp = arr[0];
            arr[0] = arr[l-1];
            arr[l-1] = temp;
            // reduce the size of the heap
            l--;
            // heapify the new root to get the next max
            heapify(arr,l,0);
        }
    }

    // O(log l)
    public static void heapify(int[] arr, int l, int parent) {
        int largest = parent;
        int leftChild = 2*parent + 1;
        int rightChild = 2*parent + 2;

        if(leftChild < l && arr[leftChild] > arr[largest]) {
            largest = leftChild;
        }
        if(rightChild < l && arr[rightChild] > arr[largest]) {
            largest = rightChild;
        }

        if(largest != parent) {
            // swap parent with largest
            int temp = arr[parent];
            arr[parent] = arr[largest];
            arr[largest] = temp;
            //call heapify again
            heapify(arr, l, largest);
        }
    }
}
