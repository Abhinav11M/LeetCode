package leetcode;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import datastructures.ListNode;
import datastructures.ListNodeWithRandom;

public class LeetAlgos {
	
	/**
	 * Algo 1:
	 * Given an array of integers, return indices of the two numbers such 
	 * that they add up to a specific target.
	 * You may assume that each input would have exactly one solution, 
	 * and you may not use the same element twice.
	 */
	public static int[] twoSum(int[] nums, int target) {
		for(int i = 0; i < nums.length - 1; ++i) {
			for(int j = i+1; j < nums.length;  ++j) {
				if(nums[i] + nums[j] == target) {
					return new int[] {i,j};
				}
			}
		}
		return null;
	}
	
	/**
	 * Given an array nums of n integers, are there elements a, b, c in nums such
	 * that a + b + c = 0? Find all unique triplets in the array which gives the sum
	 * of zero.
	 */
	public static List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> retVals = new ArrayList<>();
		for(int i = 0; i < nums.length-1; ++i ) {
			// Number to array index hash
			Map<Integer, Integer> hash = new HashMap<>();
			for(int j = i+1; j < nums.length; ++j) {
				if(hash.containsKey(-(nums[i] + nums[j]))) {
					// Found the triplet
					retVals.add(Arrays.asList(new Integer[] {nums[i], nums[j], -(nums[i] + nums[j])}));
				}
				else {
					hash.put(nums[j], j);
				}
			}
		}

