package educative.revision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class SlidingWindowMedian {
	private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
	private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

	public double[] findSlidingWindowMedian(int[] nums, int k) {
		List<Double> res = new ArrayList<>();
		int left = 0;
		for(int i = 0; i < nums.length; ++i) {
			int val = nums[i];
			
			if(maxHeap.isEmpty()) {
				maxHeap.add(val);
			}
			else {
				if(maxHeap.size() == minHeap.size()) {
					if(val > minHeap.peek()) {
						maxHeap.offer(minHeap.poll());
						minHeap.offer(val);
					}
					else {
						maxHeap.offer(val);
					}
				}
				else {
					if(val < maxHeap.peek()) {
						minHeap.offer(maxHeap.poll());
						maxHeap.offer(val);
					}
					else {
						minHeap.offer(val);
					}
				}
			}
			
			// Shrink the window
			if(i >= k-1) {
				res.add(getMedian());
				int valAtIndex = nums[left];
				if(valAtIndex > maxHeap.peek()) {
					minHeap.remove(valAtIndex);
				}
				else {
					maxHeap.remove(valAtIndex);
				}
				++left;
			}
		}

		return res.stream().mapToDouble(x -> x).toArray();
	}

	private double getMedian() {
		if(maxHeap.size() > minHeap.size()) {
			return maxHeap.peek();
		}
		
		return (minHeap.peek() + maxHeap.peek())/2.0;
	}
}
