package geeksforgeeks.dp;

public class DPMain {
	public static void main(String[] args) {
		DPQuestions dp = new DPQuestions();
		
		System.out.println(dp.getNthUglyNo(10));
		System.out.println(dp.getNthUglyNoDP(10));
		System.out.println(dp.getNthUglyNoDPOpt(10));
		
		System.out.println(dp.fib(10));
		System.out.println(dp.fibMem(10));
	}
}
