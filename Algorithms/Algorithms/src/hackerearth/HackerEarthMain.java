package hackerearth;

public class HackerEarthMain {

	public static void main(String[] args) {
		MonkAndWilliomson2 obj = new MonkAndWilliomson2();
		
		System.out.println(obj.countHigh());
		obj.push(442);
		System.out.println(obj.countHigh());
		System.out.println(obj.countLow());
		obj.push(7555);
		System.out.println(obj.diff());
		obj.push(2799);
		System.out.println(obj.diff());
		obj.push(8543);
		System.out.println(obj.diff());
		System.out.println(obj.diff());
	}

}
