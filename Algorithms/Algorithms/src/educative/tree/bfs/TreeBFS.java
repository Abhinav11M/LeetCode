package educative.tree.bfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import datastructures.ListNodeWithRandom;
import datastructures.TreeNode;

public class TreeBFS {
	/**
	 * Binary Tree Level Order Traversal (easy)
	 */
	public List<List<Integer>> levelOrderTraverse(TreeNode root) {
		if(root == null) {
			return Collections.emptyList();
		}
		
		List<List<Integer>> result = new ArrayList<>();
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		queue.add(null); // To mark the end of a level
		
		List<Integer> values = new ArrayList<>();
		
		while(!queue.isEmpty()) {
			TreeNode node = queue.poll();
			if(node == null) { // end of a level
				result.add(new ArrayList<>(values));
				values.clear();
				if(!queue.isEmpty()) {
					queue.add(null);
				}
			}
			else {
				values.add(node.val);
				if(node.left != null) {
					queue.add(node.left);
				}
				if(node.right != null) {
					queue.add(node.right);
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Level order traversal reverse
	 */
	public List<List<Integer>> levelOrderTraverseReverse(TreeNode root) {
		if(root == null) {
			return Collections.emptyList();
		}
		
		List<List<Integer>> result = new LinkedList<>();
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		queue.add(null); // To mark the end of a level
		
		List<Integer> values = new ArrayList<>();
		
		while(!queue.isEmpty()) {
			TreeNode node = queue.poll();
			if(node == null) { // end of a level
				result.add(0, new ArrayList<>(values));
				values.clear();
				if(!queue.isEmpty()) {
					queue.add(null);
				}
			}
			else {
				values.add(node.val);
				if(node.left != null) {
					queue.add(node.left);
				}
				if(node.right != null) {
					queue.add(node.right);
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Given a binary tree, populate an array to represent its zigzag level order traversal. 
	 * You should populate the values of all nodes of the first level from left to right, 
	 * then right to left for the next level and keep alternating in the same manner for 
	 * the following levels.
	 * @param root Root node of the binary tree.
	 * @return
	 */
	public List<List<Integer>> traverseZigZag(TreeNode root) {
		
		List<List<Integer>> result = new ArrayList<>();
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		boolean leftToRight = true;
		
		while(!queue.isEmpty()) {
			List<Integer> list = new LinkedList<>();
			int n = queue.size();
			for(int i = 0; i < n; ++i) {
				TreeNode node = queue.poll();
				if(node.left != null) {
					queue.add(node.left);
				}
				if(node.right != null) {
					queue.add(node.right);
				}
				
				if(leftToRight) {
					list.add(node.val);
				}
				else {
					list.add(0, node.val);
				}
			}
			leftToRight = !leftToRight;
			result.add(list);
		}
		
		return result;
	}
	
	/**
	 * Given a binary tree, populate an array to represent the averages of all of its levels.
	 */
	public List<Double> findLevelAverages(TreeNode root) {
		List<Double> result = new ArrayList<>();
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			int n = queue.size();
			int sum = 0;
			for(int i = 0; i < n; ++i) {
				TreeNode node = queue.poll();

				if(node.left != null) {
					queue.offer(node.left);
				}
				if(node.right != null) {
					queue.offer(node.right);
				}
				
				sum += node.val;
			}
			result.add(sum*1.0/n);
		}
		
		return result;
	}
	
	/**
	 * Minimum Depth of a Binary Tree
	 * Find the minimum depth of a binary tree. 
	 * The minimum depth is the number of nodes along the shortest path 
	 * from the root node to the nearest leaf node.
	 */
	public int findMinDepth(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		int level = 0;
		
		while(!queue.isEmpty()) {
			int n = queue.size();
			++level;
			for(int i = 0; i < n; ++i) {
				
				TreeNode node = queue.poll();
				if(node.left == null && node.right == null) {
					return level;
				}
				
				if(node.left != null) {
					queue.add(node.left);
				}
				if(node.right != null) {
					queue.add(node.right);
				}
			}
		}

		return level;
	}
	
	/**
	 * Level Order Predecessor
	 * Given a binary tree and a node, find the level order predecessor of the 
	 * given node in the tree. The level order predecessor is the node that appears 
	 * right before the given node in the level order traversal.
	 */
	 public TreeNode findLevelOrderPredecessor(TreeNode root, int key) {
		 Queue<TreeNode> queue = new LinkedList<>();
		 queue.add(root);
		 TreeNode prev = null;
		 
		 while(!queue.isEmpty()) {
			 int n = queue.size();
			 for(int i = 0; i < n; ++i) {
				 TreeNode curr = queue.poll();
				 
				 if(curr.val == key) {
					 return prev;
				 }
				 
				 if(curr.left != null) {
					 queue.add(curr.left);
				 }
				 if(curr.right != null) {
					 queue.add(curr.right);
				 }
				 
				 prev = curr;
			 }
		 }
		 // Key could not be found.
		 return null; 
	 }
	 
	 /**
	  * Level Order Successor
	  * Given a binary tree and a node, find the level order successor 
	  * of the given node in the tree. The level order successor is the 
	  * node that appears right after the given node in the level order traversal.
	  */
	 public TreeNode findLevelOrderSuccessor(TreeNode root, int key) {
		 Queue<TreeNode> queue = new LinkedList<>();
		 queue.add(root);
		 
		 while(!queue.isEmpty()) {
			 int n = queue.size();
			 for(int i = 0; i < n; ++i) {
				 TreeNode curr = queue.poll();
				 
				 if(curr.left != null) {
					 queue.offer(curr.left);
				 }
				 if(curr.right != null) {
					 queue.offer(curr.right);
				 }
				 
				 if(curr.val == key) {
					 return queue.peek();
				 }
			 }
		 }
		 
		 return null;
	 }
	 
	 /**
	  * Connect Level Order Siblings 
	  * Given a binary tree, connect each node with its level order successor. 
	  * The last node of each level should point to a null node.
	  */
	 public void connectLevelOrderSiblings(TreeNodeWithNext root) {
		Queue<TreeNodeWithNext> queue = new LinkedList<>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			int n = queue.size();
			TreeNodeWithNext prev = null;
			for(int i = 0; i < n; ++i) {
				TreeNodeWithNext curr = queue.poll();
				if(i == n-1) {
					curr.next = null;
				}
				if(prev!= null) {
					prev.next = curr;
				}
				prev = curr;
				
				if(curr.left != null) {
					queue.offer(curr.left);
				}
				if(curr.right != null) {
					queue.offer(curr.right);
				}
			}
		}
	 }
	 
	 /**
	  * Connect All Level Order Siblings
	  */
	 public void connectAllSiblings(TreeNodeWithNext root) {
		 Queue<TreeNodeWithNext> queue = new LinkedList<>();
		 queue.add(root);
		 TreeNodeWithNext prev = null;
		 while(!queue.isEmpty()) {
			 TreeNodeWithNext curr = queue.poll();
			 if(prev != null) {
				 prev.next = curr;
			 }
			 prev = curr;
			 
			 if(curr.left != null) {
				 queue.offer(curr.left);
			 }
			 if(curr.right != null) {
				 queue.offer(curr.right);
			 }
		 }
		 prev.next = null;
	 }
	 
	 /**
	  * Right View of a Binary Tree
	  */
	 public List<TreeNode> rightViewTree(TreeNode root) {
		 List<TreeNode> result = new ArrayList<>();
		 
		 Queue<TreeNode> queue = new LinkedList<>();
		 queue.add(root);
		 
		 while(!queue.isEmpty()) {
			 int n = queue.size();
			 for(int i = 0; i < n; ++i) {
				 TreeNode curr = queue.poll();
				 
				 if(i == n-1) {
					 result.add(curr);
				 }
				 
				 if(curr.left != null) {
					 queue.add(curr.left);
				 }
				 if(curr.right != null) {
					 queue.add(curr.right);
				 }
			 }
		 }
		 
		 return result;
	 }
}
