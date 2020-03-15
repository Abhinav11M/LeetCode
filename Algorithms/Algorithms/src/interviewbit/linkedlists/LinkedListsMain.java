package interviewbit.linkedlists;

import educative.fastandslowpointers.ListNode;

public class LinkedListsMain {
	public static void main(String[] args) {
		
		LinkedLists ll = new LinkedLists();

		ListNode head = ListNode.createLinkedList(new int[] {1,2,3,2,1});
		System.out.println(ll.isPelindrome(head));	
		head = ListNode.createLinkedList(new int[] {1,2,2,1});
		System.out.println(ll.isPelindrome(head));	
		head = ListNode.createLinkedList(new int[] {1,2,1,2});
		System.out.println(ll.isPelindrome(head));	
		head = ListNode.createLinkedList(new int[] {1,8});
		System.out.println(ll.isPelindrome(head));	
		
//		head = ListNode.createLinkedList(new int[] {1,2,3,3,4,4,5});
//		ll.removeDuplicates(head);
//		ListNode.printList(ll.removeDuplicates2(head));
		head = ListNode.createLinkedList(new int[] {1,1,2,2,3,3,4,4,5,5});
		head = ll.removeDuplicates2(head);
		
		ListNode head1 = ListNode.createLinkedList(new int[] {5,8,20});
		ListNode head2 = ListNode.createLinkedList(new int[] {4,11,15});
		
		head1 = ListNode.createLinkedList(new int[] {1});
		head2 = ListNode.createLinkedList(new int[] {1});

		ListNode.printList(ll.mergeTwoLists(head1, head2));
		
		head = ListNode.createLinkedList(new int[] {1,2,3,4,5});
		ListNode.printList(ll.rotateRight(head, 2));
		
		head = ListNode.createLinkedList(new int[] {1,2,3,4});
		ListNode.printList(ll.reorderList(head));
		
		ListNode.printList(
				ll.addNumbers(
						ListNode.createLinkedList(new int[] {1,2,3}),
						ListNode.createLinkedList(new int[] {4,5,6})
						)
				);
		
		ListNode.printList(
				ll.addNumbers(
						ListNode.createLinkedList(new int[] {1,}),
						ListNode.createLinkedList(new int[] {9,9,9})
						)
				);
		
		ListNode.printList(
				ll.reverseAlternatingKElements(
						ListNode.createLinkedList(new int[] {1,2,3,4,5,6,7,8}), 3)
				);

		System.out.println();
	}
	
}
