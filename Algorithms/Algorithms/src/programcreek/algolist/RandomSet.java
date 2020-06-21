package programcreek.algolist;

import java.util.HashMap;
import java.util.Random;

/**
 * 
 * Insert Delete and GetRandom in O(1)
 */
public class RandomSet {
	private HashMap<Integer, Integer> indexToVal = new HashMap<>();
	private HashMap<Integer, Integer> valToIndex = new HashMap<>();
	
	public boolean insert(int val) {
		if(valToIndex.containsKey(val)) {
			return false; // value is already inserted
		}
		
		valToIndex.put(val, valToIndex.size());
		indexToVal.put(indexToVal.size(), val);
		
		return true;
	}
	
	public boolean remove(int val) {
		if(!valToIndex.containsKey(val)) {
			return false; // value is not present in the map.
		}
		
		int index = valToIndex.get(val);

		if(index == valToIndex.size()-1) { // last index, no adjustment required
			valToIndex.remove(val);
			indexToVal.remove(index);
			return true;
		}
		
		// Adjust the maps
		int lastVal = indexToVal.get(indexToVal.size()-1);
		
		valToIndex.remove(val);
		valToIndex.put(lastVal, index);
		
		indexToVal.put(index, lastVal);
		indexToVal.remove(indexToVal.size()-1);
		
		return true;
	}
	
	public int getRandom() {
		return indexToVal.get(new Random().nextInt(indexToVal.size()));
	}
}
