package educative.tree.bfs;

public class TreeNodeWithNext {

	public int val;
	public TreeNodeWithNext left;
	public TreeNodeWithNext right;
	public TreeNodeWithNext next;

	public TreeNodeWithNext(int x) {
		val = x;
		left = right = next = null;
	}
	
	@Override
	public String toString() {
		String leftStr = left == null ? "null" : Integer.toString(left.val);
		String rightStr = right == null ? "null" : Integer.toString(right.val);
		String nextStr = next == null ? "null" : Integer.toString(next.val);
		return "[val : " + val + ", left : " + leftStr + ", right : " + rightStr + ", next : " + nextStr + "]";
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
