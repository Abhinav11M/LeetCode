package leetcode;

import java.util.List;

import datastructures.ListNode;

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
		
		ListNode res = LeetAlgos.mergeLists(l1, l2);
		printListNode(res);
	}

	private static void printListNode(ListNode res) {
		while(res != null) {
			System.out.print(Integer.toString(res.val) + ",");
			res = res.next;
		}
	}

	private static ListNode createList1() {
		ListNode l = new ListNode(1);
		l.next = new ListNode(2);
		l.next.next = new ListNode(4);
		return l;
	}

	private static ListNode createList2() {
		ListNode l = new ListNode(1);
		l.next = new ListNode(3);
		l.next.next = new ListNode(4);
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

}
