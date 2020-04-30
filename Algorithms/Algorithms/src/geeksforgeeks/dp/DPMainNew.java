package geeksforgeeks.dp;

import java.util.List;

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
		
		int a[][] = {{4, 5, 3, 2}, 
                {2, 10, 1, 4}}; 
		int t[][] = {{0, 7, 4, 5}, 
				{0, 9, 2, 8}}; 
		int e[] = {10, 12}, x[] = {18, 7}; 
		
		System.out.println(dp.carAssembly(a,t,e,x));
		System.out.println(dp.carAssemblyDP(a,t,e,x));
		
		int mat[][] = new int[][] { { 9, 6, 5, 2 }, { 8, 7, 6, 5 }, { 7, 3, 1, 6 }, { 1, 1, 1, 7 }, };
		
		dp.findSnakeSequence(mat);
		dp.findSnakeSequenceRec2(mat);
		List<MatCoordinates> snakePath = dp.findSnakeSequenceDP(mat);
		
		System.out.println(snakePath);
		
		dp.reverseFibonacci(8);
		
		System.out.println(dp.lcsRecursive("ABCDGH", "AEDFHR"));
		System.out.println(dp.lcsRecursive("AGGTAB", "GXTXAYB"));
		System.out.println(dp.lcsDP("ABCDGH", "AEDFHR"));
		System.out.println(dp.lcsDP("AGGTAB", "GXTXAYB"));
		
		System.out.println(dp.lisDP(new int[] {10, 22, 9, 33, 21, 50, 41, 60, 80}));
		System.out.println(dp.lisDP(new int[] {10, 9}));
		
	}

}
