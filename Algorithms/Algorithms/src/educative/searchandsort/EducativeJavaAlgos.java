package educative.searchandsort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class EducativeJavaAlgos {
/**
 * Searching and Sorting
 */
	/**
	 * Question 1 : Two number sum
	 */
	public int[] findSum(int[] arr, int n) {
		Set<Integer> set = new HashSet<>();
		int i = 0;
		while(i < arr.length) {
			int elem = arr[i];
			if(set.contains(n-elem)) {
				return new int[] {n-elem, elem};
			}
			set.add(elem);
			++i;
		}
		
		return new int[0];
	}
	
	/**
	 * Question 2: Search in rotated array
	 */
	public int searchRotatedArray(int arr[], int n) {

		int start = 0;
		int end = arr.length-1;
		
		while(start <= end) {
			int mid = start + (end-start)/2;
			if(arr[mid] == n) {
				return mid;
			}
			else if(arr[mid] > arr[end]) { // Right half not sorted (left-rotation)
				if(n < arr[mid] && n >= arr[0]) {
					end = mid-1;
				}
				else {
					start = mid+1;
				}
			}
			else if(arr[0] > arr[mid]) { // Left half not sorted (right-rotation)
				if(n > arr[mid] && n<= arr[end]) {
					start = mid+1;
				}
				else {
					end = mid-1;
				}
			}
			else { // all sorted
				if(arr[mid] > n) {
					end = mid-1;
				}
				else {
					start = mid+1;
				}
			}
		}
		
		return -1;
	}
	
	/**
	 * Question 3: Group Anagrams
	 */
	public List<List<String>> groupAnagrams(String arr[]) {
		
		Map<Integer, List<String>> groups = new HashMap<>();
		
		for(String str : arr) {
			int hash = calculateHash(str);
			groups.computeIfAbsent(hash, x -> new ArrayList<>()).add(str);
		}
		
		return groups.values().stream().collect(Collectors.toList());
	}
	
	private int calculateHash(String str) {
		int val = 0;
		for(char ch : str.toCharArray()) {
			val += ch-'a';
		}
		
		return val;
	}
	
	/**
	 * Question 4: Median of two sorted arrays
	 * <a>https://www.youtube.com/watch?v=LPFhl65R7ww&ab_channel=TusharRoy-CodingMadeSimple</a>
	 */
	public double getMedian(int arrX[], int arrY[]) {
		// Always make sure that the 1st array is the smaller one
		if(arrX.length > arrY.length) {
			return getMedian(arrY, arrX);
		}
		
		int s1 = arrX.length, s2 = arrY.length;
		
		int start = 0; // Meaning 0 elements are left to this breaking line
		int end = arrX.length; // Meaning all elements are to the left of the breaking line
		
		while(start <= end) {
			int partitionX = (start+end)/2;
			int partitionY = (s1+s2+1)/2 - partitionX;
			
			// Xs on boundaries
			int leftX = partitionX == 0 ? Integer.MIN_VALUE : arrX[partitionX-1];
			int rightX = partitionX == s1 ? Integer.MAX_VALUE : arrX[partitionX];
			
			// Ys on boundaries
			int leftY = partitionY == 0 ? Integer.MIN_VALUE : arrY[partitionY-1];
			int rightY = partitionY == s2 ? Integer.MAX_VALUE : arrY[partitionY];
			
			if((leftX < rightY) && (rightX > leftY)) { // Found the partitions
				if((s1+s2)%2 == 0) { // Even number of elements
					return (Math.max(leftX, leftY) + Math.min(rightX, rightY))/2.0;
				}
				else { // Odd number of elements
					return Math.max(leftX, leftY);
				}
			}
			else if(leftX > rightY) { // move X towards left
				end = partitionX-1;
			}
			else {
				start = partitionX+1;
			}
		}
		
		return -1;
	}
	
	/**
	 * Question 5: Find duplicates in an array
	 */
	public ArrayList <Integer> findDuplicates(int arr[]) {
		
		Map<Integer, Integer> map = new HashMap<>();
		
		for(int val : arr) {
			map.put(val, map.getOrDefault(val, 0)+1);
		}
		
		return map.entrySet().stream()
				.filter(x -> x.getValue() > 1)
				.map(x -> x.getKey())
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	/**
	 * Question 6: Search in a sorted matrix
	 */
	public boolean findKey(int[][] matrix, int target) {
		if(matrix == null || matrix.length == 0) {
			return false;
		}
		
		int nRows = matrix.length;
		int nCols = matrix[0].length;
		
		int start = 0, end = nRows*nCols-1;
		
		while(start <= end) {
			int mid = start + (end-start)/2;
			
			int[] rc = mapToRowCol(mid, nCols);
			
			if(matrix[rc[0]][rc[1]] == target) {
				return true;
			}
			else if(matrix[rc[0]][rc[1]] > target) { // move left
				end = mid - 1;
			}
			else {
				start = mid + 1;
			}
		}
		
		return false;
	}

	private static int[] mapToRowCol(int index, int nCols) {

		int[] key = new int[2];
		key[0] = index/nCols;
		key[1] = index%nCols;
		
		return key;
	}

	/**
	 * Question 7: Count element occurrence
	 */
	public int calcFreq(int arr[], int key) {
		
		int start = 0, end = arr.length-1;
		
		while(start <= end) {
			int mid = start + (end-start)/2;
			
			if(arr[mid] == key) {
				// count the left and right elements
				int count = 1;
				int l = mid-1;
				int r = mid+1;
				
				while(l >= start && arr[l] == key) {
					++count;
					--l;
				}
				while(r <= end && arr[r] == key) {
					++count;
					++r;
				}
				
				return count;
			}
			else if(key < arr[mid]) { // move left
				end = mid-1;
			}
			else {
				start = mid+1;
			}
		}
		
		return 0;
	}

	public int calcFreqOpt(int arr[], int key) {
		int start = 0, end  = arr.length-1, resLeftIdx = -1;
		
		while(start <= end) {
			int mid = start + (end-start)/2;
			
			if(arr[mid] == key) {
				resLeftIdx = mid;
				end = mid-1; // Moving left to find the start index of key in the array
			}
			else if(key < arr[mid]) {
				end = mid-1;
			}
			else {
				start = mid+1;
			}
		}
		
		if(resLeftIdx == -1) { // element not found
			return 0;
		}
		
		start = 0;
		end  = arr.length-1;
		int resRightIdx = -1;
		while(start <= end) {
			int mid = start + (end-start)/2;
			
			if(arr[mid] == key) {
				resRightIdx = mid;
				start = mid+1; // Moving right to find the last index of key in the array
			}
			else if(key < arr[mid]) {
				end = mid-1;
			}
			else {
				start = mid+1;
			}
		}
		
		return resRightIdx - resLeftIdx + 1;
	}
	
	/**
	 * Question 8: Search Insert Position
	 */
	public int insertPosition(int arr[], int target) {
		if(arr == null || arr.length == 0) {
			return 0;
		}

		int start = 0, end = arr.length-1;
		int mid = -1;
		
		while(start <= end) {
			mid = start + (end-start)/2;
			
			if(target == arr[mid]) {
				return mid;
			}
			else if(target < arr[mid]) {
				end = mid-1;
			}
			else {
				start = mid+1;
			}
		}
		
		return arr[mid] < target ? mid+1 : mid;
	}
	
	/**
	 * Sparse Search
	 * Implement a function that returns the index of the target 
	 * string in a sorted and sparsed array of strings.
	 * array = {"", "educative", "", "", "",  "hello", "", "learning", "world", "", "", ""};
	 * target = "educative"
	 * output : 1
	 */
	public int searchForString(String[] array, String target) {
		return searchForString(array, target, 0, array.length-1);
	}

	private int searchForString(String[] array, String target, int start, int end) {
		if(start > end) {
			return -1;
		}
		
		while(start <= end) {
			int mid = start + (end-start)/2;
			
			if(array[mid].equals(target)) {
				return mid;
			}
			else if(array[mid].equals("")) {
				int left = searchForString(array, target, start, mid-1);
				if(left != -1) {
					return left;
				}
				int right = searchForString(array, target, mid+1, end);
				return right;
			}
			else if(array[mid].compareTo(target) < 0) { // go right as mid < target
				start = mid+1;
			}
			else {
				end = mid-1;
			}
		}
		
		return -1;
	}

	/**
	 * Method 2 for above question
	 */
	public int searchForString2(String[] array, String target) {
		return searchForString2(array, target, 0, array.length-1);
	}

	private int searchForString2(String[] array, String target, int start, int end) {
		if(start > end) {
			return -1;
		}
		
		int mid = start + (end-start)/2;
		
		if(array[mid].equals("")) {
			// Decide which side to choose
			int i = mid-1, j = mid+1;
			while(true) {
				if(i >= start && !array[i].equals("")) {
					mid = i;
					break;
				}
				else if(j <= end && !array[j].equals("")) {
					mid = j;
					break;
				}
				else {
					--i;
					++j;
				}
			}
		}
		
		if(array[mid].equals(target)) {
			return mid;
		}
		else if(array[mid].compareTo(target) < 0) {
			return searchForString2(array, target, mid+1, end);
		}
		else {
			return searchForString2(array, target, start, mid-1);
		}
	}
	
	/**
	 * Dutch National Flag Problem
	 */
	public void mySwap(int[] arr) {
		if(arr == null || arr.length < 2) {
			return;
		}
		
		int left = 0, right = arr.length-1, curr = 0; // Current is the moving pointer that will check values
		
		while(curr <= right) {
			if(arr[curr] == 0) { // moving 0 to left
				swap(arr, curr, left);
				++left;
			}
			else if(arr[curr] == 2) { // moving 2 to right
				swap(arr, curr, right);
				--right;
			}
			// No else case as we don't want to move 1s. If 0s and 2 are sorted, 1s will fall into place.
			++curr;
		}
	}

	private void swap(int[] arr, int idx1, int idx2) {
		int temp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = temp;
	}
}
