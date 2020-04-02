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
	public boolean canPartitionMemoizationTabulation(int[] nums) {
		int sum = Arrays.stream(nums).boxed().collect(Collectors.summingInt(x -> x));
		
		if(sum%2 != 0) {
			return false;
		}

//		int halfSum = sum/2;
		
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
					bool1 = dp[i][c] = dp[i-1][c-nums[i]];
				}
				boolean bool2 = dp[i-1][c];
				dp[i][c] = bool1 || bool2;
			}
		}
		
		return dp[nums.length-1][sum/2];
	}
}
