package educative.tree.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
	
	/**
	 * Given a binary tree where each node can only have a digit (0-9) value, each root-to-leaf path will represent a number. 
	 * Find the total sum of all the numbers represented by all paths.
	 * (1 -> (7, (9 -> (2, 9))) => 408; 17 + 192 + 199
	 */
	public int findSumOfPathNumbers(TreeNode root) {
		return findSumOfPathNumbers(root, 0, 0);
	}

	private int findSumOfPathNumbers(TreeNode root, Integer totalSum, int pathSum) {
		if(root == null) {
			return totalSum;
		}
		
		pathSum = pathSum*10 + root.value;
		if(root.left == null && root.right == null) { //Leaf node
			totalSum += pathSum;
		}
		
		totalSum = findSumOfPathNumbers(root.left, totalSum, pathSum);
		totalSum = findSumOfPathNumbers(root.right, totalSum, pathSum);
		
		return totalSum;
	}
	
	/**
	 * Given a binary tree and a number sequence, find if the sequence is 
	 * present as a root-to-leaf path in the given tree.
	 * (1 -> (7, (9 -> (2, 9))), [1,9,9] => True
	 */
	public boolean findPath(TreeNode root, int[] sequence) {
		return findPath(root, new ArrayList<Integer>(), Arrays.stream(sequence).boxed().collect(Collectors.toList()));
	}

	private boolean findPath(TreeNode root, ArrayList<Integer> path, List<Integer> sequence) {
		if (root == null) {
			return false;
		}
		path.add(root.value);
		if (path.equals(sequence) && root.left == null && root.right == null) {
			return true;
		}
	
		boolean left = false;
		if(root.left != null) {
			left = findPath(root.left, path, sequence);
			path.remove(path.size()-1);
		}

		boolean right = false;
		if(root.right != null) {
			right = findPath(root.right, path, sequence);
			path.remove(path.size()-1);
		}
		return left || right;
	}
}
