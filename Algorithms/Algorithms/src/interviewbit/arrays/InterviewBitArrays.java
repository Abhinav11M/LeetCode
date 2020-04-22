package interviewbit.arrays;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class InterviewBitArrays {
	 public int coverPoints(ArrayList<Integer> A, ArrayList<Integer> B) {
		 int x = A.get(0);
		 int y = B.get(0);
		 int steps = 0;
		 for(int i = 1; i < A.size(); ++i) {
			 int diffX = Math.abs(A.get(i) - x);
			 int diffY = Math.abs(B.get(i) - y);
			 
			 int maxDiff = Math.max(diffX, diffY);
			 steps += maxDiff;
			 
			 x = A.get(i);
			 y = B.get(i);
		 }
		 
		 return steps;
	 }
	 
	 public ArrayList<Integer> plusOne(ArrayList<Integer> A) {
		 List<Integer> res = new LinkedList<>();
		 
		 int carry = 1;
		 for(int i = A.size()-1; i >= 0; --i) {
			 if(A.get(i) + carry <= 9) {
				 A.set(i, A.get(i) + carry);
				 carry = 0;
			 }
			 else {
				 A.set(i, 0);
				 carry = 1;
			 }
		 }
		 
		 if(carry == 1) {
			 res.add(0,1);
		 }
		 res.addAll(A);
		 // Remove trailing 0s
		 while(true) {
			if(res.get(0) == 0) {
				res.remove(0);
			}
			else {
				break;
			}
		 }
	 
		 return new ArrayList<Integer>(res);
	 }
	 
	 /**
	  * Maximum sub of contiguous subArray
	  */
	 public int maxSumContSubArray(int[] arr) {
		int max = Integer.MIN_VALUE;
		int sum = 0;

		// Keep moving until first positive
		int i = 0;
		while (i < arr.length && arr[i] < 0) {
			sum = arr[i];
			if (arr[i] > max) {
				max = arr[i];
			}
			++i;
		}

		if (i == arr.length) {
			return max;
		}

		sum = 0;
		for (; i < arr.length; ++i) {
			if (arr[i] < 0) {
				if (sum + arr[i] < 0) {
					// Update max
					if (max < sum) {
						max = sum;
					}
					sum = 0;
				} else {
					sum += arr[i];
				}
			} else {
				sum += arr[i];
				if (sum > max) {
					max = sum;
				}
			}
		}

		return sum > max ? sum : max;
	 }
	 
	 public int maxSumContSubArray1(List<Integer> A) {
		int max = Integer.MIN_VALUE;
		int sum = 0;

		// Keep moving until first positive
		int i = 0;
		while (i < A.size() && A.get(i) < 0) {
			sum = A.get(i);
			if (A.get(i) > max) {
				max = A.get(i);
			}
			++i;
		}

		if (i == A.size()) {
			return max;
		}

		sum = 0;
		for (; i < A.size(); ++i) {
			if (A.get(i) < 0) {
				if (sum + A.get(i) < 0) {
					// Update max
					if (max < sum) {
						max = sum;
					}
					sum = 0;
				} else {
					sum += A.get(i);
				}
			} else {
				sum += A.get(i);
				if (sum > max) {
					max = sum;
				}
			}
		}

		return sum > max ? sum : max;
	 }
	 
	 /**
	  * Maximum Absolute Difference
	  * <a>https://www.interviewbit.com/problems/maximum-absolute-difference/</a>
	  */
	 public int maxAbsDiff(ArrayList<Integer> A) {
		 // Max and min of A[i]+i
		 int max1 = Integer.MIN_VALUE;
		 int min1 = Integer.MAX_VALUE;
		 
		 for(int i = 0; i < A.size(); ++i) {
			 max1 = Math.max(max1, A.get(i)+i);
			 min1 = Math.min(min1, A.get(i)+i);
		 }
		 
		 int maxDiff = max1-min1;
		 
		 // Max and min of A[i]-i		 
		 int max2 = Integer.MIN_VALUE;
		 int min2 = Integer.MAX_VALUE;
		 
		 for(int i = 0; i < A.size(); ++i) {
			 max2 = Math.max(max2, A.get(i)-i);
			 min2 = Math.min(min2, A.get(i)-i);
		 }
		 
		 return Math.max(maxDiff, max2-min2);
	 }
	 
	 public ArrayList<Integer> repeatedNumber(final List<Integer> A) {
		 for(int i = 0; i < A.size(); ++i) {
			int num = A.get(i);
			if(num == i+1) {
				continue;
			}
			else {
				//swap
				int temp = A.get(num-1);
				A.set(num-1, num);
				A.set(i, temp);
			}
		 }
		 
		 int i = 0;
		 for(; i < A.size(); ++i) {
			 if(A.get(i) != i+1) {
				 break;
			 }
		 }
		 
		 ArrayList<Integer> ret = new ArrayList<>();
		 ret.add(A.get(i));
		 ret.add(i+1);
		 
		 return ret;
	 }
	 
	 /**
	  * We are not supposed to modify the list
	  * @param A
	  * @return
	  */
	 public ArrayList<Integer> repeatedNumberMod(final List<Integer> A) {
		 
		 int n = A.size();
		 int expSum = (n*(n+1))/2;
		 int expSumOfSquare = n*(n+1)*((2*n)+1)/6;
		 int sum = 0;
		 int sumOfSquares = 0;
		 
		 for(int i = 0; i < A.size(); ++i) {
			 sum += A.get(i);
			 sumOfSquares += A.get(i)*A.get(i);
		 }
		 
		 int missing = (((expSumOfSquare - sumOfSquares)/(expSum - sum)) + (expSum - sum))/2;
		 int repeating = missing - (expSum - sum);
		 
		 ArrayList<Integer> list = new ArrayList<>();
		 list.add(repeating);
		 list.add(missing);

		 return list;
	 }
	 
	 /**
	  * Flip
	  */
	 public ArrayList<Integer> flip(String A) {
		 int left = 1, right = 1, leftMax = 1, rightMax = 1, count = 0, countMax = 0;
		 // Move to the next 0
		 left = moveTillNext0(A, left-1);
		 if(left == A.length()+1) {
			 return new ArrayList<>();
		 }
		 right = left;
		 
		 while(right <= A.length()) {
			 if(A.charAt(right-1) == '0') {
				 ++count;
				 if(count > countMax) {
					 countMax = count;
					 leftMax = left;
					 rightMax = right;
				 }
			 }
			 else {
				 --count;
			 }
			 ++right;
			 
			 // Reset if the count goes to 0
			 if(count <= 0) {
				 left = right;
			 }
		 }
		 
		 ArrayList<Integer> retVal = new ArrayList<>();
		 retVal.add(leftMax);
		 retVal.add(rightMax);
		 return retVal;
	 }

	private int moveTillNext0(String A, int left) {
		for(int i = left; i < A.length(); ++i) {
			if(A.charAt(i) == '0') {
				return i+1;
			}
		}
		return A.length()+1 ;
	}
	
	/**
	 * Spiral traversal
	 */
	public void printSpiral(int[][] matrix) {
		int iRight = matrix[0].length-1;
		int iLeft = 0;
		int jDown = matrix.length-1;
		int jUp = 0;
		
//		int i = 0, j = 0; // i -> Cols, j -> Rows
		for(int c = 0; c <= matrix.length/2; ++c) {
			// left to right
			for(int i = iLeft; i <= iRight; ++i) {
				System.out.print(matrix[jUp][i] + " ");
			}
			System.out.println();
			jUp++;
			
			// up to down
			for(int j = jUp; j <= jDown; ++j) {
				System.out.print(matrix[j][iRight] + " ");
			}
			System.out.println();
			iRight--;
			
			// right to left
			for(int i = iRight; i >= iLeft; i--) {
				System.out.print(matrix[jDown][i] + " ");
			}
			System.out.println();
			jDown--;
			
			// Down to up
			for(int j = jDown; j >= jUp; j--) {
				System.out.print(matrix[j][iLeft] + " ");
			}
			System.out.println();
			++iLeft;
		}
	}
}
