package QueryProcessor;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

public class Test {
	
	@AllArgsConstructor
	@Getter
	@ToString
	public static class Emp {
		private String id;
		private int age;
	}
	
	public static void main(String[] args) {
		Emp[] arr = new Emp[] {new Emp("3", 12), new Emp("1", 12), new Emp("6", 12)};
		
		Arrays.sort(arr, (a, b) -> a.getId().compareTo(b.getId()));
		
		System.out.println();
	}
}
