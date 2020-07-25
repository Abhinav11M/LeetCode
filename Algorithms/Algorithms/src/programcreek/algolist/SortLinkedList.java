package programcreek.algolist;

import datastructures.ListNode;

public class SortLinkedList {
	public ListNode mergeSortList(ListNode head) {
		
		return mergeSortListRec(head);
	}

	private ListNode mergeSortListRec(ListNode head) {

		if(head == null || head.next == null) {
			return head;
		}
		
		int len = length(head)-1;
		
		int mid = len/2;
		
		ListNode temp = head;
		int count = 1;
		ListNode rightHead = null;
		
		while(count <= mid && temp != null) {
			++count;
			temp = temp.next;
		}
		
		if(temp != null) {
			rightHead = temp.next;
		}
		temp.next = null;
		
		ListNode leftSorted = mergeSortListRec(head);
		ListNode rightSorted = mergeSortListRec(rightHead);
		
		return merge(leftSorted, rightSorted);
		
	}

	private static ListNode merge(ListNode leftSorted, ListNode rightSorted) {
		
		ListNode mergedHead = null;
		ListNode finalHead = null;
		
		while(leftSorted != null && rightSorted != null) {
			if(leftSorted.val < rightSorted.val) {
				if(mergedHead == null) {
					mergedHead = leftSorted;
					finalHead = leftSorted;
					leftSorted = leftSorted.next;
				}
				else {
					mergedHead.next = leftSorted;
					mergedHead = mergedHead.next;
					leftSorted = leftSorted.next;
				}
			}
			else {
				if(mergedHead == null) {
					mergedHead = rightSorted;
					finalHead = rightSorted;
					rightSorted = rightSorted.next;
				}
				else {
					mergedHead.next = rightSorted;
					mergedHead = mergedHead.next;
					rightSorted = rightSorted.next;
				}
			}
		}
		
		if(leftSorted != null) {
			mergedHead.next = leftSorted;
		}
		
		if(rightSorted != null) {
			mergedHead.next = rightSorted;
		}
		
		return finalHead;
	}

	private static int length(ListNode head) {
		int count = 0;
		while(head != null) {
			++count;
			head = head.next;
		}
		
		return count;
	}
}
