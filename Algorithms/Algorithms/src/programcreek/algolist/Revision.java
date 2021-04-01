package programcreek.algolist;

import java.util.Stack;

public class Revision {
	/**
	 * Move Zeroes
	 */
	public void moveZeroes(int[] nums) {
		int i = 0, j = 0;
		
		while(i < nums.length) {
			if(nums[i] != 0) {
				swap(nums, i, j);
				++j;
			}
			++i;
		}
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	
	/**
	 * Container with most water
	 */
	public int maxArea(int[] heights) {
		if(heights == null || heights.length < 2) {
			return 0;
		}
		
		int left = 0, right = heights.length-1;
		int maxArea = 0;
		
		while(left < right) {
			int currArea = Math.min(heights[left], heights[right]) * (right-left);
			maxArea = Math.max(maxArea, currArea);
			
			if(heights[left] > heights[right]) {
				--right;
			}
			else {
				++left;
			}
		}
		
		return maxArea;
	}
	
	/**
	 * Candy
	 */
	public int candy(int[] ratings) {
		int[] candies = new int[ratings.length];
		int res = 0;
		
		// First pass makes sure that from left to right candies increase if rating increase
		candies[0] = 1;
		for(int i = 1; i < ratings.length; ++i) {
			if(ratings[i] > ratings[i-1]) {
				candies[i] = candies[i-1] + 1;
			}
			else {
				candies[i] = 1;
			}
		}
		
		// The last index will have the right value as there is no right value to it.
		// In the second pass, we make sure that candies increase if the ratings increase
		res = candies[candies.length-1];
		for(int i = ratings.length-2; i >= 0; i--) {
			int currCandy = 1;
			if(ratings[i] > ratings[i+1]) {
				currCandy = candies[i+1]+1;
			}
			res += Math.max(currCandy, candies[i]);
			candies[i] = currCandy;
		}
		
		return res;
	}
	
	/**
	 * Trapping Rain Water
	 */
	public int trap(int[] height) {
		int[] left = new int[height.length];
		int[] right = new int[height.length];
		
		// Set left values
		left[0] = height[0];
		int currLeft = height[0];
		for(int i = 1; i < height.length; ++i) {
			left[i] = currLeft;
			if(height[i] > currLeft) {
				currLeft = height[i];
			}
		}
		
		// Set left values
		right[height.length-1] = height[height.length-1];
		int currRight = height[height.length-1];
		for(int i = height.length-2; i >= 0; --i) {
			right[i] = currRight;
			if(height[i] > currRight) {
				currRight = height[i];
			}
		}
		
		// Calculate trapped rain water (min(left[i], right[i]) - height[i])
		int res = 0;
		for(int i = 1; i < height.length-1; ++i) {
			
			int min = Math.min(left[i], right[i]);
			if(min > height[i]) {
				res += min - height[i];
			}
		}
		
		return res;
	}
	
	public boolean isOneEditDistance(String s, String t) {
		int l1 = s.length();
		int l2 = t.length();
		
		if(Math.abs(l1 - l2) > 1) {
			return false;
		}
		
		int i1 = 0, i2 = 0;
		
		boolean isDiff = false;
		while(i1 < l1 && i2 < l2) {
			if(s.charAt(i1) != t.charAt(i2)) {
				if(isDiff) {
					return false;
				}

				if(l1 == l2) {
					isDiff = true;
					++i1;
					++i2;
				}
				else {
					if(l1 > l2) {
						++i1;
					}
					else {
						++i2;
					}
				}
			}
			else {
				++i1;
				++i2;
			}
		}
		
		if(i1 != l1 || i2 != l2) {
			isDiff = true;
		}
		
		return isDiff;
	}
	
	// Insert 0 for opening bracket and 1 for closing bracket
	public int longestValidParentheses(String s) {
		Stack<Integer[]> stack = new Stack<>();
		int max = 0;
		int currCount = 0;
		for(int i = 0; i < s.length(); ++i) {
			char currChar = s.charAt(i);
			if(currChar == '(') {
				stack.push(new Integer[] {0, i});
			}
			else {
				if(stack.empty() || stack.peek()[0] == 1) {
					stack.push(new Integer[] {1,i});
				}
				else {
					currCount = 0;
					stack.pop();
					if(stack.isEmpty()) {
						currCount = i+1;
					}
					else {
						currCount = i-stack.peek()[1];
					}
				}
				max = Math.max(currCount, max);
			}
		}
		
		return max;
    }
	
	public int largestRectangleArea(int[] heights) {
		int max = 0;
		int i = 0; // indices of heights
		Stack<Integer> stack = new Stack<>();
		
		while(i < heights.length) {
			if(stack.isEmpty() || heights[stack.peek()] <= heights[i]) { // keep pushing till heights don't decrease
				stack.add(i++);
			}
			else { // value decreased at index i
				int l = heights[stack.peek()];
				int w = i - stack.pop();
				max = Math.max(max, l*w);
			}
		}
		
		// Only increasing values left in the stack
		while(!stack.isEmpty()) {
			int l = heights[stack.peek()];
//			int w = i - stack.pop();
			int w = i - stack.peek();
			int last = stack.pop();
			if(stack.isEmpty() && last > 0) {
				w += 1;
			}
			max = Math.max(max, l*w);
		}
		
		return max;
	}
}
