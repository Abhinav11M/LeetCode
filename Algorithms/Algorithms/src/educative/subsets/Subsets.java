package educative.subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Subsets {
	/**
	 * Given a set with distinct elements, find all of its distinct subsets.
	 * Input: [1, 3]
	 * Output: [], [1], [3], [1,3]
	 */
	public List<List<Integer>> findSubsets(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		
		findSubsets(nums, 0, new ArrayList<Integer>(), res);
		
		return res;
	}

	private void findSubsets(int[] nums, int index, ArrayList<Integer> list, List<List<Integer>> res) {
		if(index > nums.length) {
			return;
		}
		res.add(new ArrayList<>(list));
		
		for(int i = index; i < nums.length; ++i) {
			list.add(nums[i]);
			findSubsets(nums, i+1, list, res);
			list.remove(list.size()-1);
		}
	}

	public List<List<Integer>> findSubsetsIter(int[] nums) {
		List<List<Integer>> subsets = new ArrayList<>();
		
		subsets.add(new ArrayList<>()); // Added empty list as the first element
		
		for(int num : nums) {
			int n = subsets.size();
			
			for(int i = 0; i < n; ++i) { // Adding num to each of the subset
				List<Integer> subset = new ArrayList<>(subsets.get(i));
				subset.add(num);
				subsets.add(subset);
			}
		}
		
		return subsets;
	}
	
	/**
	 * Subsets With Duplicates
	 * Given a set of numbers that might contain duplicates, find all of its distinct subsets.
	 */
	public List<List<Integer>> findSubsetsWithDuplicates(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(nums);
		
		findSubsetsWithDuplicates(nums, 0, new ArrayList<Integer>(), res);
		
		return res;
	}

	private void findSubsetsWithDuplicates(int[] nums, int index, ArrayList<Integer> list, List<List<Integer>> res) {
		if(index > nums.length) {
			return;
		}
		
		res.add(new ArrayList<>(list));
		List<Integer> prev = null;
		for(int i = index; i < nums.length; ++i) {
			list.add(nums[i]);
			if(prev != null && prev.equals(list)) {
				list.remove(list.size()-1);
				continue;
			}

			prev = new ArrayList<>(list);
			findSubsetsWithDuplicates(nums, i+1, list, res);
			list.remove(list.size()-1);
		}
	}

	public List<List<Integer>> findSubsetsWithDuplicatesIter(int[] nums) {
		
		Arrays.sort(nums);
		
		List<List<Integer>> subsets = new ArrayList<>();
		
		subsets.add(new ArrayList<>()); // Added empty list as the first element
		
		int startIdx = 0, endIdx = 0;
		
		for(int i = 0; i < nums.length; ++i) {
			startIdx = 0;
			
			if(i > 0 && nums[i] == nums[i-1]) {
				startIdx = endIdx + 1;
			}
			
			endIdx = subsets.size()-1;
			
			for(int j = startIdx; j <= endIdx; ++j) {
				List<Integer> subset = new ArrayList<>(subsets.get(j));
				subset.add(nums[i]);
				subsets.add(subset);
			}
		}
		
		return subsets;
	}
	
	/**
	 * Find all the permutations of a array of digits
	 */
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		
		List<Integer> source = Arrays.stream(nums).boxed().collect(Collectors.toList());
		
		permute(source, new ArrayList<Integer>(), result);
		
		return result;
	}

	private void permute(List<Integer> source, List<Integer> destination, List<List<Integer>> result) {
		if(source.size() == 0) {
			result.add(destination);
			return;
		}
		
		for(int i = 0; i < source.size(); ++i) {
			List<Integer> sourceNew = new ArrayList<>(source);
			List<Integer> destNew = new ArrayList<>(destination);
			destNew.add(sourceNew.remove(i));
			permute(sourceNew, destNew, result);
		}
	}
	
	/**
	 * Find all permutations of a array of numbers : Iterative (Educative)
	 */
	public List<List<Integer>> permuteIter(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		
		Queue<List<Integer>> permutations = new LinkedList<>();
		permutations.add(new ArrayList<>()); // Pushed an empty list to the queue
		
		for(int i = 0; i < nums.length; ++i) {
			int n = permutations.size();
			for(int k = 0; k < n; ++k) {
				List<Integer> oldPermutation = permutations.poll();
				
				// Add nums[i] to each of the possible indices of oldPermutation
				for(int j = 0; j <= oldPermutation.size(); ++j) {
					List<Integer> newPermutation = new ArrayList<>(oldPermutation);
					newPermutation.add(j, nums[i]);
					
					if(newPermutation.size() == nums.length) {
						result.add(newPermutation);
					}
					else {
						permutations.add(newPermutation);
					}
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Find all permutations of a array of numbers (Recursive : Educative)
	 */
	public List<List<Integer>> permuteRecursive(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		
		permuteRecursive(nums, 0, new ArrayList<Integer>(), res);
		
		return res;
	}

	private void permuteRecursive(int[] nums, int index, List<Integer> currentPermutation, List<List<Integer>> res) {
		if(index == nums.length) {
			res.add(currentPermutation);
		}
		else {
			for(int i = 0; i <= currentPermutation.size(); ++i) {
				List<Integer> newPermutation = new ArrayList<>(currentPermutation);
				// Add nums[index] on all the indices
				newPermutation.add(i, nums[index]);
				permuteRecursive(nums, index+1, newPermutation, res);
			}
		}
	}
	
	/**
	 * String Permutations by changing case 
	 */
	public List<String> permutationByChangingCase(String str) {
		Queue<String> result = new LinkedList<>();
		
		result.add(str);
		
		permutationByChangingCase(str, 0, result);
		
		return new ArrayList<String>(result);
	}

	private void permutationByChangingCase(String str, int index, Queue<String> result) {
		if(index == str.length()) {
			return;
		}

		if( (str.charAt(index) <= 90 && str.charAt(index) >= 65) || 
				(str.charAt(index) <= 122 && str.charAt(index) >= 97) ) {

			int n = result.size();
			for(int i = 0; i < n; ++i) {
				String temp = result.poll();
				result.add(new String(temp));
				char[] s = temp.toCharArray();
				if(s[index] <= 90 && s[index] >= 65) { //Capital
					s[index] += 32;
				}
				else {
					s[index] -= 32;
				}
				
				result.add(new String(s));
			}
		}
		
		permutationByChangingCase(str, index+1, result);
		
	}
	
	/**
	 * For a given number ‘N’, write a function to generate all 
	 * combination of ‘N’ pairs of balanced parentheses.
	 * Input: N=2
	 * Output: (()), ()()
	 */
	public List<String> generateValidParentheses(int num) {
		if(num == 0) {
			return new ArrayList<String>();
		}
		
		List<String> result = new ArrayList<>();
		
		generateValidParentheses(new String("("), num-1, num, result);
		
		return result;
	}

	private void generateValidParentheses(String parentheses, int left, int right, List<String> result) {
		if(left == 0 && right == 0) {
			result.add(parentheses);
			return;
		}
		
		// Add a left paranthesis
		if(left != 0) {
			String s = new String(parentheses) + "(";
			generateValidParentheses(s, left-1, right, result);
		}
		
		if(right != 0 && right > left) { // Can't close before parenthesis opens
			String s = new String(parentheses) + ")";
			generateValidParentheses(s, left, right-1, result);
		}
	}
	
	public List<Integer> diffWaysToEvaluateExpression(String input) {
		
		List<Integer> result = new ArrayList<>();
	   // Base case
		if(!input.contains("+") && !input.contains("-") && !input.contains("*")) {
			result.add(Integer.parseInt(input));
			return result;
		}
		
		for(int i = 0; i < input.length(); ++i) {
			if(!Character.isDigit(input.charAt(i))) {
				// Split the expression into two values and evaluate
				char operator = input.charAt(i);
				List<Integer> leftSide = diffWaysToEvaluateExpression(input.substring(0, i));
				List<Integer> rightSide = diffWaysToEvaluateExpression(input.substring(i+1));
				
				
				for(Integer leftVal : leftSide) {
					for(Integer rightVal : rightSide) {
						if(operator == '+') {
							result.add(leftVal + rightVal);
						}
						else if(operator == '-') {
							result.add(leftVal - rightVal);
						}
						else {
							result.add(leftVal * rightVal);
						}
					}
				}
			}
		}
		
		return result;
	}
}
