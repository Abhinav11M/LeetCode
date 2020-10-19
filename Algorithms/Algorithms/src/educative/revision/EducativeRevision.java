package educative.revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import educative.fastandslowpointers.ListNode;

public class EducativeRevision {
	
	/**========================
	 * ========================
	 * ==== Sliding Window ====
	 * ========================
	 * ========================
	 */

	/**
	 * Maximum Sum Subarray of Size K (easy)
	 */
	public int findMaxSumSubArray(int k, int[] arr) {
		int sum = 0;
		int i = 0;
		for(; i < k; ++i) {
			sum += arr[i];
		}

		int maxSum = sum;
		
		int left = 0;
		while(i < arr.length) {
			sum = sum + arr[i++] - arr[left++];
			maxSum = Math.max(maxSum, sum);
		}

		return maxSum;
	}
	
	/**
	 * Smallest Subarray with a given sum (easy)
	 */
	public int findMinSubArray(int S, int[] arr) {
		int sum = 0;
		int idx = 0;
		int count = 0;
		while(idx < arr.length && sum < S) {
			++count;
			sum += arr[idx++];
		}
		
		int minCount = count;
		int left = 0;
		
		while(idx < arr.length) {
			if(sum > S) {
				sum = sum - arr[left++];
				--count;
			}
			else {
				sum = sum + arr[idx++];
				++count;
			}
			minCount = Math.min(minCount, count);
		}
		
		// If the number is at the last index and making sure atleast one element is picked up.
		while(sum > S && count > 1) {
			sum = sum - arr[left++];
			--count;
		}
		
		return Math.min(minCount, count);
	}

	/**
	 * Longest Substring with K Distinct Characters (medium)
	 */
	public int findLength(String str, int k) {
		int max = 0;
		
		int left = 0, right = 0;
		HashMap<Character, Integer> map = new HashMap<>();
		for(; right < str.length(); ++right) {
			
			map.put(str.charAt(right), right);

			if(map.size() > k) { // remove one char
				while(true) {
					char curr = str.charAt(left);
					if(map.get(curr) == left) {
						map.remove(curr);
						++left;
						break;
					}
					++left;
				}
			}
			
			max = Math.max(max, right-left+1);
		}

		return max;
	}
	
	/**
	 * Fruits into Baskets (medium)
	 */
	public int findMaxBasketSize(char[] arr) {
		HashMap<Character, Integer>	map = new HashMap<>();
		int max = 0;
		
		int left = 0, right = 0;
		for(; right < arr.length; ++right) {
			map.put(arr[right], map.getOrDefault(arr[right], 0) + 1);
		
			// Only 2 baskets are available
			while(map.size() > 2) {
				char curr = arr[left];
				map.put(curr, map.get(curr)-1);

				if(map.get(curr) == 0) {
					map.remove(curr);
				}
				++left;
			}
			
			max = Math.max(max, right-left+1);
		}
		
		return max;
	}
	
	/**
	 * No-repeat Substring (hard)
	 */
	public int findLongestNonRepeatingSubstr(String s) {
		int max = 0;
		HashMap<Character, Integer> map = new HashMap<>();
		
		int left = 0, right = 0;
		for(; right < s.length(); ++right) {
			char curr = s.charAt(right);
			if(map.containsKey(curr) && map.get(curr) >= left) {
				left = map.get(curr) + 1;
			}
			map.put(curr, right);
			max = Math.max(max, right - left + 1);
		}
		return max;
	}
	
	/**
	 * Longest Substring with Same Letters after Replacement (hard)
	 */
	public int findLongestRepeatingStrWithKReplacement(String str, int k) {
		int max = 0;
		int left = 0, right = 0, maxRepeatingCharFreq = 0;
		HashMap<Character, Integer> map = new HashMap<>();
		
		for(; right < str.length(); ++right) {
			char curr = str.charAt(right);
			map.put(curr, map.getOrDefault(curr, 0) + 1);
			
			maxRepeatingCharFreq = Math.max(maxRepeatingCharFreq, map.get(curr));
			
			// right - left + 1 = Number of elements in the window
			// Window length - maxRepeating should not cross k
			if (right - left + 1 - maxRepeatingCharFreq > k) { 
				// shrink the window from left
				map.put(str.charAt(left), map.get(str.charAt(left))-1);
				++left;
			}
			max = Math.max(max, right - left + 1);
		}
		
		
		return max;
	}
	
