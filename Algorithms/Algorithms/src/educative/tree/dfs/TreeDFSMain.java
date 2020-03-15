package educative.tree.dfs;

import java.util.List;

public class TreeDFSMain {
	public static void main(String[] args) {
		TreeDFS treeDfs = new TreeDFS();
		
		TreeNode root = new TreeNode(12);
	    root.left = new TreeNode(7);
	    root.right = new TreeNode(1);
	    root.left.left = new TreeNode(9);
	    root.right.left = new TreeNode(10);
	    root.right.right = new TreeNode(5);
	    System.out.println("Tree has path: " + treeDfs.hasPath(root, 23));
	    System.out.println("Tree has path: " + treeDfs.hasPath(root, 16));
	    
	    root = new TreeNode(12);
	    root.left = new TreeNode(7);
	    root.right = new TreeNode(1);
	    root.left.left = new TreeNode(4);
	    root.right.left = new TreeNode(10);
	    root.right.right = new TreeNode(5);
	    int sum = 23;
	    List<List<Integer>> result = treeDfs.findPaths(root, sum);
	    System.out.println();
	}
}
