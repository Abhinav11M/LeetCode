package leetcode30daychallenge;

public class LeetCode30DayChallenge {
	/**
	 * Day1 :  Single Number
	 */
	public int singleNumber(int[] nums) {
		int val = nums[0];
		
		for(int i = 1; i < nums.length; ++i) {
			val = val^nums[i];
		}
		
		return val;
	}
	
	/**
	 * Day2 : Happy Number
	 */
	public boolean isHappy(int n) {
		int slow = squareOfDigits(n);
		int fast = squareOfDigits(slow);
		
		while(fast != slow) {
			slow = squareOfDigits(slow);
			fast = squareOfDigits(squareOfDigits(fast));
			if(fast == 1) {
				return true;
			}
		}
		return false;
	}

	private int squareOfDigits(int n) {
		int retVal = 0;
		while(n > 0) {
			retVal += Math.pow(n%10,2);
			n = n/10;
		}
		return retVal;
	}
}
