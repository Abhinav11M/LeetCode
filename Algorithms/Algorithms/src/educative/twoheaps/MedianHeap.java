package educative.twoheaps;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianHeap {
	
	public void add(int num) {
		// Insert the first value in max Heap
		if(minHeap.isEmpty() && maxHeap.isEmpty()) {
			maxHeap.offer(num);
		}
		else {
			if(num > maxHeap.peek()) { // Should be inserted into minHeap
				if(minHeap.size() > maxHeap.size()) {
					maxHeap.offer(minHeap.poll());
				}
				minHeap.offer(num);
			}
			else { // Should be inserted into maxHeap
				if(maxHeap.size() > minHeap.size()) {
					minHeap.offer(maxHeap.poll());
				}
				maxHeap.offer(num);
			}
		}
	}
	
	void remove(int elem) {
		if(elem > maxHeap.peek()) {
			minHeap.remove(elem);
		}
		else {
			maxHeap.remove(elem);
		}
		
		// Rebalance the heaps
		if(maxHeap.size() < minHeap.size()) {
			maxHeap.offer(minHeap.poll());
		}
		else if(minHeap.size() < maxHeap.size()-1) {
			minHeap.offer(maxHeap.poll());
		}
	}
	
	public double getMedian() {
		if(minHeap.size() == maxHeap.size()) {
			return (minHeap.peek() + maxHeap.peek())/2.0;
		}
		if(minHeap.size() > maxHeap.size()) {
			return minHeap.peek();
		}
		else {
			return maxHeap.peek();
		}
	}
	
	private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
	private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
}