	/**
	 * Longest Subarray with Ones after Replacement (hard)
	 */
	public int findLongestArrayWithOnes(int[] arr, int k) {
		int max = 0;
		int left = 0, right = 0;
		
		int zeroCount = 0;
		
		for(; right < arr.length; ++right) {
			int curr = arr[right];
			if(curr == 0) {
				++zeroCount;
			}
			if(zeroCount > k) {
				while(left < arr.length && arr[left] != 0) {
					++left;
				}
				++left;
				--zeroCount;
			}
			
			max = Math.max(max, right - left + 1);
		}
		
		return max;
	}
	
	/**
	 * Permutation in a String (hard)
	 */
	public boolean findPermutation(String str, String pattern) {
		// Keep the freq of chars in the pattern
		HashMap<Character, Integer> map = new HashMap<>();
		for(char ch : pattern.toCharArray()) {
			map.put(ch, map.getOrDefault(ch, 0) + 1);
		}
		
		HashMap<Character, Integer> removed = new HashMap<>();
		
		int left = 0;
		for(; left < str.length(); ++left) {
			char curr = str.charAt(left);
			if(map.containsKey(curr)) {
				map.put(curr, map.get(curr) - 1);
				removed.put(curr, map.getOrDefault(curr, 0) + 1);
				if(map.get(curr) == 0) {
					map.remove(curr);
				}
			}
			else {
				// put all the removed chars back in map
				for(Map.Entry<Character, Integer> entry : removed.entrySet()) {
					char ch = entry.getKey();
					map.put(ch, map.getOrDefault(ch, 0) + entry.getValue());
				}
				removed.clear();
			}
			
			if(map.size() == 0) {
				return true;
			}
		}
		
		return false;
	}

	public boolean findPermutationEducativeSol(String str, String pattern) {
		
		// Matched represents the numbers of distinct chars to match
		int right = 0, left = 0, matched = 0;
		// Keep the freq of chars in the pattern
		HashMap<Character, Integer> map = new HashMap<>();
		for(char ch : pattern.toCharArray()) {
			map.put(ch, map.getOrDefault(ch, 0) + 1);
		}
		
		for(; right < str.length(); ++right) {
			char curr = str.charAt(right);
			if(map.containsKey(curr)) {
				map.put(curr, map.get(curr)-1);
				if(map.get(curr) == 0) { // we matched 1 char (all freq)
					++matched;
				}
			}
			
			// If all matched
			if(map.size() == matched) {
				return true;
			}
			
			if(right >= pattern.length()-1) { // window size reached pattern length but not matched all
				char leftChar = str.charAt(left);
				if(map.containsKey(leftChar)) {
					if(map.get(leftChar) == 0) { // If the freq is down to 0, it means that the character was matched already.
						--matched;
					}
					map.put(leftChar, map.get(leftChar) + 1);
				}
				++left;
			}
		}
		
		return false;
	}
	
	/**
	 * String Anagrams (hard)
	 */
	public List<Integer> findStringAnagrams(String str, String pattern) {
		int left = 0, right = 0, matched = 0;
		List<Integer> res = new ArrayList<>();
		
		HashMap<Character, Integer> map = new HashMap<>();
		for(char ch : pattern.toCharArray()) {
			map.put(ch, map.getOrDefault(ch, 0) + 1);
		}
		
		for(; right < str.length(); ++right) {
			char curr = str.charAt(right);
			
			if(map.containsKey(curr)) {
				map.put(curr, map.get(curr)-1);
				if(map.get(curr) == 0) {
					++matched;
				}
			}
			
			if(matched == map.size()) {
				res.add(left);
			}
			
			if(right >= pattern.length()-1) {
				char leftChar = str.charAt(left++);
				if(map.containsKey(leftChar)) {
					if(map.get(leftChar) == 0) {
						--matched;
					}
					map.put(leftChar, map.get(leftChar) + 1);
				}
			}
		}
		
		return res;
	}
	
