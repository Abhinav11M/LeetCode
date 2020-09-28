package educative.divideandconquer;

import java.util.Arrays;

public class DivideAndConquerQues {
	/**
	 * Pascal's Triangle
	 */
	public int[] pascalTriangle(int lineNumber) {
		
		if(lineNumber == 0) {
			return new int[] {1};
		}
		
		int[] arr = new int[lineNumber+1];
		
		arr[0] = 1;
		arr[lineNumber] = 1;
		
		for(int i = 1; i < lineNumber; ++i) {
			arr[i] = pascalTriangle(lineNumber, i);
		}
		
		return arr;
	}

	private int pascalTriangle(int lineNumber, int idx) {
		if(lineNumber == 0 || lineNumber == 1) {
			return 1;
		}
		
		if(idx == 0 || idx == lineNumber) {
			return 1;
		}
		
		return pascalTriangle(lineNumber-1, idx-1) + pascalTriangle(lineNumber-1, idx);
	}
	
	/**
	 * Pascal's Triangle Method2
	 */
	public int[] pascalTriangleRecur(int lineNumber) {
		
		if(lineNumber == 0) {
			return new int[] {1};
		}
		if(lineNumber == 1) {
			return new int[] {1,1};
		}
		
		int[] currentLine = new int[lineNumber+1];
		
		int[] prevLine = pascalTriangleRecur(lineNumber-1);
		
		for(int i = 0; i < currentLine.length; ++i) {
			if(i == 0 || i == currentLine.length-1) {
				currentLine[i] = 1;
			}
			else {
				currentLine[i] = prevLine[i-1] + prevLine[i];
			}
		}
		
		return currentLine;
	}
	
	/**
	 * GCD : Euclidean algorithm
	 */
	public int gcd(int a, int b) {
		if(a < b) {
			return gcd(b,a);
		}
		
		if(b == 0) {
			return a;
		}
		
		if(a%b == 0) {
			return b;
		}
		
		return gcd(b, a%b);
	}
	
	/**
	 * Peak Element
	 */
	public int findPeak(int arr[]) {

		if(arr == null || arr.length == 0) {
			return -1;
		}
		
		if(arr.length == 1) {
			return arr[0];
		}
		
		return findPeak(arr, 0, arr.length-1);
	}

	private int findPeak(int[] arr, int left, int right) {
		
		int mid = left + (right-left)/2;
		
		if((mid == 0 || arr[mid-1] <= arr[mid]) && (mid == arr.length-1 || arr[mid] >= arr[mid+1])) {
			return mid;
		}
		else if(arr[mid-1] > arr[mid]) { // the peak will be towards left
			return findPeak(arr, left, mid-1);
		}
		else {
			return findPeak(arr, mid+1, right);
		}
	}
	
	/**
	 * Maximum subarray sum
	 */
	public int maxSumArr(int arr[], int left, int right) {
		if(arr == null || arr.length == 0) {
			return 0;
		}
		
		if(arr.length == 1) {
			return arr[0];
		}
		
		return maxSumArr(arr, 0, arr.length-1, 0, new Integer[arr.length]);
	}

	private int maxSumArr(int[] arr, int left, int right, int sum, Integer[] dp) {
		
		if(dp[left] != null) {
			dp[left] = Math.max(sum, dp[left]);
			return dp[left];
		}
		
		if(left == right) {
			dp[left] = (dp[left] == null) ? sum+arr[left] : Math.max(sum+arr[left], dp[left]);
			return dp[left];
		}
		
		// Including element at left
		int s1 = maxSumArr(arr, left+1, right, sum+arr[left], dp);
		
		// Excluding element at left
		int s2 = maxSumArr(arr, left+1, right, sum, dp);

		int max = Math.max(s1, s2);
		
		dp[left] = (dp[left] == null) ? max : Math.max(dp[left], max);

		return dp[left];
	}
	
	/**
	 * Method2 for max subarray sum
	 */
	public int maxSumArr2(int arr[], int left, int right) {
		int sum = Integer.MIN_VALUE;
		int maxSum = Integer.MIN_VALUE;
		
		while(left < right) {
			maxSum = Math.max(maxSum, sum);
			if((sum + arr[left] < 0) || (sum < 0 && sum+arr[left] > 0)) {
				sum = arr[left];
			}
			else {
				sum += arr[left];
			}
			++left;
		}
		
		return Math.max(maxSum, sum);
	}
	
	/**
	 * Method 3 for max subarray (Divide and Conquer)
	 */
	public int maxSumArrRec(int arr[], int left, int right) {
		if(left == right) {
			return arr[left];
		}
		
		int mid = left + (right - left)/2;
		
		return maxOfThree(
				maxSumArrRec(arr, left, mid), 
				maxSumArrRec(arr, mid+1, right), 
				crossOverMaxSum(arr, left, mid, right)
				);
	}

