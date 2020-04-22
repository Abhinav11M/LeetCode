package leetcode30daychallenge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeMap;

import datastructures.ListNode;
import datastructures.TreeNode;

public class LeetCode30DayChallenge {
	/**
	 * Day1 :  Single Number
	 */
	public int singleNumber(int[] nums) {
		int val = nums[0];
		
		for(int i = 1; i < nums.length; ++i) {
			val = val^nums[i];
		}
		
		return val;
	}
	
	/**
	 * Day2 : Happy Number
	 */
	public boolean isHappy(int n) {
		int slow = squareOfDigits(n);
		int fast = squareOfDigits(slow);
		
		while(fast != slow) {
			slow = squareOfDigits(slow);
			fast = squareOfDigits(squareOfDigits(fast));
			if(fast == 1) {
				return true;
			}
		}
		return false;
	}

	private int squareOfDigits(int n) {
		int retVal = 0;
		while(n > 0) {
			retVal += Math.pow(n%10,2);
			n = n/10;
		}
		return retVal;
	}
	
	/**
	 * Given an integer array nums, find the contiguous subarray
	 * (containing at least one number) which has the largest sum and return its sum.
	 * Input: [-2,1,-3,4,-1,2,1,-5,4],
	 * Output: 6
	 * Explanation: [4,-1,2,1] has the largest sum = 6.
	 */
	public int maxSubArray(int[] nums) {
		int max = Integer.MIN_VALUE;
		
		int sum = 0;
		int i = 0;
		
		for(; i < nums.length; ++i) {
			sum += nums[i];
			if(sum > max) {
				max = sum;
			}
			if(sum <= 0) {
				sum = 0;
			}
		}
		
		return max;
	}
	
	/**
	 * Move Zeros
	 * Given an array nums, write a function to move all 0's to 
	 * the end of it while maintaining the relative order of the non-zero elements.
	 * Input: [0,1,0,3,12]
	 * Output: [1,3,12,0,0]
	 */
	 public void moveZeroes(int[] nums) {
		 
		 int i = 0;
		 for(;i < nums.length && nums[i] != 0; ++i);
		 if(i == nums.length) {
			 return;
		 }
		 
		 int insIdx = i; // first index of 0
		 ++i;
		 for(; i < nums.length && insIdx < nums.length; ++i) {
			 if(nums[i] != 0) {
				 // Swap
				 nums[insIdx] = nums[i];
				 nums[i] = 0;
				 // Move the insIdx to next zero
				 while(insIdx < nums.length && nums[insIdx] != 0) {
					 ++insIdx;
				 }
				 
			 }
		 }
	 }
	 
	 /**
	  * Group Anagrams
	  */
	 public List<List<String>> groupAnagrams(String[] strs) {
		
		 boolean[] flags = new boolean[strs.length];
		 List<List<String>> res = new ArrayList<>();
		 
		 for(int i = 0; i < strs.length; ++i) {
			 for(; i < strs.length && flags[i] == true; ++i);

			 if(i == strs.length) {
				 break;
			 }

			 List<String> list = new ArrayList<>();
			 String str = strs[i];
			 list.add(str);
			 flags[i] = true;
			 
			 for(int j = i+1; j < strs.length; ++j) {
				 if(flags[j] == true) {
					 continue;
				 }
				 if(isAnagram(str,strs[j])) {
					 flags[j] = true;
					 list.add(strs[j]);
				 }
			 }
			 res.add(list);
		 }
		 
		 return res;
	 }

	public int xor(String str1, String str2) {
		if(str1.length() != str2.length()) {
			return 1;
		}

		int xor = 0;
		for(int i = 0; i < str1.length(); ++i) {
			xor ^= str1.charAt(i)^str2.charAt(i);
		}
		
		return xor;
	}
	
