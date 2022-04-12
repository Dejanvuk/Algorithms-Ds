package com.dejanvuk.algos.sorting.BubbleSort.Java;

public class BubbleSort {

	public static void main(String[] args) {	
		int arr[] = {5,4,3,2,1};
		BubbleSort bs = new BubbleSort();
		bs.sort(arr);	
	}
	
	public void sort(int[] array) {
		boolean swapped;
		
		for(int i = 0 ; i < array.length - 1; i++) {
			swapped = false;
			for(int j = 0; j < array.length - i - 1; j++) {
				if(array[j] > array[j+1]) {
					int temp = array[j+1];
					array[j+1] = array[j];
					array[j] = temp;
					swapped = true;
				}
			}
			if(!swapped) return;
		}
	}
}
