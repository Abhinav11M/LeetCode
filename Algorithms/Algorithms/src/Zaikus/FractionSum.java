package Zaikus;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
class Fraction {
	public int num;
	public int den;
}

public class FractionSum {

	private static int $;

/*	public static void main(String[] args) {
		
	
////		int[] nums = new int[] { 2, 5, 4, 7, 5, 6 };
//		int[] nums = new int[] { 4, 7, 3, 5 };
//		List<Fraction> fractions = new LinkedList<>();
//
//		for (int i = 0; i < nums.length - 1; i += 2) {
//			fractions.add(new Fraction(nums[i], nums[i + 1]));
//		}
//		
//		int denom = fractions.get(0).den; // Final numerator
//		for(int i = 1; i < fractions.size(); ++i) {
//			Fraction b = fractions.get(i);
//			denom = lcm(denom, b.den);
//		}
//		
//		// final num
//		int numr = 0;
//		for(int i = 0; i < fractions.size(); ++i) {
//			Fraction f = fractions.get(i);
//			numr += (denom/f.den) * f.num;
//		}
//
//		System.out.println(new Fraction(numr, denom));

		int n;
		for (int i = 0; i < 5; i++) {
			n = getValue();
			assert n > 2;
			System.out.println(" " + n);

		}

	}*/
	
	public static void main(String[] args) {
		int val = getVal();
		System.out.println("Main : " + val);
	}
	
	public static int getVal() {
		int a = 10;
		try {
			throw new Exception("Exception");
		}
		catch(Exception e) {
			System.out.println("Catch : " + a);
			return a;
		}
		finally {
			a += 10;
			System.out.println("Finally : " + a);
//			return a; // We should not return from finally block
		}
	}

	static int val = 3;

	static int getValue() {
		return val--;
	}

	private static int gcd(int a, int b) {
		if (a > b) {
			return findGCD(b, a);
		} else {
			return findGCD(a, b);
		}
	}

	private static int findGCD(int a, int b) {
		if (a == 0) {
			return b;
		}

		return findGCD(b % a, a);
	}

	private static int lcm(int a, int b) {
		int gcd = gcd(a, b);
		return (a * b) / gcd;
	}

}
