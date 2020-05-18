package programcreek.algolist;

import helpers.Helpers;

public class ProgCreekAlgosMain {

	public static void main(String[] args) {
		ProgCreekAlgos algos = new ProgCreekAlgos();
		Helpers.printArr(algos.twoSum(new int[] {2,7,11,15}, 9));
		Helpers.printArr(algos.twoSum(new int[] {9,6,2,8}, 14));

		Helpers.printArr(algos.twoSum2(new int[] {1,2,3,5,7}, 8));
		Helpers.printArr(algos.twoSum2(new int[] {2,5,8,12,15}, 27));
		
		TwoSum twoSum = new TwoSum();
		twoSum.add(1);
		twoSum.add(4);
		twoSum.add(1);
		twoSum.add(5);
		System.out.println(twoSum.find(2));
		System.out.println(twoSum.find(9));
		System.out.println(twoSum.find(3));
		
		System.out.println(algos.threeSum(new int[] {-1,0,1,2,-1,-4}));
		System.out.println(algos.minSubArrayLen(7, new int[] {2,3,1,2,4,3}));
		System.out.println(algos.minSubArrayLen(7, new int[] {2,3,3,1,6,2,4}));

		System.out.println(algos.removeDuplicates(new int[] {1,2,2,3,4,4,5,6,6}));
		System.out.println(algos.removeDuplicates(new int[] {1,1,1,1,1,1,2}));

		System.out.println(algos.removeDuplicates2(new int[] {1,1,1,1,1,1,2}));
		System.out.println(algos.removeDuplicates2(new int[] {1,2,2,2,4,4}));

		System.out.println(algos.removeElement(new int[] {1,2,2,4,4,2,5,6,2,1}, 2));

		int[] arr = new int[] {0,1,0,0,2,0,0,3,0,0,0,5,0};
		algos.moveZeroes(arr);
		Helpers.printArr(arr);
		
		System.out.println(algos.maxArea(new int[] {1,2,1,3,2,1,1,1}));

		System.out.println(algos.trappingWater(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));

		System.out.println(algos.summaryRanges(new int[] {0,1,2,4,5,7}));
		
		System.out.println(algos.editDistance("cba", "cd"));
		System.out.println(algos.editDistanceDP("cba", "cd"));

		int[] arr1 = new int[] {2,0};
		int[] arr2 = new int[] {1};

		algos.mergeSortedArrays(arr1, 1, arr2, 1);
		Helpers.printArr(arr1);
		
		System.out.println(algos.medianOfSortedArrays(new int[] {1,12,15,26,38}, new int[] {2,13,17,30,45}));
		
		// Min in rotated sorted array
		System.out.println(algos.findMin(new int[] {3,4,5,1,2}));
		System.out.println(algos.findMin(new int[] {4,5,6,7,0,1,2}));
		System.out.println(algos.findMin(new int[] {2,1}));
		System.out.println(algos.findMin(new int[] {2,3,4,5,1}));
		System.out.println(algos.findMin(new int[] {5,1,2,3,4}));
		System.out.println(algos.findMin(new int[] {2,2,2,0,1}));
		System.out.println(algos.findMinWithDup(new int[] {3,3,1,3}));
		System.out.println(algos.findMinWithDup(new int[] {3,3,3,3}));

		Helpers.printArr(algos.searchRange(new int[] {5,7,7,8,8,10}, 8));

		System.out.println(algos.searchInRotatedSortedArray(new int[] {4,5,6,7,0,1,2}, 0));
		System.out.println(algos.searchInRotatedSortedArrayWithDup(new int[] {1,3,1,1,1}, 3));
		
		System.out.println(algos.evalRPN(new String[] {"2", "1", "+", "3", "*"}));
		System.out.println(algos.evalRPN(new String[] {"4", "13", "5", "/", "+"}));
		System.out.println(algos.evalRPN(new String[] {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
		
	}
}
