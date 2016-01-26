package stack;

//解决线程死锁的问题最好只锁定一个对象，不要同时锁定两个对象
public class TestDeadLock implements Runnable {
	public int flag = 1;
	static Object o1 = new Object(), o2 = new Object();

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "的flag=" + flag);
		/*
		 * 运行程序后发现程序执行到这里打印出flag以后就再也不往下执行后面的if语句了 程序也就死在了这里，既不往下执行也不退出
		 */

		/* 这是flag=1这个线程 */
		if (flag == 1) {
			synchronized (o1) {
				/* 使用synchronized关键字把对象01锁定了 */
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (o2) {
					/*
					 * 前面已经锁住了对象o1，只要再能锁住o2，那么就能执行打印出1的操作了
					 * 可是这里无法锁定对象o2，因为在另外一个flag=0这个线程里面已经把对象o1给锁住了
					 * 尽管锁住o2这个对象的线程会每隔500毫秒睡眠一次，可是在睡眠的时候仍然是锁住o2不放的
					 */
					System.out.println("1");
				}
			}
		}
		/*
		 * 这里的两个if语句都将无法执行，因为已经造成了线程死锁的问题 flag=1这个线程在等待flag=0这个线程把对象o2的锁解开，
		 * 而flag=0这个线程也在等待flag=1这个线程把对象o1的锁解开 然而这两个线程都不愿意解开锁住的对象，所以就造成了线程死锁的问题
		 */

		/* 这是flag=0这个线程 */
		if (flag == 0) {
			synchronized (o2) {
				/* 这里先使用synchronized锁住对象o2 */
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (o1) {
					/*
					 * 前面已经锁住了对象o2，只要再能锁住o1，那么就能执行打印出0的操作了
					 * 可是这里无法锁定对象o1，因为在另外一个flag=1这个线程里面已经把对象o1给锁住了
					 * 尽管锁住o1这个对象的线程会每隔500毫秒睡眠一次，可是在睡眠的时候仍然是锁住o1不放的
					 */
					System.out.println("0");
				}
			}
		}
	}

	public static void main(String args[]) {
		TestDeadLock td1 = new TestDeadLock();
		TestDeadLock td2 = new TestDeadLock();
		td1.flag = 1;
		td2.flag = 0;
		Thread t1 = new Thread(td1);
		Thread t2 = new Thread(td2);
		t1.setName("线程td1");
		t2.setName("线程td2");
		t1.start();
		t2.start();
	}
}
