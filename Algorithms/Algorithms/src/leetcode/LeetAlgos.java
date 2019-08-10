package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

import datastructures.ListNode;

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
}

