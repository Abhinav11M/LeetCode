package educative.dynamicprogramming;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DynamicProgramming {
	/**
	 * Given the weights and profits of ‘N’ items, we are asked to put 
	 * these items in a knapsack which has a capacity ‘C’. The goal is to 
	 * get the maximum profit out of the items in the knapsack. 
	 * Each item can only be selected once, as we don’t have multiple quantities of any item.
	 * Let’s take the example of Merry, who wants to carry some fruits 
	 * in the knapsack to get maximum profit. Here are the weights and profits of the fruits:
	 * Items: { Apple, Orange, Banana, Melon }
	 * Weights: { 2, 3, 1, 4 }
	 * Profits: { 4, 5, 3, 7 }
	 * Knapsack capacity: 5
	 * Apple + Orange (total weight 5) => 9 profit
	 * Apple + Banana (total weight 3) => 7 profit
	 * Orange + Banana (total weight 4) => 8 profit
	 * Banana + Melon (total weight 5) => 10 profit
	 */
	public int knapsackRecursive(int[] profits, int[] weights, int capacity) {
		return knapsackRecursive(profits, weights, capacity, 0, 0);
	}

	private int knapsackRecursive(int[] profits, int[] weights, int capacity, int idx, int profit) {
		if(idx == profits.length || capacity < weights[idx]) {
			return profit;
		}
		
		// Taking item at index idx
		int profit1 = knapsackRecursive(profits, weights, capacity-weights[idx], idx+1, profit+profits[idx]);
		// Ignoring item at index idx
		int profit2 = knapsackRecursive(profits, weights, capacity, idx+1, profit);
		
		return Math.max(profit1, profit2);
	}
	
	/**
	 * Knapsack using memoization
	 */
	public int knapsackMemoization(int[] profits, int[] weights, int capacity) {
		return knapsackRecursiveMem(profits, weights, capacity, 0, 0, new int[profits.length][capacity+1]);
	}

	private int knapsackRecursiveMem(int[] profits, int[] weights, int capacity, int idx, int profit, int memory[][]) {
		if(idx == profits.length || capacity < weights[idx]) {
			return profit;
		}
		
		if(memory[idx][capacity] != 0) {
			return memory[idx][capacity];
		}
		
		// Taking item at index idx
		int profit1 = knapsackRecursiveMem(profits, weights, capacity-weights[idx], idx+1, profit+profits[idx], memory);
		// Ignoring item at index idx
		memory[idx][capacity-weights[idx]] = profit1;
		int profit2 = knapsackRecursiveMem(profits, weights, capacity, idx+1, profit, memory);
		memory[idx][capacity] = profit2;
		
		return Math.max(profit1, profit2);
	}
	
	/**
	 * Knapsack using bottom-up approach
	 * dp[i][c] = max (dp[i-1][c], profit[i] + dp[i-1][c-weight[i]]) 
	 */
	public int knapsackTabulation(int[] profits, int[] weights, int capacity) {
		
		int n = profits.length;
		
		// [Index][Capacity]
		int[][] dp = new int[n][capacity+1];
		
		// 0 capacity will always have 0 profit
		for(int i = 0; i < n; ++i) {
			dp[i][0] = 0;
		}
		
		// if the capacity is greater that or equal to weight for index 0, profit will be profits[0]
		for(int c = 0; c <= capacity; ++c) {
			if(weights[0] <= c) {
				dp[0][c] = profits[0];
			}
		}
		
		for(int i = 1; i < n; ++i) {
			for(int c = 1; c <= capacity; ++c) {
				int profit1 = 0;
				// Including profit[i], i.e. element at i
				if(weights[i] <= c) {
					profit1 = profits[i] + dp[i-1][c-weights[i]];
				}
				
				int profit2 = dp[i-1][c];
				
				dp[i][c] = Math.max(profit1, profit2);
			}
		}
		
		return dp[n-1][capacity];
	}
	
	/**
	 * Equal Subset Sum Partition
	 * Given a set of positive numbers, find if we can partition it into two subsets 
	 * such that the sum of elements in both subsets is equal.
	 * Input: {1, 2, 3, 4}
	 * Output: True
	 * Explanation: The given set can be partitioned into two subsets with equal sum: {1, 4} & {2, 3}
	 */
	public boolean canPartition(int[] nums) {
		int sum = Arrays.stream(nums).boxed().collect(Collectors.summingInt(x -> x));
		
		if(sum%2 != 0) {
			return false;
		}
		
		return canPartition(nums, 0, sum/2);
	}

	private boolean canPartition(int[] nums, int idx, int sum) {
		if(idx > nums.length-1 || nums[idx] > sum) {
			return false;
		}
		if(nums[idx] == sum) {
			return true;
		}

		return canPartition(nums, idx+1, sum-nums[idx]) || canPartition(nums, idx+1, sum);
	}
	
	/**
	 * Can partition using memoization
	 * @param nums
	 * @return
	 */
	public boolean canPartitionMemoization(int[] nums) {
		int sum = Arrays.stream(nums).boxed().collect(Collectors.summingInt(x -> x));
		
		if(sum%2 != 0) {
			return false;
		}
		
		return canPartitionMemoization(nums, 0, sum/2, new Boolean[nums.length][(sum/2)+1]);
	}

	private boolean canPartitionMemoization(int[] nums, int idx, int sum, Boolean[][] dp) {
		if(idx > nums.length-1 || nums[idx] > sum) {
			return false;
		}
		if(nums[idx] == sum) {
			return true;
		}
		
		if(dp[idx][sum] != null) {	
			return dp[idx][sum];
		}

		dp[idx][sum] = canPartitionMemoization(nums, idx+1, sum, dp);
		dp[idx][sum - nums[idx]] = canPartitionMemoization(nums, idx+1, sum-nums[idx], dp);
		
		return dp[idx][sum] || dp[idx][sum - nums[idx]];
	}
	
	/**
	 * canPartition using tabulation
	 */
	public boolean canPartitionTabulation(int[] nums) {
		int sum = Arrays.stream(nums).boxed().collect(Collectors.summingInt(x -> x));
		
		if(sum%2 != 0) {
			return false;
		}

		Boolean[][] dp = new Boolean[nums.length][(sum/2)+1];
		
		for(int i = 0; i < nums.length; ++i) {
			if(nums[i] == 0) {
				dp[i][0] = true;
			}
			else {
				dp[i][0] = false;
			}
		}
		
		for(int c = 0; c <= sum/2; ++c) {
			if(nums[0] == c) {
				dp[0][c] = true;
			}
			else {
				dp[0][c] = false;
			}
		}
		
		for(int i = 1; i < nums.length; ++i) {
			for(int c = 1; c <= sum/2; ++c) {
				boolean bool1 = false;
				if(c - nums[i] > 0) {
//					bool1 = dp[i][c] = dp[i-1][c-nums[i]];
					bool1 = dp[i][c] = dp[i-1][c-nums[i]];
				}
				boolean bool2 = dp[i-1][c];
				dp[i][c] = bool1 || bool2;
			}
		}
		
		return dp[nums.length-1][sum/2];
	}
	
	/**
	 * Subset Sum
	 * Given a set of positive numbers, determine if a subset exists whose sum is equal to a given number ‘S’.
	 */
	public boolean hasSubsetSumRecursive(int[] nums, int sum) {
		return hasSubsetSumRecursive(nums, sum, 0);
	}

	private boolean hasSubsetSumRecursive(int[] nums, int sum, int index) {
		if(index >= nums.length || sum < nums[index]) {
			return false;
		}

		if(sum == 0 || sum == nums[index]) {
			return true;
		}
		
		// Taking num[i] into the subset
		boolean bool1 = hasSubsetSumRecursive(nums, sum-nums[index], index+1);
		
		// Not taking num[i] into the subset
		boolean bool2 = hasSubsetSumRecursive(nums, sum, index+1);
		
		return bool1||bool2;
	}
	
	public boolean hasSubsetSumMemoization(int[] nums, int sum) {
		
		Boolean[][] dp = new Boolean[nums.length][sum+1];
		
		return hasSubsetSumMemoization(nums, sum, 0, dp);
	}

	private boolean hasSubsetSumMemoization(int[] nums, int sum, int index, Boolean[][] dp) {
		if(index >= nums.length) {
			return false;
		}

		if(dp[index][sum] != null) {
			return dp[index][sum];
		}
		
		if(sum < nums[index]) {
			return false;
		}
		
		if(sum == 0 || sum == nums[index]) {
			return true;
		}
		
		boolean bool1 = hasSubsetSumMemoization(nums, sum-nums[index], index+1, dp);
		boolean bool2 = hasSubsetSumMemoization(nums, sum, index+1, dp);
		
		dp[index][sum] = bool1 || bool2;
		return dp[index][sum];
	}
	
	public boolean hasSubsetSumTabulation(int[] nums, int sum) {
		Boolean[][] dp = new Boolean[nums.length][sum+1];
		
		// Filling the first column
		for(int i = 0; i < nums.length; ++i) {
			// Sum 0 taking nums[i]
			if(nums[i] == 0) {
				dp[i][0] = false;
			}
			else {
				dp[i][0] = false;
			}
		}
		
		// Filling the first row
		for(int i = 0; i <= sum; ++i) {
			// sum is equal to the first element
			if(nums[0] == i) {
				dp[0][i] = true;
			}
			else {
				dp[0][i] = false;
			}
		}
		
		for(int i = 1; i < nums.length; ++i) {
			for(int s = 1; s <= sum; ++s) {
				boolean bool1 = dp[i-1][s];
				boolean bool2 = false;
				if(s-nums[i] < 0) {
					bool2 = false;
				}
				else {
					bool2 = dp[i][s-nums[i]];
				}
				
				dp[i][s] = bool1 || bool2;
			}
		}
		
		return dp[nums.length-1][sum];
	}
	
	/**
	 * Minimum Subset Sum Difference
	 * Given a set of positive numbers, partition the set into two 
	 * subsets with minimum difference between their subset sums.
	 */
	public int minSubsetDiffRecursive(int[] nums) {
		int sum = Arrays.stream(nums).boxed().collect(Collectors.summingInt(x -> x));
		return minSubsetDiffRecursive(nums, 0, sum);
	}

	private int minSubsetDiffRecursive(int[] nums, int index, int diff) {
		if(index >= nums.length || diff <= 0) {
			return diff;
		}
		
		int min1 = minSubsetDiffRecursive(nums, index+1, diff-2*nums[index]); // Considering nums[index]
		int min2 = minSubsetDiffRecursive(nums, index+1, diff); // Not considering nums[index]
		
		return Math.min(Math.abs(min1), Math.abs(min2));
	}
	
	public int minSubsetDiffRecursiveEducative(int[] nums) {
		return minSubsetDiffRecursiveEducative(nums, 0, 0, 0);
	}

	private int minSubsetDiffRecursiveEducative(int[] nums, int index, int sum1, int sum2) {
		if(index == nums.length) {
			return Math.abs(sum1-sum2);
		}
		
		// Putting nums[i] in the first sub-list
		int s1 = minSubsetDiffRecursiveEducative(nums, index+1, sum1+nums[index], sum2);
		// Putting nums[i] in the second sub-list
		int s2 = minSubsetDiffRecursiveEducative(nums, index+1, sum1, sum2+nums[index]);
		
		return Math.min(s1, s2);
	}
	
	/**
	 * Top-down approach: Memoization
	 * @param nums Array of integers
	 * @return Minimum difference in the subsets
	 */
	public int minSubsetDiffMemoization(int[] nums) {
		int sum = Arrays.stream(nums).boxed().collect(Collectors.summingInt(x -> x));
		Integer[][] dp = new Integer[nums.length][sum+1];
		return minSubsetDiffMemoization(nums, 0, 0, 0, dp);
	}

	private int minSubsetDiffMemoization(int[] nums, int index, int sum1, int sum2, Integer[][] dp) {
		if(index == nums.length) {
			return Math.abs(sum1-sum2);
		}
		
		if(dp[index][sum1] == null) {
			int diff1 = minSubsetDiffMemoization(nums, index+1, sum1+nums[index], sum2, dp);
			int diff2 = minSubsetDiffMemoization(nums, index+1, sum1, sum2+nums[index], dp);
			
			dp[index][sum1] = Math.min(diff1, diff2);
		}
		
		return dp[index][sum1];
	}
	
	/**
	 * Bottom-up approach
	 */
	public int minSubsetDiffTabulation(int[] nums) {
		int sum = Arrays.stream(nums).boxed().collect(Collectors.summingInt(x -> x));
		boolean[][] dp = new boolean[nums.length][sum/2+1];
		
		for(int i = 0; i < nums.length; ++i) {
				dp[i][0] = true;
		}
		
		for(int s = 0; s <= sum/2; ++s) {
			if(s == nums[0]) {
				dp[0][s] = true;
			}
		}
		
		for(int i = 1; i < nums.length; ++i) {
			for(int s = 1; s <= sum/2; ++s) {
				if(dp[i-1][s]) {
					dp[i][s] = true;
				}
				else {
					if(nums[i] <= s) {
						dp[i][s] = dp[i-1][s-nums[i]];
					}
				}
			}
		}
		
		int sum1 = sum/2;
		for(; sum1 >= 0; --sum1) {
			if(dp[nums.length-1][sum1] == true) {
				break;
			}
		}
		
		int sum2 = sum - sum1;
		
		return Math.abs(sum1-sum2);
	}
	
	
	/**
	 * Count of Subset Sum
	 */
	public int countSubsets(int[] nums, int sum) {
		int[][] dp = new int[nums.length][sum+1];
		
		for(int i = 0; i < nums.length; ++i) {
			dp[i][0] = 1;
		}
		
		for(int s = 1; s <= sum; ++s) {
			if(s == nums[0]) {
				dp[0][s] = 1;
			}
		}
		
		for(int i = 1; i < nums.length; ++i) {
			for(int s = 1; s <= sum; ++s) {
				int val1 = dp[i-1][s];
				int val2 = 0;
				if(s >= nums[i]) {
					val2 = dp[i-1][s-nums[i]];
				}
				
				dp[i][s] = val1 + val2;
			}
		}
		
		return dp[nums.length-1][sum];
	}
	
	/**
	 * Target Sum
	 * You are given a set of positive numbers and a target sum ‘S’.
	 * Each number should be assigned either a ‘+’ or ‘-’ sign. 
	 * We need to find the total ways to assign symbols to make the sum 
	 * of the numbers equal to the target ‘S’.
	 * Input: {1, 1, 2, 3}, S=1
	 * Output: 3
	 * Explanation: The given set has '3' ways to make a sum of '1': 
	 * 	{+1-1-2+3} & {-1+1-2+3} & {+1+1+2-3}
	 */
	public int findTargetSubsets(int[] nums, int s) {
		return findTargetSubsets(nums, s, 0, 0);
	}

	private int findTargetSubsets(int[] nums, int target, int sum, int index) {
		if(index == nums.length) {
			if(sum == target) {
				return 1;
			}
			else {
				return 0;
			}
		}
		
		int left = findTargetSubsets(nums, target, sum-nums[index], index+1);
		int right = findTargetSubsets(nums, target, sum+nums[index], index+1);
		
		return left+right;
	}
	
	/**
	 * The above problem boils down to how many subsets are there with sum (S+sum(Array))/2
	 */
	public int findTargetSubsetsTabulation(int[] nums, int target) {
		int sum = Arrays.stream(nums).boxed().collect(Collectors.summingInt(x -> x));
		
		if(sum < target || (target+sum)%2 == 1) {
			return 0;
		}
		
		sum = (target+sum)/2;
		
		int[][] dp = new int[nums.length][sum+1];
		
		for(int i = 0; i < nums.length; ++i) {
			dp[i][0] = 1;
		}
		
		for(int s = 1; s <= sum; ++s) {
			if(nums[0] == s) {
				dp[0][s] = 1;
			}
		}
		
		for(int i = 1; i < nums.length; ++i) {
			for(int s = 1; s <= sum; ++s) {
				dp[i][s] += dp[i-1][s];
				if(nums[i] <= s) {
					dp[i][s] += dp[i-1][s-nums[i]];
				}
			}
		}
		
		return dp[nums.length-1][sum];
	}
}
