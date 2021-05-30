package graphs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
	
	private LinkedList<Integer>[] adjList;
	
	@SuppressWarnings("unchecked")
	Graph(int numberOfVertices) {
		
		adjList = new LinkedList[numberOfVertices];
		for(int i = 0; i < numberOfVertices; ++i) {
			adjList[i] = new LinkedList<>();
		}
	}
	
	public void addEdge(int a, int b) {
		adjList[a].add(b);
	}
	
	public void bfsTraversal(int startVertex) {
		boolean[] visited = new boolean[adjList.length];
		Queue<Integer> queue = new LinkedList<>();
		
		queue.add(startVertex);
		visited[startVertex] = true;

		while(!queue.isEmpty()) {
			int val = queue.poll();
			System.out.print(val + "->");
			
			Iterator<Integer> iter = adjList[val].listIterator();
			
			while(iter.hasNext()) {
				int elem = iter.next();
				if(visited[elem] == false) {
					visited[elem] = true;
					queue.offer(elem);
				}
			}
		}
		System.out.println();
	}
	
	public void dfsTraversal(int startVertex) {
		boolean[] visited = new boolean[adjList.length];
		
		dfsTraversal(startVertex, visited);
		System.out.println();
	}

	private void dfsTraversal(int startVertex, boolean[] visited) {
		System.out.print(startVertex + "->");
		visited[startVertex] = true;
		
		Iterator<Integer> iter = adjList[startVertex].listIterator();
		while(iter.hasNext()) {
			int elem = iter.next();
			if(visited[elem] == false) {
				visited[elem] = true;
				dfsTraversal(elem, visited);
			}
		}
	}
}
