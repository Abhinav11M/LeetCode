package programcreek.algolist;

import java.util.LinkedList;
import java.util.Queue;

public class StackFromQ {
	private Queue<Integer> queue1;
	private Queue<Integer> queue2;
	
	public StackFromQ() {
		queue1 = new LinkedList<>();
		queue2 = new LinkedList<>();
	}
	
	public void push(int val) {
		if(queue2.isEmpty()) {
			queue1.add(val);
		}
		else {
			queue2.add(val);
		}
	}
	
	public int pop() {
		if(queue1.isEmpty() && queue2.isEmpty()) {
			return -1;
		}
		else {
			if(queue1.isEmpty()) {
				int retVal = -1;
				while(!queue2.isEmpty()) {
					retVal = queue2.poll();
					if(!queue2.isEmpty()) {
						queue1.add(retVal);
					}
				}
				return retVal;
			}
			else {
				int retVal = -1;
				while(!queue1.isEmpty()) {
					retVal = queue1.poll();
					if(!queue1.isEmpty()) {
						queue2.add(retVal);
					}
				}
				return retVal;
			}
		}
	}
	
	public int top() {
		if(queue1.isEmpty() && queue2.isEmpty()) {
			return -1;
		}
		else {
			if(queue1.isEmpty()) {
				int retVal = -1;
				while(!queue2.isEmpty()) {
					retVal = queue2.poll();
					queue1.add(retVal);
				}
				return retVal;
			}
			else {
				int retVal = -1;
				while(!queue1.isEmpty()) {
					retVal = queue1.poll();
					queue2.add(retVal);
				}
				return retVal;
			}
		}
	}
	
	public boolean empty() {
		return queue1.isEmpty() && queue2.isEmpty();
	}
}
