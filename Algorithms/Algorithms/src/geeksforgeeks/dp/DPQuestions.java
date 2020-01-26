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
	
}
