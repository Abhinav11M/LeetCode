package geeksforgeeks.dp;

public class DPMainNew {

	public static void main(String[] args) {
		DPQuestionsNew dp = new DPQuestionsNew();
		System.out.println(dp.getNthUglyNo(13));

		System.out.println(dp.catalanDP(6));
		System.out.println(dp.bellNumber(5));

		System.out.println(dp.countP(3,2));

		System.out.println(dp.tileCount(3));
		System.out.println(dp.tileCount(4));

		System.out.println(dp.binomialCoefficient(6,2));
		System.out.println(dp.binomialCoefficient(6,3));

		System.out.println(dp.permutationCoefficient(6,3));

		System.out.println(dp.countCoins(new int[] {1,2,3}, 4));
		System.out.println(dp.countCoins(new int[] {2, 5, 3, 6}, 10));

		System.out.println(dp.countCoinsDP(new int[] {1,2,3}, 4));
		System.out.println(dp.countCoinsDP(new int[] {2, 5, 3, 6}, 10));
		System.out.println(dp.countCoinsDP(new int[] {2}, 3));
		System.out.println(dp.countCoinsDP(new int[] {1,2,5}, 11));

		System.out.println(dp.countFriendsPairings(3));
		
		System.out.println(dp.isSubsetSum(new int[] {3, 34, 4, 12, 5, 2}, 9));
		
		System.out.println(dp.cutRod(new int[] {1, 5, 8, 9, 10, 17, 17, 20}));
		System.out.println(dp.cutRodOpt(new int[] {1, 5, 8, 9, 10, 17, 17, 20}));

		System.out.println(dp.countWays(3,2));
	}

}
