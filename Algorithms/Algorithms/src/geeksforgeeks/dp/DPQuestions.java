package geeksforgeeks.dp;

public class DPQuestions {

	/**
	 * Ugly Numbers
	 */
	public int getNthUglyNo(int n) {
		int i = 1;
		int counter = 0;
		while(true) {
			if(isUgly(i)) {
				++counter;
			}
			
			if(counter == n) {
				break;
			}
			++i;
		}

		return i;
	}
	
	public boolean isUgly(int num) {
		if(num == 1) {
			return true;
		}
		
		int[] factors = new int[] {2,3,5};
		
		return isUgly(num, factors, 0);
	}

	private boolean isUgly(int num, int[] factors, int index) {
		if(index >= factors.length) {
			return false;
		}
		
		if(num == factors[index]) {
			return true;
		}
		
		if(num % factors[index] != 0) {
			return isUgly(num, factors, ++index);
		}
		
		num = num/factors[index];
		
		return isUgly(num, factors, index);
	}
	
	// Optimize the above using DP
	public int getNthUglyNoDP(int n) {
		int num = 1;
		int counter = 0;
		boolean[] isUgly = new boolean[256];
		int[] factors = new int[] {2,3,5};
		
		while(true) {
			if(isUglyDP(num, factors, 0, isUgly)) {
				++counter;
			}
			
			if(counter == n) {
				break;
			}
			++num;
		}

		return num;
	}

	private boolean isUglyDP(int num, int[] factors, int index, boolean[] isUgly) {
		if(num == 1) {
			isUgly[num] = true; // Setting in the memoization array
			return true;
		}
		
		if(isUgly[num] == true) {
			return true;
		}
		
		if(index >= factors.length) {
			return false;
		}
		
		if(num == factors[index]) {
			isUgly[num] = true; // Setting in the memoization array
			return true;
		}
		
		if(num % factors[index] != 0) {
			return isUgly(num, factors, ++index);
		}
		
		num = num/factors[index];
		
		return isUglyDP(num, factors, index, isUgly);
	}
	
	// More optimized DP solution
	public int getNthUglyNoDPOpt(int n) {
		int[] ugly = new int[256];
		ugly[0] = 1;
		int idx2 = 0, idx3 = 0, idx5 = 0;
		
		for(int i = 1; i < n; ++i) {
			int nextMulOf2 = ugly[idx2] * 2;
			int nextMulOf3 = ugly[idx3] * 3;
			int nextMulOf5 = ugly[idx5] * 5;
			
			int nextUgly = minimum(nextMulOf2, nextMulOf3, nextMulOf5);
			ugly[i] = nextUgly;
			
			if(nextUgly == nextMulOf2) {
				++idx2;
			}
			if(nextUgly == nextMulOf3) {
				++idx3;
			}
			if(nextUgly == nextMulOf5) {
				++idx5;
			}
		}
		
		return ugly[n-1];
	}
	
	public int minimum(int a, int b, int c) {
		return Math.min(Math.min(a, b),c);
	}
	
	/**
	 * Fiboncci Number
	 */
	// Tabulation method
	public int fib(int n) {
		int[] f = new int[n+1];
		f[0] = 1;
		f[1] = 1;
		
		for(int i = 2; i <= n; ++i) {
			f[i] = f[i-1] + f[i-2];
		}
		
		return f[n];
	}
	
	// Memoization method
	public int fibMem(int n) {
		int[] f = new int[n+1];
		
		return fibMem(n, f);
	}

	private int fibMem(int n, int[] f) {
		if(f[n] != 0) { // found in the memoization array
			return f[n];
		}
		
		if(n == 0 || n == 1) {
			f[n] = 1;
			return f[n];
		}
		
		f[n] = fibMem(n-1) + fibMem(n-2);
		
		return f[n];
	}
	
