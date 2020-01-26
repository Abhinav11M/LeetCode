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
	
	
}
