package programcreek.algolist;

import java.util.LinkedList;
import java.util.Queue;

public class MovingAverage {

	private int maxSize;
	private double average;
	private Queue<Integer> queue;
	
	MovingAverage(int size) {
		this.maxSize = size;
		this.queue = new LinkedList<>();
		this.average = 0.0;
	}
	
	public double next(int val) {
		
		if(queue.isEmpty()) {
			queue.add(val);
			average = val;
			return val;
		}

		if(queue.size() < maxSize) {
			average = (average*queue.size() + val)/(queue.size()+1);
			queue.offer(val);
		}
		else {
			int head = queue.poll();
			average = average + (val-head)/maxSize;
			queue.offer(val);
		}
		
		return average;
	}
	
}