	/**
	 * Catalan numbers : n = 0, 1, 2, 3,... are 1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862,...
	 * Recurrence relation : C(0) = 1 and C(n+1) = Summation[0 to n](C(i)*C(n-i))
	 */
	public int catalan(int n) {
		if(n == 0 || n == 1) {
			return 1;
		}
		
		int tab[] = new int[n];
		
		tab[0] = 1;
		tab[1] = 1;
		
		for(int i = 2; i < n; ++i) {
			int sum = 0;
			for(int j = 0; j < i; ++j) {
				sum += tab[j]*tab[i-1-j];
			}
			tab[i] = sum;
		}
		
		return tab[n-1];
	}
	
	/**
	 * Binomial Coefficient
	 * nCr = ((n-r+1)/r) * nCr-1
	 * Also, nCr = (n-1)C(r-1) + (n-1)C(r)
	 */
	public int binomialCoeff(int n, int r) {
		if(n == r || r == 0) { // nC0 and nCn is 1
			return 1;
		}
		
		int[] tab = new int[r+1];
		tab[0] = 1; // saved nC0
		
		for(int i = 1; i <= r; ++i) {
			tab[i] = ((n-i+1)*tab[i-1])/i;
		}
		
		return tab[r];
	}
	
	/**
	 * Permutation coefficient
	 * Recurrence Relation : nPr = (n-1)P(r) + r*(n-1)P(r-1)
	 */

	int permutationCoeff(int n, int k) {
		
		if(n == 0 || k == 0) {
			return 1;
		}
	
		// Create a table for n[p][r]
		int[][] data = new int[n+1][n+1];
		
		// nP0 is always 1
		for(int i = 0; i < n; ++i) {
			data[i][0] = 1;
		}
		
		
		for(int i = 1; i <= n; ++i) {
			for(int j = 1; j <= Math.min(i, k); ++j) { // Only need to calculate till nPk.. Don't need to go till nPn.
				data[i][j] = data[i-1][j] + j*data[i-1][j-1];
			}
		}
		
		return data[n][k];
	}
	
	/**
	 * Tiling Problem
	 * <a>https://www.geeksforgeeks.org/tiling-problem/</a>
	 */
	public int numWaysToTile(int n) {
		if(n == 1 || n == 2) {
			return n;
		}
		
		int[] data = new int[n];
		data[0] = 1; // for n = 1
		data[1] = 2; // for n = 2. So index i-1
		
		for(int i = 2; i < n; ++i) {
			data[i] = data[i-1] + data[i-2];
		}
		
		return data[n-1];
	}
	
	/**
	 * Gold Mine Problem => Incorrect
	 * Recursive approach (DP optimized below)
	 * TODO : Correct this recursive algo
	 */
	public int getMaxGold(int[][] mine) {
		// We don't know the starting position. So we will have to try for all the indexes in column 0.
		int max = Integer.MIN_VALUE;
		for(int r = 0; r < mine.length; ++r) {
			int maxGold = getMaxGold(mine, r, 0, mine[r][0]);
			max = maxGold > max ? maxGold : max;
		}
		
		return max;
	}

	private int getMaxGold(int[][] mine, int r, int c, int goldMax) {
		if(c == mine.length-1) { // Reached the end of column. Can't move
			// Return max from the cols
			return goldMax;
		}
		
		// find where to move
		int rightUp = Integer.MIN_VALUE, right = Integer.MIN_VALUE, rightDown = Integer.MIN_VALUE;

		if(r-1 >= 0) {
			rightUp = mine[r-1][c+1];
		}
		right = mine[r][c+1];
		if(r+1 < mine[0].length) {
			rightDown = mine[r+1][c+1];
		}
		
		int max = Math.max(rightUp, Math.max(right, rightDown));
		
		if(max == rightUp) {
			return getMaxGold(mine, r-1, c+1, goldMax+max);
		}
		else if(max == right) {
			return getMaxGold(mine, r, c+1, goldMax+max);
		}
		else {
			return getMaxGold(mine, r+1, c+1, goldMax+max);
		}
	}
	
