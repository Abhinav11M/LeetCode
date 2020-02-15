package educative.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SlidingWindow {
	/**
	 * Given an array, find the average of all contiguous subarrays of size ‘K’ in it.
	 * @param k
	 * @param arr
	 * @return
	 */
	public double[] findAveragesOfSubarrayOfSizeK(int k, int[] arr) {
		int startIdx = 0;
		int endIdx = 0;
		int sum = 0;
		double[] res = new double[arr.length-k+1];
		
		for(endIdx = 0; endIdx < arr.length; ++endIdx) {
			sum += arr[endIdx];
			
			if(endIdx >= k-1) {
				res[startIdx] = sum/(double)k;
				// Subtract the front
				sum -= arr[startIdx++];
			}
		}
		
		return res;
	}
	
	/**
	 * Given an array of positive numbers and a positive number ‘k’, 
	 * find the maximum sum of any contiguous subarray of size ‘k’.
	 */
	public int findMaxSumSubArray(int k, int[] arr) {
		int startIdx = 0;
		int endIdx = 0;
		int max = Integer.MIN_VALUE;
		int sum = 0;
		
		for(endIdx = 0; endIdx < arr.length; ++endIdx) {
			sum += arr[endIdx];
			
			if(endIdx >= k-1) {
				if(sum > max) {
					max = sum;
				}
				sum -= arr[startIdx++];
			}
		}
		
		return max;
	}

	/**
	 * Smallest Subarray with a given sum
	 */
	public int findMinSubArray(int s, int[] arr) {
		int startIdx = 0;
		int endIdx = 0;
		int sum = 0;
		int subsetSize = Integer.MAX_VALUE;
		
		for(endIdx = 0; endIdx < arr.length; ++endIdx) {
			sum += arr[endIdx];
			
			if(sum >= s) {
				while(sum >= s) {
					sum -= arr[startIdx++];
				}
				
				int newSubsetLen = endIdx - (startIdx-1) + 1;
				
				if(newSubsetLen < subsetSize) {
					subsetSize = newSubsetLen;
				}
			}
			
			
			
		}
		
		return subsetSize;
	}
	
	/**
	 * Given a string, find the length of the longest substring in it with no more than K distinct characters.
	 */
	public int LongestSubstringKDistinct(String str, int k) {
		int startIdx = 0;
		int endIdx = 0;
		int maxWindow = 0;
		
		Map<Character, Integer> map = new HashMap<>();
		
		for(;endIdx < str.length(); ++endIdx) {
			map.put(str.charAt(endIdx), map.getOrDefault(str.charAt(endIdx), 0) + 1);

			// remove one of the characters from the map that exhausts first
			while(map.size() > k) {
				char charToDecrement = str.charAt(startIdx);
				map.put(charToDecrement, map.get(charToDecrement)-1);
				if(map.get(charToDecrement) == 0) { // remove this character as this is exhausted
					map.remove(charToDecrement);
				}
				startIdx++;
			}
			
			maxWindow = Math.max(maxWindow ,(endIdx - startIdx + 1));
		}
		
		return maxWindow;
	}
	
	/**
	 * Fruits into Baskets
	 */
	public int findMaxFruits(char[] arr) {
		if(arr.length == 0 || arr.length == 1) {
			return arr.length;
		}

		char char1 = arr[0];
		char char2 = ' ';
		int max = Integer.MIN_VALUE;
		int startIdx = 0;
		int endIdx = 1;
		
		for(; endIdx < arr.length; ++endIdx) {
			if(arr[endIdx] == char1 ) {
				continue;
			}
			else if(arr[endIdx] == char2) {
				continue;
			}
			else {
				// If char 2 is empty, then set it and continue
				if(char2 == ' ') {
					char2 = arr[endIdx];
				}
				else {
					// we have crossed 2 alphabets here
					int tempMax = endIdx - startIdx;
					max = tempMax > max ? tempMax : max;
					
					int i = endIdx-1;
					// find the alphabet at startIdx from endIdx
					if(arr[startIdx] == arr[endIdx-1]) {
						// move back till you find something other that arr[startIdx]
						for(; i >= 0 && arr[i] == arr[startIdx]; --i);
					}
					else {
						// Find the first occurence of arr[startIdx] from endIdx going backwards
						for(; arr[i] != arr[startIdx]; --i);
					}
					startIdx = i+1;
					char1 = arr[startIdx];
					char2 = arr[endIdx];
				}
			}
		}
		
		
		return (endIdx-startIdx) > max ? (endIdx-startIdx) : max;
	}
	
	/**
	 * No-repeat Substring
	 * More optimized method below
	 */
	public int getNoRepeatSubstringLength(String str) {
		int startIdx = 0;
		int endIdx = 0;
		int max = 0;
		
		Set<Character> set = new HashSet<>();
		
		for(; endIdx < str.length(); ++endIdx) {
			char currentChar = str.charAt(endIdx);
			if(set.contains(currentChar)) {
				// Find the position of current char b/w startIdx and endIdx.
				// Reset startIdx to the next position of the find position.
				int i = endIdx-1;
				for(; i >= startIdx && str.charAt(i) != currentChar; --i);
				// Reset startIdx
				startIdx = i+1;
			}
			else {
				set.add(currentChar);
			}
			
			max = Math.max(max, endIdx-startIdx+1);
		}
		
		return max;
	}
	
	public int getNoRepeatSubstringLengthOpt(String str) {
		int[] visited = new int[256];
		
		for(int i = 0; i < visited.length; ++i) {
			visited[i] = -1;
		}
		
		visited[str.charAt(0)] = 0;

		int maxLength = 1;
		int currentLength = 1;
		
		for(int i = 1; i < str.length(); ++i) {
			char currentChar = str.charAt(i);
			if(visited[currentChar] == -1 || (i - visited[currentChar]) > currentLength) {
				++currentLength;
			}
			else {
				if(maxLength < currentLength) {
					maxLength = currentLength;
				}
				currentLength = i - visited[currentChar];
			}
			visited[currentChar] = i;
		}
		
		return currentLength > maxLength ? currentLength : maxLength;
	}
	
	// This is as given in educative
	public int getNoRepeatSubstringLengthOpt2(String str) {
		Map<Character, Integer> map = new HashMap<>();
		int startIdx = 0;
		int endIdx = 0;
		int maxLength = 0;
		
		for(;endIdx < str.length(); ++endIdx) {
			char currChar = str.charAt(endIdx);
			if(map.containsKey(currChar)) {
				startIdx = Math.max(startIdx, map.get(currChar)+1); // +1 because we don't want that 
																	// character as it is already there at index endIdx
			}
			maxLength = Math.max(maxLength, endIdx-startIdx+1);
			map.put(currChar, endIdx);
		}
		
		return maxLength;
	}
	
	/**
	 * Longest Substring with Same Letters after Replacement
	 * (similar to longest substring with k distict characters)
	 */
	public int longestSubstrAfterReplacement(String str, int k) {
		int startIdx = 0;
		int endIdx = 0;
		int maxLength = 0;
		int countRepeatChar = 0;
		
		Map<Character, Integer> countMap = new HashMap<>();
		
		for(; endIdx < str.length(); ++endIdx) {
			char currChar = str.charAt(endIdx);
			countMap.put(currChar, countMap.getOrDefault(currChar, 0) + 1);
			
			countRepeatChar = Math.max(countRepeatChar, countMap.get(currChar));
			
			if(endIdx - startIdx + 1 - countRepeatChar > k) {
				char charAtBegin = str.charAt(startIdx);
				countMap.put(charAtBegin, countMap.get(charAtBegin)-1);
				startIdx++;
			}

			maxLength = Math.max(maxLength, endIdx-startIdx+1);
		}
		
		return maxLength;
	}
	
	/*
	 * Longest Subarray with Ones after Replacement (hard)
	 */
	public int findLengthReplacingOnes(int[] arr, int k) {
		
		int startIdx = 0;
		int endIdx = 0;
		int maxCount = 0;
		int countZeros = 0;
		
		for(; endIdx < arr.length; ++endIdx) {
			int currInt = arr[endIdx];
			
			if(currInt == 0) {
				++countZeros;
			}
			
			if(countZeros > k) {
			// Move the startIdx till it crosses atleast one zero.
				for(;; ++startIdx) {
					if(arr[startIdx] == 0) {
						break;
					}
				}
				++startIdx;
				countZeros--;
			}
			
			maxCount = Math.max(maxCount, endIdx-startIdx+1);
		}
		
		return maxCount;
	}
	
	/**
	 * Problem challenge 1
	 * Permutation in a String
	 * TODO: This has a few failing test cases. See next 2 algos for correct solution.
	 */
	public boolean checkInclusion(String str, String pattern) {
		Map<Character, Integer> patternMap = new HashMap<>();
		
		for(char ch : pattern.toCharArray()) {
			patternMap.put(ch, patternMap.getOrDefault(ch, 0)+1);
		}
		
		Map<Character, Integer> patternMapTemp = new HashMap<>();
		
		int startIdx = 0;
		int endIdx = 0;
		for(; endIdx < str.length(); ++endIdx) {
			char currChar = str.charAt(endIdx);

			if(!patternMap.containsKey(currChar)) {
				startIdx = endIdx+1;
				patternMapTemp.clear();
			}
			else {
				patternMapTemp.put(currChar, patternMapTemp.getOrDefault(currChar, 0)+1);
				if(patternMapTemp.get(currChar) > patternMap.get(currChar)) {
					// move the startIdx towards right until it removes 1 currChar from the window
					while(str.charAt(startIdx) != currChar) {
						startIdx++;
					}
					startIdx++;
					patternMapTemp.put(currChar, patternMapTemp.get(currChar)-1);
				}
			}
			
			if(endIdx - startIdx + 1 == pattern.length()) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean checkInclusion1(String str, String pattern) {
		
		Map<Character, Integer> patternMap = new HashMap<>();
		
		for(char ch : pattern.toCharArray()) {
			patternMap.put(ch, patternMap.getOrDefault(ch, 0)+1);
		}
		
		int startIdx = 0;
		int endIdx = 0;
		int matched = 0; // number of distinct chars in the pattern to be matched.
		
		for(;endIdx < str.length(); ++endIdx) {
			char currChar = str.charAt(endIdx);
			if(patternMap.containsKey(currChar)) {
				patternMap.put(currChar, patternMap.get(currChar) - 1);
				
				if(patternMap.get(currChar) == 0) {
					++matched; // Completed the matching of one of the chars in the pattern.
				}
			}
			
			if(matched == patternMap.size()) { // all the distinct chars were matched.
				return true;
			}
			
			if(endIdx >= pattern.length()-1) { // Shrink the window by 1 char each time
				char leftChar = str.charAt(startIdx++);
				if(patternMap.containsKey(leftChar)) {
					if(patternMap.get(leftChar) == 0) { // This character was matched
						--matched;
					}
					patternMap.put(leftChar, patternMap.get(leftChar) + 1); // Incrementing the frequency of the char by 1
				}
			}
		}
		
		return false;
	}
	
	/* Solution from leetcode */
	public boolean checkInclusion2(String str, String pattern) {   
        int[] freq = new int[26];
        
        for(int i = 0; i < pattern.length(); ++i) {
        	freq[pattern.charAt(i) - 'a']++;
        }
        
        int strLen = str.length();
        int patternLen = pattern.length();
        
        int left = 0, right = 0;
        
        while(right < strLen) {
        	int rightIndex = str.charAt(right) - 'a';
        	if(freq[rightIndex] > 0) {
        		// decrement the counter in the array and move the window right
        		freq[rightIndex]--;
        		right++;
        	}
        	else {
        		// Increment the freq of the left char by 1 as this will be reduced by right pointer anyhow
        		freq[str.charAt(left)-'a']++;
        		left++;
        	}
        	
        	if(right-left == patternLen) {
        		return true;
        	}
        }
        
        return false;
    }
	
	/**
	 * String Anagrams
	 */
	public List<Integer> findStringAnagrams(String str, String pattern) {
		List<Integer> list = new ArrayList<>();
		
		int[] freq = new int[26];
		
		for(int i = 0; i < pattern.length(); ++i ) {
			freq[pattern.charAt(i) - 'a']++;
		}
		
		int left = 0, right = 0, patternLen = pattern.length(), strLen = str.length();
		
		while(right < strLen) {
			int rightIndex = str.charAt(right) - 'a';
			if(freq[rightIndex] > 0) {
				freq[rightIndex]--;
				right++;
			}
			else {
				int leftIndex = str.charAt(left)-'a';
				freq[leftIndex]++;
				left++;
			}
			
			if(right-left == patternLen) {
				list.add(left);
				freq[str.charAt(left) - 'a']++;
				left++;
			}
		}
		
		return list;
	}
	
	/**
	 * Smallest Window containing Substring 
	 */
	
	public String findSubstring(String str, String pattern) {
		Map<Character, Integer> freqMap = new HashMap<>();
		
		for(char ch : pattern.toCharArray()) {
			freqMap.put(ch, freqMap.getOrDefault(ch, 0)+1);
		}
		
		int left = 0, right = 0, minLength = Integer.MAX_VALUE;
		int matched = 0;
		String ret = "";
		
		for(;right < str.length() && !freqMap.containsKey(str.charAt(right)); ++right);
		left = right;
		
		for(; right < str.length(); ++right) {
			char rightChar = str.charAt(right);
			
			if(freqMap.containsKey(rightChar)) {
				freqMap.put(rightChar, freqMap.get(rightChar)-1);
				
				if(freqMap.get(rightChar) == 0) { // One character matched
					matched++;
				}
			}
			
			if(freqMap.size() == matched) {
				if(right-left+1 < minLength) {
					minLength = right-left+1;
					ret = str.substring(left, right+1);
				}
				
				// Keeping the right end fixed, move the left end.
				while(matched == freqMap.size()) {
					// Left will always be on a pattern char. So we can simply remove that char from map and 
					// move the left pointer to the next pattern character.
					freqMap.put(str.charAt(left), freqMap.get(str.charAt(left))+1);
					if(freqMap.get(str.charAt(left)) == 1) {
						--matched;
					}
					
					// Move to the next char  exists in the pattern
					left++;
					for(;left < str.length() && !freqMap.containsKey(str.charAt(left)); ++left);
					
					if(freqMap.size() == matched) {
						if(right-left+1 < minLength) {
							minLength = right-left+1;
							ret = str.substring(left, right+1);
						}
					}
				}
			}
		}
		
		return ret;
	}
}
