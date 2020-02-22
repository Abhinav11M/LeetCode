package educative.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
	
	/**
	 * Triplet Sum to Zero (medium)
	 */
	public List<List<Integer>> TripletSumToZero(int[] arr) {
		List<List<Integer>> res = new ArrayList<>();
		
		Arrays.sort(arr);
		
		for(int i = 0; i < arr.length-2; ++i) {
			// This is to handle duplicates. If we have -2, -2 at the front, we will have two same solutions.
			if(i != 0 && arr[i] == arr[i-1]) {
				continue;
			}

			int j = i+1;
			int k = arr.length-1; // Pointer from the end
			while(k > j) {
				if(arr[i] + arr[j] + arr[k] == 0) {
					res.add(Arrays.asList(new Integer[] {arr[i], arr[j], arr[k]}));
					j++;
					k--;
					
					// This is to handle duplicates, if we have 2,2 at the end, we will end up with 2 same solutions.
					// So adjust the pointers to point to different values than the previous one.
					while(j < k && arr[j] == arr[j-1]) {
						j++;
					}
					while(k > j && arr[k] == arr[k+1]) {
						k--;
					}
				}
				else if(arr[i] + arr[j] + arr[k] < 0) { // Negatives are larger, so shift j towards right
					j++;
				}
				else { // Positive is more, so shift k towards left
					k--;
				}
			}
		}
		
		return res;
	}
	
	/**
	 * Triplet Sum Close to Target (medium)
	 */
	public int tripletSumCloseToTarget(int[] arr, int targetSum) {
		Arrays.sort(arr);
		
		int difference = Integer.MAX_VALUE;
		int retTarget = 0;
		
		for(int i = 0; i < arr.length-2; ++i) {
			// Ignore duplicates
			if(i != 0 && arr[i] == arr[i-1]) {
				continue;
			}

			int left = i+1;
			int right = arr.length-1;
			
			while(left < right) {
				if(arr[i] + arr[left] + arr[right] == targetSum) {
					return targetSum;
				}
				else if(arr[i] + arr[left] + arr[right] > targetSum) {
					int tempDifference = Math.abs(targetSum - (arr[i] + arr[left] + arr[right]));
					if(tempDifference < difference) {
						difference = tempDifference;
						retTarget = arr[i] + arr[left] + arr[right];
					}
					// Try to move closer towards the sum
					--right;
				}
				else {
					int tempDifference = Math.abs(targetSum - (arr[i] + arr[left] + arr[right]));
					if(tempDifference < difference) {
						difference = tempDifference;
						retTarget = arr[i] + arr[left] + arr[right];
					}
					// Try to move closer towards the sum
					++left;
				}
			}
		}
		
		return retTarget;
	}
	
	/**
	 * Triplets with Smaller Sum
	 */
	public int tripletWithSmallerSum(int[] arr, int target) {
		int count = 0;
		Arrays.sort(arr);
		
		for(int i = 0; i < arr.length-2; ++i) {
			// Avoiding duplicates
			if(i != 0 && arr[i] == arr[i-1]) {
				continue;
			}

			int left = i+1;
			int right = arr.length-1;
			
			while(left < right) {
				if(arr[i] + arr[left] + arr[right] >= target) {
					// We need a smaller sum, so move right towards left
					--right;
					// Avoid duplicates
					while(right > left && arr[right] == arr[right+1]) {
						--right;
					}
				}
				else {
					++count;
					for(int j = right-1; j > left; --j) {
						if(arr[j] == arr[j+1]) {
							continue;
						}
						else {
							++count;
						}
					}
					
					++left;
					// Avoid duplicates
					while(left < right && arr[left] == arr[left-1]) {
						++left;
					}
				}
			}
		}
		
		return count;
	}
	
	/**
	 * Subarrays with Product Less than a Target 
	 */
	public List<List<Integer>> subarrayProductLessThanKOpt(int[] arr, int target) {
		List<List<Integer>> res = new ArrayList<>();
		
		int left = 0, product = 1;
		for(int right = 0; right < arr.length; ++right) {
			product *= arr[right];
			
			// If the product exceeds the product, move the left window towards right.
			while(product >= target) {
				product /= arr[left++];
			}
			
			// All the subsets between left and right is a required subArray.
			// To avoid duplicates, we will have to construct the subArrays from right to left.
			List<Integer> l = new LinkedList<>();
			for(int i = right; i >= left; --i) {
				l.add(0, arr[i]);
				res.add(new ArrayList<>(l));
			}
		}
		
		return res;
	}
	
	/**
	 * This is not optimized (Brute force)
	 * @param nums
	 * @param k
	 * @return
	 */
	public int subarrayProductLessThanKOpt_leetCode(int[] nums, int k) {
		int product = 1;
		int counter = 0;
		
		for(int i = 0; i < nums.length; ++i) {
			product = nums[i];
			if(product >= k) {
                product /= nums[i];
				continue;
			}
			++counter; // Just nums[i]
            
			for(int j = i+1; j < nums.length; ++j) {
				product *= nums[j];
				if(product >= k) {
                    product /= nums[j];
                    break;
				}
				++counter;
			}
		}
		
		return counter;
	}
	
	public int subarrayProductLessThanKOpt_leetCode_Opt(int[] nums, int k) {
		int left = 0;
		int product = 1;
		int counter = 0;
		
		for(int right = 0; right < nums.length; ++right) {
			product *= nums[right];
			
			while(product >= k && left <= right) {
				product /= nums[left++];
			}
			
			// If still the product is larger, move to next : When target = 0, and inputs are all positive
			if(product > k) {
				continue;
			}
			
			counter += right-left+1;
		}
		
		return counter;
	}
	
	/**
	 * Dutch National Flag Problem 
	 * Given an array containing 0s, 1s and 2s, sort the array in-place. 
	 * You should treat numbers of the array as objects,
	 * hence, we can’t count 0s, 1s, and 2s to recreate the array.
	 * Algo : Shift the 0s to left and 2s to the right, leaving 1 in the middle.
	 */
	public void sort(int[] arr) {
		int left = 0, right = arr.length-1;
		for(int i = 0; i <= right;) {
			if(arr[i] == 0) {
				// Move this to the left extreme of the array.
				swap(arr, i, left);
				++i; // We won't do i++ when we move 2 to the right extreme 
					// as we don't know the value we are swapping with. It might as be 
					// another 2 which again needs swapping to the end
				++left;
			}
			else if(arr[i] == 1) {
				++i; // Just continue as this doesn't need swapping. Will stay in the middle.
			}
			else { // arr[i] == 2
				swap(arr, i, right);
				--right; // No increment in i
			}
		}
		
	}

	private void swap(int[] arr, int i, int left) {
		int temp = arr[i];
		arr[i] = arr[left];
		arr[left] = temp;
	}
	
	
	/**
	 * Quadruple Sum to Target
	 * Given an array of unsorted numbers and a target number, 
	 * find all unique quadruplets in it, whose sum is equal to the target number.
	 */
	public List<List<Integer>> searchQuadruplets(int[] arr, int target) {
		Arrays.sort(arr);
		
		List<List<Integer>> res = new ArrayList<>();
		for(int i = 0; i < arr.length-3; ++i) {
			if(i != 0 && arr[i] == arr[i-1]) { // Ignoring duplicates
				continue;
			}
			for(int j = i+1; j < arr.length-2; ++j) {
				if(arr[j] == arr[j-1]) { // Ignoring duplicates
					continue;
				}
				int left = j+1;
				int right = arr.length-1;
				while(left < right) {
					int sum = arr[i] + arr[j] + arr[left] + arr[right];
					if(sum == target) {
						res.add(Arrays.asList(new Integer[] {arr[i] ,arr[j] ,arr[left] ,arr[right]}));
						++left;
						while(left < right && arr[left] == arr[left-1]) {
							++left;
						}
						--right;
						while(right > left && arr[right] == arr[right+1]) {
							--right;
						}
					}
					else if(sum > target) {
						--right;
					}
					else {
						++left;
					}
				}
			}
		}

		return res;
	}
	
	/**
	 * Given two strings containing backspaces (identified by the character ‘#’), check if the two strings are equal.
	 */
	public boolean backspaceCompare(String str1, String str2) {
		
		int right1 = str1.length()-1;
		int right2 = str2.length()-1;
		
		while(right1 >= 0 || right2 >= 0) {
			right1 = findNextValidIndex(str1, right1);
			right2 = findNextValidIndex(str2, right2);
			
			if(right1 < 0 && right2 < 0 ) { // both Strings exhausted
				return true;
			}
			else if(right1 < 0 || right2 < 0) { // Only one of the string exhausted
				return false;
			}
			else if(str1.charAt(right1) != str2.charAt(right2)) {
				return false;
			}
			
			--right1;
			--right2;
		}
		
		return true;
	}
	
	private int findNextValidIndex(String str, int index) {
		int backspaceCount = 0;
		while(index >= 0) {
			if(str.charAt(index) == '#') {
				++backspaceCount;
			}
			else if(backspaceCount > 0) {
				--backspaceCount;
			}
			else {
				break;
			}
			
			--index;
		}
		
		return index;
	}
	
	/**
	 * Minimum Window Sort
	 * Given an array, find the length of the smallest subarray in it which when sorted will sort the whole array.
	 * Ex : 
	 * Input: [1, 3, 2, 0, -1, 7, 10]
	 * Output: 5
	 * Explanation: We need to sort only the subarray [1, 3, 2, 0, -1] to make the whole array sorted.
	 * Algo : Here, [3, 2, 0, -1], is out of order, but the minimum of this subarray is smaller than the 
	 * 		rest of the array from the start. So, we need to extend this subarray, to add elements that 
	 * 		are bigger than -1 at the beginning. Same thing has to be done at the end as well.
	 */
	public int shortestWindowSort(int[] arr) {
		int right = arr.length-1;
		int left = 0;
		
		for(left = 0; left < arr.length-1 && arr[left] <= arr[left+1]; ++left); // Equals for duplicates
		if(left == arr.length-1) {// All sorted already
			return 0;
		}
		
		for(right = arr.length-1; right > 0 && arr[right] >= arr[right-1]; --right);
		
		// Extending sub-array
		int min = getMin(arr, left, right);
		int max = getMax(arr, left, right);
		
		
		int i = left-1;
		while(i >= 0 && arr[i] > min) {
			--i;
		}
		left = i+1;
		
		i = right+1;
		while(i < arr.length && arr[i] < max) {
			++i;
		}
		right = i-1;
		
		return right-left+1;
	}

	private int getMax(int[] arr, int left, int right) {
		int max = Integer.MIN_VALUE;
		
		for(;left <= right; ++left) {
			max = Math.max(arr[left], max);
		}
		return max;
	}

	private int getMin(int[] arr, int left, int right) {
		int min = Integer.MAX_VALUE;

		for(;left <= right; ++left) {
			min = Math.min(arr[left], min);
		}
		return min;
	}
}
