package educative.topkelements;

import java.util.PriorityQueue;

public class KthLargestNumberInStream {
	
	public KthLargestNumberInStream(int[] nums, int k) {
		// Create a heap with K largest elements
		int i = 0;
		for(; i < k; ++i) {
			minHeap.add(nums[i]);
		}
		
		for(; i < nums.length; ++i) {
			add(nums[i]);
		}
	}
	
	public int add(int val) {
		if(minHeap.peek() < val) {
			minHeap.poll();
			minHeap.add(val);
		}
		
		return minHeap.peek();
	}

	private PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a, b));
}
