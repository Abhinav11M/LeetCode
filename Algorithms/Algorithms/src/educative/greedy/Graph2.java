package educative.greedy;

import java.util.ArrayList;
import java.util.List;

import lombok.ToString;

public class Graph2 {

	@ToString
	public static class Edge {
		public Edge(int s, int e, int w) {
			this.start = s;
			this.end = e;
			this.weight = w;
		}
		
		public int start;
		public int end;
		public int weight;
	}
	
	public Graph2(int v) {
		this.vertices = v;
		edges = new ArrayList<>();
	}
	
	public void addEdge(int start, int end, int weight) {
		edges.add(new Edge(start, end, weight));
	}
	
	public List<Edge> getEdges() {
		return this.edges;
	}
	
	public int getNVertices() {
		return this.vertices;
	}
	
	private int vertices;
	private List<Edge> edges;
}
