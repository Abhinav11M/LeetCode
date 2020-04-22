package interviewbit.arrays;

import java.util.ArrayList;

import com.google.common.collect.Lists;

public class InterviewBitArraysMain {
	public static void main(String[] args) {
		InterviewBitArrays arrays = new InterviewBitArrays();
		System.out.println(arrays.coverPoints(Lists.newArrayList(0, 1, 1), 
				Lists.newArrayList(0, 1, 2)));
		
//		System.out.println(arrays.plusOne(Lists.newArrayList(1,9,9)));
		System.out.println(arrays.plusOne(Lists.newArrayList(0, 3, 7, 6, 4, 0, 5, 5, 5)));
		System.out.println(arrays.plusOne(Lists.newArrayList(0)));
		System.out.println(arrays.maxSumContSubArray(new int[] {1,2,-1,4,-3,1,2}));
		System.out.println(arrays.maxSumContSubArray(new int[] {-300}));
		
		System.out.println(arrays.maxAbsDiff(Lists.newArrayList(1, 3, -1)));

//		System.out.println(arrays.repeatedNumber(Lists.newArrayList(3,1,2,5,3)));
//		System.out.println(arrays.repeatedNumberMod(Lists.newArrayList(3,1,2,5,3)));
		System.out.println(arrays.repeatedNumberMod(Lists.newArrayList(2,2,2,2,2)));
		System.out.println("FLIP");
//		System.out.println(arrays.flip("1010011"));
//		System.out.println(arrays.flip("1000011"));
//		System.out.println(arrays.flip("10010001"));
//		System.out.println(arrays.flip("10010000"));
//		System.out.println(arrays.flip("111"));
		System.out.println(arrays.flip("010"));
		System.out.println(arrays.flip("1101010001"));
		
		arrays.printSpiral(new int[][] { {1,2,3}, {4,5,6}, {7,8,9} });
		arrays.printSpiral(new int[][] { {1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16} });
	}
}