	/**
	 * Smallest Window containing Substring (hard)
	 */
	public String findSubstring(String s, String t) {
		int left = 0, right = 0, matched = 0;
		int minLen = Integer.MAX_VALUE;
		String res = "";

		HashMap<Character, Integer> map = new HashMap<>();
		for(char ch : t.toCharArray()) {
			map.put(ch, map.getOrDefault(ch, 0) + 1);
		}
		
		for(; right < s.length(); ++right) {
			char curr = s.charAt(right);
			
			if(map.containsKey(curr)) {
				map.put(curr, map.get(curr) - 1);
				if(map.get(curr) == 0) {
					++matched;
				}
			}
			
			if(matched == map.size()) {
				if(minLen > (right - left + 1)) {
					res = s.substring(left, right + 1);
					minLen = right - left + 1;
				}
				
				while(matched == map.size()) {
					char leftChar = s.charAt(left++);
					if(map.containsKey(leftChar)) {
						if(map.get(leftChar) == 0) {
							--matched;
						}
						map.put(leftChar, map.get(leftChar) + 1);
					}
					if(matched == map.size() && res.length() > right-left+1) {
						res = s.substring(left, right+1);
						minLen = res.length();
					}
				}
			}
		}
		
		return res;
	}
	
	/**
	 * Words Concatenation (hard)
	 */
	public List<Integer> findWordConcatenation(String str, String[] words) {
		List<Integer> res = new ArrayList<>();
		
		int nWords = words.length;
		int wLen = words[0].length();
		HashMap<String, Integer> map = new HashMap<>();
		for(String word : words) {
			map.put(word, map.getOrDefault(word, 0) + 1);
		}
		
		for(int i = 0; i <= str.length()-nWords*wLen; ++i) {
			HashMap<String, Integer> seenWords = new HashMap<>();
			for(int j = 0; j < nWords; ++j) {
				int wordIdx = i + j*wLen; 
				String sub = str.substring(wordIdx, wordIdx + wLen);
				// check if the word exists in the map or not
				if(!map.containsKey(sub)) {
					// No need to advance ahead to find next words
					break;
				}
				// word found
				seenWords.put(sub, seenWords.getOrDefault(sub, 0) + 1);
				
				// We need to make sure that not only the word exists, but also the count 
				// of the word is same in the concat string
				if(seenWords.get(sub) > map.get(sub)) {
					// Word freq is more in the string than the word array
					break;
				}
				
				// If j has reached the last index, then all the words have been found.
				if(j+1 == words.length) {
					// found all words starting index i
					res.add(i);
				}
				
			}
		}
		
		return res;
	}

	/**========================
	 * ========================
	 * ==== Sliding Window ====
	 * ========================
	 * ========================
	 */
	
	// ======================================================================================================
	
	/**========================
	 * ========================
	 * ===== Two pointers =====
	 * ========================
	 * ========================
	 */
	
	/**
	 * Pair with Target Sum (easy)
	 */
	public int[] pairWithTargetSum(int[] arr, int targetSum) {
		HashMap<Integer, Integer> map = new HashMap<>();
		
		for(int i = 0; i < arr.length; ++i) {
			if(map.containsKey(arr[i])) {
				return new int[] { map.get(arr[i]), i};
			}
			map.put(targetSum - arr[i], i);
		}
		
		return new int[] { -1, -1 };
	}
	
	/**
	 * Remove Duplicates (easy)
	 */
	public int removeDuplicates(int[] arr) {
		int left = 1, right = 1;
		int prev = arr[0];
		
		while(right < arr.length) {
			int curr = arr[right];
			
			if(curr == prev) {
				++right;
			}
			else {
				arr[left] = arr[right];
				++left;
				++right;
				prev = curr;
			}
		}
		
		return left;
	}
	
