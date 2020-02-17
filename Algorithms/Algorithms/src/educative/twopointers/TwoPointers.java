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
}
