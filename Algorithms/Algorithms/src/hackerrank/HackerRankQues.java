package hackerrank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HackerRankQues {

	/**
	 * This is wrong
	 */
	public long calculateWays(int nSlots) {
        if(nSlots == 1) {
            return 2;
        }

        long[] dp = new long[nSlots+1];

        dp[1] = 2;
        dp[2] = 3;

        for(int i = 3; i <= nSlots; ++i) {
//            dp[i] = 2*dp[i-1] - 1;
        	dp[i] = (2*dp[i-1]) + (long)Math.pow(2, i-2) - (2*dp[i-2]) + 1;
        }
    
        return dp[nSlots];
    }
	
	public int towerArrangement(int[] arr) {
		
		int[] neg = new int[arr.length];
		int prev = 0;

		for(int i = 0; i < arr.length; ++i) {
			if(arr[i] == -1) {
				neg[i] = prev+1;
				prev = neg[i];
			}
			else {
				neg[i] = prev;
			}
		}
		
		int minRes = Integer.MAX_VALUE;
		for(int i = 0; i < arr.length-1; ++i) {
			minRes = Math.min(minRes, i+1 - neg[i] + neg[neg.length-1] - neg[i]);
		}
		
		return minRes;
	}
	
	private static int[][][] dp = new int[10][2][3];
	private static boolean[][][] vis = new boolean[10][2][3];
	
	public int f(int idx, int prev, int cnt)
	{
	    if ( idx == 0 ) return (cnt >= 2 ? 1 : 0);
	    
	    if ( vis[idx][prev][cnt] ) return dp[idx][prev][cnt];
	    vis[idx][prev][cnt] = true;
	    
	    int ans = f(idx - 1, prev, cnt);
	    if ( cnt == 0 ) {
	        ans += f(idx - 1, idx%2, 1);
//	        if ( ans >= M ) ans -= M;
	    }
	    else {
	        if ( idx%2 != prev ) {
	            ans += f(idx - 1, idx%2, Math.min(cnt + 1, 2));
//	            if ( ans >= M ) ans -= M;
	        }
	    }
		
	    dp[idx][prev][cnt] = ans;
	    return ans;
	}
	
	public void lines() {
		Scanner sc = new Scanner(System.in);
		
		int lines = sc.nextInt();
		List<Integer> rList = new ArrayList<>();
		List<Integer> bList = new ArrayList<>();
		 	
		for(int i = 0; i < lines; ++i) {
			String line = sc.next();
			
			Map<Character, Integer> map = new HashMap<>();
			
			for(char ch : line.toCharArray()) {
				map.put(ch, map.getOrDefault(ch, 0)+1);
			}
			
			if(map.containsKey('R')) {
				rList.add(map.get('R'));
			}
			else {
				rList.add(0);
			}
			
			if(map.containsKey('B')) {
				bList.add(map.get('B'));
			}
			else {
				bList.add(0);
			}
		}
		
		int q = sc.nextInt();
		
		for(int i = 0; i < q; ++i) {
			int rr = sc.nextInt();
			int bb = sc.nextInt();
			
			int res = compute(rList, bList, rr, bb);
			System.out.println(res);
		}
		
		sc.close();
		
	}
	
	private int computeRec(List<Integer> rList, List<Integer> bList, int rr, int bb) {
		// Create the lower triangular matrix
        int[][] dpR = new int[201][201];
		int[][] dpB = new int[201][201];

		
		for(int i = 0; i <= rr; ++i) {
			for(int j = 0; j <= i; ++j) {
				dpR[i][j] = 1;
			}
		}

		for(int i = 0; i <= bb; ++i) {
			for(int j = 0; j <= i; ++j) {
				dpB[i][j] = 1;
			}
		}
		
		return computeRec(dpR, dpB, rList, bList, rr, bb, 0);
	}

	private int computeRec(int[][] dpR, int[][] dpB, List<Integer> rList, List<Integer> bList, int remR, int remB, int idx) {

        if(idx >= rList.size()) {
			return 0;
		}
		
		int val1 = 0, val2 = 0;
		
		// Can we adjust line idx
		if((dpR[remR][rList.get(idx)] != 0) && (dpB[remB][bList.get(idx)] != 0)) {
			val1 = 1 + computeRec(dpR, dpB, rList, bList, remR-rList.get(idx), remB-bList.get(idx), idx+1);
		}
		
		// Don't take value at idx
		val2 = computeRec(dpR, dpB, rList, bList, remR, remB, idx+1);
		
		return Math.max(val1, val2);
    }

	private int compute(List<Integer> rList, List<Integer> bList, int reds, int blacks) {
		
		int maxVal = Integer.MIN_VALUE;
		
		int n = rList.size();
		int[][][] dp = new int[n][reds+1][blacks+1];
		
		for(int r = rList.get(0); r <= reds; ++r) {
			for(int b = bList.get(0); b <= blacks; ++b) {
				dp[0][r][b] = 1;
			}
		}
		
		for(int i = 1; i < n; ++i) {
			int rC = rList.get(i);
			int bC = bList.get(i);
			
			for(int r = rC; r <= reds; ++r) {
				for(int b = bC; b <= blacks; ++b) {
					dp[i][r][b] = Math.max(dp[i-1][r][b], 1+dp[i-1][r-rC][b-bC]);
					maxVal = Math.max(maxVal, dp[i][r][b]);
				}
			}
			
		}
		
		return maxVal;
	}
}