	public int[] makeSquares(int[] arr) {
		int[] result = new int[arr.length];
		
		// Find the 1st positive number
		int i = 0;
		while(i < arr.length && arr[i] < 0) {
			++i;
		}
		
		int i1 = i-1, i2 = i;
		int r = 0;
		while(i1 >= 0 && i2 < arr.length) {
			if(Math.abs(arr[i1]) < arr[i2]) {
				result[r++] = arr[i1]*arr[i1];
				--i1;
			}
			else {
				result[r++] = arr[i2]*arr[i2];
				++i2;
			}
		}
		
		// If either sides have exhausted
		while(i1 >= 0) {
			result[r++] = arr[i1] * arr[i1];
			--i1;
		}
		while(i2 < arr.length) {
			result[r++] = arr[i2] * arr[i2];
			++i2;
		}
		
		return result;
	}

	public int[] makeSquares2(int[] arr) {
		int[] result = new int[arr.length];
		
		int left = 0, right = arr.length-1;
		int r = arr.length-1; // fill from the right
		
		while(left <= right) {
			int val1 = arr[left] * arr[left];
			int val2 = arr[right] * arr[right];
			
			if(val1 > val2) {
				result[r--] = val1;
				++left;
			}
			else {
				result[r--] = val2;
				--right;
			}
		}
		
		return result;
	}
	
	/**
	 * Triplet Sum to Zero (medium)
	 */
	public List<List<Integer>> searchTriplets(int[] arr) {
		List<List<Integer>> res = new ArrayList<>();
		
		
		for(int i = 0; i < arr.length-2; ++i) {
			int target = -arr[i];
			HashSet<Integer> set = new HashSet<>();
			for(int j = i+1; j < arr.length; ++j) {
				if(set.contains(arr[j])) {
					res.add(Lists.newArrayList(arr[i], target-arr[j], arr[j]));
				}
				else {
					set.add(target-arr[j]);
				}
			}
		}
		
		return res;
	}

	public List<List<Integer>> searchTripletsEduSol(int[] arr) {
		Arrays.sort(arr);
		List<List<Integer>> res = new ArrayList<>();
		
		for(int i = 0; i < arr.length-2; ++i) {
			if(i != 0 && arr[i] == arr[i-1]) { // To handle duplicates
				continue;
			}
			
			findTargetPairs(arr, i+1, arr.length-1, -arr[i], res);
		}
		
		return res;
	}

	// Note: arr is a sorted array.
	private void findTargetPairs(int[] arr, int left, int right, int target, List<List<Integer>> res) {
		while(left < right) {
			int sum = arr[left] + arr[right];
			if(sum == target) {
				res.add(Lists.newArrayList(-target, arr[left], arr[right]));
				++left;
				--right;
				
				// keep moving till we can have duplicates
				while(left < right && arr[left] == arr[left-1]) {
					++left;
				}
				while(right > left && arr[right] == arr[right+1]) {
					--right;
				}
			}
			else if(sum > target) {
				--right;
			}
			else {
				++left;
			}
		}
	}

	/**
	 * Triplet Sum Close to Target (medium)
	 */
	public int tripletSumCloseToTarget(int[] arr, int target) {
		// Sort the array
		Arrays.sort(arr);
		
		int achievedTarget = Integer.MAX_VALUE;
		int minSum = 0;
		
		for(int i = 0; i < arr.length-2; ++i) {
			int reqd = target - arr[i];
			int left = i+1, right = arr.length-1;

			while(left < right) {
				int sum = arr[left] + arr[right];
				if(reqd == sum) {
					return 0;
				}
				else if(reqd > sum) {
					++left;
				}
				else {
					--right;
				}
				if(achievedTarget > Math.abs(reqd-sum)) {
					achievedTarget = Math.abs(reqd-sum);
					minSum = arr[i] + sum;
				}
			}
		}
		
		return minSum;
	}
	
