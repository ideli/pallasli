package stack;

public class SyncStack {
	private int index = 0;
	private char[] data = new char[6];

	public synchronized void push(char c) {
		if (index == data.length) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}
		this.notify();
		data[index] = c;
		index++;
	}

	public synchronized char pop() {
		if (index == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}
		this.notify();
		index--;
		return data[index];
	}
}
