package Zaikus;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Zaikus {
	public int find_string(String infStr, String toFind) {
		if(toFind == null || toFind.equals("")) {
			return 1;
		}
		
		Queue<Character> queue = new LinkedList<>();
		// Add all infStr to the queue
		for(char ch : infStr.toCharArray()) {
			queue.add(ch);
		}
		
		// Align the heads of toFind and infStr
		char begin = toFind.charAt(0);
		int count = 0;
		while(!queue.peek().equals(begin) && count < queue.size()) {
			char ch = queue.poll();
			queue.add(ch);
		}
		if(count == queue.size()) {
			return 0;
		}
		
		for(int i = 0; i < toFind.length(); ++i) {
			char ch = toFind.charAt(i);
			if(!queue.peek().equals(ch)) {
				return 0;
			}
			
			queue.add(queue.poll());
		}
		
		return 1;
	}
	
	public boolean checkInclusion(String s1, String s2) {
		Map<Character, Integer> map = new HashMap<>();
		
		for(char ch : s1.toCharArray()) {
			map.put(ch, map.getOrDefault(ch, 0) + 1);
		}
		
		int left = 0, right = 0, nChars = 0;
		for(; right < s2.length(); ++right) {
			char ch = s2.charAt(right);
			if(map.containsKey(ch)) {
				if(map.get(ch) > 0) { // the char is not exhausted yet.
					map.put(ch, map.get(ch)-1);
					if(map.get(ch) == 0) { // matched 1 char
						++nChars;
					}
					if(nChars == map.size()) {
						return true;
					}
				}
				else { // the char is already exhausted.
					while(s2.charAt(left) != ch) {
						char newChar = s2.charAt(left);
						// If this char is already exhausted
						if(map.get(newChar) == 0) {
							--nChars;
						}
						map.put(newChar, map.get(newChar)+1);
						++left;
					}
					map.put(ch, map.get(ch)+1); // will be 1
					++left;
				}
				
			}
			else { // not found in the map
				// keep moving left till here and push all elements in the map
				while(left < right) {
					char newChar = s2.charAt(left);
					map.put(newChar, map.get(newChar) + 1);
					++left;
				}
				++left;
				nChars = 0;
			}
		}
		
		return false;
    }
	
	
	
	
	
	
}
