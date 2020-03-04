package educative.cyclicsort;

import helpers.Helpers;

public class CyclicSortMain {
	public static void main(String[] args) {
		CyclicSort cyclicSort = new CyclicSort();
		System.out.println(cyclicSort.findMissingNumbers(new int[] {1,2,2,4,5,5,8}));
		
		int arr[] = new int[] {4,1,5,2,3};
		cyclicSort.cyclicSort2(arr);
		Helpers.printArr(arr);
		
		System.out.println(cyclicSort.findMissingNumber(new int[] {4,0,1,3}));
		System.out.println(cyclicSort.findDuplicate(new int[] {3,2,1,3}));
		System.out.println(cyclicSort.findAllDuplicates(new int[] {3,4,4,5,5}));
		System.out.println(cyclicSort.findAllDuplicates(new int[] {5,4,7,2,3,5,3}));
		Helpers.printArr(cyclicSort.findCorruptedNumbers(new int[] {3, 1, 2, 5, 2}));
		Helpers.printArr(cyclicSort.findCorruptedNumbers(new int[] {3, 1, 2, 3, 6, 4}));
//		System.out.println(cyclicSort.findFirstMissingPositiveInteger(new int[] {2,5,-1,4,-2,1,0}));
//		System.out.println(cyclicSort.findFirstMissingPositiveInteger(new int[] {1,1}));
//		System.out.println(cyclicSort.findFirstMissingPositiveInteger(new int[] {3,4,-1,1}));
		System.out.println(cyclicSort.findFirstMissingPositiveInteger(new int[] {2147483647,2147483646,2147483645,3,2,1,-1,0,-2147483648}));
	}
}
