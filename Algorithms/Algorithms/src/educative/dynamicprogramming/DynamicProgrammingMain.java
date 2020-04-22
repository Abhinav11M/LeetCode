package educative.dynamicprogramming;

public class DynamicProgrammingMain {
	public static void main(String[] args) {
		DynamicProgramming dp = new DynamicProgramming();
		int[] profits = { 1, 6, 10, 16 };
		int[] weights = { 1, 2, 3, 5 };
		int maxProfit = dp.knapsackRecursive(profits, weights, 7);
		System.out.println("Total knapsack profit ---> " + maxProfit);
		maxProfit = dp.knapsackRecursive(profits, weights, 6);
		System.out.println("Total knapsack profit ---> " + maxProfit);
		
		// Using memoization
		maxProfit = dp.knapsackMemoization(profits, weights, 7);
		System.out.println("Total knapsack profit ---> " + maxProfit);
		maxProfit = dp.knapsackMemoization(profits, weights, 6);
		System.out.println("Total knapsack profit ---> " + maxProfit);

		// Using Tabulation
		maxProfit = dp.knapsackTabulation(profits, weights, 7);
		System.out.println("Total knapsack profit ---> " + maxProfit);
		maxProfit = dp.knapsackTabulation(profits, weights, 6);
		System.out.println("Total knapsack profit ---> " + maxProfit);
		
		System.out.println(dp.canPartition(new int[] {1, 2, 3, 4}));
		System.out.println(dp.canPartition(new int[] {1, 1, 3, 4, 7}));
		System.out.println(dp.canPartition(new int[] {2, 3, 4, 6}));

		System.out.println(dp.canPartitionMemoization(new int[] {1, 2, 3, 4}));
		System.out.println(dp.canPartitionMemoization(new int[] {1, 1, 3, 4, 7}));
		System.out.println(dp.canPartitionMemoization(new int[] {2, 3, 4, 6}));

		System.out.println(dp.canPartitionMemoizationTabulation(new int[] {1, 2, 3, 4}));
		System.out.println(dp.canPartitionMemoizationTabulation(new int[] {1, 1, 3, 4, 7}));
		System.out.println(dp.canPartitionMemoizationTabulation(new int[] {2, 3, 4, 6}));
		
		
		System.out.println("***Has Subset ***");
		System.out.println(dp.hasSubsetSumRecursive(new int[] {1, 2, 3, 7}, 6));
		System.out.println(dp.hasSubsetSumRecursive(new int[] {1, 2, 4, 10}, 8));
		System.out.println(dp.hasSubsetSumRecursive(new int[] {1, 2, 7, 1, 5}, 10));
		System.out.println(dp.hasSubsetSumRecursive(new int[] {1, 3, 4, 8}, 6));

		System.out.println(dp.hasSubsetSumMemoization(new int[] {1, 2, 3, 7}, 6));
		System.out.println(dp.hasSubsetSumMemoization(new int[] {1, 2, 4, 10}, 8));
		System.out.println(dp.hasSubsetSumMemoization(new int[] {1, 2, 7, 1, 5}, 10));
		System.out.println(dp.hasSubsetSumMemoization(new int[] {1, 3, 4, 8}, 6));
		
		System.out.println(dp.hasSubsetSumTabulation(new int[] {1, 2, 3, 7}, 6));
		System.out.println(dp.hasSubsetSumTabulation(new int[] {1, 2, 4, 10}, 8));
		System.out.println(dp.hasSubsetSumTabulation(new int[] {1, 2, 7, 1, 5}, 10));
		System.out.println(dp.hasSubsetSumTabulation(new int[] {1, 3, 4, 8}, 6));

		System.out.println(dp.minSubsetDiffRecursive(new int[] {1, 2, 3, 9}));
		System.out.println(dp.minSubsetDiffRecursive(new int[] {1, 2, 7, 1, 5}));
		System.out.println(dp.minSubsetDiffRecursive(new int[] {1, 3, 100, 4}));

		System.out.println(dp.minSubsetDiffRecursiveEducative(new int[] {1, 2, 3, 9}));
		System.out.println(dp.minSubsetDiffRecursiveEducative(new int[] {1, 2, 7, 1, 5}));
		System.out.println(dp.minSubsetDiffRecursiveEducative(new int[] {1, 3, 100, 4}));

		System.out.println(dp.minSubsetDiffMemoization(new int[] {1, 2, 3, 9}));
		System.out.println(dp.minSubsetDiffMemoization(new int[] {1, 2, 7, 1, 5}));
		System.out.println(dp.minSubsetDiffMemoization(new int[] {1, 3, 100, 4}));

		System.out.println(dp.minSubsetDiffTabulation(new int[] {1, 2, 3, 9}));
		System.out.println(dp.minSubsetDiffTabulation(new int[] {1, 2, 7, 1, 5}));
		System.out.println(dp.minSubsetDiffTabulation(new int[] {1, 3, 100, 4}));
		
		System.out.println(dp.countSubsets(new int[] {1, 1, 2, 3}, 4));
		System.out.println(dp.countSubsets(new int[] {1, 2, 7, 1, 5}, 9));
		System.out.println(dp.countSubsets(new int[] {1, 2, 3, 4, 5, 6}, 7));
		
		System.out.println(dp.findTargetSubsets(new int[] {1, 1, 2, 3}, 1));
		System.out.println(dp.findTargetSubsets(new int[] {1, 2, 7, 1}, 9));

		System.out.println(dp.findTargetSubsetsTabulation(new int[] {1, 1, 2, 3}, 1));
		System.out.println(dp.findTargetSubsetsTabulation(new int[] {1, 2, 7, 1}, 9));
	}
}
