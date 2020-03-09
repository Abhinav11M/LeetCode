package educative.fastandslowpointers;

public class ListNode {
	public int value = 0;
	public ListNode next;

	public ListNode(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ListNode [value=" + value + ", next=" + next + "]";
	}

	public static ListNode createLinkedList(int[] arr) {
		ListNode head = null;
		ListNode retHead = null;
		
		for(int i = 0; i < arr.length; ++i) {
			if(head == null) {
				head = new ListNode(arr[i]);
				retHead = head;
			}
			else {
				head.next = new ListNode(arr[i]);
				head = head.next;
			}
		}
		
		return retHead;
	}
	
	public static void printList(ListNode head) {
		while(head != null) {
			System.out.print(head.value + "=>");
			head = head.next;
		}
		System.out.println();
	}
}
