package geeksforgeeks.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import helpers.Helpers;

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

	
	/**
	 * Car assembly
	 */
	public int carAssembly(int[][] a, int[][] t, int[] e, int[] x) {
		int n = a[0].length;
		
		// f(1,n)
		int f1n = x[0] + carAssembly(a, t, 0, a[0].length-1);
		int f2n = x[1] + carAssembly(a, t, 1, a[0].length-1);
		
		int minValue = Math.min(f1n+e[0], f2n+e[1]);
		
		return minValue;
	}

	private int carAssembly(int[][] a, int[][] t, int level, int n) {
		if(n == 0) {
			return a[level][0];
		}
		
		int f1 = carAssembly(a, t, level, n-1) + a[level][n];
		int nextLevel = level == 1 ? 0 : 1;
		int f2 = carAssembly(a, t, nextLevel, n-1) + t[1][n] + a[level][n];
		
		return Math.min(f1, f2);
	}

	public int carAssemblyDP(int[][] a, int[][] t, int[] e, int[] x) {
		int n = a[0].length;
		
		int[] dp0 = new int[n];
		int[] dp1 = new int[n];
		
		dp0[0] = e[0];
		dp1[0] = e[1];
		
		dp0[0] = e[0] + a[0][0];
		dp1[0] = e[1] + a[1][0];
		
		for(int i = 1; i < n; ++i) {
			// Calculate f(0,n)
			int up1 = dp0[i-1] + a[0][i]; // One coming from left
			int up2 = dp1[i-1] + t[1][i] + a[0][i]; // One coming from down
			dp0[i] = Math.min(up1, up2);
			
			//Calculate f(1,n)
			int down1 = dp1[i-1] + a[1][i]; // Coming from left
			int down2 = dp0[i-1] + t[0][i] + a[1][i]; // Coming from up
			dp1[i] = Math.min(down1, down2);
		}
		
		return Math.min(dp0[n-1]+x[0], dp1[n-1]+x[1]);
	}
	
	/**
	 * Find maximum length Snake sequence
	 */
	public int findSnakeSequence(int[][] mat) {
		int m = mat.length;
		int n = mat[0].length;
		
		int[][] result = new int[m][n];
//		result[m-1][n-1] = 1;
		
		int res = findSnakeSequence(mat, result, 0, 0, m, n);
		
		return 0;
	}

	private int findSnakeSequence(int[][] mat, int[][] result, int i, int j, int m, int n) {
		if(i == m-1 && j == n-1) { // Reached the end of matrix
			result[i][j] = 1;
			return 1;
		}
		
		if(j == n-1 ) { // Can't go any right
			if(Math.abs(mat[i+1][j] - mat[i][j]) != 1) {
				result[i][j] = Math.max(1, result[i][j]);
			}
			else {
				result[i][j] = Math.max(1+findSnakeSequence(mat, result, i+1, j, m, n), result[i][j]);
			}
		}
		else if(i == m-1) { // Can't go any down
			if(Math.abs(mat[i][j+1]-mat[i][j]) != 1) {
				result[i][j] = Math.max(1, result[i][j]);
			}
			else {
				result[i][j] = Math.max(1+findSnakeSequence(mat, result, i, j+1, m, n), result[i][j]);
			}
		}
		else {
			int temp1 = 0, temp2 = 0;
			if(Math.abs(mat[i+1][j] - mat[i][j]) != 1) {
				temp1 = 1;
			}
			else {
				temp1 = 1+findSnakeSequence(mat, result, i+1, j, m, n);
			}
			
			if(Math.abs(mat[i][j+1]-mat[i][j]) != 1) {
				temp2 = 1;
			}
			else {
				temp2 = 1+findSnakeSequence(mat, result, i, j+1, m, n);
			}
			
			result[i][j] = Math.max(Math.max(temp1, temp2), result[i][j]);
		}
		
		return result[i][j];
	}
	
	// First element has 0 length
	public int findSnakeSequenceRec2(int[][] mat) {
		int r = mat.length;
		int c = mat[0].length;
		int maxLen = 0;
		int maxX = 0, maxY = 0;
		int[][] result = new int[r][c];
		findSnakeSequenceRec2(mat, result, r-1, c-1, r-1, c-1, maxLen, maxX, maxY);
		
		return 0;
	}
	
	private int findSnakeSequenceRec2(int[][] mat, int[][] res, int r, int c, int i, int j, int maxLen, int maxX, int maxY) {
		if(i == 0 && j == 0) {
			return 0;
		}
		
		int up = 0; 
		if(i > 0) { // Not the end of column
			up = 1+findSnakeSequenceRec2(mat, res, r, c, i-1, j, maxLen, maxX, maxY);
			if(Math.abs(mat[i][j] - mat[i-1][j]) == 1) {
				res[i][j] = Math.max(res[i][j], up);
			}
		}
		
		int left = 0;
		if(j > 0) {
			left = 1+findSnakeSequenceRec2(mat, res, r, c, i, j-1, maxLen, maxX, maxY);
			if(Math.abs(mat[i][j] - mat[i][j-1]) == 1) {
				res[i][j] = Math.max(res[i][j], left);
			}
		}
		
		if(res[i][j] > maxLen) {
			maxX = j;
			maxY = i;
			
			maxLen = res[i][j];
		}
		
		return res[i][j];
	}
	
	public List<MatCoordinates> findSnakeSequenceDP(int[][] mat) {
		
		int nRows = mat.length;
		int nCols = mat[0].length;
		
		int[][] result = new int[nRows][nCols];
		
		result[0][0] = 0;
		int maxLen = 0;
		int maxR = 0;
		int maxC = 0;
		
		for(int i = 0; i < nRows; ++i) {
			for(int j = 0; j < nCols; ++j) {
				if(i == 0 && j == 0) {
					continue;
				}
				
				// coming from up
				if(i > 0 && Math.abs(mat[i][j]-mat[i-1][j]) == 1) {
					result[i][j] = Math.max(result[i][j], 1+result[i-1][j]);
				}
				
				// Coming from left
				if(j > 0 && Math.abs(mat[i][j]-mat[i][j-1]) == 1) {
					result[i][j] = Math.max(result[i][j], 1+result[i][j-1]);
				}
				
				if(result[i][j] > maxLen) {
					maxLen = result[i][j];
					maxR = i;
					maxC = j;
				}
			}
		}
		
		List<MatCoordinates> list = new ArrayList<>();
		while( (maxR >= 0 || maxC >= 0) && maxLen >= 0) {
			
			list.add(new MatCoordinates(maxR, maxC));
			
			if(maxC > 0 && result[maxR][maxC-1] == maxLen-1) {
				// Move left
				--maxC;
			}
			else if(maxR > 0){
				--maxR;
			}
			--maxLen;
		}
		
		return list;
	}
	
	/**
	 * Print Fibonacci Series in reverse order
	 */
	void reverseFibonacci(int n) {
		int[] fibo = new int[n];
		
		fibo[n-1] = 0;
		fibo[n-2] = 1;
		
		int i = n-3;
		while(i >= 0) {
			fibo[i] = fibo[i+1] + fibo[i+2];
			--i;
		}
		
		Helpers.printArr(fibo);
	}
	
	/**
	 * Longest common substring
	 */
	public int lcsRecursive(String str1, String str2) {
		return lcsRecursive(str1, str2, str1.length()-1, str2.length()-1);
	}

	private int lcsRecursive(String str1, String str2, int idx1, int idx2) {
		
		if(idx1 < 0 || idx2 < 0) {
			return 0;
		}
		
		// If last characters are a match
		if(str1.charAt(idx1) == str2.charAt(idx2)) {
			return 1 + lcsRecursive(str1, str2, idx1-1, idx2-1);
		}
		else {
			return Math.max(lcsRecursive(str1, str2, idx1-1, idx2), lcsRecursive(str1, str2, idx1, idx2-1));
		}
	}
	
	public int lcsDP(String str1, String str2) {
		int[][] dp = new int[str1.length()][str2.length()];
		
		int count = 0;
		// Along str2 (row)
		for(int i = 0; i < str2.length(); ++i) {
			if(str2.charAt(i) == str1.charAt(0)) {
				count = 1;
			}
			dp[0][i] = count;
		}
		
		count = 0;
		// Along str1 (col)
		for(int i = 0; i < str1.length(); ++i) {
			if(str1.charAt(i) == str2.charAt(0)) {
				count = 1;
			}
			dp[i][0] = count;
		}
		
		for(int i = 1; i < str1.length(); ++i) {
			for(int j = 1; j < str2.length(); ++j) {
				if(str1.charAt(i) == str2.charAt(j)) {
					dp[i][j] = 1+dp[i-1][j-1];
				}
				else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
				}
			}
		}
		
		return dp[str1.length()-1][str2.length()-1];
	}
	
	/**
	 * Longest Increasing Subsequence
	 */
	public int lisDP(int[] nums) {
		if(nums.length == 0 || nums.length == 1) {
			return nums.length;
		}
		
		int n = nums.length;
		int[] dp = new int[n];
		
		for(int i = 0; i < n; ++i) {
			dp[i] = 1; // Each number has atleast count 1
		}
		
		int lis = Integer.MIN_VALUE;
		
		for(int i = 1; i < n; ++i) {
			for(int j = 0; j < i; ++j) {
				if(nums[j] < nums[i]) { // Increasing
					dp[i] = Math.max(dp[j]+1, dp[i]);
				}
			}
			
			lis = Math.max(lis, dp[i]);
		}
		
		return lis;
	}
	
	
	public boolean canJump(int[] nums) {
		if(nums.length == 0) {
			return true;
		}
		if(nums.length == 1) {
			if(nums[0] >= 0) {
				return true;
			}
			else {
				return false;
			}
		}
		
		int n = nums.length;
		boolean[] dp = new boolean[n];
		
		for(int i = 0; i < n; ++i) {
			if(nums[0] >= i) {
				dp[i] = true;
			}
		}
		
		int trueIdx = 0;
		for(int i = 1; i < n-1; ++i) {
			for(int j = trueIdx; j < n; ++j) {
				boolean bool2 = false;
				
				if(dp[i] == true && j-i-nums[i] <= 0) {
					bool2 = true;
				}
				
				dp[j] = dp[j]||bool2;
				if(dp[j] == true) {
					trueIdx = j;
				}
			}
		}
		
        return dp[n-1];
    }

	public boolean canJumpGreedy(int[] nums) {
		int lastIndexToReach = nums.length-1;
		
		for(int i = lastIndexToReach-1; i >= 0; i--) {
			if(nums[i] + i >= lastIndexToReach) { // We can jump from i to last index
				lastIndexToReach = i;
			}
		}
		
		return lastIndexToReach == 0;
	}
	
	/**
	 * Jump Game II : Given an array of non-negative integers, you are 
	 * initially positioned at the first index of the array.
	 */
	public int minJump(int[] nums) {
        
        int[] jumps = new int[nums.length];
        int indexToReach = nums.length-1;
        int minJumps = Integer.MAX_VALUE;
        
        Arrays.fill(jumps, Integer.MAX_VALUE);
        jumps[jumps.length-1] = 0;
        
        for(int i = indexToReach-1; i >= 0; --i) {
        	if(nums[i] + i >= indexToReach) {
        		jumps[i] = 1;
        		minJumps = 1;
        	}
        	else {
        		for(int j = 1; j <= nums[i]; ++j) {
        			if(jumps[i+j] == minJumps) {
        				jumps[i] = 1+minJumps;
        				break;
        			}
        			else if(jumps[i+j] == Integer.MAX_VALUE) { // No possible jump from this index
        				
        			}
        			else {
        				jumps[i] = Math.min(jumps[i], 1+jumps[i+j]);
        			}
        		}
        	}
        	minJumps = Math.min(minJumps, jumps[i]);
        }
        
        return jumps[0];
    }
	
	/**
	 * Jump Game III
	 * Given an array of non-negative integers arr, you are initially 
	 * positioned at start index of the array. 
	 * When you are at index i, you can jump to i + arr[i] or i - arr[i], 
	 * check if you can reach to any index with value 0.
	 */
	public boolean canReachRec(int[] arr, int start) {
	
		boolean[] traversed = new boolean[arr.length];
		return canReachRec(arr, start, traversed);
    }
	
	public boolean canReachRec(int[] arr, int start, boolean[] traversed) {
		if(start > arr.length-1 || start < 0 || traversed[start] == true) {
			return false;
		}

		if(arr[start] == 0) {
			return true;
		}
		
		traversed[start] = true;
		return canReachRec(arr, start+arr[start], traversed) || canReachRec(arr, start-arr[start], traversed);
    }
}
