package geeksforgeeks.dp;

import java.util.Arrays;

public class DPQuestionsNew {
	/**
	 * Ugly number
	 */
	int getNthUglyNo(int n) {
		int[] uglyArr = new int[n];
		uglyArr[0] = 1;
		int i2 = 0, i3 = 0, i5 = 0;
		
		for(int i = 1; i < n; ++i) {
			int ugly = Math.min(Math.min(uglyArr[i2]*2, uglyArr[i3]*3), uglyArr[i5]*5);
			uglyArr[i] = ugly;

			if(ugly == uglyArr[i2]*2) {
				++i2;
			}
			if(ugly == uglyArr[i3]*3) {
				++i3;
			}
			if(ugly == uglyArr[i5]*5) {
				++i5;
			}
		}
		
		return uglyArr[n-1];
	}
	
	/**
	 * Fibonacci
	 */
	public int nthFibonacci(int n) {
		if(n == 0) {
			return 0;
		}
		
		int[] fiboArr = new int[n+1];
		fiboArr[0] = 0;
		fiboArr[1] = 1;
		
		for(int i = 2; i <= n; ++i) {
			fiboArr[i] = fiboArr[i-1] + fiboArr[i-2];
		}
		
		return fiboArr[n];
	}
	
	/**
	 * Nth Catalan number
	 */
	public int catalanDP(int n) {
		int[] dp = new int[n];
		dp[0] = 1;
		
		for(int i = 1; i < n; ++i) {
			int catalan = 0;
			for(int j = 0; j < i; ++j) {
				catalan += dp[j]*dp[i-1-j];
			}
			dp[i] = catalan;
		}
		
		return dp[n-1];
	}
	
	/**
	 * Bell Numbers (Number of ways to Partition a Set)
	 */
	public int bellNumber(int n)  {
		int[][] bellTriangle = new int[n][n];
		bellTriangle[0][0] = 1;
		
		for(int i = 1; i < n; ++i) {
			for(int j = 0; j <= i; ++j) {
				if(j == 0) {
					bellTriangle[i][j] = bellTriangle[i-1][i-1];
				}
				else {
					bellTriangle[i][j] = bellTriangle[i][j-1] + bellTriangle[i-1][j-1];
				}
			}
		}
		
		return bellTriangle[n-1][n-1];
	}
	
	/**
	 * Count number of ways to partition a set into k subsets
	 * Sterling number
	 * S(n+1,k) = k*S(n,k) + S(n,k-1)
	 * or S(n,k) = k*S(n-1,k) + S(n-1, k-1)
	 */
	public int countP(int n, int k)  {
		int[][] dp = new int[n+1][k+1]; // n elements and k subsets
		
		for(int i = 0; i <= n; ++i) {
			dp[i][0] = 0; // i elements to zero subsets is not possible
		}
		
		for(int i = 0; i <= k; ++i) {
			dp[0][i] = 0; // 0 elements into i subsets is not possible
		}
		
		for(int i = 1; i <= n; ++i) {
			for(int j = 1; j <= k; ++j) {
				if(j == 1 || i == j) {
					dp[i][j] = 1;
				}
				else {
					dp[i][j] = k*dp[i-1][j] + dp[i-1][j-1];
				}
			}
		}
		
		return dp[n][k];
	}
	
	/**
	 * Tiling Problem
	 */
	public int tileCount(int n) {
		int[] dp = new int[n+1];
		dp[0] = 1;
		dp[1] = 1;
		
		for(int i = 2; i <= n; ++i) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		
		return dp[n];
	}
	
	/**
	 * Binomial Coefficient
	 * nCr = (n-1)C(r-1) + (n-1)C(r)
	 * 
	 */
	public int binomialCoefficient(int n, int r) {
		if(r > n) {
			return 0;
		}
		
		int[][] dp = new int[n+1][r+1];
		
		for(int i = 0; i <= n; ++i) {
			dp[i][0] = 1;
		}
		
		for(int i = 1; i <= n; ++i) {
			for(int j = 1; j <= Math.min(i,r); ++j) {
				if(i == j) {
					dp[i][j] = 1;
				}
				else {
					dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
				}
			}
		}
		
		return dp[n][r];
	}
	
	/**
	 * Permutation coefficient
	 * nPr = (n-1)P(r) + r*(n-1)P(r-1)
	 */
	public int permutationCoefficient(int n, int r) {
		
		int[][] dp = new int[n+1][r+1];
		
		for(int i = 0; i <= n; ++i) {
			dp[i][0] = 1;
		}
		
		for(int i = 1; i <= n; ++i) {
			for(int j = 1; j <= Math.min(i, r); ++j) {
				dp[i][j] = dp[i-1][j] + j*dp[i-1][j-1];
			}
		}
		
		return dp[n][r];
	}
	
	/**
	 * Coin change problem
	 */
	public int countCoins(int[] coins, int target) {
		Arrays.sort(coins);
		return countCoins(coins, target, 0, 0);
	}

	private int countCoins(int[] coins, int target, int index, int sum) {
		if(sum == target) {
			return 1;
		}
		
		if(index >= coins.length || coins[index]+sum > target) {
			return 0;
		}
		
		int including = countCoins(coins, target, index, sum+coins[index]);
		int excluding = countCoins(coins, target, index+1, sum);
		
		return including + excluding;
	}
	
