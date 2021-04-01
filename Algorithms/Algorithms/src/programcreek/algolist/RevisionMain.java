package programcreek.algolist;

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
	}
}