	private int crossOverMaxSum(int[] arr, int left, int mid, int right) {
		// Find left half cross max sum at the cross-over
		int leftMax = Integer.MIN_VALUE;
		int sum = 0;
		for(int i = mid; i >= 0; --i) {
			sum += arr[i];
			if(sum > leftMax) {
				leftMax = sum;
			}
		}
		
		int rightMax = Integer.MIN_VALUE;
		sum = 0;
		for(int i = mid+1; i <= right; ++i) {
			sum += arr[i];
			if(sum > rightMax) {
				rightMax = sum;
			}
		}
		
		return leftMax + rightMax;
	}

	private int maxOfThree(int leftMaxSum, int rightMaxSum, int crossOverMaxSum) {
		return Math.max(leftMaxSum, Math.max(rightMaxSum, crossOverMaxSum));
	}
	
	/**
	 * Method 4 for max subarray (Kadane's Algorithm)
	 */
	public int maxSumArrKadanes(int arr[], int left, int right) {
		int maxSum = arr[0];
		int currSum = arr[0];
		
		for(int i = 1; i <= right; ++i) {
			currSum = Math.max(arr[i], currSum+arr[i]) ;
			maxSum = Math.max(maxSum, currSum);
		}
		
		return maxSum;
	}

	/**
	 * Missing Number in Sorted Array
	 */
	public int missingNumber(int arr[]) {
		int left = 0, right = arr.length-1;
		
		int missingIdx = -1;
		while(left <= right) {
			int mid = left + (right-left)/2;
			if(arr[mid] > mid+1) { // something missing in the left half
				missingIdx = mid;
				right = mid-1;
			}
			else {
				left = mid+1;
			}
		}
		
		return missingIdx == -1 ? missingIdx : missingIdx+1;
	}
	
	public int closestNumber(int arr[],int target) {

		if(arr == null || arr.length == 0) {
			return target;
		}
		
		if(arr.length == 1) {
			return Math.abs(target-arr[0]);
		}
		
		int minDiff = Integer.MAX_VALUE;
		int retVal = arr[0];
		int left = 0, right = arr.length-1;
		
		while(left <= right) {
			int mid = left + (right-left)/2;
			
			if(Math.abs(target-arr[mid]) < minDiff) {
				minDiff = Math.abs(target-arr[mid]);
				retVal = arr[mid];
			}
			
			if(arr[mid] == target) {
				return arr[mid];
			}
			else if(arr[mid] > target) {
				right = mid-1;
			}
			else {
				left = mid+1;
			}
		}

		return retVal;
	}
	
	/**
	 * Shuffle Integers
	 */
	public boolean shuffleIntegers(int[] arr) {
		
		if(arr.length == 1 || arr.length == 2) {
			return true;
		}
		
		if(arr.length%2 != 0) {
			return false;
		}
		
		int l = 1, r = arr.length/2;

		while(r <= arr.length-1) {
			
			int right = arr[r];
			for(int i = l; i <= r; ++i) {
				swap(arr, i, r);
			}
			arr[l] = right;
			++r;
			l+=2;
		}

		return true;
	}
	
	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	/**
	 * Shuffle Integer: Divide and Conquer
	 */
	public boolean shuffleIntegers2(int[] arr) {
		if(arr.length == 1 || arr.length == 2) {
			return true;
		}
		
		if(arr.length%2 != 0) {
			return false;
		}	
		
		shuffleIntegersRec(arr, 0, arr.length-1);
		return true;
	}

	private void shuffleIntegersRec(int[] arr, int left, int right) {
		// Base case: If we only have two or less elements, no swap would happen
		if(right-left <= 1) {
			return;
		}
		
		int mid = (left+right)/2;
		int rIdx = mid+1; // First element of the second half
		int mMid = (left+mid)/2; // mid of left half
		int lIdx = mMid+1; // First element of the right of the first half
		
		// Swap the elements
		for(int i = lIdx; i <= mid; ++i) {
			swap(arr, i, rIdx);
			++rIdx;
		}
		
		// Recursively call this for the 2 halves
		shuffleIntegersRec(arr, left, mid);
		shuffleIntegersRec(arr, mid+1, right);
	}
	
	/**
	 * Collect coins in minimum steps
	 */
	public int minSteps(int[] heights) {
		
		return minSteps(heights, 0, heights.length-1, 0);
	}

	/**
	 * @param heights Array of heights of coins
	 * @param l Left index
	 * @param r	Right index
	 * @param h Horizontal height covered till the call.
	 */
	private int minSteps(int[] heights, int l, int r, int h) {
		if(l > r) {
			return 0;
		}
		
		int verticalSteps = r - l + 1;
		
		// Find the min height index in the array of heights to split.
		int minIdx = l;
		for(int i = l+1; i <= r; ++i) {
			if(heights[i] < heights[minIdx]) {
				minIdx = i;
			}
		}
		
		// split on the minIdx
		return Math.min(
				verticalSteps, 
				minSteps(heights, l, minIdx-1, heights[minIdx]) + 
				minSteps(heights, minIdx+1, r, heights[minIdx]) +
				heights[minIdx]-h // The mid (min height) will be covered in horizontal
				);
	}
	