	/**
	 * Subarrays with Product Less than a Target (medium)
	 */
	public List<List<Integer>> subarrayProductLessThanK(int[] arr, int target) {
		List<List<Integer>> res = new ArrayList<>();
		
		int product = 1;
		int right = 0, left = 0;
		
		for(; right < arr.length; ++right) {
			product *= arr[right];
			
			// keep moving towards right if product >= target
			while(product >= target) {
				product /= arr[left++];
			}
			
			// Create all contiguous subarrays from left to right
			// To avoid duplicates, we copy from right to left, otherwise arr[left] will be copied everytime
			List<Integer> temp = new LinkedList<>();
			for(int i = right; i >= left; --i) {
				temp.add(0, arr[i]);
				res.add(new ArrayList<>(temp));
			}
		}
		
		return res;
	}
	
	/**
	 * Dutch National Flag Problem (medium)
	 */
	public void dutchNationalFlag(int[] arr) {
		int left = 0, right = arr.length-1;
		int i = 0;
		while(i <= right) { // we only want to go till right as we keep sorted elements at right during swap
			if(arr[i] == 0) {
				// swap and increment i and left
				swap(arr, i, left);
				++left;
				++i;
			}
			else if(arr[i] == 1) {
				// no need to swap
				++i;
			}
			else {
				// Swap and decrement right. Don't increment i as we don't know what comes from right.
				// It may be 0, 1 or 2.
				swap(arr, i, right);
				--right;
			}
		}
		
	}

	private void swap(int[] arr, int i1, int i2) {
		int temp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = temp;
	}
	
	/*
	 * Comparing Strings containing Backspaces (medium)
	 */
	public boolean compareStringWithBackSpaces(String str1, String str2) {
		
		int i1 = str1.length()-1;
		int i2 = str2.length()-1;
		
		while(i1 >= 0 || i2 >= 0) {

			i1 = getNextValidChar(str1, i1);
			i2 = getNextValidChar(str2, i2);
			
			if(i1 < 0 && i2 < 0) {
				return true;
			}
			
			if(i1 < 0 || i2 < 0) {
				return false;
			}
			
			if(str1.charAt(i1) != str2.charAt(i2)) {
				return false;
			}
			
			--i1;
			--i2;
		}
		
		return true;
	}

	private int getNextValidChar(String str, int index) {
		int backSpace = 0;
		while(index >= 0) {
			if(str.charAt(index) == '#') {
				++backSpace;
			}
			else if(backSpace > 0) {
				--backSpace;
			}
			else {
				break;
			}
			--index;
		}
		
		return index;
	}
	
	/**
	 * Minimum Window Sort (medium)
	 */
	public int minimumWindowSort(int[] arr) {
		int left = 0, right = arr.length-1;
		
		while(left < arr.length-1 && arr[left] < arr[left+1]) {
			++left;
		}
		
		if(left == arr.length-1) {
			// array already sorted
			return 0;
		}
		
		while(arr[right] > arr[right-1]) {
			--right;
		}
		
		// Window that needs sorting lies b/w left and right(inclusive)
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int i = left; i <= right; ++i) {
			if(arr[i] < min) {
				min = arr[i];
			}
			if(arr[i] > max) {
				max = arr[i];
			}
		}
		
		--left;
		while(left >=0 && arr[left] > min) {
			--left;
		}
		
		++right;
		while(right < arr.length && arr[right] < max) {
			++right;
		}
		