	public boolean isAnagram(String str1, String str2) {
		if(str1.length() != str2.length()) {
			return false;
		}
		
		int[] arr = new int[256];

		for(int i = 0; i < str1.length(); ++i) {
			arr[str1.charAt(i)]++;
			arr[str2.charAt(i)]--;
		}
		
		for(int i = 0; i < arr.length; ++i) {
			if(arr[i] != 0) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Counting Elements
	 * Given an integer array arr, count element x such that x + 1 is also in arr.
	 * If there're duplicates in arr, count them seperately.
	 * Input: arr = [1,2,3]
	 * Output: 2
	 * Explanation: 1 and 2 are counted cause 2 and 3 are in arr.
	 */
	public int countElements(int[] arr) {
		Integer[] count = new Integer[1001];
		
		
		for(int i = 0; i < arr.length; ++i) {
			if(count[arr[i]] == null) {
				count[arr[i]] = 1;
			}
			else {
				count[arr[i]]++;
			}
		}
		
		int result = 0;
		int prev = 0;
		
		for(int i = 0; i < count.length; ++i) {
			if(count[i] == null) {
				result -= prev;
				prev = 0;
			}
			else {
				prev = count[i];
				result += count[i];
			}
		}
		
		return result;
	}
	
	public int countElements1(int[] arr) {
		Map<Integer, Integer> map = new TreeMap<>();
		
		for(int i = 0; i < arr.length; ++i) {
			map.put(arr[i], map.getOrDefault(arr[i], 0)+1);
		}
		
		int count = 0;
		int prev = Integer.MIN_VALUE;

		for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if(prev == Integer.MIN_VALUE) {
				prev = entry.getKey();
			}
			else {
				if(entry.getKey() == prev+1) { // x+1 is available
					count += map.get(prev);
				}
				
				prev = entry.getKey();
			}
		}
		
		return count;
	}
	/**
	 * Middle of linked list
	 */
	public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        
        while(fast != null && fast.next != null) {
        	fast = fast.next.next;
        	slow = slow.next;
        }
        
        return slow;
    }
	
	/**
	 * Backspace String Compare
	 */
	public boolean backspaceCompare(String S, String T) {
		Stack<Character> stack1 = new Stack<>();
		for(char ch : S.toCharArray()) {
			if(ch == '#') {
				if(!stack1.isEmpty()) {
					stack1.pop();
				}
			}
			else {
				stack1.add(ch);
			}
		}
		
		Stack<Character> stack2 = new Stack<>();
		for(char ch : T.toCharArray()) {
			if(ch == '#') {
				if(!stack2.isEmpty()) {
					stack2.pop();
				}
			}
			else {
				stack2.add(ch);
			}
		}
		
		return stack1.equals(stack2);
	}
	
