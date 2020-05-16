package hackerearth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class MonkAndWilliomson1 {
	private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
	private PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // Will always have one element
	private HashMap<Integer, Integer> map = new HashMap<>();

	public void push(int score) {

		if (maxHeap.isEmpty() && minHeap.isEmpty()) {
			maxHeap.offer(score);
			map.put(score, 1);
		} 
		else if(map.containsKey(score)) {
			map.put(score, map.get(score)+1);
		}
		else {
			maxHeap.offer(score);
			map.put(score, 1);
			if (minHeap.isEmpty()) {
				// move the last element from here to minHeap
				moveToMinHeap();
			} 
		}
	}

	private void moveToMinHeap() {
		List<Integer> list = new ArrayList<>();

		while (!maxHeap.isEmpty()) {
			list.add(maxHeap.poll());
		}

		minHeap.add(list.get(list.size() - 1));
		list.remove(list.size() - 1);
		
		maxHeap.addAll(list);
	}

	public int diff() {
		if (maxHeap.isEmpty() && minHeap.isEmpty()) {
			return -1;
		}
		
		if(minHeap.isEmpty()) {
			int max = maxHeap.peek();
			if(map.get(max) > 1) {
				map.put(max, map.get(max)-1);
			}
			else {
				maxHeap.poll();
				map.remove(max);
			}
			return 0;
		}
		else {
			int max = maxHeap.peek();
			
			if(map.get(max) > 1) {
				map.put(max, map.get(max)-1);
			}
			else {
				maxHeap.poll();
				map.remove(max);
			}
			
			int min = minHeap.peek();
			if(map.get(min) > 1) {
				map.put(min, map.get(min)-1);
			}
			else {
				minHeap.poll();
				map.remove(min);
				// move the last element from maxHeap to minHeap
				if(maxHeap.size() > 1) {
					moveToMinHeap();
				}
			}
			
			return max-min;
		}
	}

	public int countHigh() {
		if (maxHeap.isEmpty() && minHeap.isEmpty()) {
			return -1;
		}
		
		return map.get(maxHeap.peek());
	}

	public int countLow() {
		if (maxHeap.isEmpty() && minHeap.isEmpty()) {
			return -1;
		}

		if(minHeap.isEmpty()) {
			return map.get(maxHeap.peek());
		}
		else {
			return map.get(minHeap.peek());
		}
	}
}
