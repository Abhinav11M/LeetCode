package educative.revision;

import educative.fastandslowpointers.ListNode;
import helpers.Helpers;

public class EducativeRevisionMain {

	public static void main(String[] args) {
		EducativeRevision algos = new EducativeRevision();

		// ===== Two pointers =====

		// Maximum Sum Subarray of Size K (easy)
		System.out.println(
				"Maximum sum of a subarray of size K: " + algos.findMaxSumSubArray(3, new int[] { 2, 1, 5, 1, 3, 2 }));
		System.out.println(
				"Maximum sum of a subarray of size K: " + algos.findMaxSumSubArray(2, new int[] { 2, 3, 4, 1, 5 }));

		// Smallest Subarray with a given sum (easy)
		System.out.println(algos.findMinSubArray(7, new int[] { 2, 1, 5, 2, 3, 2 }));
		System.out.println(algos.findMinSubArray(7, new int[] { 2, 1, 5, 2, 8 }));
		System.out.println(algos.findMinSubArray(8, new int[] { 3, 4, 1, 1, 6 }));

		// Longest Substring with K Distinct Characters (medium)
		System.out.println("Length of the longest substring: " + algos.findLength("araaci", 2));
		System.out.println("Length of the longest substring: " + algos.findLength("araaci", 1));
		System.out.println("Length of the longest substring: " + algos.findLength("cbbebi", 3));

		// Fruits into Baskets (medium)
		System.out.println("Maximum number of fruits: "
				+ algos.findMaxBasketSize(new char[] { 'A', 'B', 'C', 'A', 'C' }));
		System.out.println("Maximum number of fruits: "
				+ algos.findMaxBasketSize(new char[] { 'A', 'B', 'C', 'B', 'B', 'C' }));

		// No-repeat Substring (hard)
		System.out.println("Length of the longest substring: " + algos.findLongestNonRepeatingSubstr("aabccbb"));
	    System.out.println("Length of the longest substring: " + algos.findLongestNonRepeatingSubstr("abbbb"));
	    System.out.println("Length of the longest substring: " + algos.findLongestNonRepeatingSubstr("abccde"));
	    System.out.println("Length of the longest substring: " + algos.findLongestNonRepeatingSubstr("dvdf"));
	    
	    System.out.println(algos.findLongestRepeatingStrWithKReplacement("aabccbb", 2));
	    System.out.println(algos.findLongestRepeatingStrWithKReplacement("abbcb", 1));
	    System.out.println(algos.findLongestRepeatingStrWithKReplacement("abccde", 1));
	    
	    // Longest Subarray with Ones after Replacement (hard)
	    System.out.println(algos.findLongestArrayWithOnes(new int[] { 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1 }, 2));
	    System.out.println(algos.findLongestArrayWithOnes(new int[] { 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1 }, 3));
	    
	    // Permutation in a String (hard)
	    System.out.println(algos.findPermutation("oidbcaf", "abc"));
	    System.out.println(algos.findPermutation("odicf", "dc"));
	    System.out.println(algos.findPermutation("bcdxabcdy", "bcdyabcdx"));
	    System.out.println(algos.findPermutation("aaacb", "abc"));

	    System.out.println(algos.findPermutationEducativeSol("oidbcaf", "abc"));
	    System.out.println(algos.findPermutationEducativeSol("odicf", "dc"));
	    System.out.println(algos.findPermutationEducativeSol("bcdxabcdy", "bcdyabcdx"));
	    System.out.println(algos.findPermutationEducativeSol("aaacb", "abc"));
	    
	    // String Anagrams (hard)
	    System.out.println(algos.findStringAnagrams("ppqp", "pq"));
	    System.out.println(algos.findStringAnagrams("abbcabc", "abc"));
	    
	    // Smallest Window containing Substring (hard)
	    System.out.println(algos.findSubstring("aabdec", "abc"));
	    System.out.println(algos.findSubstring("abdbca", "abc"));
	    System.out.println(algos.findSubstring("adcad", "abc"));
	    System.out.println(algos.findSubstring("cabwefgewcwaefgcf", "cae"));
	    System.out.println(algos.findSubstring("aaabdabcefaecbef", "abc"));
	    
	    // Words Concatenation (hard)
	    System.out.println(algos.findWordConcatenation("catfoxcat", new String[] {"cat", "fox"}));
	    System.out.println(algos.findWordConcatenation("catcatfoxfox", new String[] {"cat", "fox"}));
		// ===== Two pointers =====
	    
	    // =========================================================================================
	    // =========================================================================================
	    
	    // Pair with target sum
	    Helpers.printArr(algos.pairWithTargetSum(new int[] {1, 2, 3, 4, 6}, 6));
	    Helpers.printArr(algos.pairWithTargetSum(new int[] {2, 5, 9, 11}, 11));
	    
	    int[] arr = new int[] {2, 3, 3, 3, 6, 9, 9};
	    System.out.println(algos.removeDuplicates(arr));
	    Helpers.printArr(arr);

	    // Remove duplicates
	    arr = new int[] {2, 2, 2, 11};
	    System.out.println(algos.removeDuplicates(arr));
	    Helpers.printArr(arr);
	    
	    // Squaring a sorted array
	    Helpers.printArr(algos.makeSquares(new int[] {-2, -1, 0, 2, 3}));
	    Helpers.printArr(algos.makeSquares(new int[] {-3, -1, 0, 1, 2}));
	    Helpers.printArr(algos.makeSquares(new int[] {-5, -4, -3, -2, -1}));
	    Helpers.printArr(algos.makeSquares(new int[] {1, 2, 3, 4, 5}));

	    Helpers.printArr(algos.makeSquares2(new int[] {-2, -1, 0, 2, 3}));
	    Helpers.printArr(algos.makeSquares2(new int[] {-3, -1, 0, 1, 2}));
	    Helpers.printArr(algos.makeSquares2(new int[] {-5, -4, -3, -2, -1}));
	    Helpers.printArr(algos.makeSquares2(new int[] {1, 2, 3, 4, 5}));
	    
	    // Search triplets
	    System.out.println(algos.searchTriplets(new int[] {-3, 0, 1, 2, -1, 1, -2}));
	    System.out.println(algos.searchTripletsEduSol(new int[] {-3, 0, 1, 2, -1, 1, -2}));
	    
	    // Triplet Sum Close to Target (medium)
	    System.out.println(algos.tripletSumCloseToTarget(new int[] {-2, 0, 1, 2}, 2));
	    System.out.println(algos.tripletSumCloseToTarget(new int[] {-3, -1, 1, 2}, 1));
	    System.out.println(algos.tripletSumCloseToTarget(new int[] {1, 0, 1, 1}, 100));
	    
	    // Subarrays with product less than a target
	    System.out.println(algos.subarrayProductLessThanK(new int[] {2, 5, 3, 10}, 30));
	    
	    // Dutch National Flag Problem (medium)
	    arr = new int[] {1, 0, 2, 1, 0};
	    algos.dutchNationalFlag(arr);
	    Helpers.printArr(arr);
	    
	    // Comparing Strings containing Backspaces (medium)
	    System.out.println(algos.compareStringWithBackSpaces("xy#z", "xzz#"));
	    System.out.println(algos.compareStringWithBackSpaces("xy#z", "xyz#"));
	    System.out.println(algos.compareStringWithBackSpaces("xp#", "xyz##"));
	    System.out.println(algos.compareStringWithBackSpaces("xywrrmp", "xywrrmu#p"));
	    
	    // Minimum window sort
	    System.out.println(algos.minimumWindowSort(new int[] {1, 2, 5, 3, 7, 10, 9, 12}));
	    System.out.println(algos.minimumWindowSort(new int[] {1, 3, 2, 0, -1, 7, 10}));
	    System.out.println(algos.minimumWindowSort(new int[] {1, 2, 3}));
	    System.out.println(algos.minimumWindowSort(new int[] {3, 2, 1}));
	    
	    // =========================================================================================
	    // =========================================================================================
	    System.out.println(algos.isHappyNumber(23));
	    System.out.println(algos.isHappyNumber(12));
	    
	    // Palindrome list
	    ListNode head = new ListNode(2);
	    head.next = new ListNode(4);
	    head.next.next = new ListNode(6);
	    head.next.next.next = new ListNode(4);
	    head.next.next.next.next = new ListNode(2);
	    System.out.println("Is palindrome: " + algos.isPalindrome(head));

	    // Reorder linked list
	    head = new ListNode(2);
	    head.next = new ListNode(4);
	    head.next.next = new ListNode(6);
	    head.next.next.next = new ListNode(8);
	    head.next.next.next.next = new ListNode(10);
	    head.next.next.next.next.next = new ListNode(12);
	    algos.reorder(head);
	    
	    ListNode.printList(head);
	    
	    // Cycle in a circular array
	    System.out.println(algos.circularArrayLoop(new int[] {1, 2, -1, 2, 2}));
	    System.out.println(algos.circularArrayLoop(new int[] {2, 2, -1, 2}));
	    System.out.println(algos.circularArrayLoop(new int[] {2, 1, -1, -2}));
	    
	}
}
