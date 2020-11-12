package helpers;

public class Helpers {
	public static <T> void printArr(T[] arr) {
		for (T data : arr) {
			System.out.print(data + ",");
		}
		System.out.println();
	}

	public static void printArr(int[] arr) {
		for (int data : arr) {
			System.out.print(data + ",");
		}
		System.out.println();
	}
	
	public static void printArr(double[] arr) {
		for (double data : arr) {
			System.out.print(data + ",");
		}
		System.out.println();
	}


	public static void printArr(int[][] arr) {
		for (int i = 0; i < arr.length; ++i) {
			for (int j = 0; j < arr[i].length; ++j) {
				System.out.print(arr[i][j] + ",");
			}
			System.out.println();
		}
	}
}
