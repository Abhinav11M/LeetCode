package programcreek.algolist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import helpers.Helpers;

public class ProgCreekAlgosMain {

	public static void main(String[] args) {
		ProgCreekAlgos algos = new ProgCreekAlgos();
		Helpers.printArr(algos.twoSum(new int[] {2,7,11,15}, 9));
		Helpers.printArr(algos.twoSum(new int[] {9,6,2,8}, 14));

		Helpers.printArr(algos.twoSum2(new int[] {1,2,3,5,7}, 8));
		Helpers.printArr(algos.twoSum2(new int[] {2,5,8,12,15}, 27));
		
		TwoSum twoSum = new TwoSum();
		twoSum.add(1);
		twoSum.add(4);
		twoSum.add(1);
		twoSum.add(5);
		System.out.println(twoSum.find(2));
		System.out.println(twoSum.find(9));
		System.out.println(twoSum.find(3));
		
		System.out.println(algos.threeSum(new int[] {-1,0,1,2,-1,-4}));
		System.out.println(algos.minSubArrayLen(7, new int[] {2,3,1,2,4,3}));
		System.out.println(algos.minSubArrayLen(7, new int[] {2,3,3,1,6,2,4}));

		System.out.println(algos.removeDuplicates(new int[] {1,2,2,3,4,4,5,6,6}));
		System.out.println(algos.removeDuplicates(new int[] {1,1,1,1,1,1,2}));

		System.out.println(algos.removeDuplicates2(new int[] {1,1,1,1,1,1,2}));
		System.out.println(algos.removeDuplicates2(new int[] {1,2,2,2,4,4}));

		System.out.println(algos.removeElement(new int[] {1,2,2,4,4,2,5,6,2,1}, 2));

		int[] arr = new int[] {0,1,0,0,2,0,0,3,0,0,0,5,0};
		algos.moveZeroes(arr);
		Helpers.printArr(arr);
		
		System.out.println(algos.maxArea(new int[] {1,2,1,3,2,1,1,1}));

		System.out.println(algos.trappingWater(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));

		System.out.println(algos.summaryRanges(new int[] {0,1,2,4,5,7}));
		
		System.out.println(algos.editDistance("cba", "cd"));
		System.out.println(algos.editDistanceDP("cba", "cd"));

		int[] arr1 = new int[] {2,0};
		int[] arr2 = new int[] {1};

		algos.mergeSortedArrays(arr1, 1, arr2, 1);
		Helpers.printArr(arr1);
		
		System.out.println(algos.medianOfSortedArrays(new int[] {1,12,15,26,38}, new int[] {2,13,17,30,45}));
		
		// Min in rotated sorted array
		System.out.println(algos.findMin(new int[] {3,4,5,1,2}));
		System.out.println(algos.findMin(new int[] {4,5,6,7,0,1,2}));
		System.out.println(algos.findMin(new int[] {2,1}));
		System.out.println(algos.findMin(new int[] {2,3,4,5,1}));
		System.out.println(algos.findMin(new int[] {5,1,2,3,4}));
		System.out.println(algos.findMin(new int[] {2,2,2,0,1}));
		System.out.println(algos.findMinWithDup(new int[] {3,3,1,3}));
		System.out.println(algos.findMinWithDup(new int[] {3,3,3,3}));

		Helpers.printArr(algos.searchRange(new int[] {5,7,7,8,8,10}, 8));

		System.out.println(algos.searchInRotatedSortedArray(new int[] {4,5,6,7,0,1,2}, 0));
		System.out.println(algos.searchInRotatedSortedArrayWithDup(new int[] {1,3,1,1,1}, 3));
		
		System.out.println(algos.evalRPN(new String[] {"2", "1", "+", "3", "*"}));
		System.out.println(algos.evalRPN(new String[] {"4", "13", "5", "/", "+"}));
		System.out.println(algos.evalRPN(new String[] {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
		
		System.out.println(algos.longestValidParentheses("()"));
		System.out.println(algos.longestValidParentheses(")()())"));
		
//		System.out.println(algos.largestRectangleArea(new int[] {2,1,5,6,2,3}));
//		System.out.println(algos.largestRectangleArea2(new int[] {4,2,0,3,2,5}));
//		System.out.println(algos.largestRectangleArea2(new int[] {3,6,5,7,4,8,1,0}));
		System.out.println(algos.largestRectangleArea2(new int[] {1,2,3,4,5,3,3,2}));

		System.out.println("Broken Calc");
//		System.out.println(algos.brokenCalc(2, 3));
//		System.out.println(algos.brokenCalc(5, 8));
//		System.out.println(algos.brokenCalc(3, 10));
//		System.out.println(algos.brokenCalc(1, 1000000000));
		
//		System.out.println(algos.palindromePairs(new String[] {"abcd","dcba","lls","s","sssll"}));
		System.out.println(algos.lengthOfLongestSubstring("abcabcbb"));
		System.out.println(algos.lengthOfLongestSubstring("bbbbb"));
		System.out.println(algos.lengthOfLongestSubstring("pwwkew"));
		System.out.println(algos.lengthOfLongestSubstring("tmmzuxt"));
		System.out.println(algos.lengthOfLongestSubstring("bbtablud"));

		System.out.println(algos.majorityElement2(new int[] {3,2,3}));
		System.out.println(algos.majorityElement2(new int[] {1,1,1,3,3,2,2,2}));
		System.out.println(algos.majorityElement2(new int[] {2,2}));
		
		System.out.println(algos.increasingTriplet(new int[] {1,1,1}));

		// Rotate array
		arr = new int[] {1,2,3,4,5,6,7}; 
		algos.rotate(arr, 3);
		Helpers.printArr(arr);
		arr = new int[] {1,2,3,4,5}; 
		algos.rotate(arr, 3);
		Helpers.printArr(arr);

		System.out.println(algos.reverseWords("the sky is blue"));
		System.out.println(algos.reverseWords(" "));
		
		String s = "the sky is blue";
		char[] cArr = s.toCharArray();
		algos.reverseWords(cArr);
		for(char ch : cArr) {
			System.out.print(ch);
		}
		System.out.println();
		
		// ========== Group Anagrams =========
		System.out.println(algos.groupAnagrams(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"}));
		System.out.println(algos.groupAnagramsOpt(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"}));

		/*
		System.out.println(algos.isMatchDP("aa","a")); // false
		System.out.println(algos.isMatchDP("aa","*a")); // true
		System.out.println(algos.isMatchDP("cb","?a")); // false
		System.out.println(algos.isMatchDP("adceb","a*c?b")); // true
		System.out.println(algos.isMatchDP("aa","*")); // true
		System.out.println(algos.isMatchDP("adceb","*a*b")); // true
		System.out.println(algos.isMatchDP("zacabz","*a?b*")); // false
		System.out.println(algos.isMatchDP("","")); // true*/
		
		System.out.println(algos.isMatch("aa","a")); // false
		System.out.println(algos.isMatch("aa","*a")); // true
		System.out.println(algos.isMatch("cb","?a")); // false
		System.out.println(algos.isMatch("adceb","a*c?b")); // true
		System.out.println(algos.isMatch("aa","*")); // true
		System.out.println(algos.isMatch("adceb","*a*b")); // true
		System.out.println(algos.isMatch("zacabz","*a?b*")); // false
		System.out.println(algos.isMatch("","")); // true

		System.out.println();
		System.out.println(algos.isReachable(Arrays.asList(new Integer[] {1,2,3,4}), 21));
		
		System.out.println(algos.flipGameMtd2("++++"));
		System.out.println(algos.flipGameMtd2("+++-++"));
		System.out.println(algos.generatePossibleNextMoves("++++"));
		System.out.println(algos.generatePossibleNextMoves("+++-++"));
		System.out.println(algos.canWin("++++"));
		
		System.out.println(algos.wordPattern2("abba", "dog cat cat dog"));
		System.out.println(algos.wordPattern2("abba", "dog cat cat fish"));
		System.out.println(algos.wordPattern2("abba", "dog dog dog dog"));

		System.out.println(algos.wordPatternMatch("abab", "redblueredblue"));
		System.out.println(algos.wordPatternMatch("aaaa", "asdasdasdasd"));
//		System.out.println(algos.wordPatternMatch("aabb", "xyzabcxzyabc")); // Failing
		
		System.out.println(algos.findKthLargest(new int[] {3,2,1,5,6,4}, 2));
		System.out.println(algos.findKthLargest(new int[] {3,2,3,1,2,4,5,5,6}, 4));

		System.out.println(algos.topKFrequent(new int[] {1,1,1,2,2,3}, 2));
		
		System.out.println(algos.minMeetingRooms(new ProgCreekAlgos.Interval[] {
				new ProgCreekAlgos.Interval(0, 30),
				new ProgCreekAlgos.Interval(5, 10),
				new ProgCreekAlgos.Interval(15, 20)
		}));
		
		System.out.println(algos.minMeetingRooms(new ProgCreekAlgos.Interval[] {
				new ProgCreekAlgos.Interval(7, 10),
				new ProgCreekAlgos.Interval(2, 4)
		}));

		System.out.println(algos.minMeetingRooms(new ProgCreekAlgos.Interval[] {
				new ProgCreekAlgos.Interval(0, 14),
				new ProgCreekAlgos.Interval(6, 25),
				new ProgCreekAlgos.Interval(12, 19),
				new ProgCreekAlgos.Interval(13, 19),
				new ProgCreekAlgos.Interval(5, 9)
		}));
		
	}

}