	public int getMaxGoldDP(int[][] mine) {
		int nRows = mine.length;
		int nCols = mine[0].length;
		
		int[][] maxArr = new int[nRows][nCols];
		
		// Initialize last col with the mine values
		for(int i = 0; i < nRows; ++i) {
			maxArr[i][nCols-1] = mine[i][nCols-1];
		}
		
		for(int c = nCols-2; c >= 0; --c) {
			for(int r = 0; r <= nRows-1; ++r) {
				int rightUp = Integer.MIN_VALUE, rightDown = Integer.MIN_VALUE;
				
				if(r-1 >= 0) {
					rightUp = maxArr[r-1][c+1];
				}
				if(r+1 <= nRows-1) {
					rightDown = maxArr[r+1][c+1];
				}
				
				maxArr[r][c] = mine[r][c] + maxNext(rightUp, maxArr[r][c+1], rightDown);
			}
		}
		
		int maxGold = Integer.MIN_VALUE;
		for(int r = 0; r < nRows; ++r) {
			maxGold = maxArr[r][0] > maxGold ? maxArr[r][0] : maxGold;
		}
		
		return maxGold;
	}

	private int maxNext(int up, int right, int down) {
		return Math.max(up, Math.max(right, down));
	}
	
	/**
	 * Coin change problem
	 */
	
	public int countCoins(int coins[], int sum) {
		return countCoins(coins, sum, 0);
	}

	private int countCoins(int[] coins, int sum, int coinIdx) {
		if(sum == 0) {
			return 1;
		}
		if((coinIdx >= coins.length) || (sum-coins[coinIdx] < 0)) {
			return 0;
		}
		
		return countCoins(coins, sum-coins[coinIdx], coinIdx) + countCoins(coins, sum, coinIdx+1);
	}
	
	// Using DP
	public int countCoinsDP(int[] coins, int sum) {
		int[][] data = new int[sum+1][coins.length];
		
		for(int i = 0; i < coins.length; ++i) {
			data[0][i] = 1;
		}

		
		for(int i = 1; i <= sum; ++i) {
			for(int j = 0; j < coins.length; ++j) {
				// Count including the coin at index j
				int x = (i-coins[j] >= 0) ? data[i-coins[j]][j] : 0;
				
				// Count excluding the coin at index j
				int y = (j >= 1) ? data[i][j-1] : 0;
				
				data[i][j] = x+y;
			}
		}
		
		return data[sum][coins.length-1];
	}
	
	/**
	 * Friends Pairing Problem
	 * f(n) = f(n-1) + (n-1) * f(n-2)
	 */
	public int countFriendsPairings(int n) {
		int data[] = new int[n+1];
		data[0] = 1;
		data[1] = 1;
		
		for(int i = 2; i <= n; ++i) {
			data[i] = data[i-1] + (i-1) * data[i-2];
		}
		
		return data[n];
	}
	
	/**
	 * Subset Sum Problem in O(sum) space
	 * TODO: Write the DP algo
	 */
	boolean isSubsetSum(int arr[], int sum) {
//		Arrays.sort(arr); // If we don't sort, then we can't check sum < arr[idx]
		return isSubsetSum(arr, sum, 0);
	}

	private boolean isSubsetSum(int[] arr, int sum, int idx) {
		if(sum == 0) {
			return true;
		}
		
//		if(idx >= arr.length|| sum < arr[idx]) {
		if(idx >= arr.length) {
			return false;
		}
		
		// Including int at idx || excluding int at idx
		return (isSubsetSum(arr, sum-arr[idx], idx+1) || isSubsetSum(arr, sum, idx+1));
	}
	
	
	
	/**
	 * Longest increasing subsequence
	 */
	public int lis(int[] arr) {
		return lis(arr, 0, Integer.MIN_VALUE, 0);
	}

	private int lis(int[] arr, int index, int prevVal, int lis) {
		if(index == arr.length-1) {
			return 1;
		}
		
		
		if(prevVal < arr[index]) {
			
		}
		
		
		return 0;
	}
	
	
	
	
}
