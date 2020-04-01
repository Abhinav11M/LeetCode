package educative.tree.bfs;

public class TreeNodeWithNext {

	int val;
	TreeNodeWithNext left;
	TreeNodeWithNext right;
	TreeNodeWithNext next;

	TreeNodeWithNext(int x) {
		val = x;
		left = right = next = null;
	}
	
	public void printLevelOrder() {
		TreeNodeWithNext nextLevelRoot = this;
		while (nextLevelRoot != null) {
			TreeNodeWithNext current = nextLevelRoot;
			nextLevelRoot = null;
			while (current != null) {
				System.out.print(current.val + " ");
				if (nextLevelRoot == null) {
					if (current.left != null)
						nextLevelRoot = current.left;
					else if (current.right != null)
						nextLevelRoot = current.right;
				}
				current = current.next;
			}
			System.out.println();
		}
	}
	
	public void printAllConnected() {
		TreeNodeWithNext root = this;
		
		while(root != null) {
			System.out.print(root.val + " ");
			root = root.next;
		}
	}
}
