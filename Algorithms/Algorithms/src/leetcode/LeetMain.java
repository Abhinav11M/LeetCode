package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import datastructures.ListNode;
import datastructures.ListNodeWithRandom;

public class LeetMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		int[] arr = LeetAlgos.twoSum(new int[] {3,2,4}, 6);
		
		int[] a = new int[] {-1,0,1,2,-1,-4};
		List<List<Integer>> val = LeetAlgos.threeSum(a);
		List<List<Integer>> val1 = LeetAlgos.threeSumMethod2(a);
		
//		System.out.println(val);
//		System.out.println(val1);
		
//		int[] a1 = new int[] {0,2,1,-3};
//		System.out.println(LeetAlgos.closestSum(a1, 1));
		
//		String[] str = LeetAlgos.letterCombinationsFromDigits("23");
//		System.out.println(LeetAlgos.getAllKeypadCombinations("23"));
//		System.out.println(LeetAlgos.getAllKeypadCombinationsOpt("23"));
		
//		System.out.println(LeetAlgos.getQuadWithTargetSum(new int[] {1, 0, -1, 0, -2, 2}, 0));
//		System.out.println(LeetAlgos.getQuadWithTargetSum1(new int[] {-3,-2,-1,0,0,1,2,3}, 0));
		
//		ListNode l = createList();
//		System.out.println(l);
//		System.out.println(LeetAlgos.removeNthFromEnd(l, 2));
//		System.out.println(LeetAlgos.areBracketsBalanced("{[]()"));
		
		ListNode l1 = createList1();
		ListNode l2 = createList2();
		
//		ListNode res = LeetAlgos.mergeLists(l1, l2);
//		printListNode(res);
		ArrayList<String> res = new ArrayList<>();
		LeetAlgos.generateParanthesis(res, "", 2, 2);
//		System.out.println(res);
		
//		ListNode mergeHead = LeetAlgos.mergeKLists2(new ListNode[] {null, l1, null, l2});
//		printListNode(mergeHead);
		
//		ListNode swapped = LeetAlgos.swapPairs(createList3());
//		System.out.println("*********");
//		printListNode(swapped);
//		ListNode swapped1 = LeetAlgos.swapPairsRec(createList3());
//		printListNode(swapped1);
		
//		System.out.println(LeetAlgos.reverseFirstK(createList(), 3));
		ListNode x = LeetAlgos.reverseKGroupRec(createList3(), 3);
		ListNode x1 = LeetAlgos.reverseKGroupRec1(createList3(), 2);
//		printListNode(x1);
		
//		int[] duplicateArr = new int[] {0,0,1,1,1,2,2,3,3,4};
//		int[] duplicateArr = new int[] {1,2,2};
//		int[] duplicateArr = new int[] {1,2,3,4,5};
//		int distinct = LeetAlgos.removeDuplicates(duplicateArr);
//		System.out.println("Len : " + distinct);
//		for(int val11 : duplicateArr) {
//			System.out.print(val11 + ",");
//		}
		
//		int[] arr1 = new int[] {3,2,2,3};
//		int[] arr1 = new int[] {0,1,2,2,3,0,4,2};
//		System.out.println(LeetAlgos.removeElement(arr1, 2));
//		printIntArr(arr1);
		
//		System.out.println(LeetAlgos.strStr1("Hello", "ll"));
//		System.out.println(LeetAlgos.strStr1("mississippi", "issip"));
//		System.out.println(LeetAlgos.subStringIndex("mississippi", "issip"));
//		System.out.println(Math.abs(-2147483648));
//		System.out.println(LeetAlgos.divide(-2147483648, -1));
//		System.out.println(LeetAlgos.divide(7, -3));
//		System.out.println(LeetAlgos.divide(10, 3));
//		System.out.println(LeetAlgos.divide(-1010369383,-2147483648));

		ListNode ll1 = new ListNode(3);
		ll1.next = new ListNode(7);
		ListNode ll2 = new ListNode(9);
		ll2.next = new ListNode(2);
		
