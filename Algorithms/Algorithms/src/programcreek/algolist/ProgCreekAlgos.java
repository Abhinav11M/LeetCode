package programcreek.algolist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

public class ProgCreekAlgos {

	/**
	 * Two sum input not sorted
	 */
	public int[] twoSum(int[] numbers, int target) {

		int[] result = new int[2];
		
		Map<Integer, Integer> map = new HashMap<>();
		
		for(int i = 0; i < numbers.length; ++i) {
			if(map.containsKey(numbers[i])) {
				result[0] = map.get(numbers[i]);
				result[1] = i;
				return result;
			}
			else {
				map.put(target-numbers[i], i); // key = how much more is required, val = index
			}
		}
		
		return null;
	}
	
	/**
	 * Two Sum II Input array is sorted
	 */
	public int[] twoSum2(int[] numbers, int target) {
		int left = 0, right = numbers.length-1;
		
		for(;left < right;) {
			if(numbers[left] + numbers[right] < target) {
				++left;
			}
			else if(numbers[left] + numbers[right] > target) {
				--right;
			}
			else {
				return new int[] {left, right};
			}
		}
		
		return null;
	}
	
	/** 
	 * 3Sum
	 * Given an array S of n integers, are there elements a, b, c 
	 * in S such that a + b + c = 0?
	 * Find all unique triplets in the array which gives the sum of zero.
	 */
	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		
		List<List<Integer>> result = new ArrayList<>();
		
		for(int i = 0; i < nums.length-2;) {
			int left = i+1, right = nums.length-1;
			
			while(left < right) {
				if(nums[i] + nums[left] + nums[right] < 0) {
					++left;
				}
				else if(nums[i] + nums[left] + nums[right] > 0) {
					--right;
				}
				else {
					result.add(Lists.newArrayList(nums[i], nums[left], nums[right]));
					
					while(left < nums.length-1 && nums[left] == nums[left+1]) {
						++left;
					}
					++left;

					while(right > 0 && nums[right] == nums[right-1]) {
						--right;
					}
					--right;
				}
			}
			
			while(i < nums.length-1 && nums[i] == nums[i+1]) {
				++i;
			}
			++i;
		}
		
