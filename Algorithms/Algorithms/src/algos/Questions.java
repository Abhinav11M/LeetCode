package algos;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Questions {

	/*
	 * In a 2-D matrix of m*n select m-1 elements from each row such that that the
	 * resulting sum is minimum and each column is selected atleast once.
	 * Ex - [{2 3 5}, {3 2 5}, {4 4 7}] Selecting (2,3) from 1st row, (2,5) from 2nd row and
	 * (4,4) from 3rd row would result in minimum sum of 20
	 */
	public static void ques1(int[][] matrix) {
		for(int i = 0; i < matrix.length; ++i) {
			int index = findLargest(matrix[i]);
			for(int j = 0; j < matrix[i].length; ++j) {
				if(j != index) {
					System.out.print(matrix[i][j] + ", ");
				}
			}
			System.out.println();
		}
	}

	private static int findLargest(int[] arr) {
		int max = arr[0];
		int index = 0;
		
		for(int i = 1; i < arr.length; ++i) {
			if(arr[i] > max) {
				max = arr[i];
				index = i;
			}
		}
		return index;
	}
	
	
	/**
	 * 
	 */
	public static List<Integer> findPerm(final String str, int n) {
		int arr[] = new int[str.length()];
		
		int i = 0;
		int d = 0;
		
		for(int j = arr.length-1; j >= 0; j--) {
			if(str.charAt(j) == 'D') {
				d = i*(-1) + 1;
				arr[j] = d;
			}
			else {
				i = d*(-1) - 1;
				arr[j] = i;
			}
		}
		
		List<Integer> res = new LinkedList<>();
		// Starting from last
		int val = 0;
		for(int j = arr.length-1; j >=0; --j) {
			if(j == arr.length-1) {
				res.add(0, val);
			}
			else {
				val += arr[j+1];
				res.add(0, val);
			}
		}
		res.add(0, val+arr[0]);
		// Find min
		int min = Collections.min(res);
		// Add min+1 in each
		return res.stream().map(x -> x+Math.abs(min)+1).collect(Collectors.toList());
	}
}