//		ListNode ret = LeetAlgos.addTwoNumbers(createList1(), createList2());
//		ListNode ret = LeetAlgos.addTwoNumbers(ll1, ll2);
//		printListNode(ret);
		
		System.out.println(LeetAlgos.lengthOfLongestSubstringOpt("abcabcbb"));
		System.out.println(LeetAlgos.lengthOfLongestSubstringOpt("pwwkew"));
		System.out.println(LeetAlgos.lengthOfLongestSubstringOpt("dvdf"));
		
		System.out.println(LeetAlgos.findMedianOfSortedArraysOfEqualLength(new int[] {1,2,3}, new int[] {4,5,6}));
		System.out.println(LeetAlgos.findMedianOfSortedArraysOfEqualLength(new int[] {1,3,6,7}, new int[] {2,5,8,12}));
		
//		System.out.println(LeetAlgos.findMedianSortedArrays(new int[] {1}, new int[] {2,3,4}));
		int[] nums1 = new int[] {2,4,8,9,12};
		int[] nums2 = new int[] {3,5,11,14,18};
//		nums1 = new int[] {2};
//		nums2 = new int[] {3,5,11,14};
		nums1 = new int[] {1,3};
		nums2 = new int[] {2};
	
		System.out.println(LeetAlgos.findMedianSortedArrays(nums1, nums2));
		
		// Clone a linked list with random pointer.
		ListNodeWithRandom l = creteLinkedListWithRandow();
		ListNodeWithRandom.printList(l);
		ListNodeWithRandom cloned = LeetAlgos.cloneListWithRandom(l);
		ListNodeWithRandom.printList(cloned);
		
		// Longest pelindrome substring
//		System.out.println(LeetAlgos.largestPelindrome1("aabcdddd"));
//		System.out.println(LeetAlgos.largestPelindrome1("aba"));
//		System.out.println(LeetAlgos.manacherAlgo("abcba"));
//		System.out.println(LeetAlgos.reverse(-321));
//		System.out.println(LeetAlgos.reverse(123));
//		System.out.println(LeetAlgos.isPalindrome(12321));
		System.out.println(LeetAlgos.maxAreaOpt(new int[] {1,8,6,2,5,4,8,3,7}));
		System.out.println(LeetAlgos.intToRoman(1249));
		System.out.println(LeetAlgos.romanToInt("MCCXLIX"));
		System.out.println(LeetAlgos.romanToIntOpt("MCCXLIX"));
//		System.out.println(LeetAlgos.romanToInt("MI"));
		System.out.println(LeetAlgos.longestCommonPrefix(new String[] {"flower","flow","flight"}));
		System.out.println(LeetAlgos.longestCommonPrefix(new String[] {"dog","racecar","car"}));
//		System.out.println(LeetAlgos.myAtoi("-1234 1234"));	
//		System.out.println(LeetAlgos.myAtoi("+-2"));	
//		System.out.println(LeetAlgos.myAtoi("  -0012a42"));
//		System.out.println(LeetAlgos.myAtoi("+-2"));
		System.out.println(LeetAlgos.convert("PAYPALISHIRING", 3));
//		System.out.println(LeetAlgos.search(new int[] {4,5,6,7,0,1,2}, 0));
//		System.out.println(LeetAlgos.search(new int[] {4,5,6,7,0,1,2}, 3));
//		System.out.println(LeetAlgos.search(new int[] {1,3}, 0));
//		System.out.println(LeetAlgos.search(new int[] {1,3}, 3));
//		System.out.println(LeetAlgos.search(new int[] {3,1}, 1));
//		System.out.println(LeetAlgos.search(new int[] {3,5,1}, 3));
		
//		System.out.println(LeetAlgos.searchRange(new int[] {5,7,7,8,8,10}, 8));
//		System.out.println(LeetAlgos.searchInsert(new int[] {1,3,5,6}, 0));
//		List<List<Integer>> resComb = LeetAlgos.combinationSum(new int[] {2,3,6,7}, 7);
//		List<List<Integer>> resComb = LeetAlgos.combinationSum(new int[] {2,3,5}, 8);
//		List<List<Integer>> resComb = LeetAlgos.combinationSum(new int[] {2,3,6,7}, 7);
//		List<List<Integer>> resComb = LeetAlgos.combinationSum(new int[] {8,7,4,3}, 11);
//		List<List<Integer>> resComb1 = LeetAlgos.combinationSum(new int[] {3,4,7,8}, 11);

