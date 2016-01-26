package stack;

public class ProducerConsumer {
	public static void main(String args[]) {
		SyncStack stack = new SyncStack();
		Runnable p = new Producer(stack);
		Runnable c = new Consumer(stack);
		Thread p1 = new Thread(p);
		Thread c1 = new Thread(c);

		p1.start();
		c1.start();
	}
}