		return (right - left - 1);
	}
	
	/**========================
	 * ========================
	 * ===== Two pointers =====
	 * ========================
	 * ========================
	 */

	// ======================================================================================================

	/**========================
	 * ========================
	 * = Fast & Slow pointers =
	 * ========================
	 * ========================
	 */
	
	/**
	 * LinkedList cycle
	 */
	public boolean hasCycle(ListNode head) {
		
		ListNode fast = head;
		ListNode slow = head;
		
		while(fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			
			if(fast == slow) {
				return true; // found cycle
			}
		}
		
		return false;
	}
	
	/**
	 * Start of linkedlist cycle
	 */
	public ListNode findCycleStart(ListNode head) {
		
		ListNode fast = head;
		ListNode slow = head;
		
		while(fast!= null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			
			if(fast == slow) {
				break;
			}
		}
		
		// Reposition the slow pointer to the head
		slow = head;
		while(fast != slow) {
			fast = fast.next;
			slow = slow.next;
		}
		
		return fast;
	}
	
	/**
	 * Happy number
	 */
	public boolean isHappyNumber(int num) {
		
		int slow = getSumOfSquares(num);
		int fast = getSumOfSquares(getSumOfSquares(num));
		
		while(fast != slow) {
			slow = getSumOfSquares(slow);
			fast = getSumOfSquares(getSumOfSquares(fast));
		}
		
		return slow == 1;
	}

	private int getSumOfSquares(int num) {
		int sum = 0;
		while(num > 0) {
			int d = num%10;
			sum += d*d;
			num /= 10;
		}

		return sum;
	}
	
	/**
	 * Middle of a linked list
	 */
	public ListNode findMiddle(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
		
		while(fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		
		return slow;
	}
	
	/**
	 * Palindrome LinkedList (medium)
	 */
	public boolean isPalindrome(ListNode head) {
		ListNode mid = getMiddleNode(head);
		ListNode left = head;
		ListNode right = mid.next;
		mid.next = null; // break the chain
		
		// Reverse right;
		right = reverseList(right);
		
		while(left != null && right != null) {
			if(left.value != right.value) {
				return false;
			}
			
			left = left.next;
			right = right.next;
		}
		
		return true;
	}

	private ListNode reverseList(ListNode head) {
		
		ListNode res = null;
		while(head != null) {
			ListNode temp = head;
			head = head.next;
			temp.next = res;
			res = temp;
		}
		
		return res;
	}

	private ListNode getMiddleNode(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
		
		while(fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		
		return slow;
	}
	
	/**
	 * Rearrange a LinkedList
	 */
	public void reorder(ListNode head) {
		ListNode mid = getMiddleNode(head);
		ListNode right = mid.next;
		mid.next = null;
		
		// reverse the right half
		right = reverseList(right);
		ListNode left = head;
		
		ListNode nextLeft = left;
		ListNode nextRight = right;
		while(right != null) {
			nextLeft = left.next;
			left.next = right;
			left = nextLeft;
			nextRight = right.next;
			right.next = nextLeft;
			right = nextRight;
		}
	}
	
	/**
	 * Cycle in a Circular Array
	 */
	public boolean circularArrayLoop(int[] arr) {
		
		for(int i = 0; i < arr.length; ++i) {
			boolean isForward = arr[i] > 0;;
			int slow = i;
			int fast = getNextIndex(arr, i, isForward);
			if(fast != -1) {
				fast = getNextIndex(arr, fast, isForward);
			}
			
			while(fast != -1 && slow != -1 && fast != slow) {
				isForward = arr[fast] > 0;
				fast = getNextIndex(arr, fast, isForward);
				if(fast != -1) {
					fast = getNextIndex(arr, fast, isForward);
				}
				
				slow = getNextIndex(arr, slow, isForward);
			}
			
			if(fast != -1 && fast == slow) {
				return true;
			}
		}
		
		return false;
	}

	private int getNextIndex(int[] arr, int curIndex, boolean isForward) {
		boolean direction = arr[curIndex] > 0;
		if(isForward != direction) {
			return -1; // direction getting reversed. No possible index
		}
		
		int nextIdx = (curIndex + arr[curIndex]) % arr.length;
		if(nextIdx < 0) { // If we are moving in negative, we need to go to the correct index
			nextIdx += arr.length;
		}
		
		// The cycle should have more that one element. So we need to ignore one element cycle.
		if(curIndex == nextIdx) {
			return -1;
		}
		
		return nextIdx;
	}

	
	/**========================
	 * ========================
	 * = Fast & Slow pointers =
	 * ========================
	 * ========================
	 */
	
	// ======================================================================================================
}
