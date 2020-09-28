package educative.searchandsort;

import helpers.Helpers;

public class EducativeJavaAlgosMain {

	public static void main(String[] args) {
		EducativeJavaAlgos algos = new EducativeJavaAlgos();
		
		Helpers.printArr(algos.findSum(new int[] {1,21,3,14,5,60,7,6}, 81));
		
		System.out.println(algos.searchRotatedArray(new int[] {7, 8, 9, 0, 3, 5, 6}, 3));

		System.out.println(algos.searchRotatedArray(new int[] {1,2,55,100,-100,-50,-10}, -100));
		System.out.println(algos.searchRotatedArray(new int[] {5,5,5,6,7,5}, 5));
		
		String[] arrForAnagrams = new String[] {
				"cat", "dog", "tac", "god", "act",  "tom marvolo riddle ","abc", "def",  "cab", "fed", 
				"clint eastwood ", "i am lord voldemort", "elvis", "old west action",  "lives"
		};
		
		System.out.println(algos.groupAnagrams(arrForAnagrams));

		System.out.println(algos.getMedian(new int[] {}, new int[] {1, 5, 8, 10, 20}));
		
		System.out.println(algos.findDuplicates(new int[] {1, 3, 4, 3, 5, 4, 100, 100}));
		
		int[][] matrix = new int[][] {
			{10, 11, 12, 13},
			{14, 15, 16, 17},
			{27, 29, 30, 31},
			{32, 33, 39, 80}
		};
		
		System.out.println(algos.findKey(matrix, 12));
		System.out.println(algos.findKey(matrix, 10));
		System.out.println(algos.findKey(matrix, 80));
		System.out.println(algos.findKey(matrix, 18));
		System.out.println(algos.findKey(matrix, 29));
		System.out.println(algos.findKey(matrix, 28));
		
		System.out.println(algos.calcFreq(new int[] {-5,-3,0,1,3,3,3,4,5}, 3));
		System.out.println(algos.calcFreqOpt(new int[] {-5,-3,0,1,3,3,3,4,5}, 3));

		System.out.println("Insert Position");
		System.out.println(algos.insertPosition(new int[] {1,3,5,6}, 5));
		System.out.println(algos.insertPosition(new int[] {1,3,5,6}, 4));
		System.out.println(algos.insertPosition(new int[] {1,3,5,6}, 7));
		System.out.println(algos.insertPosition(new int[] {1,3,5,6}, 1));
		System.out.println(algos.insertPosition(new int[] {1}, 5));
		System.out.println(algos.insertPosition(new int[] {6}, 5));
		
		System.out.println("Search for a string in a sparsed array");
		System.out.println(algos.searchForString(
				new String[] { "", "educative", "", "", "", "hello", "", "learning", "world", "", "", "" },
				"educative"));
		System.out.println(algos.searchForString2(
				new String[] { "", "educative", "", "", "", "hello", "", "learning", "world", "", "", "" },
				"educative"));
		
		int[] arr = new int[] {2, 0, 0, 1, 2, 1, 0};
		algos.mySwap(arr);
		Helpers.printArr(arr);
	}
}
