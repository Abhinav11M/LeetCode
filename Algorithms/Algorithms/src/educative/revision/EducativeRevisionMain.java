package educative.revision;

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
	}
}
