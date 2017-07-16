package multuthreading.producerconsumer.usingwaitnotify;

import java.util.LinkedList;
import java.util.Random;

public class Processor {
	private LinkedList<Integer> list = new LinkedList<>();
	private final int LIMIT = 10;
	private Object lock = new Object();

	public void produce() throws InterruptedException {
		Random random = new Random();
		while (true) {
			synchronized (lock) {
				while (list.size() == LIMIT) {
					lock.wait();
				}
				list.add(random.nextInt(100));
				lock.notify();
			}
		}
	}

	public void consume() throws InterruptedException {
		while (true) {
			synchronized (lock) {
				while (list.size() == 0) {
					lock.wait();
				}
				int v = list.removeFirst();
				System.out.println("List size: " + list.size() + " , removed value: " + v);
				lock.notify();
			}
		}
	}
}
