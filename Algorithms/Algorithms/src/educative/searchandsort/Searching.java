package educative.searchandsort;

public class Searching {
	public int linearSearch(int[] arr, int elem) {
		if(arr == null || arr.length == 0) {
			return -1;
		}
		
		for(int i = 0; i < arr.length; ++i) {
			if(arr[i] == elem) {
				return i;
			}
		}

		return -1;
	}
	
	public int binarySearch(int[] arr, int elem) {
		int start = 0, end = arr.length-1;
		
		while(start <= end) {
			int mid = start + (end - start)/2;
			
			if(arr[mid] == elem) {
				return mid;
			}
			else if(arr[mid] < elem) {
				start = mid+1;
			}
			else {
				end = mid-1;
			}
		}
		
		return -1;
	}
}
