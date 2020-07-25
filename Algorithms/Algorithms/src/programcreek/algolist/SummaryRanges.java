package programcreek.algolist;

import java.util.TreeSet;

import educative.twoheaps.Interval;

/**
 * 
 * Data Stream as Disjoint Intervals
 */
class SummaryRanges {

	TreeSet<Interval> set;
	
    /** Initialize your data structure here. */
    public SummaryRanges() {
        set = new TreeSet<>((a,b) -> Integer.compare(a.start, b.start));
    }
    
    public void addNum(int val) {
    	Interval newInterval = new Interval(val, val);
        Interval floor = set.floor(newInterval);
        if(floor != null) {
        	if(floor.end >= val) {
        		return;
        	}
        	else if(floor.end == val-1) {
        		newInterval.start = floor.start;
        		set.remove(floor);
        	}
        }

        Interval ceil = set.higher(newInterval);
        if(ceil != null && ceil.start == val+1) {
        	newInterval.end = ceil.end;
        	set.remove(ceil);
        }
        
        set.add(newInterval);
    }
    
    public int[][] getIntervals() {
    	
    	int[][] res = new int[set.size()][2];
    	
    	int i = 0;
    	for(Interval val : set) {
    		res[i][0] = val.start;
    		res[i++][1] = val.end;
    	}
    	
    	return res;
    }
}