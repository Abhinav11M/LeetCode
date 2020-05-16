package educative.topologicalsort;

public class TopologicalSortMain {
	public static void main(String[] args) {
		TopologicalSort tpSort = new TopologicalSort();
		System.out.println(tpSort.topologicalOrder(4, new int[][] { {3, 2}, {3, 0}, {2, 0}, {2, 1} }));
		System.out.println(tpSort.topologicalOrder(7, new int[][] { {6, 4}, {6, 2}, {5, 3}, {5, 4}, {3, 0}, {3, 1}, {3, 2}, {4, 1} }));

		System.out.println(tpSort.isSchedulingPossible(3, new int[][] { {0, 1}, {1, 2} }));
		System.out.println(tpSort.isSchedulingPossible(3, new int[][] { {0, 1}, {1, 2}, {2, 0} }));
		System.out.println(tpSort.isSchedulingPossible(6, new int[][] { {2, 5}, {0, 5}, {0, 4}, {1, 4}, {3, 2}, {1, 3} }));

		System.out.println(tpSort.allSchedulingOrders(4, new int[][] { {3, 2}, {3, 0}, {2, 0}, {2, 1} }));
		System.out.println(tpSort.allSchedulingOrders(6, new int[][] { {2, 5}, {0, 5}, {0, 4}, {1, 4}, {3, 2}, {1, 3} }));

		System.out.println(tpSort.findOrder(new String[] {"ba", "bc", "ac", "cab"}));
		System.out.println(tpSort.findOrder(new String[] {"cab", "aaa", "aab"}));
		System.out.println(tpSort.findOrder(new String[] {"ywx", "wz", "xww", "xz", "zyy", "zwz"}));
	}
}
