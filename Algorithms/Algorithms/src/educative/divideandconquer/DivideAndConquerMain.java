package educative.divideandconquer;

import helpers.Helpers;

public class DivideAndConquerMain {

	public static void main(String[] args) {
		DivideAndConquerQues algos = new DivideAndConquerQues();
		
		Helpers.printArr(algos.pascalTriangle(1));
		Helpers.printArr(algos.pascalTriangle(2));
		Helpers.printArr(algos.pascalTriangle(3));
		Helpers.printArr(algos.pascalTriangle(4));
		Helpers.printArr(algos.pascalTriangle(5));
		System.out.println("---------------");
		Helpers.printArr(algos.pascalTriangleRecur(1));
		Helpers.printArr(algos.pascalTriangleRecur(2));
		Helpers.printArr(algos.pascalTriangleRecur(3));
		Helpers.printArr(algos.pascalTriangleRecur(4));
		Helpers.printArr(algos.pascalTriangleRecur(5));
		System.out.println("---------------");
		System.out.println(algos.gcd(15, 20));
		System.out.println(algos.gcd(15, 75));
		System.out.println(algos.gcd(13, 25));
		System.out.println(algos.gcd(34, 15));
		System.out.println(algos.gcd(17, 159));
		System.out.println(algos.gcd(39, 2));
		System.out.println(algos.gcd(823, 77));
		System.out.println(algos.gcd(10, 0));
		System.out.println("---------------");
		System.out.println(algos.findPeak(new int[] {13, 27, 54, 31, 99, 11}));
		System.out.println(algos.findPeak(new int[] {98, 99, 100}));
		System.out.println(algos.findPeak(new int[] {55, 54, 53}));
		System.out.println("---------------");
		int arr[] = new int[] {10,7,-5,15,6};
		System.out.println(algos.maxSumArr(arr, 0, arr.length));
		arr = new int[] {-1,-2,-3,-4,-5};
		System.out.println(algos.maxSumArr(arr, 0, arr.length));
		arr = new int[] {1,3,5,7,9};
		System.out.println(algos.maxSumArr(arr, 0, arr.length));
		System.out.println("---------------");
		arr = new int[] {10,7,-5,15,6};
//		System.out.println(algos.maxSumArr2(arr, 0, arr.length));
//		arr = new int[] {-1,-2,-3,-4,-5};
//		System.out.println(algos.maxSumArr2(arr, 0, arr.length));
//		arr = new int[] {1,3,5,7,9};
//		System.out.println(algos.maxSumArr2(arr, 0, arr.length));
//		arr = new int[] {1,2,3,-7,5,3,6};
//		System.out.println(algos.maxSumArr2(arr, 0, arr.length));
//		arr = new int[] {4,3,-8,3,2};
//		System.out.println(algos.maxSumArr2(arr, 0, arr.length));
		arr = new int[] {0, 1, 2, 5}; // 8
		System.out.println(algos.maxSumArr2(arr, 0, arr.length));
		arr = new int[] {-2, 10, 7, -5, 15, 6}; // 33
		System.out.println(algos.maxSumArr2(arr, 0, arr.length));
		arr = new int[] {-1, -3, 4, -1, -2, 1, 5, -3}; // 7
		System.out.println(algos.maxSumArr2(arr, 0, arr.length));
		arr = new int[] {0, 1, 2, 5}; // 8
		System.out.println(algos.maxSumArrKadanes(arr, 0, arr.length-1));
		arr = new int[] {-2, 10, 7, -5, 15, 6}; // 33
		System.out.println(algos.maxSumArrKadanes(arr, 0, arr.length-1));
		arr = new int[] {-1, -3, 4, -1, -2, 1, 5, -3}; // 7
		System.out.println(algos.maxSumArrKadanes(arr, 0, arr.length-1));
		System.out.println("---------------");
		System.out.println("===== Missing Number =====");
		System.out.println(algos.missingNumber(new int[] {1,2,3,4,6,7,8,9,10}));
		System.out.println(algos.missingNumber(new int[] {1,2,3,4,5,6,7}));
		System.out.println(algos.missingNumber(new int[] {2,3,4}));
		System.out.println("===== Closest Number =====");
		System.out.println(algos.closestNumber(new int[] {1, 2, 4, 5, 6, 6, 8, 9}, 11));
		System.out.println(algos.closestNumber(new int[] {3, 5, 7, 9, 11, 17}, 7));
		
		arr = new int[] {1,3,5,7,2,4,6,8};
		algos.shuffleIntegers(arr);
		Helpers.printArr(arr);
		arr = new int[] {1,3,5,7,2,4,6,8};
		algos.shuffleIntegers2(arr);
		Helpers.printArr(arr);
		arr = new int[] {1,3,5,2,4,6};
		algos.shuffleIntegers2(arr);
		Helpers.printArr(arr);

		System.out.println("==== Mins Steps to collect coins ====");
		System.out.println(algos.minSteps(new int[] { 3, 1, 1, 5, 1 }));
		System.out.println(algos.minSteps(new int[] { 4, 2, 1, 5, 2 }));
		
		int arr1[][] = {{ 16, 25, 36, 79 }, { 2, 9, 15, 38 }, { 22, 55, 67, 98 }}; 
		int k = arr1.length, n = arr1[k - 1].length;
		int[] result = new int[n * k];
		
		System.out.println("==== Merge sorted arrays ====");
		algos.mergeSortedArrays(arr1, k, result);
		Helpers.printArr(result);
		int[] out = new int[2];
		algos.findFloorCeiling(new int[] { 1, 2, 3, 5, 7 }, 4, out);
		Helpers.printArr(out);

		System.out.println("==== Inversion count ====");
		System.out.println(algos.countInversions(new int[] { 9, 5, 6, 11, 8, 10 }));
	}
}
