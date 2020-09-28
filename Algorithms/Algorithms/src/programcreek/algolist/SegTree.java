package programcreek.algolist;

public class SegTree {

	// SegTreeNode class
	public static class SegTreeNode {
		public int start;
		public int end;
		public int sum;
		public SegTreeNode left;
		public SegTreeNode right;
		
		public SegTreeNode(int start, int end, int sum) {
			this.start = start;
			this.end = end;
			this.sum = sum;
			this.left = null;
			this.right = null;
		}
	}
	
	public SegTreeNode root;
	
	// Construct the segTree in the ctor
	public SegTree(int[] nums) {
		if(nums == null || nums.length == 0) {
			return;
		}

		root = buildTree(nums, 0, nums.length-1);
	}
	
	public void setValue(int idx, int value) {
		if(idx < root.start || idx > root.end) {
			return;
		}
		
		update(root, idx, value);
	}
	
	private void update(SegTreeNode root, int idx, int value) {
		if(root.start == idx && root.end == idx) {
			root.sum = value;
			return;
		}

		int mid = root.start + (root.end-root.start)/2;
		
		if(idx > mid) {
			update(root.right, idx, value);
		}
		else {
			update(root.left, idx, value);
		}
		
		root.sum = root.left.sum + root.right.sum;
	}
	
	public int sumRange(int start, int end) {
		return sumRange(root, start, end);
	}

	private int sumRange(SegTreeNode root, int start, int end) {

		if(root == null || end < root.start || start > root.end || start > end) {
			return 0;
		}
		
		if(start <= root.start && root.end <= end) {
			return root.sum;
		}
		
		int mid = root.start + (root.end-root.start)/2;
		return sumRange(root.left, start, Math.min(mid, end)) + sumRange(root.right, Math.max(mid+1, start), end);
		
	}

	// Post-order
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		printTree(root, str);
		return str.toString();
	}

	private void printTree(SegTreeNode root, StringBuilder str) {
		if(root == null) {
			return;
		}
		
		printTree(root.left, str);
		str.append(",");
		printTree(root.right, str);
		str.append(",");
		str.append(root.sum);
	}

	private SegTreeNode buildTree(int[] nums, int start, int end) {
		if(start == end) { // leaf node
			return new SegTreeNode(start, end, nums[start]);
		}
		
		SegTreeNode node = new SegTreeNode(start, end, 0);
		int mid = start + (end-start)/2;
		node.left = buildTree(nums, start, mid);
		node.right = buildTree(nums, mid+1, end);
		
		node.sum = node.left.sum + node.right.sum;
		
		return node;
	}
}
