package educative.topkelements;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CustomStack {
	public void push(int num) {
		freqMap.put(num, freqMap.getOrDefault(num, 0)+1);
		// Add this to heap
		maxHeap.offer(new Element(num, freqMap.get(num), ++sequence));
	}
	
	public int pop() {
		if(maxHeap.isEmpty()) {
			return -1;
		}
		
		int value = maxHeap.poll().value;
		if(freqMap.get(value) > 1) {
			freqMap.put(value, freqMap.get(value)-1);
		}
		else {
			freqMap.remove(value);
		}
		
		return value;
	}
	
	private int sequence = 0;
	private Map<Integer, Integer> freqMap = new HashMap<>();
	private PriorityQueue<Element> maxHeap = new PriorityQueue<>();
}
