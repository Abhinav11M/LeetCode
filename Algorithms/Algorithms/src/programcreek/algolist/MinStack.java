package programcreek.algolist;

public class MinStack {
	private Element head = null;

	public void push(int val) {
		if(head == null) {
			head = new Element(val, val);
		}
		else { // Add to the head of the stack
			Element elem = new Element(val, Math.min(val, head.min));
			elem.next = head;
			head = elem;
		}
	}
	
	public int pop() {
		if(head == null) {
			return -1;
		}
		
		int retVal = head.val;
		head = head.next;
		return retVal;
	}
	
	public int top() {
		if(head == null) {
			return -1;
		}
		else {
			return head.val;
		}
	}
	
	public int getMin() {
		if(head == null) {
			return -1;
		}

		return head.min;
	}
}
