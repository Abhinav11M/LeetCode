package hackerrank;

import java.util.Scanner;

public class HackerRankMain {
	
	public static void main(String args[] ) throws Exception {
//        Scanner s = new Scanner(System.in);
//
//        // Write your code here
//       
//        int nTestCases = s.nextInt();
//        
//        for(int i = 0; i < nTestCases; ++i) {
//            int nSlots = s.nextInt();
//            System.out.println(calculateWays(nSlots));
//        }
//
//        s.close();
		
//		System.out.println(HackerRankQues.calculateWays(100));
		
		HackerRankQues ques = new HackerRankQues();
		System.out.println(ques.towerArrangement(new int[] {-1, -1}));
		System.out.println(ques.towerArrangement(new int[] {1, -1, 1, 1, -1, -1, -1}));

		System.out.println(ques.calculateWays(100));
		System.out.println(Math.pow(2, 4) - ques.f(4,0,0));
		
		ques.lines();

    }

    public static int calculateWays(int nSlots) {
        if(nSlots == 1) {
            return 2;
        }

        int[] dp = new int[nSlots+1];

        dp[1] = 2;

        for(int i = 2; i <= nSlots; ++i) {
            dp[i] = 2*dp[i-1] - 1;
        }
    
        return dp[nSlots];
    }
}
