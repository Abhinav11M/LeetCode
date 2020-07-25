package QueryProcessor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class QueryScheduler implements Runnable {
	private ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);
	private BlockingQueue<String> queryQueue = new LinkedBlockingQueue<>();
	private ExecutorService executor = Executors.newFixedThreadPool(8);
	
	public QueryScheduler() {
		// This thread will read from file/DB and add the queries to the list;
		scheduledExecutor.scheduleWithFixedDelay(()-> {
			// Add in the queryQueue based on the flag
			// If flag is false {
			// Clear queue;
		}, 10, 10, TimeUnit.SECONDS);
		
	}

	@Override
	public void run() {
		while(true) {
			try {
				String query = queryQueue.take();
				executor.submit(() -> {
					// use the query to update in the table;
				});
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