//		List<List<Integer>> resComb = LeetAlgos.combinationSum2(new int[] {2,3,6,7}, 7);
//		List<List<Integer>> resComb = LeetAlgos.combinationSum2(new int[] {2,3,4,7}, 7);
//		List<List<Integer>> resComb = LeetAlgos.combinationSum2(new int[] {2,5,2,1,2}, 5);
//		for(List<Integer> t : resComb) {
//			System.out.println(t);
//		}
		
		/*
		 * Find the first missing positive integer.
		System.out.println(LeetAlgos.firstMissingPositive(new int[] {3,4,-1,1}));
		System.out.println(LeetAlgos.firstMissingPositive(new int[] {1,2,0}));
		System.out.println(LeetAlgos.firstMissingPositive(new int[] {7,8,9,11,12}));
		System.out.println(LeetAlgos.firstMissingPositive(new int[] {1}));
		System.out.println(LeetAlgos.firstMissingPositive(new int[] {1,2,0}));
		System.out.println(LeetAlgos.firstMissingPositive(new int[] {1,1}));
		*/
		
//		System.out.println(LeetAlgos.multiply("12", "34"));
//		System.out.println(LeetAlgos.multiply("44", "55"));
//		System.out.println(LeetAlgos.multiply("0", "0"));
//		System.out.println(LeetAlgos.permute(new int[] {1,2,3}));
//		LeetAlgos.rotate(new int[][] { {1,2,3}, {4,5,6} , {7,8,9} });
		
//		List<List<String>> res1 = LeetAlgos.groupAnagrams(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"});
		
//		System.out.println(LeetAlgos.isAnagram1("tan", "nat"));
//		System.out.println(LeetAlgos.isAnagram2("tan", "net"));
//		System.out.println(LeetAlgos.isAnagram3("tan", "nat"));
//		System.out.println(LeetAlgos.isAnagram3("pit", "pro"));
		
//		List<List<String>> res1 = LeetAlgos.groupAnagramsOpt(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"});
//		List<List<String>> res2 = LeetAlgos.groupAnagramsOpt(new String[] {"",""});
//		List<List<String>> res3 = LeetAlgos.groupAnagramsOpt(new String[] {"tao","pit","cam","aid","pro","dog"});
//		System.out.println(LeetAlgos.myPow(2, -2));
//		System.out.println(LeetAlgos.myPow(2, 10));
//		System.out.println(LeetAlgos.myPow(0.00001, 2147483647));
//		System.out.println(LeetAlgos.maxSubArray(new int[] {-2,-1}));
//		System.out.println(LeetAlgos.maxSubArray(new int[] {2}));
//		printArr(LeetAlgos.maxSubArray1(new Integer[] {-2,-1}));
//		printArr(LeetAlgos.maxSubArray1(new Integer[] {2}));
//		printArr(LeetAlgos.maxSubArray1(new Integer[] {-2,1,-3,4,-1,2,1,-5,4}));
//		System.out.println(LeetAlgos.lengthOfLastWord("Hello World"));
//		System.out.println(LeetAlgos.lengthOfLastWord("a "));
//		printArr(LeetAlgos.plusOne(new int[] {1,2,3}));
//		printArr(LeetAlgos.plusOne(new int[] {9,9,9}));
		
//		System.out.println(LeetAlgos.mySqrt(2147395600));
//		System.out.println(LeetAlgos.mySqrtOpt(2147395600));
//		System.out.println(LeetAlgos.mySqrtOpt(6));
//		System.out.println(LeetAlgos.mySqrtOpt(8));
		
//		System.out.println(LeetAlgos.climbStairs(3));
//		System.out.println(LeetAlgos.climbStairsDP(3));
//		
//		System.out.print(LeetAlgos.simplifyPath("/a/../../b/../c//.//"));
		
		/* ==== Testing merging of two sorted arrays.
		int[] merged = LeetAlgos.mergeOpt(new int[] {4,5,6,0,0,0}, 3, new int[] {1,2,8}, 3);
		merged = LeetAlgos.mergeOpt(new int[] {1,2,3,0,0,0}, 3, new int[] {5,6,7}, 3);

		merged = LeetAlgos.mergeOpt(new int[] {2,4,6,0,0,0}, 3, new int[] {1,3,5}, 3);
		*/
		
		// ========= Testing of removing duplicates from a sorted linked list
		
