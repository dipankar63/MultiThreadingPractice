package multuthreading.producerconsumer.usingwaitnotify;

public class App {

	public static void main(String[] args) {
		Processor processor = new Processor();
		Thread t1 = new Thread(() -> {
			try {
				processor.produce();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		Thread t2 = new Thread(() -> {
			try {
				processor.consume();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
