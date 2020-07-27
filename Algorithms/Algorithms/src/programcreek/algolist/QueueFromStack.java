package programcreek.algolist;

import java.util.Stack;

public class QueueFromStack {
	
	private Stack<Integer> stack1;
	private Stack<Integer> stack2;

    public QueueFromStack() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
    	stack1.add(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(stack1.isEmpty()) {
        	return -1;
        }
        
        while(!stack1.isEmpty()) {
        	stack2.add(stack1.pop());
        }
        int retVal = stack2.pop();
        while(!stack2.isEmpty()) {
        	stack1.add(stack2.pop());
        }
        
        return retVal;
    }
    
    /** Get the front element. */
    public int peek() {
    	if(stack1.isEmpty()) {
        	return -1;
        }
        
    	while(!stack1.isEmpty()) {
    		stack2.add(stack1.pop());
    	}
    	int retVal = stack2.peek();
    	while(!stack2.isEmpty()) {
    		stack1.add(stack2.pop());
    	}
    	
    	return retVal;
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.isEmpty();
    }
}