//		ListNode listNode = createSortedList();
//		listNode = LeetAlgos.deleteDuplicates(listNode);
//		System.out.println();
		
		// ========= Testing of removing duplicates from a sorted linked list
		
//		List<List<Integer>> rres = LeetAlgos.combinationSum2New(new int[] {1,2,3,4,5}, 7);
//		rres = LeetAlgos.combinationSum2New(new int[] {10,1,2,7,6,1,5}, 8);
		
//		List<List<Integer>> rres = LeetAlgos.threeSumNew1(new int[] {-1, 0, 1, 2, -1, -4});
//		rres = LeetAlgos.threeSumNew1(new int[] {0,0,0});
//		rres = LeetAlgos.threeSumNew1(new int[] {-2,0,1,1,2});
		
		int[][] counterArr = LeetAlgos.generateMatrix(3);
		counterArr = LeetAlgos.generateMatrix(4);
		counterArr = LeetAlgos.generateMatrix(5);
		
		Map<Integer, Integer> resNLE = LeetAlgos.getNextLargestElement(new int[] {5,2,1,3,9});
		System.out.println(resNLE.toString());
		List<List<Integer>> resSubWithoutDup = LeetAlgos.subsetsWithoutDuplicates(new int[] {1,2,2});
		resSubWithoutDup = LeetAlgos.subsetsWithoutDuplicates(new int[] {4,4,4,1,4});
		System.out.println(resSubWithoutDup);
		ListNode headRev = createListNodesForRev();
//		ListNode headRevRes = LeetAlgos.reverseBetween(headRev, 1, 5);
//		ListNode headRevRes = LeetAlgos.reverseBetween(headRev, 3, 4);
		ListNode headRevRes = LeetAlgos.reverseBetween(headRev, 3, 3);
		printListNode(headRevRes);
		System.out.println();
		System.out.println(LeetAlgos.removeDuplicates2(new int[] {1,1,1,1,2,2,3,3,3,3,3,4,4}));
		
		List<List<Integer>> resPU = LeetAlgos.heapsPermutation(new int[] {1,1,2});
		resPU = LeetAlgos.heapsPermutation(new int[] {1,2,3,4});
		
		List<Integer> spiratRes = LeetAlgos.spiralPrint(new int[][] { {1,2,3},{4,5,6},{7,8,9} });
		spiratRes = LeetAlgos.spiralPrint(new int[][] { {1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16} });
		
		LeetAlgos.getAllPermutations("123", "");
		LeetAlgos.getAllPermutations("ABC", "");
		
		System.out.println(LeetAlgos.getPermutation(3, 4));
		System.out.println(LeetAlgos.getPermutation(3, 3));
		System.out.println(LeetAlgos.getPermutation(9, 353955));
		System.out.println(LeetAlgos.getPermutationOpt(3, 3));
		System.out.println(LeetAlgos.getPermutationOpt(3, 4));
		
		ListNode headToRotate = createListNodesForRev();
		ListNode newHead = LeetAlgos.rotateRight(headToRotate, 10);
		
		System.out.println(LeetAlgos.uniquePaths(7, 3));
		System.out.println(LeetAlgos.uniquePathsDP(7, 3));
		System.out.println(LeetAlgos.uniquePathsDP_TopDown(7, 3));

		System.out.println(LeetAlgos.uniquePathsWithObstacles_TopDown(new int[][] { {0,0,0}, {0,1,0}, {0,0,0} }));

		System.out.println(LeetAlgos.minPathSum(new int[][] { {1,3,1}, {1,5,1}, {4,2,1} }));
		System.out.println(LeetAlgos.minPathSum_OptDP(new int[][] { {1,3,1}, {1,5,1}, {4,2,1} }));
		System.out.println(LeetAlgos.addBinary("111", "11"));
		System.out.println(LeetAlgos.addBinary("1010", "1011"));
		System.out.println(LeetAlgos.addBinary("1", "111"));
		
		System.out.println(LeetAlgos.addBinary2("111", "11"));
		System.out.println(LeetAlgos.addBinary2("1010", "1011"));
		System.out.println(LeetAlgos.addBinary2("1", "111"));
		int[][] arrSetZero = new int[][] { {1,1,1}, {1,0,1}, {1,1,1} };
		LeetAlgos.setZeroes(arrSetZero);
		printMatrix(arrSetZero);
		int[][] arrSetZero1 = new int[][] { {0,1,2,0}, {3,4,5,2}, {1,3,1,5} };
		LeetAlgos.setZeroes(arrSetZero1);
		printMatrix(arrSetZero1);
		System.out.println(LeetAlgos.searchMatrix(new int[][] { {1,3,5,7}, {10,11,16,20}, {23,30,34,50} }, 13));
		System.out.println(LeetAlgos.searchMatrix(new int[][] { {1,3,5,7}, {10,11,16,20}, {23,30,34,50} },16));
		System.out.println(LeetAlgos.searchMatrix(new int[][] { {1,3,5,13} },13));
		System.out.println(LeetAlgos.searchMatrix(new int[][] { {1}, {3}, {5} }, 5));
		System.out.println(LeetAlgos.searchMatrix(new int[][] { {} },1));
		System.out.println(LeetAlgos.searchMatrix(new int[][] {{1,3,5,7},{10,11,16,20},{23,30,34,50}} ,5));
	
