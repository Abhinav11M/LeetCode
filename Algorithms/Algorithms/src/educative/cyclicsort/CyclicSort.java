package educative.cyclicsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CyclicSort {
	/**
	 * You are given an unsorted array containing numbers taken from the range 1 to ‘n’. 
	 * The array can have duplicates,  which means that some numbers will be missing. 
	 * Find all the missing numbers
	 */
	public List<Integer> findMissingNumbers(int[] nums) {
		List<Integer> result = new ArrayList<>();

		int maxVal = Arrays.stream(nums).max().getAsInt();
		
		int[] filled = new int[maxVal]; 
		for(int i = 0; i < filled.length; ++i) {
			filled[i] = -1;
		}
		
		for(int i = 0; i < nums.length; ++i) {
			filled[nums[i]-1] = 1; // Set filled to one on the number's index
		}
		
		for(int i = 0; i < filled.length; ++i) {
			if(filled[i] < 0) {
				result.add(i+1);
			}
		}
		
		return result;
	}
	
	/**
	 * We are given an array containing ‘n’ objects. Each object, when created, was assigned 
	 * a unique number from 1 to ‘n’ based on their creation sequence. 
	 * This means that the object with sequence number ‘3’ was created just before the object 
	 * with sequence number ‘4’. Write a function to sort the objects in-place on their 
	 * creation sequence number in O(n)O(n) and without any extra space.
	 * Input: [3, 1, 5, 4, 2]
	 * Output: [1, 2, 3, 4, 5]
	 */
	public void cyclicSort(int nums[]) {
		for(int i = 0; i < nums.length; ++i) {
			while(nums[i] != i+1) {
				swap(nums, i, nums[i]-1);
			}
		}
	}
	
	public void cyclicSort2(int nums[]) {
		int i = 0;
		while(i < nums.length) {
			if(nums[i] != i+1) {
				swap(nums, i, nums[i]-1);
			}
			else {
				++i;
			}
		}
	}
	
	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	
	/**
	 * We are given an array containing ‘n’ distinct numbers taken from the range 0 to ‘n’. 
	 * Since the array has only ‘n’ numbers out of the total ‘n+1’ numbers, find the missing number.
	 */
	public int findMissingNumber(int[] nums) {
		int i = 0;
		while(i < nums.length ) {
			if(nums[i] != i && nums[i] < nums.length) {
				swap(nums, i, nums[i]);
			}
			else {
				++i;
			}
		}
		
		for(int j = 0; j < nums.length; ++j) {
			if(nums[j] != j) {
				return j;
			}
		}
		
		return nums.length;
	}

	/**
	 * We are given an unsorted array containing ‘n+1’ numbers taken from the range 1 to ‘n’. 
	 * The array has only one duplicate but it can be repeated multiple times. 
	 * Find that duplicate number without using any extra space. 
	 * You are, however, allowed to modify the input array.
	 */
	public int findDuplicate(int[] nums) {
		int i = 0;
		while(i < nums.length) {
			if(nums[i] != i+1) {
				if(nums[nums[i]-1] == nums[i]) {
					return nums[i]; // duplicate
				}
				swap(nums, i, nums[i]-1);
			}
			else {
				++i;
			}
		}
		return -1;
	}
	
	/**
	 * Find all duplicate numbers
	 * We are given an unsorted array containing ‘n’ numbers taken from the range 1 to ‘n’. 
	 * The array has some duplicates, find all the duplicate numbers without using any extra space.
	 */
	public List<Integer> findAllDuplicates(int[] nums) {
		List<Integer> duplicates = new ArrayList<>();
		
		int i = 0;
		while(i < nums.length) {
			if(nums[i] != nums[nums[i]-1]) { // Swap when index not already have same numbers
				swap(nums, i, nums[i]-1);
			}
			else {
				++i;
			}
		}
		
		for(int j = 0; j < nums.length; ++j) {
			if(j != nums[j]-1 && nums[j] == nums[nums[j]-1]) {
				duplicates.add(nums[j]);
			}
		}
		
		return duplicates;
	}
	
	/**
	 * Find the Corrupt Pair
	 * We are given an unsorted array containing ‘n’ numbers taken from the range 1 to ‘n’.
	 *  The array originally contained all the numbers from 1 to ‘n’, but due to a data error, 
	 * one of the numbers got duplicated which also resulted in one number going missing. Find both these numbers.
	 */
	public int[] findCorruptedNumbers(int[] nums) {
		int i = 0;
		while(i < nums.length) {
			// Swap if not equal
			if(nums[i] != nums[nums[i]-1]) {
				swap(nums, i, nums[i]-1);
			}
			else {
				++i;
			}
		}
		
		for(int j = 0; j < nums.length; ++j) {
			if((j != nums[j]-1) && nums[j] == nums[nums[j]-1]) {
				return new int[] {nums[j], j+1};
			}
		}
		
		return new int[] {};
	}
	
	/**
	 * Find the Smallest Missing Positive Number
	 * Given an unsorted array containing numbers, find the smallest missing positive number in it.
	 * Input: [-3, 1, 5, 4, 2]
	 * Output: 3
	 * Explanation: The smallest missing positive number is '3'
	 */
	public int findFirstMissingPositiveInteger(int[] nums) {
		// For null or empty nums
		if(nums == null || nums.length == 0) {
            return 1;
        }
		
		// Shift all the negative and 0s to the left
		int i = 0;
		for(int j = 0; j < nums.length; ++j) {
			if(nums[j] <= 0) {
				swap(nums, i, j);
				++i;
			}
		}
		
		// i will be the index from where all the positives start
		for(int j = i; j < nums.length;) {

			int index = nums[j] + i-1;
			if(index >= 0 && index < nums.length 
					&& nums[j] != j-i+1 
					&& nums[j] != nums[index]) { // Third condition is to avoid duplicates
				//swap
				swap(nums, index, j);
			}
			else {
				++j;
			}
		}
		
		for(int j = i; j < nums.length; ++j) {
			if(nums[j] != j-i+1) {
				if(j == i) {
					return 1;
				}
				else {
					return nums[j-1]+1;
				}
			}
		}
		
		return nums[nums.length-1] < 0 ? 1 : nums[nums.length-1] + 1;
	}
	
	/**
	 * Find k first missing positive integers
	 * @param nums Number array
	 * @param k Number of missing integers to be found
	 * @return List of missing positive integers from the array
	 */
	public List<Integer> firstKMissingIntegers(int[] nums, int k)  {
				// For null or empty nums
		if(nums == null || nums.length == 0) {
            return Collections.emptyList();
        }
		
		// Shift all the negative and 0s to the left
		int i = 0;
		for(int j = 0; j < nums.length; ++j) {
			if(nums[j] <= 0) {
				swap(nums, i, j);
				++i;
			}
		}
		
		// i will be the index from where all the positives start
		for(int j = i; j < nums.length;) {

			int index = nums[j] + i-1;
			if(index >= 0 && index < nums.length 
					&& nums[j] != j-i+1 
					&& nums[j] != nums[index]) { // Third condition is to avoid duplicates
				//swap
				swap(nums, index, j);
			}
			else {
				++j;
			}
		}
		
		List<Integer> missingNumbers = new ArrayList<>();
		Set<Integer> extraNumbers = new HashSet<>();
		
		int count = 0;
		int value = 1;
		while(i < nums.length && count < k) {
			if(nums[i] != value) {
				missingNumbers.add(value);
				extraNumbers.add(nums[i]); // Since the array will not be sorted [-2,-1,5,5,3,4]
				++count;
			}
			++i;
			++value;
		}
		
		if(count == k) {
			return missingNumbers;
		}
		
		while(count < k) {
			if(!extraNumbers.contains(value)) {
				missingNumbers.add(value);
				++count;
			}
			value++;
		}
		
		return missingNumbers;
	}
}
