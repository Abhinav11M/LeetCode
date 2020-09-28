package Zaikus;

import java.io.InputStreamReader;
import java.util.Scanner;

public class ZaikusMain {
	public static void main(String[] args) {
		Scanner s = new Scanner(new InputStreamReader(System.in));
		
		String s1 = s.next();
		String s2 = s.next();
		s.close();
		Zaikus z = new Zaikus();
//		System.out.println(z.find_string("ghijk", "ghijkghi"));
		System.out.println(z.find_string(s1, s2));
	}
}
