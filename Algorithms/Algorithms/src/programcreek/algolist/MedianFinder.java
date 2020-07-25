package programcreek.algolist;

import java.util.Collections;
import java.util.PriorityQueue;

class MedianFinder {

	private PriorityQueue<Integer> minHeap;
	private PriorityQueue<Integer> maxHeap;

	public MedianFinder() {
		minHeap = new PriorityQueue<>();
		maxHeap = new PriorityQueue<>(Collections.reverseOrder());
	}

	public void addNum(int num) {
		if (maxHeap.isEmpty()) {
			maxHeap.offer(num);
		} 
		else {
			if (maxHeap.peek() < num) {
				minHeap.add(num);
			} 
			else {
				maxHeap.add(num);
			}
		}
		
		// balance
		if (maxHeap.size() > minHeap.size() + 1) {
			minHeap.offer(maxHeap.poll());
		}
		else if (minHeap.size() > maxHeap.size()) {
			maxHeap.offer(minHeap.poll());
		}
	}

	public double findMedian() {
		if (maxHeap.isEmpty() && minHeap.isEmpty()) {
			return 0.0;
		} 
		else {
			if (maxHeap.size() > minHeap.size()) {
				return maxHeap.peek();
			}
			else {
				return (maxHeap.peek() + minHeap.peek()) / 2.0;
			}
		} 
	}
}