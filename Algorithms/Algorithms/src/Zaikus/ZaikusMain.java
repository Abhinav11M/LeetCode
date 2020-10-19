package Zaikus;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ZaikusMain {
	public static void main(String[] args) {
//		Scanner s = new Scanner(new InputStreamReader(System.in));
//		
//		String s1 = s.next();
//		String s2 = s.next();
//		s.close();
//		Zaikus z = new Zaikus();
//		System.out.println(z.find_string("ghijk", "ghijkghi"));
//		System.out.println(z.find_string(s1, s2));
		
		System.out.println(test(new int[] {4,3,2}, 7));
		System.out.println(test(new int[] {4,2,3}, 7));

		System.out.println(getMaxDel("URDR"));
	}
	
	public static boolean test(int[] arr, int minLen) {
		
		if(arr.length <= 2) {
			return true;
		}
		
		int len = Arrays.stream(arr).boxed().collect(Collectors.summingInt(x -> x));
		int left = 0, right = arr.length-1;
		
		while(true) {
			if(len - arr[left] >= minLen)  {
				len -= arr[left];
				++left;
			}
			else if(len - arr[right] >= minLen) {
				len -= arr[right];
				--right;
			}
			else {
				break;
			}
		}
		
		if(right - left == 1) {
			return true;
		}
		
		return false;

	}
	
	public static int getMaxDel(String str) {
		HashMap<Character, Integer> map = new HashMap<>();
		
		for(char ch : str.toCharArray()) {
			map.put(ch, map.getOrDefault(ch, 0)+1);
		}
		
		int up = map.getOrDefault('U', 0);
		int down = map.getOrDefault('D', 0);
		int left = map.getOrDefault('L', 0);
		int right = map.getOrDefault('R', 0);
		
		return (Math.min(up, down) + Math.min(left, right))*2;
	}
}
