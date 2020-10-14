package educative.revision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
}
