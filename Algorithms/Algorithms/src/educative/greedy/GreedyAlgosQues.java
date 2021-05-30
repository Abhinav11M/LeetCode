package educative.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

import educative.greedy.Graph2.Edge;
import lombok.AllArgsConstructor;

public class GreedyAlgosQues {

	/**
	 * Make a Change Machine
	 */
	public ArrayList<Integer> getMinCoins(int amount) {
		int[] coins = { 25, 10, 5, 1 };
		ArrayList<Integer> res = new ArrayList<>();

		for (int coin : coins) {
			int n = amount / coin;
			if (n > 0) {
				amount -= coin * n;
				for (int i = 0; i < n; ++i) {
					res.add(coin);
				}
			}
		}

		return res;
	}

	/**
	 * Connecting 'n' Pipes with Minimum Cost Note : This has to be solved using
	 * minHeap
	 */
	public int minCost(int[] pipes) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();

		for (int elem : pipes) {
			minHeap.add(elem);
		}

		int cost = 0;
		while (!minHeap.isEmpty()) {
			int first = minHeap.poll();
			int second = minHeap.poll();

			int c = first + second;
			cost += c;
			if (!minHeap.isEmpty()) {
				minHeap.offer(c);
			}
		}

		return cost;
	}

	/**
	 * Find the Egyptian Fraction
	 */
	public void printEgyptianFraction(int numerator, int denominator) {
		if (numerator == 1) {
			System.out.println(String.format("%d/%d", numerator, denominator));
			return;
		}

		if (denominator % numerator == 0) {
			System.out.println(String.format("1/%d", denominator / numerator));
			return;
		}

		int ceil = (int) Math.ceil((double) denominator / numerator);
		System.out.println(String.format("1/%d", ceil));
		
		printEgyptianFraction(numerator*ceil-1*denominator, denominator*ceil);
	}
	
	/**
	 * Minimum number of platforms required for a train
	 */
	@AllArgsConstructor
	static class TrainSchedule {
		public int arrival;
		public int departure;
	}

	public int findPlatform(int[] arrival, int[] departure) {
		
		// Create minHeap sorted based on the departure time;
		PriorityQueue<TrainSchedule> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a.departure, b.departure));
		
		int minPlatForms = Integer.MIN_VALUE;

		for(int i = 0; i < arrival.length; ++i) {
			if(minHeap.isEmpty()) {
				minHeap.offer(new TrainSchedule(arrival[0], departure[0]));
			}
			else {
				// keep removing if train left already
				while(!minHeap.isEmpty() && minHeap.peek().departure <= arrival[i]) {
					minHeap.poll();
				}
				minHeap.add(new TrainSchedule(arrival[i], departure[i]));
			}
			
			minPlatForms = Math.max(minPlatForms, minHeap.size());
		}
		
		return minPlatForms;
	}

	/**
	 * Minimum number of platforms required for a train : Method2
	 */
	public int findPlatform2(int[] arrival, int[] departure) { 
		// Sort both arrival and departure
		Arrays.sort(arrival);
		Arrays.sort(departure);
		
		int i = 0, j = 0;
		int platforms = 0;
		int maxPlatforms = Integer.MIN_VALUE;
		
		while(i < arrival.length && j < departure.length) {
			if(arrival[i] <= departure[j]) {
				++platforms;
				++i;
			}
			else {
				--platforms;
				++j;
			}
			
			maxPlatforms = Math.max(maxPlatforms, platforms);
		}
		
		return maxPlatforms;
	}
	
	/**
	 * Help the Police Officers Catch the Thieves!
	 */
	 public int policeThief(char[] arr, int k) {
		 
		 ArrayList<Integer> pIndices = new ArrayList<>();
		 ArrayList<Integer> tIndices = new ArrayList<>();

		 for(int i = 0; i < arr.length; ++i) {
			 if(arr[i] == 'P') {
				 pIndices.add(i);
			 }
			 else {
				 tIndices.add(i);
			 }
		 }
		 
		 int p = 0, t = 0;
		 int catches = 0;
		 
		 while (p < pIndices.size() && t < tIndices.size()) {
			 if(Math.abs(pIndices.get(p)-tIndices.get(t)) <= k) {
				 ++catches;
				 ++p;
				 ++t;
			 }

			 else if(pIndices.get(p) < tIndices.get(t)) {
				 ++p;
			 }

			 else {
				 ++t;
			 }
		 }
		 
		 return catches;
	 }
	 
	 public double fractionalKnapsack(double[] weights, double[] values, double capacity) {
		 
		 TreeMap<Double, Integer> ratiosIndices = new TreeMap<>(Comparator.reverseOrder());
		 for(int i = 0; i < weights.length; ++i) {
			 ratiosIndices.put(values[i]/weights[i], i);
		 }
		 
		 double knapsack = 0.0;
		 Collection<Integer> indices = ratiosIndices.values();

		 for (int index : indices) {
			 if(weights[index] >= capacity) {
				 knapsack += (values[index]/weights[index])*capacity;
				 break;
			 }
			 else {
				 knapsack += values[index];
			 }
			 capacity -= weights[index];
		 }
		 
		 return knapsack;
	 }
	 
	 /**
	  * Find the largest number
	  */
	 public void findLargestNumber(int number_of_digits, int sum_of_digits ) {
		 int num = 0;
		 
		 while(sum_of_digits > 9 && number_of_digits > 0) {
			 num = num*10+9;
			 --number_of_digits;
			 sum_of_digits -= 9;
		 }
		 
		 if(number_of_digits > 0 && sum_of_digits > 0) {
			 num = num*10 + sum_of_digits;
			 sum_of_digits = 0;
			 --number_of_digits;
		 }
		 
		 while(number_of_digits > 0) {
			 num *= 10;
			 --number_of_digits;
		 }
		 
		 if(sum_of_digits > 0) {
			 System.out.println("None");
		 }
		 else {
			 System.out.println(num);
		 }
	 }
	 
	 /**
	  * Color the graph
	  */
	 public void greedyColoring(Graph g) {
		 int vertices = g.getVertices();
		 
		 // Colors
		 boolean available[] = new boolean[vertices];
		 Arrays.fill(available, true);
		 
		 HashMap<Integer, Integer> colorMap = new HashMap<>();
		 
		 for(int curVertex = 0; curVertex < vertices; ++curVertex) {
			 // Get the adjacency list
			 LinkedList<Integer> adjList = g.getAdjList()[curVertex];
			 for(int adjVertex : adjList) {
				 if(colorMap.containsKey(adjVertex)) { // Adjacent vertex colored
					 available[colorMap.get(adjVertex)] = false;
				 }
			 }
			 
			 // Find the first available color and fill the current vertex
			 for(int i = 0; i < vertices; ++i) {
				 if(available[i] == true) {
					 colorMap.put(curVertex, i);
					 break;
				 }
			 }
			 // Make all the colors available again
			 Arrays.fill(available, true);
		 }
		 
		 System.out.println(colorMap);
	 }
	 
	 /**
	  * @apiNote Prim's algorithm to find the minimum spanning tree
	  * @param g Input graph
	  */
	 public void primsAlgo(int[][] g) {
		 int v = g.length; // number of vertices
		 
		 int[] parent = new int[v]; // parent is to track the mst path.
		 
		 // This tracks which vertex has already been traversed.
		 boolean[] traversed = new boolean[v];
		 
		 // This will contain the min cost of all vertices from the vertex being traversed.
		 // If the current vertex has a higher cost to go a vertex compared to the previous vertex,
		 // then we don't update.
		 int[] key = new int[v];
		 for(int i = 0; i < v; ++i) {
			 key[i] = Integer.MAX_VALUE;
		 }
		 
		 // To start, we need to pick one vertex. So we pick the first vertex.
		 key[0] = 0; // cost to traverse from 0 to 0 is 0.
		 
		 parent[0] = -1; // There is no parent for this vertex.
		 
		 for(int i = 0; i < v; ++i) {
			 int u = getMinIdx(key, traversed); // get the next vertex that should be traversed next.
			 traversed[u] = true; // since u should be traversed as it has the min cost
			 
			 // Update the key and the parent
			 for(int j = 0; j < v; ++j) {
				 // If j is not traversed and path exists from i->j and key[j] < graph[u][j] (less cost)
				 // then update key and parent
				 if(!traversed[j] && g[u][j] != 0 && key[j] > g[u][j]) {
					 parent[j] = u; // update parent
					 key[j] = g[u][j]; // update key
				 }
			 }
		 }
		 
		 printMST(parent, g);
	 }

	private void printMST(int[] parent, int[][] g) {
		System.out.println("Edge    Weight");
		for(int i = 1; i < parent.length; ++i) {
			System.out.println(parent[i] + "->" + i + " = " + g[parent[i]][i]);
		}
	}

	private int getMinIdx(int[] key, boolean[] traversed) {
		int min = Integer.MAX_VALUE;
		int minIdx = 0;
		
		for(int i = 1; i < key.length; ++i) {
			if(!traversed[i] && key[i] < min) {
				min = key[i];
				minIdx = i;
			}
		}
		
		return minIdx;
	}
	
	 /**
	  * @apiNote Kruskal's algorithm to find the minimum spanning tree
	  * @param g Input graph
	  */
	public void KruskalMST(Graph2 g) {
		// Get all the graph edges
		List<Edge> edges = g.getEdges();
		
		// Sort based on the weights
		Collections.sort(edges, (a,b) -> Integer.compare(a.weight, b.weight));

		int nVertices = g.getNVertices();
		
		List<Edge> result = new ArrayList<>();
		
		// Create disjoint sets for each vertex
		DisjointSet ds = new DisjointSet();
		for(int i = 0; i < nVertices; ++i) {
			ds.makeSet(i);
		}
		
		int i = 0;
		// MST will always have n-1 edges
		while(result.size() < nVertices-1) {
			// find the parents of the disjoint sets containing start and end
			int parent1 = ds.findSet(edges.get(i).start).value;
			int parent2 = ds.findSet(edges.get(i).end).value;

			// If both the edges are in the same disjoint set, we don't add that edge 
			// as it will create a cycle
			if(parent1 != parent2) { 
				result.add(edges.get(i));
				ds.union(parent1, parent2);
			}
			++i;
		}
		
		result.forEach(System.out::println);
	}
	
}











