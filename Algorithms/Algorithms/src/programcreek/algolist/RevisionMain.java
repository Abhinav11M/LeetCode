package programcreek.algolist;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import datastructures.ListNode;
import datastructures.TreeNode;
import educative.tree.bfs.TreeNodeWithNext;
import helpers.Helpers;

public class RevisionMain {
	public static void main(String[] args) {
		Revision algos = new Revision();
		int[] arr = new int[] {0, 1, 0, 3, 12};
		algos.moveZeroes(arr);
		Helpers.printArr(arr);
		
		System.out.println(algos.maxArea(new int[] {1,8,6,2,5,4,8,3,7}));
		System.out.println(algos.candy(new int[] {1,0,2}));
		System.out.println(algos.candy(new int[] {1,2,2}));
		
		System.out.println("Water Trapping");
		System.out.println(algos.trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
		System.out.println(algos.trap(new int[] {4,2,0,3,2,5}));

		System.out.println(algos.isOneEditDistance("geeks", "geek"));
		System.out.println(algos.isOneEditDistance("geeks", "geeks"));
		System.out.println(algos.isOneEditDistance("geaks", "geeks"));
		System.out.println(algos.isOneEditDistance("peaks", "geeks"));
		
//		System.out.println(algos.longestValidParentheses("(()"));
//		System.out.println(algos.longestValidParentheses(")()())"));
		System.out.println(algos.longestValidParentheses("()(())"));
		
		System.out.println(algos.largestRectangleArea(new int[] {2,1,2}));

		System.out.println(algos.lengthOfLongestSubstring("abcabcbb"));
		System.out.println(algos.lengthOfLongestSubstring("bbbb"));
		System.out.println(algos.lengthOfLongestSubstring("dvdf"));
		
		System.out.println(algos.lengthOfLongestSubstringKDistinct("araaci", 2));
		System.out.println(algos.lengthOfLongestSubstringKDistinct("araaci", 1));
		System.out.println(algos.lengthOfLongestSubstringKDistinct("cbbebi", 3));

		System.out.println(algos.findSubstring1("barfoothefoobarman", new String[] {"foo", "bar"}));
		System.out.println(algos.findSubstring1("wordgoodgoodgoodbestword", new String[] {"word","good","best","word"}));
		System.out.println(algos.findSubstring1("barfoofoobarthefoobarman", new String[] {"bar", "foo", "the"}));
		
		System.out.println(algos.numDecodings("226"));
		System.out.println(algos.numDecodings("12"));
		System.out.println(algos.numDecodings("06"));
		System.out.println(algos.numDecodings("10"));
		
//		System.out.println(algos.numDecodingsTabulation("226"));
//		System.out.println(algos.numDecodingsTabulation("12"));
//		System.out.println(algos.numDecodingsTabulation("06"));
//		System.out.println(algos.numDecodingsTabulation("10"));
//		System.out.println(algos.numDecodingsTabulation("2101"));
		System.out.println(algos.numDecodingsTabulation("27"));

//		System.out.println(algos.isIsomorphic("egg", "add"));
//		System.out.println(algos.isIsomorphic("foo", "bar"));
//		System.out.println(algos.isIsomorphic("paper", "title"));
//		System.out.println(algos.isIsomorphic("badc", "baba"));
		
		System.out.println(algos.longestConsecutive(new int[] {100,4,200,1,3,2}));
		System.out.println(algos.longestConsecutive(new int[] {0,3,7,2,5,8,4,6,0,1}));
		System.out.println(algos.longestConsecutive(new int[] {0,0,-1}));

		System.out.println(algos.longestConsecutiveOpt(new int[] {100,4,200,1,3,2}));
		System.out.println(algos.longestConsecutiveOpt(new int[] {0,3,7,2,5,8,4,6,0,1}));
		System.out.println(algos.longestConsecutiveOpt(new int[] {0,0,-1}));
		
		List<TreeNode> structurallyUniqueBSTs = algos.generateTrees(3);
		for(TreeNode root : structurallyUniqueBSTs) {
			System.out.println(algos.inorderTraversal(root));
		}
		
		System.out.println(algos.getNthCatalanNumber(3));
//		System.out.println(algos.majorityElement(new int[] {3,2,3}));
//		System.out.println(algos.majorityElement(new int[] {1,2}));
		System.out.println(algos.majorityElement(new int[] {2,2,1,3}));
		
//		TreeNode t = new TreeNode(3);
//		t.left = new TreeNode(9);
//		t.right = new TreeNode(20);
//		t.right.left = new TreeNode(15);
//		t.right.right = new TreeNode(7);
		
		TreeNode t = new TreeNode(1);
		t.right = new TreeNode(2);
		
		System.out.println(algos.diameterMain(t));
		
		ListNode l = new ListNode(1);
		l.next = new ListNode(2);
		l.next.next = new ListNode(3);
		l.next.next.next = new ListNode(4);
		l.next.next.next.next = new ListNode(5);
		algos.reorderList(l);
		System.out.println(l);
		
		/*
		TreeNodeWithNext tn = new TreeNodeWithNext(1);
		tn.left = new TreeNodeWithNext(2);
		tn.right = new TreeNodeWithNext(3);
		tn.left.left = new TreeNodeWithNext(4);
		tn.left.right = new TreeNodeWithNext(5);
		tn.right.left = new TreeNodeWithNext(6);
		tn.right.right = new TreeNodeWithNext(7);
		
		algos.connect(tn);
		System.out.println();
		*/
	
		t = new TreeNode(1);
		t.left = new TreeNode(2);
		t.right = new TreeNode(5);
		t.left.left = new TreeNode(3);
		t.left.right = new TreeNode(4);
		t.right.right = new TreeNode(6);
		
		algos.flatten(t);
		
		System.out.println(algos.increasingTriplet(new int[] {1,2,3,4,5}));
		System.out.println(algos.increasingTriplet(new int[] {5,4,3,2,1}));
		System.out.println(algos.increasingTriplet(new int[] {2,1,5,0,4,6}));
		System.out.println(algos.increasingTriplet(new int[] {0,4,2,1,0,-1,-3}));
		
		
		TreeNodeWithNext tn = new TreeNodeWithNext(1);
		tn.left = new TreeNodeWithNext(2);
		tn.right = new TreeNodeWithNext(3);
		tn.left.left = new TreeNodeWithNext(4);
		tn.left.right = new TreeNodeWithNext(5);
		tn.right.right = new TreeNodeWithNext(7);
		
		algos.connect2(tn);
		System.out.println(tn);
		
		tn = new TreeNodeWithNext(2);
		tn.left = new TreeNodeWithNext(1);
		tn.right = new TreeNodeWithNext(3);
		tn.left.left = new TreeNodeWithNext(0);
		tn.left.right = new TreeNodeWithNext(7);
		tn.right.left = new TreeNodeWithNext(9);
		tn.right.right = new TreeNodeWithNext(1);
		tn.left.left.left = new TreeNodeWithNext(2);
		tn.left.right.left = new TreeNodeWithNext(1);
		tn.left.right.right = new TreeNodeWithNext(0);
		tn.right.right.left = new TreeNodeWithNext(8);
		tn.right.right.right = new TreeNodeWithNext(8);
		tn.left.right.right.left = new TreeNodeWithNext(7);
		algos.connect2(tn);
		System.out.println(tn);
		
//		System.out.println(algos.generatePascalsTriangle(5));
//		System.out.println(algos.letterCombinations("23"));
//		System.out.println(algos.letterCombinationsRec("23"));
		
		System.out.println(algos.findMaxForm(new String[] {"1100","100000","011111"}, 6, 6));
		System.out.println(algos.findMaxForm(new String[] {"10","0001","111001","1","0"}, 4, 3));
		System.out.println(algos.findMaxFormTabulation(new String[] {"1100","100000","011111"}, 6, 6));
		System.out.println(algos.findMaxFormTabulation(new String[] {"10","0001","111001","1","0"}, 4, 3));
//		System.out.println(algos.longestValidParentheses1("(()"));
//		System.out.println(algos.longestValidParentheses1(")()())"));
		System.out.println(algos.longestIncreasingPath(new int[][] {{1}}));
		
		t = new TreeNode(2);
		t.right = new TreeNode(3);
		t.right.right = new TreeNode(4);
		t.right.right.right = new TreeNode(5);
		t.right.right.right.right = new TreeNode(6);
		System.out.println(algos.minDepth(t));
		
		List<List<Integer>> ll = Lists.newArrayList(
				Lists.newArrayList(2),
				Lists.newArrayList(3,4),
				Lists.newArrayList(6,5,7),
				Lists.newArrayList(4,1,8,3)
		);
		System.out.println(algos.minimumTotal(ll));
		System.out.println(algos.minimumTotalDP(ll));
		
		// LRU Cache
		LRUCache cache = new LRUCache(1);
		cache.put(2, 1);
		cache.get(2);
//		LRUCache cache = new LRUCache(3);
//		cache.put(1, 1);
//		cache.put(2, 2);
//		cache.put(3, 3);
//		cache.put(4, 4);
//		cache.get(3);
//		cache.get(2);
//		cache.get(1);
//		cache.put(5, 5);
//		cache.get(1);
//		cache.get(2);
//		cache.get(3);
//		cache.get(4);
//		cache.get(5);
	}
}
