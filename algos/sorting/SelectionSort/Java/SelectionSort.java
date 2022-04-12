package com.dejanvuk.algos.sorting.SelectionSort.Java;

public class SelectionSort {
	public static void main(String[] args) {
		int[] arr = {44,3,1,6,9,4,5,2,0,1,2,2,3};
		SelectionSort.sort(arr);
		SelectionSort.print(arr);
	}
	
	public static void print(int[] arr) {
		for(int i = 0; i < arr.length; i++) System.out.print(arr[i] + " ");
	}
	
	public static void sort(int arr[]) {
		for(int i = 0; i < arr.length - 1; i++) {
			int min = i;
			for(int j = i+1; j < arr.length; j++) {
				if(arr[j] < arr[min]) min = j;
			}	
			int temp = arr[i];
			arr[i] = arr[min];
			arr[min] = temp;
		}
	}
}
