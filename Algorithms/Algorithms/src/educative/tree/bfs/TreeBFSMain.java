package educative.tree.bfs;

import java.util.List;

import datastructures.TreeNode;

public class TreeBFSMain {
	public static void main(String[] args) {
		TreeBFS treeBFS = new TreeBFS();
		
		TreeNode root = new TreeNode(12);
		root.left = new TreeNode(7);
		root.right = new TreeNode(1);
		root.left.left = new TreeNode(9);
		root.right.left = new TreeNode(10);
		root.right.right = new TreeNode(5);
		List<List<Integer>> result = treeBFS.levelOrderTraverse(root);
		System.out.println("Level order traversal: " + result);
		List<List<Integer>> resultRev = treeBFS.levelOrderTraverseReverse(root);
		System.out.println("Level order traversal: " + resultRev);
		List<List<Integer>> resultZigZag = treeBFS.traverseZigZag(root);
		System.out.println("Level order traversal: " + resultZigZag);
		
		
		TreeNode root1 = new TreeNode(12);
	    root1.left = new TreeNode(7);
	    root1.right = new TreeNode(1);
	    root1.left.left = new TreeNode(9);
	    root1.left.right = new TreeNode(2);
	    root1.right.left = new TreeNode(10);
	    root1.right.right = new TreeNode(5);
	    List<Double> resultAvg = treeBFS.findLevelAverages(root1);
	    System.out.println("Level averages are: " + resultAvg);
	    resultAvg = treeBFS.findLevelAverages(root);
	    System.out.println("Level averages are: " + resultAvg);
	    
	    
	    TreeNode root2 = new TreeNode(12);
	    root2.left = new TreeNode(7);
	    root2.right = new TreeNode(1);
	    root2.right.left = new TreeNode(10);
	    root2.right.right = new TreeNode(5);
	    System.out.println("Tree Minimum Depth: " + treeBFS.findMinDepth(root2));
	    root2.left.left = new TreeNode(9);
	    root2.right.left.left = new TreeNode(11);
	    System.out.println("Tree Minimum Depth: " + treeBFS.findMinDepth(root2));
	    
	    
	    TreeNode root3 = new TreeNode(12);
	    root3.left = new TreeNode(7);
	    root3.right = new TreeNode(1);
	    root3.left.left = new TreeNode(9);
	    root3.right.left = new TreeNode(10);
	    root3.right.right = new TreeNode(5);
	    TreeNode findResult = treeBFS.findLevelOrderPredecessor(root, 12);
	    if (findResult != null)
	      System.out.println(findResult.val + " ");
	    findResult = treeBFS.findLevelOrderPredecessor(root3, 9);
	    if (findResult != null)
	      System.out.println(findResult.val + " ");

	    findResult = treeBFS.findLevelOrderSuccessor(root, 12);
	    if (findResult != null)
	      System.out.println(findResult.val + " ");
	    findResult = treeBFS.findLevelOrderSuccessor(root3, 9);
	    if (findResult != null)
	      System.out.println(findResult.val + " ");
	    
	    
	    TreeNodeWithNext root4 = new TreeNodeWithNext(12);
	    root4.left = new TreeNodeWithNext(7);
	    root4.right = new TreeNodeWithNext(1);
	    root4.left.left = new TreeNodeWithNext(9);
	    root4.right.left = new TreeNodeWithNext(10);
	    root4.right.right = new TreeNodeWithNext(5);
	    treeBFS.connectLevelOrderSiblings(root4);
	    System.out.println("Level order traversal using 'next' pointer: ");
	    root4.printLevelOrder();
	    
	    
	    TreeNodeWithNext root5 = new TreeNodeWithNext(12);
	    root5.left = new TreeNodeWithNext(7);
	    root5.right = new TreeNodeWithNext(1);
	    root5.left.left = new TreeNodeWithNext(9);
	    root5.right.left = new TreeNodeWithNext(10);
	    root5.right.right = new TreeNodeWithNext(5);
	    treeBFS.connectAllSiblings(root5);
	    root5.printAllConnected();
	    
	    System.out.println();
	    TreeNode root6 = new TreeNode(12);
	    root6.left = new TreeNode(7);
	    root6.right = new TreeNode(1);
	    root6.left.left = new TreeNode(9);
	    root6.right.left = new TreeNode(10);
	    root6.right.right = new TreeNode(5);
	    root6.left.left.left = new TreeNode(3);
	    List<TreeNode> resultLeftView = treeBFS.rightViewTree(root6);
	    for (TreeNode node : resultLeftView) {
	        System.out.print(node.val + " ");
	    }
	}
}
