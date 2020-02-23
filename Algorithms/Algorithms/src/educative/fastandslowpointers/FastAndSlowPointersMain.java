package educative.fastandslowpointers;

public class FastAndSlowPointersMain {
	public static void main(String[] args) {
		FastAndSlowPointers fAndSPointers = new FastAndSlowPointers();
		
		ListNode head = new ListNode(1);
	    head.next = new ListNode(2);
	    head.next.next = new ListNode(3);
	    head.next.next.next = new ListNode(4);
	    head.next.next.next.next = new ListNode(5);
	    head.next.next.next.next.next = new ListNode(6);

	    head.next.next.next.next.next.next = head.next.next;
	    System.out.println("LinkedList cycle start: " + fAndSPointers.linkedListCycleStart(head).value);

	    head.next.next.next.next.next.next = head.next.next.next;
	    System.out.println("LinkedList cycle start: " + fAndSPointers.linkedListCycleStart(head).value);

	    head.next.next.next.next.next.next = head;
	    System.out.println("LinkedList cycle start: " + fAndSPointers.linkedListCycleStart(head).value);
	    
	    System.out.println(fAndSPointers.happyNumber(23));
	    System.out.println(fAndSPointers.happyNumber(13));
	    System.out.println(fAndSPointers.happyNumber(12));
	    
	    ListNode head1 = new ListNode(2);
	    head1.next = new ListNode(4);
	    head1.next.next = new ListNode(6);
	    head1.next.next.next = new ListNode(4);
	    head1.next.next.next.next = new ListNode(2);
	    System.out.println("Checking pelindrome linked list");
	    
	    System.out.println("Is palindrome: " + fAndSPointers.isPalindromicLinkedList(head1));

	    head1.next.next.next.next.next = new ListNode(2);
	    System.out.println("Is palindrome: " + fAndSPointers.isPalindromicLinkedList(head1));
	    
	    ListNode head2 = new ListNode(1);
	    head2.next = new ListNode(2);
	    head2.next.next = new ListNode(3);
	    head2.next.next.next = new ListNode(4);
	    head2.next.next.next.next = new ListNode(5);
	    
	    fAndSPointers.reorder(head2);
	    
	    ListNode head3 = ListNode.createLinkedList(new int[] {2, 4, 6, 8, 10, 12});
	    fAndSPointers.reorder(head3);
	    
	    System.out.println();
	}
}
