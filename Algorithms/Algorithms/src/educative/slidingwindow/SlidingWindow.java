package educative.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class SlidingWindow {
	/**
	 * Given an array, find the average of all contiguous subarrays of size ‘K’ in it.
	 * @param k
	 * @param arr
	 * @return
	 */
	public double[] findAveragesOfSubarrayOfSizeK(int k, int[] arr) {
		int startIdx = 0;
		int endIdx = 0;
		int sum = 0;
		double[] res = new double[arr.length-k+1];
		
		for(endIdx = 0; endIdx < arr.length; ++endIdx) {
			sum += arr[endIdx];
			
			if(endIdx >= k-1) {
				res[startIdx] = sum/(double)k;
				// Subtract the front
				sum -= arr[startIdx++];
			}
		}
		
		return res;
	}
	
	/**
	 * Given an array of positive numbers and a positive number ‘k’, 
	 * find the maximum sum of any contiguous subarray of size ‘k’.
	 */
	public int findMaxSumSubArray(int k, int[] arr) {
		int startIdx = 0;
		int endIdx = 0;
		int max = Integer.MIN_VALUE;
		int sum = 0;
		
		for(endIdx = 0; endIdx < arr.length; ++endIdx) {
			sum += arr[endIdx];
			
			if(endIdx >= k-1) {
				if(sum > max) {
					max = sum;
				}
				sum -= arr[startIdx++];
			}
		}
		
		return max;
	}

	/**
	 * Smallest Subarray with a given sum
	 */
	public int findMinSubArray(int s, int[] arr) {
		int startIdx = 0;
		int endIdx = 0;
		int sum = 0;
		int subsetSize = Integer.MAX_VALUE;
		
		for(endIdx = 0; endIdx < arr.length; ++endIdx) {
			sum += arr[endIdx];
			
			if(sum >= s) {
				while(sum >= s) {
					sum -= arr[startIdx++];
				}
				
				int newSubsetLen = endIdx - (startIdx-1) + 1;
				
				if(newSubsetLen < subsetSize) {
					subsetSize = newSubsetLen;
				}
			}
			
			
			
		}
		
		return subsetSize;
	}
	
	/**
	 * Given a string, find the length of the longest substring in it with no more than K distinct characters.
	 */
	public int LongestSubstringKDistinct(String str, int k) {
		int startIdx = 0;
		int endIdx = 0;
		int maxWindow = 0;
		
		Map<Character, Boolean> map = new HashMap<>();
		int distinctLetters = 0;
		
		for(;endIdx < str.length(); ++endIdx) {
			if(!map.containsKey(str.charAt(endIdx)) || map.get(str.charAt(endIdx)) == false) {
				map.put(str.charAt(endIdx), true);
				++distinctLetters;
			}
			if(distinctLetters > k) {
				int newWindow = endIdx - startIdx;
				maxWindow = maxWindow > newWindow ? maxWindow : newWindow;
				// Move start so that it point to a different char than the first char
				char charToFind = str.charAt(startIdx);
				int i = endIdx-1;
				// Move back to find the new start index
				// The new start index will be the last max index of str[startIdx] before endIdx.
				for(; i >= 0 && str.charAt(i) != charToFind; --i);
				startIdx = i+1;
				distinctLetters--;
				// Unset that character in the map
				map.put(charToFind, false);
			}
		}
		
		return maxWindow;
	}
}
