package educative.twopointers;

import helpers.Helpers;

public class TwoPointersMain {

	public static void main(String[] args) {
		TwoPointers twoPointers = new TwoPointers();
		Helpers.printArr(twoPointers.pairWithTargetSum(new int[] {1, 2, 3, 4, 6}, 6));
		
		System.out.println(twoPointers.removeDuplicates(new int[] {2, 2, 2, 11}));
		System.out.println(twoPointers.removeDuplicates(new int[] {0,0,1,1,1,2,2,3,3,4}));
		
		Helpers.printArr(twoPointers.makeSortedArraySquares(new int[] {-2, -1, 0, 2, 3}));

		System.out.println(twoPointers.TripletSumToZero(new int[] {-2, -1, 0, 2, 3}));

		System.out.println(twoPointers.tripletSumCloseToTarget(new int[] {-2, 0, 1, 2}, 2));
		System.out.println(twoPointers.tripletSumCloseToTarget(new int[] {-1,2,1,-4}, 1));
		
		System.out.println(twoPointers.tripletWithSmallerSum(new int[] {-1, 4, 2, 1, 3}, 5));
		
//		System.out.println(twoPointers.subarrayProductLessThanK(new int[] {1,2,3,4,5}, 5));
		System.out.println(twoPointers.subarrayProductLessThanKOpt(new int[] {1,2,3,4,5}, 5));
		System.out.println(twoPointers.subarrayProductLessThanKOpt(new int[] {10,9,10}, 60));
		
		System.out.println(twoPointers.subarrayProductLessThanKOpt_leetCode(new int[] {10,9,10,4,3,8,3,3,6,2,10,10,9,3}, 19));
		
		int[] arr = new int[] {1, 0, 2, 1, 0};
		arr = new int[] {1,2};
		arr = new int[] {2,0,1};
		twoPointers.sort(arr);
		Helpers.printArr(arr);
		
		System.out.println(twoPointers.searchQuadruplets(new int[] {2, 0, -1, 1, -2, 2}, 2));
		System.out.println(twoPointers.backspaceCompare("xy#z","xzz#"));
		System.out.println(twoPointers.backspaceCompare("xy#z","xyz#"));
		System.out.println(twoPointers.backspaceCompare("xp#","xyz##"));
		System.out.println(twoPointers.backspaceCompare("xywrrmp","xywrrmu#p"));
		
		System.out.println(twoPointers.shortestWindowSort(new int[] {1, 2, 5, 3, 7, 10, 9, 12}));
		System.out.println(twoPointers.shortestWindowSort(new int[] {1, 3, 2, 0, -1, 7, 10}));
		System.out.println(twoPointers.shortestWindowSort(new int[] {1, 2, 3}));
		System.out.println(twoPointers.shortestWindowSort(new int[] {3, 2, 1}));
	}
}
