package educative.tree.dfs;

import java.util.ArrayList;
import java.util.List;

public class TreeDFS {
	/**
	 * Binary Tree Path Sum
	 * Given a binary tree and a number ‘S’, find if the tree has a path from root-to-leaf 
	 * such that the sum of all the node values of that path equals ‘S’.
	 */
	public boolean hasPath(TreeNode root, int sum) {
		return hasPath(root, 0, sum);
	}

	private boolean hasPath(TreeNode root, int sum, int target) {
		if(root == null) {
			return false;
		}
		
		sum += root.value;
		if(sum == target && root.left == null && root.right == null) { // Should end in a leaf node
			return true;
		}
		
		// If the nodes can only have positive values, we can return from the 
		// previous stage only and stop the recursion.
		return hasPath(root.left, sum, target) || hasPath(root.right, sum, target);
	}
	
	/**
	 * Given a binary tree and a number ‘S’, find all paths from root-to-leaf 
	 * such that the sum of all the node values of each path equals ‘S’.
	 */
	 public List<List<Integer>> findPaths(TreeNode root, int sum) {
		 List<List<Integer>> paths = new ArrayList<>();
		 findPaths(root, new ArrayList<Integer>(), paths, 0, sum);
		 
		 return paths;
	 }

	private void findPaths(TreeNode root, ArrayList<Integer> path, List<List<Integer>> paths, int sum, int target) {
		if(root == null) {
			return;
		}
		path.add(root.value);
		sum += root.value;
		
		if(sum == target && root.left == null && root.right == null) {
			List<Integer> l = new ArrayList<>(path);
			paths.add(l);
		}

		findPaths(root.left, path, paths, sum, target);
		findPaths(root.right, path, paths, sum, target);
		path.remove(path.size()-1);
	}
	
}
