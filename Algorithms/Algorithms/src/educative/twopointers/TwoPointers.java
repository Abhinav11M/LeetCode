package educative.twopointers;

public class TwoPointers {
	
	/**
	 * Given an array of sorted numbers and a target sum, 
	 * find a pair in the array whose sum is equal to the given target.
	 * @param arr Input array
	 * @param target Target sum
	 * @return Array of indices
	 */
	public int[] pairWithTargetSum(int[] arr, int target) {
		int start = 0, end = arr.length-1;
		
		while(start < end) {
			if((arr[start] + arr[end]) == target) {
				return new int[] {start, end};
			}
			else if((arr[start] + arr[end]) > target) {
				end--;
			}
			else {
				start++;
			}
		}
		
		return new int[] {-1,-1};
	}
	
	/**
	 * Given an array of sorted numbers, remove all duplicates from it. You should not use any extra space; 
	 * after removing the duplicates in-place return the new length of the array.
	 */
	public int removeDuplicates(int[] arr) {
		if(arr == null || arr.length == 0) {
			return 0;
		}
		
		int index1 = 1, index2 = 1;

		int currVal = arr[0];
		for(;index2 < arr.length; ++index2) {
			if(arr[index2] == currVal) {
				continue;
			}

			arr[index1++] = arr[index2];
			currVal = arr[index2];
		}
		
		return index1;
	}
	
	/**
	 * Given a sorted array, create a new array containing squares of all 
	 * the number of the input array in the sorted order.
	 */
	public int[] makeSortedArraySquares(int[] arr) {
		int[] res = new int[arr.length];
		int resIdx = 0;
		
		int indexNeg = 0, indexPos = 0;
		// Find the 1st positive index
		for(; indexPos < arr.length && arr[indexPos] < 0; ++indexPos); // IndexPos points to 0 or the 1st positive integer
		indexNeg = indexPos-1;
		
		while(indexPos < arr.length || indexNeg >= 0) {
			if(indexPos == arr.length) { // Positives are exhausted
				for(; indexNeg >= 0; --indexNeg) {
					res[resIdx++] = arr[indexNeg] * arr[indexNeg];
				}
			}
			else if(indexNeg < 0) { // Negatives are exhausted
				for(; indexPos < arr.length; ++indexPos) {
					res[resIdx++] = arr[indexPos] * arr[indexPos];
				}
			}
			else {
				// Compare and add to result
				int negative = arr[indexNeg] * arr[indexNeg];
				int positive = arr[indexPos] * arr[indexPos];
				
				if(negative < positive) {
					res[resIdx++] = negative;
					--indexNeg;
				}
				else {
					res[resIdx++] = positive;
					++indexPos;
				}
			}
		}
		
		return res;
	}
	
}
