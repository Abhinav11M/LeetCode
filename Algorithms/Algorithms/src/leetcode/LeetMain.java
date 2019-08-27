package leetcode;

import java.util.ArrayList;
import java.util.List;

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
