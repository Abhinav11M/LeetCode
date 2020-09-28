package hackerearth.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import helpers.Helpers;
import lombok.ToString;

@ToString
class Item {
	public int value;
	public int index;
	public Item(int val, int idx) {
		value = val;
		index = idx;
	}
}

public class AGameOfNumbers {
	
	public int[] solution(int[] numbers) {
		Stack<Item> stack = new Stack<>();
		
		if(numbers == null || numbers.length == 0) {
			return new int[0];
		}

		int[] result = new int[numbers.length];
		for(int i = 0; i < result.length; ++i) {
			result[i] = -1;
		}
		
		List<Item> curr = new ArrayList<>();

		int i = 0;
		while(i < numbers.length) {
			if(stack.isEmpty()) {
				stack.add(new Item(numbers[i], i));
			}
			else if(stack.peek().value < numbers[i]) { // Larger found
				while(!stack.isEmpty() && numbers[i] > stack.peek().value) {
					curr.add(stack.pop());
				}
				stack.add(new Item(numbers[i], i));
			}
			else {
				if(!curr.isEmpty()) {
					for(Item c : curr) {
						result[c.index] = stack.peek().index;
					}
					curr.clear();
				}
				stack.add(new Item(numbers[i], i));
			}
			++i;
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		int[] res = new AGameOfNumbers().solution(new int[] {3,7,1,7,8,4,5,2});
		Helpers.printArr(res);
	}
}
