package leetcode30daychallenge;

public class LeetCode30DayChallengeMain {
	public static void main(String[] args) {
		LeetCode30DayChallenge leet = new LeetCode30DayChallenge();
		System.out.println(leet.singleNumber(new int[] {2,2,1}));
		System.out.println(leet.singleNumber(new int[] {4,1,2,1,2}));
		
		System.out.println(leet.isHappy(19));
		System.out.println(leet.isHappy(12));
	}
}
