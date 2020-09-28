package Zaikus;

import java.util.ArrayList;
import java.util.List;

public class MinCost {
	public static void main(String[] args) {
		MinCost m = new MinCost();
		System.out.println(m.minCost(6, 24));
	}
	
	public int minCost(int s, int t) {
		return minCost(s, t, 0);
	}

	public int minCost(int s, int t, int c) {
		if(s == t || s > t) {
			return c;
		}
		
		// get all even factors of s
		List<Integer> factors = getEvenFactors(s);
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < factors.size(); ++i) {
			if(s+factors.get(i) > t) {
				break;
			}
			
			min = Math.min(minCost(s+factors.get(i), t, c+(s/factors.get(i))), min);
		}
		
		return min;
	}
	
	List<Integer> getEvenFactors(int num) {
		List<Integer> res = new ArrayList<>();

		for(int i = 2; i < num; ++i) {
			if(num%i == 0) {
				res.add(i);
			}
		}
		
		return res;
	}
}
