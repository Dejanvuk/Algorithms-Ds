package com.dejanvuk.algos.sorting.InsertionSort.Java;

public class InsertionSort {
	public static void main(String[] args) {
		int[] arr = {44,3,1,6,9,4,5,2,0,1,2,2,3,77};
		InsertionSort.sort(arr);
		InsertionSort.print(arr);
	}
	
	public static void print(int[] arr) {
		for(int i = 0; i < arr.length; i++) System.out.print(arr[i] + " ");
	}
	
	public static void sort(int arr[]) {
		for(int i = 0; i < arr.length; i++) {
			int pos = i;
			for(int j = i-1; j >= 0; j--) {
				if(arr[pos] < arr[j]) {
					int temp = arr[j];
					arr[j] = arr[pos];
					arr[pos]= temp;
					pos =j;
				}
			}
		}
	}
}
