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
}
