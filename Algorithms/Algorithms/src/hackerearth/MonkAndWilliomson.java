package hackerearth;

import java.util.HashMap;
import java.util.PriorityQueue;

class Runs {
	public int score;
	public int count;
	
	public Runs(int run) {
		this.score = run;
		this.count = 1;
	}
}

public class MonkAndWilliomson {
	private PriorityQueue<Runs> heap1 = new PriorityQueue<>((a,b) -> b.score - a.score);
	private PriorityQueue<Runs> heap2 = new PriorityQueue<>((a,b) -> b.score - a.score);
	private HashMap<Integer, Integer> map = new HashMap<>();
	
	public void push(int score) {
		
		if(heap1.isEmpty() && heap2.isEmpty()) {
			heap1.offer(new Runs(score));
		}
		else if(heap1.isEmpty()) {
			if(map.containsKey(score)) {
				while(!heap2.isEmpty()) {
					Runs runs = heap2.poll();
					if(runs.score == score) {
						++runs.count;
					}
					heap1.offer(runs);
				}
			}
			else {
				heap2.offer(new Runs(score));
			}
		}
		else { // heap2 is empty
			if(map.containsKey(score)) {
				while(!heap1.isEmpty()) {
					Runs runs = heap1.poll();
					if(runs.score == score) {
						++runs.count;
					}
					heap2.offer(runs);
				}
			}
			else {
				heap1.offer(new Runs(score));
			}
		}
		
		map.put(score, map.getOrDefault(score, 0)+1);
	}
	
	public int diff() {
		if(heap1.isEmpty() && heap2.isEmpty()) {
			return -1;
		}
		
		int diff = 0;
		
		if(heap1.isEmpty()) {
			Runs maxRuns = heap2.poll();
			int maxScore = maxRuns.score;
			int minScore = maxScore;
			--maxRuns.count;
			
			if(maxRuns.count > 0) {
				heap1.offer(maxRuns);
			}
			
			if(heap2.size() == 0) { // Same max and min
				return 0;
			}
			
			Runs last = null;
			while(!heap2.isEmpty()) {
				last = heap2.poll();
				if(heap2.isEmpty()) {
					minScore = last.score;
					--last.count;
					if(last.count > 0) {
						heap1.offer(last);
					}
				}
				else {
					heap1.offer(last);
				}
			}
			diff = maxScore - minScore;
		}
		else { // Heap2 is empty
			Runs maxRuns = heap1.poll();
			int maxScore = maxRuns.score;
			int minScore = maxScore;
			--maxRuns.count;
			
			if(maxRuns.count > 0) {
				heap2.offer(maxRuns);
			}
			
			if(heap1.size() == 0) { // Same max and min
				return 0;
			}
			
			Runs last = null;
			while(!heap1.isEmpty()) {
				last = heap1.poll();
				if(heap1.isEmpty()) {
					minScore = last.score;
					--last.count;
					if(last.count > 0) {
						heap2.add(last);
					}
				}
				else {
					heap2.offer(last);
				}
			}
			diff = maxScore - minScore;
		}
		
		return diff;
	}
	
	public int countHigh() {
		if(heap1.isEmpty() && heap2.isEmpty()) {
			return -1;
		}
		if(heap1.isEmpty()) {
			return heap2.peek().count;
		}
		else {
			return heap1.peek().count;
		}
	}
	
	public int countLow() {
		if(heap1.isEmpty() && heap2.isEmpty()) {
			return -1;
		}
		
		Runs last = null;
		if(heap1.isEmpty()) {
			while(!heap2.isEmpty()) {
				last = heap2.poll();
				heap1.offer(last);
			}
		}
		else {
			while(!heap1.isEmpty()) {
				last = heap1.poll();
				heap2.offer(last);
			}
		}
		return last.count;
	}
}
