package multuthreading.producerconsumer.usingblockingqueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {

	public static void main(String[] args) {
		Processor processor = new Processor();
		ExecutorService executorService = Executors.newFixedThreadPool(20);
		for (int i = 0; i < 5; i++) {
			executorService.execute(() -> {
				try {
					processor.produce();
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}

		for (int i = 0; i < 6; i++) {
			executorService.execute(() -> {
				try {
					processor.consume();
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}

		executorService.shutdown();
		try {
			executorService.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
