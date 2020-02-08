package educative.slidingwindow;

import java.util.Arrays;

public class SlidingWindowMain {
	public static void main(String[] args) {
		SlidingWindow slidingWindow = new SlidingWindow();
		
		double[] aveArr = slidingWindow.findAveragesOfSubarrayOfSizeK(5, 
				new int[] { 1, 3, 2, 6, -1, 4, 1, 8, 2 });
		printArr(Arrays.stream(aveArr).boxed().toArray(Double[]::new));
		
		System.out.println(slidingWindow.findMaxSumSubArray(3, new int[] {2, 1, 5, 1, 3, 2}));
		System.out.println(slidingWindow.findMaxSumSubArray(2, new int[] {2, 3, 4, 1, 5}));
		
		System.out.println(slidingWindow.findMinSubArray(7, new int[] {2, 1, 5, 2, 3, 2}));
		System.out.println(slidingWindow.findMinSubArray(7, new int[] {2, 1, 5, 2, 8}));
		System.out.println(slidingWindow.findMinSubArray(8, new int[] {3, 4, 1, 1, 6}));
		
		System.out.println(slidingWindow.LongestSubstringKDistinct("cbbebi", 3));
		System.out.println(slidingWindow.LongestSubstringKDistinct("araaci", 2));
		System.out.println(slidingWindow.LongestSubstringKDistinct("araaci", 1));
	}
	
	public static <T> void printArr(T[] arr) { 
		for(T data : arr) {
			System.out.print(data+",");
		}
		System.out.println();
	}
}
