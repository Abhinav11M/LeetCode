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
		
	/**
	 * Reverse alternating K-element Sub-list 
	 */
	public ListNode reverseAlternatingKElements(ListNode head, int k) {
		ListNode prev = null;
		ListNode next = head;
		ListNode finalHead = null;
		
		ListNode rev = null;
		
		boolean doReverse = true;
		
		while(head != null) {
			if(!doReverse) {
				for(int i = 0; i < k; ++i) {
					prev = head;
					head = head.next;
				}
				next = head;
				doReverse = true;
				continue;
			}
			
			int count = 1;
			while(count <= k && head != null) {
				if(rev == null) {
					rev = head;
					head = head.next;
					rev.next = null;
				}
				else {
					ListNode temp = head;
					head = head.next;
					temp.next = rev;
					rev = temp;
				}
				++count;
			}
			if(prev != null) {
				prev.next = rev;
			}
			else {
				finalHead = rev;
			}
			next.next = head;
			prev = next;
			next = head;
			
			doReverse = false;
		}
		
		return finalHead;
	}

	/**
	 * Rotate a linked list
	 */
	public ListNode rotate(ListNode head, int rotations) {
		if(head == null || head.next == null) {
			return head;
		}
		
		int numNodes = countNodes(head);
		rotations = rotations % numNodes; // N rotations will give back the same list
		
		if(rotations == 0) {
			return head;
		}
		
		ListNode prev = null;
		ListNode next = head;
		
		for(int i = 0; i < numNodes - rotations; ++i) {
			prev = next;
			next = next.next;
		}
		
		prev.next = null;
		ListNode newHead = next;
		
		while(next.next != null) {
			next = next.next;
		}
		next.next = head;
		
		return newHead;
	}

	private int countNodes(ListNode head) {
		if(head == null) {
			return 0;
		}
		
		int count = 0;
		while(head != null) {
			++count;
			head = head.next;
		}
		
		return count;
	}
}
