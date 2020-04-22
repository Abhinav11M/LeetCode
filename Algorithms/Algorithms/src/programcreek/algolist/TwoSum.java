package programcreek.algolist;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
	public void add(int val) {
		// Value -> Count
		data.put(val, data.getOrDefault(val, 0)+1);
	}
	
	public boolean find(int val) {
		for(int i : data.keySet()) {
			int target = val - i;
			if(data.containsKey(target)) {
				if(i == target && data.get(target) < 2) {
					continue;
				}
				return true;
			}
		}
		
		return false;
	}
	
	private Map<Integer, Integer> data = new HashMap<>();
}
