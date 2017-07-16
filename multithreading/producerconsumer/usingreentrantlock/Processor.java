package multuthreading.producerconsumer.usingreentrantlock;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Processor {
	private LinkedList<Integer> list = new LinkedList<>();
	private final int LIMIT = 10;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void produce() throws InterruptedException {
		Random random = new Random();
		while (true) {
			lock.lock();
			System.out.println("Producing...");
			try {
				while (list.size() == LIMIT) {
					condition.await();
				}
				list.add(random.nextInt(100));
				System.out.println("produce() List size: " + list.size());
				condition.signal();
			} finally {
				lock.unlock();
			}
		}
	}

	public void consume() throws InterruptedException {

		while (true) {
			/*
			 * Wait for 200 millisecond so that Producer run faster than
			 * consumer.
			 */
			Thread.sleep(200);
			lock.lock();
			System.out.println("Consuming...");
			try {
				while (list.size() == 0) {
					condition.await();
				}
				list.removeFirst();
				System.out.println("consume() List size: " + list.size());
				condition.signal();
			} finally {
				lock.unlock();
			}
		}
	}
}
