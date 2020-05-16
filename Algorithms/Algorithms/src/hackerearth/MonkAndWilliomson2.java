package hackerearth;

import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class MonkAndWilliomson2 {
	private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
	private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
	private HashMap<Integer, Integer> map = new HashMap<>();

	public void push(int score) {
		if(map.containsKey(score)) {
			map.put(score, map.get(score)+1);
		}
		else {
			map.put(score, 1);
			maxHeap.add(score);
			minHeap.add(score);
		}
	}

	public int diff() {
		if (maxHeap.isEmpty() && minHeap.isEmpty()) {
			return -1;
		}
		
		while(!map.containsKey(maxHeap.peek())) {
			maxHeap.poll();
		}
		int max = maxHeap.poll();

		while(!map.containsKey(minHeap.peek())) {
			minHeap.poll();
		}
		int min = minHeap.poll();
		
		if(max == min) {
			// Reduce count by 1
			map.put(max, map.get(max)-1);
		}
		else {
			map.put(max, map.get(max)-1);
			map.put(min, map.get(min)-1);
			
			if(map.get(max) == 0) {
				map.remove(max);
			}
			if(map.get(min) == 0) {
				map.remove(min);
			}
		}
		
		return max-min;
	}

	public int countHigh() {
		if (maxHeap.isEmpty()) {
			return -1;
		}
		
		return map.get(maxHeap.peek());
	}

	public int countLow() {
		if (minHeap.isEmpty()) {
			return -1;
		}

		return map.get(minHeap.peek());
	}
}