		return result;
	}

	/**
	 * Minimum Size Subarray Sum
	 */
	public int minSubArrayLen(int target, int[] nums) {
		int sClose = Integer.MAX_VALUE;
		int sum = 0;
		int left = 0, right = 0;
		int indexDiff = Integer.MAX_VALUE;
		
		if(nums == null || nums.length == 0) {
			return 0;
		}
		
		while(right < nums.length && left < nums.length) {
			if(target <= sum) {
				if(sum <= sClose) {
					sClose = sum;
					indexDiff = Math.min(indexDiff, right-left);
				}
				sum -= nums[left++];
			}
			else {
				sum += nums[right];
				right++;
			}
		}
		
		while(sum >= target && left < nums.length) {
			if(sum <= sClose) {
				indexDiff = Math.min(indexDiff, (right-left));
			}
			sum -= nums[left++];
		}
		
		return indexDiff == Integer.MAX_VALUE ? 0 : indexDiff;
	}
	
	/**
	 * Remove duplicates from sorted array
	 */
	public int removeDuplicates(int[] nums) {
		if(nums.length == 0 || nums.length == 1) {
			return nums.length;
		}
		
		int i = 1;
		int insIdx = i;
		int prev = nums[0];
		while(i < nums.length) {
			while(i < nums.length && nums[i] == prev) {
				++i;
			}
			if(i == nums.length) {
				break;
			}
			nums[insIdx++] = nums[i];
			prev = nums[i++];
		}

		return insIdx;
	}
	
	/**
	 * Remove Duplicates from Sorted Array II
	 * Duplicates are allowed atmost twice
	 */
	public int removeDuplicates2(int[] nums) {
		if(nums.length <= 2) {
			return nums.length;
		}
		
		int prev = nums[0];
		int i = 0;
		int insIdx = 0;
		
		while(i < nums.length) {
			int count = 0;
			while(i < nums.length && nums[i] == prev) {
				++count;
				++i;
			}
			
			for(int j = 0; j < Math.min(count, 2); ++j) {
				nums[insIdx++] = prev;
			}
			
			if(i == nums.length) {
				break;
			}
			
			prev = nums[i];
		}
		
		return insIdx;
	}
	
	/**
	 * Remove Element
	 */
	public int removeElement(int[] nums, int elem) {
		// Find the first occurrence
		int i = 0;
		for(; i < nums.length && nums[i] != elem; ++i);

		if(i == nums.length) {
			return i;
		}
		
		int insIdx = i;
		
		for(; i < nums.length;) {
			if(nums[i] == elem) {
				++i;
			}
			else {
				nums[insIdx++] = nums[i++];
			}
		}
		
		return insIdx;
	}
	
	/**
	 * Move Zeros
	 */
	public void moveZeroes(int[] nums) {
		// Find the first occurrence of zero
		int i = 0;
		for(; i < nums.length && nums[i] != 0; ++i);
		int insIdx = i;
		
		if(i == nums.length) {
			return;
		}
		
		for(; i < nums.length;) {
			if(nums[i] != 0) {
				// swap
				nums[insIdx++] = nums[i];
				nums[i] = 0;
			}
			else {
				++i;
			}
		}
	}
	
	/**
	 * Container with most water
	 */
	public int maxArea(int[] height) {
		if(height.length == 0) {
			return 0;
		}
		if(height.length == 1) {
			return height[0];
		}
		
		int left = 0;
		int right = height.length-1;
		
		int max = Math.min(height[left], height[right]) * (right-left);
		
		while(left < right) {
			if(height[left] > height[right]) {
				right--;
			}
			else if(height[left] < height[right]) {
				left++;
			}
			else {
				left++;
				right--;
			}
			
			int newArea = Math.min(height[left], height[right]) * (right-left);
			max = Math.max(max, newArea);
		}
		
		return max;
	}
	
	/**
	 * Candy Problem
	 */
	public int candy(int[] ratings) {
		int[] candies = new int[ratings.length];
		
		candies[0] = 1;
		
		for(int i = 1; i < ratings.length; ++i) {
			if(ratings[i] > ratings[i-1]) {
				candies[i] = candies[i-1] + 1;
			}
			else {
				candies[i] = 1; // giving minimum 1 candy
			}
		}

		// Only left neighbour for the last index
		int result = candies[candies.length-1]; 
		
		for(int i = ratings.length-2; i >= 0; --i) {
			int currCandy = 1;
			if(ratings[i] > ratings[i+1]) {
				currCandy = candies[i+1]+1;
			}
			
			result += Math.max(currCandy, candies[i]);
			candies[i] = currCandy;
		}
		
		return result;
	}
	
	/**
	 * Trapping Rain Water
	 */
	public int trappingWater(int[] height) {
		
		int[] left = new int[height.length];
		int[] right = new int[height.length];
		
		int max = height[0];
		left[0] = max;
		
		for(int i = 1; i < height.length; ++i) {
			if(height[i] > max) {
				max = height[i];
			}
			left[i] = max;
		}

		max = height[height.length-1];
		right[height.length-1] = max;

		for(int i = height.length-2; i >=0 ; --i) {
			if(height[i] > max) {
				max = height[i];
			}
			right[i] = max;
		}
		
		//Summation (min(l[i], r[i]) - h[i])
		int result = 0;
		for(int i = 0; i < height.length; ++i) {
			result += Math.min(left[i], right[i]) - height[i];
		}
		
		return result;
	}
	
	/**
	 * Summary Ranges
	 * [0,1,2,4,5,7], return ["0->2","4->5","7"]
	 */
	public List<String> summaryRanges(int[] nums) {
		
		List<String> result = new ArrayList<>();
		
		int start = nums[0];
		int end = nums[0];
		
		for(int i = 1; i < nums.length; ++i) {
			if(nums[i]-nums[i-1] == 1) {
				end++;
			}
			else {
				String str = "";
				if(start == end) {
					str = "\"" + start + "\"";
				}
				else {
					str = String.format("\"%d->%d\"", start, end);
				}
				result.add(str);
				start = nums[i];
				end = nums[i];
			}
		}
		
		if(start == end) {
			result.add("\"" + start + "\"");
		}
		else {
			result.add(String.format("\"%d->%d\"", start, end));
		}
		
		return result;
	}
	
	/**
	 * Check if edit distance between two strings is one
	 * An edit between two strings is one of the following changes. 
	 * 
	 * Add a character
	 * Delete a character
	 * Change a character
	 */
	public boolean isOneEditDistance(String s, String t) {
		
		int m = s.length();
		int n = t.length();

		if(Math.abs(m-n) > 1) {
			return false;
		}
		
		int i = 0;
		int j = 0;
		int editDiff = 0;
		
		while(i < m && j < n) {
			if(s.charAt(i) != t.charAt(j)) {
				if(editDiff == 1) {
					return false;
				}
				
				if(m > n) {
					++i;
					// Delete 1 from s
				}
				else if(m < n) {
					// Insert 1 into s
					++j;
				}
				else {
					// Replace char in t into s
					++i;
					++j;
				}
				++editDiff;
			}
			else {
				++i;
				++j;
			}
		}
		
		// If the string are 1 char different in length, we need to check the last char
		if(i < m || j < n) {
			++editDiff;
		}
		
		return editDiff == 1;
	}
	
	/**
	 * Given two strings str1 and str2 and below operations that can performed on str1. 
	 * Find minimum number of edits (operations) required to convert ‘str1’ into ‘str2’.
	 * Insert
	 * Remove
	 * Replace
	 * All of the above operations are of equal cost.
	 */
	public int editDistance(String str1, String str2) {
		return editDistance(str1, str2, str1.length()-1, str2.length()-1, new Integer[str1.length()][str2.length()]);
	}

	private int editDistance(String str1, String str2, int idx1, int idx2, Integer[][] dp) {
		if(idx1 == -1 && idx2 == -1) {
			return 0;
		}
		
		if(idx1 == -1) {
			return idx2+1;
		}
		
		if(idx2 == -1) {
			return idx1+1;
		}
		
		if(dp[idx1][idx2] != null) {
			return dp[idx1][idx2];
		}
		
		if(str1.charAt(idx1) == str2.charAt(idx2)) {
			return editDistance(str1, str2, idx1-1, idx2-1, dp);
		}
		
		return 1+minimum(editDistance(str1, str2, idx1, idx2-1, dp), // Insert
						editDistance(str1, str2, idx1-1, idx2, dp), // Delete
						editDistance(str1, str2, idx1-1, idx2-1, dp)); // Replace
	}
	
	private int minimum(int a, int b, int c) {
		return Math.min(Math.min(a, b), c);
	}
	
	// Convert str1 to str2
	public int editDistanceDP(String str1, String str2) {
		
		int m = str1.length();
		int n = str2.length();
		
		int[][] dp = new int[m+1][n+1];
		// Cols is str1; rows are str2
		
		// Converting an empty string to a str of length i will take i insertions
		for(int i = 0; i <= n; ++i) {
			dp[0][i] = i;
		}
		
		// Converting str of length i to empty string will take i deletions
		for(int i = 0; i <= m; ++i) {
			dp[i][0] = i;
		}
		
		// i -> col(str1); j -> row(str2)
		// Replacement => dp[i][j] => Transform => dp[i-1][j-1]
		// Deletion => dp[i][j] => Transform => dp[i-1][j]
		// insertion => dp[i][j] => Transform => dp[i][j-1]
		
		for(int i = 1; i <= m; ++i) {
			for(int j = 1; j <= n; ++j) {
				if(str1.charAt(i-1) == str2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1];
				}
				else {
					dp[i][j] = 1+Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1]);
				}
			}
		}
		
		return dp[m][n];
	}
	
	/**
	 * Merge sorted array
	 * Given two sorted integer arrays A and B, merge B into A as one sorted array.
	 * Note: You may assume that A has enough space to hold additional elements from
	 * B. The number of elements initialized in A and B are m and n respectively
	 */
	public void mergeSortedArrays(int[] nums1, int m, int[] nums2, int n) {
		int i = m-1;
		int j = n-1;
		int idx = m+n-1;
		
		while(i >= 0 && j >= 0) {
			if(nums1[i] > nums2[j]) {
				nums1[idx--] = nums1[i--];
			}
			else if(nums1[i] < nums2[j]) {
				nums1[idx--] = nums2[j--];
			}
			else {
				nums1[idx--] = nums1[i--];
				nums1[idx--] = nums2[j--];
			}
		}
		
		// Fill out the non exhausted elements
		while(j >= 0) {
			nums1[idx--] = nums2[j--];
		}
	}
	
	/**
	 * Median of two sorted arrays
	 */
	public double medianOfSortedArrays(int[] nums1, int[] nums2) {
		if(nums1.length == 0) {
			return nums2.length%2 == 0 ? (nums2[(nums2.length/2)-1] + nums2[nums2.length/2])/2.0 : nums2[nums2.length/2];
		}
		if(nums2.length == 0) {
			return nums1.length%2 == 0 ? (nums1[(nums1.length/2)-1] + nums1[nums1.length/2])/2.0 : nums1[nums1.length/2];
		}
		
		int l1 = 0, l2 = 0;
		int r1 = nums1.length-1, r2 = nums2.length-1;
		
		// Until both of length 2 or less
		while((r1-l1) > 1 && (r2-l2) > 1) {
			double m1 = getMedian(nums1, l1, r1);
			double m2 = getMedian(nums2, l2, r2);
			
			if(m1 == m2) {
				return m1; // median found
			}
			else if(m1 > m2) { // take the left half of nums1 and right half of nums2
				r1 = (l1+r1)/2;
				l2 = (l2+r2)/2;
			}
			else { // take the right half of nums1 and left half of nums2
				l1 = (l1+r1)/2;
				r2 = (l2+r2)/2;
			}
		}
		
		return (Math.max(nums1[l1], nums2[l2]) + Math.min(nums1[r1], nums2[r2]))/2.0;
	}
	
	private double getMedian(int[] nums, int l, int r) {
		double m = -1.0;
		if((r-l) %2 == 0) {
			m = nums[(r+l)/2];
		}
		else {
			m = (nums[(r+l)/2] + nums[(r+l+1)/2])/2;
		}
		
		return m;
	}
	
}
