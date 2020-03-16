package educative.tree.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
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
	
	/**
	 * Given a binary tree and a number ‘S’, find all paths in the tree such 
	 * that the sum of all the node values of each path equals ‘S’. Please note
	 * that the paths can start or end at any node but all paths must follow direction 
	 * from parent to child (top to bottom).
	 */
	public int countAllPathSum(TreeNode root, int target) {
		return countAllPathSum(root, new LinkedList<Integer>(), target);
	}

	private int countAllPathSum(TreeNode root, LinkedList<Integer> path, int target) {
		if(root == null) {
			return 0;
		}
		
		path.add(root.value);
		// Add elements to see if the path has any target or not
		int sum = 0, count = 0;
		ListIterator<Integer> iter = path.listIterator(path.size());
		
		while(iter.hasPrevious()) {
			sum+= iter.previous();
			if(sum == target) {
				++count;
			}
		}
		
		// recursively find in the left and right
		count += countAllPathSum(root.left, path, target);
		count += countAllPathSum(root.right, path, target);
		
		// Going up, we need to remove the last value inserted in the list
		path.remove(path.size()-1);
		
		return count;
	}
	
	/**
	 * Tree Diameter
	 */
	 public int findDiameter(TreeNode root) {
		 if(root == null) {
			 return 0;
		 }
		 
		 int lHeight = findHeightOfTree(root.left);
		 int rHeight = findHeightOfTree(root.right);
		 
		 int diameter = lHeight+rHeight+1;
		 
		 return Math.max(diameter, Math.max(findDiameter(root.left), findDiameter(root.right)));
	 }
	 
	 private int findHeightOfTree(TreeNode root) {
		 if(root == null) {
			 return 0;
		 }
		 
		 return 1 + Math.max(findHeightOfTree(root.left), findHeightOfTree(root.right));
	 }
	 
	 /**
	  * Path with Maximum Sum
	  * Find the path with the maximum sum in a given binary tree. 
	  * Write a function that returns the maximum sum. 
	  * A path can be defined as a sequence of nodes between any two 
	  * nodes and doesn’t necessarily pass through the root
	  */
	 public int findMaximumPathSum(TreeNode root) {
		 maxPathSum = Integer.MIN_VALUE;
		 findMaxPathSumRecursive(root);
		 return maxPathSum;
	 }
	 
	 private int findMaxPathSumRecursive(TreeNode root) {
		if(root == null) {
			return 0;
		}
		
		int leftSum = findMaxPathSumRecursive(root.left);
		int rightSum = findMaxPathSumRecursive(root.right);
		
		// We don't want negative sums
		leftSum = Math.max(leftSum, 0);
		rightSum = Math.max(rightSum, 0);
		
		int localMaxPathSum = leftSum + rightSum + root.value;
		
		// Update maxPathSum
		maxPathSum = Math.max(maxPathSum, localMaxPathSum);
		
		return Math.max(leftSum, rightSum) + root.value;
 	}

	private static int maxPathSum;
}
