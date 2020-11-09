package educative.tree.dfs;

import lombok.ToString;

@ToString
public class TreeNode {
	public int value;
	public TreeNode left;
	public TreeNode right;

	public TreeNode(int x) {
		value = x;
	}
}