	/**
	 * Merge a Number of Sorted Arrays
	 * All arrays are of same length
	 */
	public void mergeSortedArrays(int [][] arr, int k, int[] result) {
		int[] allMerged = mergeSortedArrays(arr, 0, k-1);
		
		for(int i = 0; i < allMerged.length; ++i) {
			result[i] = allMerged[i];
		}
	}

	private int[] mergeSortedArrays(int[][] arr, int l, int r) {
		if(l == r) {
			return arr[l];
		}

		int mid = (l+r)/2;
		int[] leftSorted = mergeSortedArrays(arr, l, mid);
		int[] rightSorted = mergeSortedArrays(arr, mid+1, r);
		
		
		return mergeSortedArrays(leftSorted, rightSorted);
	}

	private int[] mergeSortedArrays(int[] leftSorted, int[] rightSorted) {
		int[] res = new int[leftSorted.length+rightSorted.length];
		int rIdx = 0;
		
		int i1 = 0, i2 = 0;
		while(i1 < leftSorted.length && i2 < rightSorted.length) {
			if(leftSorted[i1] > rightSorted[i2]) {
				res[rIdx] = rightSorted[i2];
				++i2;
			}
			else {
				res[rIdx] = leftSorted[i1];
				++i1;
			}
			++rIdx;
		}
		
		// If rightSorted exhausted
		while(i1 < leftSorted.length) {
			res[rIdx] = leftSorted[i1];
			++i1;
			++rIdx;
		}

		// If leftSorted exhausted
		while(i2 < rightSorted.length) {
			res[rIdx] = rightSorted[i2];
			++i2;
			++rIdx;
		}
		
		return res;
	}
	
	/**
	 * Find the floor and ceiling of a number.
	 * @param Sorted input array.
	 * @param x The number to find the floor and ceiling of.
	 * @param output Results output array.
	 */
	public void findFloorCeiling(int [] input, int x, int [] output) {
		findFloorCeiling(input, x, 0, input.length, output);
	}

	private void findFloorCeiling(int[] input, int x, int left, int right, int[] output) {
		
		while(left <= right) {
			int mid = left + (right-left)/2;
			
			if(x == input[mid]) {
				output[0] = x;
				output[1] = x;
				break;
			}
			
			if(mid == 0 && input[mid] > x) {
				output[0] = -1; // no floor
				output[1] = input[mid];
				break;
			}
			
			if(mid == input.length-1 && x > input[mid]) {
				output[0] = input[mid];
				output[1] = -1; // No ceiling
				break;
			}

			if(input[mid] >= x && input[mid-1] <= x) {
				output[0] = input[mid-1];// floor
				output[1] = input[mid]; // ceiling
				break;
			}

			if(input[mid] <= x && input[mid+1] >= x) {
				output[0] = input[mid];// floor
				output[1] = input[mid+1]; // ceiling
				break;
			}
			
			if(x > input[mid]) {
				left = mid+1;
			}
			else {
				right = mid-1;
			}
		}
	}
	
	/**
	 * Number of inversion count
	 */
	public int countInversions(int[] arr) {
		return countInversions(arr, 0, arr.length-1);
	}

	private int countInversions(int[] arr, int left, int right) {
		if(left >= right) {
			return 0;
		}
		
		int count = 0;
		
		int mid = (left+right)/2;
		
		// Recursive call for left
		count += countInversions(arr, left, mid);
		// Recursive call for right
		count += countInversions(arr, mid+1, right);
		// Merging and counting for two sub-arrays
		count += mergeAndCount(arr, left, mid, right);
		
		return count;
	}

	private int mergeAndCount(int[] arr, int left, int mid, int right) {
		int[] leftSub = Arrays.copyOfRange(arr, left, mid+1);
		int[] rightSub = Arrays.copyOfRange(arr, mid+1, right+1);
		
		int i = 0, j = 0, k = left;
		int count = 0;
		
		while(i < leftSub.length && j < rightSub.length) {
			if(leftSub[i] < rightSub[j]) {
				arr[k++] = leftSub[i++];
			}
			else {
				arr[k++] = rightSub[j++];
				count += (mid+1) - (left+i);
			}
		}
		
		// Fill the rest of the array
		while(i < leftSub.length) {
			arr[k++] = leftSub[i++];
		}
		
		while(j < rightSub.length) {
			arr[k++] = rightSub[j++];
		}
		
		return count;
	}
}