		return retVals;
	}

	public static List<List<Integer>> threeSumMethod2(int[] nums) {
		// Sort all the elements
		List<List<Integer>> retVal = new ArrayList<>();
		Arrays.sort(nums);
		
		for(int i = 0; i < nums.length-2; ++i) {
			int l = i+1;
			int h = nums.length-1;
			
			while(l < h) {
				if(nums[i] + nums[l] + nums[h] == 0) {
					retVal.add(Arrays.asList(new Integer[] {nums[i], nums[l], nums[h]}));
					l++;
					h--;
				}
				else if(nums[i] + nums[l] + nums[h] < 0) { // add a bigger number 
					l++;
				}
				else { // add a smaller number
					h--;
				}
			}
		}
		
		return retVal;
	}
	
	/*
	 * Given an array nums of n integers and an integer target, find three integers in nums 
	 * such that the sum is closest to target. Return the sum of the three integers. 
	 * You may assume that each input would have exactly one solution
	 */
	public static int closestSum(int[] nums, int target) {
		Arrays.sort(nums);
		int targetDiff = Integer.MAX_VALUE;
		int targetSum = 0;

		for(int i = 0; i < nums.length-2; ++i) {
			int l = i+1;
			int h = nums.length-1;
			
			while(l < h) {
				int s = nums[i] + nums[l] + nums[h];
				int diff = s - target;
				if(Math.abs(diff) < Math.abs(targetDiff)) {
					targetDiff = diff;
					targetSum = s;
				}
				
				if(diff > 0) { // Sum needs to be smaller
					h--;
				}
				else if(diff < 0) {
					l++;
				}
				else {
					return targetSum;
				}
			}
		}
		
		return targetSum;
	}
	
	/**
	 * Given a string containing digits from 2-9 inclusive, return all possible letter 
	 * combinations that the number could represent.
	 * 2-9 are telephone pad number, so 2 => abc, 3 => def and so on.
	 * Ex : Input: "23"
			Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
	 */
	public static String[] letterCombinationsFromDigits(String digits) {
		final Map<Character, String> digitToCharacters = new HashMap<Character, String>(){
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
		
		char[] splitDigits = digits.toCharArray();
		
		List<String> listToCombine = new ArrayList<>();
		for(char c : splitDigits) {
			listToCombine.add(digitToCharacters.get(c));
		}
		
		// Get the first string as a array of characters.
		String[] res = comineStringCharByChar(new String[] {}, listToCombine.get(0));
		
		for(int i = 1; i < listToCombine.size(); ++i) {
			res = comineStringCharByChar(res, listToCombine.get(i));
		}
		
		return res;
	}

	private static String[] comineStringCharByChar(String string1[], String string2) {
		if(string1.length == 0) {
			// Split the string2 into a char array and return
			List<String> arr = new ArrayList<>();
			
			for(char c : string2.toCharArray()) {
				arr.add(Character.toString(c));
			}
			
			return arr.toArray(new String[0]);
		}
		
		List<String> ret = new ArrayList<>();

		for(int i = 0; i < string1.length; ++i) {
			String s1 = string1[i];
			for(int j = 0; j < string2.length(); ++j) {
				char c2 = string2.charAt(j);
				String s = s1 + Character.toString(c2);
				ret.add(s);
			}
		}
		return ret.toArray(new String[0]);
	}
	
	// Method 2 for the above question
	public static List<String> getAllKeypadCombinations(String digits) {
		final Map<Character, String> keypadMap = new HashMap<Character, String>(){
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
		
		char[] digitsArr = digits.toCharArray();
		
		Queue<List<Character>> queueToMerge = new LinkedList<>();
		
		for(char ch : digitsArr) {
			queueToMerge.add(keypadMap.get(ch).chars().mapToObj(x -> (char)x).collect(Collectors.toList()));
		}
		
		return mergeCharacters(queueToMerge);
	}
	
	private static List<String> mergeCharacters(Queue<List<Character>> queueToMerge) {
		List<String> retVal = new ArrayList<>();
		retVal = mergeCharacters(retVal, queueToMerge);
		
		return retVal;
	}

	private static List<String> mergeCharacters(List<String> retVal, Queue<List<Character>> queueToMerge) {
		if(queueToMerge.isEmpty()) { 
			return retVal;
		}
		
		List<Character> temp = queueToMerge.poll();
		
		if(retVal.isEmpty()) { // First entry in the retVal
			for(char ch : temp) {
				retVal.add(Character.toString(ch));
			}
		}
		else {
			// Append each to the string in retVal
			List<String> newRetVal = new ArrayList<>();
			for(String elem : retVal) {
				for(char ch : temp) {
					newRetVal.add(elem + Character.toString(ch));
				}
			}
			retVal = newRetVal;
		}
		
		return mergeCharacters(retVal, queueToMerge);
	}
	
	public static List<String> getAllKeypadCombinationsOpt(String digits) {
		final Map<Character, String> keypadMap = new HashMap<Character, String>(){
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
		
		List<String> retVal = new ArrayList<String>();
		getCombinations(retVal, digits, 0, keypadMap, new StringBuilder());
		return retVal;
	}

	private static void getCombinations(List<String> retVal, String digits, int index, Map<Character, String> keypadMap,
			StringBuilder str) {
		if(index > digits.length() - 1) {
			retVal.add(str.toString());
			return;
		}
		
		String letters = keypadMap.get(digits.charAt(index));
		for(int i = 0; i < letters.length(); ++i) {
			str.append(letters.charAt(i));
			getCombinations(retVal, digits, index+1, keypadMap, str);
			str.setLength(str.length() - 1); // Popping up the last character
		}
	}
	
	/**
	 * Given an array nums of n integers and an integer target, are there elements a, b, c, 
	 * and d in nums such that a + b + c + d = target? Find all unique quadruplets 
	 * in the array which gives the sum of target.
	 * Ex : Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
	 * A solution set is:[[-1,  0, 0, 1], [-2, -1, 1, 2], [-2,  0, 0, 2]]
	 */
	public static List<List<Integer>> getQuadWithTargetSum1(int[] nums, int target) {
		List<List<Integer>> results = new ArrayList<>();
		Arrays.sort(nums);
		
		for(int i = 0; i < nums.length - 3; ++i) {
			for(int j = i+1; j < nums.length - 2; ++j) {
				int l = j + 1;
				int h = nums.length - 1;

				while(l < h) {
					if(nums[i] + nums[j] + nums[l] + nums[h] > target) { // Need a smaller number at the end
						h--;
					}
					else if(nums[i] + nums[j] + nums[l] + nums[h] < target) {
						l++;
					}
					else {
						System.out.println(Arrays.asList(new Integer[] {nums[i], nums[j], nums[l], nums[h]}));
						if(results.isEmpty()) {
							results.add(Arrays.asList(new Integer[] {nums[i], nums[j], nums[l], nums[h]}));
						} 
						else {
							if (nums[l] != results.get(results.size() - 1).get(2)
									|| nums[h] != results.get(results.size() - 1).get(3)) {
								results.add(Arrays.asList(new Integer[] { nums[i], nums[j], nums[l], nums[h] }));
							}
						}
						l++;
						h--;
					}
				}
			}
		}
		
		return results;
	}
	
	/**
	 * Given a linked list, remove the n-th node from the end of list and return its head.
	 * Given linked list: 1->2->3->4->5, and n = 2.
	 * After removing the second node from the end, the linked list becomes 1->2->3->5.
	 */
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode tempHead1 = head;
		
		if(head.next == null && n == 1) {
			return null;
		}
		
		for(int i = 0; i < n; ++i) {
			tempHead1 = tempHead1.next;
		}
		// Exhausted the list
		if(tempHead1 == null) {
			head = head.next;
			return head;
		}
		
		ListNode tempHead2 = head;
		while(tempHead1.next != null ) {
			tempHead2 = tempHead2.next;
			tempHead1 = tempHead1.next;
		}
		
		tempHead2.next = tempHead2.next.next;
		return head;
	}
	
	/**
	 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
	 * An input string is valid if:
	 * Open brackets must be closed by the same type of brackets
	 * Open brackets must be closed in the correct order.
	 * Note that an empty string is also considered valid.
	 */
	public static boolean areBracketsBalanced(String s) {
		char[] charArr = s.toCharArray();
		Stack<Character> stack = new Stack<>();
		
		Map<Character, Character> bracketsMap = new HashMap<Character, Character>() {
			{
				put('(',')');
				put('{','}');
				put('[',']');
			}
		};
		
		for(int i = 0; i < charArr.length; ++i) {
			if (charArr[i] == '(' || charArr[i] == '{' || charArr[i] == '[') {
				stack.add(charArr[i]);
			}
			else {
				if(stack.isEmpty()) {
					return false;
				}
				
				char top = stack.pop();
				if(bracketsMap.get(top) == charArr[i]) {
					continue;
				}
				else {
					return false;
				}
			}
		}
		
		if(stack.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Merge two sorted linked lists and return it as a new list. 
	 * The new list should be made by splicing together the nodes of the first two lists.
	 * Input: 1->2->4, 1->3->4
	 * Output: 1->1->2->3->4->4
	 */
	public static ListNode mergeLists(ListNode list1, ListNode list2) {
		ListNode head = null;
		head = mergeListsRecursively(list1, list2);
		return head;
	}

	private static ListNode mergeListsRecursively(ListNode list1, ListNode list2) {
		if(list1 == null && list2 == null) {
			return null;
		}
		
		if(list1 == null) {
			return list2;
		}
		else if(list2 == null) {
			return list1;
		}
		else {
			if(list1.val > list2.val) {
					ListNode head = new ListNode(list2.val);
					head.next = mergeListsRecursively(list1, list2.next);
					return head;
			}
			else {
				ListNode head = new ListNode(list1.val);
				head.next = mergeListsRecursively(list1.next, list2);
				return head;
			}
		}
	}

	/**
	 * Given n pairs of parentheses, write a function to generate all 
	 * combinations of well-formed parentheses.
	 * For example, given n = 3, a solution set is:
	 * [ "((()))", "(()())", "(())()", "()(())", "()()()" ]
	 */
	public static void generateParanthesis(List<String> result, String s, int left, int right){
	    if(left > right) // If there are more left brackets remaining than right, return
	        return;
	 
	    if(left == 0 && right == 0){
	        result.add(s);
	        return;
	    } 
	 
	    if(left > 0){
	        generateParanthesis(result, s+"(", left-1, right);
	    }
	 
	    if(right > 0){
	        generateParanthesis(result, s+")", left, right-1);
	    }
	}
	
	/**
	 * Merge k sorted linked lists and return it as one sorted list. 
	 * Analyze and describe its complexity.
	 * ==== Not a optimal solution ====
	 */
	public static ListNode mergeKLists(ListNode[] listNodes) {
		ListNode head = null;
		for(int i = 0; i < listNodes.length; ++i) {
			head = mergeListsRecursively(head, listNodes[i]);
		}
		
		return head;
	}
	
	/**
	 * This has even worse complexity
	 */
	public static ListNode mergeKLists1(ListNode[] listNodes) {
	
		ListNode mergeHead = null;
		ListNode returnHead = null;
		
		ListNode[] heads = new ListNode[listNodes.length];
		for(int i = 0; i < listNodes.length; ++i) {
			heads[i] = listNodes[i];
		}
		
		while(!allHeadsNull(heads)) {
		// Find the min of heads and increment the heads
			ListNode tempNode = null;
			int min = Integer.MAX_VALUE;
			int index = 0;

			for(int i = 0; i < heads.length; ++i) {
				if(heads[i] == null) {
					continue;
				}
				
				if(heads[i].val < min) {
					min = heads[i].val;
					tempNode = heads[i];
					index = i;
				}
			}

			if(mergeHead == null) {
				mergeHead = new ListNode(min);
				returnHead = mergeHead;
			}
			else {
				mergeHead.next = new ListNode(min);
				mergeHead = mergeHead.next;
			}
			try {
			heads[index] = heads[index].next;
			}catch(Exception e) {
				System.out.println("Caught");
			}
		}
		
		return returnHead;
	}

	private static boolean allHeadsNull(ListNode[] heads) {
		for(ListNode l : heads) {
			if(l != null) {
				return false;
			}
		}
		return true;
	}
	
	// Using divide and conquer
	public static ListNode mergeKLists2(ListNode[] listNodes) {
		if(listNodes == null || listNodes.length == 0) {
			return null;
		}
		
		return mergeKLists(listNodes, listNodes.length-1);
	}

	private static ListNode mergeKLists(ListNode[] listNodes, int last) {
		int tempLast = last;
		
		while(last != 0) {
			int begin  = 0;
			while(begin < tempLast) {
				listNodes[begin] = mergeLists(listNodes[begin], listNodes[tempLast]);
				begin++;
				tempLast--;
			}
			
			if(begin >= tempLast) { // Update the last once all lists are merged
				last = tempLast;
			}
		}
		
		return listNodes[0];
//		
//		
//		if(begin > last) {
//			return null;
//		}
//		if(begin == last) {
//			return listNodes[begin];
//		}
//		
//		int mid = (last-begin)/2;
//		ListNode head1 = mergeKLists(begin, mid, listNodes);
//		ListNode head2 = mergeKLists(mid + 1, last, listNodes);
//		
//		return mergeLists(head1, head2);
	}
	
	/**
	 * Given a linked list, swap every two adjacent nodes and return its head. 
	 * You may not modify the values in the list's nodes, only nodes itself may be changed.
	 * Given 1->2->3->4, you should return the list as 2->1->4->3.
	 */
	public static ListNode swapPairs(ListNode head) {
		if(head == null || head.next == null) {
			return head;
		}
		ListNode tempHead = head.next;
		ListNode prev = null;
		
		while(head != null && head.next != null) {
			ListNode front = head;
			head = head.next;
			front.next = head.next;
			head.next = front;
			if(prev != null) {
				prev.next = head;
			}
			prev = front;
			head = prev.next;
		}
		
		return tempHead;
	}
	
	public static ListNode swapPairsRec(ListNode head) {
		if(head == null || head.next == null) {
			return head;
		}
		
		ListNode headToRet = head.next;
		swapPairsRecursively(head);
		return headToRet;
	}
	
	public static ListNode swapPairsRecursively(ListNode head) {
		if(head == null || head.next == null) {
			return head;
		}
		
		// Swap the first two from head and return new head.
		ListNode newHead = head.next;
		head.next = newHead.next;
		newHead.next = head;
		head.next = swapPairsRecursively(head.next);
		return newHead;
	}
	
	/**
	 * Given a linked list, reverse the nodes of a linked list k at a time and return its 
	 * modified list. k is a positive integer and is less than or equal to the length of the 
	 * linked list. If the number of nodes is not a multiple of k then left-out nodes 
	 * in the end should remain as it is.
	 * Given this linked list: 1->2->3->4->5
	 * For k = 2, you should return: 2->1->4->3->5
	 * For k = 3, you should return: 3->2->1->4->5
	 */
	public static ListNode reverseKGroupRec(ListNode head, int k) {
		if(head == null) {
			return head;
		}

		Stack<ListNode> stack = new Stack<>();
		ListNode tempHead = head;
		
		for(int i = 0; i < k; ++i) {
			if(tempHead == null) {
				return head; // Return without reversing
			}

			stack.add(tempHead);
			tempHead = tempHead.next;
		}
		
		ListNode returnHead = stack.pop();
		ListNode nextHead = returnHead.next;
		ListNode newHead = returnHead;
		
		while(!stack.isEmpty()) {
			newHead.next = stack.pop();
			newHead = newHead.next;
		}
		
		newHead.next = reverseKGroupRec(nextHead, k);
		
		return returnHead;
	}

	// Without using a Stack
	public static ListNode reverseKGroupRec1(ListNode head, int k) {
		ListNode next = null;
		ListNode prev = null;
		ListNode current = head;
		int count = 0;
		
		if(!hasKElements(head, k)) {
			return head;
		}
		
		while(current != null && count < k) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
			++count;
		}
		
		if(current != null) {
			head.next = reverseKGroupRec1(current, k);
		}
		
		return prev;
	}
	
	private static boolean hasKElements(ListNode head, int k) {
		int count = 0;
		
		while(head != null) {
			++count;
			head = head.next;

			if(count == k) {
				return true;
			}
		}
		
		return false;
	}

	private static int countList(ListNode head) {
		int count = 0;
		while(head != null) {
			head = head.next;
			++count;
		}
		return count;
	}
	
	/**
	 * Given a sorted array nums, remove the duplicates in-place such that each element appear 
	 * only once and return the new length. * Do not allocate extra space for another array, 
	 * you must do this by modifying the input array in-place with O(1) extra memory.
	 * Given nums = [1,1,2],
	 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
	 * It doesn't matter what you leave beyond the returned length.
	 */
	public static int removeDuplicates(int[] nums) {
		if(nums.length == 0 || nums.length == 1) {
			return nums.length;
		}
		
		int indexToUpdate = 1;
		int indexToIterate = 1;
		int prevNum = nums[0];
		while(indexToIterate < nums.length) {
			if(nums[indexToIterate] == prevNum) {
				++indexToIterate;
				continue;
			}
			nums[indexToUpdate++] = nums[indexToIterate];
			prevNum = nums[indexToIterate++];
		}
		return indexToUpdate;
	}
	
	/**
	 * Given an array nums and a value val, remove all instances of that value in-place
	 * and return the new length. Do not allocate extra space for another array,
	 * you must do this by modifying the input array in-place with O(1) extra memory. 
	 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
	 * Given nums = [3,2,2,3], val = 3,
	 * Your function should return length = 2, with the first two elements of nums being 2.
	 */
	 public static int removeElement(int[] nums, int val) {
		 int length = nums.length;
		 for(int i = 0; i < length;) {
			 if(nums[i] == val) {
				 for(int j = i; j < nums.length-1; ++j) {
					 nums[j] = nums[j+1];
				 }
				 --length;
			 }
			 else {
				 ++i;
			 }
		 }
		 
		 return length;
	 }
	 
	 /**
	  * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
	  * Input: haystack = "hello", needle = "ll"
	  * Output: 2
	  * Input: haystack = "aaaaa", needle = "bba"
	  * Output: -1
	  * When needle is an empty string, we will return 0.
	  */
	 // This is not efficient
	public static int strStr(String haystack, String needle) {
		if (needle.isEmpty()) {
			return 0;
		}
		
		int hayStackIndex = 0;
		
		while(hayStackIndex < haystack.length()) {
			// Get the first index
			while(haystack.charAt(hayStackIndex) != needle.charAt(0)) {
				if(hayStackIndex >= haystack.length()-1) {
					return -1;
				}
				hayStackIndex++;
			}
			
			// First char index will be hayStackIndex
			int returnIndex = hayStackIndex;
			int tempHayStackIndex = hayStackIndex;
			++tempHayStackIndex;
			int i = 1;
			for(i = 1; i < needle.length(); ++i) {
				if(tempHayStackIndex >= haystack.length() || haystack.charAt(tempHayStackIndex) != needle.charAt(i)) {
					break;
				}
				++tempHayStackIndex;
			}
			
			if(i == needle.length()) {
				return returnIndex;
			}
			++hayStackIndex;
		}
		
		return -1;
	}
	
	// Still not quite optimized
	public static int strStr1(String haystack, String needle) {
		if(needle.isEmpty()) {
			return 0;
		}
		
		List<Integer> startIndices = new ArrayList<>();
		
		for(int i = 0; i < haystack.length()-needle.length(); ++i) {
			if(haystack.charAt(i) == needle.charAt(0)) {
				startIndices.add(i);
			}
		}
		
		if(startIndices.isEmpty()) {
			return -1;
		}
		
		for(int beg : startIndices) {
			if(beg + needle.length() > haystack.length()) {
				continue;
			}
			
			int haystackIndex = beg+1;
			for(int i = 1; i < needle.length(); ++i) {
				if(haystack.charAt(haystackIndex) != needle.charAt(i)) {
					break;
				}
				++haystackIndex;
			}
			if(haystackIndex == beg + needle.length()) {
				 return beg;
			}
		}
		
		return -1;
	}
	
	public static int subStringIndex(String str, String substr) {
		for(int i = 0; i <= str.length()-substr.length(); ++i) {
			int j = 0;
			for(j = 0; j < substr.length(); ++j) {
				if(substr.charAt(j) != str.charAt(i+j)) {
					break;
				}
				if(j == substr.length()-1) {
					return i;
				}
			}
		}

		return -1;
	}
	
	/**
	 * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
	 * Return the quotient after dividing dividend by divisor.
	 * The integer division should truncate toward zero.
	 * Input: dividend = 10, divisor = 3
	 * Output: 3
	 * Input: dividend = 7, divisor = -3
	 * Output: -2
	 */
	public static int divide(int dividend, int divisor) {
		if(dividend == 0 ) {
			return 0;
		}
		if(divisor == 1) {
			return dividend;
		}
		if(divisor == Integer.MIN_VALUE) {
			if(dividend == Integer.MIN_VALUE) {
				return 1;
			}
			return 0;
		}
		if(divisor == -1) {
			if(dividend == Integer.MIN_VALUE) {
				return Integer.MAX_VALUE;
			}
			else {
				return dividend * (-1);
			}
		}
		
		boolean invertSign = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0);
		int quotient = 0;
		
		if(divisor < 0) {
			divisor = -divisor;
		}
		
		if(dividend == Integer.MIN_VALUE) {
			dividend += divisor;
			++quotient;
		}
		if(dividend < 0) {
			dividend = -dividend;
		}
		
		while(dividend >= divisor) {
			dividend -= divisor;
			++quotient;
		}
		
		return invertSign ? -quotient : quotient;
	}
	
	/**
	 * You are given two non-empty linked lists representing two non-negative integers. 
	 * The digits are stored in reverse order and each of their nodes contain a single digit. 
	 * Add the two numbers and return it as a linked list.You may assume the two numbers 
	 * do not contain any leading zero, except the number 0 itself.
	 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	 * Output: 7 -> 0 -> 8
	 * Explanation: 342 + 465 = 807.
	 */
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		return addTwoNumbers(null, l1, l2, 0);
	}
	
	private static ListNode addTwoNumbers(ListNode newHead, ListNode l1, ListNode l2, int carry) {
		if(l1 == null && l2 == null) {
			if(carry == 0) {
				return null;
			}
			else {
				return new ListNode(carry);
			}
		}
		if(l1 == null) {
			if(carry == 0) {
				return l2;
			}
			else {
				return addTwoNumbers(null, l2, new ListNode(carry), 0);
			}
		}
		if(l2 == null) {
			if(carry == 0) {
				return l1;
			}
			else {
				return addTwoNumbers(null, l1, new ListNode(carry), 0);
			}
		}
		
		int val = l1.val + l2.val + carry;
		int newCarry = 0;
		if(val >= 10) {
			val = val - 10;
			newCarry = 1;
		}
		
		
		newHead = new ListNode(val);
		
		newHead.next = addTwoNumbers(newHead.next, l1.next, l2.next, newCarry);

		return newHead;
	}
	
	/**
	 * Given a string, find the length of the longest substring without repeating characters.
	 * Input: "abcabcbb"
	 * Output: 3 
	 * Explanation: The answer is "abc", with the length of 3.
	 * Input: "bbbbb"
	 * Output: 1
	 * Explanation: The answer is "b", with the length of 1.
	 * Input: "pwwkew"
	 * Output: 3
	 * Explanation: The answer is "wke", with the length of 3. 
	 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
	 */
	public static int lengthOfLongestSubstring(String s) {
		if(s.isEmpty()) {
			return 0;
		}
		
		int max = 0;
		char[] arr = s.toCharArray();
		
		for(int i = 0; i < arr.length; ++i)
		{ 
			int counter = 0;
			Set<Character> alphaUsed = new HashSet<>();
			int startIndex = i;
			while(startIndex < arr.length && !alphaUsed.contains(arr[startIndex])) {
				++counter;
				alphaUsed.add(arr[startIndex]);
				++startIndex;
			}
			if(max < counter) {
				max = counter;
			}
		}
		return  max;
	}
	
	// This is way more optimized that the prev solution (O(n) vs O(n^3))
	public static int lengthOfLongestSubstringOpt(String s) {
		if(s.isEmpty()) {
			return 0;
		}
		
		// Create a array of all the characters to indices
		int[] visited = new int[256];
		for(int x = 0; x < 256; ++x) {
			visited[x] = -1;
		}
		
		int maxLength = 1;
		int currLength = 1;
		
		visited[s.charAt(0)] = 0;
		
		for(int i = 1; i < s.length(); ++i) {
			int prevIndexOfChar = visited[s.charAt(i)];
			if(prevIndexOfChar == -1 || i - prevIndexOfChar > currLength) { // Not visited yet or is not in the range of the 
											// current NRCS since it may be starting from pos x
				++currLength;
			}
			else {
				// set i to the previous index of the character found.
				if(currLength > maxLength) {
					maxLength = currLength;
				}
				currLength = i - prevIndexOfChar; // Removed the part from NRCS till the prev occurence of the char
													// Ex: pabapq => when coming to 2nd a, I am removing the distance
													// 				till 1st a, i.e. 4-2 = 2.
				
			}
			visited[s.charAt(i)] = i;
		}
		
		return currLength > maxLength ? currLength : maxLength;
	}
	
	/**
	 * Median of two sorted arrays of same size
	 * Input : arr1[] = {1,12,15,26,38}, arr2[] = {2,13,17,30,25}
	 * Output : 16
	 */
	public static double findMedianOfSortedArraysOfEqualLength(int[] arr1, int[] arr2) {
		if(arr1.length == 1) {
			return (arr1[0] + arr2[0])/2;
		}
		
		int l1 = 0;
		int l2 = 0;
		int h1 = arr1.length-1;
		int h2 = arr2.length-1;
		double median1 = 0;
		double median2 = 0;
		
		while(h1-l1 != 1 && l2-h2 != 1) {
			if((h1-l1)%2 == 0) {
				median1 = (arr1[(h1-l1-1)/2] + arr1[(h1-l1)/2])/2;
				median2 = (arr2[(h2-l2-1)/2] + arr2[(h2-l2)/2])/2;
			}
			else {
				median1 = arr1[(h1-l1-1)/2];
				median2 = arr2[(h2-l2-1)/2];
			}
			
			if(median1 < median2) {
				l1 = (h1-l1+1)/2;
				h2 = h2/2;
			}
			else {
				h1 = h1/2;
				l2 = (h2-l1+1)/2;
			}
		}
		
		return (Math.max(arr1[l1], arr2[l2]) + Math.min(arr1[h1], arr2[h2]))/2.0;
	}
	
	/**
	 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
	 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
	 * You may assume nums1 and nums2 cannot be both empty.
	 * Ex1 : nums1 = [1, 3]
	 * nums2 = [2]
	 * The median is 2.0
	 * Ex2 : nums1 = [1, 2]
	 * nums2 = [3, 4]
	 * The median is (2 + 3)/2 = 2.5
	 */
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		if(nums1.length == 0 && nums2.length == 0) {
			return 0;
		}
		
		if(nums1.length == 0 ) {
			return nums2.length%2 == 0 ? (nums2[(nums2.length/2)-1] + nums2[nums2.length/2])/2.0 : nums2[(nums2.length/2)];
		}
		
		if(nums2.length == 0 ) {
			return nums1.length%2 == 0 ? (nums1[(nums1.length/2)-1] + nums1[nums1.length/2])/2.0 : nums1[(nums1.length/2)];
		}
		
		int[] x = null; // x should be the smaller array.
		int[] y = null; // y should be the bigger array.
		if(nums1.length < nums2.length) {
			x = nums1;
			y = nums2;
		}
		else {
			x = nums2;
			y = nums1;
		}
		
		// Partition x and y
		int xBegin = 0;
		int xEnd = x.length;
		int partX = 0;
		int partY = 0;
		
		while (xBegin <= xEnd) {
			partX = (xBegin + xEnd) / 2;
			// Maintain equal number of elements on left and right side
			partY = (x.length + y.length + 1)/2 - partX;
			
			int maxXLeft = partX == 0 ? Integer.MIN_VALUE : x[partX-1];
			int maxYLeft = partY == 0 ? Integer.MIN_VALUE : y[partY-1];
			int minXRight = partX == x.length ? Integer.MAX_VALUE : x[partX];
			int minYRight = partY == y.length ? Integer.MAX_VALUE : y[partY];
			
			if(maxXLeft > minYRight) { // Need less x elements in the left side. Move the mid to left
				xEnd = partX-1;
			}
			else if(maxYLeft > minXRight) { // Need more x elements in the left side. Move the mid to the right
				xBegin = partX+1;
			}
			else { // x[xLow] < y[yHigh] && y[yLow] < x[xHigh]
				// Partition found
				if((x.length + y.length)%2 == 0) {
					return (Math.max(maxXLeft,maxYLeft) + Math.min(minXRight, minYRight))/2.0;
				}
				else {
					return Math.max(maxXLeft,maxYLeft);
				}
			}
		}
		return 0.0; // Should never come here. Only comes here if the arrays are not sorted.
	}
	
	/**
	 * Clone a linked list with random pointer
	 */
	public static ListNodeWithRandom cloneListWithRandom(ListNodeWithRandom head) {
		ListNodeWithRandom tempHead = head;

		// Insert a dummy node with prev data for each node
		while(tempHead != null) {
			ListNodeWithRandom dummy = new ListNodeWithRandom(tempHead.data);
			dummy.next = tempHead.next;
			tempHead.next = dummy;
			tempHead = tempHead.next.next;
		}
		
		// Set the random pointers.
		tempHead = head;
		while(tempHead != null) {
			tempHead.next.random = tempHead.random.next; // setting the random of a dummy node to a different dummy node.
			tempHead = tempHead.next.next;
		}
		
		// Separate the lists out
		tempHead = head;
		ListNodeWithRandom retHead = head.next;
		ListNodeWithRandom tempRetHead = head.next;
		while(tempHead != null && tempHead.next.next != null) {
			tempHead.next = tempHead.next.next;
			tempRetHead.next = tempRetHead.next.next;
			tempHead = tempHead.next;
			tempRetHead = tempRetHead.next;
		}
		tempHead.next = null;
		tempRetHead.next = null;
		
		return retHead;
	}
	
	/**
	 * Find the longest pelindrome subsequence.
	 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
	 * Input: "babad"
	 * Output: "bab"
	 * Note: "aba" is also a valid answer.
	 * Input: "cbbd"
	 * Output: "bb"
	 */
	public static String longestPalindrome(String s) {
		if(s.isEmpty() || s.length() == 1) {
			return "";
		}
		
		String finalStr = "";
		
		for(int i = 1; i < s.length()-1; ++i) {
			String retSubstr = "";
			if(s.charAt(i-1) == s.charAt(i+1)) { // Odd case
				retSubstr = retSubstr + s.charAt(i);
				int left = i-1;
				int right = i+1;
				while(left >= 0 && right < s.length()) {
					retSubstr = s.charAt(left) + retSubstr + s.charAt(right);
					--left;
					++right;
				}
				if(finalStr.length() < retSubstr.length()) {
					finalStr = retSubstr;
				}
			}
			else if(s.charAt(i) == s.charAt(i-1)) {//Even case
				retSubstr = retSubstr + s.charAt(i-1) + s.charAt(i);
				int left = i-2;
				int right = i+1;
				while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
					retSubstr = s.charAt(left) + retSubstr + s.charAt(right);
					--left;
					++right;
				}
				if(finalStr.length() < retSubstr.length()) {
					finalStr = retSubstr;
				}
			}
//			else if(s.length() >= i+1 && s.charAt(i-1) == s.charAt(i+1)) { // what if i is the last index
//				
//			}
			else {
				i++;
			}
		}
		
		return finalStr;
	}
	
	public static String largestPelindrome1(String s) {
		if(s.isEmpty()) {
			return s;
		}
		
		String largestPelindrome = "" + s.charAt(0);
		
		// For odd pelindromes
		for(int i = 1; i < s.length()-1; ++i) {
			if(s.charAt(i-1) == s.charAt(i+1)) {
				int left = i-2;
				int right = i+2;
				String temp = "" + s.charAt(i-1) + s.charAt(i) + s.charAt(i+1);
				while(left >= 0 && right <= s.length()-1) {
					if(s.charAt(left) == s.charAt(right)) {
						temp = s.charAt(left) + temp + s.charAt(right);
						--left;
						++right;
					}
					else {
						break;
					}
				}
				if(temp.length() > largestPelindrome.length()) {
					largestPelindrome = temp;
				}
			}
		}
		
		// For even pelindromes
		for(int i = 1; i < s.length(); ++i) {
			if(s.charAt(i-1) == s.charAt(i)) {
				int left = i-2;
				int right = i+1;
				String temp = "" + s.charAt(i-1) + s.charAt(i);
				while(left >=0 && right <= s.length()-1) {
					if(s.charAt(left) == s.charAt(right)) {
						temp = s.charAt(left) + temp + s.charAt(right);
						--left;
						++right;
					}
					else {
						break;
					}
				}
				if(temp.length() > largestPelindrome.length()) {
					largestPelindrome = temp;
				}
			}
		}
		
		return largestPelindrome;
	}
	
	/**
	 * @apiNote : Manacher's algorithm finds the longest pelindrome substring in O(n) time.
	 * @param s String to find the longest pelindrome in.
	 * @return longest pelindrome substring.
	 */
	public static String manacherAlgo(String s) {
		String str = "@#"; // Marks the start of the string
		for(int i = 0; i < s.length(); ++i) {
			str = str + s.charAt(i) + "#";
		}
		str = str + "$"; // Marks the end of the string

		int[] p = new int[str.length()];
		
		int center = 0;
		int rightBoundary = 0;
		int mirror = 0;
		
		for(int i = 1; i < str.length(); ++i) {
			
			mirror = 2*center - i;
			
			// Copy the pelindrome value from the mirror if i lies on the left side of the right boundary
			if(i < rightBoundary) {
				p[i] = Math.min(rightBoundary-i, p[mirror]); // TODO: Need to understand this
			}
			
			// Expand the pelindrome value
			while((i + p[i] + 1 < str.length()) && (str.charAt(i + p[i] + 1) == str.charAt(i - (p[i] + 1)))) {
				p[i]++;
			}
			
			// If the pelindrome crosses the right boundary, update the center and right boundary
			if(p[i] + i > rightBoundary) {
				center = i;
				rightBoundary = p[i] + i;
			}
		}
		
		// Find the index and max length
		int idx = -1;
		int maxLen = -1;
		
		for(int i = 0; i < p.length; ++i) {
			if(p[i] > maxLen) {
				maxLen = p[i];
				idx = i;
			}
		}
		
		String ret = "";
		for(int i = (idx - 1 - maxLen)/2; i < (idx - 1 + maxLen)/2; ++i) {
			ret += s.charAt(i);
		}

		return ret;
	}
	
	/**
	 * Given a 32-bit signed integer, reverse digits of an integer.
	 * int (min, max) values => (-2,147,483,648 to +2,147,483,647).
	 * 1534236469 => 9646324153
	 * 1056389758 964632435
	 */
	public static int reverse(int x) {
		int posX = Math.abs(x);
		boolean isNegative = x < 0;
		int reverse = 0;
		
		while(posX > 0) {
			if(reverse*10 / 10 != reverse ) {
				// bit overflow
				return 0;
			}
			reverse = reverse * 10 + posX%10;
			posX = posX/10;
		}
		return isNegative ? reverse*(-1) : reverse;
	}
	
	/**
	 * Determine whether an integer is a palindrome. An integer is a palindrome when it 
	 * reads the same backward as forward.
	 */
	public static boolean isPalindrome(int x) {
		int tempX = x;
		int reverse = 0;
		
		while(x > 0) {
			reverse = reverse * 10 + x%10;
			x = x/10;
		}
		return tempX == reverse;
	}
	
	/**
	 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). 
	 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
	 * Find two lines, which together with x-axis forms a container, 
	 * such that the container contains the most water.
	 */
	// Need to optimize this.
	public static int maxArea(int[] heights) {
		int maxArea = -1;
		for(int i = 0; i < heights.length; ++i) {
			for(int j = i+1; j < heights.length; ++j) {
				int area = (j-i)*(Math.min(heights[i], heights[j]));
				if(area > maxArea) {
					maxArea = area;
				}
			}
		}
		
		return maxArea;
	}
	
	public static int maxAreaOpt(int[] height) {
		int max = 0;
		int left = 0, right = height.length-1;
		while(left < right) {
			int tempMax = Math.min(height[left], height[right])*(right-left);
			max = max > tempMax ? max : tempMax;
			if(height[left] < height[right]) { // left is small, move right
				++left;
			}
			else if(height[left] > height[right]) { // right is small, move left
				--right;
			}
			else { // We can move both left and right ahead as it even if we move only left or right, 
					//the amount of water will
					// always be less or equal to that the tank with same height
				++left;
				--right;
			}
		}
		
		return max;
	}
	
	/*
	 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
	 * {I=>1, V=>5, X=>10, L=>50, C=>100, D=>500, M=>1000}
	 * I can be placed before V (5) and X (10) to make 4 and 9. 
	 * X can be placed before L (50) and C (100) to make 40 and 90. 
	 * C can be placed before D (500) and M (1000) to make 400 and 900.
	 * Input: 3
	 * Output: "III"
	 * Input: 4
	 * Output: "IV"
	 * Input: 9
	 * Output: "IX"
	 * Input: 9
	 * Output: "IX"
	 * Input: 1994
	 * Output: "MCMXCIV"
	 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
	 */
	public static String intToRoman(int num) {
		int[] values = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
		String[] romans = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
		int index = 12;
		
		// optimizing search index
		while(num < values[index]) {
			--index;
		}
		
		String roman = "";
		
		while(num > 0) {
			int divisor = num/values[index]; // Getting the first digit
			num = num%values[index];
			
			while(divisor > 0){
				// Repeat the roman characters
				roman += romans[index];
				--divisor;
			}
			--index;
		}
		
		return roman;
	}
	
	public static int romanToInt(String s) {
		int val = 0;
		Map<String, Integer> map = Stream.of(
				new AbstractMap.SimpleEntry<String, Integer>("I",1),
				new AbstractMap.SimpleEntry<String, Integer>("IV",4),
				new AbstractMap.SimpleEntry<String, Integer>("V",5),
				new AbstractMap.SimpleEntry<String, Integer>("IX",9),
				new AbstractMap.SimpleEntry<String, Integer>("X",10),
				new AbstractMap.SimpleEntry<String, Integer>("XL",40),
				new AbstractMap.SimpleEntry<String, Integer>("L",50),
				new AbstractMap.SimpleEntry<String, Integer>("XC",90),
				new AbstractMap.SimpleEntry<String, Integer>("C",100),
				new AbstractMap.SimpleEntry<String, Integer>("CD",400),
				new AbstractMap.SimpleEntry<String, Integer>("D",500),
				new AbstractMap.SimpleEntry<String, Integer>("CM",900),
				new AbstractMap.SimpleEntry<String, Integer>("M",1000)
				).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		
		for(int i = 0; i < s.length();) {
			if(i+1 < s.length()) { // not the last index
				String temp = s.substring(i,i+2);
				if(map.containsKey(temp)) {
					val += map.get(temp);
					i += 2;
				}
				else {
					val += map.get("" + s.charAt(i));
					++i;
				}
			}
			else { // last index
				val += map.get("" + s.charAt(s.length()-1));
				++i;
			}
		}
		
        return val;
    }
	
	public static int romanToIntOpt(String s) {
		if(s.isEmpty()) {
			return 0;
		}

		Map<Character, Integer> map = Stream.of(
				new AbstractMap.SimpleEntry<Character, Integer>('I',1),
				new AbstractMap.SimpleEntry<Character, Integer>('V',5),
				new AbstractMap.SimpleEntry<Character, Integer>('X',10),
				new AbstractMap.SimpleEntry<Character, Integer>('L',50),
				new AbstractMap.SimpleEntry<Character, Integer>('C',100),
				new AbstractMap.SimpleEntry<Character, Integer>('D',500),
				new AbstractMap.SimpleEntry<Character, Integer>('M',1000)
				).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

		int val = map.get(s.charAt(0));
		int prev = map.get(s.charAt(0));
		
		for(int i = 1; i < s.length(); ++i) {
			int current = map.get(s.charAt(i));
			val = current <= prev ? val + current : val + current - 2*prev;
			prev = current;
		}
		
        return val;
	}
	
	/**
	 *  Write a function to find the longest common prefix string amongst an array of strings.
	 *  Input: ["flower","flow","flight"]
	 *  Output: "fl"
	 *  Input: ["dog","racecar","car"]
	 *  Output: ""
	 */
	public static String longestCommonPrefix(String[] strs) {
		if(strs.length == 0) {
			return "";
		}
		if(strs.length == 1) {
			return strs[0];
		}
		
		String comPre = strs[0];
		
		for(int j = 1; j < strs.length; ++j) {
			int iterLen = Math.min(comPre.length(), strs[j].length());
			while(true) {
				if(iterLen == 0) {
					return "";
				}
				if(comPre.substring(0, iterLen).equals(strs[j].substring(0, iterLen))) {
					comPre = comPre.substring(0, iterLen);
					break;
				}
				--iterLen;
			}
		}
		
		return comPre; 
    }
	
	/**
	 * Implement atoi which converts a string to an integer.
	 * Input: "42"
	 * Output: 42
	 * Input: "   -42"
	 * Output: -42
	 * Input: "4193 with words"
	 * Output: 4193
	 * Input: "-91283472332"
	 * Output: -2147483648 => Since input is out of Int32 range and is negative, return INT_MIN
	 */
	public static int myAtoi(String str) {
		int retVal = 0;
		boolean isNegative = false;
		boolean isInitialized = false;
		
		for(int i = 0; i < str.length(); ++i) {
			if(str.charAt(i) == ' ') {
				if(isInitialized) { // For case when +/- is followed by a space
					return isNegative ? retVal*-1 : retVal;
				}
				else {
					continue;
				}
			}
			if(str.charAt(i) == '-' || str.charAt(i) == '+') {
				if(!isInitialized) {
					if(str.charAt(i) == '-') {
						isNegative = true;
					}
					isInitialized = true;
					continue;
				}
				else {
					return isNegative ? retVal*-1 : retVal;
				}
			}
			if(str.charAt(i) < 48 || str.charAt(i) > 57) {
				return isNegative ? retVal*-1 : retVal;
			}
			
			int val = str.charAt(i) % 48;
			int retValNew = retVal*10 + val;
			if(retValNew/10 !=  retVal) { // Int32 buffer exceeded.
				return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
			}
			else {
				retVal = retValNew;
			}
			
			if(str.charAt(i) != ' ' && !isInitialized) {
				isInitialized = true;
			}
		}
		
        return isNegative ? retVal*-1 : retVal;
    }
	
	/**
	 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
	 * (you may want to display this pattern in a fixed font for better legibility)
	 * Input: s = "PAYPALISHIRING", numRows = 4
	 * Output: "PINALSIGYAHRPI"
	 * Explanation:
	 * 	P     I    N
	 * 	A   L S  I G
	 * 	Y A   H R
	 * 	P     I
	 */
	public static String convert(String s, int numRows) {
		if(numRows == 1) {
			return s;
		}
		
		StringBuilder retStr = new StringBuilder();
		
		for(int i = 0; i < numRows; ++i) {
			int start = i, end = s.length()-1, jump = 0;
			if(i == 0 || i == numRows-1) {
				jump = 2*numRows - 2;
				while(start <= end) {
					retStr.append(s.charAt(start));
					start += jump;
				}
			}
			else {
				boolean bottomToTop = true;
				while(start <= end) {
					retStr.append(s.charAt(start));
					if(bottomToTop) {
						jump = 2*numRows - 2*i - 2;
						start += jump;
					}
					else {
						jump = 2*i;
						start += jump;
					}
					bottomToTop = !bottomToTop;
				}
			}
		}
		
		return retStr.toString();
	}
	
	/**
	 * <p>
	 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
	 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
	 * You are given a target value to search.
	 * If found in the array return its index, otherwise return -1.
	 * You may assume no duplicate exists in the array.
	 * Your algorithm's runtime complexity must be in the order of O(log n).
	 * Input: nums = [4,5,6,7,0,1,2], target = 0
	 * Output: 4
	 * Input: nums = [4,5,6,7,0,1,2], target = 3
	 * Output: -1
	 * </p>
	 */
	public static int search(int[] nums, int target) {
        if(nums.length == 0) {
        	return -1;
        }
        if(nums.length == 1) {
        	if(nums[0] == target) {
        		return 0;
        	}
        	return -1;
        }
        
        int left = 0, right = nums.length-1; 
        
        while(left <= right) {
        	int mid = (left + right)/2;
        	if(nums[mid] == target) {
        		return mid;
        	}
        	else if(nums[mid] >= nums[0]) { // This part is not skewed
        		if(nums[mid] > target && nums[0] <= target ) {
        			right = mid-1;
        		}
        		else {
        			left = mid+1;
        		}
        	}
        	else { // This is not skewed.
        		if(nums[mid] < target && nums[nums.length-1] >= target) {
        			left = mid+1;
        		}
        		else {
        			right = mid-1;
        		}
        	}
        }
        
        return -1;
    }
	
	/*
	 * Given an array of integers nums sorted in ascending order, 
	 * find the starting and ending position of a given target value.
	 * Your algorithm's runtime complexity must be in the order of O(log n).
	 * If the target is not found in the array, return [-1, -1].
	 */
	public static int[] searchRange(int[] nums, int target) {
		int lIdx = findLeftMostIndex(nums, target);
		int rIdx = findRightMostIndex(nums, target);
		
		return new int[] {lIdx, rIdx};
	}
	
	private static int findLeftMostIndex(int[] nums, int target) {
		
		int left = 0, right = nums.length-1;
		int sIdx = -1;
		
		while(left <= right) {
			int mid = (left+right)/2;
			if(target < nums[mid]) {
				right = mid-1;
			}
			else if(target > nums[mid]) {
				left = mid+1;
			}
			else {
				sIdx = mid;
				right = mid-1;
			}
		}
		
		return sIdx;
	}
	
	private static int findRightMostIndex(int[] nums, int target) {
		
		int left = 0, right = nums.length-1;
		int sIdx = -1;
		
		while(left <= right) {
			int mid = (left+right)/2;
			if(target < nums[mid]) {
				right = mid-1;
			}
			else if(target > nums[mid]) {
				left = mid+1;
			}
			else {
				sIdx = mid;
				left = mid+1;
			}
		}
		
		return sIdx;
	}
	
	/**
	 * Given a sorted array and a target value, return the index if the target is found. 
	 * If not, return the index where it would be if it were inserted in order.
	 * You may assume no duplicates in the array.
	 * Input: [1,3,5,6], 5
	 * Output: 2
	 * Input: [1,3,5,6], 2
	 * Output: 1
	 */
	public static int searchInsert(int[] nums, int target) {
		int left = 0, right = nums.length-1;
		
		while(left <= right) {
			int mid = (left+right)/2;
			if(target == nums[mid]) {
				return mid;
			}
			else if(target > nums[mid]) {
				left = mid + 1;
			}
			else {
				right = mid - 1;
			}
		}
		
		return left;
	}
	
	public static List<List<Integer>> combinationSum(int[] nums, int target) {
		Arrays.sort(nums);
		List<List<Integer>> finalResults = new ArrayList<>();
		for(int i = 0; i < nums.length; ++i) {
			List<Integer> l = new ArrayList<>();
			l.add(nums[i]);
			check(nums, i, nums[i], target, l, finalResults);
		}
		return finalResults;
	}

	private static void check(int[] nums, int index, int sum, int target, List<Integer> l, List<List<Integer>> finalResults) {
		if(sum == target) {
			finalResults.add(l);
		}
		else {
			for(int i = index; i < nums.length; ++i) {
				if(sum+nums[i] > target) {
					return;
				}
				List<Integer> ll = new ArrayList<>(l);
				ll.add(nums[i]);
				check(nums, i, sum + nums[i], target, ll, finalResults);
			}
		}
	}
	
	/**
	 * Given a collection of candidate numbers (candidates) and a target number (target), 
	 * find all unique combinations in candidates where the candidate numbers sums to target.
	 * Each number in candidates may only be used once in the combination.
	 * Note:
	 * All numbers (including target) will be positive integers.
	 * The solution set must not contain duplicate combinations.
	 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
	 * A solution set is:
	 * [
	 * [1, 7],
	 * [1, 2, 5],
	 * [2, 6],
	 * [1, 1, 6]
	 * ]
	 * Input: candidates = [2,5,2,1,2], target = 5,
	 * A solution set is:
	 * [
	 * [1,2,2],
	 * [5]
	 * ]
	 */
	public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
		Arrays.sort(candidates);
		List<List<Integer>> finalResults = new ArrayList<>();
		for(int i = 0; i < candidates.length; ++i) {
			List<Integer> l = new ArrayList<>();
			l.add(candidates[i]);
			check2(candidates, i, candidates[i], target, l, finalResults);
		}
		return finalResults;
    }
	
	private static void check2(int[] nums, int index, int sum, int target, List<Integer> l, List<List<Integer>> finalResults) {
		if(sum == target) {
			finalResults.add(l);
		}
		else {
			for(int i = index+1; i < nums.length; ++i) {
				if(sum+nums[i] > target) {
					return;
				}
				List<Integer> ll = new ArrayList<>(l);
				ll.add(nums[i]);
				check(nums, i+1, sum + nums[i], target, ll, finalResults);
			}
		}
	}
	
	/**
	 * Given an unsorted integer array, find the smallest missing positive integer.
	 * Input: [1,2,0]
	 * Output: 3
	 * Input: [3,4,-1,1]
	 * Output: 2
	 * Input: [7,8,9,11,12]
	 * Output: 1
	 */
	public static int firstMissingPositive(int[] nums) {
		// Move the negatives to the left and positives to the right
		int posIndex = moveNegativesToLeft(nums);
		
		if(posIndex == nums.length) { // All negatives
			return 1;
		}
		
		// Change the values at indices to negatives.
		for(int i= posIndex; i < nums.length; ++i) {
			if(Math.abs(nums[i])+posIndex-1 < nums.length) {
				nums[Math.abs(nums[i])+posIndex-1] *= (-1);
			}
		}
	  
	    // Return the first index value at which is positive 
		for(int i = posIndex; i < nums.length; ++i) {
			if(nums[i] > 0) {
				return i-posIndex+1;
			}
		}
		
		return nums.length - posIndex +1;
	}

	private static int moveNegativesToLeft(int[] nums) {
		int negIndex = 0;
		
		for(int i = 0; i < nums.length; ++i) {
			if(nums[i] <= 0) {
				// Swap
				int temp = nums[i];
				nums[i] = nums[negIndex];
				nums[negIndex++] = temp;
			}
		}
		
		return negIndex;
	}
	
	/**
	 * Given two non-negative integers num1 and num2 represented as strings, 
	 * return the product of num1 and num2, also represented as a string.
	 */
	public static String multiply(String num1, String num2) {
		if(num1.length() == 0 || num2.length() == 0) {
			return "0";
		}
		if(num1.equals("0") || num2.equals("0")) {
			return "0";
		}
		
		int res[] = new int[num1.length()+num2.length()];
		
		for(int i = num1.length()-1; i >= 0; --i) {
			int carry = 0;
			int d1 = charToInt(num1.charAt(i));
			int j = num2.length()-1;
			for(j = num2.length()-1; j >= 0; --j) {
				int d2 = charToInt(num2.charAt(j));
				int product = d1*d2;
				//Set this in the array;
				int dRes = product + carry + res[i+j+1];
				int idxRes = dRes%10;
				carry = dRes/10;
				res[i+j+1] = idxRes;
			}
			res[i+j+1] = carry;
		}
		
		return convertToString(res);
	}

	private static String convertToString(int[] res) {
		if(res.length == 0) {
			return "";
		}
		
		int startIndex = 0;
		for(int i = 0; i < res.length; ++i) {
			if(res[i] != 0) {
				startIndex = i;
				break;
			}
		}
		
		
		StringBuilder str = new StringBuilder();
		for(int i = startIndex; i < res.length; ++i) {
			
			str.append(res[i]);
		}
		
		return str.toString();
	}

	private static int charToInt(char digit) {
		if(digit =='0') {
			return 0;
		}
		
		return digit%48;
	}
	
	/**
	 * Given a collection of distinct integers, return all possible permutations.
	 */
	public static List<List<Integer>> permute(int[] nums) {
		List<Integer> numsList = Arrays.stream(nums).boxed().collect(Collectors.toList());
		List<List<Integer>> res = new ArrayList<>();
		
//		for(int i = 0; i < numsList.size(); ++i) {
//			List<Integer> source = new ArrayList<>(numsList);
//			List<Integer> dest = new ArrayList<>();
//			int s = source.remove(i);
//			dest.add(s);
//			permute(source, dest, res);
//		}
		permute(numsList, new ArrayList<Integer>(), res);
		
		return res;
    }

	private static void permute(List<Integer> source, List<Integer> dest, List<List<Integer>> res) {
		if(source.size() == 0) {
			res.add(dest);
		}
		
		for(int i = 0; i < source.size(); ++i) {
			List<Integer> source1 = new ArrayList<>(source);
			List<Integer> dest1 = new ArrayList<>(dest);
			int temp = source1.remove(i);
			dest1.add(temp);
			permute(source1, dest1, res);
		}
	}
	
	/**
	 * ou are given an n x n 2D matrix representing an image.
	 * Rotate the image by 90 degrees (clockwise).
	 * Note : DO NOT allocate another 2D matrix
	 */
	public static void rotate(int[][] matrix) {
		int n = matrix.length-1;
		
		printMatrix(matrix);
		System.out.println(matrix[n-1][0]);
		for(int i = 0; i <= matrix.length/2; ++i) { // Number of rotation cycles will be N/2
			for(int j = i; j < n-i; ++j) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[n-j][i];
				matrix[n-j][i] = matrix[n-i][n-j];
				matrix[n-i][n-j] = matrix[j][n-i];
				matrix[j][n-i] = temp;
			}
		}
		
		printMatrix(matrix);
	}

	public static void printMatrix(int[][] matrix) {
		StringBuilder str = new StringBuilder();
		str.append("[\n");
		for(int i = 0; i < matrix.length; ++i) {
			str.append("  [");
			for(int j = 0; j < matrix[i].length; ++j) {
				str.append(matrix[i][j] + ",");
			}
			str.append("]\n");
		}
		str.append("]");
		System.out.println(str.toString());
	}
	
	/**
	 * Given an array of strings, group anagrams together.
	 */
	public static List<List<String>> groupAnagrams(String[] strs) {
		List<String> sorted = new ArrayList<>(strs.length);
		for(String s : strs) {
			sorted.add(s.chars().sorted().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString());
		}
		Map<String, List<String>> res = new HashMap<>();

		for(int i = 0; i < sorted.size(); ++i) {
			res.computeIfAbsent(sorted.get(i), x -> new ArrayList<String>()).add(strs[i]);
		}
		
		return res.values().stream().collect(Collectors.toList());
    }
	
	public static List<List<String>> groupAnagramsOpt(String[] strs) {
		if(strs.length == 0) {
			return null;
		}
		
		int[] traversed = new int[strs.length];
		for(int i = 0; i < traversed.length; ++i) {
			traversed[i] = -1;
		}
		
		int curIdx = 0;
		while(true) {
			if(curIdx >= strs.length-1) {
				break;
			}
			traversed[curIdx] = curIdx;

			for(int i = curIdx+1; i < strs.length; ++i) {
				if(isAnagram3(strs[curIdx] ,strs[i])) {
					traversed[i] = curIdx;
				}
			}
			// find the next curIdx
			int i = 0;
			for(i = curIdx + 1; i < strs.length; ++i) {
				if(traversed[i] == -1) {
					break;
				}
			}
			curIdx = i;
		}
		
		Map<Integer, List<String>> retVal = new HashMap<>();
		for(int i = 0; i < traversed.length; ++i) {
			retVal.computeIfAbsent(traversed[i], x -> new ArrayList<>()).add(strs[i]);
		}
		
		return retVal.values().stream().collect(Collectors.toList());
	}
	
	/**
	 * Check if two strings are anagram or not
	 * Method 1 : Sort the strings and compare
	 */
	public static boolean isAnagram1(String str1, String str2) {
		if(str1.length() != str2.length()) {
			return false;
		}
		String sortedStr1 = str1.chars().sorted()
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		String sortedStr2 = str2.chars().sorted()
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		
		
		return sortedStr1.equals(sortedStr2);
	}
	
	/**
	 * Check if two strings are anagram or not
	 * Method 2 : Create an 2 arrays of possible chars and add to both iterating over the strings.
	 * 				If both arrays are same then return true.
	 * 				We can have only one array and we can add and subtract from the same array.
	 * 				Then we need to check if the array is all 0s.
	 */
	public static boolean isAnagram2(String str1, String str2) {
		if(str1.length() != str2.length()) {
			return false;
		}
		
		int arr[] = new int[256];
		
		for(int i = 0; i < str1.length(); ++i) {
			arr[str1.charAt(i)]++;
			arr[str2.charAt(i)]--;
		}
		
		for(int i = 0; i < 256; ++i) {
			if(arr[i] != 0) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Check if two strings are anagram or not
	 * Method 3: Using bit manipulation
	 * 			If we XOR all the characters of both the strings, we should get 0 (a XOR a = 0)
	 */
	public static boolean isAnagram3(String str1, String str2) {
		if(str1.length() != str2.length()) {
			return false;
		}
		
		int value = 0;
		for(int i = 0; i < str1.length(); ++i) {
			value = value ^ (int)str1.charAt(i);
			value = value ^ (int)str2.charAt(i);
		}
		
		return value == 0;
	}
	
	// TODO: Write a more optimized algo for pow.
	/**
	 * Implement pow(x, n), which calculates x raised to the power n (xn).
	 * Input: 2.00000, 10
	 * Output: 1024.00000
	 * Input: 2.10000, 3
	 * Output: 9.26100
	 * Input: 2.00000, -2
	 * Output: 0.25000
	 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
	 */
	public static double myPow(double x, int n) {
		double xOrig = x;
		
		if(x == 0) {
			return 0;
		}
		if(n == 0) {
			return 1;
		}
		if(n == 1) {
			return x;
		}
		
		if(n > 0) {
			int i = 2;
			for(i = 2; i <= n; i = i*2) {
				if(i*2 < i) {
					break; // bit overflow
				}
				x = x*x;
			}
			
			for(; i <=n; ++i) {
				if(i+1 < i) {
					break; // bit overflow;
				}
				x = x*xOrig;
			}
//			while(n > 1) {
//				x *= xOrig;
//				--n;
//			}
		}
		else {
			while(n <= 0) {
				x /= xOrig;
				++n;
			}
		}
		
		return x;
    }
	
	/**
	 * Given an integer array nums, find the contiguous subarray 
	 * (containing at least one number) which has the largest sum and return its sum.
	 * Input: [-2,1,-3,4,-1,2,1,-5,4],
	 * Output: 6
	 * Explanation: [4,-1,2,1] has the largest sum = 6.
	 */
	public static int maxSubArray(int[] nums) {
		int maxSum = Integer.MIN_VALUE;
		int sum = 0;
		
		int i = 0;
		while(i < nums.length) {
			sum = sum + nums[i];
			if(sum > maxSum) {
				maxSum = sum;
			}
			if(sum < 0) { // Restart summing
				sum = 0;
			}
			++i;
		}
		
		return maxSum;
	}
	
	/**
	 * Same as above algo, just instead of the sum, it returns the subarray.
	 */
	public static Integer[] maxSubArray1(Integer[] nums) {
		int fStart = 0;
		int fEnd = 0;
		
		int start = 0;
		int maxSum = Integer.MIN_VALUE;
		int sum = 0;
		
		int i = 0;
		while(i < nums.length) {
			sum += nums[i];
			if(sum > maxSum) {
				fStart = start;
				maxSum = sum;
				fEnd = i;
			}
			if(sum < 0) {
				sum = 0;
				start = i+1; // Reset the start index to next element in the array.
			}
			++i;
		}
		
		return Arrays.copyOfRange(nums, fStart, fEnd+1);
	}
	
	/**
	 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', 
	 * return the length of last word in the string.
	 */
	public static int lengthOfLastWord(String s) {
		if(s.isEmpty()) {
			return 0;
		}
		
		int i = s.length()-1; // last index
		int count = 0;
		boolean foundAlpha = false;
		while(i >= 0) {
			if(!foundAlpha && s.charAt(i) != ' ') { // Mark the first occurence of an alphabet
				foundAlpha = true;
			}
			if(foundAlpha && s.charAt(i) == ' ') { // If already encountered a character and reached the end of that word, break.
				break;
			}
			
			if(s.charAt(i) != ' ') {
				count++;
			}
			--i;
		}
		return count; 
	}
	
	/**
	 * Given a non-empty array of digits representing a non-negative integer, add one to the integer.
	 */
	public static int[] plusOne(int[] digits) {
		if(digits.length == 0) {
			return new int[] {1};
		}
		
		boolean isUnit = true;
		
		int carry = 0;
		for(int i = digits.length-1; i >=0; --i) {
			if(isUnit) {
				digits[i] = digits[i] + 1 + carry;
				isUnit = !isUnit;
			}
			else {
				digits[i] = digits[i] + carry;
			}
			carry = digits[i]/10;
			digits[i] = digits[i]%10;
		}
		if(carry == 0) {
			return digits;
		}
		else {
			int[] retArr = new int[digits.length+1];
			retArr[0] = carry;
			Arrays.copyOfRange(digits, 1, digits.length+1);
			return retArr;
		}
	}
	
	/**
	 * Implement int sqrt(int x).
	 * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
	 * Since the return type is an integer, the decimal digits are truncated 
	 * and only the integer part of the result is returned.
	 */
	public static int mySqrt(int x) {
		if(x == 1 || x == 0) {
			return x;
		}
		
		int root = 2;
		while(Math.pow(root, 2) < x) {
			++root;
		}
		
		return Math.pow(root, 2) == x ? root : root-1;
	}
	
	// Using binary search
	public static int mySqrtOpt(int x) {
		if(x == 0 || x == 1) {
			return x;
		}
		
		int start = 0, end = x, ans=start;
		
		int mid = 0;
		while(start <= end) {
			mid = (start+end)/2;
			
			if(mid*mid == x) {
				return mid;
			}
			if(Integer.MAX_VALUE/mid < mid) { // mid^2 will surpass int length. So number should be less that mid.
				end = mid-1;
			}
			else if(mid*mid < x) {// square root of x lies on the right side.
				start = mid+1;
				ans = mid;
			}
			else {
				end = mid-1; // square root of x lies on the left side.
			}
		}
		
		return ans;
	}
	
	/**
	You are climbing a stair case. It takes n steps to reach to the top.

	Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

	Note: Given n will be a positive integer.

	Example 1:

	Input: 2
	Output: 2
	Explanation: There are two ways to climb to the top.
	1. 1 step + 1 step
	2. 2 steps
	*/
	
	static int stairsCount = 0;
	public static int climbStairs(int n) {
		climbStairsAlgo(n);
		return stairsCount;
    }
	
	public static void climbStairsAlgo(int sum) {
		if(sum == 0) {
			++stairsCount;
			return;
		}
		
		if(sum < 0) {
			return;
		}
		
		climbStairsAlgo(sum-1);
		climbStairsAlgo(sum-2);
	}
	
	/**
	 * The previos algo will create a huge recursion stack.
	 * Using DP memoization technique to optimize.
	 * @param n
	 * @return
	 */
	public static int climbStairsDP(int n) {
		Map<Integer, Integer> map = new HashMap<>();
		return climbStairsDP(n, map);
	}

	private static int climbStairsDP(int sum, Map<Integer, Integer> map) {
		if(sum < 0) {
			return 0; // There is no possible way
		}
		if(sum == 0) {
			return 1; // Only one way i.e. take one stairs
		}
		
		if(map.containsKey(sum)) {
			return map.get(sum);
		}
		
		// Calculate the number of possible ways to take step
		int numWays = climbStairsDP(sum-1, map) + climbStairsDP(sum-2, map); // Take 1 step or two steps.
		map.put(sum, numWays);
		
		return numWays;
	}
}


