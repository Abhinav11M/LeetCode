package educative.topkelements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

public class TopKElements {

	/**
	 * Given an unsorted array of numbers, find the ‘K’ largest numbers in it.
	 */
	public List<Integer> findKLargestNumbers(int[] nums, int k) {
//		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a, b));
		
		int i = 0;
		for(i = 0; i < k; ++i) {
			minHeap.add(nums[i]);
		}
		
		for(; i < nums.length; ++i) {
			if(minHeap.peek() < nums[i]) { // Number greater than the smallest heap element
				minHeap.poll();
				minHeap.add(nums[i]);
			}
		}
		
		return new ArrayList<>(minHeap);
	}
	
	/**
	 * Given an unsorted array of numbers, find Kth smallest number in it.
	 */
	public int findKthSmallestNumber(int[] nums, int k) {
		//Create a max heap with k elements
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> Integer.compare(b, a));
		
		int i = 0;
		for(; i < k; ++i) {
			maxHeap.add(nums[i]);
		}
		
		// Iterate over the remaining elements and remove root and add to heap
		// if the number is less than the root.
		
		for(; i < nums.length; ++i) {
			if(maxHeap.peek() > nums[i]) {
				maxHeap.poll();
				maxHeap.add(nums[i]);
			}
		}
		
		return maxHeap.poll();
	}
	
	/**
	 * Given an array of points in the a 2D plane, find ‘K’ closest points to the origin.
	 * Input: points = [[1,2],[1,3]], K = 1
	 * Output: [[1,2]]
	 */
	 public List<Point> findClosestPoints(Point[] points, int k) {
		 PriorityQueue<Point> maxHeap = new PriorityQueue<>((a,b) -> Integer.compare(b.distFromOrigin(), a.distFromOrigin()));
		 
		 int i = 0;
		 for(; i < k; ++i) {
			 maxHeap.add(points[i]);
		 }
		 
		 for(; i < points.length; ++i) {
			 // If point is closer, remove from root and add the new point
			 if(maxHeap.peek().distFromOrigin() > points[i].distFromOrigin()) {
				 maxHeap.poll();
				 maxHeap.add(points[i]);
			 }
		 }
		 
		 return new ArrayList<>(maxHeap);
	 }
	 
	 /**
	  * Given ‘N’ ropes with different lengths, we need to connect these 
	  * ropes into one big rope with minimum cost. The cost of connecting 
	  * two ropes is equal to the sum of their lengths.
	  * Input: [1, 3, 11, 5]
	  * Output: 33
	  * Explanation: First connect 1+3(=4), then 4+5(=9), and then 9+11(=20).
	  * So the total cost is 33 (4+9+20)
	  */
	 public int minimumCostToConnectRopes(int[] ropeLengths) {
		 PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a, b));
		 
		 int minCost = 0;
		 // Push all the rope lengths into the heap.
		 for(int val : ropeLengths) {
			 minHeap.add(val);
		 }
		 
		 while(minHeap.size() > 1) {
			 int min1 = minHeap.poll();
			 int min2 = minHeap.poll();
			 int joinedRope = min1+min2;
			 minCost += joinedRope;
			 minHeap.add(joinedRope);
		 }
		 
		 return minCost;
	 }

	/**
	 * Given an unsorted array of numbers, find the top ‘K’ frequently occurring numbers in it.
	 */
	 public List<Integer> findTopKFrequentNumbers(int[] nums, int k) {
		 Map<Integer, Integer> freqMap = new HashMap<>();
		 
		 for(int num : nums) {
			 freqMap.put(num, freqMap.getOrDefault(num, 0)+1);
		 }
		 
		 PriorityQueue<Map.Entry<Integer, Integer>> minHeap = 
				 new PriorityQueue<>((a,b) -> Integer.compare(a.getValue(), b.getValue()));
		 
		 // Put first k elements of the HashMap into the min-heap.
		 Iterator<Entry<Integer, Integer>> iter = freqMap.entrySet().iterator();
		 int i = 0;
		 for(; i < k; ++i) {
			 minHeap.add(iter.next());
		 }
		 
		 while(iter.hasNext()) {
			 Entry<Integer, Integer> nextElement = iter.next();
			 if(minHeap.peek().getValue() < nextElement.getValue()) {
				 minHeap.poll();
				 minHeap.add(nextElement);
			 }
		 }
		 
		 return minHeap.stream().map(x -> x.getKey()).collect(Collectors.toList());
	 }

	 public String sortCharacterByFrequency(String str) {
		 Map<Character, Integer> freqMap = new HashMap<>();
		 
		 
		 for(int i = 0; i < str.length(); ++i) {
			 char ch = str.charAt(i);
			 freqMap.put(ch, freqMap.getOrDefault(ch, 0)+1);
		 }
		 
		 PriorityQueue<Map.Entry<Character, Integer>> maxHeap = 
				 new PriorityQueue<>((a,b) -> Integer.compare(b.getValue(), a.getValue()));
		 
		 for(Map.Entry<Character, Integer> val : freqMap.entrySet()) {
			 maxHeap.add(val);
		 }
		 
		 String retStr = "";
		 while(!maxHeap.isEmpty()) {
			 Entry<Character, Integer> charAndFreq = maxHeap.poll();
			 
			 char ch = charAndFreq.getKey();
			 int freq = charAndFreq.getValue();
			 
			 for(int i = 0; i < freq; ++i) {
				 retStr += ch;
			 }
		 }
		 
		 return retStr;
	 }
	 
	 /**
	  * Given a sorted number array and two integers ‘K’ and ‘X’, find ‘K’ 
	  * closest numbers to ‘X’ in the array. 
	  * Return the numbers in the sorted order.
	  * ‘X’ is not necessarily present in the array.
	  */
	 public List<Integer> findClosestElements(int[] arr, int K, Integer X) {
		 
		 PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> Integer.compare(Math.abs(X-b), Math.abs(X-a)));
		 
		 /**
		  * We don't need to push the entire arr. We can binary search the index which is closest to X.
		  * We can then look K towards left and K towards right to find the closest K elements.
		  */
		 
		 int index = binarySearch(arr, X);
		 int left = Math.max(index-K, 0); // Can't go beyond index 0
		 int right = Math.min(index+K, arr.length-1); // Can't go beyond index arr.length-1
		 
		 int i = 0;
		 for(; i < K; ++i) {
			 maxHeap.add(arr[left++]);
		 }
		 
		 for(;left <= right;) {
			 if(Math.abs(X-maxHeap.peek()) > Math.abs(X-arr[left])) {
				 maxHeap.poll();
				 maxHeap.add(arr[left]);
			 }
			 ++left;
		 }
		 
		 List<Integer> res = new ArrayList<>();

		 while(!maxHeap.isEmpty()) {
			 res.add(maxHeap.poll());
		 }
		 
		 Collections.sort(res);
		 
		 return res;
	 }
	 
	 /**
	  * Using two pointers
	  */
	 public List<Integer> findClosestElements1(int[] arr, int K, Integer X) {
		 int index = binarySearch(arr, X);
		 
		 int left = index;
		 int right = index+1;
		 
		 List<Integer> result = new LinkedList<>();

		 for(int i = 0; i < K; ++i) {
			 if(left >= 0 && right < arr.length) {
				 int leftDiff = Math.abs(X-arr[left]);
				 int rightDiff = Math.abs(X-arr[right]);
				 
				 if(leftDiff < rightDiff) {
					 result.add(0, arr[left--]);
				 }
				 else {
					 result.add(arr[right++]);
				 }
			 }
			 else if(left >= 0) {
				 result.add(0, arr[left--]);
			 }
			 else {
				 result.add(arr[right++]);
			 }
		 }
		 
		 return result;
	 }

	 private int binarySearch(int[] arr, Integer x) {
		 int left = 0;
		 int right = arr.length-1;

		 while(right > left) {
			 int mid = (left+right)/2;
			 if(arr[mid] == x) {
				 return mid;
			 }
			 else if(arr[mid] > x) { // Move left
				 right = mid-1;
			 }
			 else {
				 left = mid+1;
			 }
		 }
		 return left;
	 }
	 
	 /**
	  * Given an array of numbers and a number ‘K’, we need to remove ‘K’ numbers
	  * from the array such that we are left with maximum distinct numbers.
	  * Input: [7, 3, 5, 8, 5, 3, 3], and K=2
	  * Output: 3
	  * Explanation: We can remove two occurrences of 3 to be left with 3 distinct numbers [7, 3, 8], we have 
	  */
	 public int findMaximumDistinctElements(int[] nums, int k) {
		 
		 Map<Integer, Integer> freqMap = new HashMap<>();
		 for(int i = 0; i < nums.length; ++i) {
			 freqMap.put(nums[i], freqMap.getOrDefault(nums[i],0)+1);
		 }
		 
		 PriorityQueue<Map.Entry<Integer, Integer>> minHeap = 
				 new PriorityQueue<>((a,b) -> Integer.compare(a.getValue(), b.getValue()));
		 
		 int distictCount = 0;
		 for(Map.Entry<Integer, Integer> val : freqMap.entrySet()) {
			 if(val.getValue() == 1) {
				 ++distictCount;
			 }
			 else {
				 minHeap.add(val);
			 }
		 }
		 
		 // Start sutracting K
		 while(k >= 0 && !minHeap.isEmpty()) {
			 Entry<Integer, Integer> temp = minHeap.poll();
			 if(temp.getValue() > k) {
				 k = 0;
				 break;
			 }
			 else {
				 k -= temp.getValue()-1;
				 ++distictCount;
			 }
		 }
		 
		 while(k > 0) {
			 distictCount--;
			 k--;
		 }
		 
		 return distictCount;
	 }
	 
	 /**
	  * Given an array, find the sum of all numbers between the K1’th and K2’th 
	  * smallest elements of that array.
	  * Input: [1, 3, 12, 5, 15, 11], and K1=3, K2=6
	  * Output: 23
	  * Explanation: The 3rd smallest number is 5 and 6th smallest number 15. 
	  * he sum of numbers coming between 5 and 15 is 23 (11+12).
	  */
	 public int findSumOfElements(int[] nums, int k1, int k2) {
		 // Create a max heap of K1 elements to find out the K1th smallest element
		 PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		 
		 int i = 0;
		 for(; i < k1; ++i) {
			 maxHeap.add(nums[i]);
		 }
		 
		 for(; i < nums.length; ++i) {
			 if(nums[i] < maxHeap.peek()) {
				 maxHeap.poll();
				 maxHeap.add(nums[i]);
			 }
		 }
		 
		 int k1thSmallestNum = maxHeap.poll();
		 
		 int numOfElements = k2-k1-1;
		 maxHeap.clear();
		 
		 i = 0;
		 for(int j = 0; j < nums.length; ++j) {
			 if(nums[j] <= k1thSmallestNum) {
				 continue;
			 }
			 if(i < numOfElements) { // Simply add to the heap
				 maxHeap.add(nums[j]);
				 ++i;
			 }
			 else { // Remove the bigger from head and add a smaller one
				 if(maxHeap.peek() > nums[j]) {
					 maxHeap.poll();
					 maxHeap.add(nums[j]);
				 }
			 }
		 }
		 
		 int sum = 0;
		 while(!maxHeap.isEmpty()) {
			 sum+= maxHeap.poll();
		 }
		 
		 return sum;
	 }
	 
	 /**
	  * Solution 2
	  */
	 public int findSumOfElements1(int[] nums, int k1, int k2) {
		 PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		 
		 // Push all the elements to the heap.
		 for(int i = 0; i < nums.length; ++i) {
			 minHeap.add(nums[i]);
		 }
		 
		 // Remove the first k1 elements
		 for(int i = 0; i < k1; ++i) {
			 minHeap.poll();
		 }
		 
		 // Get the next k2-k2-1 elements to find the required sum
		 int sum = 0;
		 for(int i = 0; i < k2-k1-1; ++i) {
			 sum += minHeap.poll();
		 }
		 
		 return sum;
	 }
	 
	 /**
	  * Given a string, find if its letters can be rearranged in 
	  * such a way that no two same characters come next to each other.
	  * Input: "aappp"
	  * Output: "papap"
	  * Explanation: In "papap", none of the repeating characters come next to each other.
	  */
	 public String rearrangeString(String str) {
		 Map<Character, Integer> freqMap = new HashMap<>();
		 
		 for(int i = 0; i < str.length(); ++i) {
			 char ch = str.charAt(i);
			 freqMap.put(ch, freqMap.getOrDefault(ch, 0)+1);
		 }
		 
		 PriorityQueue<Map.Entry<Character, Integer>> maxHeap = 
				 new PriorityQueue<>((a,b) -> Integer.compare(b.getValue(), a.getValue()));
		 
		 for(Map.Entry<Character, Integer> val : freqMap.entrySet()) {
			 maxHeap.add(val);
		 }
		 
		 StringBuilder res = new StringBuilder();
		 Map.Entry<Character, Integer> prevEntry = null;
		 while(!maxHeap.isEmpty()) {
			 Entry<Character, Integer> currEntry = maxHeap.poll();
			 res.append(currEntry.getKey());
			 
			 if(prevEntry != null && prevEntry.getValue() > 0) {
				 maxHeap.offer(prevEntry);
			 }
			 
			 currEntry.setValue(currEntry.getValue()-1);
			 prevEntry = currEntry;
		 }
		 
		 return res.length() == str.length() ? res.toString() : "";
		 
			 
		 }

	 /**
	  * Rearrange String K Distance Apart
	  * Given a string and a number ‘K’, find if the string can be 
	  * rearranged such that the same characters are at least ‘K’ 
	  * distance apart from each other.
	  * Input: "mmpp", K=2
	  * Output: "mpmp" or "pmpm"
	  * Explanation: All same characters are 2 distance apart.
	  */
	 public String reorganizeString(String str, int k) {
		 // Create max heap of the frequencies
		 Map<Character, Integer> freqMap = new HashMap<>();
		 for(int i = 0; i < str.length(); ++i) {
			 char ch = str.charAt(i);
			 freqMap.put(ch, freqMap.getOrDefault(ch, 0)+1);
		 }
		 
		 PriorityQueue<Map.Entry<Character, Integer>> maxHeap = 
				 new PriorityQueue<>((a,b) -> Integer.compare(b.getValue(), a.getValue()));
		 
		 for(Map.Entry<Character, Integer> val : freqMap.entrySet()) {
			 maxHeap.add(val);
		 }
		 
		 Queue<Map.Entry<Character, Integer>> queue = new LinkedList<>();
		 
		 StringBuilder result = new StringBuilder();
		 while(!maxHeap.isEmpty()) {
			 Entry<Character, Integer> currEntry = maxHeap.poll();
			 if(currEntry.getValue() > 0) {
				 result.append(currEntry.getKey());
			 }
			 currEntry.setValue(currEntry.getValue()-1);
			 
			 if(currEntry.getValue() >= 0) {
				 queue.add(currEntry);
			 }

			 if(queue.size() == k) {
				 maxHeap.offer(queue.poll());
			 }
		 }
		 
		 return result.length() == str.length() ? result.toString() : "";
	 }
	 
	/**
	 * Scheduling Tasks
	 * <p>
	 * You are given a list of tasks that need to be run, 
	 * in any order, on a server. Each task will take one CPU interval 
	 * to execute but once a task has finished, it has a cooling period during 
	 * which it can’t be run again. If the cooling period for all tasks 
	 * is ‘K’ intervals, find the minimum number of CPU intervals that the 
	 * server needs to finish all tasks. If at any time the server can’t 
	 * execute any task then it must stay idle.
	 * </p>
	 * Input: [a, a, a, b, c, c], K=2
	 * Output: 7
	 * Explanation: a -> c -> b -> a -> c -> idle -> a
	 */
	public int scheduleTasks2(char[] tasks, int k) {
		Map<Character, Integer> freqMap = new HashMap<>();
		for (int i = 0; i < tasks.length; ++i) {
			char ch = tasks[i];
			freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
		}

		PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(
				(a, b) -> Integer.compare(b.getValue(), a.getValue()));

		maxHeap.addAll(freqMap.entrySet());

		int count = 0;

		while (!maxHeap.isEmpty()) {
			List<Map.Entry<Character, Integer>> waitingList = new ArrayList<>();
			int n = k + 1; // tasks to be completed
			for (; n > 0 && !maxHeap.isEmpty(); --n) {
				++count;
				Entry<Character, Integer> currentTask = maxHeap.poll();

				if (currentTask.getValue() > 1) {
					currentTask.setValue(currentTask.getValue() - 1);
					waitingList.add(currentTask);
				}
			}

			// Add back all the waiting tasks in the heap
			maxHeap.addAll(waitingList);

			// If there were not enough breaks, n will not be 0;
			if (!maxHeap.isEmpty()) { // If maxHeap is already empty, tasks are completed
				count += n;
			}
		}

		return count;
	}
}