//		LeetAlgos.combineRec(4, 2);
//		LeetAlgos.combineRec2(4, 2);
//		LeetAlgos.subsets(new int[] {1,2,3});
		LeetAlgos.subsetsOpt(new int[] {1,2,3});
//		System.out.println(LeetAlgos.removeDuplicates3(new int[] {1,1,1,2,2,3}));
		System.out.println(LeetAlgos.removeDuplicates3(new int[] {0,0,1,1,1,1,2,3,3}));
		int[] arrLeftShift = new int[] { 1,2,3,4,5,6,7,8 };
		LeetAlgos.leftRorateArray(arrLeftShift, 4);
		
		System.out.println(LeetAlgos.searchRotatedArray2(new int[] { 0,0,1,2,2,5,6 }, 0));
		System.out.println(LeetAlgos.searchRotatedArray2(new int[] { 2,5,6,0,0,1,2 }, 0));
		System.out.println(LeetAlgos.searchRotatedArray2(new int[] { 2,5,6,0,0,1,2 }, 1));
		System.out.println(LeetAlgos.searchRotatedArray2(new int[] { 2,5,6,0,0,1,2 }, 3));
		System.out.println(LeetAlgos.searchRotatedArray2(new int[] { 3,1 }, 1));
		System.out.println(LeetAlgos.searchRotatedArray2(new int[] {3,5,1}, 1));
		System.out.println(LeetAlgos.searchRotatedArray2(new int[] {1,3,1,1,1}, 3));
		System.out.println(LeetAlgos.searchRotatedArray2(new int[] {1,1,3,1}, 3));
		System.out.println(LeetAlgos.searchRotatedArray2(new int[] {0,0,1,1,2,0}, 2));
		System.out.println(LeetAlgos.searchRotatedArray2(new int[] {5,1,3}, 3));
		
		// ============= Remove duplicate values from a sorted linked list ==========
		ListNode listToTest = createListToTest();
//		listToTest = LeetAlgos.deleteDuplicateNodes(listToTest);
//		listToTest = LeetAlgos.keepDistinct(listToTest);
		
		ListNode llll = new ListNode(1);
		llll.next = new ListNode(1);
		llll.next.next = new ListNode(2);
		
		listToTest = LeetAlgos.keepDistinct(llll);
		
		System.out.println(LeetAlgos.maxContSum(new int[] {3,4,1,9,7,2}, 3));
		System.out.println(LeetAlgos.maxContSum(new int[] {9,8,7,1,2,3}, 3));
		
		System.out.println("********* Grumpy **********");
