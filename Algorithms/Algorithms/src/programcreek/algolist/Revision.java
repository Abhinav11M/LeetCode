package programcreek.algolist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import datastructures.ListNode;
import datastructures.TreeNode;
import educative.tree.bfs.TreeNodeWithNext;

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
	
	/**
	 * Longest substring without repeating characters.
	 */
	public int lengthOfLongestSubstring(String s) {
		int max = 0;
		Map<Character, Integer> position = new HashMap<>();
		
		int left = 0, right = 0;
		for(; right < s.length(); ++right) {
			char ch = s.charAt(right);
			if(position.containsKey(ch) && position.get(ch) >= left) {
				// Reposition left
				left = position.get(ch) + 1;
			}

			position.put(ch, right);
			max = Math.max(max, right-left+1);
		}
		
		return max;
	}
	
	/**
	 * Longest substring with atmost K distinct characters
	 */
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		int max = 0;
		int left = 0, right = 0;
		Map<Character, Integer> freq = new HashMap<>();
		
		for(; right < s.length(); ++right) {
			char ch = s.charAt(right);
			freq.put(ch, freq.getOrDefault(ch, 0) + 1);
			
			while(freq.size() > k) {
				char l = s.charAt(left);
				freq.put(l, freq.getOrDefault(l, 0)-1);
				if(freq.get(l) == 0) {
					freq.remove(l);
				}
				++left;
			}
			
			max = Math.max(max, right - left + 1);
		}
			
		return max;
	}
	
	public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> results = new ArrayList<>();
        Map<String, Integer> freq = new HashMap<>();
        
        for(String word : words) {
			freq.put(word, freq.getOrDefault(word, 0) + 1);
        }
        
        // length of words
        int len = words[0].length();
        
        int left = 0, right = 0;
        while(right <= s.length()-len){
        	String word = s.substring(right, right+len);
        	if(freq.containsKey(word)) {
        		freq.put(word, freq.get(word)-1);
        		if(freq.get(word) == 0) {
        			freq.remove(word);
        		}
        		if(freq.isEmpty()) { // found one substring
        			results.add(left);
        			String word1 = s.substring(left, left+len);
        			freq.put(word1, freq.getOrDefault(word1, 0)+1);
        			left += len;
        		}
        		right += len;
        	}
        	else {
        		if(left < right) {
        			String word1 = s.substring(left, left+len);
        			freq.put(word1, freq.getOrDefault(word1, 0)+1);
        			left += len;
        		}
        		else {
        			++right;
        			left = right;
        		}
        	}
        }
        
        return results;
    }
	
	public List<Integer> findSubstring1(String s, String[] words) {
        List<Integer> results = new ArrayList<>();
        Map<String, Integer> freq = new HashMap<>();
        
        for(String word : words) {
			freq.put(word, freq.getOrDefault(word, 0) + 1);
        }
        
        // length of words
        int wordLen = words[0].length();
        int totalWords = words.length;
        
        for(int i = 0; i <= s.length()-totalWords*wordLen; ++i) {
        	// Seen words in current iteration.
        	Map<String, Integer> currFreq = new HashMap<>();
        	for(int j = 0; j < words.length; ++j) { // find all words starting at index i
        		int wordIdx = i + j*wordLen;
        		String sub = s.substring(wordIdx, wordIdx+wordLen);
        		if(!freq.containsKey(sub)) {
        			break;
        		}

        		currFreq.put(sub, currFreq.getOrDefault(sub, 0) + 1);
        		if(currFreq.get(sub) > freq.get(sub)) {
        			break;
        		}
        		
        		if(j+1 == totalWords) {
        			results.add(i);
        		}
        	}
        }
        
        return results;
    }
	
	public int numDecodings(String s) {
		if(s == null || s.length() == 0 || s.charAt(0) == '0') {
			return 0;
		}

//        return numDecodings(s, 0);
        return numDecodingsMemoization(s, 0, new int[s.length()]);
    }

	private int numDecodings(String s, int i) {
		if(i >= s.length()) {
			return 0;
		}

		if(s.charAt(i) == '0') {
			return 0;
		}
		
		if(i == s.length()-1) {
			return 1;
		}
		
		String sub = s.substring(i, i+2);
		if(i == s.length()-2) {
			if(Integer.parseInt(sub) <= 26) {
				return 1 + numDecodings(s, i+1);
			}
			return numDecodings(s, i+1);
		}
		
		if(Integer.parseInt(sub) > 26) {
			return numDecodings(s, i+1);
		}
		else {
			return numDecodings(s, i+1) + numDecodings(s, i+2);
		}
	}
	
	private int numDecodingsMemoization(String s, int i, int[] dp) {
		if(i >= s.length()) {
			return 0;
		}

		if(s.charAt(i) == '0') {
			return 0;
		}
		
		if(i == s.length()-1) {
			return 1;
		}
		
		String sub = s.substring(i, i+2);
		if(i == s.length()-2) {
			if(Integer.parseInt(sub) <= 26) {
				return 1 + numDecodingsMemoization(s, i+1, dp);
			}
			return numDecodingsMemoization(s, i+1, dp);
		}

		if(dp[i] != 0) {
			return dp[i];
		}
		
		if(Integer.parseInt(sub) > 26) {
			dp[i] = numDecodingsMemoization(s, i+1, dp);
		}
		else {
			int res = numDecodingsMemoization(s, i+1, dp) + numDecodingsMemoization(s, i+2, dp);
			dp[i] = res;
		}
		
		return dp[i];
	}
	
	public boolean isIsomorphic(String s, String t) {
		if(s.length() != t.length()) {
			return false;
		}

        Map<Character, Character> map = new HashMap<>();
        for(int i = 0; i < s.length(); ++i) {
        	char ss = s.charAt(i);
        	char tt = t.charAt(i);
        	
        	if(!map.containsKey(ss)) {
        		if(map.containsValue(tt)) {
        			return false;
        		}
        		map.put(ss, tt);
        	}
        	else if(map.containsKey(ss) && map.get(ss) != tt) {
        		return false;
        	}
        }

        return true;
    }
	
	public int numDecodingsTabulation(String s) {
		if(s == null || s.length() == 0 || s.charAt(0) == '0') {
			return 0;
		}
		if(s.length() == 1) {
			return 1;
		}

		int[] dp = new int[s.length()];
		
		dp[s.length()-1] = s.charAt(s.length()-1) == '0' ? 0 : 1;
		if(s.charAt(s.length()-2) == '0') {
			dp[s.length()-2] = 0;
		}
		else {
			String sub1 = s.substring(s.length()-2, s.length());
			if(Integer.parseInt(sub1) > 26) {
				dp[s.length()-2] = dp[s.length()-1];
			}
			else {
				dp[s.length()-2] = dp[s.length()-1] + 1;
			}
		}

		for(int i = s.length()-3; i >= 0; --i) {
			if(s.charAt(i) == '0') {
				dp[i] = 0;
				continue;
			}
			String sub = s.substring(i, i+2);
			if(Integer.parseInt(sub) > 26) {
				dp[i] = dp[i+1];
			}
			else {
				dp[i] = dp[i+1] + dp[i+2];
			}
		}

		return dp[0];
	}
	
	public int longestConsecutive(int[] nums) {
		int max = 0;
        for(int i = 0; i < nums.length; ++i) {
        	max = Math.max(max, findLongestConsecutiveSeq(nums[i], nums, 1));
        }
        
        return max;
    }

	private int findLongestConsecutiveSeq(int val, int[] nums, int c) {
		int c1 = c;
		for(int i = 0; i < nums.length; ++i) {
			if(nums[i]-val != 1) {
				continue;
			}
			else {
				c1 = Math.max(c1, findLongestConsecutiveSeq(nums[i], nums, c+1));
			}
		}
		
		return c1;
	}
	
	public int longestConsecutiveOpt(int[] nums) {
		HashSet<Integer> set = new HashSet<>();
		for(int e : nums) {
			set.add(e);
		}
		
		int max = 0;
		for(int e : nums) {
			int count = 1;
			int left = e-1;
			int right = e+1;
			
			// Scan all the lower elements
			while(set.contains(left)) {
				++count;
				set.remove(left);
				--left;
			}
			// Scan all the greater elements
			while(set.contains(right)) {
				++count;
				set.remove(right);
				++right;
			}
			
			max = Math.max(max, count);
		}
		
		return max;
	}

	public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorderTraversal(root, res);
        return res;
    }

	private void inorderTraversal(TreeNode root, List<Integer> res) {
		if(root == null) {
			return;
		}
		res.add(root.val);
		inorderTraversal(root.left, res);
		inorderTraversal(root.right, res);
	}
	
	/**
	 * Generate structurally unique BSTs
	 */
	public List<TreeNode> generateTrees(int n) {
		return generateTrees(1, n);
	}

	private List<TreeNode> generateTrees(int start, int end) {
		// base condition
		List<TreeNode> list = new ArrayList<>();
		if(start > end) {
			list.add(null);
			return list;
		}
		
		// Split the left half and right half, i being the root
		for(int i = start; i <= end; ++i) {
			List<TreeNode> leftSubtrees = generateTrees(start, i-1);
			List<TreeNode> rightSubtrees = generateTrees(i+1, end);
			
			// construct the result
			for(int l = 0; l < leftSubtrees.size(); ++l) {
				TreeNode left = leftSubtrees.get(l);

				for(int r = 0; r < rightSubtrees.size(); ++r) {
					TreeNode right = rightSubtrees.get(r);
					TreeNode root = new TreeNode(i);
					root.left = left;
					root.right = right;
					list.add(root);
				}
			}
		}
		
		return list;
	}
	
	/**
	 * Nth catalan number
	 */
	public int getNthCatalanNumber(int n) {
		if(n == 0) {
			return 1;
		}
		
		int[] dp = new int[n+1];
		// C0 = 1
		dp[0] = 1;
		
		for(int i = 1; i <= n; ++i) {
			dp[i] = calculateCatalanNumer(dp, i);
		}
		
		return dp[n];
	}

	private int calculateCatalanNumer(int[] dp, int n) {
		int sum = 0;
		// Cn = Summation of CiCn-1-i; 0 <= i < n-1
		for(int i = 0; i <= n-1; ++i) {
			sum += dp[i]*dp[n-1-i];
		}
		
		return sum;
	}
	
	/**
	 * Symmetric Tree
	 */
	public boolean isSymmetric(TreeNode root) {
		if(root == null) {
			return true;
		}
		
		return isSymmetric(root.left, root.right);
    }

	private boolean isSymmetric(TreeNode left, TreeNode right) {
		if(left == null && right == null) {
			return true;
		}
		
		if (left != null && right != null && left.val == right.val) {
			return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
		}
		
		return false;
	}
	
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        if(root == null) {
            return res;
        }
        
        boolean leftToRight = true;
        queue.add(root);
        while(!queue.isEmpty()) {
        	List<Integer> level = new LinkedList<>();
        	int n = queue.size();
        	for(int i = 0; i < n; ++i) {
        		TreeNode node = queue.poll();
        		if(leftToRight) {
        			level.add(node.val);
        		}
        		else {
        			level.add(0, node.val);
        		}

        		if(node.left != null) { 
        			queue.add(node.left);
        		}
        		if(node.right != null) { 
        			queue.add(node.right);
        		}
        	}
        	leftToRight = !leftToRight;
        	res.add(level);
        }
        
        return res;
    }
	
	/**
	 * Majority Element II
	 */
	public List<Integer> majorityElement(int[] nums) {
		// need to find numbers with frequency > n/3 => Only possible for 2 numbers at max
		int c1 = 0, c2 = 0;
		int val1 = Integer.MIN_VALUE, val2 = Integer.MIN_VALUE;
		
		for(int i = 0; i < nums.length; ++i) {
			int num = nums[i];
			if(num == val1) {
				++c1;
			}
			else if(num == val2) {
				++c2;
			}
			else if(c1 == 0) {
				val1 = num;
				c1 = 1;
			}
			else if(c2 == 0) {
				val2 = num;
				c2 = 1;
			}
			else {
				--c1;
				--c2;
			}
		}

		List<Integer> res = new LinkedList<>();
		c1 = 0;
		c2 = 0;
		for(int i = 0; i < nums.length; ++i) {
			if(nums[i] == val1) {
				++c1;
			}
			else if(nums[i] == val2) {
				++c2;
			}
		}

		if(c1 > nums.length/3) {
			res.add(val1);
		}
		if(c2 > nums.length/3) {
			res.add(val2);
		}

		return res;
	}
	
	/**
	 * Maximum depth of a binary tree
	 */
	private static int l = 0;
	public int diameterMain(TreeNode root) {
		diameter(root);
		return l;
	}

	public int diameter(TreeNode root) {
		if(root == null) {
			return 0;
		}
		if(root.left == null && root.right == null) {
			return 1;
		}
		
		int left = diameter(root.left);
		int right = diameter(root.right);

		l = Math.max(l, 1+left+right);
		
		return 1 + Math.max(left, right);
	}
	
	public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        if(root.left == null && root.right == null) {
            return 1;
        }
        
        return 1+Math.max(maxDepth(root.left), maxDepth(root.right));
    }
	
	/**
	 * Reorder List
	 */
	public void reorderList(ListNode head) {
		// find mid
		ListNode mid = middleNode(head);
		
		ListNode rightHalf = mid.next;
		mid.next = null;
		// Reverse the rightHalf
		ListNode reversedRight = reverseList(rightHalf);
		
		ListNode left = head;
		ListNode right = reversedRight;

		while(right != null) {
			ListNode temp = left.next;
			left.next = right;
			left = temp;
			ListNode temp1 = right.next;
			right.next = left;
			right = temp1;
		}
	}

	private ListNode reverseList(ListNode head) {
		ListNode newHead = null;
		while(head != null) {
			ListNode temp = head.next;
			head.next = null;
			if(newHead == null) {
				newHead = head;
			}
			else {
				head.next = newHead;
				newHead = head;
			}

			head = temp;
		}

		return newHead;
	}

	private ListNode middleNode(ListNode head) {
        if(head == null || head.next == null) {
			return head;
		}
		
		ListNode slow = head;
		ListNode fast = head;
		while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
	
	/*
	public TreeNodeWithNext connect(TreeNodeWithNext root) {
		// connect left to right
		connectLeftToRight(root);
		// connect right to left
		connectRightToLeft(root.left, root.right);
		
        return root;
    }

	private void connectRightToLeft(TreeNodeWithNext left, TreeNodeWithNext right) {
		if(left == null || right == null || left.right == null) {
			return;
		}
		
		left.right.next = right.left;
		// call for sub-trees
		connectRightToLeft(left.left, left.right);
		connectRightToLeft(right.left, right.right);
	}

	private void connectLeftToRight(TreeNodeWithNext root) {
		if(root == null || root.left == null) {
			return;
		}
		
		root.left.next = root.right;
		connectLeftToRight(root.left);
		connectLeftToRight(root.right);
	}*/

	public TreeNodeWithNext connect(TreeNodeWithNext root) {
		if(root == null || root.left == null || root.right == null) {
			return root;
		}
		
		connect(root.left, root.right);
		
		return root;
	}

	private void connect(TreeNodeWithNext left, TreeNodeWithNext right) {
		if(left == null) {
			return;
		}
		
		left.next = right;
		connect(left.left, left.right);
		connect(left.right, right.left);
		connect(right.left, right.right);
	}
	
	/**
	 * Flatten binary tree
	 */
	public void flatten(TreeNode root) {
		flattenedNode = null;
		flatRes = null;
		if(root == null) {
			return;
		}

		flattenTree(root);
		root.left = null;
		root.right = flatRes.right;
		System.out.println();
	}

	private static TreeNode flattenedNode = null;
	private static TreeNode flatRes = null;
	private void flattenTree(TreeNode root) {
		if(root == null) {
			return;
		}
		
		if(flattenedNode == null) {
			flattenedNode = new TreeNode(root.val);
			flatRes = flattenedNode;
		}
		else {
			flattenedNode.right = new TreeNode(root.val);
			flattenedNode = flattenedNode.right;
		}
		flattenTree(root.left);
		flattenTree(root.right);
	}
	
	private TreeNode next;
	/**
	 * Algo: Recursively reach till the end create the flattened tree from last to first
	 * Since the order is preorder, right will be next to left and left will be next to root
	 */
	public void flattenRecursive(TreeNode root) {
		if(root == null) {
			return;
		}
		
		flattenRecursive(root.right);
		flattenRecursive(root.left);
		
		root.right = next;
		root.left = null;
		next = root;
	}
	
	/**
	 * Increasing triplet subsequence
	 */
	public boolean increasingTriplet(int[] nums) {
		int left = 0, right = 1;
		while(right < nums.length) {
			if(nums[right] > nums[right-1]) {
				++right;
			}
			else {
				left = right;
				++right;
			}
			
			if(right - left == 3) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Populating Next Right Pointers in Each Node II
	 */
	public TreeNodeWithNext connect2(TreeNodeWithNext root) {
		if(root == null || (root.left == null && root.right == null)) {
			return root;
		}
		
		connect2Rec(root);
		return root;
	}

	private void connect2Rec(TreeNodeWithNext root) {
		if(root == null) {
			return;
		}
		
		if(root.left != null) {
			if(root.right != null) {
				root.left.next = root.right;
			}
			else {
				root.left.next = getNextRight(root);
			}
		}
		
		if(root.right != null) {
			root.right.next = getNextRight(root);
		}
		
		connect2Rec(root.right);
		connect2Rec(root.left);
	}
	
	private TreeNodeWithNext getNextRight(TreeNodeWithNext root) {
		
		while(root.next != null) {
			root = root.next;
			if(root.left != null) {
				return root.left;
			}
			else if(root.right != null) {
				return root.right;
			}
		}
		
		// no nodes found to the right
		return null;
	}

	/**
	 * Sum Root to leaf nodes
	 */
	int pathSum = 0;
	public int sumNumbers(TreeNode root) {
		sumNumbers(root, 0);
		
		return pathSum;
	}

	private void sumNumbers(TreeNode root, int pathVal) {
		if(root == null) {
			return;
		}
		// Leaf node
		if(root.left == null && root.right == null) {
            pathVal = pathVal*10 + root.val;
			pathSum += pathVal;
		}
		
		sumNumbers(root.left, pathVal*10 + root.val);
		sumNumbers(root.right, pathVal*10 + root.val);
	}
	
	/**
	 * Pascal's Triangle
	 */
	public List<List<Integer>> generatePascalsTriangle(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < numRows; ++i) {
        	List<Integer> row = new ArrayList<>();
        	for(int j = 0; j <= i; ++j) {
        		if(j == 0 || j == i) {
        			row.add(1);
        		}
        		else {
        			row.add(res.get(i-1).get(j-1) + res.get(i-1).get(j));
        		}
        	}
        	res.add(row);
        }
        
        return res;
    }

	/**
	 * Pascal's Triangle II
	 * Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
	 * Note: Nth row is of pascal triangle is constructed as nC0, nC1,... nCn
	 * Recurrence relation : nCr = n-1Cr-1 + n-1Cr
	 */
	public List<Integer> getRow(int rowIndex) {
		List<List<Integer>> res = generatePascalsTriangle(rowIndex + 1);
		return res.get(rowIndex);
    }
	
	/**
	 * Letter Combinations of a Phone Number
	 */
	public List<String> letterCombinations(String digits) {
		List<String> res = new ArrayList<>();
		Map<Character, String> keypad = new HashMap<>();
		keypad.put('2', "abc");
		keypad.put('3', "def");
		keypad.put('4', "ghi");
		keypad.put('5', "jkl");
		keypad.put('6', "mno");
		keypad.put('7', "pqrs");
		keypad.put('8', "tuv");
		keypad.put('9', "wxyz");
		
		
        for(char d : digits.toCharArray()) {
        	List<String> temp = new ArrayList<>();
        	String key = keypad.get(d);
        	if(res.isEmpty()) {
        		for(char k : key.toCharArray()) {
        			temp.add(""+k);
        		}
        	}
        	else {
        		for(String val : res) {
        			for(char k : key.toCharArray()) {
        				String updatedVal = val + k;
        				temp.add(updatedVal);
        			}
        		}
        	}
        	
        	res = temp;
        }
        
        return res;
    }

	private static HashMap<Character, String> keypad = new HashMap<>();
	public List<String> letterCombinationsRec(String digits) {
		List<String> res = new ArrayList<>();
		if(digits == null || digits.isEmpty()) {
			return res;
		}
		keypad = new HashMap<Character, String>() {
			{
			put('2', "abc");
			put('3', "def");
			put('4', "ghi");
			put('5', "jkl");
			put('6', "mno");
			put('7', "pqrs");
			put('8', "tuv");
			put('9', "wxyz");
			}
		};
		
		letterCombinationsRec(digits, 0, new StringBuilder(), res);
		
		return res;
	}

	private void letterCombinationsRec(String digits, int index, StringBuilder str, List<String> res) {
		if(index >= digits.length()) {
			res.add(str.toString());
			return;
		}
		
		String letters = keypad.get(digits.charAt(index));
		for(int i = 0; i < letters.length(); ++i) {
			str.append(letters.charAt(i));
			letterCombinationsRec(digits, index+1, str, res);
			str.setLength(str.length()-1);
		}
	}
	
	/**
	 * Copy List with Random Pointer
	 */
	public NodeWithRandom copyRandomList(NodeWithRandom head) {
		
		if(head == null) {
			return null;
		}
		
		NodeWithRandom tempHead = head;

		// Clone nodes adjacently 1->1->2->2.....
		while(tempHead != null) {
			NodeWithRandom newNode = new NodeWithRandom(tempHead.val);
			NodeWithRandom temp = tempHead.next;
			tempHead.next = newNode;
			newNode.next = temp;
			tempHead = temp;
		}

		// Copy the random pointers
		tempHead = head;
		while(tempHead != null) {
			tempHead.next.random = tempHead.random == null ? null : tempHead.random.next;
			tempHead = tempHead.next.next;
		}

		// Separate out the lists
		NodeWithRandom res = head.next;
		NodeWithRandom tempHeadRes = res;
		tempHead = head;

	   while(tempHead != null) {
			tempHead.next = tempHead.next.next;
			if(tempHeadRes.next != null) {
				tempHeadRes.next = tempHeadRes.next.next;
				tempHeadRes = tempHeadRes.next;
			}
			tempHead = tempHead.next;
		}

		return res;
	}
	
	/**
	 * Balanced binary tree
	 */
	public boolean isBalanced(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) {
        	return true;
        }
        
        if(Math.abs(height(root.left) - height(root.right)) <= 1
        		&& isBalanced(root.left)
        		&& isBalanced(root.right)
        		) {
        	return true;
        }
        
        return false;
    }
	
	public int height(TreeNode root) {
		if(root == null) {
			return 0;
		}
		if(root.left == null && root.right == null) {
			return 1;
		}
		
		return 1+Math.max(height(root.left), height(root.right));
	}
	
	/**
	 * Ones and Zeroes
	 */
	public int findMaxForm(String[] strs, int m, int n) {
//		return findMaxForm(strs, 0, m, n, 0);
		return findMaxFormDP(strs, 0, m, n, 0, new Integer[strs.length][m+1][n+1]);
    }

	private int findMaxForm(String[] strs, int idx, int m, int n, int count) {
		if(m < 0 || n < 0) {
			return count -1;
		}
		
		if(idx >= strs.length) {
			return count;
		}
		
		int[] oneAndZeros = countZeroesAndOnes(strs[idx]);
		int count1 = findMaxForm(strs, idx+1, m-oneAndZeros[0], n-oneAndZeros[1], count+1);
		int count2 = findMaxForm(strs, idx+1, m, n, count);
		
		return Math.max(count1, count2);
	}
	
	private int findMaxFormDP(String[] strs, int idx, int m, int n, int count, Integer[][][] dp) {
//		if(m < 0 || n < 0) {
//			return count -1;
//		}
//		
		if(idx >= strs.length) {
			return count;
		}
		
		if(dp[idx][m][n] != null) {
			return dp[idx][m][n];
		}
		
		int[] oneAndZeros = countZeroesAndOnes(strs[idx]);
		int count1 = 0;
		if ((m - oneAndZeros[0] >= 0) && (n - oneAndZeros[1] >= 0)) {
			count1 = findMaxFormDP(strs, idx+1, m-oneAndZeros[0], n-oneAndZeros[1], count+1, dp);
		}
		int count2 = findMaxFormDP(strs, idx+1, m, n, count, dp);
		
		dp[idx][m][n] = Math.max(count1, count2);
		
		return dp[idx][m][n];
	}

	private int[] countZeroesAndOnes(String str) {
		int zeros = 0, ones = 0;
		for(char ch : str.toCharArray()) {
			if(ch == '0') {
				++zeros;
			}
			else {
				++ones;
			}
		}
		
		return new int[] {zeros, ones};
	}
	
	/**
	 * Longest valid parenthesis
	 */
	public int longestValidParentheses1(String s) {
		if(s == null || s.length() == 0) {
			return 0;
		}

        int max = 0;
        Stack<Integer[]> stack = new Stack<>();
        
        for(int i = 0; i < s.length(); ++i) {
        	char ch = s.charAt(i);
        	if(ch == '(') {
        		stack.add(new Integer[] {0, i});
        	}
        	else { // ch == ')'
        		if(stack.isEmpty() || stack.peek()[0] == 1) {
        			stack.add(new Integer[] {1, i});
        		}
        		else {
        			if(stack.peek()[0] == 0) {
        				stack.pop();
        				if(stack.isEmpty()) {
        					max = Math.max(max, i+1);
        				}
        				else {
        					max = Math.max(i-stack.peek()[1], max);
        				}
        			}
        		}
        		
        	}
        }
        
        return max;
    }
	
	/**
	 * Ones and Zeroes
	 */
	public int findMaxFormTabulation(String[] strs, int m, int n) {
		int[][][] dp = new int[strs.length][m+1][n+1];
		
		int[] onesAndZeros = countZeroesAndOnes(strs[0]);
		for(int i = 0; i <= m; ++i) {
			for(int j = 0; j <= n; ++j) {
				if ((i - onesAndZeros[0] >= 0) && (j - onesAndZeros[1] >= 0)) {
					dp[0][i][j] = 1;
				}
			}
		}
		
		for(int i = 1; i < strs.length; ++i) {
			int[] onesAndZeros1 = countZeroesAndOnes(strs[i]);
			for(int j = 0; j <= m; ++j) {
				for(int k = 0; k <= n; ++k) {
					if(j-onesAndZeros1[0] >= 0 && k-onesAndZeros1[1] >= 0) {
							dp[i][j][k] = Math.max(dp[i-1][j][k], dp[i-1][j-onesAndZeros1[0]][k-onesAndZeros1[1]]+1);
					}
					else {
						dp[i][j][k] = dp[i-1][j][k];
					}
				}
			}
		}
		
		return dp[strs.length-1][m][n];
	}
	
	/**
	 * Construct Binary Tree from Preorder and Inorder Traversal
	 * @return
	 */
	private static int index;
	public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null) {
        	return null;
        }
        
        if(preorder.length == 1) {
        	return new TreeNode(preorder[1]);
        }
        
        index = 0;
        TreeNode res = buildTree(preorder, inorder, 0, preorder.length);
        
        return res;
    }

	private TreeNode buildTree(int[] preorder, int[] inorder, int l, int r) {
		if(index >= preorder.length || (l > r)) {
			return null;
		}
		
		if(l == r) {
			return new TreeNode(inorder[l]);
		}
		
		int idx = findIdx(preorder[index], inorder, l, r);
		TreeNode root = new TreeNode(preorder[index++]);
		root.left = buildTree(preorder, inorder, l, idx-1);
		root.right = buildTree(preorder, inorder, idx+1, r);
		
		return root;
	}

	private int findIdx(int val, int[] inorder, int l, int r) {
		for(int i = l; i <= r; ++i) {
			if(val == inorder[i]) {
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * Longest Increasing Path in a Matrix
	 */
	public int longestIncreasingPath(int[][] matrix) {
		Integer[][] dp = new Integer[matrix.length][matrix[0].length];
		
		int max = 0;
		for(int i = 0; i < matrix.length; ++i) {
			for(int j = 0; j < matrix[0].length; ++j) {
				// check longest path for each index
				max = Math.max(max, longestIncreasingPath(matrix, i, j, dp));
			}
		}
		
		return maxPathLen;
    }
	
	
	static int maxPathLen = 0;
	public int longestIncreasingPath(int[][] matrix, int i, int j, Integer dp[][]) {
		if(i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) {
			return 0;
		}
		
		int up = 1, down = 1, left = 1, right = 1;
		// up
		if(i > 0 && matrix[i-1][j] > matrix[i][j]) {
			if(dp[i-1][j] != null) {
				up = 1 + dp[i-1][j];
			}
			else {
				up = 1 + longestIncreasingPath(matrix, i-1, j, dp);
			}
		}
		// down
		if(i < matrix.length-1 && matrix[i+1][j] > matrix[i][j]) {
			if(dp[i+1][j] != null) {
				down = 1 + dp[i+1][j];
			}
			else {
				down = 1 + longestIncreasingPath(matrix, i+1, j, dp);
			}
		}
		
		// left
		if(j > 0 && matrix[i][j-1] > matrix[i][j]) {
			if(dp[i][j-1] != null) {
				left = 1 + dp[i][j-1];
			}
			else {
				left = 1 + longestIncreasingPath(matrix, i, j-1, dp);
			}
		}

		// right
		if(j < matrix[0].length-1 && matrix[i][j+1] > matrix[i][j]) {
			if(dp[i][j+1] != null) {
				right = 1 + dp[i][j+1];
			}
			else {
				right = 1 + longestIncreasingPath(matrix, i, j+1, dp);
			}
		}

		dp[i][j] = Math.max(Math.max(left, right), Math.max(up, down));
		maxPathLen = Math.max(maxPathLen, dp[i][j]);
		return dp[i][j];
	}
	
	/**
	 * Minimum Depth of Binary Tree
	 */
	public int minDepth(TreeNode root) {
        if(root == null) {
        	return 0;
        }
        
        if(root.left == null && root.right == null) {
        	return 1;
        }
        
        if(root.left == null) {
        	return 1+minDepth(root.right);
        }

        if(root.right == null) {
        	return 1+minDepth(root.left);
        }
        
        return 1+Math.min(minDepth(root.left), minDepth(root.right));
    }
	
	/**
	 * Triangle : Given a triangle array, return the minimum path sum from top to bottom.
	 */
	public int minimumTotal(List<List<Integer>> triangle) {
        return minTotal(triangle, 0, 0, 0);
    }

	private int minTotal(List<List<Integer>> triangle, int sum, int r, int c) {
		if(r >= triangle.size()) {
			return sum;
		}
		
		int s = minTotal(triangle, sum+triangle.get(r).get(c), r+1, c);
		if(c < triangle.get(r).size()-1) {
			s = Math.min(s, minTotal(triangle, sum+triangle.get(r).get(c+1), r+1, c+1));
		}
		
		return s;
	}

	public int minimumTotalDP(List<List<Integer>> triangle) {
		int[] dp = new int[triangle.size()];
		for(int i = 0; i < dp.length; ++i) {
			dp[i] = Integer.MAX_VALUE;
		}
		
		List<Integer> l = new ArrayList<>();
		dp[0] = triangle.get(0).get(0);
		
		// row loop
		for(int i = 1; i < triangle.size(); ++i) {
			
			// column loop
			for(int j = i; j >= 0; --j) {
				if(j == 0) {
					dp[j] = dp[j] + triangle.get(i).get(j);
				}
				else {
					dp[j] = Math.min(dp[j], dp[j-1]) + triangle.get(i).get(j);
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < dp.length; ++i) {
			if(dp[i] < min) {
				min = dp[i];
			}
		}
		
		return min;
    }
	
	public int deepestLeavesSum(TreeNode root) {
		// length of path for leaf node -> Sum of those nodes
		Map<Integer, Integer> res = new HashMap<>();
		
		deepestLeavesSum(root, 0, res);
		
		Integer maxLen = Collections.max(res.keySet());
		
		return res.get(maxLen);
    }

	private void deepestLeavesSum(TreeNode root, int idx, Map<Integer, Integer> res) {
		if(root == null) {
			return;
		}
		
		if(root.left == null && root.right == null) {
			res.put(idx, res.getOrDefault(idx, 0) + root.val);
		}
		
		else {
			deepestLeavesSum(root.left, idx+1, res);
			deepestLeavesSum(root.right, idx+1, res);
		}
	}

}







