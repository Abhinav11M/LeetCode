package Zaikus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class Point {
	int index;
	int value;

	Point(int i, int v) {
		this.index = i;
		this.value = v;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + index;
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (index != other.index)
			return false;
		if (value != other.value)
			return false;
		return true;
	}

}

public class MaxDistance {
	
	Map<Integer, Integer> map;
	int maxIdx;
	int minIdx;
	
	public MaxDistance() {
		map = new TreeMap<>();
		maxIdx = -1;
		minIdx = -1;
	}
	
	public void add(int i, int v) {
		if(maxIdx == -1) {
			maxIdx = i;
			minIdx = i;
		}
		else {
				if(v > map.get(maxIdx)) {
					maxIdx = i;
				}
			else if(v < map.get(minIdx)) {
				minIdx = i;
			}
		}
		map.put(i, v);
	}
	
	public void remove(int idx) {
		int val = map.get(idx);
		map.remove(idx);
		if(idx == maxIdx) {
			updateMaxIdx();
		}
		if(idx == minIdx) {
			updateMinIdx(val);
		}
	}
	
	private void updateMaxIdx() {
		// TODO Auto-generated method stub
		
	}

	private void updateMinIdx(int remVal) {
		for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
			int val = entry.getValue();
			if(val < remVal) {
				minIdx = entry.getKey();
				break;
			}
		}
	}

	public long calc(int i, int v) {
		add(i, v);
		if(v < map.get(minIdx)) {
			return Math.abs(map.get(maxIdx) - v);
		}
		else if(v >= map.get(minIdx) && v <= map.get(maxIdx)) {
			return Math.max(Math.abs(v-map.get(minIdx)), Math.abs(map.get(maxIdx) - v));
		}
		else {
			return Math.abs(map.get(minIdx) - v);
		}
	}
	
	public static long[] solve(int Q, int[][] queries) {
		
		MaxDistance m = new MaxDistance();
		
		List<Long> res = new ArrayList<>();
		
		for(int i = 0; i < Q; ++i) {
			int[] values = queries[i];
			
			if(values[0] == 1) {
				m.add(i+1, values[1]);
			}
			else if(values[0] == 2) {
				m.remove(values[1]);
			}
			else if(values[0] == 3) {
				res.add(m.calc(i+1, values[1]));
			}
		}
		
		long ret[] = new long[res.size()];
		for(int i = 0; i < res.size(); ++i) {
			ret[i] = res.get(i);
		}
		
		return ret;
	}
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter wr = new PrintWriter(System.out);
         int Q = Integer.parseInt(br.readLine().trim());
         int[][] Queries = new int[Q][2];
         for(int i_Queries = 0; i_Queries < Q; i_Queries++)
         {
         	String[] arr_Queries = br.readLine().split(" ");
         	for(int j_Queries = 0; j_Queries < arr_Queries.length; j_Queries++)
         	{
         		Queries[i_Queries][j_Queries] = Integer.parseInt(arr_Queries[j_Queries]);
         	}
         }

         long[] out_ = solve(Q, Queries);
         System.out.print(out_[0]);
         for(int i_out_ = 1; i_out_ < out_.length; i_out_++)
         {
         	System.out.print(" " + out_[i_out_]);
         }

         wr.close();
         br.close();
	}
}