//		System.out.println(LeetAlgos.maxSatisfied1(new int[]{4,10,10}, new int[]{1,1,0}, 2));
//		System.out.println(LeetAlgos.maxSatisfied1(new int[]{1,0,1,2,1,1,7,5}, new int[]{0,1,0,1,0,1,0,1}, 3));
//		System.out.println(LeetAlgos.maxSatisfied1(new int[]{7,8,8,6}, new int[]{0,1,0,1}, 3));
//		System.out.println(LeetAlgos.maxSatisfied1(new int[]{5,10,2,2,3,8}, new int[]{1,1,1,1,0,1}, 2));
//		System.out.println(LeetAlgos.maxSatisfied1(new int[]{7,3,2,5,1,6}, new int[]{0,1,1,1,0,0}, 1));
//		System.out.println(LeetAlgos.maxSatisfied1(new int[]{6,10,2,1,7,9}, new int[]{1,0,0,0,0,1}, 3));
		System.out.println(LeetAlgos.maxSatisfied1(new int[]{4,2,1,2,8,9,1}, new int[]{0,1,1,1,1,0,0}, 4));
		System.out.println("********* Grumpy **********");
		
		System.out.println();
	}

	private static ListNode createListToTest() {
		ListNode l = new ListNode(1);
		l.next = new ListNode(2);
		l.next.next = new ListNode(3);
		l.next.next.next = new ListNode(3);
		l.next.next.next.next = new ListNode(4);
		l.next.next.next.next.next = new ListNode(4);
		l.next.next.next.next.next.next = new ListNode(5);
		
		return l;
	}
	
	private static ListNode createListNodesForRev() {
		ListNode l = new ListNode(1);
		l.next = new ListNode(2);
		l.next.next = new ListNode(3);
		l.next.next.next = new ListNode(4);
		l.next.next.next.next = new ListNode(5);
		
		return l;
	}

	private static ListNode createSortedList() {
		ListNode l = new ListNode(1);
		l.next = new ListNode(1);
		l.next.next = new ListNode(2);
		l.next.next.next = new ListNode(3);
		l.next.next.next.next = new ListNode(3);
		
		return l;
	}

	public static void printArr(int[] arr) {
		for(int data : arr) {
			System.out.print(data + ",");
		}
		System.out.println();
	}
	
	public static void printMatrix(int[][] matrix) {
		System.out.print("[\n");
		for(int i = 0; i < matrix.length; ++i) {
			System.out.print("  [");
			for(int j = 0; j < matrix[0].length; ++j) {
				System.out.print(matrix[i][j] + ",");
			}
			System.out.print("]\n");
		}
		System.out.println("]");
	}
	
	public static<T> void printArr(T[] arr) {
		for(T data : arr) {
			System.out.print(data + ",");
		}
		System.out.println();
	}

	private static ListNodeWithRandom creteLinkedListWithRandow() {
		ListNodeWithRandom a = new ListNodeWithRandom(1);
		ListNodeWithRandom b = new ListNodeWithRandom(2);
		ListNodeWithRandom c = new ListNodeWithRandom(3);
		ListNodeWithRandom d = new ListNodeWithRandom(4);
		ListNodeWithRandom e = new ListNodeWithRandom(5);
		a.next = b;
		a.random = d;
		b.next = c;
		b.random = e;
		c.next = d;
		c.random = a;
		d.next = e;
		d.random = b;
		e.next = null;
		e.random = c;
		
		return a;
	}

	private static void printIntArr(int[] arr) {
		for(int i : arr) {
			System.out.print(i + ", ");
		}
	}
	
	private static void printListNode(ListNode res) {
		while(res != null) {
			System.out.print(Integer.toString(res.val) + ",");
			res = res.next;
		}
	}

	private static ListNode createList1() {
		ListNode l = new ListNode(4);
		l.next = new ListNode(6);
		l.next.next = new ListNode(4);
		return l;
	}

	private static ListNode createList2() {
		ListNode l = new ListNode(6);
		l.next = new ListNode(4);
		l.next.next = new ListNode(6);
		return l;
	}

	private static ListNode createList() {
		ListNode l = new ListNode(1);
		l.next = new ListNode(2);
		l.next.next = new ListNode(3);
		l.next.next.next = new ListNode(4);
		l.next.next.next.next = new ListNode(5);
		
		return l;
	}
	
	private static ListNode createList3() {
		ListNode l = new ListNode(1);
		l.next = new ListNode(2);
		l.next.next = new ListNode(3);
		l.next.next.next = new ListNode(4);
		l.next.next.next.next = new ListNode(5);
//		l.next.next.next.next.next = new ListNode(6);
//		l.next.next.next.next.next.next = new ListNode(7);
//		l.next.next.next.next.next.next.next = new ListNode(8);
//		l.next.next.next.next.next.next.next.next = new ListNode(9);
		return l;
	}
}
