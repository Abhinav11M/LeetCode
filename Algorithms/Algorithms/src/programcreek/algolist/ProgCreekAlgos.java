package programcreek.algolist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import datastructures.ListNode;
import lombok.AllArgsConstructor;

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
	 * Find minimum number of edits (operations) required to convert �str1� into �str2�.
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
	
	/**
	 * Find Minimum in Rotated Sorted Array
	 */
	public int findMin(int[] nums) {
		
		if(nums.length == 1) {
			return nums[0];
		}
		
		int left = 0, right = nums.length-1;
		
		while(left <= right) {
			if(nums[left] <= nums[right]) { // Equal because they can come to the same index that might be min
				return nums[left];
			}
			
			int mid = (left+right)/2;
			// Check skewness
			if(nums[mid] < nums[left]) { // left skewed
				// Move to the right half
				right = mid;
			}
			else {// (nums[mid] > nums[right]) // right skewed
				left = mid + 1;
			}
		}
		
		return -1;
    }
	
	/**
	 * Find Minimum in Rotated Sorted Array (With duplicates)
	 */
	public int findMinWithDup(int[] nums) {
		
		if(nums.length == 1) {
			return nums[0];
		}
		
		int left = 0, right = nums.length-1;
		
		while(left <= right) {
			if(nums[left] < nums[right]) {
				return nums[left];
			}
			
			if(nums[left] == nums[right]) {
				++left;
				continue;
			}
			
			int mid = (left+right)/2;
			// Check skewness
			if(nums[mid] < nums[left]) { // right skewed
				// Move to the right half
				right = mid;
			}
			else { // if(nums[mid] > nums[right]) { // left skewed
				left = mid + 1;
			}
			
		}
		
        return nums[right];
    }
	
    public int[] searchRange(int[] nums, int target) {
    	
    	if(nums.length == 1) {
    		return nums[0] == target ? new int[] {0,0} : new int[] {-1,-1};
    	}
    	
    	int left = 0, right = nums.length-1;
    	
    	while(left <= right) {
    		int mid = (left+right)/2;
    		
    		if(nums[mid] == target) {
    			while(nums[left] != nums[mid]) {
    				++left;
    			}
    			
    			while(nums[right] != nums[mid]) {
    				--right;
    			}
    			
    			return new int[] {left, right};
    		}
    		else if(nums[mid] > target) {
    			right = mid-1;
    		}
    		else {
    			left = mid+1;
    		}
    	}
    	
    	return new int[] {-1, -1};
    }
    
    /**
     * Search in rotated sorted array
     */
    public int searchInRotatedSortedArray(int[] nums, int target) {
    	if(nums.length == 1) {
    		return nums[0] == target ? 0 : -1;
    	}

    	int left = 0, right = nums.length-1;
    	
    	while(left <= right) {
    		int mid = (left+right)/2;
    		
    		if(nums[mid] == target) {
    			return mid;
    		}
    		
    		if(nums[left] > nums[mid]) { // left skewed, right sorted
    			// if the target is greater than mid, it can be in either half.
    			// if the target is less than mid, it has to be in the left half.
    			
    			if(target < nums[mid]) {
    				right = mid-1;
    			}
    			else {
    				if(target > nums[right]) {
    					right = mid-1;
    				}
    				else {
    					left = mid+1;
    				}
    			}
    		}
    		else if(nums[right] < nums[mid]) { // right skewed, left sorted
    			// If the target is less than mid, it can be in either halves.
    			// But if the target is greater than mid, it has to be in the right half.
    			if(target > nums[mid]) {
    				left = mid+1;
    			}
    			else {
    				if(target < nums[left]) {
    					left = mid+1;
    				}
    				else {
    					right = mid-1;
    				}
    			}
    		}
    		else { // no skew
    			if(nums[mid] > target) {
    				right = mid-1;
    			}
    			else {
    				left = mid+1;
    			}
    		}
    	}
    	
    	return -1;
    }

    public int searchInRotatedSortedArrayWithDup(int[] nums, int target) {
    	if(nums.length == 1) {
    		return nums[0] == target ? 0 : -1;
    	}

    	int left = 0, right = nums.length-1;
    	
    	while(left <= right) {
    		int mid = (left+right)/2;
    		
    		if(nums[mid] == target) {
    			return mid;
    		}
    		
    		if(nums[mid] == nums[left]) {
    			++left;
    			continue;
    		}
    		
    		if(nums[left] > nums[mid]) { // left skewed, right sorted
    			// if the target is greater than mid, it can be in either half.
    			// if the target is less than mid, it has to be in the left half.
    			
    			if(target < nums[mid]) {
    				right = mid-1;
    			}
    			else {
    				if(target > nums[right]) {
    					right = mid-1;
    				}
    				else {
    					left = mid+1;
    				}
    			}
    		}
    		else if(nums[right] < nums[mid]) { // right skewed, left sorted
    			// If the target is less than mid, it can be in either halves.
    			// But if the target is greater than mid, it has to be in the right half.
    			if(target > nums[mid]) {
    				left = mid+1;
    			}
    			else {
    				if(target < nums[left]) {
    					left = mid+1;
    				}
    				else {
    					right = mid-1;
    				}
    			}
    		}
    		else { // no skew
    			if(nums[mid] > target) {
    				right = mid-1;
    			}
    			else {
    				left = mid+1;
    			}
    		}
    	}
    	
    	return -1;
    }
    
    /**
     * Reverse Polish Notation
     */
    public int evalRPN(String[] tokens) {
        
        Stack<Integer> stack = new Stack<>();
    	for(String token : tokens) {
    		if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
    			int operand1 = stack.pop();
    			int operand2 = stack.pop();
    			stack.push(evaluate(operand1, operand2, token));
    		}
    		else {
    			stack.push(Integer.parseInt(token));
    		}
    	}
    	return stack.pop();
    }
    
    private int evaluate(int operand1, int operand2, String operator) {
    	switch (operator) {
		case "+":
			return operand2 + operand1;
		case "-":
			return operand2 - operand1;
		case "*":
			return operand2 * operand1;
		case "/":
			return operand2 / operand1;
		default:
			return -1;
		}
    }
    
    /**
     * Longest valid parentheses
     */
    public int longestValidParentheses(String s) {
        
        Stack<Integer[]> stack = new Stack<>();
        int maxLength = 0;
        
        // Using 0 for '(' and 1 for ')'
        for(int i = 0; i < s.length(); ++i) {
        	char ch = s.charAt(i);
        	if(ch == '(') {
        		// (push index,0)
        		stack.push(new Integer[] {i, 0});
        	}
        	else {
        		// No '(' to be popped from the stack
        		if(stack.isEmpty() || stack.peek()[1] == 1) {
        			stack.push(new Integer[] {i, 1});
        		}
        		else {
        			stack.pop(); // pop the balancing parenthesis
        			int currLength = 0;
        			if(stack.isEmpty()) {
        				currLength = i+1; // All the parenthesis are consumed/balanced
        			}
        			else {
        				currLength = i-stack.peek()[0];
        			}
        			
        			maxLength = Math.max(maxLength, currLength);
        		}
        	}
        }
        
        return maxLength;
    }
    
    public int largestRectangleArea(int[] heights) {
    	int i = 0;
    	int max = 0;
    	
    	Stack<Integer> stack = new Stack<>();
    	
    	while(i < heights.length) {
    		if(stack.isEmpty() || heights[i] >= heights[stack.peek()]) {
    			stack.push(i++);
    		}
    		else { // Reached the end of increasing values. So the last histogram has come to an end
    			int top = stack.pop();
    			int l = heights[top];
    			
    			int w = stack.empty() ? i : i-1-stack.peek();
    			
    			max = Math.max(max, l*w);
    		}
    	}
    	
    	while(!stack.isEmpty()) {
    		int top = stack.pop();
    		int l = heights[top];
    		int w = stack.empty() ? i : i-1-stack.peek();
    		
    		max = Math.max(max, l*w);
    	}
    	
    	return max;
    }
    
	public int largestRectangleArea2(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
		Stack<Integer> stack = new Stack<Integer>();
		int max = 0;
		int i = 0;
		while (i < height.length) {
			// push index to stack when the current height is larger than the previous
			if (stack.isEmpty() || height[i] >= height[stack.peek()]) {
				stack.push(i);
				i++;
			} else {
				// calculate max value when the current height is less than the previous
				int p = stack.pop();
				int h = height[p];
				int w = stack.isEmpty() ? i : i - stack.peek() - 1;
				max = Math.max(h * w, max);
			}
		}
		while (!stack.isEmpty()) {
			int p = stack.pop();
			int h = height[p];
			int w = stack.isEmpty() ? i : i - stack.peek() - 1;
			max = Math.max(h * w, max);
		}
		return max;
	}
	
	/**
	 * Broken Calculator : Still incomplete
	 */
	 public int brokenCalc(int X, int Y) {
	   
		 int count = 0;
		 while(X < Y/2) {
			 ++count;
			 X *= 2;
		 }
		 
		 return brokenCalc(X, Y, count);
	     
	 }

	 private int brokenCalc(int x, int y, int count) {
		 if(x == y) {
			 return count;
		 }
		 
		 int val1 = Integer.MAX_VALUE;
		 // Bit overflow check
		 if(x <= Integer.MAX_VALUE/2) {
			 val1 = Math.abs(x*2-y);
		 }
		 
		 int val2 = Math.abs((x-1)*2-y);
		 
		 if(val1 > val2) {
			 return brokenCalc(x-1, y, count+1);
		 }
		 else {
			 return brokenCalc(x*2, y, count+1);
		 }
	 }
	 
	public boolean isAnagram(String s1, String s2) {

		if(s1.length() != s2.length()) {
			return false;
		}
		
		int[] count = new int[256];

		for (int i = 0; i < s1.length(); ++i) {
			++count[s1.charAt(i)];
			--count[s2.charAt(i)];
		}
		
		for(int i = 0; i < count.length; ++i) {
			if(count[i] != 0) {
				return false;
			}
		}

		return true;
	}
	
	/**
	 * Palindrome Pairs
	 */
	/*public List<List<Integer>> palindromePairs(String[] words) {
		boolean[] isPalindrome = new boolean[words.length];
		List<List<Integer>> pairs = new ArrayList<>();

		for(int i = 0; i < words.length; ++i) {
			if(isPalindrome[i] == true) {
				continue;
			}
			
			String word1 = words[i];
			for(int j = i+1; j < words.length; ++j) {
				String word2 = words[j];
				
				int i1 = 0;
				int i2 = word2.length()-1;
				while(i1 < word1.length() && i2 >= 0) {
					if(word1.charAt(i1) != word2.charAt(i2)) {
						break;
					}
					else {
						++i1;
						--i2;
					}
				}
				
				if(i1 == word1.length() && i2 == -1) {
					pairs.add(Arrays.asList(new Integer[] {i,j}));
					pairs.add(Arrays.asList(new Integer[] {j,i}));
					continue;
				}
				
				// If one of the words are exhausted.
				// word1 exhausted but word2 has not
				if(i1 == word1.length() && i2 != -1) {
					i1 = 0;
					while(i1 <= i2) {
						if(word2.charAt(i1) != word2.charAt(i2)) {
							break;
						}
						else {
							++i1;
							--i2;
						}
					}
				}
				
				// word2 exhausted
				if(i1 != word1.length() && i2 == -1) {
					i2 = word1.length();
					while(i1 <= i2) {
						if(word1.charAt(i1) != word1.charAt(i2)) {
							break;
						}
						else {
							++i1;
							--i2;
						}
					}
				}
				
				if(i1 > i2) {
					pairs.add(Arrays.asList(new Integer[] {i,j}));
					isPalindrome[j] = true;
				}
			}
		}
		
        return null;
    }*/
	
	/**
	 * Longest substring without repeating characters.
	 */
	public int lengthOfLongestSubstring(String s) {

		int count = 0;
		int max = 0;
		
		Map<Character, Integer> index = new HashMap<>();
		
		for(int i = 0; i < s.length(); ++i) {
			char ch = s.charAt(i);
			++count;

			if(index.containsKey(ch)) {
				if(i-index.get(ch) < count) {
					count = i-index.get(ch);
				}
				index.put(ch, i);
			}
			else {
				index.put(ch, i);
			}
			max = Math.max(max, count);
		}
		
		return max;
	}
	
	/**
	 * Majority Element
	 */
	public int majorityElement(int[] nums) {
		Map<Integer, Integer> count = new HashMap<>();

		for(int val : nums) {
			count.put(val, count.getOrDefault(val, 0)+1);
			if(count.get(val) > nums.length/2) {
				return val;
			}
		}
		
		return 0;
	}

	public int majorityElementOpt(int[] nums) {
		int element = nums[0];
		int count = 1;
		
		for(int i = 1; i < nums.length; ++i) {
			if(nums[i] == element) {
				++count;
			}
			else {
				--count;
			}
			if(count == 0) {
				element = nums[i];
				count = 1;
			}
			
		}
		
		return element;
	}
	
	/**
	 * Majority Element II
	 */
	public List<Integer> majorityElement2(int[] nums) {

		if(nums.length == 0) {
            return new ArrayList<Integer>();
        }	
		
        int e1 = nums[0];
        int c1 = 0;
        int e2 = nums[0];
        int c2 = 0;
        
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < nums.length; ++i) {
        	if(c1 == 0) {
        		e1 = nums[i];
        		c1 = 1;
        	}
        	else if(c2 == 0 && nums[i] != e1) {
        		e2 = nums[i];
        	}
        	
        	if(nums[i] == e1) {
        		++c1;
        	}
        	else if(nums[i] == e2) {
        		++c2;
        	}
        	else {
        		--c1;
        		--c2;
        	}
        }
        
        c1 = 0;
        c2 = 0;
        for(int i = 0; i < nums.length; ++i) {
        	if(nums[i] == e1) {
        		++c1;
        	}
        	else if(nums[i] == e2) {
        		++c2;
        	}
        }
        
        if(c1 > nums.length/3) {
        	res.add(e1);
        }
        if(c2 > nums.length/3) {
        	res.add(e2);
        }
        
        return res;
    }
	
	/**
	 * Increasing Triplet Subsequence
	 */
	public boolean increasingTriplet(int[] nums) {
		
		int x = Integer.MAX_VALUE;
		int y = Integer.MAX_VALUE;
		
		for(int i = 0; i < nums.length; ++i) {
			if(nums[i] <= x) {
				x = nums[i];
			}
			else if(nums[i] <= y) {
				y = nums[i];
			}
			else {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Rotate array
	 */
	public void rotate(int[] nums, int k) {
		
		int n = nums.length;
		k = k%n;
		
		// reverse 0 to n-k-1
		int l = 0;
		int r = n-k-1;
		while(l < r) {
			// Swap
			swap(l, r, nums);
			l++;
			r--;
		}
		
		//reverse n-k to n-1
		l = n-k;
		r = n-1;
		while(l < r) {
			swap(l, r, nums);
			l++;
			r--;
		}
		
		// Reverse the entire array
		l = 0;
		r = n-1;
		while(l < r) {
			swap(l, r, nums);
			l++;
			r--;
		}
		
//		k = k%nums.length;
//		
//		if(k == 0) {
//			return;
//		}
//		
//		int curr = 0;
//		int next = k;
//		
//		while(curr != next) {
//			//swap
//			int temp = nums[curr];
//			nums[curr] = nums[next];
//			nums[next] = temp;
//			
//			next = (next+k)%nums.length;
//		}
	}

	private void swap(int l, int r, int[] nums) { 
		int temp = nums[l];
		nums[l] = nums[r];
		nums[r] = temp;
	}
	
	public String reverseWords(String s) {
		if(s.isEmpty()) {
			return s;
		}
		
		StringBuilder str = new StringBuilder();
		List<String> words = new LinkedList<>();
		for(char ch : s.toCharArray()) {
			if(ch == ' ' && str.length() != 0) {
				// End of word
				words.add(0, str.toString());
				str = new StringBuilder();
			}
			else if(ch == ' '  && str.length() == 0) {
				continue;
			}
			else {
				str.append(ch);
			}
		}
		if(str.length() != 0) {
			words.add(0, str.toString());
		}
		
		StringBuilder res = new StringBuilder();
		for(String word : words) {
			res.append(word).append(" ");
		}
		
		return res.length() == 0 ? "" : res.toString().substring(0, res.length()-1);
	}

	/**
	 * Reverse words in a string [In-place reversal]
	 */
	public void reverseWords(char[] s) {
		if(s.length == 0) {
			return;
		}
		
		int i = 0;
		for(int j = 0; j < s.length; ++j) {
			if(s[j] == ' ') {
				// Reverse the word
				reverse(i,j-1,s);
				i = j+1; // point to the start of the next word
			}
		}
		
		// reverse the last word
		reverse(i, s.length-1, s);
		
		// reverse the entire array
		reverse(0, s.length-1, s);
	}

	private void reverse(int l, int r, char[] s) {
		while(l < r) {
			char temp = s[l];
			s[l] = s[r];
			s[r] = temp;
			++l;
			--r;
		}
	}
	
	/**
	 * Group anagrams
	 */
	public List<List<String>> groupAnagrams(String[] strs) {

		List<List<String>> res = new ArrayList<>();
		boolean[] isVisited = new boolean[strs.length];
		
		for(int i = 0; i < strs.length; ++i) {
			if(isVisited[i]) {
				continue;
			}
			
			List<String> list = new ArrayList<>();
			list.add(strs[i]);
			isVisited[i] = true;
			
			for(int j = i + 1; j < strs.length; ++j) {
				if(isVisited[j]) {
					continue;
				}
				
				if(isAnagram(strs[i], strs[j])) {
					list.add(strs[j]);
					isVisited[j] = true;
				}
			}
			
			res.add(list);
		}
		
		return res;
	}
	
	/**
	 * Group anagrams Optimized
	 */
	public List<List<String>> groupAnagramsOpt(String[] strs) {
		List<List<String>> res = new ArrayList<>();
		
		Map<String, List<String>> map = new HashMap<>();
		
		for(String str : strs) {
			char[] arr = new char[26];
			
			// We need to create a hash of this String
			for(int i = 0; i < str.length(); ++i) {
				arr[str.charAt(i) - 'a']++; // This will obviously create a matrix with junk chars (Based on ascii values 0-25)
			}
			
			String hash = new String(arr);
			map.computeIfAbsent(hash, x -> new ArrayList<>()).add(str);
		}
		
		
		res.addAll(map.values());
		return res;
	}
	
	/**
	 * Wildcard Matching
	 */
	public boolean isMatch(String s, String p) {
		int i = 0; // s index
		int j = 0; // p index
		int starIndex = -1; // track index of last '*'
		int iIndex = -1; // track index till where * was able to match
		
		while(i < s.length()) {
			if(j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
				++i;
				++j;
			}
			else if( j < p.length() && p.charAt(j) == '*') {
				starIndex = j;
				iIndex = i;
				++j;
			}
			else if(starIndex != -1) {
				// Reposition the pointer till where we were able to match with star
				i = iIndex+1;
				j = starIndex + 1;
				++iIndex;
			}
			else {
				return false;
			}
		}
		
		// When s gets exhausted and p is not
		while(j < p.length() && p.charAt(j) == '*') {
			++j;
		}
		
		return j == p.length();
    }
	
	/**
	 * Wildcard Matching
	 */
	public boolean isMatchDP(String s, String p) {
		boolean[][] dp = new boolean[s.length()+1][p.length()+1];
		dp[0][0] = true;
		
		for(int i = 1; i <= p.length() && p.charAt(i-1) == '*'; ++i) {
			dp[0][i] = true;
		}

		for(int i = 1; i <= s.length(); ++i) {
			for(int j = 1; j <= p.length(); ++j) {
				if((s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?') && dp[i-1][j-1]) {
					dp[i][j] = true;
				}
				else if(p.charAt(j-1) == '*') {
					// Either disregard or use *
					if(dp[i][j-1] || dp[i-1][j]) {
						dp[i][j] = true;
					}
				}
				else { // Not needed as boolean has false default value.
					dp[i][j] = false;
				}
			}
		}
		
		return dp[s.length()][p.length()];
	}

	/**
	 * Get Target Number Using Number List and Arithmetic Operations
	 */
	public boolean isReachable(List<Integer> list, int target) {
		
		if(list == null || list.isEmpty()) {
			return false;
		}
		
		List<Integer> result = isReachable(list, target, 0, list.size()-1);
		
		for(int val : result) {
			if(val == target) {
				return true;
			}
		}
		
		return false;
	}

	private List<Integer> isReachable(List<Integer> list, int target, int left, int right) {
		
		List<Integer> result = new ArrayList<>();
		
		
		
		if(left > right) {
			return result;
		}
		if(left == right) { // Nothing to partition anymore
			result.add(list.get(left));
			return result;
		}
		
		// Partition and apply all the operations
		for(int i = left; i < right; ++i) {
			List<Integer> res1 = isReachable(list, target, left, i);
			List<Integer> res2 = isReachable(list, target, i+1, right);
			
			for(int val1 : res1) {
				for(int val2 : res2) {
					result.add(val1+val2);
					result.add(val1-val2);
					result.add(val1*val2);
					if(val2 != 0) {
						result.add(val1/val2);
					}
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Flip Game
	 */
	public List<String> flipGame(String s) {
		List<String> moves = new ArrayList<>();
		
		int i = 0;
		int start = 0;
		int c = 0;
		while(i < s.length()) {
			if(s.charAt(i) == '+') {
				if(c == 0) {
					start = i;
				}
				++c;
			}
			else {
				c = 0;
				start = i;
			}
			if(c == 2) { // found 2 consecutive ++
				moves.add(s.substring(0, start) + "--" + s.substring(start+2));
				c = c-1;
				++start;
			}
			++i;
		}
		
		return moves;
	}
	
	// Method 2
	public List<String> flipGameMtd2(String s) {
		List<String> moves = new ArrayList<String>();
		
		for(int i = 0; i < s.length()-1; ++i) {
			if(s.charAt(i) == '+' && s.charAt(i+1) == '+') {
				moves.add(s.substring(0, i) + "--" + s.substring(i+2));
			}
		}
		
		return moves;
	}

	public List<String> generatePossibleNextMoves(String s) {
		List<String> moves = new ArrayList<>();
		
		int i = 0;
		while(i < s.length()-1) {
			int findIdx = s.indexOf("++", i);
			
			if(findIdx == -1) {
				return moves;
			}
			
			String move = s.substring(0, findIdx) + "--" + s.substring(findIdx+2);
			moves.add(move);
			i = findIdx + 1;
		}
		
		return moves;
	}
	
	/**
	 * Flip Game II
	 */
	boolean canWin(String s) {
		return canWin(s, false);
	}

	private boolean canWin(String s, boolean canWin) {
		// Find the next possible move
		List<String> nextMoves = generatePossibleNextMoves(s);
		
		if(nextMoves.isEmpty()) { // no possible moves for the 1st player
			if(!canWin) {
				return false;
			}
			else {
				return true;
			}
		}
		
		canWin = !canWin;
		
		for(String move : nextMoves) {
			canWin = canWin(move, canWin);
			if(canWin) {
				return true;
			}
		}
		
		return false;
	}

	boolean canWinMtd2(String s) {
		if(s == null || s.length() < 2) {
			return false;
		}
		
		for(int i = 0; i < s.length()-1; ++i) {
			if(s.charAt(i) == '+' && s.charAt(i+1) == '+') {
				String newState = s.substring(0, i) + "--" + s.substring(i+2);
				if(!canWin(newState)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Word Pattern
	 */
	public boolean wordPattern(String pattern, String str) {
		
		Map<Character, List<Integer>> pMap = new HashMap<>();
		for(int i = 0; i < pattern.length(); ++i) {
			pMap.computeIfAbsent(pattern.charAt(i), x -> new ArrayList<>()).add(i);
		}
		
		String[] values = str.split(" ");
		Map<String, List<Integer>> sMap = new HashMap<>();
		for(int i = 0; i < values.length; ++i) {
			sMap.computeIfAbsent(values[i], x -> new ArrayList<>()).add(i);
		}
		
		Collection<List<Integer>> pSet = pMap.values();
		Collection<List<Integer>> sSet = sMap.values();
		
		if(pSet.size() != sSet.size()) {
			return false;
		}
		
		for(List<Integer> v : pSet) {
			if(!sSet.contains(v)) {
				return false;
			}
		}
		
		return true;
	}

	public boolean wordPattern2(String pattern, String str) {
		
		String[] values = str.split(" ");
		if(values.length != pattern.length()) {
			return false;
		}
		
		Map<Character, String> map = new HashMap<>();
		for(int i = 0; i < values.length; ++i) {
			char ch = pattern.charAt(i);
			String value = values[i];
			
			if(!map.containsKey(ch)) {
				if(map.containsValue(value)) {
					return false;
				}
				map.put(ch, value);
			}
			else if(!map.get(ch).equals(value)) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean wordPatternMatch(String pattern, String str) {
		return wordPattern(pattern, str, 0, 0, pattern.length(), str.length(), new HashMap<Character, String>(),
				new HashSet<String>());
	}

	private boolean wordPattern(
			String pattern, 
			String str, 
			int i, // pattern index
			int j, // string index
			int pLen, 
			int sLen,
			HashMap<Character, String> map, 
			HashSet<String> set) {
		
		if(i == pLen && j == sLen) {
			return true;
		}
		
		if(i >= pLen || j >= sLen) {
			return false;
		}
		
		char ch = pattern.charAt(i);
		
		// If found in the map
		if(map.containsKey(ch)) {
			String s = map.get(ch);
			int len = s.length();
			// match the next len chars
			if(!str.subSequence(j, j+len).equals(s)) {
				return false;
			}
			return wordPattern(pattern, str, i+1, j+len, pLen, sLen, map, set);
		}
		
		// If not found in the map, create all possible subsequences
		for(int k = 1; k < sLen-j; ++k) {
			String sub = str.substring(j, j+k);
			if(set.contains(sub)) {
				continue;
			}
			
			map.put(ch, sub);
			set.add(sub);
			
			if(wordPattern(pattern, str, i+1, j+k, pLen, sLen, map, set)) {
				return true;
			}
			
			map.remove(ch);
			set.remove(sub);
		}
		
		return false;
	}
	
	/**
	 * Kth largest element in an array
	 */
	public int findKthLargest(int nums[], int k) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, (a,b) -> Integer.compare(a, b));
		
		int i = 0;
		for(; i < k; ++i) {
			minHeap.add(nums[i]);
		}
		
		for(; i < nums.length; ++i) {
			if(minHeap.peek() < nums[i]) {
				minHeap.poll();
				minHeap.add(nums[i]);
			}
		}
		
		return minHeap.peek();
	}
	
	private static class ItemFreq {
		public int value;
		public int freq;
		
		public ItemFreq(int value, int freq) {
			this.value = value;
			this.freq = freq;
		}
	}
	
	/**
	 * Top K frequent elements
	 */
	public List<Integer> topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		for(int val : nums) {
			map.put(val, map.getOrDefault(val, 0)+1);
		}
		
		PriorityQueue<ItemFreq> minHeap = new PriorityQueue<>((a,b) -> a.freq - b.freq);
		
		for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
			minHeap.add(new ItemFreq(entry.getKey(), entry.getValue()));
			
			if(minHeap.size() > k) {
				minHeap.poll();
			}
		}
		
		List<Integer> results = new LinkedList<>();
		while(!minHeap.isEmpty()) {
			results.add(0, minHeap.poll().value);
		}
		
		return results;
	}
	
	@AllArgsConstructor
	public static class Interval {
		public int start;
		public int end;
	}
	/**
	 * Meeting Rooms II
	 */
	public int minMeetingRooms(Interval[] intervals) {
		// Create a priority queue to the end time of intervals to identify which meeting is ending first
		PriorityQueue<Interval> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a.end, b.end));
		
		Arrays.sort(intervals, (a,b) -> Integer.compare(a.start, b.start));
		
		for(int i = 0; i < intervals.length; ++i) {
			Interval curr = intervals[i];
			if(minHeap.isEmpty()) {
				minHeap.offer(curr);
				continue;
			}
			
			if(minHeap.peek().end <= curr.start) { 
				// Meeting ended and taken over by next meeting
				minHeap.poll();
			}
			minHeap.offer(curr);
		}
		
		return minHeap.size();
	}
	
	/**
	 * Meeting Rooms
	 */
	public boolean canAttendMeetings(Interval[] intervals) {
		if(intervals == null || intervals.length == 0) {
			return true;
		}
		
		// Sort intervals based on the start time
		Arrays.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
		
		for(int i = 1; i < intervals.length; ++i) {
			if(intervals[i].start < intervals[i-1].end) { // Next meeting starts before the prev ends
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Range Addition
	 * updates[] : [[start, end, increment]]
	 */
	public int[] getModifiedArray(int length, int[][] updates) {
		
		int[] sum = new int[length];
		int nQueries = updates.length;
		
		for(int i = 0; i < nQueries; ++i) {
			int start = updates[i][0];
			int end = updates[i][1];
			int increment = updates[i][2];
			
			sum[start] += increment;
			if(end+1 < length) {
				sum[end+1] -= increment;
			}
		}
		
		for(int i = 1; i < length; ++i) {
			sum[i] += sum[i-1];
		}
		
		return sum;
	}
	
	/**
	 * Merge k sorted arrays
	 */
	public ListNode mergeKLists(ListNode[] lists) {
		int len = lists.length;
		
		PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
		
		for(int i = 0; i < len; ++i) {
			if(lists[i] != null) {
				minHeap.offer(lists[i]);
			}
		}

		ListNode head = null;
		ListNode retHead = head;
		
		while(!minHeap.isEmpty()) {
			ListNode curr = minHeap.poll();
			if(head == null) {
				head = new ListNode(curr.val);
				head.next = null;
				retHead = head;
			}
			else {
				head.next = new ListNode(curr.val);
				head = head.next;
			}
			
			if(curr.next != null) {
				minHeap.offer(curr.next);
			}
		}
		// If all the arrays are empty, we will have a null head.
		if(head != null) {
			head.next = null;
		}
		
		return retHead;
	}
	
	/**
	 * Contains Duplicate
	 */
	public boolean containsDuplicate(int[] nums) {
		HashSet<Integer> set = new HashSet<>();
		for(int i = 0; i < nums.length; ++i) {
			if(set.contains(nums[i])) {
				return true;
			}
			
			set.add(nums[i]);
		}
		
		return false;
	}
	
	/**
	 * Contains Duplicate II
	 */
	public boolean containsNearbyDuplicate(int[] nums, int k) {
		
		HashMap<Integer, Integer> map = new HashMap<>();
		
		for(int i = 0; i < nums.length; ++i) {
			if(map.containsKey(nums[i]) && i-map.get(nums[i]) <= k) {
				return true;
			}
			else {
				map.put(nums[i], i);
			}
		}
		
		return false;
	}
	
	/**
	 * Contains Duplicate III
	 * @param k Max index diff
	 * @param t Max value diff
	 */
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		TreeSet<Integer> set = new TreeSet<>();
		
		for(int i = 0; i < nums.length; ++i) {
			int curr = nums[i];
			int leftRange = -1, rightRange = -1;
			if(t >= 0) {
				leftRange = curr - t;
				rightRange = curr + t+1; // Last value is exclusive
			}
			else {
				leftRange = curr + t;
				rightRange = curr - t + 1;
			}
			
			if(set.subSet(leftRange, rightRange).size() > 0) {
				return true;
			}
			
			set.add(nums[i]);
			if(i >= k) {
				set.remove(nums[i-k]);
			}
		}
		
		return false;
    }
	
	/**
	 * Missing number
	 */
	public int missingNumber(int[] nums) {
		int i = 0;
		int retVal = nums.length;
		while(i < nums.length) {
			if(nums[i] == i) {
				++i;
			}
			else {
				if(nums[i] >= nums.length) {
					retVal = i;
					++i;
				}
				else { // Swap
					int temp = nums[nums[i]];
					nums[nums[i]] = nums[i];
					nums[i] = temp;
				}
			}
		}
		return retVal;
	}
	
	public int missingNumberMtd2(int[] nums) {
		// Sort the array in place
		int i = 0;
		while(i < nums.length) {
			if(nums[i] < nums.length && nums[i] != i) {
				// Swap
				int temp = nums[nums[i]];
				nums[nums[i]] = nums[i];
				nums[i] = temp;
			}
			else {
				++i;
			}
		}
		
		// Check for the missing value
		for(i = 0; i < nums.length; ++i) {
			if(nums[i] != i) {
				return i;
			}
		}
		
		return nums.length;
	}
	
	public int missingNumberMtd3(int[] nums) {
		int n = nums.length;
		int sum = 0;
		for(int i = 0; i < n; ++i) {
			sum += nums[i];
		}

		return (n*(n+1))/2 - sum;
	}

	
	/**
	 * Find the duplicate number
	 * This solution only expects one duplicate value.
	 */
	public int findDuplicate(int[] nums) {
		int n = nums.length;
		int sum = 0;
		for(int i = 0; i < n; ++i) {
			sum += nums[i];
		}
		
		int expSum = n*(n+1)/2;
		int expectedNum = expSum - sum;
		int duplicate = n-expectedNum;
		
		return duplicate;
	}
	
	/**
	 * Here we are modifying the array. What if the array is read only and we only have O(1) space?
	 * @param nums
	 * @return
	 */
	public int findDuplicateLeet(int[] nums) {
		int i = 0;
		while(i < nums.length) {
			if(nums[i] == i+1) {
				++i;
			}
			else {
				// Swap
				if(nums[nums[i]-1] == nums[i]) {
					return nums[i];
				}
				int temp = nums[nums[i]-1];
				nums[nums[i]-1] = nums[i];
				nums[i] = temp;
			}
		}
		
		return nums.length;
	}
	
	/**
	 * Find duplicate using Floyd's algorithm
	 */
	public int FindDuplicateFloyd(int[] nums) {
		
		int slow = nums[0];
		int fast = nums[0];
		
		slow = nums[slow];
		fast = nums[nums[fast]];
		
		while(slow != fast) {
			slow = nums[slow];
			fast = nums[nums[fast]];
		}
		
		fast = nums[0];
		while(slow != fast) {
			slow = nums[slow];
			fast = nums[fast];
		}
		
		return fast;
	}
	
	/**
	 * First Missing Positive Number
	 */
	public int firstMissingPositive(int[] nums) {
		int n = nums.length;
		int i = 0;
		
		// We only need to swap +ve numbers and we will shift +ve numbers starting index 0.
		while(i < n) {
			if (nums[i] > 0 && nums[i] <= n //&& nums[i] - 1 != i && nums[nums[i] - 1] != i
					&& nums[i] != nums[nums[i] - 1]) {
				//swap
				int temp = nums[nums[i]-1];
				nums[nums[i]-1] = nums[i];
				nums[i] = temp;
			}
			else {
				++i;
			}
		}
		
		// The numbers {1,2...} are now arranged starting index 0.
		for(i = 0; i < n; ++i) {
			if(nums[i] != i+1) {
				return i+1;
			}
		}
		
		return i+1;
	}
	
	/**
	 * H-Index
	 */
	public int hIndex(int[] citations) {
		Arrays.sort(citations);
		int len = citations.length;
		int hIndex = 0;
		
		for(int i = 0; i < len; ++i) {
			int val = citations[i];
			if(len-i >= val) {
				hIndex = val;
			}
			else {
				hIndex = Math.max(hIndex, len-i);
			}
		}
		
		return hIndex;
	}

	/**
	 * Just a more cleaner way for above algo
	 */
	public int hIndexClean(int[] citations) {
		
		Arrays.sort(citations);
		
		int hIndex = 0;
		int len = citations.length;
		
		for(int i = 0; i < len; ++i) {
			int possibleVal = Math.min(citations[i], len-i);
			hIndex = Math.max(hIndex, possibleVal);
		}
		
		return hIndex;
	}
	
	/**
	 * H-Index II (Citations are sorted in ascending order)
	 * @param citations
	 * @return
	 */
	public int hIndex2(int[] citations) {
		
		int hIndex = 0;
		int len = citations.length;
		
		for(int i = len-1; i >= 0; --i) {
			int possibleVal = Math.min(citations[i], len-i);
			hIndex = Math.max(hIndex, possibleVal);
		}
		
		return hIndex;
	}
	
	/**
	 * Use binary search to optimize
	 * @param citations
	 * @return
	 */
	public int hIndex2Opt(int[] citations) {
	
		if(citations == null || citations.length == 0) {
			return 0;
		}
		
		int left = 0, right = citations.length-1;
		int len = citations.length;
		
		int hIndex = Integer.MIN_VALUE;
		while(left <= right) {
			int mid = (right+left)/2;
			hIndex = Math.max(hIndex, Math.min(citations[mid], len-mid));
			// check on the mid if we can move towards right
			if(citations[mid] > len-mid) { // move left
				right = mid-1;
			}
			else if(citations[mid] < len-mid) { // move right
				left = mid+1;
			}
			else {
				return hIndex;
			}
		}
		
		return hIndex;
	}
	
	/**
	 * Sliding Window Maximum
	 */
	public int[] maxSlidingWindow(int[] nums, int k) {
		if(nums == null || nums.length < k) {
			return new int[] {};
		}
		
		if(k == 1) {
			return nums;
		}
		
		int[] res = new int[nums.length-k+1];
		
		// Save the indices and not the numbers
		LinkedList<Integer> queue = new LinkedList<>();

		int i = 0;
		for(; i < nums.length; ++i) { 
			// Queue cannot have elements from elements left to index i-k+1
			while(!queue.isEmpty() && queue.peek() < i-k+1) {
				queue.poll();
			}
			
			// keep removing elements from until the head becomes max
			// this will make sure that the queue is always in descending order
			while(!queue.isEmpty() && nums[i] > nums[queue.peekLast()]) { 
				queue.removeLast();
			}
			
			// Push the index
			queue.offer(i);
			
			if(i >= k-1) {
				res[i-k+1] = nums[queue.peek()];
			}
		}
		
		return res;
	}
	
	
	public ArrayList<Integer> slidingMaximum12(final List<Integer> A, int B) {
        if(A == null || A.size() < B) {
			return new ArrayList<>();
		}
		
		if(B == 1) {
			return new ArrayList<Integer>(A);
		}
		
		ArrayList<Integer> res = new ArrayList<>();
		
		// Save the indices and not the numbers
		LinkedList<Integer> queue = new LinkedList<>();

		int i = 0;
		for(; i < A.size(); ++i) { 
			// Queue cannot have elements from elements left to index i-k+1
			while(!queue.isEmpty() && queue.peek() < i-B+1) {
				queue.poll();
			}
			
			// keep removing elements from until the head becomes max
			// this will make sure that the queue is always in descending order
			while(!queue.isEmpty() && A.get(i) > A.get(queue.peekLast())) { 
				queue.removeLast();
			}
			
			// Push the index
			queue.offer(i);
			
			if(i >= B-1) {
				res.add(A.get(queue.peek()));
			}
		}
		
		return res;
    }
	
	/**
	 * Largest Number
	 */
	public String largestNumber(int[] nums) {
		List<String> strNums = Arrays.stream(nums).boxed().map(x -> Integer.toString(x)).collect(Collectors.toList());
		
		Collections.sort(strNums, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				String s1 = o1+o2;
				String s2 = o2+o1;
				return s2.compareTo(s1);
			}
		});
		
		StringBuilder resStr = new StringBuilder();
		for(String s : strNums) {
			resStr.append(s);
		}
        
        while(resStr.length() > 1 && resStr.charAt(0) == '0') {
        	resStr.deleteCharAt(0);
        }
        
        return resStr.toString();
    }	

	/**
	 * Sort Colors
	 */
	public void sortColors(int[] nums) {
		int c0 = 0, c1 = 0;
		for(int i = 0; i < nums.length; ++i) {
			if(nums[i] == 0) {
				++c0;
			}
			else if(nums[i] == 1) {
				++c1;
			}
		}
		
		for(int i = 0; i < c0; ++i) {
			nums[i] = 0;
		}
		for(int i = c0; i < c0+c1; ++i) {
			nums[i] = 1;
		}
		for(int i = c0+c1; i < nums.length; ++i) {
			nums[i] = 2;
		}
	}
	
	// Solved using counting sort
	public void sortColors2(int[] nums) {
		int[] counts = new int[3];
		
		// Save counts of 0,1,2
		for(int i = 0; i < nums.length; ++i) {
			counts[nums[i]]++;
		}
		
		// Add prev value to get the indices
		for(int i = 1; i < counts.length; ++i) {
			counts[i] += counts[i-1];
		}
		
		int res[] = new int[nums.length];
		for(int i = 0; i < nums.length; ++i) {
			int idx = counts[nums[i]]-1;
			res[idx] = nums[idx];
			counts[nums[i]]--;
		}
		
		System.arraycopy(res, 0, nums, 0, nums.length);
	}
	
	/**
	 * Merge Intervals
	 */
	public int[][] merge(int[][] intervals) {
		
		if(intervals == null || intervals.length < 2) {
			return intervals;
		}
		
		// Sort on the start time
		Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));

		List<Integer[]> result = new ArrayList<>();
		
		int start = intervals[0][0];
		int end = intervals[0][1];

		for(int i = 1; i < intervals.length; ++i) {
			// If the interval starts before the prev ends
			if(intervals[i][0] <= end) {
				end = Math.max(end, intervals[i][1]);
			}
			else {
				// Add to the result and update start and end
				result.add(new Integer[] {start, end});
				start = intervals[i][0];
				end = intervals[i][1];
			}
		}
		// Add the last interval
		result.add(new Integer[] {start, end});
		
		int[][] resArr = new int[result.size()][2];
		for(int i = 0; i < result.size(); ++i) {
			resArr[i] = new int[] { result.get(i)[0], result.get(i)[1] };
		}
		
		return resArr;
	}
	
	/**
	 * Insert Interval
	 */
	public int[][] insert(int[][] intervals, int[] newInterval) {
		
		if(intervals == null || intervals.length == 0) {
			return new int[][] {{newInterval[0], newInterval[1]}};
		}
		
		List<Integer[]> res = new ArrayList<>();
		
		for(int i = 0; i < intervals.length;) {//int[] interval : intervals) {
			// Interval end is before new Interval start
			if(intervals[i][1] < newInterval[0]) {
				res.add(new Integer[] {intervals[i][0], intervals[i][1]});
				++i;
			}
			else if(newInterval[1] < intervals[i][0]) {
				res.add(new Integer[] {newInterval[0], newInterval[1]});
				res.add(new Integer[] {intervals[i][0], intervals[i][1]});
				++i;
			}
			else {
				// newInterval ends before interval starts
				// find the interval
				int start = intervals[i][0];
				int end = newInterval[1];
				while(i < intervals.length && intervals[i][0] <= newInterval[1]) {
					end = Math.max(end, intervals[i][1]);
					++i;
				}
				res.add(new Integer[] {start, end});
			}
		}
		
		if(!res.isEmpty() && res.get(res.size()-1)[1] < newInterval[0]) {
			res.add(new Integer[] {newInterval[0], newInterval[1]});
		}
		
		int[][] resArr = new int[res.size()][2];
		for(int i = 0; i < res.size(); ++i) {
			resArr[i] = new int[] { res.get(i)[0], res.get(i)[1] };
		}
		
		return resArr;
	}
	
	public int[][] insert1(int[][] intervals, int[] newInterval) {
		
		if(intervals == null || intervals.length == 0) {
			return new int[][] {{newInterval[0], newInterval[1]}};
		}
		
		List<Integer[]> res = new ArrayList<>();
		int i = 0;
		int start = Integer.MAX_VALUE, end = Integer.MIN_VALUE;
		while(i < intervals.length) {
			// Overlapping
			if(intervals[i][0] <= newInterval[0] && intervals[i][1] >= newInterval[0] || 
					newInterval[0] <= intervals[i][0] && newInterval[1] >= intervals[i][0]) {
				start = Math.min(intervals[i][0], newInterval[0]);
				end = Math.max(intervals[i][1], newInterval[1]);
				++i;
			}
			else if(intervals[i][1] < newInterval[0]) {
				res.add(new Integer[] {intervals[i][0], intervals[i][1]});
				++i;
			}
			else {
				res.add(new Integer[] {newInterval[0], newInterval[1]});
				break;
			}
		}
		
		if(i == intervals.length) {
			res.add(new Integer[] {start, end});
		}
		
		while(i < intervals.length) {
			res.add(new Integer[] {intervals[i][0], intervals[i][1]});
		}
		
		int[][] resArr = new int[res.size()][2];
		for(i = 0; i < res.size(); ++i) {
			resArr[i] = new int[] { res.get(i)[0], res.get(i)[1] };
		}
		
		return resArr;
	}

	public int[][] insert2(int[][] intervals, int[] newInterval) {
		
		if(intervals == null || intervals.length == 0) {
			return new int[][] {{newInterval[0], newInterval[1]}};
		}
		
		List<Integer[]> res = new ArrayList<>();
		int i = 0;
		//Add while no overlap
		while(i < intervals.length && intervals[i][1] < newInterval[0]) {
			res.add(new Integer[] {intervals[i][0], intervals[i][1]});
			++i;
		}
		
		// Merge all the overlapping
		while(i < intervals.length && intervals[i][0] <= newInterval[1]) {
			newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
			newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
			++i;
		}
		// Add the merged interval
		res.add(new Integer[] {newInterval[0], newInterval[1]});
		
		while(i < intervals.length) {
			res.add(new Integer[] {intervals[i][0], intervals[i][1]});
			++i;
		}
		
		int[][] resArr = new int[res.size()][2];
		for(i = 0; i < res.size(); ++i) {
			resArr[i] = new int[] { res.get(i)[0], res.get(i)[1] };
		}
		
		return resArr;
	}
}













