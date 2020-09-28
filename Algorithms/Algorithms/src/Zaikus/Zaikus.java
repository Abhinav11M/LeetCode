package Zaikus;

import java.util.LinkedList;
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
	
	
	
	
	
	
	
	
}