	public boolean backspaceCompare1(String S, String T) {
		
		int r1 = S.length()-1;
		int r2 = T.length()-1;
		
		while(r1 >= 0 && r2 >= 0) {
			
			if(S.charAt(r1) != '#' && T.charAt(r2) != '#') {
				if(S.charAt(r1) == T.charAt(r2)) {
					--r1;
					--r2;
				}
				else {
					return false;
				}
				continue;
			}

			if(S.charAt(r1) == '#') {
				r1--;
				int count = 1;
				while(r1 >= 0 && count >= 0) {
					if(S.charAt(r1) == '#') {
						++count;
					}
					else {
						--count;
					}
					--r1;
				}
			}
			
			if(T.charAt(r2) == '#') {
				r2--;
				int count = 1;
				while(r2 >= 0 && count >= 0) {
					if(S.charAt(r2) == '#') {
						++count;
					}
					else {
						--count;
					}
					--r2;
				}
			}

			if(r1 < 0 || r2 < 0) {
				break;
			}
			
		}

		if(r1 > 0) {
			while(r1 >=0) {
				if(S.charAt(r1--) != '#') {
					return false;
				}
			}
		}
		
		if(r2 > 0) {
			while(r2 >=0) {
				if(T.charAt(r2--) != '#') {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Find the Diameter of a tree
	 */
    public int diameterOfBinaryTree(TreeNode root) {
    	if(root == null) {
    		return 0;
    	}
    	
    	int lHeight = heightOfTree(root.left);
    	int rHeight = heightOfTree(root.right);
    	
    	return Math.max((lHeight + rHeight), Math.max(diameterOfBinaryTree(root.left), diameterOfBinaryTree(root.right)));
    }

	private int heightOfTree(TreeNode root) {
		if(root == null) {
			return 0;
		}
		
		return 1+Math.max(heightOfTree(root.left), heightOfTree(root.right));
	}
	
	public int diameterOfBinaryTreeOpt(TreeNode root) {
		if(root == null) {
			return 0;
		}

		Diameter d = new Diameter();
		height(root, d);
		
		return d.value;
	}
    
	private int height(TreeNode root, Diameter diameter) {
		if(root == null) {
			return 0;
		}
		
		int lHeight = height(root.left, diameter);
		int rHeight = height(root.right, diameter);
		
		int height = 1+Math.max(lHeight,rHeight);
		
		diameter.value = Math.max(diameter.value, lHeight+rHeight);
		
		return height;
	}

	static class Diameter {
		public int value = 0;
	}
	
	
	public int lastStoneWeight(int[] stones) {
		int[] weights = new int[1001];
		for(int i = 0; i < stones.length; ++i) {
			if(weights[stones[i]] == stones[i]) {
				weights[stones[i]] = 0;
			}
			else {
				weights[stones[i]] = stones[i];
			}
		}
		
		int left = 1000;
		int right = 1000;
		while(left > 0) {
			while(right > 0 && weights[right] == 0) {
				right--;
			}
			if(right == 0) {
				break;
			}
			
			left = right-1;
			while(left > 0 && weights[left] == 0) {
				left--;
			}
			if(left == 0) {
				break;
			}
			
			int diff = weights[right]-weights[left];
			weights[left] = 0;
			weights[right] = 0;
			if(weights[diff] == diff) {
				weights[diff] = 0;
			}
			else {
				weights[diff] = diff;
			}
			right = diff >= left ? diff : left-1;
		}
		
		if(left <= 0 && right <= 0) {
			return 0;
		}
		else {
			return weights[right];
		}
	}
	
	public int lastStoneWeightOpt(int[] stones) {
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
		
		for(int stone : stones) {
			maxHeap.offer(stone);
		}
		
		while(maxHeap.size() >= 2) {
			int elem1 = maxHeap.poll();
			int elem2 = maxHeap.poll();
			
			int diff = elem1 - elem2;
			
			if(diff != 0) {
				maxHeap.offer(diff);
			}
		}
		
		return maxHeap.isEmpty() ? 0 : maxHeap.peek();
	}
	
	/**
	 * Contiguous Array
	 */
	public int findMaxLength(int[] nums) {
		int maxCount = 0;
		int left = 0, right = 0;
		int count0 = 0, count1 = 0;
		
		while(right < nums.length) {
			if(nums[right] == 0 || nums[right] == 1) {
				if(nums[right] == 0) {
					++count0;
				}
				else {
					++count1;
				}
				
				if(count0 == count1) {
					maxCount = Math.max(maxCount, right-left+1); 
				}
			}
			else {
				// try balancing left and right
				while(left < right) {
					if(nums[left] == 1) {
						--count1;
					}
					else {
						--count0;
					}
					if(count0 == count1) {
						maxCount = Math.max(maxCount, right-left+1);
					}
					++left;
				}
				left = right+1;
			}
			++right;
		}
		
		// Try balancing
		while(left < right-1) {
			if(nums[left] == 1) {
				--count1;
			}
			else {
				--count0;
			}
			++left;
			if(count0 == count1) {
				maxCount = Math.max(maxCount, right-left);
			}
		}
		
		return maxCount;
    }
	
	public int maxSubArrayLen(int[] nums) {
		int maxLen = 0;
		for(int i = 0; i < nums.length;++i) {
			if(nums[i] == 0) {
				nums[i] = -1;
			}
		}
		
		// [Sum -> Index]
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, -1); // If the numbers are -1,1,-1,1, the sum will go to 0. So we need an index for 0 as well which is -1.
		
		int sum = 0;
		for(int i = 0; i < nums.length; ++i) {
			sum += nums[i];
			
			if(map.containsKey(sum)) {
				maxLen = Math.max(maxLen, i-map.get(sum));
			}
			map.putIfAbsent(sum, i);
		}
		
		return maxLen;
    }
	
	/**
	 * Product of Array Except Self
	 */
	public int[] productExceptSelf(int[] nums) {
		int[] products = new int[nums.length];
		int zeroCount = 0;
		int prodWithout0 = 1;
		int zeroIndex = -1;
		
		for(int i = 0; i < nums.length; ++i) {
			if(nums[i] == 0) {
				++zeroCount;
				zeroIndex = i;
			}
			else {
				prodWithout0 *= nums[i];
			}
		}
		
		if(zeroCount > 1) {
			return products;
		}
		else if(zeroCount == 1) {
			products[zeroIndex] = prodWithout0;
		}
		else {
			for(int i = 0; i < nums.length; ++i) {
				if(i == zeroIndex) {
					products[i] = prodWithout0;
				}
				else {
					products[i] = prodWithout0/nums[i];
				}
			}
		}
		
		return products;
	}
	
	/**
	 * Given a m x n grid filled with non-negative numbers, 
	 * find a path from top left to bottom right which minimizes 
	 * the sum of all numbers along its path.
	 */
	public int minPathSum(int[][] grid) {
		int rows = grid.length;
		int cols = grid[0].length;
		
		int[][] dp = new int[rows][cols];
		dp[0][0] = grid[0][0];
		
		// Fill the first row
		for(int i = 1; i < cols; ++i) {
			dp[0][i] = dp[0][i-1] + grid[0][i];
		}
		
		for(int i = 1; i < rows; ++i) {
			for(int j = 0; j < cols; ++j) {
				if(j == 0) {
					dp[i][j] = dp[i-1][j]+grid[i][j];
				}
				else {
					dp[i][j] = Math.min(dp[i][j-1], dp[i-1][j]) + grid[i][j];
				}
			}
		}
		
		return dp[rows-1][cols-1];
	}
	
	/**
	 * Valid Parenthesis String
	 */
	public boolean checkValidString(String s) {
		
		// Do not save the character, instead save the positions in the string.
		Stack<Integer> stack = new Stack<>();
		Stack<Integer> starStack = new Stack<>();

		for(int i = 0; i < s.length(); ++i) {
			
			char ch = s.charAt(i);
			
			if(ch == '*') {
				starStack.add(i);
			}
			else if(ch == '(') {
				stack.add(i);
			}
			else {
				if(!stack.isEmpty()) { // balance using (
					stack.pop();
				}
				else if(!starStack.isEmpty()){ // balance using *
					starStack.pop();
				}
				else { // No * or ( to balance )
					return false;
				}
			}
		}

		// If there are still open braces, they should be 
		while(!stack.isEmpty()) {
			if(starStack.isEmpty()) {
				return false;
			}
			int idxOpen = stack.pop();
			int idxStar = starStack.pop();
			
			// Star should come after open parenthesis
			if(idxOpen > idxStar) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Perform String Shifts
	 */
	public String stringShift(String s, int[][] shift) {
		int n = s.length();
		
        for(int i = 0; i < shift.length; ++i) {
        	int[] temp = shift[i];
        	if(temp[1] == n) {
        		continue;
        	}

        	if(temp[0] == 1) { // leftShift
        		s = s.substring(n-temp[1]) + s.substring(0, n-temp[1]);
        	}
        	else { // rightShift
        		s = s.substring(temp[1]) + s.substring(0, temp[1]);
        	}
        }
        
        return s;
    }
}
