package programcreek.algolist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;
import java.util.stream.Collectors;

import datastructures.ListNode;
import datastructures.TreeNode;
import educative.tree.bfs.TreeNodeWithNext;
import educative.twoheaps.Interval;
import lombok.ToString;

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
	
	/**
	 * Word Ladder
	 */
	/*private int ladder;
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        ladder = Integer.MAX_VALUE;
        
        ladderLength(0, 1, beginWord, endWord, wordList);
        
        return ladder;
    }

	private void ladderLength(int idx, int currCount, String beginWord, String endWord, List<String> wordList) {
		if(idx >= wordList.size()) {
			return;
		}
		
		for(int i = idx; i < wordList.size(); ++i) {
			if(wordList.get(i).equals(beginWord)) {
				continue;
			}
			
			if(isLetterDiffOne(beginWord, wordList.get(i))) {
				if(wordList.get(i).equals(endWord)) {
					ladder = Math.min(currCount+1, ladder);
				}
				else {
					ladderLength(i+1, currCount+1, wordList.get(i), endWord, wordList);
				}
			}
		}
	}

	private boolean isLetterDiffOne(String word1, String word2) {
		int l = 0, r = word1.length()-1;
		int diffCount = 0;
		while(l <= r) {
			if(word1.charAt(l) != word2.charAt(l)) {
				++diffCount;
			}
			if(l != r) {
				if(word1.charAt(r) != word2.charAt(r)) {
					++diffCount;
				}
			}
			++l;
			--r;
			
			if(diffCount > 1) {
				return false;
			}
		}

		return true;
	}*/
	
	/**
	 * Gas Station
	 */
	public int canCompleteCircuit(int[] gas, int[] cost) {
		if(gas.length == 1) {
			if(gas[0] >= cost[0]) {
				return 0;
			}
			return -1;
		}
		
		for(int i = 0; i < gas.length; ++i) {
			// starting at station i
			if(canCompleteCircuit(gas, cost, 0, i, i)) {
				return i ;
			}
		}
		
		return -1;
    }

	private boolean canCompleteCircuit(int[] gas, int[] cost, int fuel, int idx, int startIdx) {
		// Add fuel
		fuel += gas[idx];
		// Can we go to the next station
		if(fuel < cost[idx]) {
			return false;
		}
		
		if((idx+1)%gas.length == startIdx) {
			return true;
		}
		
		fuel = fuel - cost[idx];
		return canCompleteCircuit(gas, cost, fuel, (idx+1)%gas.length, startIdx);
	}

	public int canCompleteCircuitOpt(int[] gas, int[] cost) {
		Queue<Integer> gasAndcostDiff = new LinkedList<>();
		
		int totalDiff = 0, currTotal = 0;
		int retIdx = 0;
		
		int i = 0;
		while(i < gas.length) {
			int diff = gas[i] - cost[i];
			gasAndcostDiff.add(diff);
			currTotal += diff;
			if(currTotal < 0) {
				// keep removing from the queue
				gasAndcostDiff.clear();
				// reset the currDiff to 0 and reset the start index to next index.
				totalDiff += currTotal;
				currTotal = 0;
				retIdx = i+1;
			}
			++i;
		}
		// last iteration value will still be there in the queue.
		totalDiff += currTotal;
		
		return totalDiff >= 0 ? retIdx : -1;
	}
	
	/**
	 * Maximum Product Subarray
	 */
	public int maxProduct(int[] nums) {
		int prevMinProd = nums[0];
		int prevMaxProd = nums[0];
		int maxProd = nums[0];
		
		for(int i = 1; i < nums.length; ++i) {
			int tempPrevMaxProd = Math.max(Math.max(prevMaxProd * nums[i], prevMinProd * nums[i]), nums[i]);
			prevMinProd = Math.min(Math.min(prevMaxProd * nums[i], prevMinProd * nums[i]), nums[i]);
			prevMaxProd = tempPrevMaxProd; // used temp as can't update prevMaxProd as it is used in prevMinProd calculation
			
			maxProd = Math.max(maxProd, prevMaxProd);
		}
		
		return maxProd;
    }
	
	/**
	 * Partition List
	 */
	public ListNode partition(ListNode head, int x) {
		if(head == null || head.next == null) {
            return head;
        }
		
		ListNode left = null;
		ListNode newHead = null;
		ListNode right = null;
		ListNode rightHead = null;
		
		while(head != null) {
			if(head.val < x) {
				if(left == null) {
					left = head;
					head = head.next;
					left.next = null;
					newHead = left;
				}
				else {
					left.next = head;
					head = head.next;
					left = left.next;
					left.next = null;
				}
			}
			else {
				if(right == null) {
					right = head;
					head = head.next;
					right.next = null;
					rightHead = right;
				}
				else {
					right.next = head;
					head = head.next;
					right = right.next;
					right.next = null;
				}
			}
		}
		
		if(newHead != null) {
			left.next = rightHead;
			return newHead;
		}
		else {
			return rightHead;
		}
	}
	
	/**
	 * Verifying an Alien Dictionary
	 */
	public boolean isAlienSorted(String[] words, String order) {
		if(words == null || words.length <= 1) {
			return true;
		}
		
		Map<Character, Integer> charMapping = new HashMap<>();
		for(int i = 0; i < order.length(); ++i) {
			char ch = order.charAt(i);
			charMapping.put(ch, i);
		}
		
		for(int i = 0; i < words.length-1; ++i) {
			
			if(!compareAlienWords(words[i], words[i+1], charMapping)) {
				return false;
			}
		}
        
		return true;
    }

	private boolean compareAlienWords(String word1, String word2, Map<Character, Integer> charMapping) {
		int i = 0, j = 0;
		while(i < word1.length() && j < word2.length()) {
			char ch1 = word1.charAt(i++);
			char ch2 = word2.charAt(j++);
			
			if(charMapping.get(ch1) - charMapping.get(ch2) < 0) {
				return true;
			}
			if(charMapping.get(ch1) - charMapping.get(ch2) > 0) {
				return false;
			}
		}
		
		// If all chars matched till word1, we still have more chars in word1 (since it will become larger that word2), 
		// then it's not sorted.
		if(word1.length() > word2.length()) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Intersection of Two Linked Lists
	 */
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if(headA == null && headB == null) {
			return null;
		}
		
		if(headA == null) {
			return headB;
		}
		if(headB == null) {
			return headA;
		}

		ListNode currHeadA = headA;
		ListNode currHeadB = headB;

		while(currHeadA != currHeadB) {

			currHeadA = currHeadA.next;
			currHeadB = currHeadB.next;
            
            if(currHeadA == currHeadB) {
				return currHeadA;
			}
			
			if(currHeadA == null) {
				currHeadA = headB;
			}

			if(currHeadB == null) {
				currHeadB = headA;
			}
		}
		
		return currHeadA;
    }
	
	/**
	 * Dungeon Game
	 */
	public int calculateMinimumHP(int[][] dungeon) {
        return calculateMinimumHP(dungeon, 0, 0, 1, 1);
    }

	private int calculateMinimumHP(int[][] dungeon, int i, int j, int cost, int health) {
		if(i >= dungeon.length || j >= dungeon[0].length) {
			return cost;
		}
	
		int val = dungeon[i][j];
		if(val < 0) {
			val = Math.abs(val);
			if(health > 1) {
				if(val < health-1) {
					health = health - val;
					val = 0;
				}
				else {
					val -= (health-1);
				}
			}
			else {
				val += (1 - health);
			}
			cost += val;
			health = 1;
		}
		else {
			health += dungeon[i][j];
		}
		
		// right
		int cost1 = calculateMinimumHP(dungeon, i, j+1, cost, health);
		// down
		int cost2 = calculateMinimumHP(dungeon, i+1, j, cost, health);
		
		if(j+1 >= dungeon[0].length) {
			return cost2;
		}
		if(i+1 >= dungeon.length) {
			return cost1;
		}
		
		return Math.min(cost1, cost2);
	}
	
	/**
	 * Path with Maximum Gold 
	 */
	public int getMaximumGold(int[][] grid) {
		int maxGold = Integer.MIN_VALUE;
		for(int i = 0; i < grid.length; ++i) {
			for(int j = 0; j < grid[0].length; ++j) {
				maxGold = Math.max(maxGold, getMaxGold(grid, i, j, 0));
			}
		}
		
		return maxGold;
	}

	private int getMaxGold(int[][] grid, int row, int col, int currMax) {
		if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 0) {
			return currMax;
		}
		
		int temp = grid[row][col];
		currMax += grid[row][col];

		grid[row][col] = 0;
		
		int up = getMaxGold(grid, row-1, col, currMax);
		int down = getMaxGold(grid, row+1, col, currMax);
		int left = getMaxGold(grid, row, col-1, currMax);
		int right = getMaxGold(grid, row, col+1, currMax);
		
		grid[row][col] = temp;
		
		return Math.max(Math.max(up, down), Math.max(left, right));
	}

	//=====================
	//=====================
	//===== DP GFG ========
	//=====================
	//=====================
	
	/**
	 * Nth catalan number
	 * (Unique Binary Search Trees)
	 */
	public int getNthCatalanNumber1(int n) {
		int[] dp = new int[n+1];
		dp[0] = 1;
		
		for(int i = 1; i <= n; ++i) {
			int s = 0;
			for(int j = 0; j < i; ++j) {
				s += dp[j]*dp[i-1-j];
			}
			
			dp[i] = s;
		}
		
		return dp[n];
	}

	
	/**
	 * Coin change
	 */
	private int minCoins = Integer.MAX_VALUE;
	public int coinChange(int[] coins, int amount) {
		coinChange(coins, 0, amount);
		return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }

	private void coinChange(int[] coins, int coinCount, int amount) {
		if(amount < 0) {
			return;
		}
		
		if(amount == 0) {
			minCoins = Math.min(coinCount, minCoins);
		}
		
		for(int i = 0; i < coins.length; ++i) {
			coinChange(coins, coinCount+1, amount-coins[i]);
		}
	}

	public int coinChangeDP(int[] coins, int amount) {
		int[] dp = new int[amount+1];
		for(int i = 1; i < dp.length; ++i) {
			if(i % coins[0] == 0) {
				dp[i] = i/coins[0];
			}
			else {
				dp[i] = Integer.MAX_VALUE;
			}
		}
		
		for(int c = 1; c < coins.length; ++c) {
			for(int i = coins[c]; i <= amount; ++i) {
				if(dp[i-coins[c]] != Integer.MAX_VALUE) {
					dp[i] = Math.min(dp[i], 1 + dp[i-coins[c]]);
				}
			}
		}
		
		return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

	/**
	 * Partition Equal Subset Sum
	 */
	public boolean canPartition(int[] nums) {
		int sum = Arrays.stream(nums).reduce(0, Integer::sum);
		if(sum%2 != 0) {
			return false;
		}
		
		sum /= 2;
		
		boolean[] dp = new boolean[sum+1];
		dp[0] = true;

		for(int i = 0; i < nums.length; ++i) {
			for(int j = sum; j >= nums[i]; --j) {
				dp[j] = dp[j] || dp[j-nums[i]];	
			}
		}
		
		return dp[sum];
	}
	
	/**
	 * Cutting a Rod
	 */
	public int cutRod(int price[], int n) {
		int[] dp = new int[n+1];
		dp[0] = 0;
		
		for(int i = 1; i <= n; ++i) {
			for(int j = i; j <= n; ++j) {
				dp[j] = Math.max(dp[j], price[i-1] + dp[j-i]);
			}
		}
		
		return dp[n];
	}
	
	public int lengthOfLIS(int[] nums) {
		if(nums == null || nums.length == 0) {
			return 0;
		}
		return lengthOfLIS(nums, 0, Integer.MIN_VALUE, 0);
	}

	private int lengthOfLIS(int[] nums, int idx, int prev, int len) {
		if(idx >= nums.length) {
			return len;
		}

		int max = Integer.MIN_VALUE;
		if(nums[idx] > prev) {
			max = lengthOfLIS(nums, idx+1, nums[idx], len+1);
		}
		max = Math.max(max, lengthOfLIS(nums, idx+1, prev, len));
		
		return max;
	}
	
	public int lengthOfLIS_DP(int[] nums) {
		if(nums == null || nums.length == 0) {
			return 0;
		}

		int[] dp = new int[nums.length];
		for(int i = 0; i < nums.length; ++i) {
			dp[i] = 1;
		}
		
		for(int i = 1; i < nums.length; ++i) {
			for(int j = 0; j < i; ++j) {
				if(nums[j] < nums[i] && dp[j]+1 > dp[i]) {
					dp[i] = dp[j] + 1;
				}
			}
		}
		
		return Arrays.stream(dp).boxed().max((x,y) -> x - y).get();
	}
	
	/**
	 * Remove All Adjacent Duplicates in String II
	 */
	@ToString
	class CharAndFreq {
		public CharAndFreq(char ch, int freq) {
			this.ch = ch;
			this.freq = freq;
		}

		public char ch;
		public int freq;
	}

	public String removeDuplicates(String s, int k) {
		Stack<CharAndFreq> stack = new Stack<>();
		char prev = s.charAt(0);
		stack.add(new CharAndFreq(prev, 1));
		
		for(int i = 1; i < s.length(); ++i) {
			char ch = s.charAt(i);
			if(prev != ' ' && ch == prev) {
				stack.add(new CharAndFreq(ch, stack.peek().freq+1));
				if(stack.peek().freq == k) { // remove k elements
					for(int j = 0; j < k; ++j) {
						stack.pop();
					}
					prev = !stack.isEmpty() ? stack.peek().ch : ' ';
				}
			}
			else {
				stack.add(new CharAndFreq(ch, 1));
				prev = ch;
			}
		}
		
		String res = "";
		while(!stack.isEmpty()) {
			res = stack.pop().ch + res;
		}
		
		return res.toString();
	}
	
	/**
	 * Number of Submatrices That Sum to Target
	 */
	private static int countTarget = 0;
	public int numSubmatrixSumTarget(int[][] matrix, int target) {
        numSubmatrixSumTarget(matrix, target, 0, 0, 0, 0, 0);
        return countTarget;
    }

	private void numSubmatrixSumTarget(int[][] matrix, int target, int rStart, int rEnd, int cStart, int cEnd, int currSum) {	
		if(rStart > rEnd || cStart > cEnd || rEnd >= matrix.length || cEnd >= matrix[0].length) {
			return;
		}
		if(currSum == target) {
			System.out.println(rStart + ", " + rEnd + ", " + cStart + ", " + cEnd);
			++countTarget;
		}
		
		// Expand row towards down
		if(rEnd+1 < matrix.length) {
			// add the entire row
			int sum = 0;
			for(int i = cStart; i <= cEnd; ++i) {
				sum += matrix[rEnd+1][i];
			}
			numSubmatrixSumTarget(matrix, target, rStart, rEnd+1, cStart, cEnd, currSum+sum);
		}
		
		// Shrink towards down
		if(rEnd > rStart) {
			// subtract the entire row
			int sum = 0;
			for(int i = cStart; i <= cEnd; ++i) {
				sum += matrix[rStart][i];
			}
			numSubmatrixSumTarget(matrix, target, rStart+1, rEnd, cStart, cEnd, currSum-sum);
		}
		
		// Expand the right
		if(cEnd+1 < matrix[0].length) {
			// add the entire col
			int sum = 0;
			for(int i = rStart; i <= rEnd; ++i) {
				sum += matrix[i][cEnd+1];
			}
			numSubmatrixSumTarget(matrix, target, rStart, rEnd, cStart, cEnd+1, currSum+sum);
		}
		
		// Shrink the right
		if(cEnd > cStart) {
			// subtract the entire col
			int sum = 0;
			for(int i = rStart; i < rEnd; ++i) {
				sum += matrix[i][cStart];
			}
			numSubmatrixSumTarget(matrix, target, rStart, rEnd, cStart+1, cEnd, currSum-sum);
		}
	}
	
	/**
	 * Combination Sum IV
	 */
    public int combinationSum4(int[] nums, int target) {

    	return combinationSum4(nums, target, 0, 0);
    }

	private int combinationSum4(int[] nums, int target, int currSum, int count) {
		if(currSum == target) {
			return ++count;
		}
		
		if(currSum > target) {
			return count;
		}
		
		for(int i = 0; i < nums.length; ++i) {
			count = combinationSum4(nums, target, currSum + nums[i], count);
		}
		
		return count;
	}

    public int combinationSum4DP(int[] nums, int target) {
    	int[] dp = new int[target+1];
    	dp[0] = 1;
    	
    	for(int i = 1; i <= target; ++i) {
    		for(int j = 0; j < nums.length; ++j) {
    			if(i - nums[j] >= 0) {
    				dp[i] = dp[i] + dp[i-nums[j]];
    			}
    		}
    	}
    	
    	return dp[target];
    }
    
    /**
     * Minimum Path Sum
     */
    public int minPathSum(int[][] grid) {
    	int[][] dp = new int[grid.length][grid[0].length];
    	dp[0][0] = grid[0][0];
    	
    	for(int i = 0; i < grid.length; ++i) {
    		for(int j = 0; j < grid[0].length; ++j) {
    			if(i == 0 && j == 0) {
    				continue;
    			}
    			int up = Integer.MAX_VALUE, left = Integer.MAX_VALUE;
    			if(i-1 >= 0) {
    				up = dp[i-1][j];
    			}
    			if(j-1 >= 0) {
    				left = dp[i][j-1];
    			}
    			dp[i][j] = grid[i][j] + Math.min(up, left);
    		}
    	}
    	
    	return dp[grid.length-1][grid[0].length-1];
    }
    
    /**
     * Sliding window maximum
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
    	if(nums == null || nums.length < k) {
    		return new int[] {};
    	}
    	
    	int i = 0;
    	Deque<Integer> queue = new LinkedList<>();
    	List<Integer> res = new LinkedList<>();
    	int[] ret = new int[nums.length-k+1];
    	while(i < nums.length) {
    		if(!queue.isEmpty() && queue.peek() < i-k+1) {
    			queue.poll();
    		}
    		
    		while(!queue.isEmpty() && nums[i] > nums[queue.peekLast()]) {
    			queue.removeLast();
    		}
    		
    		queue.add(i);
    		
    		if(i >= k-1) {
    			res.add(nums[queue.peek()]);
    		}
    		++i;
    	}
    	
    	for(int j = 0; j < res.size(); ++j) {
    		ret[j] = res.get(j);
    	}
    	
    	return ret;
    }
    
    /**
     * Next larger element
     */
    public int[] nextLargerElement(int[] nums) {
    	
    	Stack<Integer> stack = new Stack<>();
    	int[] res = new int[nums.length];
    	for(int i = 0; i < res.length; ++i) {
    		res[i] = -1;
    	}
    	
    	for(int i = 0; i < nums.length; ++i) {
    		while(!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
    			res[stack.pop()] = nums[i];
    		}
    		stack.add(i);
    	}
    	
    	return res;
    }
   
    /**
     * Next Greater Element I
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        
        int j = 0; // counter to number of insertions in res
        Map<Integer, Integer> res = new HashMap<>();
        for(int k = 0; k < nums1.length; ++k) {
            res.put(nums1[k], -1);
        }
        
        for(int i = 0; i < nums2.length; ++i) {
            if(j >= nums1.length) {
                break;
            }
            
            while(!stack.isEmpty() && stack.peek() < nums2[i]) {
                int key = stack.pop();
                if(res.containsKey(key)) {
                    res.put(key, nums2[i]);
                    ++j;
                }
            }
            stack.add(nums2[i]);
        }
        
        int[] ret = new int[nums1.length];
        for(int k = 0; k < nums1.length; ++k) {
            ret[k] = res.get(nums1[k]);
        }
        
        return ret;
    }
    
    /**
     * Balance binary tree
     * Algo : Read all the nodes in InOrder
     * 		  Construct a new binary tree with mid being the root, 
     * 		  1 to mid-1 goes to left tree and mid+1 right goes to right.
     * 		  We build this tree recursively.
     */
    public TreeNode balanceBST(TreeNode root) {
    	List<TreeNode> nodes = new ArrayList<>();
    	readNodesInorder(root, nodes);
    	TreeNode newRoot = constructBalancedBinaryTree(nodes, 0, nodes.size()-1);
    	
    	return newRoot;
    }

	private TreeNode constructBalancedBinaryTree(List<TreeNode> nodes, int left, int right) {

		if(left > right) {
			return null;
		}
		
		int mid = (left+right)/2;
		TreeNode node = nodes.get(mid);
		node.left = constructBalancedBinaryTree(nodes, left, mid-1);
		node.right = constructBalancedBinaryTree(nodes, mid+1, right);
		
		return node;
	}

	private void readNodesInorder(TreeNode root, List<TreeNode> nodes) {
		if(root == null) {
			return;
		}
		
		readNodesInorder(root.left, nodes);
		nodes.add(root);
		readNodesInorder(root.right, nodes);
	}
	
	/**
	 * Maximum Number of Events That Can Be Attended
	 */
	public int maxEvents(int[][] events) {
        List<Interval> eventList = Arrays.stream(events).map(x -> new Interval(x[0], x[1])).collect(Collectors.toList());
        Collections.sort(eventList, (x,y) -> x.end - y.end);
        
        int count = 0;
        TreeSet<Integer> days = new TreeSet<>();
        // Total possible available days (constraint from problem statement)
        for(int i = 0; i < 100000; ++i) {
        	days.add(i);
        }

        for(int i = 0; i < eventList.size(); ++i) {
        	Interval event = eventList.get(i);
        	Integer upperBound = days.ceiling(event.start);
        	if(upperBound == null || upperBound > event.end) {
        		continue;
        	}
        	else {
        		days.remove(upperBound);
        		++count;
        	}

        }
        
        return count;
    }
	
	/**
	 * K Path sum of a binary tree
	 */
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> res = new ArrayList<>();
		pathSum(root, sum, 0, new ArrayList<>(), res);
		
		return res;
    }

	private void pathSum(TreeNode root, int target, int currSum, List<Integer> currPath, List<List<Integer>> res) {
		if(root == null) {
			return;
		}
		if (root.left == null && root.right == null && currSum + root.val == target) {
			List<Integer> temp = new ArrayList<>(currPath);
			temp.add(root.val);
			res.add(temp);
			return;
		}
		
		currPath.add(root.val);
		currSum += root.val;
		pathSum(root.left, target, currSum, currPath, res);
		pathSum(root.right, target, currSum, currPath, res);
		currPath.remove(currPath.size()-1);
	}
	
	public int minimumTotal1(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size()];
        dp[0] = triangle.get(0).get(0);
        for(int i = 1; i < dp.length; ++i) {
        	dp[i] = Integer.MAX_VALUE;
        }
        
        for(int i = 1; i < triangle.size(); ++i) {
        	for(int j = i; j >= 0; --j) {
        		if(j > 0) {
        			dp[j] = Math.min(dp[j], dp[j-1]) + triangle.get(i).get(j);
        		}
        		else {
        			dp[j] += triangle.get(i).get(j);
        		}
        	}
        }
        
        return Arrays.stream(dp).boxed().min((a,b) -> a - b).get();
    }
	
	static class NaryNode {
	    public int val;
	    public List<NaryNode> children;

	    public NaryNode() {}

	    public NaryNode(int _val) {
	        val = _val;
	    }

	    public NaryNode(int _val, List<NaryNode> _children) {
	        val = _val;
	        children = _children;
	    }
	};
	
	public List<Integer> preorder(NaryNode root) {
        List<Integer> res = new ArrayList<>();
        
        preorder(root, res);
        
        return res;
    }

	private void preorder(NaryNode root, List<Integer> res) {
		if(root == null) {
			return;
		}
		
		// insert root
		res.add(root.val);
		for(NaryNode child : root.children) {
			preorder(child, res);
		}
	}
	
	/**
	 * N Queen Problem
	 */
	/*public List<List<String>> solveNQueens(int n) {
		List<List<Integer[]>> res = new ArrayList<>();
        solveNQueens(0, n, res, new ArrayList<Integer[]>());
        
        return null;
    }

	private void solveNQueens(int row, int n, List<List<Integer[]>> res, List<Integer[]> currPath) {
		if(row == n) {
			res.add(currPath);
			return;
		}
		
		// find a position in row
		for(int col = 0; col < n; ++col) {
			// for each col check if the row, col is safe or not
			if(isSafe(row, col))
			
			currPath.add(new Integer[] {row, col});
		}
		
		// if this is a right position to insert
		
	}*/
	
	
	public int maxArea1(int[] height) {
		
		int left = 0;
		int right = height.length-1;
		
		int max = Integer.MIN_VALUE;
		
		while(left < right) {
			int area = (right-left)*Math.min(height[left], height[right]);
			max = Math.max(max, area);
			
			if(height[left] < height[right]) {
				++left;
			}
			else {
				--right;
			}
		}
		
		return max;
	}
	
	/**
	 * Candy
	 */
	public int candy1(int[] ratings) {
		int[] candyArr = new int[ratings.length];
		candyArr[0] = 1;
		
		int candy = 1;
		for(int i = 1; i < ratings.length; ++i) {
			if(ratings[i] > ratings[i-1]) {
				candyArr[i] = candyArr[i-1] + 1;
			}
			else {
				candyArr[i] = 1;
			}
		}
		
		// After the loop, last value of the candy array will be correctly populated.
		// since it only has left neighhbour. Candy array is populated with values based on left student.
		// We now need to proceed backwards to check the neighbours to the right.
		
		candy = candyArr[candyArr.length-1];
		for(int i = ratings.length-2; i >= 0; --i) {
			if(ratings[i] > ratings[i+1]) {
				candy += 1;
			}
			else {
				candy = 1;
			}
			candyArr[i] = Math.max(candy, candyArr[i]);
		}
		
		return Arrays.stream(candyArr).boxed().reduce(0, (a,b) -> a+b);
	}
	
	public int trap1(int[] height) {
		int[] maxLeft = new int[height.length];
		int[] maxRight = new int[height.length];
		
		int currMaxL = height[0];
		for(int i = 1; i < height.length; ++i) {
			maxLeft[i] = currMaxL;
			currMaxL = Math.max(currMaxL, height[i]);
		}
		
		int currMaxR = height[height.length-1];
		for(int i = height.length-2; i >= 0; --i) {
			maxRight[i] = currMaxR;
			currMaxR = Math.max(currMaxR, height[i]);
		}
		
		int water = 0;
		for(int i = 1; i < height.length-1; ++i) {
			int w = Math.min(maxLeft[i], maxRight[i]) - height[i];
			if(w > 0) {
				water += w;
			}
		}
		
		return water;
	}
	
	/**
	 * Count binary substring
	 */
	public int countBinarySubstrings(String s) {

		return 0;
    }
	
	/**
	 * Furthest Building You Can Reach
	 */
	public int furthestBuilding(int[] heights, int bricks, int ladders) {
		Integer[][][] dp = new Integer[heights.length][bricks+1][ladders+1];
		int last = furthestBuilding(heights, bricks, ladders, 0, dp);
		
		return last;
    }

	private int furthestBuilding(int[] heights, int bricks, int ladders, int idx, Integer[][][] dp) {
		if(idx >= heights.length-1) {
			// reached the end
			return heights.length-1;
		}
		
		if(heights[idx] > heights[idx+1]) {
			return furthestBuilding(heights, bricks, ladders, idx+1, dp);
		}
		
		// can't go any further
		if((heights[idx+1] > heights[idx]) && ladders == 0 && (bricks < heights[idx+1] - heights[idx])) {
			return idx;
		}
		
		if(dp[idx][bricks][ladders] != null) {
			return dp[idx][bricks][ladders];
		}
		
		// take bricks first
		int idx1 = idx;
		int jump = heights[idx+1] - heights[idx];
		if(jump <= bricks) {
			idx1 = furthestBuilding(heights, bricks-jump, ladders, idx+1, dp);
		}

		int idx2 = idx;
		if(ladders > 0) {
			idx2 = furthestBuilding(heights, bricks, ladders-1, idx+1, dp);
		}

		int ret = Math.max(idx1, idx2);
		dp[idx][bricks][ladders] = ret;
		
		return ret;
	}
	
	public int furthestBuildingOpt(int[] heights, int bricks, int ladders) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		
		for(int i = 0; i < heights.length-1; ++i) {
			int diff = heights[i+1] - heights[i];
			if(diff > 0) {
				minHeap.add(diff);
			}
			
			// If the number of ladders are less than minHeap size, use bricks
			if(minHeap.size() > ladders) {
				int smallestJump = minHeap.poll();
				bricks -= smallestJump;
				if(bricks < 0) { // did not have enough bricks
					return i; // cannot go to i+1
				}
			}
		}
		
		return heights.length-1;
	}
	
	/**
	 * Power of Three
	 */
	public boolean isPowerOfThree(int n) {
        if(n == 1) {
        	return true;
        }

        if(n%3 != 0) {
        	return false;
        }
        
        return isPowerOfThree(n/3);
    }
	
	/**
	 * Validate Binary Search Tree
	 * Algo: The value of node is only allowed between bounds
	 * for left subtree the bound is anything less than the root node value.
	 * for right subtree the bound is anything more that the root node value.
	 */
	public boolean isValidBST(TreeNode root) {
		return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

	private boolean isValidBST(TreeNode root, int minValue, int maxValue) {
		if(root == null) {
			return true;
		}
        
        if(root.val == Integer.MIN_VALUE && root.left != null) {
            return false;
        }
        if(root.val == Integer.MAX_VALUE && root.right != null) {
            return false;
        }
		
        // Checking the bounds
		if(root.val < minValue || root.val > maxValue) {
			return false;
		}
		
		// now for the left subtree, bounds will be min to root.val-1
		// for the right subtree, bounds will be root.val+1, max
		return isValidBST(root.left, minValue, root.val-1) && isValidBST(root.right, root.val+1, maxValue);
	}
	
	/**
	 * Construct BST from InOrder and postOrder
	 */
	int poIdx = 0;
	public TreeNode buildTreeIP(int[] inorder, int[] postorder) {
        poIdx = postorder.length-1;
        TreeNode res = buildTreeIP(inorder, postorder, 0, inorder.length-1);
        
        return res;
    }

	private TreeNode buildTreeIP(int[] inorder, int[] postorder, int ioStart, int ioEnd) {
		if(ioStart > ioEnd) {
			return null;
		}
		
		if(ioStart == ioEnd) {
			--poIdx;
			return new TreeNode(inorder[ioStart]);
		}
		
		TreeNode root = new TreeNode(postorder[poIdx]);
		int idx = findIdx(inorder, postorder[poIdx], ioStart, ioEnd);
		--poIdx;
		
		root.right = buildTreeIP(inorder, postorder, idx+1, ioEnd);
		root.left = buildTreeIP(inorder, postorder, ioStart, idx-1);
		
		return root;
	}

	private int findIdx(int[] inorder, int val, int stIdx, int endIdx) {
		for(int i = stIdx; i <= endIdx; ++i) {
			if(inorder[i] == val) {
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * Convert Sorted Array to Binary Search Tree
	 */
	public TreeNode sortedArrayToBST(int[] nums) {
		return sortedArrayToBST(nums, 0, nums.length-1);
    }

	private TreeNode sortedArrayToBST(int[] nums, int start, int end) {
		if(start > end) {
			return null;
		}
		
		if(start == end) {
			return new TreeNode(nums[start]);
		}
		
		int mid = (start+end)/2;
		
		TreeNode root = new TreeNode(nums[mid]);
		root.left = sortedArrayToBST(nums, start, mid-1);
		root.right = sortedArrayToBST(nums, mid+1, end);
		
		return root;
	}
	
	/**
	 * Convert Sorted List to Binary Search Tree
	 */
	public TreeNode sortedListToBST(ListNode head) {
        if(head == null) {
        	return null;
        }

        if(head.next == null) {
        	return new TreeNode(head.val);
        }
        
        // find mid: mid will be the second half and head will be cut-off before mid
        ListNode mid = findMid(head);
        TreeNode root = new TreeNode(mid.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(mid.next);
        
        return root;
    }

	private ListNode findMid(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
		ListNode prev = null;
		
		while(fast != null && fast.next != null) {
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		
		prev.next = null; // breaking the chain; Left chain will still point to head.
		return slow; // This is the right chain
	}
	
	/**
	 * Surrounded Regions
	 */
	public void solve(char[][] board) {
		// Mark all the regions in touch with O at the boundaries with a special char '*'
		// On the boundary columns
		for(int r = 0; r < board.length; ++r) {
			if(board[r][0] == 'O') { // first column
				// mark the areas
				markBoundaryWithZero(board, r, 0);
			}
			if(board[r][board[0].length-1] == 'O') { // last column
				// mark the areas
				markBoundaryWithZero(board, r, board[0].length-1);
			}
		}

		// On the boundary rows
		for(int c = 0; c < board[0].length; ++c) {
			if(board[0][c] == 'O') { // first row
				// mark the areas
				markBoundaryWithZero(board, 0, c);
			}
			if(board[board.length-1][c] == 'O') { // last row
				// mark the areas
				markBoundaryWithZero(board, board.length-1, c);
			}
		}
		
		// All the remaining 0s can be converted to X as it is not surrounded by 0 at any end
		for(int r = 0; r < board.length; ++r) {
			for(int c = 0; c < board[0].length; ++c) {
				if(board[r][c] == 'O') {
					board[r][c] = 'X';
				}
				else if(board[r][c] == '*') {
					board[r][c] = 'O';
				}
			}
		}
    }

	private void markBoundaryWithZero(char[][] board, int r, int c) {
		if(r < 0 || r > board.length || c < 0 || c > board[0].length || board[r][c] == 'X') {
			return;
		}
		
		board[r][c] = '*'; // marked

		// check the surrounding reasons
		// up
		if(r > 0 && board[r-1][c] == 'O') {
			markBoundaryWithZero(board, r-1, c);
		}
		
		// down
		if(r < board.length-1 && board[r+1][c] == 'O') {
			markBoundaryWithZero(board, r+1, c);
		}
		
		// left
		if(c > 0 && board[r][c-1] == 'O') {
			markBoundaryWithZero(board, r, c-1);
		}

		// right
		if(c < board[0].length-1 && board[r][c+1] == 'O') {
			markBoundaryWithZero(board, r, c+1);
		}
	}
	
	/**
	 * Insertion Sort List
	 */
	public ListNode insertionSortList(ListNode head) {
        ListNode newHead = null;
        ListNode insHead = head;
        ListNode iterHead = head;
        ListNode prevToInsHead = null;
        
        while(insHead != null) {
        	iterHead = insHead.next;
        	ListNode prev = insHead;
        	while(iterHead != null) {
        		if(iterHead.val < insHead.val) {
        			// swap nodes
        			// Unlink iterNode
        			prev.next = iterHead.next;
        			if(prevToInsHead == null) {
        				iterHead.next = insHead;
        			}
        			else {
        				prevToInsHead.next = iterHead;
        				iterHead.next = insHead;
        			}
        			insHead = iterHead;
        		}
        		prev = iterHead;
        		iterHead = iterHead.next;
        	}
        	if(newHead == null) {
        		newHead = insHead;
        	}
        	prevToInsHead = insHead;
        	insHead = insHead.next;
        }
        
        return newHead;
    }
	
	public String convertToTitle(int columnNumber) {
        String res = "";
        while(columnNumber > 26) {
            int rem = columnNumber%26;
            // find the char
            res = getCharFromVal(rem) + res;
            columnNumber /= 26;
        }
        
        res = getCharFromVal(columnNumber) + res;
        return res;
    }
    
    private char getCharFromVal(int rem) {
        return (char) (64+rem);
    }
    
    /**
     * Binary Tree Right Side View
     */
    public List<Integer> rightSideView(TreeNode root) {
    	List<Integer> res = new ArrayList<>();
    	
    	Queue<TreeNode> queue = new LinkedList<>();
    	queue.offer(root);
    	while(!queue.isEmpty()) {
    		int n = queue.size();
    		for(int i = 0; i < n; ++i) {
    			TreeNode elem = queue.poll();
    			if(elem.left != null) {
    				queue.offer(elem.left);
    			}
    			if(elem.right != null) {
    				queue.offer(elem.right);
    			}
    			if(i == n-1) {
    				res.add(elem.val);
    			}
    		}
    	}
        
        return res;
    }

    /**
     * Remove Linked List Elements
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode newHead = null;
        while(curr != null) {
        	if(curr.val == val) {
        		if(prev == null) {
        			curr = curr.next;
        		}
        		else {
        			prev.next = curr.next;
        			curr = curr.next;
        		}
        	}
        	else {
        		prev = curr;
        		curr = curr.next;
        		if(newHead == null) {
        			newHead = prev;
        		}
        	}
        }
        
        return newHead;
    }	
    
    /*
     * Next Greater Node In Linked List
     */
    public int[] nextLargerNodes(ListNode head) {
    	List<Integer[]> res = new ArrayList<>();
    	Stack<Integer[]> stack = new Stack<>();
    	stack.add(new Integer[] {0, head.val});
    	head = head.next;
    	int counter = 1;
    	
    	while(head != null) {
    		while(!stack.isEmpty() && head.val > stack.peek()[1]) {
    			Integer[] elem = stack.pop();
    			res.add(new Integer[] {elem[0], head.val});
    		}
    		stack.add(new Integer[] {counter++, head.val});
    		head = head.next;
    	}
    	
    	while(!stack.isEmpty()) {
    		Integer[] elem = stack.pop();
    		res.add(new Integer[] {elem[0], 0});
    	}
    	
    	int arr[] = new int[res.size()];
    	for(Integer[] elem : res) {
    		arr[elem[0]] = elem[1];
    	}
    	
    	return arr;
    }
    
    /**
     *  Course Schedule II
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
    	Map<Integer, List<Integer>> deps = new HashMap<>();
        Map<Integer, Integer> freqMap = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        
        // Initialize all the courses freq and deps
        for(int i = 0; i < numCourses; ++i) {
        	freqMap.put(i, 0);
        	deps.put(i, new ArrayList<>());
        }
        
        for(int i = 0; i < prerequisites.length; ++i) {
        	int[] elem = prerequisites[i];
        	freqMap.put(elem[0], freqMap.get(elem[0]) + 1);
        	deps.computeIfAbsent(elem[1], x -> new ArrayList<>()).add(elem[0]);
        }
        
        PriorityQueue<Integer> tasks = new PriorityQueue<>();
        for(Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
        	if(entry.getValue() == 0) {
        		tasks.offer(entry.getKey());
        	}
        }
        
        while(!tasks.isEmpty()) {
        	int task = tasks.poll();
        	res.add(task);
        	List<Integer> dependentTasks = deps.get(task);
        	for(int dep : dependentTasks) {
        		freqMap.put(dep, freqMap.get(dep)-1);
        		if(freqMap.get(dep) == 0) {
        			// Add to heap and remove
        			tasks.add(dep);
        		}
        	}
        }
        
        if(res.size() != numCourses) {
        	return new int[] {};
        }
        
        int[] arr = new int[numCourses];
        for(int i = 0; i < res.size(); ++i) {
        	arr[i] = res.get(i);
        }
        
        return arr;
    }
    
    /**
     * Combination Sum III
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> numbers = Arrays.asList(new Integer[] {1,2,3,4,5,6,7,8,9});
        combinationSum3(k, n, res, new ArrayList<Integer>(), numbers, 0);
        
        return res;
    }

	private void combinationSum3(int k, int n, List<List<Integer>> res, ArrayList<Integer> currPath, List<Integer> numbers, int currIdx) {
		if(n == 0) {
			if(currPath.size() == k) {
				res.add(new ArrayList<>(currPath));
			}
			return;
		}
		
		if(currPath.size() > k || n < 0 || currIdx > 8) {
			return;
		}
		
		for(int i = currIdx; i < numbers.size(); ++i) {
			currPath.add(numbers.get(i));
			combinationSum3(k, n-numbers.get(i), res, currPath, numbers, i+1);
			currPath.remove(currPath.size()-1);
		}
	}
	
	/**
	 * Count Primes
	 */
	public int countPrimes(int n) {
		int count = 0;
		
		for(int i = 2; i <= n; ++i) {
			if(isPrime(i)) {
				++count;
			}
		}
		
		return count;
	}

	private boolean isPrime(int n) {
		if(n == 2 || n == 3) {
			return true;
		}

		for(int i = 2; i <= (int)Math.sqrt(n)+1; ++i) {
			if(n%i == 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Sieve of Eratosthenes
	 */
	public int countPrimesOpt(int n) {
		boolean[] primes = new boolean[n+1];
		for(int i = 2; i < primes.length; ++i) {
			primes[i] = true;
		}
		
		for(int i = 2; i * i <= n; ++i) {
			if(primes[i]) {
				// set all the multiples as non prime
				for(int j = i * i; j <= n; j += i) {
					primes[j] = false;
				}
			}
		}
		
		// count primes
		int count = 0;
		for(int i = 2; i < primes.length; ++i) {
			if(primes[i]) {
				++count;
			}
		}
		
		return count;
	}
	
	/**
	 * House Robber II
	 */
	public int rob(int[] nums) {
		if(nums.length == 0) {
			return 0;
		}
		if(nums.length == 1) {
			return nums[0];
		}
		
        int m1 = rob(nums, 0, 0, 0);
        int m2 = rob(nums, 1, 1, 0);
        
        return Math.max(m1, m2);
    }

	private int rob(int[] nums, int startIdx, int currIdx, int rob) {
		// reached the end
		if(rob != 0 && (currIdx == startIdx) || ((currIdx + 1)%nums.length - startIdx) == 0) {
			return rob;
		}
		
		// Visit the current idx
		rob += nums[currIdx];

		int max = rob;
		for(int i = currIdx + 2; i < nums.length; ++i) {
			max = Math.max(max, rob(nums, startIdx, i, rob));
		}
		
		return max;
	}
	
	/**
	 * Maximal Square
	 */
	public int maximalSquare(char[][] matrix) {
		int[][] dp = new int[matrix.length+1][matrix[0].length+1];
		int max = Integer.MIN_VALUE;
		
		for(int r = 1; r <= matrix.length; ++r) {
			for(int c = 1; c <= matrix[0].length; ++c) {
				if(matrix[r-1][c-1] == '1') {
					// take min of top, left and diagonal-left
					int val = Math.min(dp[r-1][c], Math.min(dp[r][c-1], dp[r-1][c-1]));
					dp[r][c] = val+1;
					max = Math.max(max, dp[r][c]);
				}
			}
		}
		
		return max * max;
    }
	
	/**
	 * Invert binary tree
	 */
	public TreeNode invertTree(TreeNode root) {
		invertBTree(root);
		return root;
    }
	
	public void invertBTree(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) {
        	return;
        }
        
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertBTree(root.left);
        invertBTree(root.right);
	}
	
	/**
	 * Binary Tree Paths
	 */
	public List<String> binaryTreePaths(TreeNode root) {
        if(root == null) {
        	return new ArrayList<>();
        }
        
        List<String> res = new ArrayList<>();
        binaryTreePathsAll(root, res, new StringBuilder());
        
        return res;
    }

	public void binaryTreePathsAll(TreeNode root, List<String> res, StringBuilder curr) {
		if (root.left == null && root.right == null) {
			curr.append(root.val);
			res.add(curr.toString());
			curr.setLength(curr.length()-len(root.val));
			return;
		}

		curr.append(root.val).append("->");
		if(root.left != null) {
			binaryTreePathsAll(root.left, res, curr);
		}
		if(root.right != null) {
			binaryTreePathsAll(root.right, res, curr);
		}
		curr.setLength(curr.length()-2-len(root.val));
	}
	
	public int len(int val) {
		return Integer.toString(val).length();
	}
	
	/**
	 * Kth Smallest Element in a BST
	 */
	public int kthSmallest(TreeNode root, int k) {
		List<Integer> res = new ArrayList<>();
		kthSmallest(root, k, new ArrayList<Integer>());
		return res.get(k);
    }

	private void kthSmallest(TreeNode root, int k, List<Integer> res) {
		if(res.size() >= k) {
			return;
		}
		
		if(root == null) {
			return;
		}
		
		kthSmallest(root.left, k, res);
		res.add(root.val);
		kthSmallest(root.right, k, res);
	}
	
	/**
	 * Wildcard matching
	 */
	public boolean isMatch(String s, String p) {
		boolean dp[][] = new boolean[s.length()+1][p.length()+1];
		
		dp[0][0] = true;
		for(int i = 1; i <= p.length(); ++i) {
			dp[0][i] = dp[0][i-1] && (p.charAt(i-1) == '*');
		}

		for(int i = 1; i <= s.length(); ++i) {
			char sChar = s.charAt(i-1);
			for(int j = 1; j <= p.length(); ++j) {
				char pChar = p.charAt(j-1);
				if(sChar == pChar || pChar == '?') {
					dp[i][j] = dp[i-1][j-1];
				}
				else if(pChar == '*') {
					dp[i][j] = dp[i-1][j] || dp[i][j-1];
				}
				else {
					dp[i][j] = false;
				}
			}
		}
		
		return dp[s.length()][p.length()];
	}
	
	/**
	 * Regular Expression Matching
	 */
	public boolean isMatchRegex(String s, String p) {
		
		boolean[][] dp = new boolean[s.length()+1][p.length()+1];
		dp[0][0] = true;
		
		for(int i = 1; i <= p.length(); ++i) {
			char ch = p.charAt(i-1);
			if(ch == '*') {
				dp[0][i] = dp[0][i-1] || dp[0][i-2];
			}
		}
		
		// always take chars from i-1 and j-1
		for(int i = 1; i <= s.length(); ++i) {
			char sChar = s.charAt(i-1);
			for(int j = 1; j <= p.length(); ++j) {
				char pChar = p.charAt(j-1);
				
				if(sChar == pChar || pChar == '.') {
					dp[i][j] = dp[i-1][j-1];
				}
				else if(pChar == '*') {
					// if a* or .* have 0 occurrence, we only need to match j-2 chars in the pattern with i chars
					if(j-2 >= 0 && dp[i][j-2]) {
						dp[i][j] = dp[i][j-2]; // .* or a* is discarded
					}
					// If last char was in the pattern was . or char in the string; .* or a*
					if(!dp[i][j] && j-2 >= 0) {
						char prevCharInPattern = p.charAt(j-2);
						if(prevCharInPattern == '.' || prevCharInPattern == sChar) {
							dp[i][j] = dp[i-1][j];
						}
					}
				}
			}
		}
		
		return dp[s.length()][p.length()];
	}
	
	/**
	 * Get Target Number Using Number List and Arithmetic Operations
	 */
	public static boolean isReachable(ArrayList<Integer> list, int target) {
		// keep partitioning and combining using all operators
		// compare values with target
		
		List<Integer> results = getResults(list, 0, list.size()-1);
		for(int val : results) {
			if(val == target) {
				return true;
			}
		}
		
		return false;
	}

	private static List<Integer> getResults(ArrayList<Integer> list, int start, int end) {
		List<Integer> results = new ArrayList<>();
		if(start > end) {
			return results;
		}
		if(start == end) {
			results.add(list.get(start));
			return results;
		}
		
		// partition and get results
		for(int i = start; i <= end; ++i) {
			List<Integer> leftRes = getResults(list, start, i);
			List<Integer> rightRes = getResults(list, i+1, end);

			// combine the two halves
			for(int lVal : leftRes) {
				for(int rVal : rightRes) {
					results.add(rVal+lVal);
					results.add(rVal - lVal);
					results.add(rVal * lVal);
					if(rVal != 0) {
						results.add(lVal/rVal);
					}
				}
			}
		}
		
		return results;
	}
	
	/**
	 * Flip game
	 */
	public List<String> generatePossibleNextMoves(String s) {
		int i = 0;
		List<String> res = new ArrayList<>();
		
		while(i < s.length()) {
			int nextMove = i == 0 ? s.indexOf("++") : s.indexOf("++", i+1);
			if(nextMove == -1) {
				return res;
			}
			else {
				res.add(s.substring(0, nextMove) + "--" + s.substring(nextMove + 2));
			}
			
			i = nextMove + 1;
		}
		
		return res;
	}
	
	public boolean canWin(String s) {
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
	 * Scramble String
	 */
	/*public boolean isScramble(String s1, String s2) {
		if((s1 == null && s2 == null) || s1.equals(s2)) {
			return true;
		}
		if(s1 == null || s2 == null || s1.length() != s2.length()) {
			return false;
		}

        return isScramble(s1, s2, 0, s2.length()-1);
    }

	private boolean isScramble(String s1, String s2, int start, int end) {
		if(start >= end) {
			return false;
		}

		for(int i = start+1; i <= end; ++i) {
			String left = s2.substring(start, i);
			String right  = s2.substring(i, end+1); // end inclusive
			
			// swap and check
			String swapped = right + left;
			if(end < s2.length()-1) { // end is not at end of s2
				 swapped += s2.substring(end+1);
			}
			if(swapped.equals(s1)) {
				return true;
			}

//			if (isScramble(s1, s2, start, i) || 
//					isScramble(s1, s2, i + 1, end) || 
//					isScramble(s1, swapped, start, i) || 
//					isScramble(s1, swapped, i + 1, end)) {
//				return true;
//			}
			
			if (isScramble(s1, s2, start, i) || 
					isScramble(s1, s2, i + 1, end) || 
					isScramble(s1, swapped, start, i-1) ||
					isScramble(s1, swapped, i+1, end-i)) {
				return true;
			}
		}

		return false;
	}*/
	
	/*
	 * Find the Shortest Superstring
	 */
	/*
	public String shortestSuperstring(String[] words) {
       if(words == null || words.length == 0) {
    	   return "";
       }
       
       if(words.length == 1) {
    	   return words[0];
       }
       
       ArrayList<Integer> pos = new ArrayList<>();
       pos.add(0);
       ArrayList<String> res = new ArrayList<String>();
       shortestSuperstring(words, 0, pos, "", res);
       
       String ret = res.get(0);
       for(int i = 1; i < res.size(); ++i) {
    	   String word = res.get(i);
    	   if(ret.length() > word.length()) {
    		   ret = word;
    	   }
       }
       
       return ret;
    }

	private void shortestSuperstring(
			String[] words,
			int idx,
			ArrayList<Integer> pos,
			String currStr,
			ArrayList<String> res) {

		if(idx == words.length) {
			res.add(currStr);
			return;
		}
		
		String word = words[idx];
		
		if(currStr.contains(word)) {
			shortestSuperstring(words, idx+1, pos, currStr, res);
		}
		else {
			// insert at each pos
			for(int i = 0; i < pos.size(); ++i) {
				int p = pos.get(i);
				String newStr = currStr.substring(0, p) + word + currStr.substring(p);
				// construct the new pos list
				ArrayList<Integer> newPos = new ArrayList<Integer>();
				//copy till i in the new pos
				for(int j = 0; j <= i; ++j) {
					newPos.add(pos.get(j));
				}
				// from i till end, copy i + word.length
				for(int j = i; j < pos.size(); ++j) {
					newPos.add(pos.get(j) + word.length());
				}

				shortestSuperstring(words, idx+1, newPos, newStr, res);
			}
		}
	}
	*/
	
	/**
	 * Path Sum III
	 */
	class ListAndSum {
		public LinkedList<Integer> list = new LinkedList<>();
		public int currSum = 0;
	}

	private int pathWithSumCount;
	public int pathSum3(TreeNode root, int sum) {
        pathWithSumCount = 0;
        Map<Integer, Integer> sumAndCountSum = new HashMap<>();
        sumAndCountSum.put(0, 1);
        
		pathSum3(root, 0, sum, sumAndCountSum);
		
		return pathWithSumCount;
    }

	private void pathSum3(TreeNode root, int currSum, int target, Map<Integer, Integer> sumAndCountSum) {
		if(root == null) {
			return;
		}
		
		// Add the node value to current sum
		currSum += root.val;
		// Check if the [currSum-k] has been yet encountered or not.
		if(sumAndCountSum.containsKey(currSum - target)) {
			pathWithSumCount += sumAndCountSum.get(currSum - target);
		}
		// Add the entry of currSum to the map
		sumAndCountSum.put(currSum, sumAndCountSum.getOrDefault(currSum, 0) + 1);
		
		pathSum3(root.left, currSum, target, sumAndCountSum);
		pathSum3(root.right, currSum, target, sumAndCountSum);
		
		// remove the last entry of currSum from the map
		sumAndCountSum.put(currSum, sumAndCountSum.get(currSum)-1);
	}
	
	/**
	 * String Compression
	 */
	public int compress(char[] chars) {
        int i = 0;
        int insIdx = 0;
        int count = 1;
        
        if(chars == null) {
        	return 0;
        }
        if(chars.length < 2) {
        	return chars.length;
        }
        
        while(i < chars.length) {
        	while(i < chars.length-1 && chars[i] == chars[i+1]) {
        		++count;
        		++i;
        	}

        	chars[insIdx++] = chars[i];
        	if(count != 1) {
        		String c = Integer.toString(count);
        		for(char d : c.toCharArray()) {
        			chars[insIdx++] = d;
        		}
        		
        	}
        	count = 1;
        	++i;
        }
        
        return insIdx;
	}
	
	/**
	 * Partition Labels
	 */
	public List<Integer> partitionLabels(String s) {
        // Find the last occurrence of each character in the string
		Map<Character, Integer> lastIndex = new HashMap<>();
		for(int i = 0; i < s.length(); ++i) {
			char ch = s.charAt(i);
			lastIndex.put(ch, i);
		}
		
		List<Integer> partitionIndices = new LinkedList<>();
		int idx = 0;
		while(idx < s.length()) {
			char ch = s.charAt(idx);
			int lastIdx = lastIndex.get(ch);
			// Traverse b/w idx and last idx and expand the window if needed
			int i = idx + 1;
			for(; i <= lastIdx && lastIdx < s.length(); ++i) {
				char ch1 = s.charAt(i);
				// if ch1 lies in the window then continue, else expand
				int lIdx = lastIndex.get(ch1);
				if(lIdx <= lastIdx) { // no need to expand as ch1 is in the window
					continue;
				}
				else {
					lastIdx = lIdx;
				}
			}

			partitionIndices.add(i);
			idx = i;
		}
		
		List<Integer> res = new LinkedList<>();
		Iterator<Integer> it = partitionIndices.iterator();
		res.add(it.next());
		int prev = res.get(0);
		while(it.hasNext()) {
			int curr = it.next();
			res.add(curr - prev);
			prev = curr;
		}

		return res;
    }
	
	/**
	 * Longest String Chain
	 */
	public int longestStrChain(String[] words) {
        // Create String to index map
		Map<String, Integer> posMap = new HashMap<>();
		for(int i = 0; i < words.length; ++i) {
			posMap.put(words[i], i);
		}
		
		// Graph structure. It will only the list of vertices from each vertex.
		List<List<Integer>> edges = new ArrayList<>();
		for(int i = 0; i < words.length; ++i) {
			edges.add(new LinkedList<Integer>());
		}
		
		for(int i = 0; i < words.length; ++i) {
			String currWord = words[i];
			// Find the next possible words from word and check if it is available in the map
			// Iterate over the chars of the word removing one char at a time
			// and check if its available in the list of words or not (check in the map)
			for(int j = 0; j < currWord.length(); ++j) {
				String possibleNext = currWord.substring(0, j) + currWord.substring(j+1);
				if(posMap.containsKey(possibleNext)) {
					// Create an edge from possibleNext to currWord
					edges.get(posMap.get(possibleNext)).add(i);
				}
			}
		}
		
		// Find the longest path in the graph
		return getLongest(edges);
    }
	
	private int getLongest(List<List<Integer>> edges) {
		int longest = 0;
		// Tracks the max length of each vertex
		Integer[] connectedLength = new Integer[edges.size()];
		for(int v = 0; v < edges.size(); ++v) {
			longest = Math.max(longest, getLongest(edges, connectedLength, v));
		}
		
		return longest;
	}

	private int getLongest(List<List<Integer>> edges, Integer[] connectedLength, int v) {
		if(connectedLength[v] != null) {
			return connectedLength[v];
		}
		// Initializing the first vertex as 1 as the length of chain with 1 vertex is still 1
		connectedLength[v] = 1;
		List<Integer> connections = edges.get(v);
		for(int j = 0; j < connections.size(); ++j) {
			connectedLength[v] = Math.max(connectedLength[v],
					1 + getLongest(edges, connectedLength, connections.get(j)));
		}
		
		return connectedLength[v];
	}
	
	/**
	 * Word Ladder I
	 */
	public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
		Set<String> words = new HashSet<String>(wordList);
		words.remove(beginWord);
		Queue<String> queue = new LinkedList<>();
		queue.offer(beginWord);
		int level = 1; // one word inserted
		
		// BFS
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int i = 0; i < size; ++i) {
				String currWord = queue.poll();
				for(int j = 0; j < currWord.length(); ++j) {
					for(char ch = 'a'; ch <= 'z'; ++ch) {
						String newWord = currWord.substring(0,j) + ch + currWord.substring(j+1);
						if(words.contains(newWord)) {
							if(newWord.equals(endWord)) {
								return level+1;
							}
							queue.add(newWord);
							words.remove(newWord);
						}
					}
				}
			}
			// level for BFS completed
			++level;
		}
		
		return 0;
	}

	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		if(wordList.get(0) != beginWord) {
			wordList.add(0, beginWord);
		}
		// Create map of word to pos
		Map<String, Integer> posMap = new HashMap<>();
		for(int i = 0; i < wordList.size(); ++i) {
			posMap.put(wordList.get(i), i);
		}
		
		if(!posMap.containsKey(endWord)) {
			return 0;
		}
		
		// Forming the graph/adjacency matrix
		List<List<Integer>> edges = new ArrayList<List<Integer>>();
		for(int i = 0; i < posMap.size(); ++i) {
			edges.add(new ArrayList<Integer>());
		}

		// create a graph from all the words after
		String allAlpha = "abcdefghijklmnopqrstuvwxyz";
		boolean[] traversed = new boolean[wordList.size()];
		String currWord = wordList.get(0);
		for(int i = 1; i < wordList.size(); ++i) {
			// try to form different combinations using allAlpha to get the next word
			// If found, make an entry in the graph
			for(int j = 0; j < currWord.length(); ++j) {
				// Replacing the char at j with all chars in allAlpha
				for(int k = 0; k < 26; ++k) {
					String possible = currWord.substring(0, j) + allAlpha.charAt(k) + currWord.substring(j+1);
					if(posMap.containsKey(possible) && !traversed[posMap.get(possible)]) {
						// add to the graph
						edges.get(i).add(posMap.get(possible));
						traversed[i] = true;
					}
				}
			}
			currWord = wordList.get(i);
		}
		
		// Traverse the graph to identify the path from beginWord till endWord
		boolean[] isTraversed = new boolean[wordList.size()];
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> currPath = new ArrayList<Integer>();
		currPath.add(0);
		isTraversed[0] = true;
		findPaths(edges, isTraversed, 0, wordList.size()-1, currPath, res);
		
		int min = Integer.MAX_VALUE;
		for(List<Integer> l : res) {
			if(l.size() < min) {
				min = l.size();
			}
		}
		
		return min == Integer.MAX_VALUE ? 0 : min;
    }
	
	
	/**
	 * Word Ladder II
	 */
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		wordList.add(0, beginWord);
		// Create map of word to pos
		Map<String, Integer> posMap = new HashMap<>();
		for(int i = 0; i < wordList.size(); ++i) {
			posMap.put(wordList.get(i), i);
		}
		
		if(!posMap.containsKey(endWord)) {
			return new ArrayList<List<String>>();
		}
		
		// Forming the graph/adjacency matrix
		List<List<Integer>> edges = new ArrayList<List<Integer>>();
		for(int i = 0; i < posMap.size(); ++i) {
			edges.add(new ArrayList<Integer>());
		}

		// create a graph from all the words after
		String allAlpha = "abcdefghijklmnopqrstuvwxyz";
		boolean[] traversed = new boolean[wordList.size()];
		for(int i = 0; i < wordList.size(); ++i) {
			String currWord = wordList.get(i);
			traversed[i] = true;
			// try to form different combinations using allAlpha to get the next word
			// If found, make an entry in the graph
			for(int j = 0; j < currWord.length(); ++j) {
				// Replacing the char at j with all chars in allAlpha
				for(int k = 0; k < 26; ++k) {
					String possible = currWord.substring(0, j) + allAlpha.charAt(k) + currWord.substring(j+1);
					if(posMap.containsKey(possible) && !traversed[posMap.get(possible)]) {
						// add to the graph
						edges.get(i).add(posMap.get(possible));
					}
				}
			}
		}
		
		// Traverse the graph to identify the path from beginWord till endWord
		boolean[] isTraversed = new boolean[wordList.size()];
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> currPath = new ArrayList<Integer>();
		currPath.add(0);
		isTraversed[0] = true;
		findPaths(edges, isTraversed, 0, wordList.size()-1, currPath, res);
//		System.out.println(res);
		return null;
    }

	private void findPaths(
			List<List<Integer>> edges,
			boolean[] isTraversed,
			int currIdx,
			int endIdx,
			List<Integer> currPath,
			List<List<Integer>> res) {
		
		if(currIdx == endIdx) {
			res.add(new ArrayList<Integer>(currPath));
			return;
		}
		
		List<Integer> nextVertices = edges.get(currIdx);
		for(int i = 0; i < nextVertices.size(); ++i) {
			int next = nextVertices.get(i);
			if(isTraversed[next]) {
				continue;
			}

			isTraversed[next] = true;
			currPath.add(next);

			findPaths(edges, isTraversed, next, endIdx, currPath, res);

			isTraversed[next] = false;
			currPath.remove(currPath.size()-1);
		}
	}
	
	/**
	 * Range Addition II
	 */
	public int maxCountOpt(int m, int n, int[][] ops) {
		if(ops == null || ops.length == 0) {
			return m*n;
		}

		int xMax = ops[0][0];
		int yMax = ops[0][1];
		
		for(int i = 1; i < ops.length; ++i) {
			int[] op = ops[i];
			if(op[0] < xMax) {
				xMax = op[0];
			}
			if(op[1] < yMax) {
				yMax = op[1];
			}
		}
		
		return xMax * yMax;
	}
	
	/**
	 * Contains Duplicate III
	 */
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		// Set of numbers
		// Note: Only keep numbers b/w i and i + k in the set
		TreeSet<Integer> cache = new TreeSet<>();
		
		for(int i = 0; i < nums.length; ++i) {
			int leftBoundary = Integer.MIN_VALUE;
			if(nums[i] < Integer.MIN_VALUE + t) {
				leftBoundary = Integer.MIN_VALUE;
			}
			else {
				leftBoundary = nums[i] - t;
			}

			int rightBoundary = Integer.MAX_VALUE;
			if(nums[i] > Integer.MAX_VALUE - t - 1) {
				rightBoundary = Integer.MAX_VALUE;
			}
			else {
				rightBoundary = nums[i] + t + 1;
			}
			
			SortedSet<Integer> subset = cache.subSet(leftBoundary, rightBoundary);
			if(subset.size() > 0) {
				return true;
			}
			
			cache.add(nums[i]);
			if(i >= k) {
				cache.remove(nums[i-k]);
			}
		}
		
		return false;
	}
	
	/**
	 * Move Zeroes
	 */
	public void moveZeroes1(int[] nums) {
		int insIdx = 0;
		for(int i = 0; i < nums.length; ++i) {
			if(nums[i] == 0) {
				continue;
			}
			else if(i == insIdx) {
				++insIdx;
				continue;
			}
			else {
				nums[insIdx] = nums[i];
				nums[i] = 0;
				++insIdx;
			}
		}
	}
	
	/**
	 * Container With Most Water
	 */
	public int maxArea2(int[] height) {
		int left = 0, right = height.length-1;
		int max = Integer.MIN_VALUE;
		
		while(left < right) {
			max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
			
			if(left < right) {
				++left;
			}
			else if(left > right) {
				--right;
			}
			else {
				++left;
				--right;
			}
		}
		
		return max;
	}
	
	/**
	 * Minimum Size Subarray Sum
	 */
	public int minSubArrayLen(int s, int[] nums) {
        int left = 0, right = 0;
        int currSum = 0;
        int minLength = Integer.MAX_VALUE;
        while(right < nums.length) {
        	currSum += nums[right];
        	if(currSum >= s) {
        		minLength = Math.min(right - left + 1, minLength);
        	}
        	
        	while(currSum > s) {
        		currSum -= nums[left++];
        		if(currSum >= s) {
        			minLength = Math.min(right - left + 1, minLength);
        		}
        	}

        	++right;
        }
        
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
	
	/**
	 * Median of Two Sorted Arrays
	 */
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		
		int s1 = nums1.length, s2 = nums2.length;
		int xStart = 0, yStart = 0;
		int xEnd = nums1.length-1, yEnd = nums2.length-1;
		
		while(xStart <= xEnd) {
			int xMid = (xStart + xEnd) / 2;
			int yMid = (s1 + s2 + 1) / 2 - xMid;
			
			int xLeft = xMid == 0 ? Integer.MIN_VALUE : nums1[xMid-1];
			int yLeft = yMid == 0 ? Integer.MIN_VALUE : nums2[yMid-1];
			int xRight = xMid == s1 ? Integer.MAX_VALUE : nums1[xMid];
			int yRight = yMid == s2 ? Integer.MAX_VALUE : nums2[yMid];
			
			if(xLeft > yRight) { // need smaller value from x, so move to left
				xEnd = xMid-1;
			}
			else if(yLeft > xRight) { // Need more element of X; Mid has to be moved to right
				xStart = xMid + 1;
			}
			else {
				if((s1 + s2) % 2 == 0) {
					return (Math.max(xLeft, yLeft) + Math.min(xRight, yRight))/2.;
				}
				else {
					return Math.max(xLeft, yLeft);
				}
			}
		}
		
		return 0.;
	}
	
	/**
	 * 
	 */
	public int findMin(int[] nums) {
		int left = 0, right = nums.length-1;
		
		while(left < right) {
			int mid = left + (right - left)/2;
			
			if(mid > 0 && nums[mid-1] > nums[mid]) {
				return nums[mid];
			}
			
			if(nums[mid] > nums[left] && nums[right] < nums[mid]) { // left sorted and right unsorted
				left = mid + 1;
			}
			else {
				right = mid - 1;
			}
		}
		
		return nums[left];
    }
	
	/**
	 * Find Minimum in Rotated Sorted Array II
	 */
	public int findMin2(int[] nums) {
		int left = 0, right = nums.length-1;
		
		while(left < right) {
			while(left < right && nums[left] == nums[right]) {
				++left;
			}
			if(left >= right) {
				return nums[left];
			}
			
			int mid = left + (right-left)/2;
			if(mid > 0 && nums[mid] < nums[mid-1]) {
				return nums[mid];
			}
			
			if(nums[mid] > nums[right]) {
				left = mid+1;
			}
			else {
				right = mid-1;
			}
		}
		
		return nums[left];
	}
	
	/**
	 * Search in Rotated Sorted Array
	 */
	public int search(int[] nums, int target) {
		
		if(nums.length == 1) {
    		return nums[0] == target ? 0 : -1;
    	}
		
		int left = 0, right = nums.length-1;
		while(left <= right) {
			int mid = left + (right-left)/2;
			
			if(nums[mid] == target) {
				return mid;
			}
			
			if(target > nums[mid]) {
				if(nums[left] > nums[mid]) {
					if(target < nums[left]) {
						left = mid + 1;
					}
					else {
						right = mid-1;
					}
				}
				else if(nums[right] < nums[mid]) {
					left = mid + 1;
				}
				else {
					left = mid + 1;
				}
			}
			else {
				if(nums[left] > nums[mid]) {
					right = mid - 1;
				}
				else if(nums[right] < nums[mid]) {
					if(target < nums[left]) {
						left = mid + 1;
					}
					else {
						right = mid - 1;
					}
				}
				else {
					right = mid - 1;
				}
			}
		}
		
		return -1;
	}
	
	/**
	 * Minimum Deletions to Make Character Frequencies Unique
	 */
	public int minDeletions(String s) {
		HashMap<Character, Integer> freq = new HashMap<>();
		
		for(char ch : s.toCharArray()) {
			freq.put(ch, freq.getOrDefault(ch, 0) + 1);
		}
		
		List<Integer> list = new ArrayList<>(freq.values());
//		Collections.sort(list, (a, b) -> Integer.compare(b, a));
		Collections.sort(list);
		
		int res = 0;
		boolean isDuplicate = true;
		while(isDuplicate) {
			isDuplicate = false;
			for(int i = 0; i < list.size()-1; ++i) {
				if(list.get(i) > 0 && list.get(i+1) > 0 && (list.get(i) == list.get(i+1))) {
					++res;
					list.set(i, list.get(i)-1);
					isDuplicate = true;
				}
			}
		}

		return res;
		
		/*
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
		for(int i = 0; i < list.size(); ++i) {
			int f = list.get(i);
			if(minHeap.isEmpty()) {
				minHeap.offer(f);
			}
			else {
				if(minHeap.peek() <= f) {
					if(minHeap.peek() == 0) {
						res += f;
					}
					else {
						res += f - minHeap.peek() + 1;
						minHeap.offer(minHeap.peek()-1);
					}
				}
				else {
					minHeap.offer(f);
				}
			}
		}
		
		return res;*/
	}
	
	/**
	 * Open the Lock
	 */
	public int openLock(String[] deadends, String target) {
		if(target.equals("0000")) {
			return 0;
		}

		int turns = 0;
		HashSet<String> blocks = new HashSet<>();
		for(String d : deadends) {
			blocks.add(d);
		}
		
		if(blocks.contains(target) || blocks.contains("0000")) {
			return -1;
		}
		
		// we don't want to go to previously visited states. So we cache the states
		HashSet<String> traversed = new HashSet<>();
		
		// We will use DFS to reach to the final state
		Queue<String> queue = new LinkedList<>();
		queue.add("0000");
		
		while(!queue.isEmpty()) {
			// entries from the previous state or level
			int n = queue.size();
			++turns; // at every level, we will turn only one of the digits. So, the turns should increase by 1.
			for(int i = 0; i < n; ++i) {
				String lastState = queue.poll();
				for(int k = 0; k < 4; ++k) {
					List<String> possibleStates = getPossibleStates(lastState, k);
					for(String s : possibleStates) {
						if(target.equals(s)) {
							return turns;
						}
						else if(blocks.contains(s) || traversed.contains(s)) {
							continue;
						}
						else {
							queue.add(s);
							traversed.add(s);
						}
					}
				}
			}
		}
		
		return -1; // could not reach the target state
	}
	
	private List<String> getPossibleStates(String lastState, int i) {
		List<String> ret = new ArrayList<>();
		char[] lState = lastState.toCharArray();
		int digit = Character.getNumericValue(lastState.charAt(i));
		// move ahead
		int d1 = digit == 9 ? 0 : digit + 1;
		// move back
		int d2 = digit == 0 ? 9 : digit - 1;
		
		char ch = Character.forDigit(d1, 10);
		lState[i] = ch;
		ret.add(String.valueOf(lState));

		ch = Character.forDigit(d2, 10);
		lState[i] = ch;
		ret.add(String.valueOf(lState));
		
		return ret;
	}

	/*
	private int turns = Integer.MAX_VALUE;
	public int openLock(String[] deadends, String target) {
		Set<String> blocks = new HashSet<>();
		turns = Integer.MAX_VALUE;
		for(String d : deadends) {
			blocks.add(d);
		}
		
		Set<String> traversed = new HashSet<>();
		openLock("0000", target, blocks, 0, traversed);
		return turns;
    }

	private void openLock(String lastState, String target, Set<String> blocks, int currCount, Set<String> traversed) {
		if(lastState.equals(target)) {
			turns = Math.min(currCount, turns);
			return;
		}
		if(blocks.contains(lastState) || traversed.contains(lastState)) {
			return;
		}
		
		traversed.add(lastState);
		// Turn one char at each index
		for(int i = 0; i < 4; ++i) {
			char[] arr = lastState.toCharArray();
			int val = Character.getNumericValue(arr[i]);
			int nextVal1 = val + 1;
			int nextVal2 = val > 0 ? val - 1 : 9;
			if(nextVal1 <= 9) {
				arr[i] = Character.forDigit(nextVal1, 10);
				openLock(String.valueOf(arr), target, blocks, currCount+1, traversed);
			}
			arr[i] = Character.forDigit(nextVal2, 10);
			openLock(String.valueOf(arr), target, blocks, currCount+1, traversed);
		}
	}*/
	
	/**
	 * Minimum Remove to Make Valid Parentheses
	 */
	public String minRemoveToMakeValid(String s) {
		if(s == null || s.isEmpty()) {
			return s;
		}
		
		Deque<Integer> openIndices = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		List<Integer> removeIndices = new LinkedList<>();
		
		for(int i = 0; i < s.length(); ++i) {
			char ch = s.charAt(i);
			
			if(ch == '(') {
				openIndices.add(i);
			}
			else if(ch == ')') {
				if(openIndices.isEmpty()) {
					removeIndices.add(i);
					continue;
				}
				openIndices.pollLast();
			}
			sb.append(ch);
		}
		
		while(!openIndices.isEmpty()) {
			int idx = openIndices.pollFirst();
			if(removeIndices.isEmpty()) {
				removeIndices.add(idx);
			}
			else {
				if(removeIndices.get(0) > idx) {
					removeIndices.add(0, idx);
				}
				else {
					removeIndices.add(idx);
				}
			}
		}
		
		if(removeIndices.isEmpty()) {
			return s;
		}
		
		StringBuilder retVal = new StringBuilder();
		int start = 0;
		for(int end : removeIndices) {
			retVal.append(s.substring(start, end));
			start = end+1;
		}
		if(start < s.length()) {
			retVal.append(s.substring(start));
		}
		
		return retVal.toString();
	}
	
	/**
	 * Simplify Path
	 */
	String simplifyPath(String path) {
		Stack<Character> stack = new Stack<>();
		int dotCount = 0;
		
		for(char ch : path.toCharArray()) {
			if(!stack.isEmpty() && stack.peek() == '/' && ch == '/') {
				continue;
			}
			if(ch == '.') {
				if(dotCount == 0 && !stack.isEmpty()) {
					if(stack.peek() != '/') {
						stack.push(ch);
					}
				}
				else {
					stack.push(ch);
					++dotCount;
				}
				continue;
			}

			if(ch == '/' && dotCount == 1) {
				// remove the last dot
				stack.pop();
				dotCount = 0;
			}
			else if(ch == '/' && dotCount == 2) {
				// remove till 2nd the last /
				int slashCount = 0;
				while(!stack.isEmpty()) {
					char c = stack.pop();
					if(c == '/') {
						++slashCount;
					}
					if(slashCount == 2) {
						stack.push('/');
						break;
					}
				}
				dotCount = 0;
                if(stack.isEmpty()) {
					stack.push('/');
				}
			}
			else {
				stack.push(ch);
				dotCount = 0;
			}
		}
		
        if(!stack.isEmpty() && stack.peek() == '.') {
            if(dotCount == 2) {
                // remove till the 2nd last /
                int slashCount = 0;
                while(!stack.isEmpty()) {
                    char c = stack.pop();
                    if(c == '/') {
                        ++slashCount;
                    }
                    if(slashCount == 2) {
                        stack.push('/');
                        break;
                    }
                }
                dotCount = 0;
                if(stack.isEmpty()) {
                    stack.push('/');
                }

            }
            if(dotCount == 1) {
                stack.pop();
            }
        }
        
		if(!stack.isEmpty() && stack.peek() == '/') {
			stack.pop();
		}
		if(stack.isEmpty()) {
			stack.push('/');
		}

		StringBuilder ret = new StringBuilder();
		for(char ch : stack) {
			ret.append(ch);
		}
        
		return ret.toString();
	}
	
	public int[] temp(int[] arr) {
		Stack<Integer> stack = new Stack<Integer>();
		Map<Integer, Integer> dict = new HashMap<>();

		for(int i = 0; i < arr.length; ++i) { 
			while(!stack.isEmpty() && stack.peek() < arr[i]) { 
				dict.put(stack.pop(), arr[i]);
			}
			stack.push(arr[i]);
		}

		while(!stack.isEmpty()) {
			dict.put(stack.pop(), -1);
		}
		
		int[] res = new int[arr.length];
		for(int i = 0; i < res.length; ++i) {
			res[i] = dict.get(arr[i]);
		}
		
		return res;
	}
	
	public int maxVal11(int[] pvs, int[] fvs, int savings) {
		int n = pvs.length;
		int dp[] = new int[savings+1];
		
		for(int i = 0; i < n; ++i) {
			for(int j = savings; j >= pvs[i]; --j) {
				dp[j] = Math.max(dp[j] , fvs[i] - pvs[i] + dp[j - pvs[i]]);
			}
		}
		
		return dp[savings];
	}
}







