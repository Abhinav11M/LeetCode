package educative.greedy;

import java.util.HashMap;

/**
 * Class for a list of disjoint sets/nodes
 */
public class DisjointSet {

	/**
	 * Class for a Disjoint Node
	 */
	public static class DisjointNode {
		
		public DisjointNode(int value) {
			this.value = value;
			this.rank = 0;
			this.parent = this;
		}
		
		public DisjointNode parent;
		public int value;
		public int rank;
	}
	
	public void makeSet(int value) {
		DisjointNode node = new DisjointNode(value);
		map.put(value, node);
	}
	
	/**
	 * @apiNote: Return the top most parent node of the disjoint set.
	 * 			 It also does the path compression while doing so.
	 * @param node Node to be searched
	 * @return The root element of the disjoint set that contains node.
	 */
	public DisjointNode findSet(int value) {
		if(!map.containsKey(value)) {
			throw new RuntimeException("Value : " + value + " not found in the disjoint set");
		}
		return findSet(map.get(value));
	}

	public DisjointNode findSet(DisjointNode node) {
		DisjointNode parent = node.parent;
		if(parent == node) {
			return parent;
		}
		
		// Recursively call findSet on the parent also update the parent
		// of the node with the parent of the parent (path compression).
		node.parent = findSet(parent);
		return node.parent;
	}
	
	/**
	 * @apiNote: Merges two disjoint sets.
	 * @param value1
	 * @param value2
	 */
	public void union(int value1, int value2) {
		if(!map.containsKey(value1)) {
			throw new RuntimeException("Value1 " + value1 + " is not present in the disjoint set");
		}
		if(!map.containsKey(value2)) {
			throw new RuntimeException("Value2 " + value2 + " is not present in the disjoint set");
		}
		
		// get the parent of both
		DisjointNode parent1 = findSet(map.get(value1));
		DisjointNode parent2 = findSet(map.get(value2));
		
		if(parent1.rank >= parent2.rank) { // parent1 will become the parent of parent2 post merge
			if(parent1.rank == parent2.rank) { // Incrementing the rank in case both the set's parents share the same rank.
				parent1.rank++;
			}
			parent2.parent = parent1;
		}
		else { // parent2 will become the parent of parent1 post merge
			parent1.parent = parent2;
		}
	}
	
	// Data
	private HashMap<Integer, DisjointNode> map = new HashMap<>();
}
