package educative.twopointers;

import helpers.Helpers;

public class TwoPointersMain {

	public static void main(String[] args) {
		TwoPointers twoPointers = new TwoPointers();
		Helpers.printArr(twoPointers.pairWithTargetSum(new int[] {1, 2, 3, 4, 6}, 6));
		
		System.out.println(twoPointers.removeDuplicates(new int[] {2, 2, 2, 11}));
		System.out.println(twoPointers.removeDuplicates(new int[] {0,0,1,1,1,2,2,3,3,4}));
		
		Helpers.printArr(twoPointers.makeSortedArraySquares(new int[] {-2, -1, 0, 2, 3}));
	}
	
	

}
