package educative.linkedlists;

import educative.fastandslowpointers.ListNode;

public class LinkedListsMain {
	public static void main(String[] args) {
		ListNode head = ListNode.createLinkedList(new int[] {2,4,6,8,10});
		LinkedLists ll = new LinkedLists();
		ListNode.printList(ll.reverseList(head));
		ListNode.printList(head);
		ListNode.printList(ll.reverseSubList(head, 2,4));
		head = ListNode.createLinkedList(new int[] {1,2});
		ListNode.printList(ll.reverseSubList(head, 1,2));
		head = ListNode.createLinkedList(new int[] {1,2,3,4,5,6,7,8});
		ListNode.printList(ll.reverseEveryKElements(head, 3));
		
		ListNode.printList(
				ll.reverseAlternatingKElements(
						ListNode.createLinkedList(new int[] {1,2,3,4,5,6,7,8}), 3)
				);
	}
}
