package educative.extras;

import java.util.ArrayList;
import java.util.List;

public class EducativeExtras {
	
	public static List<List<Integer>> findSubarrays(int[] arr, int target) {
		
		List<List<Integer>> result = new ArrayList<>();
		
		for(int i = 0; i < arr.length; ++i) {
			List<Integer> list = new ArrayList<>();
			list.add(arr[i]);
			findSubArrays(arr, target, i+1, result, list, arr[i]);
		}
		
		return result;
	}

	private static void findSubArrays(int[] arr, int target, int index, List<List<Integer>> result, List<Integer> list, int product) {
		if(product >= target || index >= arr.length) {
			return;
		}
		if(product < target) {
			result.add(new ArrayList<Integer>(list));
		}
		list.add(arr[index]);
		product = product * arr[index];
		findSubArrays(arr, target, index+1, result, list, product);
	}
	
	 public boolean subsetSumExists(int[] num, int sum) {
		 boolean[] dp = new boolean[sum+1];
		 
		 // Sum 0 can be achieved by not choosing anything
		 dp[0] = true;
		 for(int i = 0; i <= sum; ++i) {
//			 dp[i] = dp[]
		 }
		 
		 
		 return false;
	 }
}
