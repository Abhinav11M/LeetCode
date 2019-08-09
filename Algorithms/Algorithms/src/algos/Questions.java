package algos;

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
}
