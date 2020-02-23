package educative.fastandslowpointers;

class ListNode {
	int value = 0;
	ListNode next;

	ListNode(int value) {
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
}
