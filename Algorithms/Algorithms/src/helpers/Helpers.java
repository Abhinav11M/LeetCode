package helpers;

public class Helpers {
	public static <T> void printArr(T[] arr) { 
		for(T data : arr) {
			System.out.print(data+",");
		}
		System.out.println();
	}
	
	public static void printArr(int[] arr) {
		for(int data : arr) {
			System.out.print(data+",");
		}
		System.out.println();
	}
}
