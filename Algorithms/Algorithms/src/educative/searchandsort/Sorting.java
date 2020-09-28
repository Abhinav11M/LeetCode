package educative.searchandsort;

import java.util.Random;

public class Sorting {
	/**
	 * In selection sort, after every iteration, the smallest number comes to the front
	 */
	public void selectionSort(int[] arr) {
		for(int i = 0; i < arr.length - 1; ++i) {
			for(int j = i + 1; j < arr.length; ++j) {
				if(arr[i] > arr[j]) {
					swap(arr, i, j);
				}
			}
		}
	}

	/**
	 * Method 2 for selection sort.
	 * Find min index in each iteration and swap that to bring it to the front.
	 * @param arr
	 */
	public void selectionSort2(int[] arr) {
		for(int i = 0; i < arr.length-1; ++i) {
			// Find min b/w i and arr.length-1
			int minIdx = findMin(arr, i);
			
			// Swap
			swap(arr, i, minIdx);
		}
	}

	/**
	 * In Bubble sort, we move the largest element at the last at the end of each iteration.
	 */
	public void bubbleSort(int[] arr) {
		for(int i = 0; i < arr.length; ++i) {
			for(int j = 0; j < arr.length-i-1; ++j) {
				if(arr[j] > arr[j+1]) {
					swap(arr, j, j+1);
				}
			}
		}
	}
	
	/**
	 * It iterates over a given array and 
	 */
	public void insertionSort(int[] arr) {
		for(int i = 1; i < arr.length; ++i) {
			int j = i-1;
			while(j >= 0 && arr[j] > arr[j+1]) {
				swap(arr, j, j+1);
				j--;
			}
		}
	}

	public void insertionSort2(int[] arr) {
		for(int i = 1; i < arr.length; ++i) {
			int j = i-1;
			int elem = arr[i];
			while(j >= 0 && arr[j] > elem) {
				// copy the larger element to right
				arr[j+1] = arr[j];
				--j;
			}
			// Copy elem to the correct position
			arr[j+1] = elem;
		}
	}
	
	/**
	 * Merge sort : This uses divide and conquer
	 */
	public void mergeSort(int[] arr) {
		mergeSort(arr, 0, arr.length-1);
	}
	
	private void mergeSort(int[] arr, int start, int end) {
		if(start >= end) {
			return;
		}
		
		int mid = start + (end-start)/2;
		mergeSort(arr, start, mid);
		mergeSort(arr, mid+1, end);
		
		merge(arr, start, mid, end);
	}

	private void merge(int[] arr, int start, int mid, int end) {
		
		// Merge these two into arr
		int lSize = mid-start+1;
		int[] left = new int[lSize];
		
		int rSize = end - mid; // End - (mid+1) + 1
		int[] right = new int[rSize];
		
		// Copy elements
		for(int i = 0; i < lSize; ++i) {
			left[i] = arr[i+start];
		}
		
		for(int i = 0; i < rSize; ++i) {
			right[i] = arr[mid+1+i];
		}
		
		int i = start;
		int l = 0, r = 0;
		while(l < left.length && r < right.length) {
			if(left[l] > right[r]) {
				arr[i++] = right[r++];
			}
			else {
				arr[i++] = left[l++];
			}
		}
		
		// If right has exhausted
		while(l < left.length) {
			arr[i++] = left[l++];
		}
		// If left has exhausted
		while(r < right.length) {
			arr[i++] = right[r++];
		}
	}
	
	/**
	 * Quick Sort
	 * @param arr
	 */
	public void quickSort(int[] arr) {
		quickSort(arr, 0, arr.length-1);
	}
	
	private void quickSort(int[] arr, int start, int end) {
		if(start >= end) {
			return;
		}
		
		int partition = partition(arr, start, end); // After this step, the partition will already be at its correct place.
		
		quickSort(arr, start, partition-1);
		quickSort(arr, partition+1, end);

	}
	
	private int partition(int[] arr, int start, int end) {
		
		// find the pivot index
		int pIdx = getPivotIndex(start, end);
		
		// Push the pivot to the end
		swap(arr, pIdx, end);
		
		int pivot = arr[end];
		
		// Move all the elements that are less that pivot to the left side
		int l = start;
		
		for(int i = start; i < end; ++i) {
			if(arr[i] <= pivot) {
				swap(arr, l, i);
				l++;
			}
		}
		// Swap the pivot element
		swap(arr, l, end);
		
		return l;
	}
	

	private int getPivotIndex(int start, int end) {

		
		int i1 = start + random.nextInt(end - start + 1);
		int i2 = start + random.nextInt(end - start + 1);
		int i3 = start + random.nextInt(end - start + 1);
		
		return Math.max(Math.min(i1, i2), Math.min(Math.max(i1, i2), i3));
	}

	private int findMin(int[] arr, int start) {
		int minIdx = start;
		
		for(int i = start+1; i < arr.length; ++i) {
			if(arr[i] < arr[minIdx]) {
				minIdx = i;
			}
		}
		
		return minIdx;
	}

	private void swap(int[] arr, int i, int j) {
		int elem = arr[i];
		arr[i] = arr[j];
		arr[j] = elem;
	}
	
	private Random random = new Random();
}
