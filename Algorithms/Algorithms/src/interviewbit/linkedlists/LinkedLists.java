package interviewbit.linkedlists;

import java.util.Deque;
import java.util.LinkedList;

import educative.fastandslowpointers.ListNode;

public class LinkedLists {
	
	/**
	 * Check if a linked list is pelindrome or not
	 * @param head head of the linked list
	 * @return
	 */
	public boolean isPelindrome(ListNode head) {
		if(head == null || head.next == null) {
			return true;
		}
		
		ListNode fast = head;
		ListNode slow = head;
		ListNode prev = null;
		
		while(fast != null && fast.next != null) {
			fast = fast.next.next;
			prev = slow;
			slow = slow.next;
		}
		
		// Slow will be the mid
		// reverse from mid->next
		
		ListNode revHead = prev.next;
		prev.next = null; // break the link
		
		ListNode newHead = null;
		while(revHead != null) {
			if(newHead == null) {
				newHead = revHead;
				revHead = revHead.next;
				newHead.next = null;
			}
			else {
				ListNode temp = revHead;
				revHead = revHead.next;
				temp.next = newHead;
				newHead = temp;
			}
		}
		
		while(head != null && newHead != null) {
			if(head.value != newHead.value) {
				return false;
			}
			head = head.next;
			newHead = newHead.next;
		}
		
		return true;
	}
	
	/**
	 * Remove Duplicates From Sorted List
	 * Given 1=>1=>2, return 1=>2.
	 * Given 1=>1=>2=>3=>3, return 1=>2=>3.
	 */
	public ListNode removeDuplicates(ListNode head) {
		ListNode currNode = head;
		while(currNode != null) {
			ListNode nextNode = currNode;
			while(nextNode != null && nextNode.value == currNode.value) {
				nextNode = nextNode.next;
			}
			
			currNode.next = nextNode;
			currNode = nextNode;
		}
		
		return head;
	}
	
	/**
	 * Remove Duplicates from Sorted List II
	 * Given 1->2->3->3->4->4->5, return 1->2->5.
	 * Given 1->1->1->2->3, return 2->3.
	 */
	public ListNode removeDuplicates2(ListNode head) {
		if(head == null || head.next == null) {
			return head;
		}
		
		ListNode currNode = null;
		
		ListNode finalHead = null;
		
		while(head != null && head.next != null) {
			if(head.value == head.next.value) {
				// Move the head till the next different value
				while(head != null && head.next != null &&
						head.value == head.next.value) {
					head = head.next;
				}
				head = head.next;
				continue;
			}
			
			// Connect the next different value
			if(finalHead == null) {
				finalHead = head;
				currNode = finalHead;
			}
			else {
				currNode.next = head;
				currNode = head;
			}
			
			// Move the pointer ahead
			head = head.next;
		}
		
		// We have not checked the last node
		if(currNode != null) {
			currNode.next = head;
		}
		else {
			finalHead = head;
		}
		return finalHead;
	}
	
	/**
	 * Merge two sorted lists
	 */
	public ListNode mergeTwoLists(ListNode head1, ListNode head2) {
		
		if(head1 == null) {
			return head2;
		}
		if(head2 == null) {
			return head1;
		}
		
		ListNode retHead = null;
		ListNode finalHead = null;
		
		while(head1 != null && head2 != null) {
			if(head1.value > head2.value) {
				if(retHead == null) {
					retHead = head2;
					finalHead = retHead;
					head2 = head2.next;
				}
				else {
					retHead.next = head2;
					retHead = retHead.next;
					head2 = head2.next;
				}
			}
			else {
				if(retHead == null) {
					retHead = head1;
					finalHead = retHead;
					head1 = head1.next;
				}
				else {
					retHead.next = head1;
					retHead = retHead.next;
					head1 = head1.next;
				}
			}
		}
		
		if(head1 == null) {
			retHead.next = head2;
		}
		else if(head2 == null) {
			retHead.next = head1;
		}
		
		return finalHead;
	}
	
	/**
	 * Rotate list
	 */
	public ListNode rotateRight(ListNode head, int rotation) {
		
		if(head == null || head.next == null) {
			return head;
		}
		
		// count the number of nodes
		ListNode tempHead = head;
		int length = 0;
		while(tempHead != null) {
			tempHead = tempHead.next;
			++length;
		}
		
		rotation = rotation % length;
		if(rotation == 0) {
		    return head;
		}
		
		tempHead = head;
		for(int i = 0; i < length - rotation-1; ++i) {
			tempHead = tempHead.next;
		}
		
		ListNode newHead = tempHead.next;
		tempHead.next = null; // Disconnecting lists
		
		tempHead = newHead;
		while(tempHead.next != null) {
			tempHead = tempHead.next;
		}
		
		tempHead.next = head;
		
		return newHead;
	}
	
	/**
	 * Reorder List
	 * Given a singly linked list
	 * L: L0 -> L1 -> ... -> Ln-1 -> Ln
	 * reorder it to:
	 * L0 -> Ln -> L1 -> Ln-1 -> L2 -> Ln-2 -> ...
	 */
	 public ListNode reorderList(ListNode head) {
		 Deque<ListNode> deque = new LinkedList<ListNode>();
		 while(head != null) {
			 deque.addLast(head);
			 head = head.next;
		 }
		 
		 ListNode retHead = null;
		 ListNode finalHead = null;
		 boolean pickFromFront = false;
		 
		 while(!deque.isEmpty()) {
			 if(retHead == null) {
				 retHead = deque.pollFirst();
				 finalHead = retHead;
			 }
			 else {
				 if(pickFromFront == false) {
					 retHead.next = deque.pollLast();
					 pickFromFront = true;
				 }
				 else {
					 retHead.next = deque.pollFirst();
					 pickFromFront = false;
				 }
				 retHead = retHead.next;
			 }
		 }
		 
		 retHead.next = null;
		 
		 return finalHead;
	 }
	
	 /**
	  * Add Two Numbers as Lists
	 * @return 
	  */
	 public ListNode addNumbers(ListNode head1, ListNode head2) {
		 // Reverse both the heads
		 head1 = reverseList(head1);
		 head2 = reverseList(head2);
		 
		 int carry = 0;
		 ListNode finalHead = null;
		 ListNode retHead = null;
		 
		 while(head1 != null || head2 != null) {
			 int val = 0;
			 if(head1 != null) {
				 val += head1.value;
				 head1 = head1.next;
			 }
			 if(head2 != null) {
				 val += head2.value;
				 head2 = head2.next;
			 }
			 
			 val += carry;
			 if(val > 9) {
				 carry = 1;
				 val = val - 10;
			 }
			 else {
				 carry = 0;
			 }
			 
			 if(finalHead == null) {
				 finalHead = new ListNode(val);
				 retHead = finalHead;
			 }
			 else {
				 finalHead.next = new ListNode(val);
				 finalHead = finalHead.next;
			 }
		 }
		 
		 if(carry != 0) {
			 finalHead.next = new ListNode(carry);
			 finalHead.next.next = null;
		 }
		 else {
			 finalHead.next = null;
		 }
		 
		 return reverseList(retHead);
	 }

	private ListNode reverseList(ListNode head) {
		if(head == null || head.next == null) {
			return head;
		}
		
		ListNode retHead = null;
		
		while(head != null) {
			if(retHead == null) {
				retHead = head;
				head = head.next;
				retHead.next = null;
			}
			else {
				ListNode tempHead = head;
				head = head.next;
				tempHead.next = retHead;
				retHead = tempHead;
			}
		}
		
		return retHead;
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
}
