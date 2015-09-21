package com.test.lock;

/**
 * 可重入锁，也叫做递归锁，指的是同一线程 外层函数获得锁之后 ，<br/>
 * 内层递归函数仍然有获取该锁的代码，但不受影响。 在JAVA环境下 <br/>
 * ReentrantLock和synchronized 都是 可重入锁<br/>
 * 
 * 可重入锁最大的作用是避免死锁<br/>
 * 
 * @author edwin
 * 
 */
public class SynchronizedTest implements Runnable {
	private SynchronizedResourse res;

	public SynchronizedTest(SynchronizedResourse res) {
		this.res = res;
	}

	@Override
	public void run() {
		res.reentrant();
		res.testSync();
	}

	public static void main(String[] args) {
		SynchronizedResourse res = new SynchronizedResourse();
		Thread t1 = new Thread(new SynchronizedTest(res));
		t1.start();

		Thread t2 = new Thread(new SynchronizedTest(res));
		t2.start();

		Thread t3 = new Thread(new SynchronizedTest(res));
		t3.start();
	}

}

class SynchronizedResourse {
	public void testSync() {
		this.get();
		this.set();
		staticGet();
		staticSet();
	}

	/**
	 * Reentrant Lock avoid dead lock
	 */
	public synchronized void reentrant() {
		printLog("reentrant");
		this.reentrant_2();
	}

	public synchronized void reentrant_2() {
		printLog("reentrant_2");
	}

	/**
	 * Lock Object(this)
	 */
	public synchronized void get() {
		printLog("get");
	}

	/**
	 * Lock Object(this)
	 */
	public synchronized void set() {
		printLog("set");
	}

	/**
	 * Lock Class(SynchronizedResourse.class)
	 */
	public static synchronized void staticGet() {
		printLog("staticGet");
	}

	/**
	 * Lock Class(SynchronizedResourse.class)
	 */
	public static synchronized void staticSet() {
		printLog("staticSet");
	}

	private static void printLog(String methodName) {
		System.out.println(String.format("%s-%s", Thread.currentThread()
				.getId(), methodName));
	}
}
