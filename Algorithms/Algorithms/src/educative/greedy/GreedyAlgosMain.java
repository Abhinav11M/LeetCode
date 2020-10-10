package educative.greedy;

public class GreedyAlgosMain {

	public static void main(String[] args) {
		GreedyAlgosQues algos = new GreedyAlgosQues();
		System.out.println("==== Make a coin machine ====");
		System.out.println(algos.getMinCoins(49));

		System.out.println("==== Min cost to connect pipes ====");
		System.out.println(algos.minCost(new int[] { 4, 2, 3, 7 }));
		System.out.println(algos.minCost(new int[] { 4, 5, 6, 7 }));
		
		System.out.println("==== Egyptian Fraction ====");
		algos.printEgyptianFraction(2, 3);
		
		System.out.println("==== Minimum number of platforms ====");
		System.out.println(algos.findPlatform(new int[] {900, 940, 950, 1100, 1500, 1800}, 
				new int[] {910, 1200, 1120, 1130, 1900, 2000}));
		System.out.println(algos.findPlatform2(new int[] {900, 940, 950, 1100, 1500, 1800}, 
				new int[] {910, 1200, 1120, 1130, 1900, 2000}));

		System.out.println("==== Police Thief ====");
		System.out.println(algos.policeThief(new char[] { 'P', 'T', 'T', 'P', 'T' }, 1));
		System.out.println(algos.policeThief(new char[] { 'T', 'T', 'P', 'P', 'T', 'P' }, 2));
		System.out.println(algos.policeThief(new char[] { 'P', 'T', 'P', 'T', 'T', 'P' }, 3));

		System.out.println("==== Fraction Knapsack ====");
		System.out.println(algos.fractionalKnapsack(new double[] { 2, 1, 6, 0.5, 0.25, 7 },
				new double[] { 50, 70, 100, 50, 30, 99 }, 10));

		System.out.println("==== Fraction Knapsack ====");
		algos.findLargestNumber(2, 9);
		algos.findLargestNumber(3, 20);
		algos.findLargestNumber(3, 100);
		
		System.out.println("==== Graph Coloring ====");
		Graph g1 = new Graph(5);
		g1.addEdge(0, 1);
		g1.addEdge(0, 2);
		g1.addEdge(1, 2);
		g1.addEdge(1, 3);
		g1.addEdge(2, 3);
		g1.addEdge(3, 4);

		algos.greedyColoring(g1);
		System.out.println();

		Graph g2 = new Graph(5);
		g2.addEdge(0, 1);
		g2.addEdge(0, 2);
		g2.addEdge(1, 2);
		g2.addEdge(1, 4);
		g2.addEdge(2, 4);
		g2.addEdge(4, 3);
		algos.greedyColoring(g2);

		System.out.println("==== Prim's algorithm for minimum spanning tree ====");
		int graph[][] = new int[][] { 
			{ 0, 2, 0, 6, 0 }, 
			{ 2, 0, 3, 8, 5 }, 
			{ 0, 3, 0, 0, 7 }, 
			{ 6, 8, 0, 0, 9 },
			{ 0, 5, 7, 9, 0 } 
		};

		algos.primsAlgo(graph);
		
		System.out.println("==== Disjoint Sets (Needed for Kruskal's algo) ====");
		DisjointSet ds = new DisjointSet();
		ds.makeSet(1);
		ds.makeSet(2);
		ds.makeSet(3);
		ds.makeSet(4);
		ds.makeSet(5);
		ds.union(1, 2);
		ds.union(3, 4);
		ds.union(3, 5);
		for(int i = 1; i <=5; ++i) {
			System.out.println(i + "=>" + ds.findSet(i).value);
		}
		ds.union(2, 3);
		for(int i = 1; i <=5; ++i) {
			System.out.println(i + "=>" + ds.findSet(i).value);
		}
		
		System.out.println("==== Kruskal's algorithm for minimum spanning tree ====");
		Graph2 g = new Graph2(5);
		g.addEdge(0, 1, 2);
		g.addEdge(0, 3, 6);
		g.addEdge(1, 2, 3);
		g.addEdge(1, 3, 8);
		g.addEdge(1, 4, 5);
		g.addEdge(2, 4, 7);
		g.addEdge(3, 4, 9);
		
		algos.KruskalMST(g);
	}
}
