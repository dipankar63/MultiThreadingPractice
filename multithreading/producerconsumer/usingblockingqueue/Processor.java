package multuthreading.producerconsumer.usingblockingqueue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Processor {
	
	private BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

	public void produce() throws InterruptedException {
		System.out.println("Producing...");
	    	Random random = new Random();
		while (true) {
			queue.put(random.nextInt(10));
			System.out.println("Queue size after adding: " + queue.size());
		}
	}

	public void consume() throws InterruptedException {
		/*
		 * Wait the consumer thread for 2000 second so that producer thread
		 * kicks on first.
		 */
		Random random = new Random();
		Thread.sleep(200);
		System.out.println("Consuming...");
		while (true) {
			if (random.nextInt(10) == 0) {
				int v = queue.take();
				System.out.println("Queue size: " + queue.size() + " Removed value: " + v);
			}
		}

	}
}
