package educative.topologicalsort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class TopologicalSort {
	/**
	 * Given a directed graph, find the topological ordering of its vertices.
	 */
	public List<Integer> topologicalOrder(int vertices, int[][] edges) {
		List<Integer> result = new ArrayList<>();

		Map<Integer, List<Integer>> graph = new HashMap<>();
		Map<Integer, Integer> freqMap = new HashMap<>();

		// Initialize
		for (int i = 0; i < vertices; ++i) {
			graph.put(i, new ArrayList<>());
			freqMap.put(i, 0);
		}

		for (int i = 0; i < edges.length; ++i) {
			graph.get(edges[i][0]).add(edges[i][1]);
			freqMap.put(edges[i][1], freqMap.get(edges[i][1]) + 1);
		}

		List<Integer> sources = freqMap.entrySet().stream().filter(x -> x.getValue() == 0).map(y -> y.getKey())
				.collect(Collectors.toList());

		Queue<Integer> sourceQueue = new LinkedList<>(sources);

		while (!sourceQueue.isEmpty()) {
			int source = sourceQueue.poll();
			result.add(source);
			for (int elem : graph.get(source)) {
				if (freqMap.get(elem) == 1) {
					sourceQueue.offer(elem);
					freqMap.remove(elem);
				} else {
					freqMap.put(elem, freqMap.get(elem) - 1);
				}
			}
		}

		// If length is not same, then there is a cycle
		return result.size() == vertices ? result : new ArrayList<>();
	}

	/**
	 * Tasks Scheduling (medium) There are ‘N’ tasks, labeled from ‘0’ to ‘N-1’.
	 * Each task can have some prerequisite tasks which need to be completed before
	 * it can be scheduled. Given the number of tasks and a list of prerequisite
	 * pairs, find out if it is possible to schedule all the tasks.
	 */
	public boolean isSchedulingPossible(int tasks, int[][] prerequisites) {
		// Jobs can be only scheduled if there is a topological sequence possible
		Map<Integer, List<Integer>> graph = new HashMap<>();
		Map<Integer, Integer> freqMap = new HashMap<>();

		// Initialize
		for (int i = 0; i < tasks; ++i) {
			graph.put(i, new ArrayList<>());
			freqMap.put(i, 0);
		}

		for (int i = 0; i < prerequisites.length; ++i) {
			graph.get(prerequisites[i][0]).add(prerequisites[i][1]);
			freqMap.put(prerequisites[i][1], freqMap.get(prerequisites[i][1]) + 1);
		}

		List<Integer> sources = freqMap.entrySet().stream().filter(x -> x.getValue() == 0).map(y -> y.getKey())
				.collect(Collectors.toList());

		Queue<Integer> sourcesQueue = new LinkedList<>(sources);

		List<Integer> taskSequence = new ArrayList<>();

		while (!sourcesQueue.isEmpty()) {
			Integer task = sourcesQueue.poll();
			taskSequence.add(task);
			for (int elem : graph.get(task)) {
				if (freqMap.get(elem) == 1) {
					sourcesQueue.offer(elem);
					freqMap.remove(elem);
				} else {
					freqMap.put(elem, freqMap.get(elem) - 1);
				}
			}
		}

		return taskSequence.size() == tasks;
	}

	/**
	 * Tasks Scheduling Order
	 */
	public List<Integer> findOrder(int tasks, int[][] prerequisites) {
		Map<Integer, List<Integer>> graph = new HashMap<>();
		Map<Integer, Integer> freqMap = new HashMap<>();

		// Initialize
		for (int i = 0; i < tasks; ++i) {
			graph.put(i, new ArrayList<>());
			freqMap.put(i, 0);
		}

		// Populate graph and frequency map.
		for (int i = 0; i < prerequisites.length; ++i) {
			graph.get(prerequisites[i][0]).add(prerequisites[i][1]);
			freqMap.put(prerequisites[i][1], freqMap.get(prerequisites[i][1]) + 1);
		}

		List<Integer> sources = freqMap.entrySet().stream().filter(x -> x.getValue() == 0).map(x -> x.getKey())
				.collect(Collectors.toList());

		Queue<Integer> sourceQueue = new LinkedList<>(sources);
		List<Integer> scheduledTasks = new ArrayList<>();

		while (!sourceQueue.isEmpty()) {
			int task = sourceQueue.poll();
			scheduledTasks.add(task);

			for (int elem : graph.get(task)) {
				freqMap.put(elem, freqMap.get(elem) - 1);

				if (freqMap.get(elem) == 0) {
					sourceQueue.add(elem);
					freqMap.remove(elem);
				}
			}
		}

		return scheduledTasks.size() == tasks ? scheduledTasks : new ArrayList<>();
	}

	/**
	 * All Tasks Scheduling Orders
	 */
	public List<List<Integer>> allSchedulingOrders(int tasks, int[][] prerequisites) {

		Map<Integer, List<Integer>> graph = new HashMap<>();
		Map<Integer, Integer> freqMap = new HashMap<>();

		// Initialize
		for (int i = 0; i < tasks; ++i) {
			graph.put(i, new ArrayList<>());
			freqMap.put(i, 0);
		}

		// Populate contents
		for (int i = 0; i < prerequisites.length; ++i) {
			graph.get(prerequisites[i][0]).add(prerequisites[i][1]);
			freqMap.put(prerequisites[i][1], freqMap.get(prerequisites[i][1]) + 1);
		}

		// Find all sources
		LinkedList<Integer> sources = freqMap.entrySet().stream().filter(x -> x.getValue() == 0).map(y -> y.getKey())
				.collect(Collectors.toCollection(LinkedList::new));
		
		Queue<Integer> sourceQueue = new LinkedList<>(sources);

		List<List<Integer>> result = new ArrayList<>();

		allSchedulingOrders(graph, freqMap, sourceQueue, result, new ArrayList<>(), tasks);
		
		return result;
	}

	private void allSchedulingOrders(Map<Integer, List<Integer>> graph, Map<Integer, Integer> freqMap, Queue<Integer> sourceQueue,
			List<List<Integer>> result, ArrayList<Integer> sequence, int tasks) {
		
		if(sourceQueue.isEmpty() && sequence.size() == tasks) {
			result.add(new ArrayList<>(sequence));
			return;
		}
		
		for(int vertex : sourceQueue) {
			sequence.add(vertex);
			Queue<Integer> queueForNextRec = new LinkedList<>(sourceQueue);
			queueForNextRec.remove(vertex);
			
			List<Integer> children = graph.get(vertex);
			
			for(int elem : children) {
				freqMap.put(elem, freqMap.get(elem)-1);
				if(freqMap.get(elem) == 0) {
					queueForNextRec.add(elem);
				}
			}
			
			allSchedulingOrders(graph, freqMap, queueForNextRec, result, sequence, tasks);

			sequence.remove(sequence.size()-1);
			for(int elem : children) {
				freqMap.put(elem, freqMap.get(elem)+1);
			}
		}
	}
	
	/**
	 * Alien Dictionary
	 */
	public String findOrder(String[] words) {
		
		Map<Character, List<Character>> graph = new HashMap<>();
		Map<Character, Integer> freqMap = new HashMap<>();
		
		// Initialize
		for(String word: words) {
			for(char ch : word.toCharArray()) {
				graph.put(ch, new ArrayList<>());
				freqMap.put(ch, 0);
			}
		}
		
		// Build the graph
		for(int i = 0; i < words.length-1; ++i) {
			String parent = words[i];
			String child = words[i+1];
			
			for(int j = 0; j < Math.min(parent.length(), child.length()); ++j) {
				if(parent.charAt(j) != child.charAt(j)) {
					graph.get(parent.charAt(j)).add(child.charAt(j));
					freqMap.put(child.charAt(j), freqMap.get(child.charAt(j))+1);
					break;
				}
			}
		}
		
		// Filter sources
		List<Character> sources = freqMap.entrySet().stream().filter(x -> x.getValue() == 0).map(x -> x.getKey())
				.collect(Collectors.toList());
		
		Queue<Character> sourceQueue = new LinkedList<>(sources);
		
		StringBuilder res = new StringBuilder();
		while(!sourceQueue.isEmpty()) {
			Character source = sourceQueue.poll();
			res.append(source);
			
			for(Character child : graph.get(source)) {
				freqMap.put(child, freqMap.get(child)-1);
				
				if(freqMap.get(child) == 0) {
					sourceQueue.offer(child);
				}
			}
		}
		
		return res.toString().length() == freqMap.keySet().size() ? res.toString() : "";
	}
}
