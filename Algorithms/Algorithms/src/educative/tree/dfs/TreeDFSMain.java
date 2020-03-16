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
	    
	    root = new TreeNode(1);
	    root.left = new TreeNode(0);
	    root.right = new TreeNode(1);
	    root.left.left = new TreeNode(1);
	    root.right.left = new TreeNode(6);
	    root.right.right = new TreeNode(5);
	    
	    int pathSum = treeDfs.findSumOfPathNumbers(root);
	    
	    root = new TreeNode(1);
	    root.left = new TreeNode(7);
	    root.right = new TreeNode(9);
	    root.right.left = new TreeNode(2);
	    root.right.right = new TreeNode(9);
	    
	    pathSum = treeDfs.findSumOfPathNumbers(root);
	    
	    boolean seqFound = treeDfs.findPath(root, new int[] {1,9,9});
	    
	    root = new TreeNode(1);
	    root.left = new TreeNode(0);
	    root.right = new TreeNode(1);
	    root.left.left = new TreeNode(1);
	    root.right.left = new TreeNode(6);
	    root.right.right = new TreeNode(5);

	    seqFound = treeDfs.findPath(root, new int[] {1,0,7});
	    seqFound = treeDfs.findPath(root, new int[] {1,1,6});

	    root = new TreeNode(12);
	    root.left = new TreeNode(7);
	    root.right = new TreeNode(1);
	    root.left.left = new TreeNode(4);
	    root.right.left = new TreeNode(10);
	    root.right.right = new TreeNode(5);
	    
	    int pathsCount = treeDfs.countAllPathSum(root, 11);
	    
	    System.out.println("Diameter :" + treeDfs.findDiameter(root));
	    
	    root = new TreeNode(0);
	    System.out.println(treeDfs.findMaximumPathSum(root));
	    
	    System.out.println();
	}
}
