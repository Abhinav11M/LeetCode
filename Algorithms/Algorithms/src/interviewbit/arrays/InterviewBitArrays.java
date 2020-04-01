package interviewbit.arrays;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
}
