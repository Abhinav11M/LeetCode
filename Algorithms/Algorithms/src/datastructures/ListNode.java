package datastructures;

public class ListNode {
	@Override
	public String toString() {
		return "ListNode [val=" + val + ", next=" + next + "]";
	}

	public int val;
	public ListNode next;

	public ListNode(int x) {
		val = x;
		next = null;
	}
	
	public ListNode(int x, ListNode n) {
		val = x;
		next = n;
	}
	
	public static ListNode getLinkedList(int[] values) {
		ListNode head = null;
		ListNode curr = null;
		
		for(int v : values) {
			if(head == null) {
				head = new ListNode(v);
				curr = head;
			}
			else {
				curr.next = new ListNode(v);
				curr = curr.next;
			}
		}
		
		return head;
	}
}
