package educative.twoheaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TwoHeaps {
	/**
	 * Sliding Window Median 
	 */
	public List<Double> slidingWindowMedian(int[] nums, int k) {
		List<Double> res = new ArrayList<>();
		
		MedianHeap medHeap = new MedianHeap();
		int left = 0, right = 0;
		for(; right < k; ++right) {
			medHeap.add(nums[right]);
		}
		
		while(right < nums.length) {
			res.add(medHeap.getMedian());
			medHeap.remove(nums[left++]);
			medHeap.add(nums[right++]);
		}
		
		res.add(medHeap.getMedian()); // Inserting the last value
		
		return res;
	}
	
	/**
	 * Maximize Capital
	 */
	public int findMaximumCapital(int[] capital, int[] profits, int numberOfProjects, int initialCapital) {
		PriorityQueue<ProjectCost> maxHeap = 
				new PriorityQueue<>((a,b) ->Integer.compare(b.projectProfit, a.projectProfit));
		
		for(int i = 0; i < capital.length; ++i) {
			maxHeap.add(new ProjectCost(capital[i], profits[i]));
		}
		
		List<ProjectCost> list = new ArrayList<>();
		
		int count = 0;
		while(count < numberOfProjects) {
			while(maxHeap.peek().projectCapital > initialCapital) {
				list.add(maxHeap.poll());
			}
			// Choose this project
			ProjectCost temp = maxHeap.poll();
			initialCapital = initialCapital + temp.projectProfit;
			++count;
			maxHeap.addAll(list);
			list.clear();
		}
		
		return initialCapital;
	}
	
	/**
	 * 
	 * @param capital => Should always be in increasing consecutive sequence
	 * @param profits
	 * @param numberOfProjects
	 * @param initialCapital
	 * @return
	 */
	public int findMaximumCapital1(int[] capital, int[] profits, int numberOfProjects, int initialCapital) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a, b));
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> Integer.compare(b, a));
		
		// We want the max profit with min capital, so pushing capital to minHeap
		for(int i = 0; i < capital.length; ++i) {
			minHeap.offer(capital[i]);
		}
		
		for(int i = 0; i < numberOfProjects; ++i) {
			while(!minHeap.isEmpty() && minHeap.peek() <= initialCapital) {
				maxHeap.add(minHeap.poll());
			}
			
			initialCapital += profits[maxHeap.poll()];
		}
		
		return initialCapital;
	}
	
	/**
	 * Next Interval (hard) #
	 * Given an array of intervals, find the next interval of each 
	 * interval. In a list of intervals, for an interval ‘i’ its next interval ‘j’ 
	 * will have the smallest ‘start’ greater than or equal to the ‘end’ of ‘i’.
	 * Write a function to return an array containing indices of the next interval
	 *  of each input interval. If there is no next interval of a given interval, 
	 *  return -1. It is given that none of the intervals have the same start point.
	 *  Input: Intervals [[2,3], [3,4], [5,6]]
	 *  Output: [1, 2, -1]
	 *  Explanation: The next interval of [2,3] is [3,4] having index ‘1’. 
	 *  Similarly, the next interval of [3,4] is [5,6] having index ‘2’. 
	 *  There is no next interval for [5,6] hence we have ‘-1’.
	 *  Input: Intervals [[3,4], [1,5], [4,6]]
	 *  Output: [2, -1, -1]
	 *  Explanation: The next interval of [3,4] is [4,6] which has index ‘2’. 
	 *  There is no next interval for [1,5] and [4,6].
	 */
	public int[] findNextInterval(Interval[] intervals) {
		Map<Interval, Integer> indexMap = new HashMap<>();
		
		// Create a min heap based on start time
		PriorityQueue<Interval> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a.start, b.start));
		// Create a max heap based on end time
		PriorityQueue<Interval> maxHeap = new PriorityQueue<>((a,b) -> Integer.compare(b.end, a.end));
		
		for(int i = 0; i < intervals.length; ++i) {
			minHeap.add(intervals[i]);
			maxHeap.add(intervals[i]);
			indexMap.put(intervals[i], i);
		}
		
		List<Interval> list = new ArrayList<>();
		Map<Interval, Integer> res = new HashMap<>();
		
		while(!minHeap.isEmpty()) {
			list.clear();
			Interval interval = minHeap.poll();
			while(maxHeap.peek().start >= interval.end) { // next interval should start after the previous has ended
				list.add(maxHeap.poll());
			}
			if(list.isEmpty()) {
				res.put(interval, -1);
			}
			else {
				res.put(interval, indexMap.get(list.get(list.size()-1))); // Getting the last inserted element
			}
			maxHeap.addAll(list);
		}
		
		int[] resArr = new int[intervals.length];
		
		for(Map.Entry<Interval, Integer> val : res.entrySet()) {
			int index = indexMap.get(val.getKey());
			resArr[index] = val.getValue();
		}
		
		return resArr;
	}
}
