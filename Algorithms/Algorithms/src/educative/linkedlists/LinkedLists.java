package educative.linkedlists;

import educative.fastandslowpointers.ListNode;

public class LinkedLists {
	/**
	 * Reverse a linked list
	 */
	public ListNode reverseList(ListNode head) {
		ListNode newHead = null;
		
		while(head != null) {
			if(newHead == null) {
				newHead = head;
				head = head.next;
				newHead.next = null;
			}
			else {
				ListNode tempNode = head;
				head = head.next;
				tempNode.next = newHead;
				newHead = tempNode;
			}
		}
		
		return newHead;
	}
	
	/**
	 * Reverse a Sub-list
	 * Given the head of a LinkedList and two positions ‘p’ and ‘q’, reverse the LinkedList from position ‘p’ to ‘q’.
	 */
	public ListNode reverseSubList(ListNode head, int p, int q) {
		if(head == null || head.next == null || p == q) {
			return head;
		}
		
		ListNode subListHead = head;
		ListNode prev = null;
		int i = 1;
		while(i < p) {
			prev = subListHead;
			subListHead = subListHead.next;
			++i;
		}

		int count = q-p;
		ListNode reverseHead = null;
		ListNode tailSubList = null;
		
		while(count >= 0) {
			if(reverseHead == null) {
				reverseHead = subListHead;
				subListHead = subListHead.next;
				reverseHead.next = null;
				tailSubList = reverseHead;
			}
			else {
				ListNode tempNode = subListHead;
				subListHead = subListHead.next;
				tempNode.next = reverseHead;
				reverseHead = tempNode;
			}
			--count;
		}
		
		tailSubList.next = subListHead;
		if(prev != null) {
			prev.next = reverseHead;
		}
		
		return prev == null ? reverseHead : head;
	}
	
	/**
	 * Reverse every K-element Sub-list 
	 * Given the head of a LinkedList and a number ‘k’, reverse every ‘k’ sized sub-list starting from the head.
	 * If, in the end, you are left with a sub-list with less than ‘k’ elements, reverse it too.
	 */
	public ListNode reverseEveryKElements(ListNode head, int k) {
		
		if(k == 1) {
			return head;
		}

		ListNode prev = null;
		ListNode next = head;
		ListNode finalList = null;


		while (head != null) {
			int count = 1;
			ListNode newHead = null;
			while (true) {
				if (newHead == null) {
					newHead = head;
					head = head.next;
					newHead.next = null;
				}
				else {
					ListNode temp = head;
					head = head.next;
					temp.next = newHead;
					newHead = temp;
				}
				++count;

				if (count == k || head.next == null) { // or end of list) { // head has reached kth node
					next.next = head.next;
					ListNode temp = head;
					head = head.next;
					temp.next = newHead;
					newHead = temp;
					
					if(prev != null) { // Connect the left link
						prev.next = newHead;
					}
					prev = next;
					next = head;
					
					if(finalList == null) {
						finalList = newHead;
					}
					
					break;
				}
			}
		}

		return finalList;
	}
		
}
