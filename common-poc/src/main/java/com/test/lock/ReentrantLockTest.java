package com.test.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 1、ReentrantLock构造函数提供了2种公平性的选择：
 * 1）创建公平锁。如果锁已经被其他线程战友有，新的请求线程会加入到等待队列，或者已经有一些线程在等待锁了。
 * 2）创建非公平锁，线程按顺序请求获得公平锁，而一个非公平锁可以闯入
 * ，如果锁的状态可用，请求非公平锁的线程可在等待队列中向前跳跃，获得该锁。内部锁synchronized没有提供确定的公平性保证
 * 2、在synchronized和ReenrantLock之间进行选择
 * 在synchronized内部锁不能满足使用时，ReentrantLock才能做为更高级的工具。当你需要以下特性时，才应使用：
 * 1）可定时的、可轮询的与可中断的锁获取操作 2）公平队列或非块结构的锁
 * JAVA5。0中内部锁比ReentrantLock相比，有另一个优点：线程转储能显示哪些个调用框架获得了哪些锁
 * ，并能识别发生了死锁的那些线程。JAVA6解决了这个问题
 * 
 * @author edwin
 * 
 */
public class ReentrantLockTest implements Runnable {
	private SyncResourse res;

	public ReentrantLockTest(SyncResourse res) {
		this.res = res;
	}

	@Override
	public void run() {
		// res.nonFairReentrant();
		res.fairReentrant();
	}

	public static void main(String[] args) {
		SyncResourse res = new SyncResourse();
		Thread t1 = new Thread(new ReentrantLockTest(res));
		t1.start();

		Thread t2 = new Thread(new ReentrantLockTest(res));
		t2.start();

		Thread t3 = new Thread(new ReentrantLockTest(res));
		t3.start();
	}

}

class SyncResourse {
	/**
	 * non-fair-lock
	 */
	private ReentrantLock nonFairLock = new ReentrantLock(false);
	/**
	 * fair-lock
	 */
	private ReentrantLock fairLock = new ReentrantLock(true);

	/**
	 * Reentrant Lock avoid dead lock
	 */
	public void nonFairReentrant() {
		try {
			nonFairLock.lock();
			printLog("nonFairReentrant");
			this.nonFairReentrant_2();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			nonFairLock.unlock();
		}
	}

	public void nonFairReentrant_2() {
		try {
			nonFairLock.lock();
			printLog("nonFairReentrant_2");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			nonFairLock.unlock();
		}
	}

	/**
	 * Reentrant Lock avoid dead lock
	 */
	public void fairReentrant() {
		try {
			fairLock.lock();
			printLog("fairReentrant");
			this.fairReentrant_2();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fairLock.unlock();
		}
	}

	public void fairReentrant_2() {
		try {
			fairLock.lock();
			printLog("fairReentrant_2");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fairLock.unlock();
		}
	}

	private static void printLog(String methodName) {
		System.out.println(String.format("%s-%s", Thread.currentThread()
				.getId(), methodName));
	}
}