	public int countCoinsDP(int[] coins, int target) {
		
		if(coins == null) {
			return 0;
		}
		
		if(coins.length == 0) {
			if(target == 0) {
				return 1;
			}
			else {
				return 0;
			}
		}
		
		int[][] dp = new int[coins.length][target+1];
		
		for(int i = 0; i < coins.length; ++i) {
			dp[i][0] = 1; // Getting 0 with any coins can have an empty list, i.e. 1 way
		}
		
		for(int j = 1; j <= target; ++j) {
			if(j%coins[0] == 0) {
				dp[0][j] = 1;
			}
			else {
				dp[0][j] = 0;
			}
		}
		
		for(int i = 1; i < coins.length; ++i) {
			for(int j = 1; j <= target; ++j) {
				int count = dp[i-1][j];
				if(j-coins[i] >= 0) {
					count += dp[i][j-coins[i]];
				}
				
				dp[i][j] = count;
			}
		}
		
		return dp[coins.length-1][target] == 0 ? -1 : dp[coins.length-1][target];
	}
	
	/**
	 * Friends pairing problem
	 * If nth friend enters, we will have two possibilities:
	 * 	i) nth friend stays single, in which case the problem reduces to 1*f(n-1), since for nth elements
	 * 		there is only one way to be single.
	 * 	ii) nth friends pairs up with someone. Nth friend can pair up with (n-1) friends in (n-1) ways.
	 * 		So the problem reduces to (n-1)*f(n-2).
	 * So the recurrence relation becomes :
	 * f(n) = f(n-1) + (n-1)f(n-2)
	 */
	public int countFriendsPairings(int n) {
		int[] dp = new int[n+1];
		dp[0] = 1; // Empty {}
		dp[1] = 1; // {1}
		
		for(int i = 2; i <= n; ++i) {
			dp[i] = dp[i-1] + (i-1)*dp[i-2];
		}
		
		return dp[n];
	}
	
	/**
	 * Subset Sum Problem
	 * Given a set of non-negative integers, and a value sum, 
	 * determine if there is a subset of the given set with sum equal to given sum.
	 */
	public boolean isSubsetSum(int set[], int sum) 
	{
		boolean[] dp = new boolean[sum+1];
		dp[0] = true; // Sum = 0; Don't take any element
		
		for(int i = 1; i < set.length; ++i) {
			for(int j = sum; j >= set[i]; j--) {
				dp[j] = dp[j] || dp[j - set[i]];
			}
		}
		
		return dp[sum];
	}
	
	/**
	 * Largest divisible pairs subset
	 */
	int largestSubsetRecursive(int[] nums) {
		
		Arrays.sort(nums);
		int n = nums.length;
		
		int[] dp = new int[n];
		// Last index has the max value. So it can't have more then one divisor (itself).
		dp[n-1] = 1;
		
		for(int i = n-2; i >=0; --i) {

			int maxDivisorNum = 0;
			for(int j = i+1; j < n; ++j) {
				if(nums[i] % nums[j] == 0) {
					maxDivisorNum = Math.max(dp[j], maxDivisorNum);
				}
			}
			dp[i] = maxDivisorNum+1;
		}
		
		return Arrays.stream(dp).max().getAsInt();
	}
	
	/**
	 * Cutting a Rod
	 */
	public int cutRod(int[] prices) {
		
		int n = prices.length;
		int[][] dp = new int[n+1][n+1];
		
		for(int i = 0; i <= n; ++i) {
			dp[i][0] = 0; // 0 length has 0 profit
		}
		
		for(int j = 0; j <= n; ++j) {
			dp[0][j] = 0; // 0 cut has 0 profit
		}
		
		for(int i = 1; i <= n; ++i) {
			for(int j = 1; j <= n; ++j) {
				int left = dp[i-1][j]; // Not considering cut[i]
				int right = 0;
				if(j >= i) { //considering cut[i]
					int temp = j/i;
					right = temp*prices[i-1] + dp[i-1][j-temp*i];
				}
				dp[i][j] = Math.max(left, right);
			}
		}
		
		return dp[n][n];
	}
	
	public int cutRodOpt(int[] prices) {
		
		int n = prices.length;

		int[] dp = new int[n+1];
		dp[0] = 0;
		
		for(int i = 1; i <= n; ++i) { // Lengths of the rod is i
			for(int j = 1; j <= i; ++j) { // Cutting off j from i
				dp[i] = Math.max(dp[i], prices[j-1] + dp[i-j]); // Price for length j is prices[j-1]
			}
		}
		
		return dp[n];
	}
	
	/**
	 * Painting Fence Algorithm
	 * <a>https://www.youtube.com/watch?v=deh7UpSRaEY</a>
	 * @param n: Number of fences
	 * @param k: Number of colors
	 */
	public long countWays(int n, int k)  {
		int[] dp = new int[n+1];
		dp[1] = k; // 1 fence with k colors = k ways (All diff)
		
		int same = 0, diff = k;
		for(int i = 2; i <= n; ++i) {
			same = diff; // Diff in last run * 1
			diff = dp[i-1]*(k-1); // All[i-1] * (k-1)
			
			dp[i] = same + diff;
		}
		
		return dp[n];
	}
	
}
