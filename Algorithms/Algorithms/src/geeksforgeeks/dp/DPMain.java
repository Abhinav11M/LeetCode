package geeksforgeeks.dp;

public class DPMain {
	public static void main(String[] args) {
		DPQuestions dp = new DPQuestions();
		
		// ===== Ugly Numbers =====
		System.out.println(dp.getNthUglyNo(10));
		System.out.println(dp.getNthUglyNoDP(10));
		System.out.println(dp.getNthUglyNoDPOpt(10));
		// ===== Ugly Numbers =====
		
		// ===== Fibonacchi series =====
		System.out.println(dp.fib(10));
		System.out.println(dp.fibMem(10));
		// ===== Fibonacchi series =====

		// ===== Catalan Numbers =====
		System.out.println(dp.catalan(7));
		// ===== Catalan Numbers =====

		// ===== Binomial Coefficient =====
		System.out.println(dp.binomialCoeff(7,3));
		System.out.println(dp.binomialCoeff(9,4));
		// ===== Binomial Coefficient =====
		
		// ===== Permutation Coefficient =====
		System.out.println(dp.permutationCoeff(10, 3));
		System.out.println(dp.permutationCoeff(7,2));
		// ===== Permutation Coefficient =====
		
		// ===== Tiling Problem =====
		System.out.println(dp.numWaysToTile(4));
		System.out.println(dp.numWaysToTile(5));
		System.out.println(dp.numWaysToTile(6));
		// ===== Tiling Problem =====

		// ===== Gold mine problem =====
		System.out.println(dp.getMaxGold( new int[][] { {1,2}, {3,4} } ));
		System.out.println(dp.getMaxGold( new int[][] { {1,3,3}, {2,1,4}, {0,6,4} } ));
		System.out.println(dp.getMaxGold( new int[][] { 
			{1, 3, 1, 5}, 
			{2, 2, 4, 1}, 
			{5, 0, 2, 3}, 
			{0, 6, 1, 2} 
		} ));
		
		System.out.println(dp.getMaxGold( new int[][] { 
			{1, 1, 12}, 
			{9, 3, 8}, 
			{12, 7, 10}
		} ));
		
		System.out.println(dp.getMaxGoldDP( new int[][] { 
			{1, 3, 1, 5}, 
			{2, 2, 4, 1}, 
			{5, 0, 2, 3}, 
			{0, 6, 1, 2} 
		} ));
		
		System.out.println(dp.getMaxGoldDP( new int[][] { 
			{1, 1, 12}, 
			{9, 3, 8}, 
			{12, 7, 10}
		} ));
		
		System.out.println(dp.getMaxGoldDP( new int[][] { 
			{1, 4, 101, 5}, 
			{50, 3, 9, 4}, 
			{9, 8, 3, 9}, 
			{7, 1, 5, 7} 
		} ));
		
		// This algo is not correct
		System.out.println(dp.getMaxGold( new int[][] { 
			{1, 4, 101, 5}, 
			{50, 3, 9, 4}, 
			{9, 8, 3, 9}, 
			{7, 1, 5, 7} 
		} ));
		// ===== Gold mine problem =====
		
		// ===== Coin change problem =====
		System.out.println(dp.countCoins(new int[]{2, 3, 5, 6}, 10));
		System.out.println(dp.countCoins(new int[]{1, 2, 3}, 4));

		System.out.println(dp.countCoinsDP(new int[]{2, 3, 5, 6}, 10));
		System.out.println(dp.countCoinsDP(new int[]{1, 2, 3}, 4));
		// ===== Coin change problem =====
		
		// ===== Friends Pairing Problem =====
		System.out.println(dp.countFriendsPairings(3));
		System.out.println(dp.countFriendsPairings(4));
		System.out.println(dp.countFriendsPairings(5));
		// ===== Friends Pairing Problem =====
		
		// ===== Subset Sum Problem in O(sum) space =====
		System.out.println(dp.isSubsetSum(new int[] {4, 1, 10, 12, 5, 2}, 9));
		System.out.println(dp.isSubsetSum(new int[] {1, 8, 2, 5}, 4));
		// ===== Subset Sum Problem in O(sum) space =====
		
	}
}
