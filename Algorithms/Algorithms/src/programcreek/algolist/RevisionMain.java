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
		
//		System.out.println(algos.canCompleteCircuit(new int[] {1,2,3,4,5}, new int[] {3,4,5,1,2}));
//		System.out.println(algos.canCompleteCircuit(new int[] {2,3,4}, new int[] {3,4,3}));
		System.out.println(algos.canCompleteCircuitOpt(new int[] {1,2,3,4,5}, new int[] {3,4,5,1,2}));
		System.out.println(algos.canCompleteCircuitOpt(new int[] {2,3,4}, new int[] {3,4,3}));
		System.out.println(algos.canCompleteCircuitOpt(new int[] {3,3,4}, new int[] {3,4,4}));

		// Verifying an Alien Dictionary
		System.out.println(algos.isAlienSorted(new String[] {"hello","leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
		System.out.println(algos.isAlienSorted(new String[] {"word","world","row"}, "worldabcefghijkmnpqstuvxyz"));
		System.out.println(algos.isAlienSorted(new String[] {"apple", "app"}, "abcdefghijklmnopqrstuvwxyz"));
		
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		ListNode l2 = l1.next;
		System.out.println(algos.getIntersectionNode(l1, l2));
		
		// Dungeon game
		int[][] dungeon = new int[][] {{-2,-3,3},{-5,-10,1},{10,30,-5}};
		System.out.println(algos.calculateMinimumHP(dungeon));
		
		System.out.println("=========================");
		System.out.println(algos.getNthCatalanNumber1(5));
		
		System.out.println(algos.getMaximumGold(new int[][] {{0,6,0},{5,8,7},{0,9,0}}));

		System.out.println(algos.coinChange(new int[] {1,2,5}, 11));
		System.out.println(algos.coinChangeDP(new int[] {1,2,5}, 11));
		System.out.println(algos.coinChangeDP(new int[] {2}, 3));

		System.out.println(algos.canPartition(new int[] {1,5,11,5}));
		System.out.println(algos.canPartition(new int[] {1,2,5}));
		
		System.out.println(algos.cutRod(new int[] {1,5,8,9,10,17,17,20}, 8));
		System.out.println(algos.cutRod(new int[] {3,5,8,9,10,17,17,20}, 8));

//		System.out.println(algos.lengthOfLIS(new int[] {10,9,2,5,3,7,101,18}));
//		System.out.println(algos.lengthOfLIS(new int[] {0,1,0,3,2,3}));
//		System.out.println(algos.lengthOfLIS(new int[] {7,7,7,7,7,7,7}));
//		System.out.println(algos.lengthOfLIS_DP(new int[] {10,9,2,5,3,7,101,18}));
//		System.out.println(algos.lengthOfLIS_DP(new int[] {0,1,0,3,2,3}));
//		System.out.println(algos.lengthOfLIS_DP(new int[] {7,7,7,7,7,7,7}));
		
//		System.out.println(algos.removeDuplicates("abcd", 2));
		System.out.println(algos.removeDuplicates("deeedbbcccbdaa", 3));
		
		System.out.println(algos.numSubmatrixSumTarget(new int[][] {{0,1,0},{1,1,1},{0,1,0}}, 0));
		
		System.out.println(algos.combinationSum4(new int[] {1,2,3}, 4));
		System.out.println(algos.combinationSum4(new int[] {9}, 3));
		System.out.println(algos.combinationSum4DP(new int[] {1,2,3}, 4));
		System.out.println(algos.combinationSum4DP(new int[] {9}, 3));
		
		System.out.println(algos.minPathSum(new int[][] {{1,3,1},{1,5,1},{4,2,1}}));

		Helpers.printArr(algos.maxSlidingWindow(new int[] {1,3,-1,-3,5,3,6,7}, 3));
		Helpers.printArr(algos.nextLargerElement(new int[] {4, 5, 2, 25}));
		Helpers.printArr(algos.nextLargerElement(new int[] {13, 7, 6, 12}));
	
		System.out.println("=========== Max Events ===========");
		System.out.println(algos.maxEvents(new int[][] {{1,2},{2,3},{3,4}}));
		System.out.println(algos.maxEvents(new int[][] {{1,2},{2,3},{3,4},{1,2}}));
		System.out.println(algos.maxEvents(new int[][] {{1,4},{4,4},{2,2},{3,4},{1,1}}));
		System.out.println(algos.maxEvents(new int[][] {{1,5},{1,5},{1,5},{2,3},{2,3}}));
		System.out.println(algos.maxEvents(new int[][] {{1,2},{2,2},{3,3},{3,4},{3,4}}));
		
		TreeNode tt = new TreeNode(-2);
		tt.left = new TreeNode(-3);
		System.out.println(algos.pathSum(tt, -5));
		
		List<List<Integer>> triangle = Lists.newArrayList(
				Lists.newArrayList(2), Lists.newArrayList(3,4), Lists.newArrayList(6,5,7), Lists.newArrayList(4,1,8,3)
				);
		System.out.println(algos.minimumTotal1(triangle));
		
		System.out.println(algos.trap1(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
		
		System.out.println("Count binary substring");
		System.out.println(algos.countBinarySubstrings("00110011"));
		
		System.out.println("Farthest building");
		System.out.println(algos.furthestBuilding(new int[] {4,2,7,6,9,14,12}, 5, 1));
		System.out.println(algos.furthestBuilding(new int[] {4,12,2,7,3,18,20,3,19}, 10, 2));
		System.out.println(algos.furthestBuilding(new int[] {14,3,19,3}, 17, 0));

		System.out.println(algos.furthestBuildingOpt(new int[] {4,2,7,6,9,14,12}, 5, 1));
		System.out.println(algos.furthestBuildingOpt(new int[] {4,12,2,7,3,18,20,3,19}, 10, 2));
		System.out.println(algos.furthestBuildingOpt(new int[] {14,3,19,3}, 17, 0));

		System.out.println(algos.isPowerOfThree(27));
		
		TreeNode res11 = algos.buildTreeIP(new int[] {9,3,15,20,7}, new int[] {9,15,7,20,3});
		res11 = algos.buildTreeIP(new int[] {1,2,3,4}, new int[] {2,1,4,3});
		
		ListNode head = new ListNode(-10);
		head.next = new ListNode(-3);
		head.next.next = new ListNode(0);
		head.next.next.next = new ListNode(5);
		head.next.next.next.next = new ListNode(9);
		res11 = algos.sortedListToBST(head);
		char[][] charMat = new char[][] {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
		algos.solve(charMat);
		charMat = new char[][] { { 'O', 'X', 'X', 'O', 'X' }, { 'X', 'O', 'O', 'X', 'O' }, { 'X', 'O', 'X', 'O', 'X' },
				{ 'O', 'X', 'O', 'O', 'O' }, { 'X', 'X', 'O', 'X', 'O' } };
		algos.solve(charMat);
		charMat = new char[][] { { 'X', 'O', 'X', 'O', 'X', 'O' }, { 'O', 'X', 'O', 'X', 'O', 'X' },
				{ 'X', 'O', 'X', 'O', 'X', 'O' }, { 'O', 'X', 'O', 'X', 'O', 'X' } };
		algos.solve(charMat);
		System.out.println();
		
		ListNode sortList = new ListNode(4);
		sortList.next = new ListNode(2);
		sortList.next.next = new ListNode(1);
		sortList.next.next.next = new ListNode(3);
		
		System.out.println(algos.insertionSortList(sortList));
		System.out.println(algos.convertToTitle(28));
		
		TreeNode rightView = new TreeNode(1);
		rightView.left = new TreeNode(2);
		rightView.right = new TreeNode(3);
		rightView.left.right = new TreeNode(5);
		rightView.right.right = new TreeNode(4);
		
		System.out.println(algos.rightSideView(rightView));

		ListNode l3 = ListNode.getLinkedList(new int[] {1,2,6,3,4,5,6});
		l3 = algos.removeElements(l3, 6);
		System.out.println(l3);
		
		l3 = ListNode.getLinkedList(new int[] {1,7,5,1,9,2,5,1});
		Helpers.printArr(algos.nextLargerNodes(l3));
		
		System.out.println("==== Course Schedule ====");
		Helpers.printArr(algos.findOrder(2, new int[][] {{1,0}}));
		Helpers.printArr(algos.findOrder(4, new int[][] {{1,0},{2,0},{3,1},{3,2}}));
		
//		System.out.println(algos.combinationSum3(3, 9));
		System.out.println(algos.combinationSum3(9, 45));
		
//		System.out.println(algos.countPrimesOpt(10));
//		System.out.println(algos.rob(new int[] {2,3,2}));
//		System.out.println(algos.rob(new int[] {1,2,3,1}));
//		System.out.println(algos.rob(new int[] {6}));
//		System.out.println(algos.rob(new int[] {1, 2, 3, 4, 5, 1, 2, 3, 4, 5}));
		
		System.out.println(algos.maximalSquare(new char[][] { { '1', '0', '1', '0', '0' }, { '1', '0', '1', '1', '1' },
				{ '1', '1', '1', '1', '1' }, { '1', '0', '0', '1', '0' } }));
		
		TreeNode t1 = new TreeNode(37);
		t1.left = new TreeNode(-34);
		t1.right = new TreeNode(48);
		t1.left.right = new TreeNode(-100);
		t1.right.left = new TreeNode(-100);
		t1.right.right = new TreeNode(48);
		
		System.out.println(algos.binaryTreePaths(t1));
		
		System.out.println(algos.isMatch("aa", "*"));
		System.out.println(algos.isMatch("aa", "a"));
		System.out.println(algos.isMatch("cb", "?a"));
		System.out.println(algos.isMatch("cb", "?b"));
		System.out.println(algos.isMatch("adceb", "*a*b"));
		System.out.println(algos.isMatch("acdcb", "a*c?b"));
		System.out.println(algos.isMatch("", "******"));
		
		System.out.println(algos.isMatchRegex("mississippi", "mis*is*p*."));
		System.out.println(algos.isMatchRegex("aa", "a*"));
		System.out.println(algos.isMatchRegex("ab", ".*"));
		
//		System.out.println("Scramble String");
//		System.out.println(algos.isScramble("great", "rgeat"));
//		System.out.println(algos.isScramble("abcde", "caebd"));
		
//		System.out.println(algos.shortestSuperstring(new String[] {"alex","loves","leetcode"}));
//		System.out.println(algos.shortestSuperstring(new String[] {"catg","ctaagt","gcta","ttca","atgcatc"}));
//		System.out.println(algos.shortestSuperstring(new String[] {"abc", "ab"}));
		
		System.out.println(algos.compress(new char[] {'a','a','b','b','c','c','c'}));
		System.out.println(algos.compress(new char[] {'a'}));
		System.out.println(algos.compress(new char[] {'a','b','b','b','b','b','b','b','b','b','b','b','b'}));
		char[] chars = new char[] {'a','a','a','b','b','a','a'};
		chars = new char[] {'a','b','c'};
		System.out.println(algos.compress(chars));
		Helpers.printArr(chars);
		
		System.out.println(algos.partitionLabels("ababcbacadefegdehijhklij"));
	}
}











