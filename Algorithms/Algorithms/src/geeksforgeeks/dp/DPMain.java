package geeksforgeeks.dp;

public class DPMain {
	public static void main(String[] args) {
		DPQuestions dp = new DPQuestions();
		
		// ===== Ugly Numbers =====
		System.out.println(dp.getNthUglyNo(10));
		System.out.println(dp.getNthUglyNoDP(10));
		System.out.println(dp.getNthUglyNoDPOpt(10));
		// ===== Ugly Numbers =====
		
		// ===== Fibonacchi series =====
		System.out.println(dp.fib(10));
		System.out.println(dp.fibMem(10));
		// ===== Fibonacchi series =====

		// ===== Catalan Numbers =====
		System.out.println(dp.catalan(7));
		// ===== Catalan Numbers =====

		// ===== Binomial Coefficient =====
		System.out.println(dp.binomialCoeff(7,3));
		System.out.println(dp.binomialCoeff(9,4));
		// ===== Binomial Coefficient =====
	}
}
