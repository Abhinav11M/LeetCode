package educative.fastandslowpointers;

public class FastAndSlowPointers {
	/**
	 * Check if a linked list has a cycle or not.
	 * @param head
	 * @return
	 */
	public boolean hasCycle(ListNode head) {
		if(head == null || head.next == null || head.next.next == null) {
			return false;
		}
		if(head.next.next == head) {
			return true;
		}
		
		ListNode fast = head.next.next;
		ListNode slow = head;
		
		while (fast != slow) {
			slow = slow.next;
			if(fast.next == null || fast.next.next == null) {
				return false;
			}
			fast = fast.next.next;
		}
		
	    return true;
	 }
	
	public boolean hasCycle2(ListNode head) {
		ListNode fast = head;
		ListNode slow = head;
		
		while(fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			
			if(fast == slow) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Given the head of a Singly LinkedList that contains a cycle, 
	 * write a function to find the starting node of the cycle.
	 */
	public ListNode linkedListCycleStart(ListNode head) {
		ListNode fast = head;
		ListNode slow = head;
		
		while(fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			
			if(fast == slow) {
				break;
			}
		}
		
		slow = head;
		while(fast != slow) {
			fast = fast.next;
			slow = slow.next;
		}
		
		return slow;
	}
	
	/**
	 * Happy Number
	 * Any number will be called a happy number if, after repeatedly replacing it with 
	 * a number equal to the sum of the square of all of its digits, leads us to number ‘1’. 
	 * All other (not-happy) numbers will never reach ‘1’. 
	 * Instead, they will be stuck in a cycle of numbers which does not include ‘1’.
	 * Input: 23   
	 * Output: true (23 is a happy number) 
	 * Explanations: Here are the steps to find out that 23 is a happy number:
	 * 2^2 + 3^2 = 13
	 * 1^2 + 3^2 = 10
	 * 1^2 + 0^2 = 1 (Magic number)
	 */
	public boolean happyNumber(int num) {
		int fast = num;
		int slow = num;
		
		while(true) {
			fast = calcSquareOfDigits(calcSquareOfDigits(fast));
			slow = calcSquareOfDigits(slow);

			if(fast == slow) {
				break;
			}
		}
		
		return slow == 1;
	}

	private int calcSquareOfDigits(int num) {
		int sum = 0;
		
		while(num > 0) {
			sum += (num%10) * (num%10);
			num /= 10;
		}
		return sum;
	}
	
	/**
	 * Middle of the LinkedList
	 * Given the head of a Singly LinkedList, write a method to return the middle node of the LinkedList.
	 * If the total number of nodes in the LinkedList is even, return the second middle node.
	 */
	public ListNode findMiddle(ListNode head) {
		ListNode fast = head;
		ListNode slow = head;
		
		while(fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		
		return slow;
	}
	
	/**
	 * Palindrome LinkedList
	 */
	public boolean isPalindromicLinkedList(ListNode head) {
		if(head == null || head.next == null) {
			return true;
		}
		
		// Find the middle
		ListNode fast = head;
		ListNode slow = head;
		ListNode prev = null; // This is the point of reversal
		
		while(fast != null && fast.next != null) {
			fast = fast.next.next;
			prev = slow;
			slow = slow.next;
		}
		
		prev.next = null; // Marked the end of the first list
		
		slow = reverseList(slow); // Reversed the other half of the list
		
		ListNode firstHalf = head; // TempHead for iteration on head
		ListNode secondHalf = slow;
		
		
		boolean retVal = true;
		while(firstHalf != null) {
			if(firstHalf.value != secondHalf.value) {
				// Cannot return from here as the list needs to be recreated
				retVal = false;
				break;
			}
			
			firstHalf = firstHalf.next;
			secondHalf = secondHalf.next;
		}
		
		slow = reverseList(slow); // Reverse back the second half
		
		prev.next = slow; // Join back the second half
		
		return retVal;
	}

	public ListNode reverseList(ListNode head) {
		ListNode newHead = null;
		
		while(head != null) {
			if(newHead == null) {
				newHead = head;
				head = head.next;
				newHead.next = null;
			}
			else {
				ListNode tempHead = head;
				head = head.next;
				tempHead.next = newHead;
				newHead = tempHead;
			}
		}

		return newHead;
	}
	
	/**
	 * Rearrange a LinkedList 
	 * Given the head of a Singly LinkedList, write a method to modify the LinkedList 
	 * such that the nodes from the second half of the LinkedList are inserted 
	 * alternately to the nodes from the first half in reverse order. 
	 * Input: 2 -> 4 -> 6 -> 8 -> 10 -> 12 -> null
	 * Output: 2 -> 12 -> 4 -> 10 -> 6 -> 8 -> null
	 * Input: 2 -> 4 -> 6 -> 8 -> 10 -> null
	 * Output: 2 -> 10 -> 4 -> 8 -> 6 -> null
	 */
	public void reorder(ListNode head) {
		// Find the middle
		ListNode fast = head;
		ListNode slow = head;
		ListNode prev = null;
		
		while(fast != null && fast.next != null) {
			fast = fast.next.next;
			prev = slow; // This will mark the end of the first list
			slow = slow.next;
		}
		
		prev.next = null; // Marks the end of first half.
		// Slow is the beginning of the second half of the list(will have more elements for odd)
		// reverse the second half of the list
		ListNode secondHalf = reverseList(slow);
		ListNode firstHalf = head;
		ListNode newHead = null;
		
		while(firstHalf != null) { // End of the first half
			if(newHead == null) {
				newHead = firstHalf;
				firstHalf = firstHalf.next;
				newHead.next = secondHalf;
				secondHalf = secondHalf.next;
				newHead = newHead.next;
			}
			else {
				newHead.next = firstHalf;
				firstHalf = firstHalf.next;
				newHead.next.next = secondHalf;
				secondHalf = secondHalf.next;
				newHead = newHead.next.next;
			}
		}
		newHead.next = secondHalf;
	}
}
