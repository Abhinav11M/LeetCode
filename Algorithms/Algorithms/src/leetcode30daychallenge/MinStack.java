package leetcode30daychallenge;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class MinStack {

    /** initialize your data structure here. */
	private int count;
	PriorityQueue<Element> queue;

    public MinStack() {
    	count = 0;
    	queue = new PriorityQueue<Element>((a,b) -> a.value-b.value);
    }
    
    public void push(int x) {
        queue.offer(new Element(count++, x));
    }
    
    public void pop() {
    	if(queue.isEmpty()) {
    		return;
    	}
    	List<Element> list = new ArrayList<>();
    	while(queue.peek().index != count-1) { // get the last element
    		list.add(queue.poll());
    	}

        queue.poll();
        --count;
        queue.addAll(list);
    }
    
    public int top() {
    	if(queue.isEmpty()) {
    		return -1;
    	}
    	List<Element> list = new ArrayList<>();
    	while(queue.peek().index != count-1) { // get the last element
    		list.add(queue.poll());
    	}

        int retVal = queue.peek().value;
        queue.addAll(list);
        
        return retVal;
    }
    
    public int getMin() {
        if(queue.isEmpty()) {
        	return -1;
        }
        return queue.peek().value;
    }
    
    private class Element {
    	public int index;
    	public int value;
    	
    	Element(int index, int value) {
    		this.index = index;
    		this.value = value;
    	}
    }
}
