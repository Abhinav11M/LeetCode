package leetcode30daychallenge;

import datastructures.TreeNode;
import helpers.Helpers;

public class LeetCode30DayChallengeMain {
	public static void main(String[] args) {
		LeetCode30DayChallenge leet = new LeetCode30DayChallenge();
		System.out.println(leet.singleNumber(new int[] {2,2,1}));
		System.out.println(leet.singleNumber(new int[] {4,1,2,1,2}));
		
		System.out.println(leet.isHappy(19));
		System.out.println(leet.isHappy(12));
		System.out.println(leet.maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
		System.out.println(leet.maxSubArray(new int[] {-2,-3,-1,-5}));

		int[] arr = new int[] {0,1,0,3,12};
		leet.moveZeroes(arr);
		Helpers.printArr(arr);
		
		System.out.println(leet.groupAnagrams(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"}));
		System.out.println(leet.groupAnagrams(new String[] {"tao","pit","cam","aid","pro","dog"}));
		System.out.println(leet.isAnagram("pit", "pro"));
		
		System.out.println("*******");
//		System.out.println(leet.countElements1(new int[] {1,2,3}));
//		System.out.println(leet.countElements1(new int[] {1,1,3,3,5,5,7,7}));
//		System.out.println(leet.countElements1(new int[] {1,3,2,3,5,0}));
//		System.out.println(leet.countElements1(new int[] {1,1,2,2}));
//		System.out.println(leet.countElements1(new int[] {4,10,11,11,1,9,6,2,4,5,8}));
//		System.out.println(leet.countElements1(new int[] {1,1,2,3,4,5,6,7,7}));
		System.out.println(leet.countElements1(new int[] {0,3,4,5,5,6,7,8,8,8,9,9,10,10,12,12}));
		System.out.println("*******");
		System.out.println(leet.backspaceCompare1("ab##","cd##"));
		System.out.println(leet.backspaceCompare1("ab#c","ad#c"));
		System.out.println(leet.backspaceCompare1("ab##","cd##"));
		System.out.println(leet.backspaceCompare1("a##c","#a#c"));
		System.out.println(leet.backspaceCompare1("y#fo##f","y#f#o##f"));
		System.out.println(leet.backspaceCompare1("a#c","b"));
		
		MinStack minStack = new MinStack();
		minStack.push(2147483647);
		minStack.push(-2147483648);
		System.out.println(minStack.top());
		System.out.println(minStack.getMin());
//		minStack.push(-2);
//		minStack.push(0);
//		minStack.push(-3);
//		System.out.println(minStack.getMin());
//		minStack.pop();
//		System.out.println(minStack.top());
//		System.out.println(minStack.getMin());
		
		
		TreeNode root = new TreeNode(1);  
	    root.left = new TreeNode(2);  
	    root.right = new TreeNode(3);  
	    root.left.left = new TreeNode(4);  
	    root.left.right = new TreeNode(5);
	    
	    System.out.println(leet.diameterOfBinaryTreeOpt(root));
	    
	    System.out.println(leet.lastStoneWeightOpt(new int[] {2,7,4,1,8,1}));
	    System.out.println(leet.lastStoneWeightOpt(new int[] {1,3}));
	    System.out.println(leet.lastStoneWeightOpt(new int[] {3,7,2}));
	    System.out.println(leet.lastStoneWeightOpt(new int[] {9,3,2,10}));
	    System.out.println(leet.lastStoneWeightOpt(new int[] {6,7,6,7,9}));

	    System.out.println(leet.findMaxLength(new int[] {0,1,0}));
	    System.out.println(leet.findMaxLength(new int[] {0,0,0,1,1,1,0}));
	    System.out.println(leet.findMaxLength(new int[] {0,1,1,0,1,1,1,0}));
	    System.out.println(leet.maxSubArrayLen(new int[] {0,0,1,0,0,0,1,1}));

	    Helpers.printArr(leet.productExceptSelf(new int[] {1,2,3,4}));
	    Helpers.printArr(leet.productExceptSelf(new int[] {0,1,2,3,0,4}));
	    Helpers.printArr(leet.productExceptSelf(new int[] {0,1,2,3,4}));
	    Helpers.printArr(leet.productExceptSelf(new int[] {1,2,3,0,4}));
	    Helpers.printArr(leet.productExceptSelf(new int[] {0,0}));
	    Helpers.printArr(leet.productExceptSelf(new int[] {1,0}));
	    
	    System.out.println(leet.minPathSum(new int[][] { {1,3,1}, {1,5,1}, {4,2,1} }));
	    
	    System.out.println(leet.checkValidString("()"));
	    System.out.println(leet.checkValidString("(*)"));
	    System.out.println(leet.checkValidString("(*))"));
	    System.out.println(leet.checkValidString("())*"));
	    System.out.println(leet.checkValidString("*(*"));
	    
//	    System.out.println(leet.stringShift("abc", new int[][] { {0,1}, {1,2} }));
	    System.out.println(leet.stringShift("abcdefg", new int[][] { {1,1}, {1,1}, {0,2}, {1,3} }));
	}
}
