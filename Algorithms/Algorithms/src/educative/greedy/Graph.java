package educative.greedy;

import java.util.LinkedList;

public class Graph {
	@SuppressWarnings("unchecked")
	public Graph(int vertices) {
		this.vertices = vertices;
		this.adjacencyList = new LinkedList[vertices];
		for(int i = 0; i < this.vertices; ++i) {
			this.adjacencyList[i] = new LinkedList<>();
		}
	}
	
	public void addEdge(int source, int destination) {
		adjacencyList[source].add(destination);
		adjacencyList[destination].add(source);
	}
	
	public int getVertices() {
		return this.vertices;
	}
	
	public LinkedList<Integer>[] getAdjList() {
		return this.adjacencyList;
	}
	
	private int vertices; // Number of vertices
	private LinkedList<Integer>[] adjacencyList; // Adjacency Lists (Array of linked list)
}
