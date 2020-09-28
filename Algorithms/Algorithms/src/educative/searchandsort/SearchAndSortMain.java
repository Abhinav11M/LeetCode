package educative.searchandsort;

import helpers.Helpers;

public class SearchAndSortMain {

	public static void main(String[] args) {
		// ===== Selection Sort =====
		Sorting sort = new Sorting();
		int[] arr = new int[] {5,4,1,0,5,95,4,-100,200,0};
		sort.selectionSort(arr);
		Helpers.printArr(arr);
		
		arr = new int[] {5,4,1,0,5,95,4,-100,200,0};
		sort.selectionSort2(arr);
		Helpers.printArr(arr);
		// ===== Selection Sort =====

		// ===== Bubble Sort =====
		arr = new int[] {5,4,1,0,5,95,4,-100,200,0};
		sort.bubbleSort(arr);
		Helpers.printArr(arr);
		// ===== Bubble Sort =====

		// ===== Insertion Sort =====
		arr = new int[] {5,4,1,0,5,95,4,-100,200,0};
		sort.insertionSort(arr);
		Helpers.printArr(arr);
		
		arr = new int[] {5,4,1,0,5,95,4,-100,200,0};
		sort.insertionSort2(arr);
		Helpers.printArr(arr);
		// ===== Insertion Sort =====

		// ===== Merge Sort =====
		arr = new int[] {5,4,1,0,5,95,4,-100,200,0};
		sort.mergeSort(arr);
		Helpers.printArr(arr);
		// ===== Merge Sort =====

		// ===== Quick Sort =====
		arr = new int[] {5,4,1,0,5,95,4,-100,200,0};
		sort.quickSort(arr);
		Helpers.printArr(arr);
		// ===== Quick Sort =====
		
		//=============Searching===================
		Searching search = new Searching();
		
		//===== Linear Search =====
		arr = new int[] {5,4,1,410,5,95,4,-100,20,0};
		System.out.println(search.linearSearch(arr, 410));
		System.out.println(search.linearSearch(arr, -100));
		System.out.println(search.linearSearch(arr, 12));
		//===== Linear Search =====

		// ===== Binary Search =====
		arr = new int[] {-100,0,0,1,4,4,5,5,95,200};
		System.out.println(search.binarySearch(arr, 4));
		System.out.println(search.binarySearch(arr, 200));
		System.out.println(search.binarySearch(arr, 12));
		// ===== Binary Search =====
	}
}
