package educative.slidingwindow;

import java.util.Arrays;

import helpers.Helpers;

public class SlidingWindowMain {
	public static void main(String[] args) {
		SlidingWindow slidingWindow = new SlidingWindow();
		
		double[] aveArr = slidingWindow.findAveragesOfSubarrayOfSizeK(5, new int[] { 1, 3, 2, 6, -1, 4, 1, 8, 2 });
		Helpers.printArr(Arrays.stream(aveArr).boxed().toArray(Double[]::new));
		
		System.out.println(slidingWindow.findMaxSumSubArray(3, new int[] {2, 1, 5, 1, 3, 2}));
		System.out.println(slidingWindow.findMaxSumSubArray(2, new int[] {2, 3, 4, 1, 5}));
		
		System.out.println(slidingWindow.findMinSubArray(7, new int[] {2, 1, 5, 2, 3, 2}));
		System.out.println(slidingWindow.findMinSubArray(7, new int[] {2, 1, 5, 2, 8}));
		System.out.println(slidingWindow.findMinSubArray(8, new int[] {3, 4, 1, 1, 6}));
		
		System.out.println(slidingWindow.LongestSubstringKDistinct("cbbebi", 3));
		System.out.println(slidingWindow.LongestSubstringKDistinct("araaci", 2));
		System.out.println(slidingWindow.LongestSubstringKDistinct("araaci", 1));
		
		System.out.println(slidingWindow.findMaxFruits(new char[] {'A', 'B', 'C', 'A', 'C'}));
		System.out.println(slidingWindow.findMaxFruits(new char[] {'A', 'B', 'C', 'B', 'B', 'C', 'A'}));

		System.out.println(slidingWindow.getNoRepeatSubstringLength("abcbbpqrstuvabcde"));
		System.out.println(slidingWindow.getNoRepeatSubstringLength("abcbbpqrstuvtabcda"));

		System.out.println(slidingWindow.getNoRepeatSubstringLengthOpt("aabccbb"));
		
		System.out.println(slidingWindow.getNoRepeatSubstringLengthOpt2("abcbbpqrstuvabcde"));
		System.out.println(slidingWindow.getNoRepeatSubstringLengthOpt2("abcbbpqrstuvtabcda"));
		System.out.println(slidingWindow.getNoRepeatSubstringLengthOpt2("aabccbb"));
		System.out.println(slidingWindow.getNoRepeatSubstringLengthOpt2("abcapqrst"));
		
		System.out.println("Longest Substr after replacement");
		System.out.println(slidingWindow.longestSubstrAfterReplacement("aaabcapqr", 2));
		System.out.println(slidingWindow.longestSubstrAfterReplacement("aabccbb", 2));
		
		System.out.println(slidingWindow.findLengthReplacingOnes(new int[] {0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1}, 2));
		System.out.println(slidingWindow.findLengthReplacingOnes(new int[] {0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1}, 3));

		System.out.println(slidingWindow.checkInclusion2("oidbcaf", "abc"));
		System.out.println(slidingWindow.checkInclusion2("odicf", "dc"));
		System.out.println(slidingWindow.checkInclusion2("bcdxabcdy", "bcdyabcdx"));
		System.out.println(slidingWindow.checkInclusion2("aaacb", "abc"));
		System.out.println(slidingWindow.checkInclusion2("kckifykx", "ky"));
		System.out.println(slidingWindow.checkInclusion2("ainwkckifykxlribaypk", "ky"));
		
		System.out.println(slidingWindow.checkInclusion2("pqrsba", "ab"));
		
		System.out.println(slidingWindow.findStringAnagrams("abbcabc", "abc"));
		System.out.println(slidingWindow.findStringAnagrams("ppqp", "pq"));
		
		System.out.println(slidingWindow.findSubstring("aabdec", "abc"));
		System.out.println(slidingWindow.findSubstring("abdcab", "abc"));
		System.out.println(slidingWindow.findSubstring("adcad", "abc"));
		System.out.println(slidingWindow.findSubstring("ADOBECODEBANC", "ABC"));
		System.out.println(slidingWindow.findSubstring("AB", "A"));
		System.out.println(slidingWindow.findSubstring("acbbaca", "aba"));
		System.out.println(slidingWindow.findSubstring("aaflslflsldkalskaaa", "aaa"));
		System.out.println(slidingWindow.findSubstring("abcabdebac", "cda"));
		System.out.println(slidingWindow.findSubstring("of_characters_and_as", "aas"));
	}
}
