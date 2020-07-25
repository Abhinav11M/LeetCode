package QueryProcessor;

public class QueryProcessorMain {
	public static void main(String[] args) {
		Thread t = new Thread(new QueryScheduler());
		t.start();
		try {
			t.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
