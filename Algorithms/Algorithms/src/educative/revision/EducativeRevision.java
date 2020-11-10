package educative.revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import educative.fastandslowpointers.ListNode;
import educative.tree.dfs.TreeNode;
import educative.twoheaps.Interval;
import lombok.ToString;

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
	// ======================================================================================================

	/**========================
	 * ========================
	 * === Merge Intervals ====
	 * ========================
	 * ========================
	 */

	/**
	 * Merge Intervals (medium)
	 */
	public List<Interval> merge(List<Interval> intervals) {
		List<Interval> res = new ArrayList<>();

		Collections.sort(intervals, (a,b) -> a.start - b.start);
		int start = intervals.get(0).start;
		int end = intervals.get(0).end;

		for(int i = 1; i < intervals.size(); ++i) {
			Interval curr = intervals.get(i);
			if(curr.start > end) {
				res.add(new Interval(start, end));
				start = curr.start;
				end = curr.end;
			}
			else {
				end = Math.max(end, curr.end);
			}
		}

		res.add(new Interval(start, end));

		return res;
	}

	/**
	 * Insert Interval (medium)
	 * Given a list of non-overlapping intervals sorted by their start time
	 */
	public List<Interval> insertInterval(List<Interval> intervals, Interval newInterval) {
		List<Interval> res = new ArrayList<>();

		// Copying non-overlapping
		int i = 0;
		for(; i < intervals.size(); ++i) {
			if(intervals.get(i).end < newInterval.start) {
				res.add(intervals.get(i));
			}
			else {
				break;
			}
		}

		// Overlapping intervals
		int start = newInterval.start;
		int end = newInterval.end;

		while(i < intervals.size() && intervals.get(i).start <= end) {
			start = Math.min(start, intervals.get(i).start);
			end = Math.max(end, intervals.get(i).end);
			++i;
		}

		res.add(new Interval(start, end));

		while(i < intervals.size()) {
			res.add(intervals.get(i++));
		}

		return res;
	}

	/**
	 * Intervals Union (Not educative)
	 */
	public List<Interval> mergeIntervalLists(Interval[] arr1, Interval[] arr2) {
		List<Interval> res = new ArrayList<>();

		int i1 = 0, i2 = 0;
		// Get the first merge interval to begin with
		Interval merge = arr1[i1].start < arr2[i2].start ? arr1[i1++] : arr2[i2++];

		while(i1 < arr1.length && i2 < arr2.length) {
			Interval interval1 = arr1[i1];
			Interval interval2 = arr2[i2];

			if(interval1.start <= merge.end) { // Overlap with first interval
				mergeInterval(merge, interval1);
				++i1;
			}
			else if(interval2.start <= merge.end) { // Overlap with the second interval
				mergeInterval(merge, interval2);
				++i2;
			}
			else { // No overlap
				res.add(merge);
				// Reset merge to the next interval with smallest start
				merge = interval1.start < interval2.start ? arr1[i1] : arr2[i2];
			}
		}

		while(i1 < arr1.length) {
			Interval curr = arr1[i1];
			if(curr.start <= merge.end) {
				mergeInterval(merge, curr);
				++i1;
			}
			else {
				res.add(merge);
				merge = curr;
			}
		}

		while(i2 < arr2.length) {
			Interval curr = arr2[i2];
			if(curr.start <= merge.end) {
				mergeInterval(merge, curr);
				++i2;
			}
			else {
				res.add(merge);
				merge = curr;
			}
		}

		res.add(merge);
		return res;
	}

	private void mergeInterval(Interval mergeInto, Interval interval) {
		mergeInto.start = Math.min(mergeInto.start, interval.start);
		mergeInto.end = Math.max(mergeInto.end, interval.end);
	}

	/**
	 * Interval Intersection
	 */
	public Interval[] intervalIntersection(Interval[] arr1, Interval[] arr2) {
		List<Interval> res = new ArrayList<>();

		int i = 0, j = 0;

		while(i < arr1.length && j < arr2.length) {
			Interval i1 = arr1[i];
			Interval i2 = arr2[j];

			if ((i1.start <= i2.start && i1.end >= i2.end) || (i1.start >= i2.start && i1.start <= i2.end)) {
				// Find intersection
				int start = Math.max(i1.start, i2.start);
				int end = Math.min(i1.end, i2.end);
				res.add(new Interval(start, end));
			}

			// Increment the counter
			if(i1.end > i2.end) {
				++j;
			}
			else {// if(i1.end < i2.end) {
				++i;
			}
		}

		return res.toArray(new Interval[0]);
	}

	/**
	 * Conflicting Appointments (medium)
	 */
	public boolean canAttendAllAppointments(Interval[] intervals) {
		// Sort based on start time
		Arrays.sort(intervals, (a,b) -> Integer.compare(a.start, b.start));

		Interval prev = intervals[0];
		int i = 1;
		while(i < intervals.length) {
			Interval curr = intervals[i];

			if(curr.start < prev.end) {
				return false;
			}
			prev = curr;
			++i;
		}

		return true;
	}

	/**
	 * Minimum Meeting Rooms (hard)
	 */

	public static class Meeting {
		int start;
		int end;

		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}
	};

	public int findMinimumMeetingRooms(List<Meeting> meetings) {
		Collections.sort(meetings, (a,b) -> Integer.compare(a.start, b.start));

		// MinHeap sorted based on min end time (So the meeting that gets over first will be at top)
		PriorityQueue<Meeting> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a.end, b.end));

		int minRooms = 0;
		for(Meeting meeting : meetings) {
			// Remove all meetings that are over before current meeting begins
			while(!minHeap.isEmpty() && minHeap.peek().end <= meeting.start) {
				minHeap.poll();
			}
			// Add the current meeting to the heap.
			minHeap.offer(meeting);

			minRooms = Math.max(minRooms, minHeap.size());
		}

		return minRooms;
	}

	/**
	 * Maximum CPU Load
	 */
	@ToString
	public static class Job {
		int start;
		int end;
		int cpuLoad;

		public Job(int start, int end, int cpuLoad) {
			this.start = start;
			this.end = end;
			this.cpuLoad = cpuLoad;
		}
	};

	public int findMaxCPULoad(List<Job> jobs) {
		// Sort jobs based on start time
		Collections.sort(jobs, (a,b) -> Integer.compare(a.start, b.start));
		// MinHeap sorted by min end time
		PriorityQueue<Job> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a.end, b.end));
		int maxCpuLoad = 0;
		int currCpuLoad = 0;

		for(int i = 0; i < jobs.size(); ++i) {
			Job curr = jobs.get(i);
			while(!minHeap.isEmpty() && curr.start >= minHeap.peek().end) {
				Job job = minHeap.poll();
				currCpuLoad -= job.cpuLoad;
			}

			minHeap.offer(curr);
			currCpuLoad += curr.cpuLoad;

			maxCpuLoad = Math.max(maxCpuLoad, currCpuLoad);
		}

		return maxCpuLoad;
	}

	/**
	 * Employee Free Time (hard)
	 */
	@ToString
	public static class EmployeeInterval {
		Interval interval;
		int employeeIndex;
		int intervalIndex;

		EmployeeInterval(Interval interval, int employeeIndex, int intervalIndex) {
			this.interval = interval;
			this.employeeIndex = employeeIndex;
			this.intervalIndex = intervalIndex;
		}
	}

	public List<Interval> findEmployeeFreeTime(List<List<Interval>> schedule) {
		List<Interval> res = new ArrayList<>();

		// Min heap to keep the employee interval information sorted on start time
		PriorityQueue<EmployeeInterval> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a.interval.start, b.interval.start));

		// Push the first interval of each employee into the minHeap
		for(int i = 0; i < schedule.size(); ++i) {
			minHeap.offer(new EmployeeInterval(schedule.get(i).get(0), i, 0));
		}

		// If we have a non-overlapping interval b/w any of the previous and the current interval,
		// it means that, that interval is a free interval for all the employees.
		Interval prev = minHeap.peek().interval;

		while(!minHeap.isEmpty()) {
			EmployeeInterval curr = minHeap.poll();

			if(curr.interval.start > prev.end) { // no-overlap, so added to result
				res.add(new Interval(prev.end, curr.interval.start));
				prev = curr.interval;
			}
			else { // overlap
				//update the prev with current only if end time of curr > end time of prev
				if(curr.interval.end > prev.end) {
					prev = curr.interval;
				}
			}

			// Add the next interval to the heap of the same employee that was removed from the heap.
			List<Interval> empInterval = schedule.get(curr.employeeIndex);
			if(curr.intervalIndex + 1 < empInterval.size()) {
				minHeap.add(new EmployeeInterval(empInterval.get(curr.intervalIndex+1), curr.employeeIndex, curr.intervalIndex+1));
			}
		}

		return res;
	}

	/**========================
	 * ========================
	 * === Merge Intervals ====
	 * ========================
	 * ========================
	 */

	// ======================================================================================================
	// ======================================================================================================

	/**========================
	 * ========================
	 * ===== Cyclic Sort ======
	 * ========================
	 * ========================
	 */

	/**
	 * Cyclic Sort (easy)
	 */
	public void cyclicSort(int[] nums) {
		int i = 0;
		while(i < nums.length) {
			if(nums[i] == i+1) {
				++i;
			}
			else {
				swap(nums, i, nums[i]-1);
			}
		}
	}

	/**
	 * Find the Missing Number (easy)
	 */
	public int findMissingNumber(int[] nums) {
		int i = 0;
		while(i < nums.length) {
			if(nums[i] == i || nums[i] >= nums.length) {
				++i;
			}
			else {
				swap(nums, i, nums[i]);
			}
		}

		for(int j = 0; j < nums.length; ++j) {
			if(nums[j] != j) {
				return j;
			}
		}

		return -1;
	}

	/**
	 * Find all Missing Numbers (easy)
	 */
	public List<Integer> findAllMissingNumbers(int[] nums) {
		int i = 0;
		while(i < nums.length) {
			if(nums[i] == i+1) {
				++i;
			}
			else {
				if(nums[nums[i]-1] != nums[i]) {
					swap(nums, i, nums[i]-1);
				}
				else {
					++i;
				}
			}
		}

		List<Integer> res = new ArrayList<>();
		for(i = 0; i < nums.length; ++i) {
			if(nums[i] != i+1) {
				res.add(i+1);
			}
		}

		return res;
	}

	/**
	 * Find the Duplicate Number (easy)
	 */
	public int findDuplicateNumber(int[] nums) {
		int i = 0;
		while(i < nums.length) {
			if(nums[i] == i+1) {
				++i;
			}
			else {
				if(nums[nums[i]-1] == nums[i]) {
					return nums[i];
				}
				else {
					swap(nums, nums[i]-1, i);
				}
			}
		}

		return -1;
	}

	/**
	 * Find all duplicate numbers
	 */
	public List<Integer> findAllDuplicateNumbers(int[] nums) {
		List<Integer> res = new ArrayList<>();

		int i = 0;
		while(i < nums.length) {
			if(nums[i] == i+1) {
				++i;
			}
			else {
				if(nums[nums[i]-1] == nums[i]) {
					res.add(nums[i]);
					++i;
				}
				else {
					swap(nums, nums[i]-1, i);
				}
			}
		}

		return res;
	}

	/**
	 * Find the Corrupt Pair (easy)
	 */
	public int[] findCorruptNums(int[] nums) {
		int i = 0;
		while(i < nums.length) {
			if(nums[i] == i+1) {
				++i;
			}
			else {
				if(nums[nums[i]-1] == nums[i]) { // Duplicate
					++i;
				}
				else {
					swap(nums, nums[i]-1, i);
				}
			}
		}

		for(i = 0; i < nums.length; ++i) {
			if(nums[i] != i+1) {
				return new int[] { nums[i], i + 1 };
			}
		}

		return new int[] { -1, -1 };

	}

	/**
	 * Find the Smallest Missing Positive Number (medium)
	 */
	public int findFirstMissingPositive(int[] nums) {
		// Move all the non-negative numbers (including 0) to the left

		int i = 0; // insert index
		int j = 0;
		while(j < nums.length) {
			if(nums[j] <= 0) {
				swap(nums, j, i);
				++i;
			}
			++j;
		}

		// i becomes the 1st index of non-negative number
		for(j = 0; j < nums.length-i;) {
			// 3rd condition is to avoid duplicates
			if(nums[i+j] != j+1 && nums[i+j] <= nums.length-i && nums[i+j] != nums[nums[i+j]-1]) {
				// swap
				swap(nums, i+j, nums[i+j]-1+i);
			}
			else {
				++j;
			}
		}

		for(j = 0; j < nums.length-i; ++j) {
			if(nums[i+j] != j+1) {
				return j+1;
			}
		}

		return nums.length-i+1;
	}

	/**
	 * Find the first k missing positive integers
	 */
	public List<Integer> findFirstKMissingPositiveIntegers(int[] nums, int k) {
		List<Integer> res = new ArrayList<>();
		HashSet<Integer> set = new HashSet<>();

		int i = 0;
		while(i < nums.length) {
			if(nums[i] > nums.length) {
				set.add(nums[i]);
				++i;
			}
			else if(nums[i] > 0 && nums[i] != i+i && nums[i] != nums[nums[i]-1]) {
				swap(nums, i, nums[i]-1);
			}
			else {
				++i;
			}
		}

		for(i = 0; i < nums.length; ++i) {
			if(nums[i] != i+1) {
				res.add(i+1);
			}
			if(res.size() == k) {
				return res;
			}
		}

		++i;
		while(res.size() < k) {
			if(!set.contains(i)) {
				res.add(i);
			}
			++i;
		}

		return res;
	}

	/**========================
	 * ========================
	 * ===== Cyclic Sort ======
	 * ========================
	 * ========================
	 */

	// ======================================================================================================
	// ======================================================================================================

	/**========================
	 * ========================
	 * === Pattern: Tree BFS ==
	 * ========================
	 * ========================
	 */

	/**
	 * Binary Tree Level Order Traversal
	 */
	public List<List<Integer>> levelOrderTraversal(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		while(!queue.isEmpty()) {
			List<Integer> levelRes = new ArrayList<>();
			int size = queue.size();
			for(int i = 0; i < size; ++i) {
				TreeNode node = queue.poll();
				levelRes.add(node.value);
				if(node.left != null) {
					queue.add(node.left);
				}
				if(node.right != null) {
					queue.add(node.right);
				}
			}
			res.add(levelRes);
		}

		return res;
	}

	/**
	 * Reverse level order traversal
	 */
	public List<List<Integer>> reverseLevelOrderTraversal(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		while(!queue.isEmpty()) {
			List<Integer> levelRes = new LinkedList<>();
			int size = queue.size();
			for(int i = 0; i < size; ++i) {
				TreeNode node = queue.poll();
				levelRes.add(0, node.value); // add to the beginning
				if(node.left != null) {
					queue.add(node.left);
				}
				if(node.right != null) {
					queue.add(node.right);
				}
			}
			res.add(levelRes);
		}

		return res;
	}

	public List<List<Integer>> zigZagLevelOrderTraversal(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		boolean leftToRight = false;

		while(!queue.isEmpty()) {
			leftToRight = !leftToRight;
			List<Integer> levelRes = new LinkedList<>();
			int size = queue.size();
			for(int i = 0; i < size; ++i) {
				TreeNode node = queue.poll();
				if(leftToRight) {
					levelRes.add(node.value);
				}
				else {
					levelRes.add(0, node.value);
				}

				if(node.left != null) {
					queue.add(node.left);
				}
				if(node.right != null) {
					queue.add(node.right);
				}
			}
			res.add(levelRes);
		}

		return res;
	}

	/**
	 * Level Averages in a Binary Tree
	 */
	public List<Double> findLevelAverages(TreeNode root) {
		List<Double> res = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		while(!queue.isEmpty()) {
			int sum = 0;
			int size = queue.size();
			for(int i = 0; i < size; ++i) {
				TreeNode node = queue.poll();
				sum += node.value;

				if(node.left != null) {
					queue.add(node.left);
				}
				if(node.right != null) {
					queue.add(node.right);
				}
			}
			res.add((double)sum/size);
		}
		return res;
	}

	/**
	 * Minimum Depth of a Binary Tree
	 */
	public int minimumBinaryTreeDepthRec(TreeNode root) {
		if(root == null) {
			return 0;
		}

		if(root.left == null && root.right == null) {
			return 1;
		}

		int left = root.left != null ? 1 + minimumBinaryTreeDepthRec(root.left) : Integer.MAX_VALUE;
		int right = root.right != null ? 1 + minimumBinaryTreeDepthRec(root.right) : Integer.MAX_VALUE;

		return Math.min(left, right);

	}

	// Method 2 : Using level order traversal
	public int minimumBinaryTreeDepth(TreeNode root) {
		int minDepth = 0;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		while(!queue.isEmpty()) {
			++minDepth;
			int levelSize = queue.size();
			for(int i = 0; i < levelSize; ++i) {
				TreeNode node = queue.poll();

				// Leaf node reached
				if(node.left == null && node.right == null) {
					return minDepth;
				}

				if(node.left != null) {
					queue.add(node.left);
				}
				if(node.right != null) {
					queue.add(node.right);
				}
			}
		}

		return minDepth;
	}

	/**
	 * Level order successor
	 */
	public TreeNode levelOrderSuccessor(TreeNode root, int key) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		while(!queue.isEmpty()) {
			TreeNode node = queue.poll();
			if(node.left != null) {
				queue.add(node.left);
			}
			if(node.right != null) {
				queue.add(node.right);
			}

			if(node.value == key) {
				return !queue.isEmpty() ? queue.peek() : null;
			}
		}

		return null;
	}

	/**
	 * Connect level order siblings
	 */
	static class TreeNodeWithNext {
		int val;
		TreeNodeWithNext left;
		TreeNodeWithNext right;
		TreeNodeWithNext next;

		public TreeNodeWithNext(int x) {
			val = x;
			left = right = next = null;
		}

		public void printLevelOrder() {
			TreeNodeWithNext nextLevelRoot = this;
			while (nextLevelRoot != null) {
				TreeNodeWithNext current = nextLevelRoot;
				nextLevelRoot = null;
				while (current != null) {
					System.out.print(current.val + " ");
					if (nextLevelRoot == null) {
						if (current.left != null)
							nextLevelRoot = current.left;
						else if (current.right != null)
							nextLevelRoot = current.right;
					}
					current = current.next;
				}
				System.out.println();
			}
		}
	}

	public void connectLevelOrderSiblings(TreeNodeWithNext root) {
		Queue<TreeNodeWithNext> queue = new LinkedList<>();
		queue.add(root);

		while(!queue.isEmpty()) {
			int levelSize = queue.size();
			for(int i = 0; i < levelSize; ++i) {
				TreeNodeWithNext currNode = queue.poll();
				if(i != levelSize-1) {
					currNode.next = queue.peek();
				}

				if(currNode.left != null) {
					queue.add(currNode.left);
				}
				if(currNode.right != null) {
					queue.add(currNode.right);
				}
			}
		}
	}

	/**
	 * Connect all siblings
	 */
	public void connectAllSiblings(TreeNodeWithNext root) {
		Queue<TreeNodeWithNext> queue = new LinkedList<>();
		queue.add(root);

		while(!queue.isEmpty()) {
			TreeNodeWithNext curr = queue.poll();
			if(curr.left != null) {
				queue.add(curr.left);
			}
			if(curr.right != null) {
				queue.add(curr.right);
			}

			if(!queue.isEmpty()) {
				curr.next = queue.peek();
			}
		}
	}

	/**
	 * Right View of a Binary Tree
	 * Note: Only the last node of each level goes to the result
	 */
	public List<TreeNode> rightViewTree(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		List<TreeNode> res = new ArrayList<>();

		while(!queue.isEmpty()) {
			int levelSize = queue.size();
			for(int i = 0; i < levelSize; ++i) {
				TreeNode curr = queue.poll();
				if(i == levelSize-1) {
					res.add(curr);
				}

				if(curr.left != null) {
					queue.add(curr.left);
				}
				if(curr.right != null) {
					queue.add(curr.right);
				}
			}
		}

		return res;
	}

	/**========================
	 * ========================
	 * === Pattern: Tree BFS ==
	 * ========================
	 * ========================
	 */

	// ======================================================================================================
	// ======================================================================================================

	/**========================
	 * ========================
	 * === Pattern: Tree DFS ==
	 * ========================
	 * ========================
	 */

	/**
	 * Binary Tree Path Sum (easy)
	 */
	public boolean hasPathWithSum(TreeNode root, int sum) {
		if(root == null || root.value > sum) {
			return false;
		}

		if(root.value == sum && root.left == null && root.right == null) {
			return true;
		}

		boolean left = hasPathWithSum(root.left, sum-root.value);
		boolean right = hasPathWithSum(root.right, sum-root.value);

		return left || right;
	}

	/**
	 * All Paths for a Sum (medium)
	 */
	public List<List<Integer>> findPathsWithSum(TreeNode root, int sum) {
		List<List<Integer>> res = new ArrayList<>();

		findPathsWithSum(root, sum, new ArrayList<Integer>(), res);

		return res;
	}

	private void findPathsWithSum(TreeNode root, int sum, List<Integer> path, List<List<Integer>> res) {
		if(root == null) {
			return;
		}

		path.add(root.value);

		// Leaf node
		if(sum == root.value && root.left == null && root.right == null) {
			res.add(new ArrayList<>(path));
		}
		else {
			findPathsWithSum(root.left, sum-root.value, path, res);
			findPathsWithSum(root.right, sum-root.value, path, res);
		}

		path.remove(path.size()-1);
	}

	/**
	 * Sum of Path Numbers (medium)
	 */
	public int findSumOfPathNumbers(TreeNode root) {
		List<Integer> res = new LinkedList<>();
		findSumOfPathNumbers(root, res, 1);
		return res.stream().collect(Collectors.summingInt(x -> x));
	}

	private void findSumOfPathNumbers(TreeNode root, List<Integer> res, int num) {
		if(root == null) {
			return;
		}

		// Leaf node
		if(root.left == null && root.right == null) {
			res.add(num);
			return;
		}
		else {
			findSumOfPathNumbers(root.left, res, num*10+root.value);
			findSumOfPathNumbers(root.right, res, num*10+root.value);
		}
	}

	/**
	 * Path With Given Sequence (medium)
	 */
	public boolean findPathWithSequence(TreeNode root, int[] sequence) {
		if(root == null) {
			return sequence.length == 0;
		}

		return findPathWithSequence(root, sequence, 0);
	}

	private boolean findPathWithSequence(TreeNode root, int[] sequence, int index) {

		if(sequence[index] != root.value) {
			return false;
		}

		// Reached the last index and the leaf node as well
		if(index == sequence.length-1 && sequence[index] == root.value && root.left == null && root.right == null) {
			return true;
		}

		boolean left = false;
		boolean right = false;
		if(index < sequence.length) {
			if(root.left != null) {
				left = findPathWithSequence(root.left, sequence, index+1);
			}
			if(root.right != null) {
				right = findPathWithSequence(root.right, sequence, index+1);
			}
		}

		return left||right;
	}

	/**
	 * Count Paths for a Sum (medium)
	 * The path can start from any node and end at any node.
	 */
	public int countPathsWithSum(TreeNode root, int S) {
		return countPathsWithSum(root, S, new ArrayList<Integer>());
	}

	private int countPathsWithSum(TreeNode root, int S, List<Integer> path) {
		if(root == null) {
			return 0;
		}

		path.add(root.value);

		// Find all the paths that have possible sum of S
		/**
		 * Note: We need to run the loop in reverse direction.
		 * This is because, after adding each node, we are covering all possible combinations,
		 * from last added node till the root node. If we start from root, we will always be adding 
		 * root node in every recursive call.
		 */
		int pathCount = 0, sum = 0;
		for(int i = path.size()-1; i >= 0; --i) {
			sum += path.get(i);
			if(sum == S) {
				++pathCount;
			}
		}

		// Search for pathCount including the child nodes
		pathCount += countPathsWithSum(root.left, S, path);
		pathCount += countPathsWithSum(root.right, S, path);

		// Remove the last inserted element
		path.remove(path.size()-1);

		return pathCount;
	}

	/**
	 * Tree Diameter (medium)
	 */
	private static int diameterOfTree = 0;

	public int diameterOfTree(TreeNode root) {
		diameterOfTree = 0;
		heightOfTree(root);
		return diameterOfTree;
	}

	/**
	 * This method will compute the height of the tree and 
	 * will also update the diameter of the tree which is the 
	 * highest height from all the nodes in the tree
	 */
	private int heightOfTree(TreeNode root) {
		if(root == null) {
			return 0;
		}

		int left = heightOfTree(root.left);
		int right = heightOfTree(root.right);

		int currDiameter = 1 + left + right;
		diameterOfTree = Math.max(diameterOfTree, currDiameter);

		return 1 + Math.max(left, right);
	}

	/**
	 * Path with Maximum Sum (hard)
	 */
	private static int maxSum = Integer.MIN_VALUE;
	public int findMaximumPathSum(TreeNode root) {
		maxSum = Integer.MIN_VALUE;
		findMaxMPath(root);
		return maxSum;
	}

	private static int findMaxMPath(TreeNode root) {

		if(root == null) {
			return 0;
		}

		// Ignore path with negative value. So we need to take max of left/right and 0.
		int left = Math.max(findMaxMPath(root.left), 0);
		int right = Math.max(findMaxMPath(root.right), 0);

		int currPathSum = left + right + root.value;
		maxSum = Math.max(maxSum, currPathSum);

		return Math.max(left, right) + root.value;
	}

	/**========================
	 * ========================
	 * === Pattern: Tree DFS ==
	 * ========================
	 * ========================
	 */

	// ======================================================================================================
	// ======================================================================================================

	/**========================
	 * ========================
	 * === Pattern: Subsets ===
	 * ========================
	 * ========================
	 */

	/**
	 * Subsets (easy)
	 */
	public List<List<Integer>> findSubsets(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();

		// Add an empty list
		res.add(new ArrayList<>());

		// Loop on each number
		for(int i = 0; i < nums.length; ++i) {
			// Loop of the list and add num to each of the existing lists
			int nSubsets = res.size();
			for(int j = 0; j < nSubsets; ++j) {
				List<Integer> newList = new ArrayList<>(res.get(j));
				newList.add(nums[i]);
				res.add(newList);
			}
		}

		return res;
	}


	/**
	 * Subsets With Duplicates (easy)
	 */
	public List<List<Integer>> findSubsetsWithDuplicates(int[] nums) {
		// Sort the numbers
		Arrays.sort(nums);

		List<List<Integer>> res = new ArrayList<>();

		// Add empty arraylist to start
		res.add(new ArrayList<>());
		int lastIterStartIndex = 0;

		for(int i = 0; i < nums.length; ++i) {
			// Handling duplicates
			// if current and the previous elements are same, create new subsets only from the subsets 
			// added in the previous step
			int j = 0;
			if(i != 0 && nums[i] == nums[i-1]) {
				j = lastIterStartIndex;
			}

			lastIterStartIndex = res.size();

			int n = res.size();
			for(; j < n; ++j) {
				List<Integer> list = new ArrayList<>(res.get(j));
				list.add(nums[i]);
				res.add(list);
			}
		}

		return res;
	}

	/**
	 * Permutations (Medium)
	 */
	public List<List<Integer>> findPermutations(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();

		// Add the first number to the list
		List<Integer> list = new LinkedList<>();
		list.add(nums[0]);
		res.add(list);

		for(int i = 1; i < nums.length; ++i) {
			List<List<Integer>> temp = new LinkedList<>();
			for(List<Integer> l : res) {
				for(int j = 0; j <= l.size(); ++j) {
					List<Integer> newList = new LinkedList<>(l);
					newList.add(j, nums[i]);
					temp.add(newList);
				}
			}
			res = temp;
		}

		return res;
	}

	public List<List<Integer>> findPermutationsRec(int[] nums) {
		List<List<Integer>> res = new LinkedList<>();

		findPermutationsRec(nums, 0, new LinkedList<Integer>(), res);

		return res;
	}

	private void findPermutationsRec(int[] nums, int index, List<Integer> currPermutation, List<List<Integer>> res) {
		if(index == nums.length) {
			res.add(currPermutation);
			return;
		}

		for(int i = 0; i <= currPermutation.size(); ++i) {
			List<Integer> temp = new LinkedList<>(currPermutation);
			temp.add(i, nums[index]);
			findPermutationsRec(nums, index+1, temp, res);
		}
	}

	/**
	 * String Permutations by changing case (medium)
	 */
	public List<String> findLetterCaseStringPermutations(String str) {
		List<String> res = new LinkedList<>();
		findLetterCaseStringPermutations(str, 0, "", res);

		return res;
	}

	private void findLetterCaseStringPermutations(String str, int index, String currStr, List<String> res) {
		if(index == str.length()) {
			res.add(currStr);
			return;
		}

		Character ch = str.charAt(index);
		if(Character.isLetter(ch)) {
			char chLower = Character.toLowerCase(ch);
			char chUpper = Character.toUpperCase(ch);

			findLetterCaseStringPermutations(str, index+1, currStr+chLower, res);
			findLetterCaseStringPermutations(str, index+1, currStr+chUpper, res);
		}
		else {
			currStr += str.charAt(index);
			findLetterCaseStringPermutations(str, index+1, currStr, res);
		}
	}

	/**
	 * Balanced Parentheses (hard)
	 */
	public List<String> generateValidParentheses(int num) {
		List<String> res = new LinkedList<>();
		generateValidParentheses(num, 0, 0, "", res);

		return res;
	}

	private void generateValidParentheses(int num, int left, int right, String currStr, List<String> res) {
		if(right > left || left > num) {
			return;
		}
		if(left == num && right == num) {
			res.add(currStr);
			return;
		}

		generateValidParentheses(num, left+1, right, currStr+"(", res);
		generateValidParentheses(num, left, right+1, currStr+")", res);

	}

	/**
	 * Evaluate Expression (hard)
	 */
	public List<Integer> diffWaysToEvaluateExpression(String input) {
		List<Integer> res = new LinkedList<>();
		if(!(input.contains("+") || input.contains("-") || input.contains("*"))) {
			res.add(Integer.parseInt(input));
		}
		else {
			for(int i = 0; i < input.length(); ++i) {
				char ch = input.charAt(i);
				if(!Character.isDigit(ch)) {
					// recursively compute the two halves
					List<Integer> leftRes = diffWaysToEvaluateExpression(input.substring(0, i));
					List<Integer> rightRes = diffWaysToEvaluateExpression(input.substring(i+1));

					for(int l : leftRes) {
						for(int r : rightRes) {
							if(ch == '+') {
								res.add(l + r);
							}
							else if(ch == '-') {
								res.add(l - r);
							}
							else {
								res.add(l * r);
							}
						}
					}
				}
			}
		}

		return res;
	}

	/**
	 * Structurally Unique Binary Search Trees (hard) 
	 */
	public List<TreeNode> findUniqueTrees(int n) {
		return findUniqueTrees(1, n);
	}

	private List<TreeNode> findUniqueTrees(int start, int end) {
		List<TreeNode> res = new LinkedList<>();
		if(start > end) {
			res.add(null);
			return res;
		}
		if(start == end) {
			TreeNode root = new TreeNode(start);
			res.add(root);
			return res;
		}


		for(int i = start; i <= end; ++i) {
			List<TreeNode> left = findUniqueTrees(start, i-1);
			List<TreeNode> right = findUniqueTrees(i+1, end);

			// Create trees choosing from left and right subtress
			for(TreeNode l : left) {
				for(TreeNode r : right) {
					TreeNode root = new TreeNode(i);
					root.left = l;
					root.right = r;

					res.add(root);
				}
			}
		}

		return res;
	}

	/**========================
	 * ========================
	 * === Pattern: Subsets ===
	 * ========================
	 * ========================
	 */

	// ======================================================================================================
	// ======================================================================================================

	/**========================
	 * ========================
	 * =Modified Binary Search=
	 * ========================
	 * ========================
	 */

	/**
	 * Order-agnostic Binary Search
	 */
	public int agnosticBinSearch(int[] arr, int key) {
		int left = 0, right = arr.length-1;
		boolean isAscending = arr[0] <= arr[arr.length-1];
		
		while(left <= right) {
			int mid = (left+right)/2;
			if(arr[mid] == key) {
				return mid;
			}
			else {
				if(isAscending) {
					if(arr[mid] > key) {
						right = mid - 1;
					}
					else {
						left = mid + 1;
					}
				}
				else {
					if(arr[mid] > key) {
						left = mid + 1;
					}
					else {
						right = mid - 1;
					}
				}
			}
		}
		
		return -1;
	}
	
	/**
	 * Ceiling of a Number (medium)
	 */
	public int searchCeilingOfANumber(int[] arr, int key) {
		int left = 0, right = arr.length-1;
		
		while(left <= right) {
			int mid = left + (right - left)/2;
			
			if(arr[mid] == key) {
				return mid;
			}
			else if(arr[mid] > key) {
				right = mid-1;
			}
			else {
				left = mid + 1;
			}
		}
		
		return left < arr.length ? left : -1;
	}

	/**
	 * Next Letter (medium)
	 */
	public char searchNextLetter(char[] letters, char key) {
		int left = 0, right = letters.length-1;
		while(left <= right) {
			int mid = left + (right-left)/2;
			if(letters[mid] == key) {
				return mid < letters.length-1 ? letters[mid+1] : letters[0];
			}
			else if(letters[mid] - key > 0) {
				right = mid - 1;
			}
			else {
				left = mid + 1;
			}
		}
		
		return left >= letters.length ? letters[0] : letters[left];
	}
	
	/**
	 * Number Range (medium)
	 */
	public int[] findRange(int[] arr, int key) {
		
		int L = -1, R = -1;
		int left = 0, right = arr.length-1;
		
		while(left <= right) {
			int mid = left + (right-left)/2;
			
			if(arr[mid] == key) {
				L = mid;
				R = mid;
				break;
			}
			else if(arr[mid] > key) {
				right = mid-1;
			}
			else {
				left = mid+1;
			}
		}
		
		if(left > right) {
			return new int[] {L,R};
		}
		
		// Find the left extreme in the left half
		left = 0;
		right = L;
		while(left <= right) {
			int mid = left + (right-left)/2;
			
			if(arr[mid] == key) {
				L = mid;
				right = mid-1;
			}
			else {
				left = mid + 1;
			}
		}

		// Find the right extreme in the left half
		left = R;
		right = arr.length-1;
		while(left <= right) {
			int mid = left + (right-left)/2;
			
			if(arr[mid] == key) {
				R = mid;
				left = mid+1;
			}
			else {
				right = mid - 1;
			}
		}
		
		return new int[] {L,R};
	}
	
	/**
	 * Minimum Difference Element (medium)
	 */
	public int searchMinDiffElement(int[] arr, int key) {
		
		int left = 0, right = arr.length-1, minDiff = Integer.MAX_VALUE, diffElem = -1;
		
		while(left <= right) {
			int mid = left + (right-left)/2;
			int currDiff = Math.abs(arr[mid] - key);
			
			if(currDiff > minDiff) {
				return diffElem;
			}
			diffElem = arr[mid];
			minDiff = currDiff;
			
			if(arr[mid] > key) {
				right = mid-1;
			}
			else if(arr[mid] < key) {
				left = mid + 1;
			}
			else {
				return diffElem;
			}
		}
		
		return diffElem;
	}
	
	/**
	 * Bitonic Array Maximum (easy)
	 */
	public int findMaxInBitonicArray(int[] arr) {
		int left = 0, right = arr.length-1;
		while(left <= right) {
			int mid = left + (right-left)/2;
			if(mid == arr.length-1) {
				return arr[mid];
			}
			if(mid == 0) {
				return arr[0];
			}
			
			if(arr[mid] > arr[mid - 1] && arr[mid] > arr[mid+1]) {
				return arr[mid];
			}
			else if(arr[mid] <= arr[mid+1]) { // still increasing
				left = mid+1;
			}
			else {
				right = mid-1;
			}
			
		}
		
		return arr[arr.length-1];
	}
	
	/**
	 * Search Bitonic Array (medium) 
	 */
	public int searchInBitonicArray(int[] arr, int key) {
		// find the bitonic index
		int bitonicIdx = -1;
		int left = 0, right = arr.length-1;
		while(left <= right) {
			int mid = left + (right-left)/2;
			if(mid == 0 || mid == arr.length-1 || (arr[mid] > arr[mid+1] && arr[mid] > arr[mid-1])) {
				bitonicIdx = mid;
				break;
			}
			else if(arr[mid] <= arr[mid+1]) {
				left = mid+1;
			}
			else {
				right = mid - 1;
			}
		}
		
		if(arr[bitonicIdx] == key) {
			return bitonicIdx;
		}
		
		// search in the left half (Sorted in ascending order)
		left = 0;
		right = bitonicIdx-1;
		while(left <= right) {
			int mid = left + (right-left)/2;
			if(arr[mid] == key) {
				return mid;
			}
			else if(arr[mid] > key) {
				right = mid - 1;
			}
			else {
				left = mid + 1;
			}
		}

		// search in the right half (Sorted in descending order)
		left = bitonicIdx+1;
		right = arr.length-1;
		while(left <= right) {
			int mid = left + (right-left)/2;
			if(arr[mid] == key) {
				return mid;
			}
			else if(arr[mid] < key) {
				right = mid - 1;
			}
			else {
				left = mid + 1;
			}
		}
		
		return -1;
	}
	
	/**
	 * Search in Rotated Array (medium)
	 */
	public int searchRotatedArray(int[] arr, int key) {
		
		int len = arr.length-1;
		int start = 0, end = arr.length-1;
		while(start <= end) {
			int mid = start + (end-start)/2;
			
			if(arr[mid] == key) {
				return mid;
			}
			
			if(arr[mid] > key) { // want a smaller number
				if(arr[mid] < arr[len-1]) { // rotated left
					if(key < arr[len-1]) { // key is in the skewed right subarray
						start = mid+1;
					}
					else {
						end = mid - 1;
					}
				}
				else {
					end = mid - 1;
				}
			}
			else { // want a bigger number
				if(arr[mid] < arr[0]) { // rotated right
					if(key > arr[len-1]) { // key is in the skewed left subarray
						end = mid - 1;
					}
					else {
						start = mid + 1;
					}
				}
				else {
					start = mid + 1;
				}
			}
		}
		
		return -1;
	}
	
	/**
	 * Rotation Count (medium)
	 */
	public int rotationCountOfRotatedArray(int[] arr) {
		int start = 0, end = arr.length-1;
		
		while(start <= end) {
			int mid = start + (end-start)/2;
			
			// Check and return on breaking points
			if(mid < end && arr[mid] > arr[mid+1]) {
				return mid + 1;
			}
			if(mid > start && arr[mid-1] > arr[mid]) {
				return mid;
			}
			
			if(arr[start] > arr[mid]) {
				end = mid - 1;
			}
			else if(arr[mid] > arr[end]) {
				start = mid + 1;
			}
			else {
				break;
			}
		}
		
		return 0;
	}
	
	/**========================
	 * ========================
	 * =Modified Binary Search=
	 * ========================
	 * ========================
	 */

	// ======================================================================================================
	// ======================================================================================================

	/**========================
	 * ========================
	 * =====Top K Elements=====
	 * ========================
	 * ========================
	 */
	
	/**
	 * Top 'K' Numbers (easy)
	 */
	public List<Integer> findKLargestNumbers(int[] nums, int k) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
		int i = 0;
		for(; i < k; ++i) {
			minHeap.offer(nums[i]);
		}
		
		for(; i < nums.length; ++i) {
			if(minHeap.peek() < nums[i]) {
				minHeap.poll();
				minHeap.offer(nums[i]);
			}
		}
		
		return new ArrayList<>(minHeap);
	}
	
	/**
	 * Kth Smallest Number (easy)
	 */
	public int findKthSmallestNumber(int[] nums, int k) {
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		
		int i = 0;
		for(; i < k; ++i) {
			maxHeap.offer(nums[i]);
		}
		
		for(; i < nums.length; ++i) {
			if(maxHeap.peek() > nums[i]) {
				maxHeap.poll();
				maxHeap.offer(nums[i]);
			}
		}
		
		return maxHeap.peek();
	}

	@ToString
	public static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int distFromOrigin() {
			// ignoring sqrt
			return (x * x) + (y * y);
		}
	}
	
	public List<Point> findClosestPoints(Point[] points, int k) {
		PriorityQueue<Point> maxHeap = new PriorityQueue<>((a,b) -> b.distFromOrigin() - a.distFromOrigin());
		
		int i = 0;
		for(; i < k; ++i) {
			maxHeap.offer(points[i]);
		}
		
		for(; i < points.length; ++i) {
			if(maxHeap.peek().distFromOrigin() > points[i].distFromOrigin()) {
				maxHeap.poll();
				maxHeap.offer(points[i]);
			}
		}
		
		return new ArrayList<>(maxHeap);
	}
	
	/**
	 * Connect Ropes (easy)
	 */
	public int minimumCostToConnectRopes(int[] ropeLengths) {
		
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		for(int val : ropeLengths) {
			minHeap.offer(val);
		}
		
		int cost = 0;
		
		while(minHeap.size() > 1) {
			int val1 = minHeap.poll();
			int val2 = minHeap.poll();
			cost += val1 + val2;
			minHeap.offer(val1 + val2);
		}
		
		return cost;
	}
	
	/**
	 * Top 'K' Frequent Numbers (medium)
	 */
	public List<Integer> findTopKFrequentNumbers(int[] nums, int k) {
		Map<Integer, Integer> freqMap = new HashMap<>();
		
		for(int elem : nums) {
			freqMap.put(elem, freqMap.getOrDefault(elem, 0) + 1);
		}
		
		PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((a,b) -> a.getValue() - b.getValue());
		for(Entry<Integer, Integer> elem : freqMap.entrySet()) {
			minHeap.offer(elem);
			if(minHeap.size() > k) {
				minHeap.poll();
			}
		}
		
		return minHeap.stream().map(x -> x.getKey()).collect(Collectors.toList());
	}
	
	/**
	 * Frequency Sort (medium)
	 */
	public String sortCharacterByFrequency(String str) {
		Map<Character, Integer> freqMap = new HashMap<>();
		for(char ch : str.toCharArray()) {
			freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
		}

		PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((a,b) -> b.getValue() - a.getValue());
		maxHeap.addAll(freqMap.entrySet());
		
		StringBuilder s = new StringBuilder();
		for(Entry<Character, Integer> elem : maxHeap) {
			char ch = elem.getKey();
			for(int i = 0; i < elem.getValue(); ++i) {
				s.append(ch);
			}
		}
		
		return s.toString();
	}
	
	/**
	 * 'K' Closest Numbers (medium)
	 * Given a sorted number array and two integers ‘K’ and ‘X’, 
	 * find ‘K’ closest numbers to ‘X’ in the array. 
	 * Return the numbers in the sorted order. ‘X’ is not 
	 * necessarily present in the array.
	 */
	public List<Integer> findClosestElements(int[] arr, int K, Integer X) {
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> Math.abs(X-b) - Math.abs(X-a));
		
		for(int elem : arr) {
			maxHeap.add(elem);
			if(maxHeap.size() > K) {
				maxHeap.poll();
			}
		}
		
		return new ArrayList<>(maxHeap);
	}
	
	public List<Integer> findClosestElements2(int[] arr, int K, Integer X) {
		int idx = binarySearch(arr, X);
		
		// Copy K elements from left of K and K elements from right of K
		int left = Math.max(0, idx-K);
		int right = Math.min(arr.length-1, idx+K);

		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> Math.abs(X-b) - Math.abs(X-a));
		for(int i = left; i <= right; ++i) {
			maxHeap.offer(arr[i]);
			if(maxHeap.size() > K) {
				maxHeap.poll();
			}
		}
		
		List<Integer> res = new ArrayList<>(maxHeap);
		Collections.sort(res);;
		
		return res;
	}

	private int binarySearch(int[] arr, Integer X) {
		int start = 0, end = arr.length-1;
		int idx = -1, minDiff = Integer.MAX_VALUE;
		while(start <= end) {
			int mid = start + (end-start)/2;
			if(arr[mid] == X) {
				return mid;
			}

			int currDiff = Math.abs(arr[mid] - X);
			if(minDiff < currDiff) {
				return idx;
			}
			
			// Update minDiff
			minDiff = currDiff;
			idx = mid;
			
			if(X < arr[mid]) {
				end = mid - 1;
			}
			else {
				start = mid + 1;
			}
		}

		return idx;
	}
	
	/**
	 * Maximum Distinct Elements (medium)
	 */
	public int findMaximumDistinctElements(int[] nums, int k) {
		Map<Integer, Integer> freqMap = new HashMap<>();
		for(int elem : nums) {
			freqMap.put(elem, freqMap.getOrDefault(elem, 0) + 1);
		}
		
		PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((a,b) -> a.getValue() - b.getValue());
		int distincElements = 0;
		for(Entry<Integer, Integer> elem : freqMap.entrySet()) {
			if(elem.getValue() > 1) {
				minHeap.add(elem);
			}
			else {
				++distincElements;
			}
		}

		while(!minHeap.isEmpty() && k > 0) {
			Entry<Integer, Integer> elem = minHeap.poll();
			k -= elem.getValue()-1;
			
			if(k > 0) {
				++distincElements;
			}
		}
		
		if(k > 0) {
			distincElements -= k;
		}

		return distincElements;
	}
	
	/**
	 * Sum of Elements (medium)
	 * Given an array, find the sum of all numbers between the 
	 * K1’th and K2’th smallest elements of that array.
	 */
	public int findSumOfElements(int[] nums, int k1, int k2) {
		if(nums.length < k2) {
			return 0;
		}
		
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		for(int elem : nums) {
			minHeap.add(elem);
		}
		
		for(int i = 0; i < k1; ++i) {
			minHeap.poll();
		}
		
		int sum = 0;
		for(int i = k1+1; i < k2; ++i) {
			sum += minHeap.poll();
		}
		
		return sum;
	}
	
	/**
	 * Rearrange String (hard)
	 * Given a string, find if its letters can be rearranged in such a way 
	 * that no two same characters come next to each other.
	 */
	public String rearrangeString(String str) {
		Map<Character, Integer> freqMap = new HashMap<>();
		for(char ch : str.toCharArray()) {
			freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
		}
		
		PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((a,b) -> b.getValue() - a.getValue());
		maxHeap.addAll(freqMap.entrySet());
		
		Map.Entry<Character, Integer> prev = null;
		StringBuilder s = new StringBuilder();
		while(!maxHeap.isEmpty()) {
			Entry<Character, Integer> curr = maxHeap.poll();
			s.append(curr.getKey());
			curr.setValue(curr.getValue()-1);

			if(prev != null && prev.getValue() > 0) {
				maxHeap.offer(prev);
			}

			prev = curr;
		}
		
		return s.toString().length() == str.length() ? s.toString() : "";
	}
	
	/**
	 * Rearrange String K Distance Apart (hard)
	 */
	public String reorganizeString(String str, int k) {
		Map<Character, Integer> freqMap = new HashMap<>();
		for(char ch : str.toCharArray()) {
			freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
		}
		
		PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((a,b) -> b.getValue() - a.getValue());
		maxHeap.addAll(freqMap.entrySet());
		
		Queue<Map.Entry<Character, Integer>> prev = new LinkedList<>();
	
		StringBuilder s = new StringBuilder();
		while(!maxHeap.isEmpty()) {
			Entry<Character, Integer> curr = maxHeap.poll();
			s.append(curr.getKey());
			curr.setValue(curr.getValue()-1);
			
			if(curr.getValue() > 0) {
				prev.offer(curr);
			}
			
			if(prev.size() == k) {
				maxHeap.offer(prev.poll());
			}
		}
		
		while(!prev.isEmpty() && prev.peek().getValue() == 1) {
			s.append(prev.poll().getKey());
		}
		
		return s.toString().length() == str.length() ? s.toString() : "";
	}
	
	/**
	 * Scheduling Tasks (hard)
	 */
	public int scheduleTasks(char[] tasks, int k) {
		Map<Character, Integer> freqMap = new HashMap<>();
		for(char ch : tasks) {
			freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
		}
		
		PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((a,b) -> b.getValue() - a.getValue());
		maxHeap.addAll(freqMap.entrySet());
		
		int time = 0;
		
		while(!maxHeap.isEmpty()) {
			List<Map.Entry<Character, Integer>> list = new LinkedList<>();
			// Total task to be done in each iteration is k+1
			int n = k+1;
			for(; n > 0 && !maxHeap.isEmpty(); n--) {
				Entry<Character, Integer> curr = maxHeap.poll();
				++time;
				if(curr.getValue() > 1) {
					curr.setValue(curr.getValue() - 1);
					list.add(curr);
				}
			}
			
			maxHeap.addAll(list);
			// Idle count becomes n as n tasks could not be completed yet
			if(!maxHeap.isEmpty()) {
				time += n;
			}
		}
		
		return time;
	}
	
	/**========================
	 * ========================
	 * =====Top K Elements=====
	 * ========================
	 * ========================
	 */

	// ======================================================================================================
	// ======================================================================================================
}
