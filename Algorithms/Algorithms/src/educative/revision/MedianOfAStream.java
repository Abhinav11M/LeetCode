package educative.revision;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianOfAStream {
	private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
	private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
	
	public void insertNum(int num) {
		// If maxHeap is empty, insert in maxHeap
		if(maxHeap.isEmpty()) {
			maxHeap.offer(num);
		}
		else {
			if(maxHeap.size() == minHeap.size()) {
				if(num > minHeap.peek())  {
					maxHeap.add(minHeap.poll());
					minHeap.offer(num);
				}
				else {
					maxHeap.offer(num);
				}
			}
			else { // maxHeap size > minHeap size
				if(num < maxHeap.peek()) {
					minHeap.offer(maxHeap.poll());
					maxHeap.offer(num);
				}
				else {
					minHeap.offer(num);
				}
			}
		}
	}
	
	public double findMedian() {
		if(minHeap.isEmpty() && maxHeap.isEmpty()) {
			return 0;
		}
		
		if(minHeap.size() == maxHeap.size()) {
			return (minHeap.peek() + maxHeap.peek())/2.0;
		}
		
		return maxHeap.peek();
	}
}
